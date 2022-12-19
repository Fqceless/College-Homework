/**
 * Name: Location.java
 * Desc: Creates the Location object for Project5
 *       Describes each square in the maze.
 * Date: 11/3/2022
 * @author Christopher LaFave
 * @version 1.0
 */

package proj5;

public class Location {
    private char sqType;      //will only be X, ., S, or T. Def: X.
    private int startDist;    //distance from start to this location. Def: -1.
    private Location prevLoc; //last location in the path. Def: null.
    private Coord currLoc;    //this location's coordinates. Def: <0 0>.
    
    //constructors
    Location(char t, Coord l){
        if (t == 'X' || t == '.' || t == 'S' || t == 'T'){sqType = t;}
        else{sqType = 'X';}
        
        startDist = -1;
        prevLoc = null;
        currLoc = l;
    }
    Location(){
        this('X', new Coord());
    }
     
    //getters & setters
    public char getType(){return sqType;}
    public void setType(char t){
        if (t == 'X' || t == '.' || t == 'S' || t == 'T'){sqType = t;}
        else{sqType = 'X';}
    }
        
    public int getDist(){return startDist;}
    public void setDist(int d){startDist = d;}
        
    public Location getPrev(){return prevLoc;}
    public void setPrev(Location p){prevLoc = p;}
        
    public Coord getLoc(){return currLoc;}
    public void setLoc(Coord l){currLoc = l;}
}