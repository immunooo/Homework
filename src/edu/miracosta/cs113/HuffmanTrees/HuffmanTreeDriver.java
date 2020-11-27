package edu.miracosta.cs113.HuffmanTrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HuffmanTreeDriver {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please input URL: ");
		String URL = input.nextLine();
		System.out.print("Please input output file path: ");
		String filename = input.nextLine();
		String outputFilePathD =  "src\\edu\\miracosta\\cs113\\HuffmanTrees\\"+ filename + "Decode.txt";
		String outputFilePath =  "src\\edu\\miracosta\\cs113\\HuffmanTrees\\"+ filename + "Encode.txt";
		String inputFilePath = "src\\edu\\miracosta\\cs113\\HuffmanTrees\\"+ filename + "Raw.txt";
		
		try {
			TextFileGenerator.makeCleanFile(URL, inputFilePath);// src/edu/miracosta/cs113/HuffmanTrees/output.txt
		} catch (IOException e) {
			System.exit(0);
			e.printStackTrace();
		}
		
		double fileBits = TextFileGenerator.getNumChars(inputFilePath);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		
		int nextChar = -1;
		String all = "";
		do {
			try {
				nextChar = in.read();
				all += (char) nextChar;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}while(nextChar != -1);
		
		HuffmanTree huff = new HuffmanTree(all);
		
		String encoded = huff.encode(all);
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(outputFilePath));
			out.write(encoded);
			out.close();
			out = new BufferedWriter(new FileWriter(outputFilePathD));
			out.write(huff.decode(encoded));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double compressedFileBits = TextFileGenerator.getNumChars(outputFilePath);
		
		System.out.printf("\nPercentage of compression: %.2f%%", (fileBits / compressedFileBits * 100));
		
		
	}

}
