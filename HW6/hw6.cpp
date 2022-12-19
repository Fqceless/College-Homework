//************************************************************************
//Summary: To check if a 2d matrix is a magic square.
//Also to become more familiar with multi-dimentional arrays.
//
//Author: Chris LaFave
//Created: 9/22/21
//Updated: 9/27/21 (Completed)
//************************************************************************


#include <iostream>
#include <string>
#include <cmath>
#include <array>
using namespace std;

int main(){
   //Declare the vars!
   int arraySize;
   int userNum;
   int rowSum = 0;
   int columnSum = 0;
   int diagonalSum = 0;
   int sumChecker;
   bool validVal = false;

   cout << "What size would you like the square to be?" << endl;
   cin >> arraySize; //How big will the square be?

   int magicSquare [arraySize][arraySize];
   
   //main function for populating the array
   for (int i = 0; i < arraySize; i++){
      for (int j = 0; j < arraySize; j++){
         validVal = false; //resets checker to default
         while (validVal == false){
            cout << "Please enter the value (1 - " << pow(arraySize, 2) << ") ";
            cout << "for position (" << i << ", " << j << ")" << endl;
            
	    cin >> userNum;
	       //checks if the userNum is out of range
	       if (userNum <= 0 || userNum > pow(arraySize, 2)){
                  cout << "Input number out of range, please try another." << endl;
	       }
	       else{
                  validVal = true;
	       }
	    }
	    magicSquare[i][j] = userNum;
       }
   }

   //Number check
   cout << "Number Check: ";
   for (int i = pow(arraySize, 2); i > 0; i--){
      validVal = false;
      for (int j = 0; j < arraySize; j++){
         for (int k = 0; k < arraySize; k++){
            if (i == magicSquare[j][k]){
               validVal = true;
	    }
	 }
      }	 
	    if (validVal == false){
               cout << "Not all numbers 1 - " << pow(arraySize ,2) << " exist.";
               cout << endl;
	       return 0;
	    }      
   }
   cout << "All numbers 1 - " << pow(arraySize, 2) << " are present." << endl;
   cout << endl;

   //Row check
   cout << "Row Check: " << endl;
   for (int i = 0; i < arraySize; i++){
      rowSum = 0;
      for (int j = 0; j < arraySize; j++){
         rowSum += magicSquare[i][j];
      }
      if (i == 0){
         sumChecker = rowSum;
      }

      cout << "Row " << i << " sum: " << rowSum << endl;

      if (rowSum != sumChecker){
         cout << "The rows don't add up." << endl;
	 return 0;
      }
   }
   cout << endl;

   //Column Check
   cout << "Column Check: " << endl;
   for (int i = 0; i < arraySize; i++){
      columnSum = 0;
      for (int j = 0; j < arraySize; j++){
        columnSum += magicSquare[j][i];
      }

      cout << "Column " << i << " sum: " << columnSum << endl;

      if (columnSum != sumChecker){
         cout << "The columns didn't match." << endl;
	 return 0;
      }
   }
   cout << endl;

   //Diagonal Check
   cout << "Diagonal Check" << endl;
   for (int i = 0; i < arraySize; i++){
      diagonalSum += magicSquare[i][i];
   }
   cout << "Diagonal #0 sum: " << diagonalSum << endl;
   if (diagonalSum != sumChecker){
      cout << "This diagonal doesn't add up." << endl;
      return 0;
   }

   diagonalSum = 0;

   for (int i = 0; i < arraySize; i++){
      for (int j = arraySize - 1; j >= 0; j--){
         diagonalSum += magicSquare[j][i];
	 break;
      }
   }
   cout << "Diagonal #1 sum : " << diagonalSum << endl;
   if (diagonalSum != sumChecker){
      cout << "This diagonal doesn't add up." << endl;
      return 0;
   }
   cout << endl;

   //for printing the array as a square
   for (int i = 0; i < arraySize; i++){
      for (int j = 0; j < arraySize; j++){
         cout << magicSquare[i][j] << " ";
      }
      cout << endl;
   }
   cout << "It is a magic square!" << endl;
}
