package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.LoginDao;
import dao.RegisterDao;
import dao.beginDao;
import dao.userDataDao;
import util.getTime;

public class Register {
	RegisterDao dao = new RegisterDao();
	LoginDao    dao1=new LoginDao();
	userDataDao   dao2=new userDataDao();
	beginDao    dao3=new beginDao();
	// ׼������
	private JFrame frame;

	private JPanel panNorth;
	private JLabel lblNewUser;
	private JTextField txtNewUser;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JLabel lblPassword1;
	private JPasswordField txtPassword1;

	private JPanel panCenter;
	private JLabel lblname;
	private JTextField txtname;
	private JLabel lblSex;
	private JLabel lblSex1;
	private JRadioButton boy;
	private JRadioButton girl;
	ButtonGroup group;

	private JPanel panSouth;
	private JButton btnRegister;
	private JButton btnClear;

	// ��ʼ������
	public Register(JFrame frame1) {
		// ��������
		frame = new JFrame("���û�ע��");
		panNorth = new JPanel();
		lblNewUser = new JLabel("�������û�����");
		txtNewUser = new JTextField(20);
		lblPassword = new JLabel("�������룺");
		txtPassword = new JPasswordField(20);
		lblPassword1 = new JLabel("�����������룺");
		txtPassword1 = new JPasswordField(20);
		// �в�����
		panCenter = new JPanel();
		group = new ButtonGroup();
		lblname = new JLabel("�ǳ�");
		txtname = new JTextField(20);
		lblSex = new JLabel("�Ա�");
		lblSex1 = new JLabel();
		boy = new JRadioButton("��");
		girl = new JRadioButton("Ů");
		// �ϲ�����
		panSouth = new JPanel();
		btnRegister = new JButton("�ύ");
		btnClear = new JButton("���");
		// 1.����������С��λ��
		frame.setSize(300, 215);
		frame.setLocationRelativeTo(null);
		// 2.�������ַ�ʽ
		frame.setLayout(new BorderLayout());
		panNorth.setLayout(new GridLayout(3, 2));
		panCenter.setLayout(new GridLayout(3, 2));
		panSouth.setLayout(new FlowLayout());
		// 3.�������
		frame.add(panNorth, BorderLayout.NORTH);
		frame.add(panCenter, BorderLayout.CENTER);
		frame.add(panSouth, BorderLayout.SOUTH);

		panNorth.add(lblNewUser);
		panNorth.add(txtNewUser);
		panNorth.add(lblPassword);
		panNorth.add(txtPassword);
		panNorth.add(lblPassword1);
		panNorth.add(txtPassword1);

		group.add(boy);
		group.add(girl);
		panCenter.add(lblname);
		panCenter.add(txtname);
		panCenter.add(lblSex);
		panCenter.add(boy);
		panCenter.add(lblSex1);
		panCenter.add(girl);

		panSouth.add(btnRegister);
		panSouth.add(btnClear);
		// 4.���ÿɼ���
		frame.setVisible(true);
		frame.setResizable(false);
		// ȷ����ť
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String user = txtNewUser.getText();//�û���
				String paw = String.valueOf(txtPassword.getPassword());//����
				String paw1= String.valueOf(txtPassword1.getPassword());//����1
				String name = txtname.getText();//�ǳ�
				String logtime=getTime.nowTime();//ע��ʱ��
				String sex  = null;//�Ա� 
				String sql = "select * from User where user='" + user + "'";//ע���û���Ϣ
				ResultSet rs = dao.queryUserInfo(sql);
				Enumeration<AbstractButton> eab = group.getElements();
				while (eab.hasMoreElements()) {
					AbstractButton btn = eab.nextElement();
					if(btn.isSelected()){
						sex=btn.getText();
						break;
					}
				}
				try {
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "�û����ظ�������������");
						txtNewUser.setText("");
						txtPassword.setText("");
						txtPassword1.setText("");
						txtname.setText("");
					} else if (!paw.equals(paw1)) {
						JOptionPane.showMessageDialog(null, "���벻һ�£���������������");
						txtPassword.setText("");
						txtPassword1.setText("");
						txtname.setText("");
					} else {
						String sql1 = "insert into User values(?,?,?,?,?)";
						String sql2 = "insert into login values(?,?)";
						String sql3 = "insert into user_game values(?,?,?,?,?)";
						dao.saveUserInfo(sql1, user, paw,name,sex,logtime);
						dao1.saveUserInfo(sql2, user, paw);
						dao2.saveUserInfo(sql3, user, name,"0","0","0");
						JOptionPane.showMessageDialog(null, "ע��ɹ�");
						frame.dispose();
						frame1.setVisible(true);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// �����ť
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtNewUser.setText("");
				txtPassword.setText("");
				txtPassword1.setText("");
				txtname.setText("");
			}
		});
	}
}