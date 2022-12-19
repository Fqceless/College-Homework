Part 2 of the final project for CS-1210, Intro to C++.

The goal of this assignment was to make a battleship AI to win in a tournament of the rest of the class.
We were given all of these files, but we only had to write code in memory_functions_[NAME].cpp.
We did have to modify some files to add our name so that the teacher's progam to let the AI's fight eachother to work.

The only important thing I remember (as of writing this txt file I wrote this code a year ago) is that my AI ran on a finite state machine like concept.
There was a find state (that just did random area that weren't picked yet)
Next was a search state that tried to determine which way the ship was oriented
And lastly a destroy state that finished the ship off
It was supposed to loop find->search->destroy->find etc. 
But there was a bug that broke it to only do find usually halfway through the game.

I still got 3rd in the class tho lol.
