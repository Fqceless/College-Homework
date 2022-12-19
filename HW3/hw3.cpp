//--------------------------------------------------------------
//Overview: To run the quadratic formula; practice coding in C++
//Author: Christopher LaFave
//Date Created: 09/01/21
//Date Modified:
//--------------------------------------------------------------

#include <iostream>
#include <cmath>

int main(){

double a;
double b;
double c;
double xPos;
double xNeg;
double root;

std::cout << "x = (-b +(or)- sqrt(b^2 - 4ac)) / 2a" << std::endl;
std::cout << "Please enter 3 numbers separated by spaces (a, b, c)" << std::endl;
std::cin >> a;
std::cin >> b;
std::cin >> c;

root = (pow(b, 2))-(4 * a * c);
if(root < 0){
   std::cout << "Sorry, there are no real roots. " << std::endl;
   std::cout << "Please try again with new numbers." << std::endl;
   return 0;
}	

xPos = ((-1 * b) + sqrt(root)) / (2 * a);
xNeg = ((-1 * b) - sqrt(root)) / (2 * a);

std::cout << "x = " << xPos << ", " << xNeg << std::endl;
return 0;

}

