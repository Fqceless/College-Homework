//**************************************************************
//Summary: Guess the number game. Picks a number between 1-20
//and gives 5 guesses, tells if high or low.
//
//Author: Chris LaFave
//Created 9/12/21
///*************************************************************

#include <iostream>
#include <string>
#include <ctime>  //I can use time() now
//i looked it up and apparently this is bad practice 
//but i don't care right now.
using namespace std;

int main(){

   //Unique Seed
   srand(time(0));

   //Calls variables
   int randNum = (rand() % 20) + 1;
   int userGuess;
   
   //Opening text
   cout << "Welcome to The Guessing Game! (patent pending)" << endl;
   cout << "The goal is to Guess! That! Number! Between 1-20!" << endl;
   cout << "Please, Guess! That! Number!" << endl;
   
   //main For loop that cin's userGuess
   for (int i = 0; i < 5; i++){
      cin >> userGuess;
      //nested if-else loop
      if (userGuess == randNum){
	 //good ending
         cout << "Congrats! A winner is you!" << endl;
	 return 0;
      }
      else if (userGuess > randNum){
	 cout << "That guess was TOOOOOOO High! Try again!" << endl;
      }
      else if (userGuess < randNum){
	 cout << "That guess was TOOOOOOO Low! Try again!" << endl;
      }
   }
   //bad ending
   cout << "OOF! You are out of guesses! A loser is you!" << endl;
   return 0;
}
