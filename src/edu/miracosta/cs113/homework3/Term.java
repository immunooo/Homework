package edu.miracosta.cs113.homework3;

/**
 * TermTest.java: a class responsible for holding coefficient and exponent of a term of a polynomial.
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @Version 1.0
 * 
 */
public class Term implements Comparable{
	
	private int coefficient;
	private int exponent;
	
	/**
	 * Default constructor sets coefficient and exponent to the value 1.
	 * 
	 */
	public Term() {
		coefficient = 1;
		exponent = 1;
		
	}
	
	/**
	 * Full constructor
	 * 
	 * @param coefficient sets the coefficient of the term to this value
	 * @param exponent sets the exponent of the term to this value
	 * 
	 */
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param other is the Term that gets copied
	 * 
	 */
	public Term(Term other) {
		this.coefficient = other.getCoefficient();
		this.exponent = other.getExponent();
	}
	
	/**
	 * String constructor
	 * 
	 * @param term is a string that is converted to a term object
	 * 
	 */
	public Term(String term) {
		
		//Removes spaces in string
		term = term.replaceAll("\\s", "");
		
		String termCoefficient, termExponent;
		
		
		if(term.contains("x")) {
			
			//gets coefficient
			termCoefficient = term.substring(0, term.indexOf("x"));
			
			//Determines what to do with a - sign is the only thing in the coefficient
			if(termCoefficient.equals("-")) {
				termCoefficient = "-1";
			}
			
			//Removes the + at beginning of coefficient
			if(term.charAt(0) == '+') {
				termCoefficient = termCoefficient.substring(1);
			}
			
			//Determines whether nothing is infront of the coefficient
			if(termCoefficient.equals("")) {
				termCoefficient = "1";
			}
			
			//Determines what to do with exponent
			if(term.contains("^")) {
				termExponent = term.substring(term.indexOf("^") + 1, term.length());
			} else {
				termExponent = "1";
			}
			
		} else {
			termCoefficient = term;
			termExponent = "0";
		}
		
		try {
			//Parses the coefficient and exponent
			coefficient = Integer.parseInt(termCoefficient);
			exponent = Integer.parseInt(termExponent);
		} catch (NumberFormatException e){
			//if the parse errors set it to a default values
			coefficient = 0;
			exponent = 0;
		}
	
		
	}
	
	/**
	 * get coefficient method
	 * 
	 * @return the coefficient value
	 * 
	 */
	public int getCoefficient() {
		return coefficient;
	}
	
	/**
	 * set exponent method
	 * 
	 * @param coefficient sets the coefficient of the term to this value
	 * 
	 */
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	
	/**
	 * get exponent method
	 * 
	 * @return the exponent value
	 * 
	 */
	public int getExponent() {
		return exponent;
	}
	
	/**
	 * set exponent method
	 * 
	 * @param exponent sets the exponent of the term to this value
	 * 
	 */
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	
	/**
	 * set all method
	 * 
	 * @param coefficient sets the coefficient of the term to this value
	 * @param exponent sets the exponent of the term to this value
	 * 
	 */
	public void setAll(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	/**
	 * compares the coefficient and exponent of this object and another
	 * 
	 * @param other is the object this is comparing to
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		Term temp = (Term) other;
		return (temp.getCoefficient() == coefficient) && (temp.getExponent() == exponent);
		
	}
	
	/**
	 * compares this object to another and returns a numerical value based on equality.
	 * 
	 * @param other is the object this is comparing to
	 * 
	 */
	@Override
	public int compareTo(Object other) {
		Term temp =(Term) other;
		
		if(temp.getExponent() > exponent) {
			return -1;
		} else if(temp.getExponent() < exponent ) {
			return 1;
		} 
		
		if(temp.getCoefficient() > coefficient) {
			return -1;
		} else if(temp.getCoefficient() < coefficient) {
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * returns a clone of this object.
	 * 
	 */
	@Override
	public Object clone() {
		return new Term(coefficient, exponent);
	}
	
	/**
	 * converts the exponent and coefficient to appropriate term form.
	 * 
	 * @return a string of the term object
	 * 
	 */
	@Override
	public String toString() {
		String term = "";
		if(coefficient != 0) {
			if(Math.abs(coefficient) != 1) {
				if(coefficient > 0) {
					term += "+";
				}
				term += coefficient;
			} else {
				if(coefficient > 0) {
					term += "+";
				} else {
					term += "-";
				}
				if(exponent == 0) {
					term += "1";
				}
			}
			
			if(exponent == 1) {
				term += "x";
			} else if(exponent != 0) {
				term += "x^" + exponent;
			}
			
		}
		return term;
	}
	
	
}
