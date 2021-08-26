package clnakagawa.hw2;

// You will need these when you copy this class file into your USERID.hw2 area.
import algs.hw2.*;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * For this assignment, you should only have to modify the part where it says
 * 
 * "HERE IS WHERE YOUR LOGIC GOES."
 * 
 */
public class Q2 {
	
	/**
	 * Find all deals that bring each card to the top. Since there are multiple ones, any that
	 * you find (which can be validated) are allowed.
	 */
	public static void findDeals() {
		SequentialSearchST<Card, Deck> ordered = new SequentialSearchST<>();
		SequentialSearchST<Card, String> shuffles = new SequentialSearchST<>();
		
		// Change below to instantiate YOUR DECK NOT MY FRAGMENTARY EXAMPLE
		MyDeck deck = new MyDeck(13);
		
		// Start your search from the initial state, where the current shuffle is "" (empty)
		// Record that (for the top card which is the AC) this is the deck
		State state = new State(deck, "");
		shuffles.put(deck.peekTop(), "");
		ordered.put(deck.peekTop(), deck);
		
		Queue<State> queue = new Queue<>();
		queue.enqueue(state);
		
		// Until you have an entry for every possible card, continue your search
		while (ordered.size() < deck.size()) {
			State temp = queue.dequeue();
			MyDeck deckIn = (MyDeck) temp.deck.copy();
			deckIn.in();
			MyDeck deckOut = (MyDeck) temp.deck.copy();
			deckOut.out();
			if (!ordered.contains(deckIn.peekTop())) {
				ordered.put(deckIn.peekTop(), deckIn);
				shuffles.put(deckIn.peekTop(), temp.shuffle + "I");
			}
			if (!ordered.contains(deckOut.peekTop())) {
				ordered.put(deckOut.peekTop(), deckOut);
				shuffles.put(deckOut.peekTop(), temp.shuffle + "O");
			}
			queue.enqueue(new State(deckIn, temp.shuffle + "I"));
			queue.enqueue(new State(deckOut, temp.shuffle + "O"));
			// HERE IS WHERE YOUR LOGIC GOES.....
		}
		
		for (Card c : new AllCards()) {
			System.out.println(c + "\t" + shuffles.get(c));
		}
	}
	public static void main(String[] args) {
		findDeals();
	} 
}
