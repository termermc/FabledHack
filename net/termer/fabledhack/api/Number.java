package net.termer.fabledhack.api;

/**
 * <p> A class for storing and converting ints and doubles.</p>
 * <p> Much like the "number" object in JavaScript.</p>
 * @author _termer_
 */

public class Number {
	int intNum;
	double doubleNum = Double.NaN;
	
	public Number(int num) {
		
	}
	public Number(double num) {
		doubleNum = num;
	}
	
	public int getNumberInt() {
		int done;
		if(Double.isNaN(doubleNum)) {
			done = intNum;
		} else {
			done = Integer.parseInt(Double.toString(doubleNum).split(".")[0]);
		}
		return done;
	}
	public double getNumberDouble() {
		double done;
		if(Double.isNaN(doubleNum)) {
			done = Double.parseDouble(Integer.toString(intNum)+".0");
		} else {
			done = doubleNum;
		}
		return done;
	}
	
	public void plus(int amount) {
		if(Double.isNaN(doubleNum)) {
			intNum+=amount;
		} else {
			doubleNum+=amount;
		}
	}
	public void plus(double amount) {
		if(Double.isNaN(doubleNum)) {
			intNum+=amount;
		} else {
			doubleNum+=amount;
		}
	}
	
	public void minus(int amount) {
		if(Double.isNaN(doubleNum)) {
			intNum-=amount;
		} else {
			doubleNum-=amount;
		}
	}
	public void minus(double amount) {
		if(Double.isNaN(doubleNum)) {
			intNum-=amount;
		} else {
			doubleNum-=amount;
		}
	}
	
	public void times(int amount) {
		if(Double.isNaN(doubleNum)) {
			intNum*=amount;
		} else {
			doubleNum*=amount;
		}
	}
	public void times(double amount) {
		if(Double.isNaN(doubleNum)) {
			intNum*=amount;
		} else {
			doubleNum*=amount;
		}
	}
	
	public void setNumber(int number) {
		if(Double.isNaN(doubleNum)) {
			intNum = number;
		} else {
			doubleNum = Double.NaN;
			intNum = number;
		}
	}
	public void setNumber(double number) {
		if(Double.isNaN(doubleNum)) {
			intNum = 0;
			doubleNum = number;
		} else {
			doubleNum = number;
		}
	}
	
	public String toString() {
		String s = "Error";
		if(doubleNum != Double.NaN) {
			s = Double.toString(doubleNum);
		} else {
			s = Integer.toString(intNum);
		}
		return s;
	}
	
	public static Number parseNumber(String n) {
		Number tmp = null;
		try {
			tmp = new Number(Integer.parseInt(n));
		} catch(Exception e) {}
		try {
			tmp = new Number(Double.parseDouble(n));
		} catch(Exception e) {}
		if(tmp == null) {
			throw new NumberFormatException("For input string: \""+n+"\"");
		}
		return tmp;
	}
}
