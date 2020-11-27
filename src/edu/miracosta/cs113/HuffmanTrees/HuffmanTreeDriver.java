package edu.miracosta.cs113.HuffmanTrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * HuffmanTreeDriver.java: a driver to compress data from a website
 *  
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
public class HuffmanTreeDriver {
	/*
     * ALGORITHM:
     * 
     * PROMPT user for URL and filename
     * 
     * Invoke makeCleanFIle with the url and path name
     * get bits in file by taking the number of chars in the file and multiplying it by 16.
     * read all the characters from the file and put it into a string
     * create a huffman tree from the string above
     * invoke encode from HuffmanTree class using the string
     * put the encoded string into a different file
     * decode the encoded and put it into a differnt file
     * get number of characters from the encoded file
     * 
     * Print compression percentage by taking the bits in raw file / compressed file bits
     *
     */
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//Prompt user for URL and file name
		System.out.print("Please input URL: ");
		String URL = input.nextLine();
		System.out.print("Please input output file path: ");
		String filename = input.nextLine();
		
		//All three diferent file names
		String outputFilePathD =  "src\\edu\\miracosta\\cs113\\HuffmanTrees\\"+ filename + "Decode.txt";
		String outputFilePath =  "src\\edu\\miracosta\\cs113\\HuffmanTrees\\"+ filename + "Encode.txt";
		String inputFilePath = "src\\edu\\miracosta\\cs113\\HuffmanTrees\\"+ filename + "Raw.txt";
		
		// open the url and put it into a file
		try {
			TextFileGenerator.makeCleanFile(URL, inputFilePath);
		} catch (IOException e) {
			System.exit(0);
			e.printStackTrace();
		}
		
		//Calculate bits from that file
		double fileBits =  8 * TextFileGenerator.getNumChars(inputFilePath);
		BufferedReader in = null;
		
		//Get a buffered reader for the file
		try {
			in = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		
		//Traverses each character in the file and appends it to a string object
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
		
		//Create huffman tree
		HuffmanTree huff = new HuffmanTree(all);
		
		//Encode the file
		String encoded = huff.encode(all);
		
		// Writes the encoded to a file and decodes the encoded and puts it into a file
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(outputFilePath));
			out.write(encoded);
			out.close();
			out = new BufferedWriter(new FileWriter(outputFilePathD));
			out.write(huff.decode(encoded));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Gets bits from file
		double compressedFileBits = TextFileGenerator.getNumChars(outputFilePath);
		
		//Prints the percentage of compression
		System.out.printf("\nPercentage of compression: %.2f", (fileBits / compressedFileBits));
		
		
	}

}
