package clnakagawahw1;

import algs.hw1.*;
import algs.hw1.api.*;

/**
 * Copy this class into your project and complete its implementation
 */
public class SlicerFinder implements ISlicerFinder {

	/** 
	 * Complete this implementation.
	 * 
	 * You can inspect the contents of the array for s using the inLeft() and inTop() methods.
	 */
	public Coordinate find(Slicer s, int target) {
		int r = 0;
		int c = r;
		boolean temp = s.inLeft(c, target);
		while (!temp && c < s.N - 1) {
			temp = s.inLeft(++c, target);
		}
		if (!temp) {
			return null;
		}
		temp = s.inTop(r, target);
		while (!temp && r < s.N - 1) {
			temp = s.inTop(++r, target);
		}
		if (!temp) {
			return null;
		}
		return new Coordinate(r, c);
	}	

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int i = 1; i < 20; i++) {
			Slicer s = new Slicer(i, 99);
			s.solver(new SlicerFinder());
		
			System.out.println(i + "\t" + s.getNumProbes());
		}
		System.out.println();
		System.out.println("BONUS: C(n) values give probes for best solution");
		for (int n = 1; n < 65; n*=2) {
			Slicer s = new Slicer(n, 99);
			int numProbes = s.solver(new SlicerFinder());
			System.out.println(n + "\t" + numProbes);
			System.out.println("C(n) = " + 2*n*n*(Math.log(n)/Math.log(2)));
		}
	}
}
