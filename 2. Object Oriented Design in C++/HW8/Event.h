#pragma once
#include<iostream>

using namespace std;

class Event {
private:
	char value;
	int time;
	int name;
	int order;
	
public:
	Event();
	Event(char n, int t, char v, int o);
	
	void SetOrder(int);
	void SetName(char);
	void SetTime(int);
	void SetValue(char);

	const int GetOrder();
	const char GetName();
	const int GetTime();
	const char GetValue();

	friend bool operator<(const Event&, const Event&);
	bool operator==(const Event&);
};