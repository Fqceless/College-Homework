//*****************************************************************************
//Summary: This program is to become more familiar with creating functions.
//
//Author: Christopher LaFave
//Created: 10/3/21
//*****************************************************************************
#include <iostream>
#include <string>

using namespace std;

//calculates hat size
double calcHatSize(double h, double w){
   return (w / h)*2.9;
}

//calculates jacket size
double calcJacketSize(double h, double w, double a){
   double size = (h * w)/288.0;
   if(a >= 40){
      size += (a/10) * 0.125; //integer division is on purpose
   }
   return size;
}

//calculates waist size
double calcWaistSize(double w, double a){
   double size = w / 5.7;
   if (a >= 30){
      size += ((a - 28)/2) * 0.1; //integer division is on purpose
   }
   return size;
}

int main(){
   //declare variables
   double userHeight;
   double userWeight;
   double userAge;
   int userChoice;
while (userChoice = 4){ //populate user variables
      cout << "\014";
      cout << "Please enter your height in inches." << endl;
      cin >> userHeight;

      cout << "\014";
      cout << "Please enter your weight in pounds." << endl;
      cin >> userWeight;

      cout << "\014";
      cout << "Please enter your age in years." << endl;
      cin >> userAge;

      userChoice = -1;
      cout << "\014";

      while (userChoice != 4) {
         //user menu
         cout << "What would you like to calculate?" << endl;
         cout << "Type \"0\" to exit the program." << endl;
         cout << "Type \"1\" for hat size." << endl;
         cout << "Type \"2\" for jacket size." << endl;
         cout << "Type \"3\" for waist size." << endl;
         cout << "Type \"4\" to re-enter height, weight, and age." << endl;
   
         cin >> userChoice;

         if(userChoice == 1){ //calls calcHatSize
            cout << "\014";
            cout << "Your hat size is: ";
            cout << calcHatSize(userHeight, userWeight);
            cout << endl << endl;
         }
         else if(userChoice == 2){ //calls calcJacketSize
            cout << "\014";
            cout << "Your jacket size is: ";
            cout << calcJacketSize(userHeight, userWeight, userAge);
            cout << endl << endl;
         }  
         else if(userChoice == 3){ //calls calcWaistSize
            cout << "\014";
            cout << "Your waist size is: ";
            cout << calcWaistSize(userWeight, userAge);
            cout << endl << endl;
         }
         else if(userChoice == 0){ //exit the program
            cout << "\014" << "Exiting program. Have a day!" << endl;
            return 0;
         }
	 else{
            cout << "\014"; //it loops nicely if they enter something illegal.
	 }
      }
   }

}
