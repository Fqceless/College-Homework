#include "SubGUIClass.h"

SubGUIClass::SubGUIClass( wxWindow* parent )
:
GUIClass( parent )
{

}

// updates the status bar and sets the turn on startup
void SubGUIClass::OnStartup( wxActivateEvent& event ){
	this->SetTurn("X");
	statusBar->SetStatusText(this->GetTurn() + "'s turn");
}

// pops up a dialog to confirm the exit
void SubGUIClass::OnExitSelected( wxCommandEvent& event ){
	int answer = wxMessageBox("Quit Program?", "Confirm", wxYES_NO, this);
	if (answer == wxYES) { this->Close(); }
}

void SubGUIClass::OnGameButtonClick( wxCommandEvent& event ){

	// makes a button pointer to determine who was clicked
	wxButton* b = (wxButton*)event.GetEventObject();

	// checks if &b is empty
	if (b->GetLabel() == "") {
		// sets correct label
		if (GetTurn() == "X") {
			b->SetLabel("X");
			this->SetTurn("O");
		}
		else {
			b->SetLabel("O");
			this->SetTurn("X");
		}

		// update status bar
		statusBar->SetStatusText(this->GetTurn() + "'s turn");

		// check for a winner or tie, and asks for reset
		if (CheckForWinner() == "X" || CheckForWinner() == "O") {
			int answer = wxMessageBox(
				CheckForWinner() + " Wins!\nPlay Again?", "Confirm", wxYES_NO, this);

			if (answer == wxYES) { ResetBoard(); }
			else { Close(); }
		}
		else if (CheckForWinner() == "T") {
			int answer = wxMessageBox("Tie!\nPlay Again?", "Confirm", wxYES_NO, this);

			if (answer == wxYES) { ResetBoard(); }
			else { Close(); }
		}
	}
}

//pops up dialouge
void SubGUIClass::OnResetClick( wxCommandEvent& event ){
	int answer = wxMessageBox("Reset Game?", "Confirm", wxYES_NO, this);
	if (answer == wxYES) { ResetBoard(); }
}

//sets the labels of all the buttons to an empty string
void SubGUIClass::ResetBoard() {
	topLeftButton->SetLabel("");
	topMiddleButton->SetLabel("");
	topRightButton->SetLabel("");
	middleLeftButton->SetLabel("");
	middleMiddleButton->SetLabel("");
	middleRightButton->SetLabel("");
	bottomLeftButton->SetLabel("");
	bottomMiddleButton->SetLabel("");
	bottomRightButton->SetLabel("");
}

//initializes gameBoard
void SubGUIClass::PopulateGameBoard() {
	gameBoard[0][0] = topLeftButton->GetLabel();
	gameBoard[0][1] = topMiddleButton->GetLabel();
	gameBoard[0][2] = topRightButton->GetLabel();
	gameBoard[1][0] = middleLeftButton->GetLabel();
	gameBoard[1][1] = middleMiddleButton->GetLabel();
	gameBoard[1][2] = middleRightButton->GetLabel();
	gameBoard[2][0] = bottomLeftButton->GetLabel();
	gameBoard[2][1] = bottomMiddleButton->GetLabel();
	gameBoard[2][2] = bottomRightButton->GetLabel();
}

//checks for the winner
std::string SubGUIClass::CheckForWinner() {
	//update gameBoard
	PopulateGameBoard();

	std::string tester[3];

	//checks for horizontal
	for (int i = 0; i <= 2; i++) {
		for (int j = 0; j <= 2; j++) {
			tester[j] = gameBoard[i][j];
		}
		if (tester[0] == tester[1] && tester[1] == tester [2] && tester[0] != ""){ 
			return tester[0]; 
		}
	}

	//checks for vertical
	for (int i = 0; i <= 2; i++) {
		for (int j = 0; j <= 2; j++) {
			tester[j] = gameBoard[j][i];
		}
		if (tester[0] == tester[1] && tester[1] == tester[2] && tester[0] != "") {
			return tester[0];
		}
	}

	//checks for diagonal TL->BR
	for (int i = 0; i <= 2; i++) {
		tester[i] = gameBoard[i][i]; 
		if (tester[0] == tester[1] && tester[1] == tester[2] && tester[0] != "") {
			return tester[0];
		}
	}

	//checks for diagonal BL->TR
	for (int i = 2, j = 0; i >= 0; i--, j++) {
		tester[j] = gameBoard[i][j];
		if (tester[0] == tester[1] && tester[1] == tester[2] && tester[0] != "") {
			return tester[0];
		}
	}
	
	//checks if the game is still going
	for (int i = 0; i <= 2; i++) {
		for (auto j : gameBoard[i]) {
			if (j == "") { return ""; }
		}
	}

	//if we get here, it's a tie
	return "T";

}
