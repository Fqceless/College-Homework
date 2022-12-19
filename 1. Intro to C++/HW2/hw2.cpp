//hw2.cpp - CS1210 2nd hw program
//Author: Christopher LaFave
//Date: 8/23/21

#include <iostream>

using namespace std;

int main(){
	string firstName;
	string lastName;
	int userAge;
	int yearsToHundred;
	double userCash;
	int userDollars;
	double userCents;
	
	std::cout << "What is your first name?" << std::endl;
	std::cin >> firstName;
	
	std::cout << "What is your last name?" << std::endl;
	std::cin >> lastName;

	std::cout << "Hello, " << firstName << " " << lastName << "!" << std::endl;

	std::cout << "How old are you?" << std::endl;
	std::cin >> userAge;

	yearsToHundred = 100 - userAge;
	
	std::cout << "Congratulation! You are going to turn 100 in " << yearsToHundred << " years!" << std::endl;

	std::cout << "How much cash money do you have on you right now?" << std::endl;
	std::cin >> userCash;

	userDollars = (int)userCash;
	userCents = 100*(userCash - userDollars);

	std::cout << "You have " << userDollars << " dollars and " << userCents << " cents!" << std::endl;

	std::cout << "Goodbye, " << firstName << " " << lastName << "!" << std::endl;
		
}

