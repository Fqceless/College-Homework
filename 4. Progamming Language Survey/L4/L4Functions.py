# L4Functions.py
# Author: Christopher LaFave
# Desc:   Functions I wrote to make PLSLab4.py look prettier
# Date:   11/14/22

#Takes a string and keeps it within the 80 column rule
#If statement #1: 
#   -Keeps track of the last space in the string before column 80
#If statement #2
#   -When you hit column 80:
#       -Slice string from the start to the last space (excluding the space)
#       -Add a newline (replacing the space)
#       -Format the rest of the string (when it's 80+ columns) with RECURSION!
#When it gets to the end, it'll hit the return str and recurse back up.
#
#I'm so proud of this function please give me extra credit 

def format_under_80(str) -> str:
    for i in range(len(str)):
        if (i < 80 and str[i] == ' '):
            lastSpace = i
        if (i == 80):
            str = str[:lastSpace] + "\n" + format_under_80(str[lastSpace+1:])
    return str


#Takes the user's book input and formats it to the program's needs
#Programs needs:
#   -must be the whole book name capitalized
#   -must accept the abreviations from the .csv 
#(Gen or genesis) -> GENESIS is what we want!
#(Gen or genesis) -> Genesis, genesis, Gen, gEnEsIs etc. is wrong!

def format_book(b) -> str:
    bible_abs = open("Bible_Abbreviations.csv")
    for line in bible_abs:
        if(line[0:len(b)] == b and line[len(b)] == ','):
            b = line[len(b)+1:len(line)-1]
    bible_abs.close()
    return b.upper()
