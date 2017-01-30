/**
 * 
 */
package calling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author mattafire
 *
 *  This will be used for file management
 *
 */

public class MyFile {
	private static Scanner input = new Scanner(System.in);
	private String fileName = "noName";
	private int fileLength = 0;
	String[] file = new String[fileLength];
	
	public MyFile(){
		
	}
	public MyFile(int num){
		loadFileName();
		try {
			loadFileLength();
			loadFile();
		} catch (FileNotFoundException e) {
			System.out.println("File Error");
		}
		
	}
	public MyFile(String[] fIn, int length){
		this.fileLength = length;
		fileSizeChanger();
		for(int i = 0; i < fIn.length; i++){
			file[i] = fIn[i];
		}
	}
	public MyFile(String name){
		this.fileName = name;
		try {
			loadFileLength();
			loadFile();
		} catch (FileNotFoundException e) {
			System.out.println("File Error");
		}
		
	}
	
	void fileSizeChanger(){
		String[] tmp = new String[this.fileLength];
		this.file = tmp;
	}
	
	void loadFileName(){
		System.out.print("Please enter a file name: ");
		this.fileName = input.next();
		if(!(fileName.substring((fileName.length() - 4)).equals(".csv"))){
			//System.out.println(fileName.substring((fileName.length() - 4)));
			//System.out.println(fileName + fileName.length());
			this.fileName = this.fileName + ".csv"; // keep this
			//System.out.println(fileName);
		}
	}
	void loadFileLength() throws FileNotFoundException{
		int length = 0;
		java.io.File file = new File(this.fileName);// open file
		Scanner inputFile = new Scanner(file);
		// read out the .csv into array and return
		@SuppressWarnings("unused")
		String tmp;
		while (inputFile.hasNext()) {// reads file in
			tmp = inputFile.nextLine();
			length++;
		}
		inputFile.close();
		this.fileLength = length;
		fileSizeChanger();
	}
	void loadFile() throws FileNotFoundException{
		java.io.File file = new java.io.File(fileName);// open file
		Scanner inputFile = new Scanner(file);
		// read out the .csv into array and return
		int i = 0;
		String[] load = new String[this.fileLength];
		while (inputFile.hasNext()) {// reads file in
			load[i] = inputFile.nextLine();
			i++;
		}
		inputFile.close();
		this.file = load;
	}
	void loadFile(String[] in){
		setFileLength(in.length);
		String[] load = new String[in.length];
		for(int i = 0; i < in.length; i++){
			load[i] = in[i];
		}
		this.file = load;
	}
	void setFileName(String fileName){
		this.fileName = fileName;
		if(this.fileName.length() > 0)
		if(!(fileName.substring((fileName.length() - 4)).equals(".csv"))){
			this.fileName = this.fileName + ".csv"; 
		}
	}
	void setFileLength(int in){
		this.fileLength = in;
		fileSizeChanger();
	}
	int load(){
		try {
		loadFileLength();
		loadFile();
		}catch (FileNotFoundException e) {
			return -1;
		}
		return 0;
	}
	String getFileName(){
		return this.fileName;
	}
	int getFileLength(){
		return this.fileLength;
	}
	String[] getFile(){
		return this.file;
	}
	void saveFile(){
		if(this.fileName == "noName"){
			loadFileName();
		}
		try {
			save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int saveFile(String outFile) {
		this.fileName = outFile;
		if(this.fileName.trim().length() > 4){
		try {
			save();
		} catch (FileNotFoundException e) {
			return -1;
		}
		return 0;
		}
		else
			return -1;
	}
	private void save() throws FileNotFoundException{
		java.io.File fileOut = new java.io.File(this.fileName);
		java.io.PrintWriter output = new java.io.PrintWriter(fileOut);
		// write to file
		// for loop to write array to file format last, first
		int i = 0;
		while ((i < file.length && file[i] != null) || i == 0) {
			// print to file
			output.println(file[i]);
			i++;
		}
		// close file
		output.close();
		
	}
	public int[] commaSubStringFinder(String file) {
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
}
