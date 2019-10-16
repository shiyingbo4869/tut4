package nz.ac.massey.cs.pp.tutorial5.id19023254;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Test {
	public static void main(String args[])throws Exception{
	String inputFileName = args[0]; 
	String outputFileName = args[1]; 
	File input = new File(inputFileName );   
	File output = new File(outputFileName );  
	StudentReader reader = new StudentReader();  
	List<Student> data = reader.fetchStudents(input); 
	//System.out.println(Student.class);
	Object2HTMLConverter converter = new Object2HTMLConverter(); 
	converter.print(data,Student.class,output);
	int x = 4; 
	int y = 38; 
    System.out.println(42 == (x+y));
	}
}