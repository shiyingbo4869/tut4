/**
 * 
 */
/**
 * @author LENOVO
 *
 */
package exam;
public class TestReferenceTypes { 
	public class Student {

	}
	public class student {
//		public static void main(String[] args) throws IllegalArgumentException {
			public class Student { 
				// (multiple) constructors
				public Student() {}  
				public Student(String fName, String lName, int a) {
					firstName = fName;
					lastName = lName;
					age = a;  
					} 
				// state: instance variables  
				public String lastName = ""; 
				public String firstName = ""; 
				public int age = 0;  
				// behavior: methods 
				public String getFullName() {
					return firstName + " " + lastName;  
				} 
			} 
		}
	  public static void main(String[] args) {   
		  Student aStudent = new Student("John","Smith",20);
		  resetName(aStudent);   
		  System.out.println(aStudent.lastName);  } 
	  public static void resetName (Student s) {  
		  s.lastName = "?";  
		  } 
	  } 