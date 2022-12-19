#include "memory_functions_laFave.h"

char rowToLetter(int row){
   if (row == 0){return 'a';}
   else if (row == 1){return 'b';}
   else if (row == 2){return 'c';}
   else if (row == 3){return 'd';}
   else if (row == 4){return 'e';}
   else if (row == 5){return 'f';}
   else if (row == 6){return 'g';}
   else if (row == 7){return 'h';}
   else if (row == 8){return 'i';}
   else if (row == 9){return 'j';}
   else{return 'q';}
}

int letterToRow(char letter){
   if (letter == 'a'){return 1;}
   else if (letter == 'b'){return 2;}
   else if (letter == 'c'){return 3;}
   else if (letter == 'd'){return 4;}
   else if (letter == 'e'){return 5;}
   else if (letter == 'f'){return 6;}
   else if (letter == 'g'){return 7;}
   else if (letter == 'h'){return 8;}
   else if (letter == 'i'){return 9;}
   else if (letter == 'j'){return 10;}
   else{return -1;}
}

int c;



using namespace std;

void initMemorylaFave(ComputerMemory &memory) {
   memory.mode        =  RANDOM;
   memory.hitRow      = -1;
   memory.hitCol      = -1;
   memory.hitShip     =  NONE;
   memory.fireDir     =  NONE;
   memory.fireDist    =  1;
   memory.lastResult  =  NONE;

   for (int i = 0; i < BOARDSIZE; i++) {
      for (int j = 0; j < BOARDSIZE; j++) {
         memory.grid[i][j] = EMPTY_MARKER;
      }
   }
}

string smartMovelaFave(const ComputerMemory &memory) {
   // fill in the code here to make a smart move based on what information
   // appears in the computer's memory
   string move = "   ";

   //RANDOM state
   if (memory.mode == RANDOM){
   move = randomMove();

   //SEARCH state
   }
   if (memory.mode == SEARCH){
      if (memory.fireDir == SOUTH){
	 if(rowToLetter(memory.hitRow) != 'j'){
            move[0] = rowToLetter(memory.hitRow + 1);
            if(memory.hitCol != 9){
	       move[1] = '1' + memory.hitCol;
	    }
	    else{
               move[1] = '1';
	       move[2] = '0';
	    }
	 }
      }
      else if (memory.fireDir == NORTH){
         if(rowToLetter(memory.hitRow) != 'a'){
            move[0] = rowToLetter(memory.hitRow - 1);
            if(memory.hitCol != 9){
	       move[1] = '1' + memory.hitCol;
	    }
	    else{
               move[1] = '1';
               move[2] = '0';
            }
	 }
      }
      else if (memory.fireDir == EAST){
         if(('0' + memory.hitCol) != '9'){
            move[0] = rowToLetter(memory.hitRow);
            move[1] = '1' + memory.hitCol + 1;
	 }
      }
      else if (memory.fireDir == WEST){
         if(('1' + memory.hitCol) != '1'){
            move[0] = rowToLetter(memory.hitRow);
            move[1] = '1' + memory.hitCol - 1;
	 }
      }
   }
   //DESTROY state
   if (memory.mode == DESTROY){
      if (memory.fireDir == SOUTH){
         move[0] = rowToLetter(memory.hitRow + c);
	 move[1] = '1' + memory.hitCol;
      }
      else if (memory.fireDir == NORTH){
         move[0] = rowToLetter(memory.hitRow - c);
	 move[1] = '1' + memory.hitCol;
      }
      else if (memory.fireDir == EAST){
	 move[0] = rowToLetter(memory.hitRow);
         move[1] = '1' + memory.hitCol + c;
      }
      else if (memory.fireDir == WEST){
	 move[0] = rowToLetter(memory.hitRow);
         move[1] = '1' + memory.hitCol - c;
      }
   }
      return move;
}

void updateMemorylaFave(int row, int col, int result, ComputerMemory &memory) {
   
   memory.lastResult = result;
	
   // RANDOM state
   if(memory.mode == RANDOM){
      if (isAHit(result)){
         memory.mode = SEARCH;
         memory.hitRow = row;
	 memory.hitCol = col;
	 if(rowToLetter(memory.hitRow) != 'j'){
	    memory.fireDir = SOUTH;
	 }
	 else{
            memory.fireDir = NORTH;
	 }
      }
   }

   //SEARCH state
   else if(memory.mode == SEARCH){
      if (isAHit(result)){
         memory.mode = DESTROY;
	 c = 2;
      }
      else if (isAMiss(result)){
	 c=0;
	 if (memory.fireDir == SOUTH && (rowToLetter(memory.hitRow + 1) != 'a')){
            memory.fireDir = NORTH;
	 }
	 else if(memory.fireDir == NORTH || memory.fireDir == SOUTH){
            if(('0' + memory.hitCol + 1) != '9'){
               memory.fireDir = EAST;
	    }
	    else{
               memory.fireDir = WEST;
	    }
	 }
	 else if (memory.fireDir == EAST){
            memory.fireDir = WEST;
	 }
      }
      else if (isASunk(result)){
         memory.mode = RANDOM;
	 c=0;
      }
   }
   //DESTROY state
   else if (memory.mode == DESTROY){
      if (isASunk(result)){
         memory.mode = RANDOM;
      }
      else if(isAMiss(result)){
	 c = 1;
         if(memory.fireDir == SOUTH){
            memory.fireDir = NORTH;
	 }
	 else if(memory.fireDir == NORTH){
            memory.fireDir = SOUTH;
         }
	 else if(memory.fireDir == EAST){
            memory.fireDir = WEST;
         }
         else if(memory.fireDir == WEST){
            memory.fireDir = EAST;
         }


      }
      else if(isAHit(result)){
         if(rowToLetter(memory.hitRow) == 'j' && memory.fireDir == SOUTH){
            memory.fireDir = NORTH;
         }
         else if(rowToLetter(memory.hitRow) == 'a' && memory.fireDir == NORTH){
            memory.fireDir = SOUTH;
         }
         else if(('0' + memory.hitCol + 1) == '9' && memory.fireDir == EAST){
            memory.fireDir = WEST;
         }
         else if(('1' + memory.hitCol + 1) == '1' && memory.fireDir == WEST){
            memory.fireDir = EAST;
	 }
	 c = c + 1;
      }
   }
}

