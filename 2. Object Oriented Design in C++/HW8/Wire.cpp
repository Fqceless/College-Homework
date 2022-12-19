//Authors: Joshua McKinniss & Chris LaFave
//
//Date: 4/11/2022
//
//Purpose: to implement a class for Wire

#include "Wire.h"
#include "Gate.h"

//Default constructor for a Wire type
Wire::Wire() {
	value = 'X';
	name = '~';
	index = -1;
}

Wire::Wire(char Name, int Index) {
	name = Name;
	index = Index;
	value = 'X';
}

//Sets the value of the passed interget to value
void Wire::SetValue(char val) {
	value = val;
}

//Sets the name of the passed character to name
void Wire::SetName(char Name) {
	name = Name;
}

void Wire::SetIndex(int Index) {
	index = Index;
}

//Adds a Gate* to the vector drives.
void Wire::SetDrives(Gate* gate) {
	drives.push_back(gate);
}

void Wire::SetHistory(char val) {
	history.push_back(val);
}


char Wire::GetValue() const{
	return value;
}

char Wire::GetName() const {
	return name;
}

vector<Gate*> Wire::GetDrives() const{
	return drives;
}

int Wire::GetIndex() const {
	return index;
}

vector<char> Wire::GetHistory() const {
	return history;
}

//Walks through the vector history 
//and prints out all its values
void Wire::PrintHistory() {
	cout << name << "|";
	for (int i = 0; i < history.size() && i <= 60; i++) {
		if (history.at(i) == '0') {
			cout << '_';
		}
		else if (history.at(i) == '1') {
			cout << '-';
		}
		else if (history.at(i) == 'X') {
			cout << 'X';
			}
	}
	cout << endl << " |" << endl;
}