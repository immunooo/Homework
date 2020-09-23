package edu.miracosta.cs113.lab4.part2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * DirectoryTest.java: Class that tests the methods inside of the Directory.java class.
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @version 1.0
 * 
 */
class DirectoryTest {
	private static Directory dir = new Directory();
	@Test
	void testAdd() {
		//Tests adding new Names and phone numbers
		assertEquals(null, dir.addOrChangeEntry("Jake", "342-523-1234"));
		assertEquals(null, dir.addOrChangeEntry("Luke", "372-643-12214"));
		assertEquals(null, dir.addOrChangeEntry("Joseph", "760-867-5309"));
		
	}
	
	
	@Test
	void testReplace() {
		//Tests the ability of the method to replace the number of an existing 
		assertEquals("372-643-12214", dir.addOrChangeEntry("Luke", "760-123-4567"));//Changes number
		assertEquals("760-123-4567", dir.getEntry(1).getNumber());//Checks to see if number changed
	}
	
	@Test
	void testRemove() {
		//Tests the removeEntry method
		assertEquals(new DirectoryEntry("Joseph", "760-867-5309"), dir.removeEntry("Joseph"));//Tests the removal of an entry
		assertEquals(null, dir.removeEntry("Hunter"));//Tests if name doesn't exist in the directory
	}
		
}
