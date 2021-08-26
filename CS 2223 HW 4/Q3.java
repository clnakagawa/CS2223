package clnakagawa.hw4;

import edu.princeton.cs.algs4.*;

/**
 * How many random directed graphs of V vertices have a cycle? and are connected?
 * 
 * Create a random graph by adding an edge between two vertices u and v with a probability
 * of 50%.
 * 
 * Run the same trial, this time using graphs whose edges each have a probability of 1/N chance
 * of being present.
 */
public class Q3 {
	public static void main(String[] args) {
		System.out.println("Graphs with probability 0.5");
		System.out.println("N\t#Cycles\t#Connected");
		for (int i = 2; i <= 15; i++) {
			int numCycles = 0;
			int numConnect = 0;
			for (int j = 0; j < 10000; j++) {
				Digraph temp = new Digraph(i);
				for (int a = 0; a < i; a++) {
					for (int b = 0; b < i; b++) {
						if (a != b) {
							if (Math.random() < 0.5) {
								temp.addEdge(a, b);
							}
						}
					}
				}
				if (new DirectedCycle(temp).hasCycle()) {
					numCycles++;
				}
				DirectedDFS connectCheck = new DirectedDFS(temp, 0);
				boolean connected = true;
				for (int k = 0; k < temp.V(); k++) {
					if (!connectCheck.marked(k)) {
						connected = false;
					}
				}
				if (connected) {
					numConnect++;
				}
			}
			System.out.println(i+"\t"+numCycles+"\t"+numConnect);
		}
		System.out.println("\nGraphs with probability 1.0/N");
		System.out.println("N\t#Cycles\t#Connected");
		for (int i = 2; i <= 15; i++) {
			int numCycles = 0;
			int numConnect = 0;
			for (int j = 0; j < 10000; j++) {
				Digraph temp = new Digraph(i);
				for (int a = 0; a < i; a++) {
					for (int b = 0; b < i; b++) {
						if (a != b) {
							if (Math.random() < 1.0 / i) {
								temp.addEdge(a, b);
							}
						}
					}
				}
				if (new DirectedCycle(temp).hasCycle()) {
					numCycles++;
				}
				DirectedDFS connectCheck = new DirectedDFS(temp, 0);
				boolean connected = true;
				for (int k = 0; k < temp.V(); k++) {
					if (!connectCheck.marked(k)) {
						connected = false;
					}
				}
				if (connected) {
					numConnect++;
				}
			}
			System.out.println(i+"\t"+numCycles+"\t"+numConnect);
		}
	}
}
