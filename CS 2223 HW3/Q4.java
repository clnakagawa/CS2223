package clnakagawa.hw3;

import edu.princeton.cs.algs4.AVLTreeST;
import edu.princeton.cs.algs4.StdRandom;

public class Q4 {
	public static void main(String args[]) {
		System.out.println("N\tLargest Height\tNumber Found\n");
		int oldmax = -1;
		for (int i = 1; i <= 40; i++) {
			int max = 0;
			int count = 0;
			for (int j = 0; j < 10000; j++) {
				AVLTreeST<Integer, Integer> temp = new AVLTreeST<Integer, Integer>();
				for (int k = 0; k < i; k++) {
					temp.put(StdRandom.uniform(Integer.MAX_VALUE), StdRandom.uniform(Integer.MAX_VALUE));
				}
				if (temp.height() > max) {
					max = temp.height();
					count = 1;
				}
				else if (temp.height() == max) {
					count++;
				}
			}
			if (max > oldmax) {
				System.out.println(i + "\t" + max + "\t\t" + count + "\n");
				oldmax = max;
			}
		}
	}
}
