//*******************************************************************
//Summary: Making a Simon Says game, to become familiar with arrays
//
//Author: Chris LaFave
//Created: 9/20/21
//Updated: 9/21/21 (Finished up)
//*******************************************************************

//Stuff the internet solutions I found needed
#include <iostream>
#include <string>
#include <time.h>
#include <unistd.h>
using namespace std;

int main(){
   srand(time(0)); //so that the pattern is true random

   char pattern [15]; //calls an array of 15 chars
   int letter; 
   string patternString;
   string userPattern;
   string answer;

   //To pick the random pattern
   for (int i = 0; i < 15; i++){
      letter =  rand() % 4;
      if (letter == 0){
         pattern[i] = 'R';
      }
      else if (letter == 1){
         pattern[i] = 'B';
      }
      else if(letter == 2){
         pattern[i] = 'G';
      }
      else if (letter == 3){
         pattern[i] = 'Y';
      }
      else{ //just in case
         cout << "Something goofed!" << endl;
	 return 0;
      }
   }

   //So that I can reveal the answer at the end
   for (int i = 0; i < 15; i++){
      answer += pattern[i];
   }
   
   cout << "Welcome to Simon Says! Press enter to play!" << endl;
   cin.ignore(); //so that it waits for the enter call
   
   
   for (int i = 0; i < 15; i++){ //15 rounds
      cout << "\014" << flush;  //screen wipe
      patternString += pattern[i]; //updates the key
      cout << "Simon Says: ";

      for (int j = 0; j <= i; j++){ //prints one letter more letter each time
         cout << pattern[j] << flush;; 
         sleep(1);  //pauses between letters and dots
         cout << "\010." << flush; //delete and add a period
      }

      cout << endl;
      cout << "Type the pattern: ";

      cin >> userPattern;

      if (userPattern != patternString){ //checks if user is right
         cout << "You lose! The pattern was: " << answer << endl;
	 return 0; //terminates the program
      }

      cout << endl;

   }

   cout << "You win! Yay!" <<endl;

}
   





