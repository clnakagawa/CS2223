package clnakagawahw1;

import algs.hw1.*;
import algs.hw1.api.*;

public class ManhattanSquareFinder implements IManhattanSquareFinder {

	/** 
	 * Return the Coordinate of location in ManhattanSquare containing target.
	 * 
	 * You can inspect the contents of the array for ms using the distance() method.
	 */
	public Coordinate find(ManhattanSquare ms, int target) {
		int r = 0;
		int c = r;
		int total = ms.distance(0, 0, target);
		r = total == 0 ? 0 : (total + ms.distance(0, ms.N - 1, target) + 1 - ms.N) / 2;
		c = total - r;
		return new Coordinate(r, c);
	}	

	// You do not need to modify below this line.
	// ------------------------------------------
	public static void main(String[] args) {
		for (int n = 1; n < 10; n++) {
			ManhattanSquare ms = new ManhattanSquare(n, 99);
			int numProbes = ms.solver(new ManhattanSquareFinder());
			System.out.println(n + "\t" + numProbes);
		}
	}
}
