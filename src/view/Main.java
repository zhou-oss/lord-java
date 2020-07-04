package view;

import java.awt.Desktop;

import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.junit.Test;

import com.Begin;
import com.Card;

import dao.modifiDao;
import util.BackgroundPanel;
import util.ExcelTest;
import util.rankFile;
import util.ruleFile;

public class Main implements ActionListener {
	private modifiDao dao = new modifiDao();
	private JFrame frame;
	private JPanel panel;
	private JMenuBar bar;

	private JMenu game;
	private JMenu help;
	private JMenu player;

	private JMenuItem newGame;
	private JMenuItem overGame;
	private JMenuItem rank;

	private JMenuItem rules;
	private JMenuItem about;
	private JMenuItem user;
	private JMenuItem del;

	private JButton begin;
	private JButton imExcel;
	private JButton exExcel;
	private JButton txtWord;
	JToolBar toolBar;

	private Card card[];
	private List<Card> players[] = null;
	private List<Card> playerCards[] = null;
	private List<Card> landlordCard = null;
	Image image;
	Image image1;
	String str = LoginUI.userNameGame;

	public Main() {
		frame = new JFrame("������");
		panel = new JPanel();

		image = new ImageIcon("pics" + File.separator + "bgx.png").getImage();
		panel = new BackgroundPanel(image);

		bar = new JMenuBar();
		toolBar = new JToolBar();
		game = new JMenu("��Ϸ");
		newGame = new JMenuItem("�µĿ�ʼ");
		overGame = new JMenuItem("�˳���Ϸ");
		rank = new JMenuItem("���԰�");
		del = new JMenuItem("�û�ע��");

		help = new JMenu("����");
		rules = new JMenuItem("��Ϸ����");
		player = new JMenu("��������");

		about = new JMenuItem("��Ϸ����");
		user = new JMenuItem("������Ϣ");

		begin = new JButton(new ImageIcon("pics" + File.separator + "begin.png"));
		imExcel = new JButton("��������");
		exExcel = new JButton("��������");
		txtWord = new JButton("��Ϸ�ĵ�");
		panel.setLayout(null);
		panel.add(begin);
		panel.add(toolBar);
		begin.setBounds(185, 200, 300, 120);
		toolBar.setBounds(0, 0, 800, 30);
		frame.setContentPane(panel);

		frame.setJMenuBar(bar);

		bar.add(game);
		bar.add(help);
		toolBar.add(imExcel);
		toolBar.add(exExcel);
		toolBar.add(txtWord);

		game.add(newGame);
		game.add(rank);
		game.add(overGame);

		help.add(rules);
		help.add(player);

		player.add(about);
		player.add(user);
		player.add(del);

		begin.addActionListener(this);
		newGame.addActionListener(this);
		overGame.addActionListener(this);
		rules.addActionListener(this);
		about.addActionListener(this);
		user.addActionListener(this);
		del.addActionListener(this);
		imExcel.addActionListener(this);
		exExcel.addActionListener(this);
		txtWord.addActionListener(this);
		rank.addActionListener(this);

		if (LoginUI.userNameGame.equals("123")) {
			toolBar.setVisible(true);
		} else {
			toolBar.setVisible(false);
		}

		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			class beginThrea extends Thread {
				public void run() {
					new Begin();
				}
			}
			new beginThrea().start();
		} else if (e.getSource() == overGame) {
			System.exit(0);
		} else if (e.getSource() == rules) {
			ruleFile r1 = ruleFile.getRuleFile();
		} else if (e.getSource() == begin) {
			class beginThrea extends Thread {
				public void run() {
					new Begin();
				}
			}
			new beginThrea().start();
		} else if (e.getSource() == about) {
			new gameData();
		} else if (e.getSource() == rank) {
			new rankFile();
		} else if (e.getSource() == user) {
			new userData();
		} else if (e.getSource() == del) {
			int n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫע����", "��ʾ", JOptionPane.YES_NO_CANCEL_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				String sql = "delete from user where user='" + LoginUI.userNameGame + "'";
				String sql1 = "delete from user_game where user='" + LoginUI.userNameGame + "'";
				String sql2 = "delete from login where user='" + LoginUI.userNameGame + "'";
				dao.deleteUserInfo(sql);
				dao.deleteUserInfo(sql1);
				dao.deleteUserInfo(sql2);
				JOptionPane.showMessageDialog(null, "��ɾ��");
				frame.setVisible(false);
				frame.dispose();
			} else if (n == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(new JFrame(), "��ȡ��");
			}
		} else if (e.getSource() == imExcel) {
			ExcelTest test = new ExcelTest();
			test.importExcel();
		} else if (e.getSource() == exExcel) {
			ExcelTest test = new ExcelTest();
			test.exportExcel();
		} else if (e.getSource() == txtWord) {
			try {
				Desktop desk = Desktop.getDesktop();
				File file = new File("file/2018401227-�ܹ���-������.docx");
				desk.open(file);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}