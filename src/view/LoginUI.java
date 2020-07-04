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
	// ��ʼ��
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
		// ==׼�����
		frame = new JFrame("������");
		//��������
		panNorth = new JPanel();
		lblNorth = new JLabel();
		lblNorth.setIcon(new ImageIcon("pics"+File.separator+"tables.png"));
		//�����в�
		panCenter = new JPanel();
		lblUser = new JLabel("�û�����");
		txtUser = new JTextField(20);
		lblPassword = new JLabel("���룺");
		txtPassword = new JPasswordField(20);
		//�����ϲ�
		panSouth = new JPanel();
		btnLongin = new JButton("��¼");
		btnClear = new JButton("����");
		btnRegister = new JButton("ע��");
		//��������λ��
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		//���ò��ַ�ʽ
		frame.setLayout(new BorderLayout());
		panNorth.setLayout(new FlowLayout());
		panCenter.setLayout(new GridLayout(2, 2));
		panSouth.setLayout(new FlowLayout());
		//������
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
		//���������ɼ��Ժ͹رշ�ʽ
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���������
		//��¼��ť
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
							JOptionPane.showMessageDialog(null, "��¼�ɹ�");
							userNameGame=user;
							new Main();
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "�û������������");
						}
					}else {
						JOptionPane.showMessageDialog(null, "�û������ڣ�");
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
