package edu.miracosta.cs113.homework3;

import java.util.LinkedList;
public class Polynomial {
	private LinkedList<Term> terms;
	
	public Polynomial() {
		terms = new LinkedList<Term>();
	}
	
	public Polynomial(Polynomial other) {
		terms = new LinkedList<Term>();
		
		for(int i = 0; i < other.getNumTerms(); i++) {
			terms.add(new Term(other.getTerm(i).getCoefficient(), other.getTerm(i).getExponent()));
		}
	}
	
	public int getNumTerms() {
		return terms.size();
	}
	
	
	public void addTerm(Term newTerm) {
		for(int i = 0; i < terms.size(); i++) {
			
			if(terms.get(i).getExponent() == newTerm.getExponent()) {
				
				terms.get(i).setCoefficient(terms.get(i).getCoefficient() + newTerm.getCoefficient());
				
				if(terms.get(i).getCoefficient() == 0) {
					terms.remove(i);
				}
				
				return;
			} else if(newTerm.compareTo(terms.get(i)) == 1) {
				
				terms.add(i, newTerm);
				return;
				
			} 
		}
		terms.add(newTerm);
		
	}
	
	public Term getTerm(int index) {
		return terms.get(index);
	}
	
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
	
	public void add(Polynomial other) {
		for(int i = 0; i < other.getNumTerms(); i++) {
			this.addTerm(other.getTerm(i));
		}
		
	}
	
	public void clear() {
		terms.clear();
	}
}
