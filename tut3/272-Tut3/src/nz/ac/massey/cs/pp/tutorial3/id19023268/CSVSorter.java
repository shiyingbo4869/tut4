package nz.ac.massey.cs.pp.tutorial3.id19023268;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedWriter;

public class CSVSorter {
	
	public static void main(String[] args) throws IllegalArgumentException {
		String csvInputFileName = null;
		String csvOutputFileName = null;
		String sortMethod = null;
		Student[] students;
		int lineNumber;
		CSVSorter main = new CSVSorter();
		// check the parameter
		try {
			 csvInputFileName = args[0];
			 csvOutputFileName = args[1];
			 sortMethod = args[2];
			 if (csvInputFileName.equals(csvOutputFileName)) {
             throw new IllegalArgumentException("parameters error");
			 }
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// create files handle
		File csvInputFile = new File(csvInputFileName);
		File csvOutputFile = new File(csvOutputFileName);
		
		// check csv file exist or not 
		if (!csvInputFile.exists()) {
			System.out.println("csv file not exist!");
			System.exit(1);
		}

		//create new csv file
		try {
			if (!csvOutputFile.exists()) {
				csvOutputFile.createNewFile();
			}
		}catch (IOException e){
			System.out.println("fail to create new csv file");
			e.printStackTrace();
			System.exit(1);
		}

		// make an analysis and an output
		lineNumber = main.lineCount(csvInputFileName) - 1;
		students = main.createStudents(csvInputFileName, lineNumber);
		main.sortFunction(students, sortMethod);
		main.csvWriter(csvOutputFileName, students);
		System.out.println(String.format("File name: <%s> has been created!", csvOutputFileName));

	}
	

	// this function is used to read the csv file and assign to Student instance
	private Student[] createStudents(String fileName, int lineNumber) {
		Student[] group = new Student[lineNumber];
		short groupIndex = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			String[] item;
			reader.readLine();
			while(((line=reader.readLine())!=null) && (groupIndex < lineNumber)){
				item = line.split(",");				
				group[groupIndex] = new Student(item[0], item[1], item[2], item[3], item[4]);
				groupIndex++;
			}
			reader.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return group;
	}

	// this function is used to write value of students to a new csv file
	private void csvWriter(String fileName, Student[] array) {
		try {
			StringBuilder writer = new StringBuilder();
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
			bw.write("student_id,fname,name,program, major\n");

			for(Student student:array){
				writer.append(student.toString());
			}

			bw.write(writer.toString());
			bw.close();
		}catch (IOException e){
			e.printStackTrace();
		}


	}

	// this function is used to count the line number of files in order to maintain the code easily
	private int lineCount(String fileName) {
		int number = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while((reader.readLine())!=null){
				number++;
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return number;
	}

	//this function is used to sort the array according to the user's order
	private void sortFunction (Student[] array, String method){
		if (method.equals("by_id")){
			Arrays.sort(array, new StudentComparatorById());
		}
		else if (method.equals("by_firstname")){
			Arrays.sort(array, new StudentComparatorByFirstName());
		}
		else if (method.equals("by_name")){
			Arrays.sort(array, new StudentComparatorByName());
		}
		else if (method.equals("by_program")){
			Arrays.sort(array, new StudentComparatorByProgram());
		}
		else if (method.equals("by_major")){
			Arrays.sort(array, new StudentComparatorByMajor());
		}
		else{
			Arrays.sort(array);
		}
	}
	
}
