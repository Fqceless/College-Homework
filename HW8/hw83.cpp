//*****************************************************************************
//Summary: To pull any verse from the Bible, and get used to using files.
//
//Author: Chris LaFave
//Created: 10/31/21
//Updated: 11/1/21
//   -Figured out how to code
//*****************************************************************************

#include <iostream>
#include <string>
#include <fstream>
using namespace std;

int main(){
   //declare streams
   ifstream in;
   ofstream out;
   
   //declare vars
   string book = "";
   string Tester = "";
   string chapter = "";
   string verse = "";
   bool isValid = true;

   //open verses.txt
   out.open("verses.txt");
   
   //clear screen
   cout << "\014" << flush;

//main function
while (verse !="q"){

   //resets test vars and opens the bible
   isValid = true;
   in.open("Bible.txt");
   
   //user input control
   cout << "Enter \"q\" at any time to quit the program" << endl;

   cout << "What book?" << endl;
   getline(cin, book);
   if(book == "q"){break;}

   cout << "What chapter?" << endl;
   getline(cin, chapter);
   if(chapter == "q"){break;}
   
   cout << "What verse?" << endl;
   getline(cin, verse);
   if(verse == "q"){break;}
   
   //make the book uppercase
   for (int i = 0; i < book.size(); i++){
      book.at(i) = toupper(book.at(i));
   }

   //modify chapter for testing
   if (book == "PSALMS"){
      chapter = "PSALM " + chapter;
   }
   else{
      chapter = "CHAPTER " + chapter;
   }
   
   //modify book for testing
   book = "THE BOOK OF " + book;
   
   //finds the book
   while(Tester != book){
      getline(in, Tester);
      if (in.eof()){//invalid book
         cout << "\014" << flush;
	 cout << "That book is not real, sorry." << endl;
	 isValid = false;
	 break;
      }
   }
   
   //finds the chapter
   while((Tester != chapter) && (isValid != false)){
      getline(in, Tester);
      if(Tester.substr(0, 11) == "THE BOOK OF"){//invlaid chapter
	 cout << "\014" << flush;
	 cout << "That chapter is not real, sorry." << endl;
	 isValid = false;
	 break;
      }
   }
   //finds the verse
   while((Tester.substr(0, verse.size()) != verse) && (isValid != false)){	   
      getline(in, Tester);
      if ((Tester.substr(0, verse.size()) == verse) && 
      (book != "THE BOOK OF PSALMS")){//prints non-psalm to out
         out << book.substr(12) << " " << chapter.substr(7);
	 out  << ":" << verse << endl << Tester << endl << endl;
	 cout << "\014" << flush;
	 cout << "Successfully retreived verse." << endl;
      }
      else if((Tester.substr(0, verse.size()) == verse) && 
      (book == "THE BOOK OF PSALMS")){//prints psalm to out
         out << book.substr(12) << " " << chapter.substr(5);
         out  << ":" << verse << endl << Tester << endl << endl;
         cout << "\014" << flush;
	 cout << "Successfully retreived verse." << endl;
      }
      if ((Tester.substr(0, 7) == "CHAPTER") || 
      (Tester.substr(0, 5) == "PSALM")){//vinvalid verse
         cout << "\014" << flush;
	 cout << "That verse is not real, sorry" << endl;
	 isValid = false;
	 break;
      }
   }
   //close Bible and start over
   in.close();
}
   //close verse.txt
   out.close();
   return 0;
}

