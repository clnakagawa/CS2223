package clnakagawa.hw3;

import java.io.IOException;

import algs.hw3.ShakespearePlay;

public class Q3 {
	public static void main(String[] args) throws IOException { 
		BST allPlay = new BST();
		for (int i = 1; i < 39; i++) {
			ShakespearePlay sp = new ShakespearePlay(i);
			for (String s : sp) {
				Integer count = allPlay.get(s);
				if (count == null) {
					allPlay.put(s, 1);
				}
				else {
					allPlay.put(s, count+1);
				}
			}
		}
		String MOSTCOMMON = allPlay.mostFrequent();
		System.out.println("a)");
		System.out.println("Most Frequent Word is " + MOSTCOMMON);
		for (int i = 1; i < 39; i++) {
			ShakespearePlay sp = new ShakespearePlay(i);
			BST somePlay = new BST();
			for (String s : sp) {
				Integer count = somePlay.get(s);
				if (count == null) {
					somePlay.put(s, 1);
				}
				else {
					somePlay.put(s, count+1);
				}
			}
			String words[] = new String[5];
			boolean hasMostCommon = false;
			for (int j = 0; j < 5; j++) {
				words[j] = somePlay.mostFrequent();
				somePlay.delete(words[j]);
				if (words[j].equals(MOSTCOMMON)) {
					hasMostCommon = true;
				}
			}
			if (!hasMostCommon) {
				System.out.println();
				System.out.println("b)");
				for (int j = 0; j < 5; j++) {
					System.out.print(words[j] + " ");
				}
				System.out.println(sp.getTitle());
			}
		}
	}
}
