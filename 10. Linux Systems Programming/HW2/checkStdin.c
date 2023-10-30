#include "headerFiles.h"

// check to see if there is data to read from stdin
int checkStdin(int sock, fd_set *fds) {
   // two characters are enough in this context
   unsigned char buf[2];
   char          c = '\r';

   if (FD_ISSET(0, fds)) {

           // read a single char - original code (see below) used getc(), modified
      // buf[0] = getc(stdin);
      read(0, buf, 1);

           // send the char over the socket - original code used send(sock,buf,1,0)
      // replaced this with equivalent write(sock,buf,1)
      if (write(sock, buf, 1) < 0)
         return 1;

           // with the terminal in raw mode we need to force a line feed
      // original code used putchar('\r') - modified to use write(1, &c, 1)
      if (buf[0] == '\n')
         write(1, &c, 1);
   }

   return 0;
}
