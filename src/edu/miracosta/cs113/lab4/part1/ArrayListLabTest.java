package edu.miracosta.cs113.lab4.part1;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
/**
 * ArrayListLab.java: a program that tests the methods in ArrayListLab.java
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
class ArrayListLabTest {
	ArrayList<String> fruit = new ArrayList<>();
	
	//Makes sure that all entries of apples are replaced with grapes
	@Test
	void testReplace() {
		fruit.add("Apples");
		fruit.add("Apples");
		fruit.add("Apples");
		fruit.add("Apples");
		fruit.add("Apples");
		fruit.add("Pears");
		fruit.add("Apples");
		fruit.add("Apples");
		ArrayListLab.replace(fruit, "Apples", "Grapes");
		for(int i = 0; i < fruit.size(); i++) {
			if(fruit.get(i).equalsIgnoreCase("Pears")) {
				assertEquals("Pears", fruit.get(i));
			} else {
				assertEquals("Grapes", fruit.get(i));
			}
		}
	}
	
	//Removes the first entry of pears
	@Test
	void testRemove() {
		ArrayListLab.delete(fruit, "Pears");
		for(int i = 0; i < fruit.size(); i++) {
			assertEquals("Grapes", fruit.get(i));
		}
	}
	
	//Makes sure it doesnt replace if the old variable doesnt exist in the list
	@Test
	void testReplace2() {
		ArrayListLab.replace(fruit, "Apples", "Grapes");
		for(int i = 0; i < fruit.size(); i++) {
			assertEquals("Grapes", fruit.get(i));
		}
	}
}
