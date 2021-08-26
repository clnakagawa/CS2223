package clnakagawa.hw2;

public class Q1 {
	public static void main(String args[]) {
		System.out.println("Table for Question 1.1");
		for (int max_rank = 1; max_rank <= 20; max_rank++) {
			MyDeck d = new MyDeck(max_rank);
			int count = 0;
			do {
				d.in();
				count++;
				// Used for bonus 1.3.1
//				if (d.isInReverseOrder()) {
//					System.out.println("reverse exists for max_rank = " + max_rank);
//				}
			} while (!d.isInOrder());
			System.out.println(max_rank + "\t" + count);
		}
		System.out.println("Table for Question 1.2");
		for (int max_rank = 1; max_rank <= 20; max_rank++) {
			MyDeck d = new MyDeck(max_rank);
			int count = 0;
			
			do {
				d.out();
				count++;
			} while (!d.isInOrder());
			System.out.println(max_rank + "\t" + count);
		}
		System.out.println("Question 1.3");
		MyDeck d = new MyDeck(13);
		int count = 0;
		while (!d.isInReverseOrder()) {
			d.in();
			count++;
		}
		System.out.println("Number of in shuffles: " + count);
	}
}
