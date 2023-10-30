#include <stdio.h>
#include <string.h>
int main(int argc, char *argv[]) {

   if (argc < 2) {
      fprintf(stderr, "Usage: %s string\n", argv[0]);
      return 1;
   }

   char buf[80] = "";

   if (argc == 2)
      printf("[BEFORE COPY] buf is at %p and contains \'%s\'\n", buf, buf);

   strcpy(buf, argv[1]);
   
   if (argc == 2)
      printf("[AFTER  COPY] buf is at %p and contains \'%s\'\n", buf, buf);

   return 0;
}
