# ASK BEFORE ADDING IMPORTS
from socket import *
from ssl import *
from base64 import *
#import ssl #for some reason this works better with my editor
#import socket
 
NL = '\r\n'         # newline
OK_CODE = "250"     # OK message number
SMTP_SERVER  =  "smtp.gmail.com"  # use the links to look this up!
SSL_TLS_PORT =  465  # use the links to look this up!
# do not copy in the Base64 encodings of these 2 strings!!!
# use the Python library to derive the encoding from the normal strings
USERNAME = "whostevejobs@gmail.com"       # your fake gmail account
PASSWD = "REDACTED"         # you can obscure this when you hand in your code

# create a clientSocket and then the sslSocket
# this requires you to use the wrap_ function a little differently
# than the example in the API -- play around with it and you'll figure it out
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((SMTP_SERVER, SSL_TLS_PORT))

context = create_default_context()
sslSocket = context.wrap_socket(create_connection((SMTP_SERVER, SSL_TLS_PORT)), server_hostname=SMTP_SERVER)

# print cipher being used
print("Cipher: " + str(sslSocket.cipher()))

# use these functions to send and recv
def send(s) :
    sslSocket.send(s.encode())
    print("SENT:" + NL + s)
    
def recv(s="") :
    s += sslSocket.recv(1024).decode()
    print("RCVD:" + NL + s)

# send hello command...beware of NL gotcha!

send("EHLO localhost" + NL)

# here the server sends two messages with a short time gap in-between.
# the first is a greeting and the second is a series of 250 messages. 
# make sure you have received a 250 message before you “speak” again.
# to accomplish this, create a loop to receive 1 byte at a time (do not use the
# recv function above) until you receive the string "250" 
# build the bytes you receive onto a string, and then call the recv() function   
# passing the string you've accumulated as an argument.
# after this, for the remainder of the script, use the recv() function above
responseString = ""
while True:
    #responseString = responseString[1:]
    responseString += sslSocket.recv(1).decode()
    #print(responseString)
    if responseString.__contains__("250"):
        break

recv(responseString)
# now that it is your turn in the dialogue “to speak” again...
# send login command because Google makes you authenticate...

send("AUTH LOGIN" + NL)

recv()

# now follow the login prompts...

bytesUsername = USERNAME.encode(encoding='UTF-8')
encodedUserName = b64encode(bytesUsername)
stringEncodedUserName = encodedUserName.decode('UTF-8')

send(stringEncodedUserName + NL)

recv()

bytesPassword = PASSWD.encode(encoding='UTF-8')
encodedPassword = b64encode(bytesPassword)
stringEncodedPassword = encodedPassword.decode('UTF-8')

send(stringEncodedPassword + NL)

recv()

# now that you are authenticated, you can start the normal SMTP dialogue

send("MAIL FROM: <" + USERNAME + ">" + NL)

recv()

send("RCPT TO: <clafave@cedarville.edu>" + NL)

recv()

send("DATA" + NL)

recv()

send("SENT:" + NL +
"From: Someone" + NL +
"To:clafave@cedarville.edu" + NL +
"Reply-To: "+ USERNAME + NL +
"Subject: SMTP EMail" + NL)

send("Ligma ball" + NL +
"." + NL)

recv()
  
# send quit command
send("QUIT")

# close connection
sslSocket.close()
