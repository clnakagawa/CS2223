package clnakagawa.hw5;

import algs.days.day26.FloydWarshallUndirected;
import algs.hw5.FloydWarshallSolutionAnimation;
import algs.hw5.map.HighwayMapWeighted;
import algs.hw5.map.WeightedInformation;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

/**
 * Question 2 of Homework 5.
 * 
 * Given the Massachusetts highway map data, find two vertices in the graph such that 
 * the shortest distance between them is greater than any other pair of vertices in the graph.
 * 
 * In other words, can you find two locations in Massachusetts such that using the available 
 * map data, you�ve computed the shortest total distance in terms of accumulated mileage, 
 * and no other pair of vertices demands a longer trip.
 */
public class Q2 {
	public static void main(String[] args) {
		WeightedInformation info = HighwayMapWeighted.undirectedGraph();
		EdgeWeightedGraph graph = info.ewgraph;
		
		System.out.println("Computing Floyd-Warshall: This might take up to a minute...");
		FloydWarshallUndirected fw = new FloydWarshallUndirected(graph);
		System.out.println("done");
		
		// THIS IS WHERE YOU MUST DO SOME WORK TO DETERMINE TWO VERTICES
		// THAT HAVE THE GREATEST OF THE SHORTEST DISTANCES.
		
		int a = 0;
		int b = 0;
		double dist = 0;
		
		for (int i = 0; i < graph.V(); i++) {
			for (int j = 0; j < graph.V(); j++) {
				double temp = fw.distTo(i, j);
				if (temp > dist) {
					a = i;
					b = j;
					dist = temp;
				}
			}
		}
		System.out.println("vertex1 is " + a + " (" + info.labels.get(a) + ") " + info.positions.get(a));
		System.out.println("vertex1 is " + b + " (" + info.labels.get(b) + ") " + info.positions.get(b));
		System.out.println("Total mileage is " + dist);
		// To visually animate the solution, properly use integer vertex endpoints. You know this is 
		// right when you see it!
		new FloydWarshallSolutionAnimation(fw).launch(a, b);
	}
}
