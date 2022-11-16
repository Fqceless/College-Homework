/**
 * Name: Search.java
 * Desc: Creates a search method for Project5
 *       Searches around a given location.
 * Date: 11/3/2022
 * @author Christopher LaFave
 * @version 1.0
 */

package proj5;
import java.util.Queue;

public class Search {
    //takes in the map, the seach queue, and the variable used for distance
    public static void Compute(Location[][] L, Queue<Location> Q, int d){
        //grab the location we are searching and remove it from the queue
        Coord currLoc = Q.remove().getLoc();
        
        /* im so sorry about how messy this is, here is the breakdown:
        if: the position we are searching is in bounds
        and: its type is either '.' or 'T'
        and: it is a postion that hasn't been searched before
        then:
            -add it to the queue
            -set its distance from start to one more than it's prev
            -set its prev to the location we are seaching
        */
        
        //search above this location
        if(currLoc.getRow() - 1 >= 0 && 
          (L[currLoc.getRow()-1][currLoc.getCol()].getType() == '.' ||
           L[currLoc.getRow()-1][currLoc.getCol()].getType() == 'T')&&
           L[currLoc.getRow()-1][currLoc.getCol()].getDist() == -1){
            Q.add(L[currLoc.getRow()-1][currLoc.getCol()]);
            L[currLoc.getRow()-1][currLoc.getCol()].setDist(d+1);
            L[currLoc.getRow()-1][currLoc.getCol()].setPrev(
                       L[currLoc.getRow()][currLoc.getCol()]);
        }
        
        //search below this location
        if(currLoc.getRow() + 1 <= L.length - 1 && 
          (L[currLoc.getRow()+1][currLoc.getCol()].getType() == '.' ||
           L[currLoc.getRow()+1][currLoc.getCol()].getType() == 'T')&&
           L[currLoc.getRow()+1][currLoc.getCol()].getDist() == -1){
            Q.add(L[currLoc.getRow()+1][currLoc.getCol()]);
            L[currLoc.getRow()+1][currLoc.getCol()].setDist(d+1);
            L[currLoc.getRow()+1][currLoc.getCol()].setPrev(
                       L[currLoc.getRow()][currLoc.getCol()]);
        }
        
        //search to the left of this location
        if(currLoc.getCol() - 1 >= 0 && 
          (L[currLoc.getRow()][currLoc.getCol()-1].getType() == '.' ||
           L[currLoc.getRow()][currLoc.getCol()-1].getType() == 'T')&&
           L[currLoc.getRow()][currLoc.getCol()-1].getDist() == -1){
            Q.add(L[currLoc.getRow()][currLoc.getCol()-1]);
            L[currLoc.getRow()][currLoc.getCol()-1].setDist(d+1);
            L[currLoc.getRow()][currLoc.getCol()-1].setPrev(
                       L[currLoc.getRow()][currLoc.getCol()]);
        }
        
        //search to the right of this location
        if(currLoc.getCol() + 1 <= L[0].length - 1 && 
          (L[currLoc.getRow()][currLoc.getCol()+1].getType() == '.' ||
           L[currLoc.getRow()][currLoc.getCol()+1].getType() == 'T')&&
           L[currLoc.getRow()][currLoc.getCol()+1].getDist() == -1){
            Q.add(L[currLoc.getRow()][currLoc.getCol()+1]);
            L[currLoc.getRow()][currLoc.getCol()+1].setDist(d+1);
            L[currLoc.getRow()][currLoc.getCol()+1].setPrev(
                       L[currLoc.getRow()][currLoc.getCol()]);
        }
    }
}
