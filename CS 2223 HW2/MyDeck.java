package clnakagawa.hw2;

import algs.hw2.Card;
import algs.hw2.Suit;
import algs.hw2.Deck;
import algs.hw2.Node;

/**
 * COPY THIS CLASS into your development area and complete it.
 * @author Home
 *
 */
public class MyDeck extends Deck {
	
	/**
	 * Ensure that no one OUTSIDE of this class invokes the no-argument constructor. You will find
	 * it useful to have this constructor within the copy() method since it must return an accurate
	 * copy of the current Deck, and it will first need to construct an "empty" MyDeck object
	 * without using the MyDeck(int max_rank) constructor.
	 * 
	 */
	protected MyDeck() {
		// You do not need to modify this method. This constructor exists to ensure that 
		// within this class, you can construct an empty MyDeck whose first and last are null.
	}
	int size = 0;
	/** 
	 * Construct a playing deck with {max_rank} cards in specific order.
	 * 
	 * Once done, the linked list of card Nodes must represent a deck that looks like the following (if 
	 * {max_rank} were 3). The suites are ordered Club < Diamond < Hearts < Spades.
	 * 
	 * AC -> 2C -> 3C -> AD -> 2D -> 3D -> AH -> 2D -> 3H -> AS -> 2S -> 3S
	 * 
	 * Note your deck will have 4*{max_rank} cards.
	 * 
	 * Performance must be O(N) where N is max_rank.
	 */
	public MyDeck(int max_rank) {
		if (max_rank < Card.ACE) { throw new IllegalArgumentException("max_rank must be between " + Card.ACE + " and " + Card.KING + " respectively"); }
		first = new Node(new Card(Suit.values()[0], 1));
		Node temp = first;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < max_rank + 1; j++) {
				temp.card = new Card(Suit.values()[i], j);
				last = temp;
				temp.next = new Node(new Card(Suit.values()[0], 1));
				temp = temp.next;
			}
		}
		last.next = null;
		size = max_rank * 4;
	}

	@Override
	public Card peekTop() {
		return this.first.card;
	}

	@Override
	public Card peekBottom() {
		return this.last.card;
	}

	@Override
	public boolean match(Card c, int n) {
		Node temp = this.first;
		for (int i = 1; i < n; i++) {
			temp = temp.next;
		}
		return c.equals(temp.card);
	}
	
	@Override
	public Deck copy() {
		MyDeck newDeck = new MyDeck();
		newDeck.first = new Node(new Card(Suit.values()[0], 1));
		newDeck.last = newDeck.first;
		Node temp = newDeck.first;
		Node current = this.first;
		while (current != null) {
			temp.card = new Card(current.card.suit, current.card.rank);
			newDeck.last = temp;
			temp.next = new Node(new Card(Suit.values()[0], 1));
			temp = temp.next;
			current = current.next;
		}
		newDeck.last.next = null;
		newDeck.size = this.size;
		return newDeck;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	protected Node cutInHalf() {
		if (this.size % 2 == 1) { throw new RuntimeException("Odd number of cards in deck"); }
		Node temp = this.first;
		for (int i = 0; i < (this.size / 2) - 1; i++) {
			this.first = this.first.next;
		}
		Node newFirst = this.first.next;
		this.size /= 2;
		this.first.next = null;
		this.first = newFirst;
		return temp;
	}

	@Override
	public void out() {
		Node left = this.cutInHalf();
		Node right = this.first;
		this.first = left;
		Node temp;
		while (right != null) {
			temp = right;
			right = right.next;
			temp.next = left.next;
			left.next = temp;
			left = left.next.next;
		}
		size *= 2;
	}

	@Override
	public void in() {
		Node left = this.cutInHalf();
		Node right = this.first;
		Node temp = left;
		left = right;
		right = temp;
		while (right != null) {
			temp = right;
			right = right.next;
			temp.next = left.next;
			left.next = temp;
			left = left.next.next;
		}
		size *= 2;
	}

	@Override
	public String representation() {
		Node temp = this.first;
		String outStr = "";
		while (temp != null) {
			outStr = outStr + temp.card.toString();
			temp = temp.next;
		}
		return outStr;
	}
	
	@Override
	public boolean isInOrder() {
		if (size % 4 != 0) { return false; }
		Node temp = first;
		int max = size / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < max + 1; j++) {
				if (temp.card.suit.getValue() != i || temp.card.rank != j) {
					return false;
				}
				temp = temp.next;
			}
		}
		return true;
	}

	@Override
	public boolean isInReverseOrder() {
		if (size % 4 != 0) { return false; }
		Node temp = first;
		int max = size / 4;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < max + 1; j++) {
				if (temp.card.suit.getValue() != 3 - i || temp.card.rank != max + 1 - j) {
					return false;
				}
				temp = temp.next;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		MyDeck deck = new MyDeck(3);
		System.out.println(deck.representation());
		Deck copyDeck = deck.copy();
		System.out.println(copyDeck.representation());
		System.out.println(deck.peekTop().toString());
		System.out.println(deck.peekBottom().toString());
		Node cut = deck.cutInHalf();
		while (cut != null) {
			System.out.print(cut.card.toString());
			cut = cut.next;
		}
		System.out.println();
		System.out.println(deck.representation());
		deck = new MyDeck(3);
		deck.out();
		System.out.println(deck.representation());
		deck = new MyDeck(3);
		deck.in();
		System.out.println(deck.representation());
		deck = new MyDeck(3);
		System.out.println(deck.isInOrder());
	}
}
