///////////////////////////////////////////////////////////////////////////
// C++ code generated with wxFormBuilder (version Jun 30 2011)
// http://www.wxformbuilder.org/
//
// PLEASE DO "NOT" EDIT THIS FILE!
///////////////////////////////////////////////////////////////////////////

#include "GUIClass.h"

///////////////////////////////////////////////////////////////////////////

GUIClass::GUIClass( wxWindow* parent, wxWindowID id, const wxString& title, const wxPoint& pos, const wxSize& size, long style ) : wxFrame( parent, id, title, pos, size, style )
{
	this->SetSizeHints( wxDefaultSize, wxDefaultSize );
	
	statusBar = this->CreateStatusBar( 1, wxST_SIZEGRIP, wxID_ANY );
	menuBar = new wxMenuBar( 0 );
	menu = new wxMenu();
	wxMenuItem* exitMenuItem;
	exitMenuItem = new wxMenuItem( menu, wxID_ANY, wxString( wxT("Exit") ) + wxT('\t') + wxT("ALT-F4"), wxEmptyString, wxITEM_NORMAL );
	menu->Append( exitMenuItem );
	
	menuBar->Append( menu, wxT("File") ); 
	
	this->SetMenuBar( menuBar );
	
	wxBoxSizer* mainBoxSizer;
	mainBoxSizer = new wxBoxSizer( wxVERTICAL );
	
	panel = new wxPanel( this, wxID_ANY, wxDefaultPosition, wxDefaultSize, wxTAB_TRAVERSAL );
	wxBoxSizer* subBoxSizer;
	subBoxSizer = new wxBoxSizer( wxVERTICAL );
	
	wxBoxSizer* topBoxSizer;
	topBoxSizer = new wxBoxSizer( wxHORIZONTAL );
	
	topLeftButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	topBoxSizer->Add( topLeftButton, 0, wxALL, 5 );
	
	topMiddleButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	topBoxSizer->Add( topMiddleButton, 0, wxALL, 5 );
	
	topRightButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	topBoxSizer->Add( topRightButton, 0, wxALL, 5 );
	
	subBoxSizer->Add( topBoxSizer, 0, wxALIGN_CENTER_HORIZONTAL|wxALL, 5 );
	
	wxBoxSizer* middleBoxSizer;
	middleBoxSizer = new wxBoxSizer( wxHORIZONTAL );
	
	middleLeftButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	middleBoxSizer->Add( middleLeftButton, 0, wxALL, 5 );
	
	middleMiddleButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	middleBoxSizer->Add( middleMiddleButton, 0, wxALL, 5 );
	
	middleRightButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	middleBoxSizer->Add( middleRightButton, 0, wxALL, 5 );
	
	subBoxSizer->Add( middleBoxSizer, 0, wxALIGN_CENTER_HORIZONTAL|wxALL, 5 );
	
	wxBoxSizer* bottomBoxSizer;
	bottomBoxSizer = new wxBoxSizer( wxHORIZONTAL );
	
	bottomLeftButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	bottomBoxSizer->Add( bottomLeftButton, 0, wxALL, 5 );
	
	bottomMiddleButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	bottomBoxSizer->Add( bottomMiddleButton, 0, wxALL, 5 );
	
	bottomRightButton = new wxButton( panel, wxID_ANY, wxEmptyString, wxDefaultPosition, wxDefaultSize, 0 );
	bottomBoxSizer->Add( bottomRightButton, 0, wxALL, 5 );
	
	subBoxSizer->Add( bottomBoxSizer, 0, wxALIGN_CENTER_HORIZONTAL|wxALL, 5 );
	
	wxBoxSizer* otherBoxSizer;
	otherBoxSizer = new wxBoxSizer( wxHORIZONTAL );
	
	resetButton = new wxButton( panel, wxID_ANY, wxT("Reset!"), wxDefaultPosition, wxSize( 60,30 ), 0 );
	otherBoxSizer->Add( resetButton, 0, wxALL, 5 );
	
	exitButton = new wxButton( panel, wxID_ANY, wxT("Exit!"), wxPoint( -1,-1 ), wxSize( 60,30 ), 0 );
	otherBoxSizer->Add( exitButton, 0, wxALL, 5 );
	
	subBoxSizer->Add( otherBoxSizer, 0, wxALL|wxALIGN_CENTER_HORIZONTAL, 5 );
	
	panel->SetSizer( subBoxSizer );
	panel->Layout();
	subBoxSizer->Fit( panel );
	mainBoxSizer->Add( panel, 1, wxEXPAND, 5 );
	
	this->SetSizer( mainBoxSizer );
	this->Layout();
	
	this->Centre( wxBOTH );
	
	// Connect Events
	this->Connect( wxEVT_ACTIVATE, wxActivateEventHandler( GUIClass::OnStartup ) );
	this->Connect( exitMenuItem->GetId(), wxEVT_COMMAND_MENU_SELECTED, wxCommandEventHandler( GUIClass::OnExitSelected ) );
	topLeftButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	topMiddleButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	topRightButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	middleLeftButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	middleMiddleButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	middleRightButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	bottomLeftButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	bottomMiddleButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	bottomRightButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	resetButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnResetClick ), NULL, this );
	exitButton->Connect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnExitSelected ), NULL, this );
}

GUIClass::~GUIClass()
{
	// Disconnect Events
	this->Disconnect( wxEVT_ACTIVATE, wxActivateEventHandler( GUIClass::OnStartup ) );
	this->Disconnect( wxID_ANY, wxEVT_COMMAND_MENU_SELECTED, wxCommandEventHandler( GUIClass::OnExitSelected ) );
	topLeftButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	topMiddleButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	topRightButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	middleLeftButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	middleMiddleButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	middleRightButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	bottomLeftButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	bottomMiddleButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	bottomRightButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnGameButtonClick ), NULL, this );
	resetButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnResetClick ), NULL, this );
	exitButton->Disconnect( wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler( GUIClass::OnExitSelected ), NULL, this );
	
}
