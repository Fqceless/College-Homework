//Authors: Joshua McKinniss & Chris LaFave
//
//Date: 4/11/2022
//
//Purpose: to implement a class for Gate

#include "Gate.h"
#include "Wire.h"

//constructor for Gate that will be called once a gates information is used
Gate::Gate(string Type, int Delay, Wire* InputA, Wire* InputB, Wire* Output) {
	type = Type;
	delay = Delay;
	inputA = InputA;
	inputB = InputB;
	output = Output;
}

//Gets the delay for a gate
int Gate::GetDelay() {
	return delay;
}

//Gets one of the wires that drives this gate 
//Wire is chosen by the passed interger
Wire* Gate::GetInput(int wireNum) const{
	if (wireNum == 1) {
		return inputA;
	}
	else {
		return inputB;
	}
}

//Returns the wire that is the output of the gate
Wire* Gate::GetOutput() const{
	return output;
}

//Takes the type of the gate and then fetches the input 
//wires values and then does some math with their values 
//to determine what value the output wire will have.
char Gate::Evaluate() {
	if (type == "AND") {
		
		if ((inputA->GetValue() == inputB->GetValue()) && inputB->GetValue() == '1') {
			return '1';
		}
		else if (inputA->GetValue() == '0' || inputB->GetValue() == '0') {
			return '0';
		}
		return 'X';
	}
	else if (type == "OR") {
		
		if (inputA->GetValue() == '1' || inputB->GetValue() == '1') {
			return '1';
		}
		else if (inputA->GetValue() == 'X' || inputB->GetValue() == 'X') {
			return 'X';
		}
		return '0';
	}
	else if (type == "XOR") {
		
		if ((inputA->GetValue() == 'X' || inputB->GetValue()) == 'X') {
			return 'X';
		}
		else if (inputA->GetValue() == inputB->GetValue()) {
			return '0';
		}
		return '1';

	} else if (type == "NAND") {
		if (inputA->GetValue() == '1' && inputB->GetValue() == '1') {
			return '0';
		}
		else if (inputA->GetValue() == '0' || inputB->GetValue() == '0') {
			return '1';
		}
		return 'X';
	}
	else if (type == "NOR") {
		
		if (inputA->GetValue() == '1' || inputB->GetValue() == '1') {
			return '0';
		}
		else if (inputA->GetValue() == 'X' || inputB->GetValue() == 'X') {
			return 'X';
		}
		return '1';
	}
	else if (type == "XNOR") {
		
		if ((inputA->GetValue() == 'X' || inputB->GetValue()) == 'X') {
			return 'X';
		}
		else if (inputA->GetValue() == inputB->GetValue()) {
			return '0';
		}
		return '1';
	}
	if (type == "NOT") {
		if (inputA->GetValue() == 'X') {
			return 'X';
		}
		else if (inputA->GetValue() == '0') {
			return '1';
		}
		else {
			return '0';
		}
	}
}