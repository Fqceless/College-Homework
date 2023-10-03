/* 
 * Homework #4
 * Chris LaFave
 * Jaedan Biermeier
 */

package algHw4;

import java.util.Random;

public class AlgHw4 {
	public static void main(String args[]) {
		final Random rand = new Random();
		final Integer maxNum = 100;
		
		RedBlackTree ted = new RedBlackTree();
		for(int i = 0; i < 25; i++) {
			ted.InsertNode(rand.nextInt(maxNum));
		}
		ted.PrintTree();
//		System.out.println();
//		
//		ted.DeleteNode(ted.rootNode.GetKey());
//		
//		ted.PrintTree();
	}
}
