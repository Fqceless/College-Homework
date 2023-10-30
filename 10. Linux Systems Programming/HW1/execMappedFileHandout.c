// Author:   K. Shomper
// Date:     Sep 2019
// Purpose:  Starter code for HW1
//
//           The assignment gives students experience with C-style file I/O,
//           pointers, structures, interpreting file specifications, file
//           mapping, and a rudimetary understanding of an executables
//           image in virtual memory under the linux O/S.
//
//           The test file for this assignment is an executable named pow
//           which computes ostensibly x^y (e.g., 2^4 = 16). Students should
//           be careful to only use this executable, since it is written
//           to avoid any reference to C libraries to remove its dependence
//           on relocatable code.
//
// Modified: Jun 2022 to add additional instructions
//
// TODO:
// Students are to complete this program for CY3320 HW1 by completing the
// five steps below. To complete the five steps below, look for sections of 
// code having the string "...." and replace that string with your own code.
//
// The steps:
//  1. Open the file specified by argv[1] and map it to memory with the 
//     appropriate flags set and as a private mapping,
//  2  In the six sub-steps (a-f) of step 2, use various fields of the ELF 
//     header and section header to locate the symbol and string tables,
//  3. In the five sub-steps (a-e) of step 3, Use the symbol and string 
//     tables to locate the main() function entry,
//  4. Set a function pointer, called exe, to the main() entry point, and
//  5. Call exe to execute the argv[1] file from within the mmap segment.

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>
#include <string.h>
#include <elf.h>

#define  PROGRAM_BASE 0x08048000

typedef int (*exeFunc_t)(int, char **);

