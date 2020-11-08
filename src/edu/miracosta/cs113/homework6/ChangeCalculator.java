package edu.miracosta.cs113.homework6;

import java.util.ArrayList;

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
	public static int[] coins = {1, 5, 10, 25};
	//Indexes: 0 = root, 1 = quarters, 2 = dimes, 3 = nickles, 4 = pennies.
	
	public static void main(String[] args) {
		System.out.println(calculateChange(90));
	}
	
	//Not done
    public static int calculateChange(int cents) {
        // TODO:
        // Implement a recursive solution following the given documentation.
    	int count = 0;
        for (int i = 0; i <= cents / 25; i++) {
            for (int j = 0; j <= cents / 10; j++) {
                for (int k = 0; k <= cents / 5; k++) {
                    for (int l = 0; l <= cents; l++) {
                        int v = i * 25 + j * 10 + k * 5 + l;
                        if (v == cents) {
                            count++;
                        } else if (v > cents) {
                            break;
                        }
                    }
                }
            }
        }
        return count;

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
    }

} // End of class ChangeCalculator