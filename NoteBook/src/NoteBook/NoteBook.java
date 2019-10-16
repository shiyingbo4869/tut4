package NoteBook;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.filechooser.FileSystemView;

/*import com.sun.org.apache.xml.internal.serialize.Printer;*/

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * ���±���ʼ
 * @author janksenhu
 */
public class NoteBook implements ActionListener {

    JFrame jf;
    JTextArea jta;
    JMenuItem[][] jmi;
    myStack ms = new myStack();

    public NoteBook() {
        jf = new JFrame("���±�");

        JMenuBar jmb = new JMenuBar();

        jta = new JTextArea();
        jf.add(jta);
        ms.push(jta.getText());

        JScrollPane jsp = new JScrollPane(jta);
        jf.add(jsp);

        String[] str1 = {"�ļ�", "�༭", "��ʽ", "����"};
        String[][] str2 = {{"�½�", "��", "����", "���", "", "ҳ������", "��ӡ", "", "�˳�"},
        {"����", "", "����", "����", "ճ��", "", "����", "�滻", "ɾ��", "ʱ��"},
        {"����"},
        {"��������", "���ڼ��±�"}};

        JMenu[] jm = new JMenu[str1.length];
//   jmi=new JMenuItem[str1.length][];//ע�������ﶨ���п�ָ���쳣��

        for (int i = 0; i < str1.length; i++) {
            jm[i] = new JMenu(str1[i]);
            System.out.println(jm[i]);
            jmb.add(jm[i]);
        }

        for (int i = 0; i < str1.length; i++) {
            jmi = new JMenuItem[str1.length][str2[i].length];
            for (int j = 0; j < str2[i].length; j++) {
                if ("".equals(str2[i][j])) {
                    jm[i].addSeparator();//��ӷָ��ߡ�
                } else {
                    jmi[i][j] = new JMenuItem(str2[i][j]);
                    jm[i].add(jmi[i][j]);
                    jmi[i][j].addActionListener(this);
                }
            }
        }

        jf.add(jmb, BorderLayout.NORTH);//ע����Ҫע����ŵ�λ�ã���Ȼ�������

        jf.setVisible(true);
        jf.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        jf.setLocation((tk.getScreenSize().width - jf.getSize().width) / 2, (tk.getScreenSize().height - jf.getSize().height) / 2);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextArea getJTextArea() {
        return this.jta;
    }

    public JFrame getJFrame() {
        return this.jf;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String str = e.getActionCommand();
        if ("�½�".equals(str)) {
            new NoteBook();

        } else if ("��".equals(str)) {

            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(null);//ע���������null��ʾ�Ի��򵯳���λ�ã�
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectfile = jfc.getSelectedFile();
                try {
                    FileReader reader = new FileReader(selectfile);
                    Scanner sc = new Scanner(reader);
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        jta.setText(line);
                    }

                    try {
                        reader.close();//////////////////////�Ķ�����Ҫ�ر���///////////////////
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
                ///////////////////////����/////////////////////////////
                javax.swing.JOptionPane.showMessageDialog(jfc, "���Ѿ�ȡ����");
            }

        } else if ("����".equals(str)) {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(null);//ע���������null��ʾ�Ի��򵯳���λ�ã�
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectfile = jfc.getSelectedFile();
                try {
                    PrintWriter pw = new PrintWriter(selectfile);//���ļ���д
                    pw.print(jta.getText());
                    pw.close();//�ر�///////////////////////////////////////////
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
                ///////////////////////////////////���//////////////////////////
                javax.swing.JOptionPane.showMessageDialog(jfc, "���Ѿ�ȡ����");
            }

        } else if ("���".equals(str)) {
            JOptionPane jop = new JOptionPane();
            String filePath = jop.showInputDialog("������·��������C:/save.txt��");//ֱ�ӷ��ص����û��������Ϣ��
//    jop.getInputValue();//ע�����ֵĵ��û��������ǲ��Եģ�
            String str2 = "^[cdefCDEF]:/[A-Za-Z0-9]{1,}.txt$";//·��������ʽ��
            System.out.println(filePath);
            if (filePath.matches(str2)) {//�ر�ע����������ʽ�ں���
                javax.swing.JOptionPane.showMessageDialog(jop, "�ļ�·����ȷ");
            } else {
                javax.swing.JOptionPane.showMessageDialog(jop, "�ļ�·������");
            }
            PrintWriter pw;
            try {
                pw = new PrintWriter(filePath);
                pw.print(jta.getText());
                pw.close();//�ر�////////////////////////// 
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }//���ļ���д

        } else if ("ҳ������".equals(str)) {
            javax.swing.JOptionPane.showMessageDialog(jf, "û�д�ӡ��");
        } else if ("��ӡ".equals(str)) {
            javax.swing.JOptionPane.showMessageDialog(jf, "û�д�ӡ��");
        } else if ("�˳�".equals(str)) {
            jf.dispose();//ע�������˳�
        } else if ("����".equals(str)) {
            String str5 = ms.pop();//����ջ
            jta.setText(str5);
        } else if ("����".equals(str)) {
            ms.push(jta.getText());//���ջ
            jta.cut();
        } else if ("����".equals(str)) {
            jta.copy();
        } else if ("ճ��".equals(str)) {
            ms.push(jta.getText());//���ջ
            jta.paste();
        } else if ("����".equals(str)) {
            String str3 = JOptionPane.showInputDialog(jf, "�������������");
            StringBuffer strbuf = new StringBuffer(jta.getText());
            int temp1 = strbuf.indexOf(str3);
            jta.select(temp1, (temp1 + str3.length()));
        } else if ("�滻".equals(str)) {
            ms.push(jta.getText());//���ջ
            exchange myExchange = new exchange(this);
        } else if ("ɾ��".equals(str)) {
            ms.push(jta.getText());//���ջ
            String str3 = jta.getSelectedText();
            StringBuffer str4 = new StringBuffer(jta.getText());
            str4.delete(str4.indexOf(str3), str4.indexOf(str3) + str3.length());
            str3 = new String(str4);
            jta.setText(str3);

        } else if ("ʱ��".equals(str)) {
            ms.push(jta.getText());//���ջ
            Date dt = new Date();
            SimpleDateFormat formater = new SimpleDateFormat();
            formater.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            String str3 = formater.format(dt);
            StringBuffer sb1 = new StringBuffer(jta.getText());
            String temp = new String(sb1.append(str3));
            jta.setText(temp);
            /////////////////����ʱ��//////////////////////// 
        } else if ("����".equals(str)) {
            ms.push(jta.getText());//���ջ 
        } else if ("��������".equals(str)) {
            help myhelp = new help(this);
        } else if ("���ڼ��±�".equals(str)) {
            javax.swing.JOptionPane.showMessageDialog(jf, "�ü��±���java���Ա�д�����ߣ�����ˮ����λ�������Ƽ���ѧ��ʱ�䣺2010��1��27");
        }
    }

////////////////////�滻��////////////////////////
    class exchange implements ActionListener {

        JDialog jd;
        JTextField jf1;
        JTextField jf2;
        JTextArea jta;

        public exchange(Object obj) {
            if (obj instanceof NoteBook) {
                NoteBook nb = (NoteBook) obj;
                jd = new JDialog(nb.getJFrame(), "�滻");
                jta = nb.getJTextArea();
            }

            jf1 = new JTextField(10);
            jf2 = new JTextField(10);

            JLabel jl1 = new JLabel("�滻����");
            JLabel jl2 = new JLabel("�滻����");

            JButton jb1 = new JButton("ȷ��");
            jb1.addActionListener(this);
            JButton jb2 = new JButton("ȡ��");
            jb2.addActionListener(this);

            JPanel jp1 = new JPanel(new FlowLayout());
            jp1.add(jl1);
            jp1.add(jf1);//�滻����

            JPanel jp3 = new JPanel(new FlowLayout());
            jp3.add(jl2);
            jp3.add(jf2);//�滻����

            JPanel jp2 = new JPanel(new FlowLayout());
            jp2.add(jb1);
            jp2.add(jb2);

            jd.setLayout(new GridLayout(3, 1));
            jd.add(jp1);
            jd.add(jp3);
            jd.add(jp2);

            jd.setSize(400, 290);
            Toolkit tk = Toolkit.getDefaultToolkit();
            jd.setLocation((tk.getScreenSize().width - jd.getSize().width) / 2, (tk.getScreenSize().height - jd.getSize().height) / 2);
            jd.setResizable(false);
            jd.setVisible(true);
            jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }

        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            System.out.println("ok");
            if ("ȷ��".equals(e.getActionCommand())) {
                String str = jf1.getText();
                String str1 = jf2.getText();
                String str2 = new String(jta.getText());
                str2 = str2.replaceAll(str, str1);
                jta.setText(str2);
                javax.swing.JOptionPane.showMessageDialog(jd, "�滻���");
            }
            if ("ȡ��".equals(e.getActionCommand())) {
                jd.dispose();//�رնԻ���
            }
        }
    }

/////////////////������///////////////////
    class help implements ActionListener {

