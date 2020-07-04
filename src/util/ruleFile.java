package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class ruleFile {
	private  static ruleFile instance=new ruleFile();
	private  static JFrame frame;
	private JTextArea txtArea;
	private ScrollPane src;
	private ruleFile() {
		//准备界面
		frame=new JFrame("游戏规则");
		frame.setLayout(new BorderLayout());
		src=new ScrollPane();
		txtArea=new JTextArea();
		txtArea.setEditable(false);
		txtArea.setBackground(new Color(150,150,110));
		txtArea.setFont(new Font("隶书",Font.BOLD,20));
		src.add(txtArea);
		frame.add(src,BorderLayout.CENTER);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		this.getRules();
		}
	public static ruleFile getRuleFile() {
		frame.setVisible(true);
		return instance;
	}
	public void getRules(){
		File file = new File("src/rules.txt");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			// 编码模式换成utf-8
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String temp=null; 
			 while((temp=br.readLine())!=null) {
				 txtArea.append(temp);;
			     txtArea.append("\n"); 
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
