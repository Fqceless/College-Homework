#include "headerFiles.h"

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
        unsigned char tmp2[] = {CMD, SUBN, CMD_WINDOW_SIZE, 0, TRMW, 0, TRMH, CMD, ESBN};
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

