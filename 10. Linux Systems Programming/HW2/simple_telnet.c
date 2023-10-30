/*
    SimpleTelnet: a simple telnet client suitable for embedded systems
    Copyright (C) 2013  netblue30@yahoo.com

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Modified by K. Shomper to support CY3320: Linux System Programming, 2022
*/

#include "headerFiles.h"
 
int isRawMode = 0;

// these defined constants are used in the negotiate function
#define ESBN 0xf0
#define SUBN 0xfa
#define WILL 0xfb
#define WONT 0xfc
#define DO   0xfd
#define DONT 0xfe
#define CMD  0xff

#define TRMW   80
#define TRMH   24

#define CMD_ECHO 1
#define CMD_WINDOW_SIZE 31
 
// handles the handshake with the telnet server to establish the telnet session
void negotiate(int sock, unsigned char *buf, int len) {
     
	 // communicate to the remote application the local terminal's window size
    if (buf[1] == DO && buf[2] == CMD_WINDOW_SIZE) {
        unsigned char tmp1[] = {CMD, WILL, CMD_WINDOW_SIZE};
        if (write(sock, tmp1, 3) < 0)
            exit(1);
         
        unsigned char tmp2[] = {CMD, SUBN, CMD_WINDOW_SIZE, 0, TRMW, 
											 0, TRMH, CMD, ESBN};
        if (write(sock, tmp2, 9) < 0)
            exit(1);
        return;
    }
     
	 // appears to be replying to the remote app, that this app WONT
    // accept commands, but WILL respond to requests.
    int i;
    for (i = 0; i < len; i++) {
        if (buf[i] == DO)
            buf[i] = WONT;
        else if (buf[i] == WILL)
            buf[i] = DO;
    }
 
	 // send that feedback
    if (write(sock, buf, len) < 0)
        exit(1);
}
 
// stores the original terminal configuration
struct termios tin;
 
// set the local terminal configuration to allow "raw" I/O
void terminal_set(void) {
    // save terminal configuration
    tcgetattr(STDIN_FILENO, &tin);
     
	 // make a copy of the present configuration
    static struct termios tlocal;
    memcpy(&tlocal, &tin, sizeof(tin));

	 // turn on "raw" I/O
    cfmakeraw(&tlocal);

	 // make its effect immediate
    tcsetattr(STDIN_FILENO,TCSANOW,&tlocal);

    isRawMode = 1;
}
 
// restore original terminal configuration upon exit
void terminal_reset(void) {
    tcsetattr(STDIN_FILENO,TCSANOW,&tin);
}
 
// setup the file descriptors for stdin and the telnet socket
void setupFds(int sock, fd_set *fds) {
        FD_ZERO(fds);
        if (sock != 0)
            FD_SET(sock, fds);
        FD_SET(0, fds);
}
 
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

// simple telnet client:  takes the ip address of a telnet sever and optionally
// a port number (default 23)
int main(int argc , char *argv[]) {

    int sock;                          // file descriptor for the connection
    struct sockaddr_in server;         // information for connecting to remote
    int rc;                            // return code for checkSocket
 
	 // check usage
    if (argc < 2 || argc > 3) {
        printf("Usage: %s address [port]\n", argv[0]);
        return 1;
    }

	 // assign port
    int port = 23;
    if (argc == 3)
        port = atoi(argv[2]);
 
    // create socket
    sock = socket(AF_INET , SOCK_STREAM , 0);
    if (sock == -1) {
        perror("Could not create socket. Error");
        return 1;
    }
 
	 // set the connection attributes
    server.sin_addr.s_addr = inet_addr(argv[1]);
    server.sin_family = AF_INET;
    server.sin_port = htons(port);
 
    // connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0) {
        perror("connect failed. Error");
        return 1;
    }
    puts("Connected...\n");
 
    // set local terminal configuration
    terminal_set();

	 // restore original configuration on exit from application
    atexit(terminal_reset);
     
	 // wait one second between polls for data
    struct timeval ts;
    ts.tv_sec = 1; // 1 second
    ts.tv_usec = 0;
 
    // loop infinitely -- program will terminate when connection is terminated
    while (1) {

        // select setup
        fd_set fds;
        setupFds(sock, &fds);
 
        // wait for data
        int nready = select(sock + 1, &fds, (fd_set *) 0, (fd_set *) 0, &ts);
        if (nready < 0) {
            perror("select. Error");
            return 1;
        }

		  // no data, then reset wait time
        else if (nready == 0) {
            ts.tv_sec = 1; 
            ts.tv_usec = 0;
        }

		  // there is data to receive on the socket
        else if ((rc = checkSocket(sock, &fds))) {
           if (rc == 2) return 1;
           if (rc == 3) return 0;
        }
         
		  // there is data on stdin to send out to the socket
        else {
           int rc = checkStdin(sock, &fds);

           if (rc == 1) {
                return 1;
           }
        }
    }

    close(sock);

    return 0;
}

