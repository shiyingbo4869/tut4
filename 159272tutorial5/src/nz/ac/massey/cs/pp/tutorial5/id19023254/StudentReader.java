package nz.ac.massey.cs.pp.tutorial5.id19023254;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentReader {
	List<Student> fetchStudents(File input) throws IOException{
		List<Student> students = new ArrayList<Student>();
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String tempstring=null;
		while((tempstring=reader.readLine()) != null) {
			Student student = new Student();
			StringTokenizer st = new StringTokenizer(tempstring,",");
			student.student_id=st.nextToken(",");
			student.student_firstName=st.nextToken(",");
			student.student_name=st.nextToken(",");
			student.student_program=st.nextToken(",");
			student.student_major=st.nextToken(",");
			students.add(student);
		}
		
		return students;
	}

}

