#include "headerFiles.h"

// setup the file descriptors for stdin and the telnet socket
void setupFds(int sock, fd_set *fds) {
        FD_ZERO(fds);
        if (sock != 0)
            FD_SET(sock, fds);
        FD_SET(0, fds);
}
