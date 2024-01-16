#Grabs function that translates from numbers to a letter
from GetChar import GetChar

#Open the string of 0, 2, -2's
f = open("PA4_transmission.txt", "r")
dataArray = [int(i) for i in f.readline().split(",")]

#Code to de-tangle the messages
C1 = [1,1,-1,1]
C2 = [-1,1,-1,-1]

M1 = []
M2 = []

FM1 = ""
FM2 = ""

#Checks for + or - 2's, all of them will be the same sign so once it finds one it jumps to the next sequence
for i in range(0, len(dataArray), 4):
    for j in range(0, 3):
        if dataArray[i + j] * C1[j] == 2:
            M1.append(1)
            break
        elif dataArray[i + j] * C1[j] == -2:
            M1.append(-1)
            break

    for j in range(0, 3):
        if dataArray[i + j] * C2[j] == 2:
            M2.append(1)
            break
        elif dataArray[i + j] * C2[j] == -2:
            M2.append(-1)
            break

#Translates from 5 number sequence to letter
for i in range(0, len(M1), 5):
    FM1 += GetChar(M1[i:i+5])
    FM2 += GetChar(M2[i:i+5])

#Print final message
print("Message #1:\n" + FM1)
print("Message #2:\n" + FM2)
