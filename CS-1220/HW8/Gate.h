//Authors: Joshua McKinniss & Chris LaFave
//
//Date: 4/11/2022
//
//Purpose: Class definition for Gate
#pragma once
#include <iostream>
using namespace std;

class Wire;

class Gate {
public:
	Gate(string, int, Wire *InputA, Wire *InputB, Wire *Output);
	int GetDelay();
	Wire* GetInput(int) const;
	Wire* GetOutput() const;
	char Evaluate();
private:
	string type;
	int delay;
	Wire *inputA, *inputB, *output;
};