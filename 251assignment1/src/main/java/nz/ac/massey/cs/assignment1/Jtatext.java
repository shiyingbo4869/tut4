package nz.ac.massey.cs.assignment1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.jar.JarException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import java.io.FileNotFoundException;
import java.io.FileReader;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonIOException;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.JsonSyntaxException;
//import com.alibaba.fasajson.JSONObject;
//import com.alibaba.fasajson.JSON;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.util.List;
 
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
public class Jtatext extends JFrame{

	public Jtatext()
	{
		
		super("Test Editor   " + new Date());
		
		//创建菜单栏(JMenuBar)对象
		JMenuBar mBar = new JMenuBar();
		//在JFrame等容器中设置菜单栏对象，即将菜单栏添加到框架容器中
		this.setJMenuBar(mBar);
		
		//创建菜单对象
		JMenu file = new JMenu("File");
		JMenu search = new JMenu("Search");
		JMenu view = new JMenu("View");
		JMenu help = new JMenu("Help");
		
		//将菜单添加到菜单栏中
		mBar.add(file);
		mBar.add(search);
		mBar.add(view);
		mBar.add(help);
	    
		final JTextArea workArea = new JTextArea();
	    JScrollPane imgScrollPane = new JScrollPane(workArea); 
	    //add(workArea);
	    add(imgScrollPane,BorderLayout.CENTER);  
	    
	    //定义打开和保存对话框  
	    final FileDialog openDia;
		final FileDialog saveDia;  
		//默认模式为 FileDialog.LOAD  
        openDia = new FileDialog(this,"Open",FileDialog.LOAD);  
        saveDia = new FileDialog(this,"Save AS",FileDialog.SAVE);  
	  
	    
	    JMenuItem item1_1 = new JMenuItem("New");
	    item1_1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		//清空文本 
	    		 workArea.setText("");
	    	}
	    });
	    JMenuItem item1_2 = new JMenuItem("Open");
	    item1_2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		openDia.setVisible(true);
	    		String dirPath = openDia.getDirectory();//获取文件路径  
                String filename = openDia.getFile();//获取文件名称  
                
              //如果打开路径 或 目录为空 则返回空  
                if(dirPath == null || filename == null){
                	return;
                }
                
                workArea.setText("");//清空文本
                
                File fileO = new File(dirPath,filename); 
                
                try{
                	BufferedReader bufr = new BufferedReader(new FileReader(fileO));
                	//设置文本字体颜色
                	if(filename.endsWith(".java")){
                		workArea.setForeground(Color.BLUE);
                	}
                	
                	if(filename.endsWith(".py")){
                		workArea.setForeground(Color.GREEN);
                	}
                	
                	if(filename.endsWith(".cpp")){
                		workArea.setForeground(Color.RED);
                	}
                	
                	String line = null;
                	
                	while((line = bufr.readLine()) != null){
                		workArea.append(line + "\r\n");
                	}
                	
                	bufr.close(); //关闭文本
                }catch(IOException er1){
                	throw new RuntimeException("File read failed!"); 
                }
                
	    	}
	    });
	    JMenuItem item1_3 = new JMenuItem("Save");
	    item1_3.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		String text = workArea.getText();
		    		System.out.println(text);
		    		System.out.println(text.contains("\n"));
		    		System.out.println(text.contains("\r\n"));
		    		new Print(text);
	    		    File fileS = null;
	    		if(fileS == null){
	    			saveDia.setVisible(true);  
                    String dirPath = saveDia.getDirectory();  
                    String fileName = saveDia.getFile();  
                    
                    if(dirPath == null || fileName == null)
                    	return;
                    
                    fileS = new File(dirPath,fileName);  
	    		}
	    		
	    		try{
	    			BufferedWriter bufw = new BufferedWriter(new FileWriter(fileS));
	    			String text1 = workArea.getText();
	    			bufw.write(text1);
	    			bufw.close();
	    		}catch(IOException er){
	    			throw new RuntimeException("File save failed!"); 
	    		}
	    	}
	    });
	    JMenuItem item1_4 = new JMenuItem("Save AS");
	    item1_4.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		File fileS = null;
	    		if(fileS == null){
	    			saveDia.setVisible(true);  
                    String dirPath = saveDia.getDirectory();  
                    String fileName = saveDia.getFile();  
                    
                    if(dirPath == null || fileName == null)
                    	return;
                    
                    fileS = new File(dirPath,fileName);  
	    		}
	    		
	    		try{
	    			BufferedWriter bufw = new BufferedWriter(new FileWriter(fileS));
	    			String text2 = workArea.getText();
	    			bufw.write(text2);
	    			bufw.close();
	    		}catch(IOException er){
	    			throw new RuntimeException("File save failed!"); 
	    		}
	    	    String text = workArea.getText();
	    		System.out.println(text);
	    		System.out.println(text.contains("\n"));
	    		System.out.println(text.contains("\r\n"));
	    		new Print(text);
	    	}
	    });
	    JMenuItem item1_5 = new JMenuItem("Quit");
	    item1_5.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
	    
	    JMenuItem item2_1 = new JMenuItem("Select text");
	    
	    JMenuItem item2_2 = new JMenuItem("Cut");
	    JMenuItem item2_3 = new JMenuItem("Copy");  		

	    JMenuItem item2_4 = new JMenuItem("Paste");
	    
	    //添加键盘快捷键(全选)
	    item2_1.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));
	    item2_1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		workArea.selectAll();
	    		workArea.copy();
	    	}
	    });
	    //复制
	    item2_3.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
	    item2_3.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		workArea.copy();
	    	}
	    });
	    //粘贴
	    item2_4.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
	    item2_4.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		workArea.paste();
	    	}
	    });
	    //截切
	    item2_2.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
	    item2_2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		workArea.cut();
	    	}
	    });
	    
	    JMenuItem item3_1 = new JMenuItem("search replace");
	    item3_1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		new Replace();
	    	}
	    });
	    final JMenuItem item3_2 = new JMenuItem("Word wrap");
	    item3_2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		Object source = e.getSource();
	    		
	    		if(source == item3_2)
	    			workArea.setLineWrap(true);    //自动换行
	    		else if(source != item3_2)
	    			workArea.setLineWrap(false);
	    	}
	    });
	    JMenuItem item4_1 = new JMenuItem("Help");
	    item4_1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		new LookHelp();
	    	}
	    });
	    JMenuItem item4_2 = new JMenuItem("About");
	    item4_2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		new AboutBook();
	    	}
	    });
	    JMenuItem item4_3 = new JMenuItem("Print");
	    item4_3.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		String text = workArea.getText();
	    		System.out.println(text);
	    		System.out.println(text.contains("\n"));
	    		System.out.println(text.contains("\r\n"));
	    		new Print(text);
	    	}
	    });
	    
	    //在菜单中添加菜单项
	    file.add(item1_1);file.add(item1_2);file.add(item1_3);file.add(item1_4);file.add(item1_5);
	    view.add(item2_1);view.add(item2_2);view.add(item2_3);view.add(item2_4);
	    search.add(item3_1);search.add(item3_2);
	    help.add(item4_1);help.add(item4_2);help.add(item4_3);
	    	    
	}//构造方法结束 
	
	
	public static void init(){
		Jtatext app = new Jtatext();	
		app.setSize(600, 400);
		app.setLocation(200,200);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}

	public static void main(String args[]){
		init();
	}
	public class Util {
		public String ReadFile(String Path){
			BufferedReader reader = null;
			String laststr = "";
			try{
				FileInputStream fileInputStream = new FileInputStream(Path);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
				reader = new BufferedReader(inputStreamReader);
				String tempString = null;
				while((tempString = reader.readLine()) != null){
					laststr += tempString;
				}
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if(reader != null){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return laststr;
		}

	}

public class JsonTest {
	@SuppressWarnings({ "static-access", "deprecation", "unchecked" })
	public <MorphDynaBean> void main(String[] args){
		String JsonContext = new Util().ReadFile("aaa.json");
		JSONArray jsonArray = JSONArray.fromObject(JsonContext);
		/*String s= java.net.URLDecoder.decode(JsonContext, "utf-8");
		JSONObject jsonArray = new JSONObject();*/
 
		int size = jsonArray.size();
		System.out.println("Size: " + size);
		for(int  i = 0; i < size; i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			System.out.println("name=" + jsonObject.get("name"));
			System.out.println("package_name=" + jsonObject.get("package_name"));
			System.out.println("check_version=" + jsonObject.get("check_version"));
			
		}
		List<MorphDynaBean> listObject = jsonArray.toList(jsonArray);
		for(int i = 0, j = listObject.size(); i < j ; i++){
			System.out.println(listObject.get(i));
		}
		for(MorphDynaBean temp: listObject){
			System.out.println(((net.sf.ezmorph.bean.MorphDynaBean) temp).get("name"));
		}
	}
}

	
}	 