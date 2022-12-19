//Authors: Joshua McKinniss & Chris LaFave
//
//Date: 4/11/2022
//
//Purpose: Class description for the Wire class

#pragma once
#include <iostream>
#include <vector>
using namespace std;

class Gate;

class Wire {
public:
	Wire();
	Wire(char, int);
	void SetValue(char);
	void SetName(char);
	void SetDrives(Gate*);
	void SetIndex(int);
	void SetHistory(char);

	char GetValue() const;
	char GetName() const;
	vector<Gate*> GetDrives() const;
	int GetIndex() const;
	vector<char> GetHistory() const;

	void PrintHistory();
private:
	char value;
	char name;
	vector<Gate*> drives;
	int index;
	vector<char> history;
	
};