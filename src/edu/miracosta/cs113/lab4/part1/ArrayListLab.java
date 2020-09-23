package edu.miracosta.cs113.lab4.part1;
import java.util.ArrayList;
/**
 * ArrayListLab.java: a program that manipulates values in an arraylist.
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
public class ArrayListLab {
	/**
	 * Replaces every element of oldItem in the arraylist with newItem
	 * 
	 * @param aList  the given array list that is manipulated.
	 * @param oldItem  the given string that is replaced by newItem.
	 * @param newItem  the given string that replaces every instance of oldItem.
	 * 
	 */
	public static void replace(ArrayList<String> aList, String oldItem, String newItem) {
		int index = 0;
		while(index != -1){
			index = aList.indexOf(oldItem);
			if(index != -1) {
				aList.remove(index);
				aList.add(index, newItem);
			}
		}
	}
	
	/**
	 * Deletes the first occurence of target in the arraylist.
	 * 
	 * @param aList  the given array list that is manipulated.
	 * @param target  the given string that is deleted.
	 * 
	 */
	public static void delete(ArrayList<String> aList, String target) {
		aList.remove(target);
	}
}
