package nz.ac.massey.cs.shi;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Replace extends JFrame {
	private static final long serialVersionUID = 1L;
	public JPanel pnl_mian;
	public JLabel lbl_help;
	public JLabel lbl_find;
	public JLabel lbl_replace;
	public JLabel lbl_path;
	public JTextField txt_find;
	public JTextField txt_replace;
	public JButton btn_sub;
	public JButton btn_reset;
	public static String path;
	public static String find;
	public static String replace;
	public Replace() {
		pnl_mian = new JPanel();
		lbl_help = new JLabel();
		lbl_find = new JLabel();
		lbl_replace = new JLabel();
		txt_find = new JTextField();
		txt_replace = new JTextField();
		btn_sub = new JButton();
		btn_reset = new JButton();
		userInit();
	}
	public void userInit() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Set to close the frame while closing the program
		this.setSize(500, 300);// 设置框架大小为长500,宽200
		this.setResizable(false);// 设置框架不可以改变大小
		this.setTitle("Search Replace");// 设置框架标题
		this.pnl_mian.setLayout(null);// 设置面板布局管理
		this.lbl_help.setText("Search Replace");// 设置标签标题
		this.lbl_find.setText("search:");
		this.lbl_replace.setText("replace:");
		this.btn_sub.setText("confirm");
		this.btn_reset.setText("reset");
		this.lbl_help.setBounds(200, 25, 100, 21);// 设置标签x坐标120,y坐标20,长60,宽20
		this.lbl_find.setBounds(100, 80, 60, 20);
		this.lbl_replace.setBounds(100, 110, 60, 25);
		this.txt_find.setBounds(150, 80, 200, 20);
		this.txt_replace.setBounds(150, 110, 200, 20);
		this.btn_sub.setBounds(130, 180, 80, 20);
		this.btn_sub.addActionListener(new ActionListener()// 匿名类实现ActionListener接口
				{
					public void actionPerformed(ActionEvent e) {
						btnsub_ActionEvent(e);
					}
				});
		this.btn_reset.setBounds(245, 180, 80, 20);
		this.btn_reset.addActionListener(new ActionListener()// 匿名类实现ActionListener接口
				{
					public void actionPerformed(ActionEvent e) {
						btnreset_ActionEvent(e);
					}
				});
		this.pnl_mian.add(lbl_help);// 加载标签到面板
		this.pnl_mian.add(lbl_find);
		this.pnl_mian.add(lbl_replace);
		this.pnl_mian.add(txt_find);
		this.pnl_mian.add(txt_replace);
		this.pnl_mian.add(btn_sub);
		this.pnl_mian.add(btn_reset);
		this.add(pnl_mian);// 加载面板到框架
		this.setVisible(true);// 设置框架可显
	}
 
	public void btnsub_ActionEvent(ActionEvent e) {
		find = txt_find.getText();
		replace = String.valueOf(txt_replace.getText());
		if (find.equals("")) {
			JOptionPane.showMessageDialog(null, "Search object cannot be empty!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (replace.equals("")) {
			JOptionPane.showMessageDialog(null, "Replace content cannot be empty!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			File file = new File(path);
			try {
				changeFile(file);
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			}
			this.dispose();
		}
	}
	public void btnreset_ActionEvent(ActionEvent e) {
		txt_find.setText("");
		txt_replace.setText("");
	}
 
	public static void changeFile(File file) throws IOException {
		BufferedReader br = null;
		try {
			if (!file.exists()) {
				JOptionPane.showMessageDialog(null, "File path error!", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			FileReader fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			StringBuffer sbf = new StringBuffer("");
			try {
				for (String tmp = null; (tmp = br.readLine()) != null; tmp = null) {
					// 在这里做替换操作
					if (tmp.contains(find)) {
						tmp = tmp.replaceAll(find, replace);
						sbf.append(tmp);
						sbf.append(System.getProperty("line.separator"));
						// 文件的重新写入
						BufferedWriter bw = new BufferedWriter(new FileWriter(
								path));
						bw.write(sbf.toString());
						JOptionPane.showMessageDialog(null, "The file content has been replaced successfully!",
								"confirm", JOptionPane.YES_OPTION);
						bw.close();
					} else {
						JOptionPane.showMessageDialog(null, "The file does not contain the content to be replaced！",
								"confirm", JOptionPane.YES_OPTION);
					}
 
				}
				br.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Read file error", "Error",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
				return;
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "File path error", "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			return;
		}
	}
	public static void main(String[] args) {
		new Replace();
	}

}
