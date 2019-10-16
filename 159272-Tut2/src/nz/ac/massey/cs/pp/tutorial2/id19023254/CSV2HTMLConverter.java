package nz.ac.massey.cs.pp.tutorial2.id19023254;
import java.io.*;
import java.util.*;
public class CSV2HTMLConverter {

	public static void main(String[] args) throws IOException{
		
			
			String nameOfInputFile = args[0];
			String nameOfOutputFile =args[1];
		

	    BufferedReader br = new BufferedReader(new FileReader(nameOfInputFile));
		String line = "";
		List<Student> information = new ArrayList<Student>();
		   
		    
        while ((line = br.readLine()) != null) {
          	 String specific[] = line.split(",");
          	 Student stu = new Student(specific[0],specific[1],specific[2],specific[3],specific[4]);
          	
              information.add(stu);
          }
        br.close();
        
        StringBuilder item = new StringBuilder();
        Student a = null;
        item.append("<html>");
        item.append("<head>");
        item.append("<table border=\"1\">");
        for(int i = 0; i < information.size(); i ++){
            a = information.get(i);
  	      item.append("<tr>");
  	      item.append("<th>" + a.studentID + "</th>");
  	      item.append("<th>" + a.firstName + "</th>");
  	      item.append("<th>" + a.name + "</th>");
  	      item.append("<th>" + a.program + "</th>");
  	      item.append("<th>" + a.major + "</th>");
  	      item.append("</tr>");}        	   
      
        String writeout = item.toString();
      
        WriteFile(nameOfOutputFile, writeout);
      
	}

	public static void WriteFile(String filename, String contents) throws IOException {
		
			FileWriter writer = new FileWriter(new File(filename));
			writer.write(contents);
			writer.close();
				
		
	}
	
}