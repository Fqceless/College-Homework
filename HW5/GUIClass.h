///////////////////////////////////////////////////////////////////////////
// C++ code generated with wxFormBuilder (version Jun 30 2011)
// http://www.wxformbuilder.org/
//
// PLEASE DO "NOT" EDIT THIS FILE!
///////////////////////////////////////////////////////////////////////////

#ifndef __GUICLASS_H__
#define __GUICLASS_H__

#include <wx/artprov.h>
#include <wx/xrc/xmlres.h>
#include <wx/statusbr.h>
#include <wx/string.h>
#include <wx/gdicmn.h>
#include <wx/font.h>
#include <wx/colour.h>
#include <wx/settings.h>
#include <wx/bitmap.h>
#include <wx/image.h>
#include <wx/icon.h>
#include <wx/menu.h>
#include <wx/button.h>
#include <wx/sizer.h>
#include <wx/panel.h>
#include <wx/frame.h>
#include <vector>

///////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////
/// Class GUIClass
///////////////////////////////////////////////////////////////////////////////
class GUIClass : public wxFrame 
{
	private:
	
	protected:
		wxStatusBar* statusBar;
		wxMenuBar* menuBar;
		wxMenu* menu;
		wxPanel* panel;
		wxButton* topLeftButton;
		wxButton* topMiddleButton;
		wxButton* topRightButton;
		wxButton* middleLeftButton;
		wxButton* middleMiddleButton;
		wxButton* middleRightButton;
		wxButton* bottomLeftButton;
		wxButton* bottomMiddleButton;
		wxButton* bottomRightButton;
		wxButton* resetButton;
		wxButton* exitButton;

		// used for determining whose turn it is
		// Should only be "X" or "O" (guess i could have used a char oops)
		std::string turn;

		// recreates the current gamestate in a 2d array for easier checking
		// gets initialized by PopulateGameBoard() in SubGUIClass
		std::string gameBoard[3][3];
		
		// Virtual event handlers, overide them in your derived class
		virtual void OnStartup( wxActivateEvent& event ) { event.Skip(); }
		virtual void OnExitSelected( wxCommandEvent& event ) { event.Skip(); }
		virtual void OnGameButtonClick( wxCommandEvent& event ) { event.Skip(); }
		virtual void OnResetClick( wxCommandEvent& event ) { event.Skip(); }

		const std::string GetTurn() { return turn; }
		void SetTurn( std::string t ) { turn = t; }

		
	
	public:
		
		GUIClass( wxWindow* parent, wxWindowID id = wxID_ANY, const wxString& title = wxT("Tic Tac Toe"), const wxPoint& pos = wxDefaultPosition, const wxSize& size = wxSize( 500,300 ), long style = wxDEFAULT_FRAME_STYLE|wxTAB_TRAVERSAL );
		
		~GUIClass();
	
};

#endif //__GUICLASS_H__
