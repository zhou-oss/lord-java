package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.gameDataDao;
import util.BackgroundPanel;

public class gameData extends JFrame{
	private JPanel contentPane;
	private Image image;
	private gameDataDao dao;
	private String sql;
	private String str[]=new String[6];
	public gameData() {
		dao=new gameDataDao();
		sql="select * from User_Game where user='"+LoginUI.userNameGame+"'";
		ResultSet rs= dao.queryUserInfo(sql);
		int colCount;
		try {
			colCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colCount; i++) {
					str[i]=rs.getString(i);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setBounds(100, 100, 271, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("游戏数据");
		image = new ImageIcon("pics" + File.separator + "timg.jpg").getImage();
		contentPane = new BackgroundPanel(image);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(10, 27, 154, 15);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_6 = new JLabel("游戏昵称");
		lblNewLabel_6.setBounds(10, 73, 154, 15);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_6.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_1 = new JLabel("总场次");
		lblNewLabel_1.setBounds(10, 115, 154, 15);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_1.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_4 = new JLabel("胜率");
		lblNewLabel_4.setBounds(10, 157, 154, 15);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_4.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_8 = new JLabel("积分");
		lblNewLabel_8.setBounds(10, 203, 154, 15);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_8.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(132, 27, 154, 15);
		lblNewLabel_2.setText(str[1]);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_2.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_7 = new JLabel();
		lblNewLabel_7.setBounds(132, 73, 154, 15);
		lblNewLabel_7.setText(str[2]);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_7.setForeground(new Color(255,255,255));
		
		
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(132, 115, 154, 15);
		lblNewLabel_3.setText(str[3]);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_3.setForeground(new Color(255,255,255));
		
		
		
		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setBounds(132, 157, 154, 15);
		lblNewLabel_5.setText(str[4]+"%");
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_5.setForeground(new Color(255,255,255));

		JLabel lblNewLabel_9 = new JLabel();
		lblNewLabel_9.setBounds(132, 203, 154, 15);
		lblNewLabel_9.setText(str[5]);
		contentPane.add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("隶书",Font.BOLD,20));
		lblNewLabel_9.setForeground(new Color(255,255,255));
	}
}
