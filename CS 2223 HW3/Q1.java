package clnakagawa.hw3;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;
import algs.days.day16.ComparableTimSort;
import algs.hw3.CountedItem;
import algs.hw3.PrimitiveTimSort;

/**
 * 
 * Use the existing SortTrial class, and write your own for your implementation
 * of TimSort and also the HeapSort 
 * 
 * https://shakespeare.folger.edu/shakespeares-works/hamlet/download/
 * 
 * What is the longest word which is not a modern English word, according to
 * our dictionary?
 */
public class Q1 {
	
	/** Return time to sort array using merge sort. */
	public static double mergeSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU(); 
		edu.princeton.cs.algs4.Merge.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using quick sort. */
	public static double quickSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Quick.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Insertion Sort. */
	public static double insertionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Insertion.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Selection Sort. */
	public static double selectionSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Selection.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Heap Sort. */
	public static double heapSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		edu.princeton.cs.algs4.Heap.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Primitive Tim Sort. */
	public static double primitiveTimSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		PrimitiveTimSort.sort(A);
		return start.elapsedTime();
	}
	
	/** Return time to sort array using Optimized Tim Sort. */
	public static double builtinSort(Comparable<?>[] A) {
		StopwatchCPU start = new StopwatchCPU();
		ComparableTimSort.sort(A);
		return start.elapsedTime();
	}
	
	/** Determine if the array is sorted. */
	public static boolean isSorted(Comparable[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i-1].compareTo(A[i]) > 0) {
				return false;
			}
		}
		return true;
	}

	/** 
	 * Given a sorted array of CountedItem<String> objects, ensure that for 
	 * any two index positions, i and j, if A[i] is equal to A[j] and i < j, 
	 * then A[i].earlier(A[j]) is true.
	 * 
	 * Performance must be O(N).
	 */
	public static boolean isSortedArrayStable(CountedItem[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i-1].equals(A[i]) && A[i].earlier(A[i-1])) {
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * Given an array of integers, return a CountedItem<Integer> array. If you construct
	 * and add the objects from left to right, then for two duplicate values A[i] and A[j],
	 * you know that the counter for A[i] is smaller than the counter for A[j] if i < j. 
	 */
	static CountedItem<Integer>[] toCountedArray(Integer vals[]) {
		CountedItem<Integer>[] copy = new CountedItem[vals.length];
		for (int i  = 0; i < copy.length; i++) {
			copy[i] = new CountedItem<>(vals[i]);
		}
		
		return copy;
	}
	
	public static void trial1_1() {
		System.out.println("Q1.1");
		
		// create array of integers with opportunities for duplicates
		Integer vals[] = new Integer[4096];
		for (int i = 0; i < vals.length; i++) { vals[i] = StdRandom.uniform(128); }
		CountedItem<Integer>[] forHeap = toCountedArray(vals);
		edu.princeton.cs.algs4.Heap.sort(forHeap);
		CountedItem<Integer>[] forInsertion = toCountedArray(vals);
		edu.princeton.cs.algs4.Insertion.sort(forInsertion);
		CountedItem<Integer>[] forMerge = toCountedArray(vals);
		edu.princeton.cs.algs4.Merge.sort(forMerge);
		CountedItem<Integer>[] forQuick = toCountedArray(vals);
		edu.princeton.cs.algs4.Quick.sort(forQuick);
		CountedItem<Integer>[] forSelection = toCountedArray(vals);
		edu.princeton.cs.algs4.Selection.sort(forSelection);
		CountedItem<Integer>[] forTimPrimitive = toCountedArray(vals);
		PrimitiveTimSort.sort(forTimPrimitive);
		CountedItem<Integer>[] forTimOptimized = toCountedArray(vals);
		ComparableTimSort.sort(forTimOptimized);
		System.out.println("HeapSort:          \t" + isSortedArrayStable(forHeap));
		System.out.println("InsertionSort:     \t" + isSortedArrayStable(forInsertion));
		System.out.println("MergeSort:         \t" + isSortedArrayStable(forMerge));
		System.out.println("QuickSort:         \t" + isSortedArrayStable(forQuick));
		System.out.println("SelectionSort:     \t" + isSortedArrayStable(forSelection));
		System.out.println("TimSort Primitive: \t" + isSortedArrayStable(forTimPrimitive));
		System.out.println("TimSort Optimized: \t" + isSortedArrayStable(forTimOptimized));
		// using this SAME ARRAY, create different CountedItem<> arrays and 
		// determine which of the sorting algorithms are stable, and which ones are not.
	}
	
	public static void trial1_2() {
		System.out.println("Q1.2");
		System.out.println("N\t\tTimSort \tPrimTS  \tMerge   \tQuick   \tHeap    ");
		for (int i = 1048576; i <= 16777216; i = i * 2) {
			Integer heapVals[] = new Integer[i];
			Integer mergeVals[] = new Integer[i];
			Integer primTSVals[] = new Integer[i];
			Integer quickVals[] = new Integer[i];
			Integer TSVals[] = new Integer[i];
			for (int j = 0; j < i; j++) {
				Integer temp = StdRandom.uniform(i);
				heapVals[j] = temp;
				mergeVals[j] = temp;
				primTSVals[j] = temp;
				quickVals[j] = temp;
				TSVals[j] = temp;
			}
			System.out.println(i + "        " + builtinSort(TSVals)
											+ "        " + primitiveTimSort(primTSVals)
											+ "        " + mergeSort(mergeVals)
											+ "        " + quickSort(quickVals)
											+ "        " + heapSort(heapVals));
		}
		// completed by student
	}
	
	
	public static void trial1_3() {
		System.out.println("Q1.3");
		System.out.println("N\t\tTimSort \tPrimTS  \tMerge   \tQuick   \tHeap    ");
		for (int i = 1048576; i <= 16777216; i = i * 2) {
			Integer heapVals[] = new Integer[i];
			Integer mergeVals[] = new Integer[i];
			Integer primTSVals[] = new Integer[i];
			Integer quickVals[] = new Integer[i];
			Integer TSVals[] = new Integer[i];
			for (int j = 0; j < i; j++) {
				Integer temp = StdRandom.uniform(i/512);
				heapVals[j] = temp;
				mergeVals[j] = temp;
				primTSVals[j] = temp;
				quickVals[j] = temp;
				TSVals[j] = temp;
			}
			System.out.println(i + "        " + builtinSort(TSVals)
											+ "        " + primitiveTimSort(primTSVals)
											+ "        " + mergeSort(mergeVals)
											+ "        " + quickSort(quickVals)
											+ "        " + heapSort(heapVals));
		}
		// completed by student
		// completed by student
	}
	
	public static void trial1_4() {
		System.out.println("Q1.4");
		System.out.println("N\t\tTimSort \tPrimTS  \tMerge   \tQuick   \tHeap    ");
		for (int i = 1048576; i <= 16777216; i = i * 2) {
			Integer heapVals[] = new Integer[i];
			Integer mergeVals[] = new Integer[i];
			Integer primTSVals[] = new Integer[i];
			Integer quickVals[] = new Integer[i];
			Integer TSVals[] = new Integer[i];
			for (int j = 0; j < i; j++) {
				Integer temp = i - j - 1;
				heapVals[j] = temp;
				mergeVals[j] = temp;
				primTSVals[j] = temp;
				quickVals[j] = temp;
				TSVals[j] = temp;
			}
			System.out.println(i + "        " + builtinSort(TSVals)
											+ "        " + primitiveTimSort(primTSVals)
											+ "        " + mergeSort(mergeVals)
											+ "        " + quickSort(quickVals)
											+ "        " + heapSort(heapVals));
		}
		// completed by student
	}
	
	public static void main(String[] args) {
		trial1_1();
		trial1_2();
		trial1_3();
		trial1_4();
	}
}
