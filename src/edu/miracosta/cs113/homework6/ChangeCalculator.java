package edu.miracosta.cs113.homework6;

import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileOutputStream;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintStream;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {
	
	public static int[] coins = {25, 10, 5, 1}; // Coin values
	public static ArrayList<String> coinCombos = new ArrayList<String>();// ArrayList to store the coin combonations
	/**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        // TODO:
        // Implement a recursive solution following the given documentation.
    	
    	coinCombos.clear();//Make sure all combos are clear
    	change(cents, 0, 0, 0, cents);
    	
    	for(int i = coinCombos.size() - 1; i >= 0; i--) {//Prints combos in ascending order (# of coins)
    		System.out.println(coinCombos.get(i));
    	}
    	return coinCombos.size();//Returns the amount of combos
    }
    
    /**
     *Recursive method that adds combos to the array list.
     *
     * @param cents a monetary value in cents
     * @param quarters amount of quarters
     * @param dimes amount of dimes
     * @param nickels amount of nickels
     * @param pennys amount of pennys
     * 
     */
    private static void change(int cents, int quarters, int dimes, int nickels, int pennys) {
    	//if the total value of coins > the cents given, return
    	if(quarters * coins[0] + dimes * coins[1] + nickels * coins[2] + pennys * coins[3] > cents) {
    		return;
    	}
    	
    	String combo = "[" + quarters + ", " +  dimes + ", " +nickels+ ", " + pennys +"]";
    	
    	//Adds the combo to the array list if its unique
    	if(!coinCombos.contains(combo)) {
    		coinCombos.add(combo);
    	}
    	
    	//Goes through every combo
    	if(pennys >= 5) {
    		change(cents, quarters, dimes, nickels + 1, pennys - 5);
    	}
    	if(pennys >= 10) {
    		change(cents, quarters, dimes + 1, nickels, pennys - 10);
    	}
    	if(pennys >= 25) {
    		change(cents, quarters + 1, dimes, nickels , pennys - 25);
    	}
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
    	coinCombos.clear();
    	calculateChange(cents);
    	try {
    		File outputFile = new File("CoinCombinations.txt");
    		if(outputFile.createNewFile()) {
    			System.out.println("File successfully created!");
    		} 
    		
    		PrintStream outputStream = new PrintStream(new FileOutputStream(outputFile));
			for(int i = coinCombos.size() - 1; i >= 0; i--) {
	    		outputStream.println(coinCombos.get(i));
	    	}
			outputStream.close();
    	} catch(IOException e) {
    		System.out.println("cannot open file");
    	}
    }

} // End of class ChangeCalculator