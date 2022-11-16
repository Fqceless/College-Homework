#include <iostream>
#include <istream>
#include <fstream>
#include <string>
#include <queue>
#include "Gate.h"
#include "Wire.h"
#include "Event.h"

using namespace std;

int main() {

	// Init vars
	int index;
	int input1;
	int input2;
	int output;
	int idelay;
	int eTime;
	int order = 0;
	char value;
	char name;
	char newVal;
	bool endProg = false;
	bool correctFileName = false;
	bool makeEvent;
	string sdelay;
	string basename;
	string cf;
	string vf;
	string readMe;
	string title;
	ifstream in;
	Wire* inputWire1 = new Wire;
	Wire* inputWire2 = new Wire;
	Wire* outputWire = new Wire;
	Wire* currWire = NULL;
	vector<Wire*> Wires;
	vector<Gate*> Gates;
	priority_queue<Event> Events;
	priority_queue<Event> EventsCpy;


	while (!correctFileName) {
		cout << "Enter file name (base only)" << endl;
		getline(cin, basename);
		cf = basename + ".txt";
		vf = basename + "_v.txt";

		// Check if file names are good
		in.open(cf);
		if (in.fail()) {
			cout << "Circuit filename not found" << endl;
			basename = "~";
		}
		in.close();

		in.open(vf);
		if (in.fail()) {
			cout << "Vector filename not found" << endl;
			basename = "~";
		}
		in.close();

		if (basename != "~") {
			correctFileName = true;
		}
	}


	// Parse circuit file
	in.open(cf);
	getline(in, title);
	in >> readMe;
	while (!in.eof()) {
		// If there is an input or output it will put a wire with the 
		// proper definition in our Wires vector of Wire*
		if (readMe == "INPUT" || readMe == "OUTPUT") {
			in >> name;
			in >> index;
			Wire* wire = new Wire();
			wire->SetName(name);
			wire->SetIndex(index);
			if (Wires.size() < index) {
				Wires.resize(index);
			}
			Wires.push_back(wire);
		}
		// If it is not a Wire then it check if it is a gate that we can handle
		// and puts a new gate into our Gates vector of Gate*
		else if (readMe == "AND" || readMe == "OR" || readMe == "XOR" ||
			readMe == "NAND" || readMe == "NOR" || readMe == "XNOR" ||
			readMe == "NOT") {
			in >> sdelay;
			idelay = stoi(sdelay);
			in >> input1;
			// For a NOT Gate there is no input2 so this code handles when to 
			// not take an input for input2
			if (readMe != "NOT") {
				in >> input2;
			}
			else {
				input2 = -1;
				inputWire2 = NULL;
			}
			in >> output;
			// This for loop will match the input1, input2, and output index ints
			// to the Wire* in out Wires vector that hold all current wires
			for (auto i : Wires) {
				if (i != NULL) {
					if (i->GetIndex() == input1) {
						inputWire1 = i;
					}
					if (i->GetIndex() == output) {
						outputWire = i;
					}
					if (i->GetIndex() == input2) {
						inputWire2 = i;
					}
				}
			}
			// If the previous for loop doesnt find a matching Wire* that currently 
			// exists, then it will create a new Wire* that has an index set properly
			// and it will have its first history to be an X
			if (inputWire1->GetName() == '~') {
				Wire* wire = new Wire();
				wire->SetIndex(input1);
				inputWire1->SetIndex(input1);
				wire->SetHistory('X');
				Wires[input1] = wire;
			}
			else if (inputWire2 != NULL) {
				if (inputWire2->GetName() == '~' && readMe != "NOT") {
					Wire* wire = new Wire();
					wire->SetIndex(input2);
					inputWire2->SetIndex(input2);
					wire->SetHistory('X');
					Wires[input2] = wire;
				}
			}
			else if (outputWire->GetName() == '~') {
				Wire* wire = new Wire();
				wire->SetIndex(output);
				outputWire = wire;
				wire->SetHistory('X');
				Wires[output] = wire;

			}
			// Puts the new information gathered into a new Gate* and adds it to Gates 
			// and also it will add the new gate to all the input wires' drives
			Gate* gate = new Gate(readMe, idelay, inputWire1, inputWire2, outputWire);
			Gates.push_back(gate);
			inputWire1->SetDrives(gate);
			if (inputWire2 != NULL) {
				inputWire2->SetDrives(gate);
			}

			// All output wires will always have some ammount of X at the beggining 
			// of their history
			outputWire->SetHistory('X');
		}
		// If the input that was taken is not formatted as a wire or a gate then it 
		// will print error code and return 0
		else {
			cout << "Circuit file not formatted corretly" << endl;
			return 0;
		}
		in >> readMe;
	}
	in.close();
	// Parse vector file
	in.open(vf);
	getline(in, title);
	in >> readMe;
	// The only thing in this file should be the title (which the getline 
	// takes care of) and INPUT so thats all that should be in this file
	if (readMe == "INPUT") {
		while (!in.eof()) {
			in >> name;
			in >> eTime;
			in >> value;
			order++;
			// Sets a new event for every line in this file that is INPUT
			Event event(name, eTime, value, order);
			Events.push(event);

			in >> readMe;
		}
	}
	// If there is a line that isnt INPUT then this prints error code and
	// returns 0
	else {
		cout << "Vector file not formatted corretly" << endl;
		return 0;
	}
	in.close();

	Event currEvent = Events.top();
	Event nextEvent;
	// This is the loop that handles printing all the events
	while (!Events.empty() && !(currEvent.GetTime() > 61)) {
		currEvent = Events.top();
		// This for loop handles matching the wire the current event is using
		// to the available wires in our list of existing wires.
		for (auto i : Wires) {
			if (i != NULL) {
				if (i->GetName() == currEvent.GetName()) {
					currWire = i;
				}
			}
		}
		// This will fill in the history of the wire with the correct values 
		// up to the time of the event by taking the the most recent thing in 
		// history (for input wires it wont matter and for output wires it will 
		// be X) and then fills out the spot at the time with the event with 
		// the value that the current event has
		int histSize = (currWire->GetHistory()).size();
		for (int i = histSize; i < currEvent.GetTime(); i++) {
			currWire->SetHistory(currWire->GetHistory().at(histSize - 1));
		}

		currWire->SetHistory(currEvent.GetValue());
		currWire->SetValue(currEvent.GetValue());
		EventsCpy = Events;
		Events.pop();
		// This loop will run through all the drives for the affected wire and
		// if and of the output wires for the Gates driven by the wire are 
		// affected, it will create a new event that will correct the wire
		for (auto i : currWire->GetDrives()) {
			if (!(currWire->GetDrives().empty())) {
				// This checks if there is an event for the output wire that 
				// will change it from what value it currently stores
				//
				// ex: The circuit4 will change every nanosecond but its 
				// delay is 2ns so it will ahve an event meaning its value will
				// change while it is checking for its next event
				newVal = i->GetOutput()->GetValue();
				while (!EventsCpy.empty()) {
					EventsCpy.pop();
					if (!EventsCpy.empty()) {
						nextEvent = EventsCpy.top();
						if (nextEvent.GetName() == i->GetOutput()->GetName()) {
							newVal = nextEvent.GetValue();
						}
					}
				}

				// If the decided most recent value differs form what will be the 
				// new output, it creates a new event to change the wire
				if ((newVal != i->Evaluate())) {

					currEvent.SetName(i->GetOutput()->GetName());
					currEvent.SetValue(i->Evaluate());
					currEvent.SetTime(currEvent.GetTime() + i->GetDelay());
					order++;
					currEvent.SetOrder(order);
					EventsCpy = Events;
					makeEvent = true;
					// Checks if it's attemting to make a duplicate event
					while (!EventsCpy.empty()) {
						
						if (currEvent == EventsCpy.top()) {
							makeEvent = false;
						}
						EventsCpy.pop();
					}

					if (makeEvent) {
						Events.push(currEvent);
					}
					
				}
			}
		}
	}

	// Once all the changes are done to the wires, this will fill in the 
	// wires history so they are the same length and print them out 
	for (auto i : Wires) {
		if (i != NULL && i->GetName() != '~') {
			for (int j = i->GetHistory().size(); j <= currEvent.GetTime(); j++) {
				i->SetHistory(i->GetValue());
			}
			i->PrintHistory();
		}
	}
	// Prints out the time stamps at the bottom of the simulation
	cout << "  0    5 ";
	int k = 10;
	while (k <= currEvent.GetTime()) {
		cout << "   " << k;
		k += 5;
	}
	cout << endl;


	return 0;
}