int main (int argc, char *argv[])
{
    // test for proper usage
    if (argc < 2) {
        fprintf (stderr, "usage: %s executableFile [args ...]\n", argv[0]);
        return 1;
    }

    // open specified file for read/write, exit on error with description
    int fd = open (argv[1], O_RDWR);
    if (fd == -1) {
        perror ("open");
        return 1;
    }

    // get information associated with file
    struct stat fileInfo;
    if (fstat (fd, &fileInfo) == -1) {
        perror ("fstat");
        return 1;
    }

    // ensure file is a "regular file", i.e., not a directory or symlink
    if (!S_ISREG (fileInfo.st_mode)) {
        fprintf (stderr, "%s is not a file\n", argv[1]);
        return 1;
    }

    // 1. map the file argv[1] to memory and allow access it it with fBuf 
    char *fBuf = mmap (0, fileInfo.st_size, PROT_READ | PROT_WRITE | PROT_EXEC, MAP_SHARED, fd, 0); // Need to modify slightly  /* .... replace NULL with correct mapping

    // exit on any error with the file mapping
    if (fBuf == NULL || fBuf == MAP_FAILED) {
        if (fBuf == NULL) {
           fprintf (stderr, "file %s not mapped\n", argv[1]);
           return 1;
        }
        perror ("mmap");
        return 1;
    }

    // with the file now accessible as an in-memory character buffer,
    // close the file to reclaim the file descriptor 
    if (close (fd) == -1) {
        perror ("close");
        return 1;
    }

    // set the ELF Header
    Elf32_Ehdr *e = (Elf32_Ehdr *) fBuf;

    // if the file is NOT executable, then exit
    if (e->e_type != ET_EXEC) {
        fprintf (stderr, "%s is not an executable file\n", argv[1]);
        return 1;
    }

    // save the index of the section header string table
    int shstrndx = e->e_shstrndx;

    #ifdef DEBUG
    printf("\nThe section index for the section header string table is %d\n",
           shstrndx);
    #endif

    // 2a. use the e_shoff field in the ELF header to set a pointer to the
    //     section header table (sht)
    Elf32_Shdr *sht = (Elf32_Shdr *) (e->e_shoff + fBuf);

    // 2b.ar use sht pointer and the section header string table index saved
    //     above and sh_offset in the section header table to set a pointer
    //     to the section header string table 
    char       *shst = (char *) (sht[shstrndx].sh_offset + fBuf);
    #ifdef DEBUG
    printf("\n");
    #endif
    // loop through all the section headers in sht to find the indices for 
    // the symbol table (sh_name is .symtab) and the string table (.strtab)
    int nument, symtabndx, strtabndx;
    for (int c = 0; c < e->e_shnum; c++) {

       // 2c. determine the index value for the name of the current section
       int   iname = sht[c].sh_name;

       // 2d.look in the section header string table at the current 
       // sections's name index to find the current section's C-string name
       char *cname = &shst[iname];

       #ifdef DEBUG
       printf("Section table entry[%d]: %s %d %d %d %x %x %d %d\n",
               c,
               cname,
               sht[c].sh_name,
               sht[c].sh_type,
               sht[c].sh_flags,
               sht[c].sh_addr,
               sht[c].sh_offset,
               sht[c].sh_size,
               sht[c].sh_entsize);
       #endif

       // 2e. find the section named ".symtab" and save the index of 
       // this section and the number of entries it hold in the variables
       // symtabndx and nument, respectively
       if (strcmp(cname, ".symtab") == 0) {
	  symtabndx = c;
          nument = sht[c].sh_size/sht[c].sh_entsize;
       }
       
       // 2f. find the section named ".strtab" and save the index of
       // this section in the variable strtabndx
       if (strcmp(cname, ".strtab") == 0) {
          strtabndx = c;
       }

    }
       
    #ifdef DEBUG
    printf("symtabndx = %d, nument = %d, strtabndx = %d\n", 
            symtabndx, nument, strtabndx);
    #endif
   

    // 3a. Use the index of the symbol table to set a pointer to the
    //     beginning of this table
    Elf32_Sym  *symtab = sht[symtabndx].sh_offset + fBuf;

    // 3b. Use the index of the string table to set a pointer to the
    //     beginning of this table
    char       *strtab = sht[strtabndx].sh_offset + fBuf;

    // search for the symbol "main"
    int mainndx, found = 0;
    for (int c = 0; c < nument; c++) {
 
       // 3c. Determine the index value for the name of the current symbol
       int   name = symtab[c].st_name;

       // 3d. Look in the string table at the current symbol's name index 
       //     to find the current symbol's name as a C-string
       char *cname =  &strtab[name];

       #ifdef DEBUG
       printf("%d: %d %s %x\n", c, name, cname, symtab[c].st_value);
       #endif

       // 3e. Compare the symbol's C-string name with "main" and exit the
       //     loop with the symbol index for "main" when matched
       if (strcmp(cname, "main") == 0) {
          mainndx = c;
	  found = 1;
       }
    }

    // if the main function was not found, then exit
    if (!found) {
       printf("main() not found in %s\n", argv[1]);
       exit(1);
    }

    // 4a. Compute the real offset of this section in the executable file
    //     NOTE: the symbol table's st_value represents a virtual address;
    //     however, we need the byte offset into the mapped file. Therefore, 
    //     use the PROGRAM_BASE constant defined at the beginning of this
    //     program to calculate a byte offset.
    int off = symtab[mainndx].st_value - PROGRAM_BASE;
	
    // 4b. Set a function pointer, called exe, to the start of the program 
    //     segment for the main() function using the exeFunc_t typedef
    exeFunc_t exe = (exeFunc_t) (fBuf + off);
    
    // set up any arguments from the command-line for the in-memory program
    char *args[argc-1];
    for (int i = 0; i < argc-1; i++) {
       args[i] = argv[i+1];
    }

    // 5. Use exe to run the memory-mapped program
    printf("Executing %s from memory ...\n\n", argv[1]);
    int rc = exe(0, NULL);

    // allow program to wait for user input on stdin before terminating
    printf("\nReturned from memory-executed program w/return code %d\n", rc);
    puts("Waiting for input ... ");
    getchar();

    // unmap the file, exit on error
    if (munmap (fBuf, fileInfo.st_size) == -1) {
        perror ("munmap");
        return 1;
    }

    return 0;
}
