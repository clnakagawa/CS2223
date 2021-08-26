package clnakagawahw1;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.*;

/**
 * Complete this implementation that takes a postfix expression and converts it into
 * an Infix Expression using a fixed Capacity stack. Also perform the necessary 
 * computation to produce its value
 * 
 * Using the postfix expression as input
 * 
 *     3 6 + 5 * 8 2 - /
 *  
 * should produce the following as output:
 * 
 *     (((3 + 6) * 5) / (8 - 2)) = 7.5
 *
 * Note that postfix expressions do not need parentheses, which is one of their
 * major selling points.
 */
public class Q3_PostFixToInfix {
	
	public static void main(String[] args) {
		
		FixedCapacityStack<String> exprs = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Double> vals = new FixedCapacityStack<Double>(100);
		double v = 0;
		String e = "";
		while (!StdIn.isEmpty()) {
			// Read token. push if operator.
			String s = StdIn.readString();
			if (s.equals("+")) {
				v = vals.pop();
				vals.push(vals.pop() + v);
				e = exprs.pop();
				exprs.push("(" + exprs.pop() + " + " + e + ")");
			}
			else if (s.equals("-")) {
				v = vals.pop();
				vals.push(vals.pop() - v);
				e = exprs.pop();
				exprs.push("(" + exprs.pop() + " - " + e + ")");
			}
			else if (s.equals("*")) {
				v = vals.pop();
				vals.push(vals.pop() * v);
				e = exprs.pop();
				exprs.push("(" + exprs.pop() + " * " + e + ")");
			}
			else if (s.equals("/")) {
				v = vals.pop();
				vals.push(vals.pop() / v);
				e = exprs.pop();
				exprs.push("(" + exprs.pop() + " / " + e + ")");
			}
			else {
				// Token no operator or paren; must be double value to push
				vals.push(Double.parseDouble(s));
				exprs.push(s);
			}
		}
		// COMPLETE IN HERE...
		
		StdOut.print(exprs.pop() + " = " + vals.pop());
	}
}
