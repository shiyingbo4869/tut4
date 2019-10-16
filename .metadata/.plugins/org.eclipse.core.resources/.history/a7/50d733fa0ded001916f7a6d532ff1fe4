package Jtatext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class Print extends JFrame implements Printable, ActionListener {
	private String text;
	private JButton printBtn = new JButton("Print");   	
	private JScrollPane scrollPane = new JScrollPane();	//设置窗口的信息，添加各种面板按钮，及初始化按钮的监听器。	
	public Print(String text) {	
		this.text = text;
		setSize(800, 600);	//设置窗体大小位置	
		add(printBtn, BorderLayout.NORTH);	
		add(scrollPane, BorderLayout.CENTER);	
		printBtn.addActionListener(this);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);	
		}	
	@Override	
	public void actionPerformed(ActionEvent e) {
		//获取打印服务对象  	
		PrinterJob job = PrinterJob.getPrinterJob();       	
		job.setPrintable(Print.this); //添加打印任务	
		try {  	
			job.print();  //执行打印任务	
		} catch (PrinterException e1) {
			e1.printStackTrace();  	
		}  	
		int ok = JOptionPane.showConfirmDialog(this,		"导出打印pdf文件成功,是否继续打印", "", JOptionPane.YES_OPTION);
		if(ok == JOptionPane.NO_OPTION){
			this.dispose();
		}
	}	
	@Override	
	public int print(Graphics graphics, PageFormat pageFormat, 	int pageIndex) throws PrinterException {
		Graphics2D g = (Graphics2D) graphics;  
		int x = (int)pageFormat.getImageableX(); 
		int y = (int)pageFormat.getImageableY();
		switch(pageIndex){
			case 0:  	
				g.setColor(Color.RED);
				String[] arr = text.split("\n");
				int[] index = new int[]{10};
				for(String line : arr){
					g.drawString(line, x + 10, y + index[0]);
					index[0] += 10;
				}
				return PAGE_EXISTS;  
			default: return NO_SUCH_PAGE;  	
		}  	
	}
	public static void main(String[] args) {
		new Print("123");	
	}
}

