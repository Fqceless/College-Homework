#include "headerFiles.h"

// read the telnet socket
int readSocket(int sock, unsigned char *buf, int len) {
   int rv = 0;
   // original code used recv(sock,buf,1,0) replaced w/equiv. read(sock,buf,1)
   if ((len = read(sock, buf, len)) < 0) {
      rv = 2;
   } else if (len == 0) {
      // original code used printf() - moified to use write()
      write(1, "Connection closed by the remote end\n\r", 37);
      rv = 3;
   }
   return rv;
}
