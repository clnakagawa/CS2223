package clnakagawa.hw4;

import edu.princeton.cs.algs4.*;
import algs.days.day21.BFSMapAnimation;
import algs.days.day21.DFSMapAnimation;
import algs.hw4.map.*;
import edu.princeton.cs.algs4.Graph;
import algs.hw4.map.*;
/**
 * The goal of this question is to:
 * 
 * 1. Find the western-most location in Massachusetts
 * 2. Find the eastern-most location in Massachusetts
 * 3. Determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT
 * 4. Next create a copy of the highway graph that removes all line segments from I-90, the 
 *    Massachusetts Turnpike toll road.
 * 5. From this copy, determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT.
 */
public class Q2 {
	
	/**
	 * This method must create a copy of the graph, which you can do by recreate a graph with 
	 * the same number of vertices as the original one, BUT you only add an edge to the copy
	 * if the edge in the original graph IS NOT EXCLUSIVELY a line segment from the Mass Pike.
	 * 
	 * For example, in the data set you will see two nodes:
	 * 
	 * 		I-90@49|MA 42.169702 -72.580876
	 * 		I-90@51|MA 42.161558 -72.541995
	 * 
	 * These lines correspond to vertex #639 (the first one @49) and vertex #641 (the second one @51).
	 * Because the label for both of these vertices includes "I-90@" this edge must not appear in 
	 * the copied graph, since it is a highway segment exclusively on the Mass Turnpike.
	 * 
	 * Note that the edge is eliminated only when BOTH are present. For example, the following
	 * line segment will remain:
	 * 
	 * 		I-95(23)/MA128	                ==> vertex #705
	 * 		I-90@123B&I-95@24&MA128@24(95)  ==> vertex #1785
	 */
	static Information remove_I90_segments(Information info) {
		Graph copy = new Graph(info.graph.V());
		for (int i = 0; i < info.graph.V(); i++) {
			for (Integer neighbor : info.graph.adj(i)) {
				if (!(info.labels.get(i).contains("I-90@") && info.labels.get(neighbor).contains("I-90@"))) {
					copy.addEdge(i, neighbor);
				}
			}
		}
		// DO YOUR WORK HERE...
		
		Information newInfo = new Information(copy, info.positions, info.labels);
		return newInfo;
	}
	
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int westernMostVertex(Information info) {
		Iterable<Integer> iter = info.positions.keys();
		Integer westKey = iter.iterator().next();
		for (Integer key : iter) {
			Integer tempKey = key;
			if (info.positions.get(tempKey).longitude < info.positions.get(westKey).longitude) {
				westKey = tempKey;
			}
		}
		return westKey;
	}
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int easternMostVertex(Information info) {
		Iterable<Integer> iter = info.positions.keys();
		Integer eastKey = iter.iterator().next();
		for (Integer key : iter) {
			Integer tempKey = key;
			if (info.positions.get(tempKey).longitude > info.positions.get(eastKey).longitude) {
				eastKey = tempKey;
			}
		}
		return eastKey;
	}
	
	/** 
	 * This helper method returns the southern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int southernMostVertex(Information info) {
		Iterable<Integer> iter = info.positions.keys();
		Integer southKey = iter.iterator().next();
		for (Integer key : iter) {
			Integer tempKey = key;
			if (info.positions.get(tempKey).latitude < info.positions.get(southKey).latitude) {
				southKey = tempKey;
			}
		}
		return southKey;
	}
	
	
	
	/** 
	 * This helper method returns the northern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int northernMostVertex(Information info) {
		Iterable<Integer> iter = info.positions.keys();
		Integer northKey = iter.iterator().next();
		for (Integer key : iter) {
			Integer tempKey = key;
			if (info.positions.get(tempKey).latitude > info.positions.get(northKey).latitude) {
				northKey = tempKey;
			}
		}
		return northKey;
	} 
	
	public static void main(String[] args) {
		System.out.println("2.1");
		Information info = HighwayMap.undirectedGraph();

		BreadthFirstPaths bfsearch = new BreadthFirstPaths(info.graph, westernMostVertex(info));
		System.out.println("Western most vertex: " + info.labels.get(westernMostVertex(info)));
		System.out.println("Eastern most vertex: " + info.labels.get(easternMostVertex(info)));
		System.out.println("Shortest distance between eastern most and western most: " + bfsearch.distTo(easternMostVertex(info)));
		bfsearch = new BreadthFirstPaths(info.graph, southernMostVertex(info));
		System.out.println("Southern most vertex: " + info.labels.get(southernMostVertex(info)));
		System.out.println("Northern most vertex: " + info.labels.get(northernMostVertex(info)));
		System.out.println("Shortest distance between southern most and northern most: " + bfsearch.distTo(northernMostVertex(info)));
		System.out.println("\n2.2");
		DepthFirstPaths dfsearch = new DepthFirstPaths(info.graph, easternMostVertex(info)); 
		int count = 0;
		for (Integer vert : dfsearch.pathTo(westernMostVertex(info))) {
			count++;
		}
		System.out.println("Shortest distance between eastern most and western most: " + (count-1));
		count = 0;
		dfsearch = new DepthFirstPaths(info.graph, northernMostVertex(info));
		for (Integer vert: dfsearch.pathTo(southernMostVertex(info))) {
			count++;
		}
		System.out.println("Shortest distance between southern most and northern most: " + (count-1));
		System.out.println("\n2.3");
		info = remove_I90_segments(info);
		bfsearch = new BreadthFirstPaths(info.graph, westernMostVertex(info));
		System.out.println("Western most vertex: " + info.labels.get(westernMostVertex(info)));
		System.out.println("Eastern most vertex: " + info.labels.get(easternMostVertex(info)));
		System.out.println("Shortest distance between eastern most and western most: " + bfsearch.distTo(easternMostVertex(info)));
		bfsearch = new BreadthFirstPaths(info.graph, southernMostVertex(info));
		System.out.println("Southern most vertex: " + info.labels.get(southernMostVertex(info)));
		System.out.println("Northern most vertex: " + info.labels.get(northernMostVertex(info)));
		System.out.println("Shortest distance between southern most and northern most: " + bfsearch.distTo(northernMostVertex(info)));
		System.out.println("\nDistance between the most east and west vertices increases while the distance between the most north and south vertices doesn't change");
	}
}
