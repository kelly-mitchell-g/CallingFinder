package calling;

public class CallingListBack {
	MyFile inFile = new MyFile();
	MyFile requested = new MyFile();
	public int error = 0;
	CallingListBack(String fileName){
		inFile.setFileName(fileName);
		error = inFile.load();
	}
	void finder(String[] list, int choice) {
		// This will take the file and check specified column for input
		//(Doesn't matter what)
		// if input move to next
		// else check previous column 
		// if input take entire row and add to file2
		// no input move to next line
		String tmpName = new String();
		requested.setFileLength(inFile.getFileLength());
		int n = 1;
		for (int i = 1; i < inFile.file.length; i++) {
			int[] indexes = inFile.commaSubStringFinder(inFile.file[i]);
			//System.out.println(i + " " + file[0].length + " " + indexes[choice-1]);
			String cell = inFile.file[i].substring(indexes[choice-1], (indexes[choice] >= 0) ? indexes[choice] : inFile.file[i].length()).trim();//cannot handle online
			//desired column
			if (cell.length() == 1) {//if nothing there (not called) [just the comma]
				if(choice == 1)//if requesting the first column
					tmpName = inFile.file[i].substring(0, indexes[0]).trim();
				else
					tmpName = inFile.file[i].substring(indexes[choice-2], indexes[choice-1]).trim();
				//column before desired
				if (tmpName.length() > 1) {//if populated
					if (n == 1) {//save categories//if first column
						requested.file[0] = inFile.file[0];
					}
					requested.file[n] = inFile.file[i];//save line
					n++;
				}
			}
		}
	}	
	int returnError(){
		return error;
	}
}
