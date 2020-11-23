package edu.miracosta.cs113.homework7;

import java.util.Scanner;

public class MorseCodeDecoder {
	
	public static void main(String[] args) {
		MorseCodeTree mct = new MorseCodeTree();
		Scanner input = new Scanner(System.in);
		
		System.out.println("----Welcome to the Morse Code Decoder!----");
		System.out.println("Instructions: Input morse code, have each word seperated by a space or it will error.");
		String answer;
		do {
			System.out.print("Please enter morse code: ");
			answer = input.nextLine();
			if(answer.equalsIgnoreCase("quit")) continue;
			
			try {
				System.out.println("Decoded: " + mct.translateFromMorseCode(answer));
				
			} catch(Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}while(!answer.equalsIgnoreCase("quit"));
	}
}
