package nz.ac.massey.cs.pp.tutorial3.id19023254;

public class Student {

	String id;
	String fname;
	String name;
	String program;
	String major;
	
	Student(String id, String fname, String name, String program, String major ) {
		this.id = id;
		this.fname = fname;
		this.name = name;
		this.program = program;
		this.major = major;
		
	}
	
	@Override
	public String toString() {
		return this.id+','+this.fname+','+this.name+','+this.program+','+this.major+'\n';
	}

}