package com.sortcompare;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;
/*
Ivan Craddock
CSCD 300 Armstrong
Assignment 3

*This program will make 12 Arrays of 4 LinkedLists each. Each of 4 sorting algorithms 
will be tested on a an array of LinkedLists that are either 500, 1000, 5000, or 10000
Nodes in length, and the LinkedLists will either be randomized, reversed, or pre-sorted.
Information on the number of opperations, data assignments, and total system time (in seconds
will be exported to the text file "sort_results.txt" and the CSV file "values.csv".

Export to CSV file implemented for extra credit.

*/
import java.util.Random;

public class SortTester {
	static DecimalFormat df = new DecimalFormat("#.#########");

	/**
	 *
	 * @param String[]
	 *            Accepts an array of strings from the command line
	 * @throws Exception
	 *             Throws an exception for file I/O errors
	 */
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.#########");
		try {
			FileWriter outFile = new FileWriter("sort_results.txt");
			PrintWriter out = new PrintWriter(outFile);
			FileWriter ccc = new FileWriter("values.csv");
			PrintWriter comma = new PrintWriter(ccc);

			LinkedList[] selection_ascending = ascendingList();
			LinkedList[] selection_descending = descendingList();
			LinkedList[] selection_random = randomList();

			LinkedList[] bubble_ascending = ascendingList();
			LinkedList[] bubble_descending = descendingList();
			LinkedList[] bubble_random = randomList();

			LinkedList[] insertion_ascending = ascendingList();
			LinkedList[] insertion_descending = descendingList();
			LinkedList[] insertion_random = randomList();

			LinkedList[] array_ascending = ascendingList();
			LinkedList[] array_descending = descendingList();
			LinkedList[] array_random = randomList();

			////////////////////////////////////////////////////////
			out.println("*********************");
			out.println("***SELECTION SORTS***");
			comma.println("SELECTION SORTS");
			out.println("---------------------");
			out.println("----Descending-------");
			comma.println("DESCENDING");
			nanoSelectionRun(selection_descending, out, comma);
			out.println("----Ascending--------");
			comma.println("ASCENDING");
			nanoSelectionRun(selection_ascending, out, comma);
			out.println("----Randomized--------");
			comma.println("RANDOMIZED");
			nanoSelectionRun(selection_random, out, comma);

			out.println("*********************");
			out.println("***BUBBLE SORTS******");
			comma.println("BUBBLE SORTS");
			out.println("---------------------");
			out.println("----Descending-------");
			comma.println("DESCENDING");
			nanoBubbleRun(bubble_descending, out, comma);
			out.println("----Ascending-------");
			comma.println("ASCENDING");
			nanoBubbleRun(bubble_ascending, out, comma);
			out.println("----Randomized-------");
			comma.println("RANDOMIZED");
			nanoBubbleRun(bubble_random, out, comma);

			out.println("*********************");
			out.println("***ARRAY SORTS*******");
			comma.println("ARRAY SORTS");
			out.println("---------------------");
			out.println("----Descending-------");
			comma.println("DESCENDING");
			nanoArrayRun(array_descending, out, comma);
			out.println("----Ascending-------");
			comma.println("ASCENDING");
			nanoArrayRun(array_ascending, out, comma);
			out.println("----Randomized-------");
			comma.println("RANDOMIZED");
			nanoArrayRun(array_random, out, comma);

			out.println("*********************");
			out.println("***INSERTION SORTS***");
			comma.println("INSERTION SORTS");
			out.println("---------------------");
			out.println("----Descending-------");
			comma.println("DESCENDING");
			nanoInsertionRun(insertion_descending, out, comma);
			out.println("----Ascending--------");
			comma.println("ASCENDING");
			nanoInsertionRun(insertion_ascending, out, comma);
			out.println("----Randomized-------");
			comma.println("RANDOMIZED");
			nanoInsertionRun(insertion_random, out, comma);

			out.close();
			outFile.close();
			ccc.close();
			comma.close();
		} catch (Exception e) {
			System.out.println("An error has occured");
		}
	}

	// public static void timePrinter(LinkedList[] ara){
	// long i = System.nanoTime();
	// System.out.print("500: ");
	// System.out.println(i - System.nanoTime());
	// i = System.nanoTime();
	// System.out.print("1000: ");
	// System.out.println(i - System.nanoTime());
	// i = System.nanoTime();
	// System.out.print("5000: ");
	// System.out.println(i - System.nanoTime());
	// i = System.nanoTime();
	// System.out.print("10000: ");
	// System.out.println(ara[3].dataAssign);

	/**
	 * Performs an insertion sort on each element in an array of LinkedLists,
	 * exporting the total opperations, total data assignments, and total system
	 * time to separate text and CSV files.
	 *
	 * @param LinkedList[]
	 * @param PrintWriter
	 * @param PrintWriter
	 */
	public static void nanoInsertionRun(LinkedList[] ara, PrintWriter out, PrintWriter comma) {
		for (int i = 0; i < ara.length; i++) {
			long l = System.nanoTime();
			ara[i].insertionSort();
			double j = (System.nanoTime() - l) / 1000000000.0;
			out.println("Size " + ara[i].getSize() + ": Time to completion = " + df.format(j)
					+ ": Total Data Assignments = " + ara[i].dataAssign + ": Total Opperations = " + ara[i].total());
			comma.println("" + ara[i].getSize() + "," + df.format(j) + "," + ara[i].dataAssign + "," + ara[i].total());
		}
	}

	/**
	 * Performs an selection sort on each element in an array of LinkedLists,
	 * exporting the total opperations, total data assignments, and total system
	 * time to separate text and CSV files.
	 *
	 * @param LinkedList[]
	 * @param PrintWriter
	 * @param PrintWriter
	 */
	public static void nanoSelectionRun(LinkedList[] ara, PrintWriter out, PrintWriter comma) {
		for (int i = 0; i < ara.length; i++) {
			long l = System.nanoTime();
			ara[i].selectionSort();
			double j = (System.nanoTime() - l) / 1000000000.0;
			out.println("Size " + ara[i].getSize() + ": Time to completion = " + df.format(j)
					+ ": Total Data Assignments = " + ara[i].dataAssign + ": Total Opperations = " + ara[i].total());
			comma.println("" + ara[i].getSize() + "," + df.format(j) + "," + ara[i].dataAssign + "," + ara[i].total());
		}
	}

	/**
	 * Performs an bubble sort on each element in an array of LinkedLists,
	 * exporting the total opperations, total data assignments, and total system
	 * time to separate text and CSV files.
	 *
	 * @param LinkedList[]
	 * @param PrintWriter
	 * @param PrintWriter
	 */
	public static void nanoBubbleRun(LinkedList[] ara, PrintWriter out, PrintWriter comma) {
		for (int i = 0; i < ara.length; i++) {
			long l = System.nanoTime();
			ara[i].bubbleSort();
			double j = (System.nanoTime() - l) / 1000000000.0;
			out.println("Size " + ara[i].getSize() + ": Time to completion = " + df.format(j)
					+ ": Total Data Assignments = " + ara[i].dataAssign + ": Total Opperations = " + ara[i].total());
			comma.println("" + ara[i].getSize() + "," + df.format(j) + "," + ara[i].dataAssign + "," + ara[i].total());
		}
	}

	/**
	 * Performs an array-type insertion sort on each element in an array of
	 * LinkedLists, exporting the totalopperations, total data assignments, and
	 * total system time to separate text and CSV files.
	 *
	 * @param LinkedList[]
	 * @param PrintWriter
	 * @param PrintWriter
	 */
	public static void nanoArrayRun(LinkedList[] ara, PrintWriter out, PrintWriter comma) {
		for (int i = 0; i < ara.length; i++) {
			long l = System.nanoTime();
			ara[i].arraySort();
			double j = (System.nanoTime() - l) / 1000000000.0;
			out.println("Size " + ara[i].getSize() + ": Time to completion = " + df.format(j)
					+ ": Total Data Assignments = " + ara[i].dataAssign + ": Total Opperations = " + ara[i].total());
			comma.println("" + ara[i].getSize() + "," + df.format(j) + "," + ara[i].dataAssign + "," + ara[i].total());
		}
	}

	// public static void timePrinter(LinkedList[] ara){
	// System.out.print("500: ");
	// System.out.println(ara[0].dataAssign);
	// System.out.print("1000: ");
	// System.out.println(ara[1].dataAssign);
	// System.out.print("5000: ");
	// System.out.println(ara[2].dataAssign);
	// System.out.print("10000: ");
	// System.out.println(ara[3].dataAssign);
	//
	// }

	// public static void selectionRun

	/**
	 * Creates an array of 4 LinkedLists that are 500, 1000, 5000, and 10000
	 * elements in length. Each list is in ascending order from 1 to its maximum
	 * value.
	 *
	 * @return LinkedList[]
	 */
	public static LinkedList[] ascendingList() {

		LinkedList[] prime = new LinkedList[4];
		LinkedList load500 = new LinkedList();
		LinkedList load1000 = new LinkedList();
		LinkedList load5000 = new LinkedList();
		LinkedList load10000 = new LinkedList();

		for (int i = 0; i < 500; i++)
			load500.add(BigInteger.valueOf(i));
		prime[0] = load500;
		///////////////////
		for (int i = 0; i < 1000; i++)
			load1000.add(BigInteger.valueOf(i));
		prime[1] = load1000;
		///////////////////
		for (int i = 0; i < 5000; i++)
			load5000.add(BigInteger.valueOf(i));
		prime[2] = load5000;
		///////////////////
		for (int i = 0; i < 10000; i++)
			load10000.add(BigInteger.valueOf(i));
		prime[3] = load10000;

		return prime;

	}

	/**
	 * Creates an array of 4 LinkedLists that are 500, 1000, 5000, and 10000
	 * elements in length. Each list is in descending order from its maximum
	 * value to 1.
	 *
	 * @return LinkedList[]
	 */
	public static LinkedList[] descendingList() {

		LinkedList[] prime = new LinkedList[4];
		LinkedList load500 = new LinkedList();
		LinkedList load1000 = new LinkedList();
		LinkedList load5000 = new LinkedList();
		LinkedList load10000 = new LinkedList();

		for (int i = 500; i > 0; i--)
			load500.add(BigInteger.valueOf(i));
		prime[0] = load500;
		///////////////////
		for (int i = 1000; i > 0; i--)
			load1000.add(BigInteger.valueOf(i));
		prime[1] = load1000;
		///////////////////
		for (int i = 5000; i > 0; i--)
			load5000.add(BigInteger.valueOf(i));
		prime[2] = load5000;
		///////////////////
		for (int i = 10000; i > 0; i--)
			load10000.add(BigInteger.valueOf(i));
		prime[3] = load10000;

		return prime;

	}

	/**
	 * Creates an array of 4 LinkedLists that are 500, 1000, 5000, and 10000
	 * elements in length. Each element in each list is assigned a random number
	 * from a seed that is identical each time this method is run.
	 *
	 * @return LinkedList[]
	 */
	public static LinkedList[] randomList() {

		LinkedList[] prime = new LinkedList[4];
		LinkedList load500 = new LinkedList();
		LinkedList load1000 = new LinkedList();
		LinkedList load5000 = new LinkedList();
		LinkedList load10000 = new LinkedList();
		Random roll;

		for (int i = 500; i > 0; i--) {
			roll = new Random(i);
			load500.add(BigInteger.valueOf(-1 * roll.nextInt()));
		}
		prime[0] = load500;
		///////////////////
		for (int i = 1000; i > 0; i--) {
			roll = new Random(i);
			load1000.add(BigInteger.valueOf(-1 * roll.nextInt()));
		}
		prime[1] = load1000;
		///////////////////
		for (int i = 5000; i > 0; i--) {
			roll = new Random(i);
			load5000.add(BigInteger.valueOf(-1 * roll.nextInt()));
		}
		prime[2] = load5000;
		///////////////////
		for (int i = 10000; i > 0; i--) {
			roll = new Random(i);
			load10000.add(BigInteger.valueOf(-1 * roll.nextInt()));
		}
		prime[3] = load10000;

		return prime;

	}

}