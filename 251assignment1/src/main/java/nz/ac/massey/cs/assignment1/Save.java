package nz.ac.massey.cs.assignment1;

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
public class Save extends JFrame implements Printable, ActionListener {
	private String text;
	private JButton printBtn = new JButton("Print");   	
	private JScrollPane scrollPane = new JScrollPane();	//Set window information
	//add various panel buttons,initialize the button listener.	
	public Save(String text) {	
		this.text = text;
		setSize(800, 600);	//Sets the form size position	
		add(printBtn, BorderLayout.NORTH);	
		add(scrollPane, BorderLayout.CENTER);	
		printBtn.addActionListener(this);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);	
		}	
	@Override	
	public void actionPerformed(ActionEvent e) {
		//Gets the print service object 	
		PrinterJob job = PrinterJob.getPrinterJob();       	
		job.setPrintable(Save.this); //Add print task	
		try {  	
			job.print();  //Perform print tasks	
		} catch (PrinterException e1) {
			e1.printStackTrace();  	
		}  	
		int ok = JOptionPane.showConfirmDialog(this,		"Export print PDF file successfully, whether to continue printing", ""
				, JOptionPane.YES_OPTION);
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
		new Save("123");	
	}
}

