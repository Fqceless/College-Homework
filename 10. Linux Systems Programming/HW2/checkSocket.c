#include "headerFiles.h"

// check to see if there is data to read or write at the telnet server
int checkSocket(int sock, fd_set *fds) {
   // buf - the character array for communication with the telnet server
   // rc  - the return code for checkSocket as expected by the caller
   //   0 - if statement not taken
   //   1 - if statement taken, normal case, let caller keep looping
   //   2 - if statement taken, error in readSocket(), notify caller to exit
   //   3 - if statement taken, connection closed in readSocket(), caller exits
   unsigned char buf[BUFLEN+1];
   int           rc = 0;;

   if (sock != 0 && FD_ISSET(sock, fds)) {

      // start by reading a single byte - readSocket() returns 0 on success or
      // 2 or 3 on error
      if ((rc = readSocket(sock, buf, 1))) { return rc; }

                // handle command messaages separate from "ordinary" data
      if (buf[0] == CMD) {
         // read 2 more bytes - readSocket() returns 0, 2 or 3
         if ((rc = readSocket(sock, buf+1, 2))) { return rc; }
         negotiate(sock, buf, 3);

                // print received data - the original code used printf() and fflush()
      // modified to write() to stderr to avoid need for fflush()
      } else {
         write(2, buf, 1);
      }

      // indicate if statement was taken
      rc = 1;
   }

   return rc;
}
