/**
 * Name: GCD.java
 * Desc: Finds the greatest common divisor
 * Date: 9/6/2022
 * @author Christopher LaFave
 * @version 1.1
 * Sept 22 2022:
 *    Took out main() to fit in with project 2
 */
package numeric ;

    public class GCD {

        public static int compute(int x, int y) {
            //return condition
            if (y == 0) {
                //make sure no negative numbers get returned
                if (x < 0) {
                    return -x;
                } else {
                    return x;
                }
            } //recursive condition
            else {
                return compute(y, x % y);
            }
            //this is all code gallagher told me to write
            //not sure how it works lol
        }
    }
