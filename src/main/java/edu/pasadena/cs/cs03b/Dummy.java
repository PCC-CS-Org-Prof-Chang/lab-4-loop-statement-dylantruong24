package edu.pasadena.cs.cs03b;
import java.util.Scanner;
import java.lang.Math;

public class Dummy {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);

		//PROBLEM 1
		double interestRate, annualCost, tenYrAnnualCost, tenYrFourCost;

		System.out.println("---------------------------");
		System.out.print("Enter the annual tuition cost of the college: ");
		annualCost = scanner.nextDouble();
		System.out.print("Enter the annual interest rate (in decimal form, i.e. 5% = 0.05): ");
		interestRate = scanner.nextDouble();
		System.out.println("Single year tuition in 10 years: " + calculateTenYrAnnual(annualCost, interestRate));
		System.out.println("Four-year tuition in 10 years: " + calculateTenYrFour(annualCost, interestRate));
		System.out.println("---------------------------");

		//PROBLEM 2
		int baseSelect, mVal;
		String valA = "", valB = "";

		System.out.println("---------------------------");
		System.out.print("Select the addition type: (1)Base-10 | (2)Base-2 | (3)Base-16: ");
		baseSelect = scanner.nextInt();
		System.out.print("Enter an m value for number of digits: ");
		mVal = scanner.nextInt();
		System.out.print("Enter the first value (include 0x if 16-base): ");
		valA = scanner.next();
		System.out.print("Enter the second value (include 0x if 16-base): ");
		valB = scanner.next();
		if (baseSelect == 1) {
			System.out.println("The sum is " + calculateBase10(mVal, valA, valB));
		}
		else if (baseSelect == 2) {
			System.out.println("The sum is " + calculateBase2(mVal, valA, valB));
		}
		else if (baseSelect == 3) {
			System.out.println("The sum is 0x" + calculateBase16(mVal, trim16Base(valA), trim16Base(valB)));
		}
		else {
			System.out.println("ERROR. Invalid choice.");
			return;
		}
		System.out.println("---------------------------");
	}

	public static double calculateTenYrAnnual(double annualCost, double interestRate) {
		return ((double) (annualCost * Math.pow((1 + (interestRate)), 10)* 100)/100.0);
	}

	public static double calculateTenYrFour(double annualCost, double interestRate) {
		double totalCost = 0;
		for (int i = 0; i < 4; i++) {
			totalCost += ((double) (annualCost * Math.pow((1 + (interestRate)), 10+i)* 100)/100.0);
		}
		return totalCost;
	}

	public static String calculateBase10(int mVal, String a, String b) {
		int carry = 0;
		int[] cValues = new int[mVal+1];
		StringBuilder sum = new StringBuilder();

		for (int i = 0; i <= mVal-1; i++) {
			int aDigit = Character.getNumericValue(a.charAt(mVal-1-i));
			int bDigit = Character.getNumericValue(b.charAt(mVal-1-i));

			int c = aDigit + bDigit + carry;

			if (c >= 10) {
				carry = 1;
				cValues[i] = c - 10;
			}
			else {
				carry = 0;
				cValues[i] = c;
			}
		}
		cValues[mVal] = carry;

		if (carry == 0) {
			for (int i = 0; i < mVal; i++) {
				sum.insert(0, cValues[i]);
			}
		}
		else {
			for (int i = 0; i <= mVal; i++) {
				sum.insert(0, cValues[i]);
			}
		}
		return sum.toString();
	}

	public static String calculateBase2(int mVal, String a, String b) {
		int carry = 0;
		int[] cValues = new int[mVal+1];
		StringBuilder sum = new StringBuilder();

		for (int i = 0; i <= mVal-1; i++) {
			int aDigit = a.charAt(mVal-1-i) - '0';
			int bDigit = b.charAt(mVal-1-i) - '0';

			int c = aDigit + bDigit + carry;

			if (c == 3) {
				carry = 1;
				cValues[i] = 1;
			}
			else if (c == 2) {
				carry = 1;
				cValues[i] = 0;
			}
			else {
				carry = 0;
				cValues[i] = c;
			}
		}
		cValues[mVal] = carry;

		if (carry == 0) {
			for (int i = 0; i < mVal; i++) {
				sum.insert(0, cValues[i]);
			}
		}
		else {
			for (int i = 0; i <= mVal; i++) {
				sum.insert(0, cValues[i]);
			}
		}
		return sum.toString();
	}

	public static String calculateBase16(int mVal, String a, String b) {
		int carry = 0;
		String[] cValues = new String[mVal+1];
		StringBuilder sum = new StringBuilder();

		for (int i = 0; i <= mVal-1; i++) {
			int aDigit = Character.getNumericValue(a.charAt(mVal-1-i));
			int bDigit = Character.getNumericValue(b.charAt(mVal-1-i));

			int c = aDigit + bDigit + carry;

			if (c == 10) {
				carry = 0;
				cValues[i] = "A";
			}
			else if (c == 11) {
				carry = 0;
				cValues[i] = "B";
			}
			else if (c == 12) {
				carry = 0;
				cValues[i] = "C";
			}
			else if (c == 13) {
				carry = 0;
				cValues[i] = "D";
			}
			else if (c == 14) {
				carry = 0;
				cValues[i] = "E";
			}
			else if (c == 15) {
				carry = 0;
				cValues[i] = "F";
			}
			else if (c >= 16) {
				carry = 1;
				cValues[i] = Integer.toString(c - 16);
			}
			else {
				carry = 0;
				cValues[i] = Integer.toString(c);
			}
		}

		cValues[mVal] = Integer.toString(carry);

		if (carry == 0) {
			for (int i = 0; i < mVal; i++) {
				sum.insert(0, cValues[i]);
			}
		}
		else {
			for (int i = 0; i <= mVal; i++) {
				sum.insert(0, cValues[i]);
			}
		}
		return sum.toString();
	}

	public static String trim16Base(String string) {
		StringBuilder trimmedString = new StringBuilder(string);
		trimmedString.deleteCharAt(1);
		trimmedString.deleteCharAt(0);

		return trimmedString.toString();
	}
}
