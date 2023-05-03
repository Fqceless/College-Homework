#include <iostream>
#include <fstream>
#include <string>
#include <bitset>
#include "lodepng.h"

int main(int argc, char** argv) {
	std::vector<unsigned char> image; //RGBA map of the input file
	unsigned width, height; //h & w of picture

	//Encoding
	if ((argc == 5 || argc == 4) && std::string(argv[1]) == "-e") {
		std::string infile = argv[2];
		std::string outfile = argv[3];
		std::string message, binarymsg = "";
		
		//grab message
		if (argc == 5) {
			std::string msgfile = argv[4];
			std::string line;
			std::ifstream msg;
			msg.open(msgfile.c_str());
			while (getline(msg, line)) {
				message += line + "\n";
			}
		}
		//no input file given
		else {
			std::cout << "Please enter your secret message: " << std::endl;
			getline(std::cin, message);
		}

		//convert the message to binary
		for (int i = 0; i < message.size(); i++) {
			binarymsg += std::bitset<8>(message.c_str()[i]).to_string();
		}
		//this is the "end message" trigger (NULL in ASCII)
		binarymsg += "00000000";

		//decode
		lodepng::decode(image, width, height, infile);

		//encrypt the message:
		//if the end bit doesn't match the message, add 1
		//if the bit is about to overflow, subtract one instead
		int ctr = 0;
		for (int i = 2; i < image.size(); i += 4, ctr++) {
			if (ctr < binarymsg.size() && image[i] % 2 != binarymsg[ctr] % 2) {
				if (image[i] != 255) {
					image[i]++;
				}
				else {
					image[i]--;
				}
			}
		}

		//encode the message into the picture
		lodepng::encode(outfile, image, width, height);
	}

	//Decoding
	else if ((argc == 4 || argc == 3) && std::string(argv[1]) == "-d") {
		std::string infile = argv[2];
		std::string message, binarymsg;
		std::bitset<8> currbits;

		//decode
		lodepng::decode(image, width, height, infile);

		//grab binary message from infile
		for (int i = 2; i < image.size(); i += 4) {
			if (image[i] % 2 == 0) {
				binarymsg += "0";
			}
			else {
				binarymsg += "1";
			}

			if (binarymsg.length() > 8 && std::string(binarymsg.substr(binarymsg.length() - 9, 8)) == "00000000") {
				break;
			}
		}

		//convert binary to ASCII
		for (int i = 0; i < binarymsg.length() - 8; i += 8) {
			currbits = std::bitset<8>(binarymsg.substr(i, 8));
			message += char(currbits.to_ulong());
		}

		//output message
		if (argc == 4) {
			std::string outfile = argv[3];
			std::ofstream out;
			out.open(outfile.c_str());
			out << message;
			out.close();
		}
		//no output file given
		else {
			std::cout << message << std::endl;
		}
	}
	//README
	else {
		std::ifstream readMe;
		std::string line;
		readMe.open("README.txt");
		
		while (getline(readMe, line)) {
			std::cout << line << std::endl;
		}
	}


}