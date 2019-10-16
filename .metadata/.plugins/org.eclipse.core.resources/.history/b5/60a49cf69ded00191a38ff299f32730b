package nz.ac.massey.cs.shi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * 测试
 * @author follow
 *
 */
public class GuiTest {
	
	//文件打开读取测试
	@Test
	public void open(){
		File file = new File("src/main/java/nz/ac/massey/cs/shi/Print.java"); 
		//try resource,执行完自动释放程序
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
        	
        	while((line = br.readLine()) != null){
        		System.out.println(line);
        		
        	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
	}
	
	//文件搜索替换
	@Test
	public void searchReplace(){
		File file = new File("src/main/test.txt"); 
		//try resource,执行完自动释放程序
		StringBuilder sb = new StringBuilder("");
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
        	
        	while((line = br.readLine()) != null){
        		System.out.println(line);
        		sb.append(line);
        	}
        	
        	System.out.println(sb.toString().replaceAll("HI", "HI World"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	//文件字符串替换保存
	@Test
	public void replaceSave(){
		File file = new File("src/main/test.txt"); 
		
		BufferedWriter bw = null;
		//try resource,执行完自动释放程序
		StringBuilder sb = new StringBuilder("");
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
        	
        	while((line = br.readLine()) != null){
        		System.out.println(line);
        		sb.append(line);
        	}
        	
        	bw = new BufferedWriter(new FileWriter(file));
        	bw.append(sb.toString().replaceAll("Hello", "HI"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}

