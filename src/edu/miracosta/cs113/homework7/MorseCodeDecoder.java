package edu.miracosta.cs113.homework7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MorseCodeDecoder {
	
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
	
	public static void printMenu() {
		System.out.println("Menu:");
		System.out.println("1 - Print morse code alphabet");
		System.out.println("2 - Input text file");
		System.out.println("3 - Enter lines of morse code");
	}
	
	public static void enterLine(MorseCodeTree mct, Scanner input) {
		System.out.print("Please enter morse code: ");
		String morseCode = input.nextLine();
		try {
			System.out.println("Decoded: " + mct.translateFromMorseCode(morseCode));
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static String getMorseCode(MorseCodeTree mct) {
		ArrayList<String> combos = mct.getCombos();
		String comboList = "";
		Collections.sort(combos);
		for(String s: combos) {
			if(s.length() < 7) {
				int length = s.length();
				for(int i = 0; i < (7 - length); i++ ) {
					s += " ";
				}
			}
			comboList += s + " | ";
			if((s.charAt(0) - 'a') % 4 == 3) {
				comboList += "\n";
			}
		}
		return comboList;
	}
}
