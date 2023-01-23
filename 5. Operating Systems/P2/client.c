/* Client.c
 * Author: Christopher LaFave
 * Borrowed Code: 
 * https://vcansimplify.wordpress.com/2013/03/14/c-socket-tutorial-echo-server/
 * Created: January 22nd, 2023
 * Summary:
 *   A client that can send a message to a server, and then paste
 *   that message into the console.
 */ 
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
 
int main(int argc,char **argv)
{
    int sockfd,n;
    char sendline[100];
    char recvline[100];
    struct sockaddr_in servaddr;
 
    if(argc != 2){
	printf("Incorrect number of arguments: %i\n", argc);
	return 0;
    }
    
    sockfd=socket(AF_INET,SOCK_STREAM,0);
    bzero(&servaddr,sizeof servaddr);
 
    servaddr.sin_family=AF_INET;
    servaddr.sin_port=htons(32069);
 
    inet_pton(AF_INET,"127.0.0.1",&(servaddr.sin_addr));
 
    connect(sockfd,(struct sockaddr *)&servaddr,sizeof(servaddr));
    
    write(sockfd,argv[1],strlen(argv[1])+1);
    read(sockfd,recvline,100);
    printf("%s\n",recvline);
}
