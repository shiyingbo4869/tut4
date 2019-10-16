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
public class Print extends JFrame implements Printable, 	ActionListener {
	private Object[][] data = {{"100", "0.8", "0.1", "90"}};	
	//JTableb表头
	private Object[] head = {"单价", "折扣", "税", "调整后价"}; 	//定义一个存放数据的JTable	
	private JTable table = new JTable(data, head);	//打印出一条记录的按钮	
	private JButton printBtn = new JButton("打印");   	
	private JScrollPane scrollPane = new JScrollPane();	//设置窗口的信息，添加各种面板按钮，及初始化按钮的监听器。	
	public Print() {	
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
		JOptionPane.showMessageDialog(this, 	"导出打印pdf文件成功");
	}	
	@Override	
	public int print(Graphics graphics, PageFormat pageFormat, 	int pageIndex) throws PrinterException {
		Graphics2D g = (Graphics2D) graphics;  
		int x = (int)pageFormat.getImageableX(); 
		int y = (int)pageFormat.getImageableY();
		switch(pageIndex){
		case 0:  	g.setColor(Color.RED);
		g.drawString("单价：" + table.getValueAt(0, 0), x + 100, y + 10);
		g.drawString("折扣：" + table.getValueAt(0, 1), x + 100, y + 30);
		g.drawString("税率：" + table.getValueAt(0, 2), x + 100, y + 50);	
		g.drawString("单价：" + table.getValueAt(0, 3), x + 100, y + 70);	
		return PAGE_EXISTS;  
		default:  	return NO_SUCH_PAGE;  	
		}  	
	}
	public static void main(String[] args) {
		new Print();	
		}
}


