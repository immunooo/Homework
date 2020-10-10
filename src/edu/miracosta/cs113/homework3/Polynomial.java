package edu.miracosta.cs113.homework3;
import java.util.LinkedList;

/**
 * Polynomial.java: a class responsible for holding a series of Term object.
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
public class Polynomial {
	private LinkedList<Term> terms;
	
	/**
	 * Default constructor creates a new linked list
	 * 
	 */
	public Polynomial() {
		terms = new LinkedList<Term>();
	}
	
	/**
	 * Copy constructor creates new Term objects with same values as another polynomial object
	 * 
	 */
	public Polynomial(Polynomial other) {
		terms = new LinkedList<Term>();
		
		for(int i = 0; i < other.getNumTerms(); i++) {
			terms.add(new Term(other.getTerm(i).getCoefficient(), other.getTerm(i).getExponent()));
		}
	}
	
	/**
	 * gets the size of the linked list
	 * 
	 * @return the amount of terms in the linked list
	 * 
	 */
	public int getNumTerms() {
		return terms.size();
	}
	
	/**
	 * adds a term to the linked list
	 * 
	 * @param newTerm the term being added to the linked list
	 * 
	 */
	public void addTerm(Term newTerm) {
		for(int i = 0; i < terms.size(); i++) {
			
			if(terms.get(i).getExponent() == newTerm.getExponent()) {
				
				terms.get(i).setCoefficient(terms.get(i).getCoefficient() + newTerm.getCoefficient());
				
				if(terms.get(i).getCoefficient() == 0) {
					terms.remove(terms.get(i));
				}
				
				return;
			} else if(newTerm.compareTo(terms.get(i)) == 1) {
				
				terms.add(i, newTerm);
				return;
				
			} 
		}
		terms.add(newTerm);
		
	}
	
	/**
	 * adds a term to the linked list from a string
	 * 
	 * @param newTerm the term being added to the linked list
	 * 
	 */
	public void addTerm(String newTerm) {
		addTerm(new Term(newTerm));
	}
	
	
	/**
	 * gets the term at the index value
	 * 
	 * @param the index in which to return Term object
	 * 
	 * @return the term object of the target index if out of bounds, returns null
	 * 
	 */
	public Term getTerm(int index) {
		if(index >= getNumTerms()) {
			return null;
		}
		return terms.get(index);
	}
	
	/**
	 * Full constructor
	 * 
	 * @param coefficient sets the coefficient of the term to this value
	 * @param exponent sets the exponent of the term to this value
	 * 
	 */
	public String toString() {
		String poly = "";
		if(getNumTerms() == 0) {
			return "0";
		}
		
		for(int i = 0; i < terms.size(); i++) {
			String termTemp = terms.get(i).toString();
			
			if(i == 0 && terms.get(i).getCoefficient() > 0) {
				poly += termTemp.substring(1, termTemp.length());
				continue;
			} 
			poly += termTemp;
		}
		return poly;
	}
	
	/**
	 * adds two polynomials together
	 * 
	 * @param other the polynomial being added to this object.
	 * 
	 */
	public void add(Polynomial other) {
		for(int i = 0; i < other.getNumTerms(); i++) {
			this.addTerm(other.getTerm(i));
		}
		
	}
	
	/**
	 * clears all values in the linked list
	 * 
	 */
	public void clear() {
		terms.clear();
	}
}
