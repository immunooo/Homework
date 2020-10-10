package edu.miracosta.cs113.homework3;




//Just need comments

public class Term implements Comparable{
	
	private int coefficient;
	private int exponent;
	
	//Default constructor
	public Term() {
		coefficient = 1;
		exponent = 1;
		
	}
	
	//Full Constructor
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	//Copy Constructor
	public Term(Term other) {
		this.coefficient = other.getCoefficient();
		this.exponent = other.getExponent();
	}
	
	//String Constructor
	public Term(String term) {
		if(term.length() == 0) {
			coefficient = 0;
			exponent = 0;
		}
		if(term.contains("x^")) {
			
			String termCoeficient = term.substring(0, term.indexOf("x"));
			String termExponent = term.substring(term.indexOf("^") + 1, term.length());
			
			try {
				coefficient = Integer.parseInt(termCoeficient);
			} catch(NumberFormatException e) {
				if(term.indexOf("+") == -1) {
					coefficient = -1;
				} else {
					coefficient = 1;
				}
			}
			
			try {
				exponent = Integer.parseInt(termExponent);
			} catch(NumberFormatException e) {
				exponent = 1;
			}
			
		} else if(term.contains("x")) {
			exponent = 1;
			String termCoeficient = term.substring(0, term.indexOf("x"));
			
			try {
				coefficient = Integer.parseInt(termCoeficient);
			} catch(NumberFormatException e) {
				if(term.indexOf("+") == 0) {
					coefficient = 1;
				} else {
					coefficient = -1;
				}
			}
		} else {
			exponent = 0;
			try {
				coefficient = Integer.parseInt(term);
			} catch(NumberFormatException e) {
				coefficient = 0;
			}
			
		}
		
	}
	
	@Override
	public boolean equals(Object other) {
		Term temp = (Term) other;
		return (temp.getCoefficient() == coefficient) && (temp.getExponent() == exponent);
		
	}
	
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
	
	@Override
	public Object clone() {
		return new Term(coefficient, exponent);
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	
	public void setAll(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
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
