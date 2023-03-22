#include <thread>
#include <mutex>
#include <condition_variable>
#include <time.h>
#include <iostream>
#include <chrono>



using namespace std;


const int N = 12;				//max numbers
mutex mtx;						//declare mutex
condition_variable_any condc;
condition_variable_any condp;	//declare cond vars for producer and consumer
char buffer[N];					//declare buffer array of size N


void producer() {
	char printChar;
	for (int i = 0; i < N; i++) {
		mtx.lock();									//lock the mutex
		while (buffer[i] != 0) { condp.wait(mtx); }	//mutex waits for condp
		buffer[i] = rand() % 26 + 65;
		printChar = buffer[i] + 32;
		cout << printChar << endl;
		condc.notify_one();//signal condc
		mtx.unlock();//unlock the mutex
	}
}

void consumer() {
	for (int i = 0; i < N; i++) {
		mtx.lock();									//lock the mutex
		while (buffer[i] == 0) { condc.wait(mtx); }	//mutex waits for condc
		cout << buffer[i] << endl;
		condp.notify_one();							//singal condp
		mtx.unlock();								//unlock the mutex
	}
}


int main(int argc, char** argv) {
	srand(time(0));
	//initialize pro and con threads
	thread con(consumer);	//create thread con to consumer
	this_thread::sleep_for(chrono::microseconds(200));
	thread pro(producer);	//create thread pro to producer
	
	con.join();
	pro.join();		//join pro and con threads
}