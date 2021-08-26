package clnakagawahw1;

import algs.hw1.*;
import algs.hw1.api.*;

/**
 * Copy this class into your USERID.hw1 package and complete it.
 */
public class FuzzyFinder implements IFuzzySquareFinder {

	/**
	 * Return the Coordinate(r,c) where target exists. If it is not in 
	 * the 2D array, return null.
	 * 
	 * You can inspect the contents of the array for fs using the probe3x3() method.
	 */ 
	public Coordinate find(FuzzySquare fs, int target) {
		int r = 0;
		int c = r;
		int code = fs.probe3x3(r, c, target);
		while (code != 0 && r < fs.N) {
			if (code == 5) {
				return null;
			}
			else {
				c = c + 3;
				if (c - 1 > fs.N - 1) {
					c = 0;
					r = r + 2;
				}
				code = fs.probe3x3(r, c, target);
			}
		}
		if (code != 0) {
			return null;
		}
		r = r + 1 < fs.N && fs.probe3x3(r + 2, c, target) == 0 ? r + 1 : r;
		r = r - 1 > -1 && fs.probe3x3(r - 2, c, target) == 0 ? r - 1 : r;
		c = c + 1 < fs.N && fs.probe3x3(r, c + 2, target) == 0 ? c + 1 : c;
		c = c - 1 > -1 && fs.probe3x3(r, c - 2, target) == 0 ? c - 1 : c;
		return new Coordinate(r, c);
	}	

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 5; i < 40; i++) {
			FuzzySquare fs = new FuzzySquare(i, 99);
			fs.solver(new FuzzyFinder());
			System.out.println(i + "\t" + fs.getNumProbes());
		}
	}
}
