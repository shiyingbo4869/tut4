
package exam;

import exam.TestReferenceTypes.student.Student;

public class ReferenceLeakers2{ 
	  public static void main(String[] args) {
		  Student aStudent = new Student("John","Smith",20);   
		  resetAge(aStudent.age);   
		  System.out.println(aStudent.age);  
		  }  
	  public static void resetAge (int age) { 
		  age = 18;  
		  } 
	  }