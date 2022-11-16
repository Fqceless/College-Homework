#include "Event.h"
#include <iostream>

using namespace std;

bool operator< (const Event& e1, const Event& e2) {
	if (e1.time == e2.time) {
		return e1.order > e2.order;
	}
	return e1.time > e2.time;
}

bool Event::operator== (const Event& e1) {
	if ((e1.value == this->value) && (e1.time == this->time) && 
		(e1.name == this->name)) {
		return true;
	}
	return false;
}



Event::Event() {
	value = '~';
	time = -1;
	name = 'X';
	order = -1;
}

Event::Event(char n, int t, char v, int o) {
	value = v;
	time = t;
	name = n;
	order = o;
}

void Event::SetOrder(int o) { order = o; }
void Event::SetName(char n) { name = n; }
void Event::SetTime(int t) { time = t; }
void Event::SetValue(char v) { value = v; }

const int Event::GetOrder() { return order; }
const char Event::GetName() { return name; }
const int Event::GetTime() { return time; }
const char Event::GetValue() { return value; }