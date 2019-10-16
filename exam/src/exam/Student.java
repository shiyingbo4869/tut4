package exam;
public class Student {  
	public enum String {

	}
	private String name = "";  
	private static String firstName = null;  
	private Student(java.lang.String string, java.lang.String string2, int i) {
		// TODO Auto-generated constructor stub
	}
	public String getFullName() { 
		// check whether first name is set   
		if (firstName==null) { 
			return name; 
			}
		else {
			return firstName + " " + name; 
			} 
	}   
	public static void main(String[] args) {
			// (multiple) constructors
//		public Student() { }	
            public Student (String fName, String lName, int a) {
			firstName = fName;
			String lastName = lName;
			int age = a;  
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
	
	

