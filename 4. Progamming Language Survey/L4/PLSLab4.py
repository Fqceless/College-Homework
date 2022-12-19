# PLSLab4.py
# Author: Christopher LaFave
# Desc:   Accepts a Bible verse from the user and prints it to an output file.
# Date:   11/14/22

import os           #clear screen function
import L4Functions  #functions I wrote for this lab

#open bible & output files

bible_file = open("Bible.txt")
out_file = open("verses.txt", 'w')


#start main loop

keep_going = ''
while(keep_going != 'N'):

    #Get user input
    #Automatically formats the book correctly

    print("Please enter your verse:")
    print(" book:", end = ' ')
    in_book = L4Functions.format_book(input().title())
    print(" chapter:", end = ' ')
    in_chap = input()
    print(" verse:", end = ' ')
    in_verse = input()


    #delcare booleans to help with program flow and error msgs

    book_found = False
    chap_found = False
    verse_found = False

    #Find correct book
    #If it hits eof, 
    #book_found = false and it skips to the error messages

    for line in bible_file:
        if (line[12:] == in_book + "\n"):
            book_found = True
            break    
    

    #Find correct chapter
    #If it hits another book,
    #chap_found = false and it skips to the error messages
    #
    #line[8:] checks for "CHAPTER " and line[6:] checks for "PSALM ".

    if (book_found == True):
        for line in bible_file:
            if(line[8:] == in_chap + "\n" or line[6:] == in_chap + "\n"):
                chap_found = True
                break
            elif(line[:12] == 'THE BOOK OF '):
                break


    #Find correct verse
    #If it hits another chapter/psalm,
    #verse_found = false and it skips to the error messages
    #
    #Inside the format function, it first adds on the book and chapter like so:
    #BOOK #:(line)
    #Then it sends it through the formatter, prints it, and writes it. 

    if (chap_found == True):
        for line in bible_file:
            if (line[:len(in_verse)] == in_verse):
                verse_found = True
                verse=L4Functions.format_under_80(in_book+" "+in_chap+":"+line)
                print(verse)
                out_file.write(verse + "\n")
                break
            elif(line[:3] == 'CHA' or line[:3] == 'PSA'):
                break


    #Error messages. Tells whether it's book/chapter/verse

    if (book_found == False):
        print("Book " + in_book + " not found")
    elif (chap_found == False):
        print("Chapter " + in_chap + " in book "  + in_book + " not found")
    elif(verse_found == False):
        print("Verse " + in_verse + " in chapter " + in_chap + " of book "
                       + in_book  + " not found")
        

    #Ask if the user would like to search again
    #Pesters user if it doesn't enter 'Y' or 'N'
    #Also handles 'y' and 'n'
    #Also also, the clear function doesn't work on VS2022, just pretend it does 

    print("Would you like to find another verse?")
    print("Please type Y/N")
    keep_going = input().upper()
    while(keep_going != 'Y' and keep_going != 'N'):
        os.system('cls')
        print("Please type Y/N")
        keep_going = input().upper()
    bible_file.seek(0)
    os.system('cls')

#End of main while loop

#Close files
bible_file.close()
out_file.close()