        JDialog jd;

        public help(Object obj) {
            if (obj instanceof NoteBook) {
                NoteBook nb = (NoteBook) obj;
                jd = new JDialog(nb.getJFrame(), "help");
            }

            JMenuBar jmb = new JMenuBar();

            JMenuItem jm1 = new JMenuItem("�ļ�");
            jm1.addActionListener(this);
            JMenuItem jm2 = new JMenuItem("�༭");
            jm2.addActionListener(this);

            jmb.add(jm1);
            jmb.add(jm2);

            jd.add(jmb, BorderLayout.NORTH);

            jd.setSize(400, 290);
            Toolkit tk = Toolkit.getDefaultToolkit();
            jd.setLocation((tk.getScreenSize().width - jd.getSize().width) / 2, (tk.getScreenSize().height - jd.getSize().height) / 2);
            jd.setResizable(false);
            jd.setVisible(true);
            jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }

        public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method 
            System.out.println(1);
            if ("�ļ�".equals(e.getActionCommand())) {
                javax.swing.JOptionPane.showMessageDialog(jd, "1����Ҫ�Ĺ������½����򿪣�����ȡ�2������Ĳ�������˵�����");
            }
            if ("�༭".equals(e.getActionCommand())) {
                javax.swing.JOptionPane.showMessageDialog(jd, "1����Ҫ�Ĺ����г�����������ճ�������ƣ�ɾ���ȡ�2������Ĳ�������˵�����");
            }
        }
    }
//////////////////////////�ҵĶ�ջ///////////////////////

    class myStack {

        List list;

        public myStack() {
            list = new ArrayList();
        }

        public void push(Object obj) {
            if (obj instanceof String) {
                String str = (String) obj;
                list.add(obj);
            } else {
                System.exit(0);
            }
        }

        public String pop() {
            String obj = (String) list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return obj;
        }
    }
//////////////////////////////////////////////////////////// 

    public static void main(String[] args) {
        NoteBook nb = new NoteBook();
    }

}
 

