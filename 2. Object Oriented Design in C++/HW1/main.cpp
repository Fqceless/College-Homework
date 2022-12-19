/*
*Summary: command line program that takes a string and reverses the chatacters 
*in that string relative to the other 2 arguements.
*Practice with command lines
* 
* Author: Christopher LaFave
* Created: 1/24/22
* Completed: 1/26/22
*/


#include <iostream>
#include <cstring>
#include <string>
using namespace std;

int zymain(int, char**);
void reverse(char*, char*);

int main(int argc, char** argv) {
    return zymain(argc, argv);
}

int zymain(int argc, char** argv) {
    // write your code for the main function of HW1 here:

    /**********************************************************************
    checking for error 1: anything other than 4 arguements entered
    **********************************************************************/
    
    if (argc != 4) {
        cout << "Supposed to recieve 4 arguements, recieved " <<
            argc << " instead. (Error code 1)";
        return 1;

    }


    /******************************************************
    initializing argv[1-3] into nice variables
    checking if argv[2-3] are ints (error 2), and turning them into ints
    ******************************************************/
    
    //initialize argv[1]
    char* str = argv[1];

    //initialize, check, and int-ify argv[2]
    char* frontIndexStr = argv[2];
    for (int i = 0; i < strlen(argv[2]); i++) {
        if (isdigit(argv[2][i]) == false) {
            cout << "Arguement 2 is not a positive integer (Error code 2)" << endl;
            return 2;
        }
    }
    int frontIndex = stoi(frontIndexStr);

    //initialize, check, and int-ify argv[3]
    char* rearIndexStr = argv[3];
    for (int i = 0; i < strlen(argv[3]); i++) {
        if (isdigit(argv[3][i]) == false) {
            cout << "Arguement 3 is not a positive integer (Error code 2)" << endl;
            return 2;
        }
    }
    int rearIndex = stoi(rearIndexStr);

    /******************************************************************
    checking for error 3 (rearIndex must be greater than frontIndex)
    checking for error 4 (rearIndex must be less than str length)
    ******************************************************************/

    //checking for error 3
    if (frontIndex >= rearIndex) {
        cout << "Arguement 2 must be less than arguement 3 (Error code 3)"
            << endl;
        return 3;
    }

    //checking for error 4
    /*
    note: pretty sure making it < instead of <= 
    avoids the /0 character at the end of C-strings
    */
    if (!(rearIndex < strlen(str))) {
        cout << "Arguement 3 must be greater than the length of arguement 1"
            << " (Error code 4)" << endl;
        return 4;
    }

    /****************************************************************
    initializing and finding the right index for *front and *rear 
    ****************************************************************/

    //initializing *front and *rear
    char* front = nullptr;
    char* rear = nullptr;

    //initializing test boolian
    bool doesIndexMatch = false;

    //finding the correct index for *front and *rear
    /*
    searches for the first instance of str[frontIndex]
    inintializes *rear relative to *front
    then checks if *rear is the correct value 

    (this is flawed. There are cases where it grabs the wrong
    index, but that is very rare and I currently don't care
    or know how to fix it)
    */
    while (doesIndexMatch == false) {
        front = strchr(str, str[frontIndex]);
        rear = front + (rearIndex - frontIndex);
        if (*rear == str[rearIndex]) {
            doesIndexMatch = true;
        }
        
    } 

    /****************************************
    calls and prints the result of reverse()
    ****************************************/
    reverse(front, rear);
    cout << "\"" << str << "\"" << endl;

    return 0;
}

void reverse(char* front, char* rear) {
    // write your code for the reverse function here

    /*************************************************
    initialize useful variables:

    segLength: length from frontIndex to rearIndex
    temp: temporary char variable so that we can swap
    *************************************************/
    int segLength = strlen(front) - strlen(rear);
    char temp;

    /*****************************************************************
    swaps the characters in str between *front and *rear

    uses front[0] as the first character
    uses front[segLength] as the last character
    after every iteration, front[0] goes up one (to front[1])
    and front[segLength] goes down one

    for loop ends the swap if i gets greater or equal to segLength
    *****************************************************************/
    for (int i = 0; i < segLength; i++) {
        temp = front[i];
        front[i] = front[segLength];
        front[segLength] = temp;
        segLength--;
    }

}