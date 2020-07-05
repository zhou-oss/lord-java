package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.modifiDao;
import dao.userDataDao;
import util.BackgroundPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class userData.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class userData extends JFrame implements ActionListener{
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The image. */
	private Image image;
	
	/** The dao. */
	private userDataDao dao;	
	
	/** The dao 1. */
	private modifiDao dao1;
	
	/** The sql. */
	private String sql;
	
	/** The str. */
	private String str[]=new String[6];
	
	/** The modifi. */
	JButton  modifi=new JButton("�޸�");
	
	/** The yes. */
	JButton  yes=new JButton("ȷ��");
	
	/** The lbl new label 2. */
	JTextField lblNewLabel_2 = new JTextField();
	
	/** The lbl new label 3. */
	JTextField lblNewLabel_3 = new JTextField();
	
	/** The lbl new label 9. */
	JTextField lblNewLabel_9 = new JTextField();
	
	/** The flag. */
	boolean flag=false;
	
	/**
	 * Instantiates a new user data.
	 */
	public userData() {
		dao=new userDataDao();
		dao1=new modifiDao();
		sql="select * from user where user='"+LoginUI.userNameGame+"'";
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
		setTitle("������Ϣ");
		image = new ImageIcon("pics" + File.separator + "timg.jpg").getImage();
		contentPane = new BackgroundPanel(image);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�û���");
		lblNewLabel.setBounds(10, 27, 154, 15);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("����",Font.BOLD,20));
		lblNewLabel.setForeground(new Color(255,255,255));
		
		
		JLabel lblNewLabel_1 = new JLabel("�ǳ�");
		lblNewLabel_1.setBounds(10, 77, 154, 15);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("����",Font.BOLD,20));
		lblNewLabel_1.setForeground(new Color(255,255,255));
		
		JLabel lblNewLabel_8 = new JLabel("�Ա�");
		lblNewLabel_8.setBounds(10,127, 154, 15);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("����",Font.BOLD,20));
		lblNewLabel_8.setForeground(new Color(255,255,255));
		
		lblNewLabel_2.setBounds(132, 27, 80, 20);
		lblNewLabel_2.setText(str[1]);
		lblNewLabel_2.setEditable(false);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("����",Font.BOLD,20));
		
		lblNewLabel_3.setBounds(132, 77, 80, 20);
		lblNewLabel_3.setText(str[3]);
		lblNewLabel_3.setEditable(false);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("����",Font.BOLD,20));
		
		
		lblNewLabel_9.setBounds(132, 127, 80, 20);
		lblNewLabel_9.setText(str[4]);
		lblNewLabel_9.setEditable(false);
		contentPane.add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("����",Font.BOLD,20));
		
		modifi.setBounds(20, 180, 80, 20);
		contentPane.add(modifi);
		lblNewLabel_9.setFont(new Font("����",Font.BOLD,20));
		modifi.addActionListener(this);
		
		yes.setBounds(160, 180, 80, 20);
		contentPane.add(yes);
		lblNewLabel_9.setFont(new Font("����",Font.BOLD,20));
		yes.addActionListener(this);
	}
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==modifi) {
			lblNewLabel_3.setEditable(true);
			lblNewLabel_9.setEditable(true);
			flag=true;
		}else if(e.getSource()==yes) {
			if(flag==true) {
				String user=lblNewLabel_2.getText();
				String name=lblNewLabel_3.getText();
				String sex=lblNewLabel_9.getText();
				String sql="update user set name='"+name+"',sex='"+sex+"'"
						+ " where user='"+user+"'";
				dao1.updataUserInfo(sql);
				flag=false;
				JOptionPane.showMessageDialog(null, "���޸�");
			}
		}
		
	}
}
