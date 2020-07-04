package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.LoginDao;

public class LoginUI {
	LoginDao dao=new LoginDao();
	// 初始化
	private JFrame frame;
	private JPanel panNorth;
	private JLabel lblNorth;
	private JPanel panCenter;
	private JLabel lblUser;
	private JTextField txtUser;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JPanel panSouth;
	private JButton btnLongin;
	private JButton btnClear;
	private JButton btnRegister;
	public static String userNameGame="123";
	public LoginUI() {
		// ==准备组件
		frame = new JFrame("斗地主");
		//容器北部
		panNorth = new JPanel();
		lblNorth = new JLabel();
		lblNorth.setIcon(new ImageIcon("pics"+File.separator+"tables.png"));
		//容器中部
		panCenter = new JPanel();
		lblUser = new JLabel("用户名：");
		txtUser = new JTextField(20);
		lblPassword = new JLabel("密码：");
		txtPassword = new JPasswordField(20);
		//容器南部
		panSouth = new JPanel();
		btnLongin = new JButton("登录");
		btnClear = new JButton("重置");
		btnRegister = new JButton("注册");
		//设置容器位置
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		//设置布局方式
		frame.setLayout(new BorderLayout());
		panNorth.setLayout(new FlowLayout());
		panCenter.setLayout(new GridLayout(2, 2));
		panSouth.setLayout(new FlowLayout());
		//添加组件
		frame.add(panNorth, BorderLayout.NORTH);
		frame.add(panCenter, BorderLayout.CENTER);
		frame.add(panSouth, BorderLayout.SOUTH);
		panNorth.add(lblNorth);
		panCenter.add(lblUser);
		panCenter.add(txtUser);
		panCenter.add(lblPassword);
		panCenter.add(txtPassword);
		panSouth.add(btnLongin);
		panSouth.add(btnRegister);
		panSouth.add(btnClear);
		//设置容器可见性和关闭方式
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//加入监听器
		//登录按钮
		btnLongin.addActionListener(new  ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = txtUser.getText();
				String password = String.valueOf(txtPassword.getPassword());
				String sql = "select * from Login where user='" + user + "'";
				ResultSet rs =dao.queryUserInfo(sql);
				try {
					if(rs.next()) {
						if (rs.getString(2).equals(password)) {
							JOptionPane.showMessageDialog(null, "登录成功");
							userNameGame=user;
							new Main();
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误！");
						}
					}else {
						JOptionPane.showMessageDialog(null, "用户不存在！");
					}
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
	});

		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtUser.setText(null);
				txtPassword.setText(null);
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame.setVisible(false);
				new Register(frame);
			}
		});
		
	}
	public static void main(String[] args) {
		new LoginUI();
	}
}
