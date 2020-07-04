package util;
/**
 * 留言板
 * @author 汐子
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import view.LoginUI;

public class LeavingMessageView extends JFrame {
	private JTextField textfind;
	private JTable leavingtable;
	private JTextField textname;
	private JTextField texttopic;
	private JTextPane textcontact;
	private JButton btnadd;
	private JButton btndelete;
	private JButton btnalter;
	private String path = "file/lead.txt";
	private JTextField textvalues;
	private String username;

	/**
	 * Create the frame.
	 */
	public LeavingMessageView() {
		setTitle("斗地主系统");
		setBounds(100, 100, 479, 550);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("留言板");
		lblNewLabel.setFont(new Font("华文行楷", Font.ITALIC, 26));
		lblNewLabel.setBounds(157, 10, 89, 29);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("内容：");
		lblNewLabel_1.setFont(new Font("方正姚体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(80, 48, 50, 25);
		getContentPane().add(lblNewLabel_1);

		textfind = new JTextField();
		textfind.setBounds(131, 49, 150, 25);
		getContentPane().add(textfind);
		textfind.setColumns(10);

		JButton btnfind = new JButton("查询");
		btnfind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnfindActionPerformed(e);
			}
		});
		btnfind.setFont(new Font("方正姚体", Font.PLAIN, 14));
		btnfind.setBounds(289, 50, 80, 25);
		getContentPane().add(btnfind);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 443, 177);
		getContentPane().add(scrollPane);

		leavingtable = new JTable();
		leavingtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				leavingTableMousePressed(e);
			}
		});
		leavingtable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u5E8F\u53F7", "\u7559\u8A00\u4EBA", "\u8BDD\u9898", "\u7559\u8A00\u5185\u5BB9" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		leavingtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		leavingtable.getColumnModel().getColumn(2).setPreferredWidth(104);
		leavingtable.getColumnModel().getColumn(3).setPreferredWidth(407);
		scrollPane.setViewportView(leavingtable);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8868\u5355\u64CD\u4F5C",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 270, 443, 168);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("留言人：");
		lblNewLabel_2.setFont(new Font("方正姚体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(20, 26, 60, 25);
		panel.add(lblNewLabel_2);

		textname = new JTextField();
		textname.setEditable(false);
		textname.setBounds(81, 27, 120, 25);
		panel.add(textname);
		textname.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("话题：");
		lblNewLabel_2_1.setFont(new Font("方正姚体", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(219, 26, 60, 25);
		panel.add(lblNewLabel_2_1);

		texttopic = new JTextField();
		texttopic.setColumns(10);
		texttopic.setBounds(276, 27, 120, 25);
		panel.add(texttopic);

		JLabel lblNewLabel_2_2 = new JLabel("留言内容：");
		lblNewLabel_2_2.setFont(new Font("方正姚体", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 94, 70, 25);
		panel.add(lblNewLabel_2_2);

		textcontact = new JTextPane();
		textcontact.setBounds(78, 83, 318, 52);
		panel.add(textcontact);
		
		textvalues = new JTextField();
		textvalues.setEditable(false);
		textvalues.setVisible(false);
		textvalues.setBounds(30, 61, 66, 21);
		panel.add(textvalues);
		textvalues.setColumns(10);

		btnadd = new JButton("添加");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnaddActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnadd.setFont(new Font("方正姚体", Font.PLAIN, 14));
		btnadd.setBounds(39, 458, 80, 30);
		getContentPane().add(btnadd);

		btndelete = new JButton("删除");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btndeleteActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btndelete.setFont(new Font("方正姚体", Font.PLAIN, 14));
		btndelete.setBounds(332, 458, 80, 30);
		getContentPane().add(btndelete);

		btnalter = new JButton("修改");
		btnalter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnalterActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnalter.setFont(new Font("方正姚体", Font.PLAIN, 14));
		btnalter.setBounds(181, 458, 80, 30);
		getContentPane().add(btnalter);
		this.fillTeacherTable();
	}
	/**
	 * 查询操作
	 * @param e
	 */
	private  void btnfindActionPerformed(ActionEvent e) {
		String tname =textfind.getText();
		DefaultTableModel dtm = (DefaultTableModel) leavingtable.getModel();
		dtm.setRowCount(0);
		ArrayList<LeavingMessage> arrayList = new ArrayList<LeavingMessage>();
	    Pattern tPattern =Pattern.compile(tname);
		try {
			ReadFile.readStudent(path, arrayList);
			int num = 1;
			for (int i = 0; i < arrayList.size(); i++) {
				Matcher tMatcher = tPattern.matcher(arrayList.get(i).getContact());
				if (tMatcher.find()) {
					LeavingMessage show = arrayList.get(i);
					Vector vector = new Vector();
					vector.add(num);
					vector.add(show.getName());
					vector.add(show.getTopic());
					vector.add(show.getContact());
					dtm.addRow(vector);
					num += 1;
				}
			}
			textfind.setText("");
		} catch (Exception se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
	}

	/**
	 * 修改操作
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void btnalterActionPerformed(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		String tvalue = textvalues.getText();
		String tname = textname.getText();
		String topic = texttopic.getText();
		String tcontact = textcontact.getText();
		if (StringUtil.isEmpty(tname)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (StringUtil.isEmpty(topic)) {
			JOptionPane.showMessageDialog(null, "话题不能为空", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (StringUtil.isEmpty(tcontact)) {
			JOptionPane.showMessageDialog(null, "留言内容不能为空", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		LeavingMessage leavingMessage = new LeavingMessage();
		ArrayList<LeavingMessage> arrayList = new ArrayList<LeavingMessage>();
		ReadFile.readStudent(path, arrayList);
		leavingMessage.setName(tname);
		leavingMessage.setTopic(topic);
		leavingMessage.setContact(tcontact);
		arrayList.set(Integer.parseInt(tvalue)-1, leavingMessage);
		WriteFile.writeData(path, arrayList);
		JOptionPane.showMessageDialog(null, "修改成功", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		resetValues();
		this.fillTeacherTable();
	}

	/**
	 * 删除操作
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void btndeleteActionPerformed(ActionEvent e) throws Exception {
		String tname = textname.getText();
		if (StringUtil.isEmpty(tname)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		ArrayList<LeavingMessage> arrayList = new ArrayList<LeavingMessage>();
		ReadFile.readStudent(path, arrayList);
		int index = -1;
		for (int x = 0; x < arrayList.size(); x++) {
			LeavingMessage s = arrayList.get(x);
			if (s.getName().equals(tname)) {
				index = x;
				break;
			}
		}
		if (index >= 0) {
			arrayList.remove(index);
			WriteFile.writeData(path, arrayList);
			JOptionPane.showMessageDialog(null, "删除成功", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			resetValues();
			this.fillTeacherTable();
		} else {
			JOptionPane.showMessageDialog(null, "删除失败", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * 添加操作
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void btnaddActionPerformed(ActionEvent e) throws Exception {
		// TODO Auto-generated method stub
		String tname = LoginUI.userNameGame;
		String topic = texttopic.getText();
		String tcontact = textcontact.getText();
		if (StringUtil.isEmpty(topic)) {
			JOptionPane.showMessageDialog(null, "话题不能为空", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (StringUtil.isEmpty(tcontact)) {
			JOptionPane.showMessageDialog(null, "留言内容不能为空", "系统提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		LeavingMessage leavingMessage = new LeavingMessage();
		ArrayList<LeavingMessage> arrayList = new ArrayList<LeavingMessage>();
		ReadFile.readStudent(path, arrayList);
		leavingMessage.setName(tname);
		leavingMessage.setTopic(topic);
		leavingMessage.setContact(tcontact);
		arrayList.add(leavingMessage);
		WriteFile.writeData(path, arrayList);
		JOptionPane.showMessageDialog(null, "添加成功", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		resetValues();
		this.fillTeacherTable();
	}

	/**
	 * 置空操作
	 */
	private void resetValues() {
		// TODO Auto-generated method stub
		this.textname.setText("");
		this.texttopic.setText("");
		this.textcontact.setText("");
	}

	/**
	 * 表格单击事件
	 */
	private void leavingTableMousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
		int row = leavingtable.getSelectedRow();
		username = LoginUI.userNameGame;
		textvalues.setText(leavingtable.getValueAt(row, 0).toString());
		textname.setText(leavingtable.getValueAt(row, 1).toString());
		texttopic.setText(leavingtable.getValueAt(row, 2).toString());
		textcontact.setText(leavingtable.getValueAt(row, 3).toString());
		if (!textname.getText().equals(username)) {
			btndelete.setEnabled(false);
			btnalter.setEnabled(false);
		}else {
			btndelete.setEnabled(true);
			btnalter.setEnabled(true);
		}
	}

	/**
	 * 表格初始化
	 * 
	 * @param schoolMessage
	 */
	private void fillTeacherTable() {
		DefaultTableModel dtm = (DefaultTableModel) leavingtable.getModel();
		dtm.setRowCount(0);
		ArrayList<LeavingMessage> arrayList = new ArrayList<LeavingMessage>();
		try {
			ReadFile.readStudent(path, arrayList);
			int num = 1;
			for (int i = 0; i < arrayList.size(); i++) {
				LeavingMessage show = arrayList.get(i);
				Vector vector = new Vector();
				vector.add(num);
				vector.add(show.getName());
				vector.add(show.getTopic());
				vector.add(show.getContact());
				dtm.addRow(vector);
				num += 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
