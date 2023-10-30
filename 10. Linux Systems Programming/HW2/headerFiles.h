// headers for various functions
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <termios.h>
#include <fcntl.h>

// common define for checkSocket() and negotiate()
#define CMD  0xff

// size of communication buffer between local and remote
// common between main(0 and checkSocket()
#define BUFLEN 20

