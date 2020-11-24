package edu.miracosta.cs113.homework7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * MorseCodeDecoder.java: a driver to use functionality from the MorseCodeTree class
 *  
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
public class MorseCodeDecoder {
	
	/*
     * ALGORITHM:
     * 
     * Instance Scanner input
     * Instance MorseCodeTree mct
     * Instance String answer
     * 
     * WHILE answer != quit
     * 
     *      PROMPT the menu
     * 		READ choice
     * 		SWITCH choice
     * 		choice is 1
     * 			PRINT morse code table
     * 		choice is 2
     * 			INVOKE enterFile method
     * 		choice is 3
     * 			INVOKE enterLine method
     * 		choice is 4
     * 			SYSTEM exit
     * 		ELSE 
     * 			OUTPUT "Invalid input, try again!"
     * 		END IF
     * END LOOP
     *
     */
	public static void main(String[] args) {
		MorseCodeTree mct = new MorseCodeTree();
		Scanner input = new Scanner(System.in);
		String answer;
		System.out.println("----Welcome to the Morse Code Decoder!----");
		do {
			
			printMenu();
			System.out.print("Input: ");
			answer = input.next();
			switch(answer) {
			
				case("1"):
					System.out.println(getMorseCode(mct));
					break;
				
				case("2"):
					input.nextLine();
					enterFile(mct, input);// try: src/edu/miracosta/cs113/homework7/code.txt
					break;
				
				case("3"):
					input.nextLine();
					enterLine(mct, input);
					break;
				
				case("quit"):
					break;
				
				default:
					System.out.println("Invalid input try again.");
					break;
			}
			System.out.println("--------------------------------");
		}while(!answer.equalsIgnoreCase("quit"));
	}
	
	/**
     * Prints the menu of the program
     */
	public static void printMenu() {
		System.out.println("Menu:");
		System.out.println("1 - Print morse code alphabet");
		System.out.println("2 - Input text file");
		System.out.println("3 - Enter lines of morse code");
	}
	
	/**
     * prompts the user to enter a line of morse code to be decoded
     * 
     * @param mct the morse code tree to decode the input
     * @param input the scanner object to collect input
     */
	public static void enterLine(MorseCodeTree mct, Scanner input) {
		System.out.print("Please enter morse code: ");
		String morseCode = input.nextLine();
		try {
			System.out.println("Decoded: " + mct.translateFromMorseCode(morseCode));
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	/**
     * prompts the user to enter a file path to decode the morse code in the file
     * 
     * @param mct the morse code tree to decode the input
     * @param input the scanner object to collect input file
     */
	public static void enterFile(MorseCodeTree mct, Scanner input) {
		System.out.print("Please enter file path: ");
		String fileName = input.nextLine();
		try {
			Scanner fileScan = new Scanner(new File(fileName));
			String morseCode = "";
			
			while(fileScan.hasNext()) {
				morseCode += fileScan.next() + " ";
			}
			
			try {
				System.out.println("Decoded: " + mct.translateFromMorseCode(morseCode));
				
			} catch(Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		} catch(IOException e) {
			System.out.println("File could not be found or opened.");
		}

	}
	
	/**
     * Returns a string that has a grid of all morse code in alphabetical order
     * 
     * @param mct the morse code tree to get the alphabet from
     */
	public static String getMorseCode(MorseCodeTree mct) {
		ArrayList<String> combos = mct.getCombos();
		
		String comboList = "";
		
		Collections.sort(combos);//Sorts the list
		
		for(String s: combos) {
			
			if(s.length() < 7) {
				
				int length = s.length();
				//Adds spaces to the end to make it look nice
				for(int i = 0; i < (7 - length); i++ ) {
					s += " ";
				}
				
			}
			//Divider
			comboList += s + " | ";
			//Breaks in the lines every 4
			if((s.charAt(0) - 'a') % 4 == 3) {
				comboList += "\n";
			}
		}
		return comboList;
	}
}
