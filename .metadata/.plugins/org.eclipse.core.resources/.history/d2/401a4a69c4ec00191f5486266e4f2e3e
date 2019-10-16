package Jtatext;
/**
 * @author Shi yingbo  zhangjing
 * @version 1.3.4
 * @date 2019-10-08
 */
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
 
public class Jtatext extends JFrame{

	public Jtatext()
	{
		
		super("Test Editor   " + new Date());
		
		//创建菜单(JMenuBar)对象
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
	    
		JTextArea workArea = new JTextArea();
	    JScrollPane imgScrollPane = new JScrollPane(workArea); 
	    //add(workArea);
	    add(imgScrollPane,BorderLayout.CENTER);  
	    
	    //定义打开和保存对话框  
	    FileDialog openDia;
		FileDialog saveDia;  
		//默认模式为 FileDialog.LOAD  
        openDia = new FileDialog(this,"Open",FileDialog.LOAD);  
        saveDia = new FileDialog(this,"Save AS",FileDialog.SAVE);  
	  
	    
	    JMenuItem item1_1 = new JMenuItem("New");
	    item1_1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		 workArea.setText("");//清空文本 
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
	    			String text = workArea.getText();
	    			bufw.write(text);
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
	    			String text = workArea.getText();
	    			bufw.write(text);
	    			bufw.close();
	    		}catch(IOException er){
	    			throw new RuntimeException("File save failed!"); 
	    		}
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
	    
	    JMenuItem item3_1 = new JMenuItem("search replace");
	    item3_1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		new Replace();
	    	}
	    });
	    JMenuItem item3_2 = new JMenuItem("Word wrap");
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
	    		new Print();
	    	}
	    });
	    
	    //在菜单中添加菜单项
	    file.add(item1_1);file.add(item1_2);file.add(item1_3);file.add(item1_4);file.add(item1_5);
	    view.add(item2_1);view.add(item2_2);view.add(item2_3);view.add(item2_4);
	    search.add(item3_1);search.add(item3_2);
	    help.add(item4_1);help.add(item4_2);help.add(item4_3);
	    	    
	}//构造方法结束 

	public static void main(String args[])
	{
		Jtatext app = new Jtatext();	
		app.setSize(600, 400);
		app.setLocation(200,200);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		
	}
	
 
}
