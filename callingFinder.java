package calling;
//This program is to take two lists of names from .csv files.

//names are to be separated by last name and first name.
//first list should be complete list of all names
//second list should be a partial list of names

//there will be an option to select how data should be displayed
//last first, names not in second list
//more options to come

import java.util.Scanner;
import java.io.*;

public class callingFinder {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		// direct the madness
		// get fileNames
		String fileName1 = getFileName() + ".csv";
		String fileName2 = getFileName() + ".csv";

		// find the length of files
		int length1 = fileLength(fileName1);
		int length2 = fileLength(fileName2);

		// make place to put the files
		String[][] files = new String[3][];
		files[0] = new String[length1];
		files[1] = new String[length2];
		files[2] = new String[length1];

		// load files
		files[0] = getFileContents(fileName1, length1);
		files[1] = getFileContents(fileName2, length2);

		// prep file for processing
		// prepFiles(files);
		// once working removed sortLists and middleNameEliminator from main

		// sort files in alphabetical order by last name//need to fix process
		//sortList(files[0]);
		//sortList(files[1]);

		// remove middle names or alt names
		middleNameEliminator(files[0]);
		middleNameEliminator(files[1]);

		// find difference
		compareLists(files);
		// print list and save if desired
		printList(files[2]);
	}

	public static int fileLength(String fileName) throws FileNotFoundException {
		// get length of file
		int length = 0;
		java.io.File file = new File(fileName);// open file
		Scanner input = new Scanner(file);
		// read out the .csv into array and return
		String tmp;
		while (input.hasNext()) {// reads file in
			tmp = input.nextLine();
			length++;
		}
		input.close();
		return length;
	}

	public static String getFileName() {// will be called 3+ times
		// get file name and return

		System.out.print("Please specify file name: ");
		String fileName = input.next();

		return fileName;
	}

	public static String[] getFileContents(String fileName, int length) throws Exception {// called
																							// 2
																							// times
		java.io.File file = new java.io.File(fileName);// open file
		Scanner input = new Scanner(file);
		// read out the .csv into array and return
		int i = 0;
		String[] names = new String[length];
		while (input.hasNext()) {// reads file in
			names[i] = input.nextLine();
			i++;
		}
		input.close();
		return names;
	}

	public static void prepFiles(String[][] files) {
		// This function will direct the finding of the first and last names in
		// the submitted files
		// by first indexing the columns or in other words find which ones have
		// the headers first/last name or equivalent
		// then after indexing the columns it will extract the first name and
		// the last names from the files

		// index columns
		for (int i = 0; i < files.length - 1; i++) {
			//find and prep name
			nameFinder(files[i]);
			// sort alphabetically
			sortList(files[i]);
		}
	}

	private static void nameFinder(String... file) {
		// This will take the file extract the names from file
		// it will take the names and return them as a list (array)
		int n = 1;
		for (int i = 1; i < file.length; i++) {
			int[] indexes = commaSubStringFinder(file[i]);
			String tmp;
			if(file[i].substring(0,indexes[0]).trim() == "Calling")//if it is the calling sheet
				tmp = file[i].substring(indexes[0], indexes[1]).trim();
			else{//if it is the phone list
				tmp = file[i].substring(indexes[0], indexes[1]).trim() + " " + file[i].substring(0, indexes[1]).trim();
			}
				
			// desired column
			if (tmp.length() > 1) {// if populated
				tmp = middleNameRemover(tmp);
				if (n == 1) {// save categories
					file[0] = "Name";
				}
				file[n] = tmp;// save name
				n++;
			}
		}
		file[n] = null;
	}

	public static int[] commaSubStringFinder(String file) {
		// this will take a line and locate the separating character ,
		// add them to indexes (of the comma) and return values

		int[] indexes = new int[6];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			indexes[i] = file.indexOf(",", index + 1);
			index = indexes[i];
		}
		if (indexes[4] == -1)
			indexes[4] = file.length();
		indexes[5] = file.length();
		return indexes;
	}

	public static void sortList(String[] file) { // called 2+ times for list 1
													// & 2
		// A to z
		// sort lists and return // not sure about 1 or 2 cells currently set
		// for 1
		for (int check = 0; check < 2; check++) {
			for (int i = 0; i < file.length; i++) { // logic statement is
														// there contents
				// compare strings
				if ((file[i].compareTo(file[i + 1]) > 0)) { // returns a value
															// greater then 0 if
															// it needs swapped
					String swap = file[i];
					file[i] = file[i + 1];
					file[i + 1] = swap;
					if (i >= 2) {
						i -= 2;// move back one spot and check order
					}
				}
			}
		}
	}

	public static void middleNameEliminator(String... names) {
		String firstName;
		String lastName;
		int index1 = 0;
		int index2 = 0;
		// find the spaces
		for (int i = 0; i < names.length - 1; i++) {
			// System.out.println(names[i] + i + " " + names.length);
			index1 = names[i].indexOf(' ');
			index2 = names[i].lastIndexOf(' ');
			// if two spaces remove middle name
			if (index2 > index1) {
				firstName = names[i].substring(0, index1).trim();
				lastName = names[i].substring(index2).trim();
				names[i] = firstName + " " + lastName;
				// System.out.println(names[i]);
			}
		}
	}
	public static String middleNameRemover(String name) {//used for new method
		String firstName;
		String lastName;
		int index1 = 0;
		int index2 = 0;
		// find the spaces
			// System.out.println(names[i] + i + " " + names.length);
			index1 = name.indexOf(' ');
			index2 = name.lastIndexOf(' ');
			// if two spaces remove middle name
			if (index2 > index1) {
				firstName = name.substring(0, index1).trim();
				lastName = name.substring(index2).trim();
				name = firstName + " " + lastName;
				// System.out.println(names[i]);
			}
		return name;
	}

	public static void compareLists(String[][] files) {
		// compare contents of list1 to list 2
		// if name not found in list 2 write to list3
		int t = 0;
		boolean found = false;
		for (int i = 0; i <= files[0].length - 1; i++) {
			found = false;// set to false for new name
			for (int j = 0; j <= files[1].length - 1; j++) {
				// search list for name
				// if name not found after searching list
				// add to list 3
				if (files[0][i].equals(files[1][j])) {// search list2
					found = true;
				}
				if (t > 0) {// search list3
					for (int k = 0; k <= t - 1; k++) {
						if (files[0][i].equals(files[2][k])) {
							found = true;
						}
					}
				}
			}
			if (!found) {// if not found
				files[2][t] = files[0][i]; // add to list3
				t++;
			}
		}
	}

	public static void printList(String... names) throws FileNotFoundException {
		// take list and read out to screen
		int i = 0;
		while (i < names.length && names[i] != null) {
			// print to file
			System.out.println(names[i]);
			i++;
		}
		// offer to save to file
		System.out.print("Do you want to save to file (Y/N): ");
		String yesNo = input.next().trim();
		if (yesNo.trim().toLowerCase().charAt(0) == 'y') {
			saveToFile(names);
		}
	}

	public static void saveToFile(String... names) throws FileNotFoundException {// array
																					// list3
		// call getFileName
		String fileName = getFileName();
		// save to file
		java.io.File file = new java.io.File(fileName + ".csv");
		java.io.PrintWriter output = new java.io.PrintWriter(file);
		// write to file
		// for loop to write array to file format last, first
		int i = 0;
		while (i < names.length && names[i] != null) {
			// print to file
			output.println(names[i]);
			i++;
		}
		// close file
		output.close();
		System.out.println("Save Completed");
	}
}
