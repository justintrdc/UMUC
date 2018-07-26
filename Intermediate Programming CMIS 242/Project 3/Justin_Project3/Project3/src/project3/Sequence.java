/**
 * Justin Casteel
 * June 26, 2016
 * Project 3
 * INTERMEDIATE PROGRAMMING CMIS 242 6382
 * ECLIPSE IDE Mars.2 Release (4.5.2)
 */

package project3;

public class Sequence {
	
	private static int efficiency = 0;
	
	/** 
	 * computerIterative receives the entry value of the user as the value "n"
	 * and then checks if n is 0 or 1 and adds to the efficiency counter to ensure accuracy.
	 * The for loop begins with i = 2.
	 */	
	public static int computeIterative(int n) {
		efficiency = 0;
		int returnValue = 0;
		if (n == 0) {
			efficiency++;
			return 0;
		} else if (n == 1) {
			efficiency++;
			return 1;
		} else {
			int last = 1;
			int nextLast = 0;
			for (int i = 2; i <= n; i++) {
				efficiency++;
				returnValue = 2 * last + nextLast;
				nextLast = last;
				last = returnValue;
			}
		}
		return returnValue;
	}
	
	/**
	 * Sets the efficiency counter to 0 and receives user input as "n"
	 * and then calls to the check method.
	 */
	public static int computeRecursive(int n) {
		efficiency = 0;
		return computeRecursiveCheck(n);
	}
	
	/**
	 * The computerRecursive method has two similar cases to the 
	 * computerIterative method by increasing the efficiency counter 
	 * by one.  To perform the operation successfully, the method
	 * must call itself two times. 
	 */
	private static int computeRecursiveCheck(int n) {
		if (n == 0) {
			efficiency++;
			return 0;
		} else if (n == 1) {
			efficiency++;
			return 1;
		} else {
			efficiency++;
			return 2 * computeRecursiveCheck(n - 1) + computeRecursiveCheck(n - 2);
		}
	}
	
	public static int getEfficiency() {
		return efficiency;
	}
	
	//Private Constructor will prevent the instantiation of this class directly.
	private Sequence() {}
}
