#include "battleship.h"

int main()
{
	// variable declarations (you'll need others, of course)
	bool done = false;
	bool whoWon;
	string playerMove,computerMove;
        Board  playerBoard, computerBoard;
        int playerCheckResult,computerCheckResult;
        int playerRow, playerCol;
	int computerRow, computerCol;
	int playerResult, computerResult;
	int playerDeadShips = 0;
	int computerDeadShips = 0;
	// Welcome the player to the game
   welcome(true);  //welcome();

	// Initialize the game boards
   initializeBoard(playerBoard);
   initializeBoard(computerBoard);

	// Play the game until one player has sunk the other's ships
	while(!done)
	{

		// Clear the screen to prepare show the game situation before the moves
		clearTheScreen();
		// Display the game board situation
                displayBoard (1, 1, HUMAN, playerBoard);
		displayBoard (1, 40, COMPUTER, computerBoard);
		// Get and validate the human player's move
		// BTW, in the following while loop (and the if statements also), I have
		// put a "0" in with the comments.  This is because in order for the
		// code to compile, you need to have something in between the parentheses
                 playerMove=getResponse(16,0,"please input a move:");
                 playerCheckResult=checkMove(playerMove, computerBoard, playerRow,playerCol);
		while(playerCheckResult!=VALID_MOVE/* need to make sure that the human player's move is valid*/)
		{
		 writeMessage(18, 0, "The move you just typed is invalid");
                 playerMove=getResponse(16,0,"please input a move:");
                 playerCheckResult=checkMove(playerMove, computerBoard, playerRow,playerCol);
		}

		// Get and validate the computer's move
		computerMove=randomMove();
		computerCheckResult=checkMove(computerMove, playerBoard, computerRow,computerCol);
		while(computerCheckResult!=VALID_MOVE/* need to make sure that the computer's move is valid*/)
		{
                 computerMove=randomMove();
                 computerCheckResult=checkMove(computerMove, playerBoard, computerRow,computerCol);
		}

		// Execute both moves
		playerResult = playMove(playerRow, playerCol, computerBoard);
		computerResult = playMove(computerRow, computerCol, playerBoard);
		// Clear the screen to show the new game situation after the moves
		clearTheScreen();
		// Display the new game board situation
		displayBoard (1,1,HUMAN, playerBoard);
		displayBoard (1, 40, COMPUTER, computerBoard);
		// Display the move choices each player made
                writeMessage(16, 0, "you played " + playerMove);
		writeMessage(17, 0, "computer played " + computerMove);
		// Show the results of the moves
		writeResult(19, 0, playerResult, HUMAN);
                writeResult(20, 0, computerResult, COMPUTER);

		// Take note if there are any sunken ships
		if (isASunk(playerResult)){
                   computerDeadShips += 1;
		}
		if (isASunk(computerResult)){
                   playerDeadShips += 1;
		}
		// determine if we have a winner
		if(playerDeadShips >= 5)
		{
		        whoWon = false;	
			done = true;
		}
		else if(computerDeadShips >= 5)
		{
			whoWon = true;
			done = true;
		}
		else
		{
                   pauseForEnter();
		}
	}

	clearTheScreen();
        displayBoard (1,1,HUMAN, playerBoard);
        displayBoard (1, 40, COMPUTER, computerBoard);

	// Announce the winner
	if(whoWon == true)
	{
		writeMessage(16, 0, "A winner is you!");
		cout << endl;
	}
	else if(whoWon == false)
	{
		writeMessage(16, 0, "A loser is you!");
		cout << endl;
	}

	// pause to let the result of the game sink in
        pauseForEnter();
	clearTheScreen();

	return 0;
}
