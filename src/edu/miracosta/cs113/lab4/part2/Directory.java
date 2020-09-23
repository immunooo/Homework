package edu.miracosta.cs113.lab4.part2;

import java.util.ArrayList;
/**
 * Directory.java: Base class for an arraylist of DirectoryEntry objects.
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @version 1.0
 * 
 */

public class Directory {
	
	private ArrayList<DirectoryEntry> theDirectory = new ArrayList<DirectoryEntry>();
	
	/**
	 * Add an entry to theDirectory or change an existing entry.
	 * 
	 * @param aName The name of the person being added or changed.
	 * @param newNumber The new number to be assigned
	 * 
	 * @return The old number, or if a new entry, null.
	 */
	public String addOrChangeEntry(String aName, String newNumber) {
		DirectoryEntry dE;
		
		int index = theDirectory.indexOf(new DirectoryEntry(aName, ""));
		
		if(index != -1) {
			//Changes number of name in directory
			dE = theDirectory.get(index);
			String oldNumber = dE.getNumber();
			dE.setNumber(newNumber);
			return oldNumber;
		} else {
			theDirectory.add(new DirectoryEntry(aName, newNumber));
			return null;
		}
			
	}
	
	/**
	 * Remove an entry.
	 * 
	 * @param aName The name of the person being removed
	 * 
	 * @return The entry removed, or null if there is no entry for aName.
	 */
	public DirectoryEntry removeEntry(String aName) {
		DirectoryEntry dE;
		
		//Finds the index of the name
		int index = theDirectory.indexOf(new DirectoryEntry(aName, "")); 
		
		if(index != -1) {
			dE = theDirectory.get(index);
			theDirectory.remove(index);
		} else {
			dE = null;
		}
		
		return dE;
	}
	
	/**
	 * Get an entry from the directory
	 * 
	 * @param index The index in which to get the entry from
	 * 
	 * @return The entry, if index is bigger than the size: null
	 */
	public DirectoryEntry getEntry(int index) {
		if(index > theDirectory.size()) {
			return null;
		}
		return theDirectory.get(index);
	}
	
}
