package edu.miracosta.cs113.homework3;

import java.util.Scanner;
/**
 * PolynomialCalculator.java: a driver that tests the functionality of the Polynomial Class
 *  
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
public class PolynomialCalculator {
	 /*
     * ALGORITHM:
     * 
     * Instance Scanner input
     * 
     * WHILE true // loop forever
     *      PROMPT the menu
     * 		READ choice
     * 		IF choice is 1
     * 			OUTPUT "What terms do you want to be added?"
     * 			READ terms
     * 			PARSE terms
     * 			ADD terms to the polynomial 1
     * 		IF choice is 2
     * 			OUTPUT "What terms do you want to be added?"
     * 			READ terms
     * 			PARSE terms
     * 			ADD terms to the polynomial 2
     * 		IF choice is 3
     * 			INSTANCE copy of polynomial 1
     * 			ADD polynomial 2 to copy
     * 			OUTPUT polynomial1 + polynomial2 = copy
     * 		IF choice is 4
     * 			OUTPUT "Thank you for using the program!"
     * 			SYSTEM exit
     * 		ELSE 
     * 			OUTPUT "Invalid input, try again!"
     * 		END IF
     * END LOOP
     *
     */

	public static Polynomial poly1 = new Polynomial();
	public static Polynomial poly2 = new Polynomial();
	
	/**
	 * the main method for the Polynomial calculator
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		
		while(true) {
			printMenu();
			
			String choice = input.next();
			input.nextLine();
			
			if(choice.equals("1")) {
				
				addTerms(input, poly1);
				
			} else if(choice.equals("2")) {
				
				addTerms(input, poly2);
				
			} else if(choice.equals("3")) {
				
				Polynomial sumPoly = new Polynomial(poly1);
				sumPoly.add(poly2);
				
				System.out.println(poly1 + " + " + poly2 + "\n = " + sumPoly);
				
				
			} else if(choice.equals("4")) {
				
				System.out.println("Thank you for using the program!");
				System.exit(0);
				
			} else {
				System.out.println("Invalid input, try again.");
			}
			
			
		}
	}
	
	/**
	 * Prints the menu for the Calculator.
	 * 
	 */
	public static void printMenu() {
		System.out.println("----------------------------");
		System.out.println("Polynomial Calulator Menu: ");
		
		System.out.println("Polynomial 1: " + poly1);
		System.out.println("Polynomial 2: " + poly2);
		
		System.out.println("1 - Edit Polynomial 1");
		System.out.println("2 - Edit Polynomial 2");
		System.out.println("3 - Add Polynomials");
		System.out.println("4 - Exit program");
		System.out.print("Input answer: ");
	}
	
	/**
	 * adds a user input term to the polynomial
	 * 
	 * @param input is the scanner object used to get user input
	 * @param poly is the polynomial that is getting a term added to it
	 * 
	 */
	public static void addTerms(Scanner input, Polynomial poly) {
		System.out.println("Please input the term(s) you want to add or type \"clear\":");
		String terms = input.nextLine();
		terms = terms.replaceAll("\\s", "");
		
		if(!terms.equalsIgnoreCase("clear")) {
		
			int tempBeginning = 0;
			for(int i = 0; i < terms.length(); i++) {
				if( (terms.charAt(i) == '-' || terms.charAt(i) == '+') && i != 0) {
					if(terms.charAt(i) == '-' && terms.charAt(i - 1) == '^' ) {
						continue;
					}
					poly.addTerm(terms.substring(tempBeginning, i));
					tempBeginning = i;
				}
				if(i == terms.length() - 1) {
					poly.addTerm(terms.substring(tempBeginning, i + 1));
					break;
				}
			}
			
		} else {
			poly.clear();
		}
	}
	
}
