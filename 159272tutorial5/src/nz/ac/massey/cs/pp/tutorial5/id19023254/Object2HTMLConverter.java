package nz.ac.massey.cs.pp.tutorial5.id19023254;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;

public class Object2HTMLConverter {
		public void print(List data,Class type,File output) throws Exception{
		    
			BufferedWriter writer = new BufferedWriter(new FileWriter(output.getName()));
			Field[] fields = type.getDeclaredFields();
			writer.write("<html>");
			writer.write("<body>");
			writer.write("<table border = '1'>");
			writer.write("<tr>");
			for (Object adata:data) {
				writer.write("<tr>");
				for(Field field:fields) {
					writer.write("<th>" + field.get(adata) + "</th>");
				}
				writer.write("</th>");
			}
			writer.close();
		}
}
