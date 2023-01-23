/* Server.c
 * Author: Christopher LaFave
 * Borrowed Code:
 * https://vcansimplify.wordpress.com/2013/03/14/c-socket-tutorial-echo-server/
 * Created: January 22nd, 2023
 * Summary:
 *   A server that can read a message from a client, modify the message,
 *   and send the modified message back to the console.
 */


/*Required Headers*/
 
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
 
int main()
{
    char str[100];
    int listen_fd, comm_fd;
 
    struct sockaddr_in servaddr;
 
    listen_fd = socket(AF_INET, SOCK_STREAM, 0);
 
    bzero( &servaddr, sizeof(servaddr));
 
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htons(INADDR_ANY);
    servaddr.sin_port = htons(32069);
 
    bind(listen_fd, (struct sockaddr *) &servaddr, sizeof(servaddr));
 
    listen(listen_fd, 10);

    while(1)
    {
	comm_fd = accept(listen_fd, (struct sockaddr*) NULL, NULL);
	    
	bzero(str, 100);
 
        read(comm_fd,str,100);
	
	for(int i = 0; i < strlen(str); i++){
		if(str[i] >= 'a' && str[i] < 'z' || 
		   str[i] >= 'A' && str[i] < 'Z'){
			str[i]++;
		}
		else if(str[i] == 'z'){
			str[i] = 'a';
		}
		else if(str[i] == 'Z'){
			str[i] = 'A';
		}
	}
	
	write(comm_fd, str, strlen(str)+1);

	
    }
}
