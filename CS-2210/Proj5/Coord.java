/**
 * Name: Coord.java
 * Desc: Creates the Coord object for Project5
 *       Basically just X and Y
 * Date: 11/3/2022
 * @author Christopher LaFave
 * @version 1.0
 */

package proj5;

public class Coord {
    private int row; //equivalent to a Y value. Def: 0.
    private int col; //equivalent to an X value. Def: 0.
        
    //constructors
    Coord(int row, int col){
        this.row = row;
        this.col = col;
    }
    Coord(){
        this(0,0);
    }
    
    //getters and setters
    public int getRow(){return row;}
    public void setRow(int r){row = r;}
      
    public int getCol(){return col;}
    public void setCol(int c){col = c;}
      
    //print function. Form: <R C>
    public void printCoord(){
        System.out.println("<" + row + " " + col + ">");
    }
}
