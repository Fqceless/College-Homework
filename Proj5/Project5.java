/**
 * Name: Project5.java
 * Desc: Main class for a program that solves a maze.
 * Date: 11/3/2022
 * @author Christopher LaFave
 * @version 1.0
 */

package proj5;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Scanner;

public class Project5 {
    public static void main(String[] args) {
        //declare the scanner and input the row and column data
        Scanner in = new Scanner(System.in);
        int rows = Integer.parseInt(in.next());
        int cols = Integer.parseInt(in.next());
        
        /* create vars to use later
        input: takes the maze input row by row as a string
        pathFound: triggers if the path was found
        target: stores the target location
            -only get this by finding it in the path,
             I don't cheat by finding it during input phase.
        targetPath: A stack that makes it easy for me to print the target path
        pathlen: length of the path
        */
        String input;
        boolean pathFound = false;
        Location target = new Location();
        Stack<Location> targetPath = new Stack<>();
        int pathLen = 0;
        
        /* create main data storage
        searchQueue: stores the locations to be searched next
        maze: just a 2D map of the maze
        */
        Queue<Location> searchQueue = new LinkedList<>();
        Location[][] maze = new Location[rows][cols];
        
        /* initialize the maze
        if it finds the start point, add it to the queue 
        and make its distance variable 0.
        */
        for(int i = 0; i < rows; i++){
            input = in.next();
            for (int j = 0; j < cols; j++){
                maze[i][j] = new Location(input.charAt(j), new Coord(i, j));
                if(input.charAt(j) == 'S'){
                    searchQueue.add(maze[i][j]);
                    maze[i][j].setDist(0);
                }
            }
        }   
        
        /* search algorithm
        if the queue is empty:
            -It ran out of locations to search, no solution.
            -Exit the program
        else if you found the target location:
            -Set pathFound to true to exit the loop
            -Initialize target and push it onto the targetPath
            -initialize pathLen
        else:
            -Run search method
        */
        while(!pathFound){
            if(searchQueue.isEmpty()){
                System.out.println("Maze not solvable.");
                System.exit(0);
            }
            else if(searchQueue.peek().getType() == 'T'){
                pathFound = true;
                target = searchQueue.remove();
                targetPath.push(target);
                pathLen = target.getDist();
            }
            else{
              Search.Compute(maze, searchQueue, searchQueue.peek().getDist());
            }
        }
        
        /* initialize targetPath
        runs back through all of the previous 
        locations and pushes them to the stack.
        once the previous location is null (starting location)
        exit the loop.
        */
        while(target.getPrev() != null){
            target = target.getPrev();
            targetPath.push(target);
        }
        
        // print the target path
        while(!targetPath.isEmpty()){
            targetPath.pop().getLoc().printCoord();
        }
        System.out.println("Total distance = " + pathLen);
    }
}
