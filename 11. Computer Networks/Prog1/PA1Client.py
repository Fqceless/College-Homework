import sys
from socket import *

#Check for correct number of arguments
if len(sys.argv) != 4:
    print('Incorrect number of arguments')
    exit()

#Grab commandline args
serverName = str(sys.argv[1])
serverPort = int(sys.argv[2])
fileToGrab = str(sys.argv[3])

#Create and connect socket
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

#Send message
sentenceToSend = 'GET ' + fileToGrab + ' HTTP/1.1\r\nHost: ' + serverName + '\r\n\r\n'
clientSocket.send(sentenceToSend.encode())

#Recieve Message
printSentence = ""
while True:
    modifiedSentence = clientSocket.recv(1024)
    printSentence += modifiedSentence.decode()
    if modifiedSentence.decode() == "":
        break
print(printSentence)
clientSocket.close()