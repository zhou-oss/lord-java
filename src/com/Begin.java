package com;

import java.awt.Image;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

import dao.beginDao;
import util.BackgroundPanel;
import util.getTime;

public class Begin implements ActionListener {
	beginDao dao=new beginDao();
	String num;
	String bgame;
	String ogame;
	// �������
	JFrame frame = new JFrame("������");
	JPanel panel = new JPanel();
	Image image;
	// ��������ť
	JButton landlord[] = new JButton[2];
	// ���ư�ť
	JButton publishCard[] = new JButton[2];
	// ������
	List<Card> lordList;
	// ����3���������
	List<Card> playerList[] = new ArrayList[3];
	// ��ǰ�ĳ���
	List<Card> currentList[] = new ArrayList[3];
	// ����54����
	Card card[] = new Card[60];
	// ��ʱ��
	JTextField time[] = new JTextField[3];

	Countdown countdown;
	int lordFlag;// ������־
	int now;// ������
	boolean nextPlayer = false; // ת����ɫ

	JLabel lordlbl = new JLabel(new ImageIcon("pics/dizhu.gif"));
	public Begin() {
		daoInit();
		Init();// ��ʼ������
		setInit();// ��ʼ�����а�ť
		CardInit();// ����
		getLord();// ��Ϸ��ʼ-->������
		countdown = new Countdown(this, 15);
		countdown.start();
	}
	@Test
	public void daoInit() {
		Random random=new Random();
		while(true) {
			num=String.valueOf(random.nextInt(1000));
			String sql="select * from game where number='"+num+"'";
			ResultSet rs=dao.queryUserInfo(sql);
			try {
				if(!rs.next()) {
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bgame=getTime.nowTime();
	}
	public void Init() {
		// ����panel����
		image = new ImageIcon("pics" + File.separator + "bg.jpg").getImage();
		panel = new BackgroundPanel(image);

		panel.setLayout(null);

		panel.add(lordlbl);
		lordlbl.setBounds(400, 200, 40, 40);
		lordlbl.setVisible(false);

		frame.setContentPane(panel);
		frame.setLocation(100, 100);// λ�þ���
		frame.setSize(870, 600);// ���ô�С
		frame.setVisible(true);// �����ɼ���
		frame.setResizable(false);
	}

	public void CardInit() {
		int count = 1;
		// ��ʼ����
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2))
					break;
				else {
					String name = i + "-" + j;// ���Ƶ�����
					card[count] = new Card(frame, name, false);
					card[count].setLocation(350, 50);
					panel.add(card[count]);
					count++;
				}
			}
		}
		// ϴ��
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			int a = random.nextInt(54) + 1;
			int b = random.nextInt(54) + 1;
			Card k = card[a];
			card[a] = card[b];
			card[b] = k;
		}
		// ��������������
		lordList = new ArrayList<Card>();
		// �������������
		for (int i = 0; i < 3; i++)
			playerList[i] = new ArrayList<Card>();
		// ��ʼ����
		int t = 0;
		for (int i = 1; i <= 54; i++) {
			if (i >= 52)// ��52 53 54������Ϊ������
			{
				Remove.move(card[i], card[i].getLocation(), new Point(300 + (i - 52) * 80, 10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++) % 3) {
			case 0:// ���AI
				Remove.move(card[i], card[i].getLocation(), new Point(50, 60 + i * 5));
				playerList[0].add(card[i]);
				break;
			case 1:// ����Լ�
				Remove.move(card[i], card[i].getLocation(), new Point(180 + i * 7, 450));
				playerList[1].add(card[i]);
				card[i].turnFront(); // ����Լ�������ʾ����
				break;
			case 2:// �ұ�AI
				Remove.move(card[i], card[i].getLocation(), new Point(700, 60 + i * 5));
				playerList[2].add(card[i]);
				break;
			}
			panel.setComponentZOrder(card[i], 0);
		}
		// ���������򣬴Ӵ�С
		for (int i = 0; i < 3; i++) {
			Remove.sortCard(playerList[i]);// ���������ƽ�������
			Remove.rePosition(panel, playerList[i], i);// ���·Ÿ�λ��
		}
	}

	public void setInit() {

		landlord[0] = new JButton("������");
		landlord[1] = new JButton("��     ��");

		publishCard[0] = new JButton("����");
		publishCard[1] = new JButton("��Ҫ");
		for (int i = 0; i < 2; i++) {
			publishCard[i].setBounds(320 + i * 100, 400, 75, 20);
			landlord[i].setBounds(320 + i * 100, 400, 75, 20);

			panel.add(landlord[i]);
			panel.add(publishCard[i]);

			landlord[i].addActionListener(this);
			publishCard[i].addActionListener(this);

			landlord[i].setVisible(false);
			publishCard[i].setVisible(false);
		}

		for (int i = 0; i < 3; i++) {
			time[i] = new JTextField("����ʱ:");
			time[i].setVisible(false);
			panel.add(time[i]);
		}
		time[0].setBounds(140, 230, 60, 20);
		time[1].setBounds(374, 360, 60, 20);
		time[2].setBounds(620, 230, 60, 20);

		for (int i = 0; i < 3; i++)
			currentList[i] = new ArrayList<Card>();
	}

	public void getLord() {
		time[1].setVisible(true);
		for (int i = 0; i < 2; i++)
			landlord[i].setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == landlord[0]) {
			time[1].setText("������");
			countdown.isRun = false; // ʱ���ս�
		} else if (e.getSource() == landlord[1]) {
			time[1].setText("����");
			countdown.isRun = false; // ʱ���ս�
		}
		// ����ǲ�Ҫ
		if (e.getSource() == publishCard[1]) {
			this.nextPlayer = true;
			currentList[1].clear();
			time[1].setText("��Ҫ");
		}
		// ����ǳ��ư�ť
		if (e.getSource() == publishCard[0]) {
			List<Card> c = new ArrayList<Card>();
			// ��ѡ����
			for (int i = 0; i < playerList[1].size(); i++) {
				Card card = playerList[1].get(i);
				if (card.clicked) {
					c.add(card);
				}
			}
			int flag = 0;

			// �������������
			if (time[0].getText().equals("��Ҫ") && time[2].getText().equals("��Ҫ")) {
				if (Remove.jugdeType(c) != CardType.c0)
					flag = 1;// ��ʾ���Գ���
			} // ����Ҹ���
			else {
				flag = Remove.checkCards(c, currentList, this);
			}
			// �ж��Ƿ���ϳ���
			if (flag == 1) {
				currentList[1] = c;
				playerList[1].removeAll(currentList[1]);// �Ƴ��ߵ���
				// ��λ����
				Point point = new Point();
				point.x = (770 / 2) - (currentList[1].size() + 1) * 15 / 2;
				;
				point.y = 300;
				for (int i = 0, len = currentList[1].size(); i < len; i++) {
					Card card = currentList[1].get(i);
					Remove.move(card, card.getLocation(), point);
					point.x += 15;
				}
				// �����ƺ�����������
				Remove.rePosition(panel, playerList[1], 1);
				time[1].setVisible(false);
				this.nextPlayer = true;
			}
		}
	}
}
