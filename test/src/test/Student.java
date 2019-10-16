package test;

import java.util.ArrayList;

public class Student{
	private String firstName;
	private String lastName;
	private int age;

	public static void main(String[] args) { 
		Student aStudent = new Student();   
		aStudent.firstName = "John";   
		aStudent.lastName = "Smith";   
		aStudent.age = 20; 
	System.out.println(aStudent.getFullName());  
	}

	private char[] getFullName() {
		// TODO Auto-generated method stub
		return null;
	} 
	
	
}		