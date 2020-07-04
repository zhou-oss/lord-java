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
	// 定义组件
	JFrame frame = new JFrame("斗地主");
	JPanel panel = new JPanel();
	Image image;
	// 抢地主按钮
	JButton landlord[] = new JButton[2];
	// 出牌按钮
	JButton publishCard[] = new JButton[2];
	// 地主牌
	List<Card> lordList;
	// 定义3个玩家手牌
	List<Card> playerList[] = new ArrayList[3];
	// 当前的出牌
	List<Card> currentList[] = new ArrayList[3];
	// 定义54张牌
	Card card[] = new Card[60];
	// 计时器
	JTextField time[] = new JTextField[3];

	Countdown countdown;
	int lordFlag;// 地主标志
	int now;// 出牌人
	boolean nextPlayer = false; // 转换角色

	JLabel lordlbl = new JLabel(new ImageIcon("pics/dizhu.gif"));
	public Begin() {
		daoInit();
		Init();// 初始化容器
		setInit();// 初始化所有按钮
		CardInit();// 发牌
		getLord();// 游戏开始-->抢地主
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
		// 设置panel背景
		image = new ImageIcon("pics" + File.separator + "bg.jpg").getImage();
		panel = new BackgroundPanel(image);

		panel.setLayout(null);

		panel.add(lordlbl);
		lordlbl.setBounds(400, 200, 40, 40);
		lordlbl.setVisible(false);

		frame.setContentPane(panel);
		frame.setLocation(100, 100);// 位置居中
		frame.setSize(870, 600);// 设置大小
		frame.setVisible(true);// 容器可见性
		frame.setResizable(false);
	}

	public void CardInit() {
		int count = 1;
		// 初始化牌
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2))
					break;
				else {
					String name = i + "-" + j;// 卡牌的名字
					card[count] = new Card(frame, name, false);
					card[count].setLocation(350, 50);
					panel.add(card[count]);
					count++;
				}
			}
		}
		// 洗牌
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			int a = random.nextInt(54) + 1;
			int b = random.nextInt(54) + 1;
			Card k = card[a];
			card[a] = card[b];
			card[b] = k;
		}
		// 地主牌三张数组
		lordList = new ArrayList<Card>();
		// 三个玩家牌数组
		for (int i = 0; i < 3; i++)
			playerList[i] = new ArrayList<Card>();
		// 开始发牌
		int t = 0;
		for (int i = 1; i <= 54; i++) {
			if (i >= 52)// 第52 53 54三张牌为地主牌
			{
				Remove.move(card[i], card[i].getLocation(), new Point(300 + (i - 52) * 80, 10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++) % 3) {
			case 0:// 左边AI
				Remove.move(card[i], card[i].getLocation(), new Point(50, 60 + i * 5));
				playerList[0].add(card[i]);
				break;
			case 1:// 玩家自己
				Remove.move(card[i], card[i].getLocation(), new Point(180 + i * 7, 450));
				playerList[1].add(card[i]);
				card[i].turnFront(); // 玩家自己的牌显示正面
				break;
			case 2:// 右边AI
				Remove.move(card[i], card[i].getLocation(), new Point(700, 60 + i * 5));
				playerList[2].add(card[i]);
				break;
			}
			panel.setComponentZOrder(card[i], 0);
		}
		// 发完牌排序，从大到小
		for (int i = 0; i < 3; i++) {
			Remove.sortCard(playerList[i]);// 玩家手里的牌进行排序
			Remove.rePosition(panel, playerList[i], i);// 重新放个位置
		}
	}

	public void setInit() {

		landlord[0] = new JButton("抢地主");
		landlord[1] = new JButton("不     抢");

		publishCard[0] = new JButton("出牌");
		publishCard[1] = new JButton("不要");
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
			time[i] = new JTextField("倒计时:");
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
			time[1].setText("抢地主");
			countdown.isRun = false; // 时钟终结
		} else if (e.getSource() == landlord[1]) {
			time[1].setText("不抢");
			countdown.isRun = false; // 时钟终结
		}
		// 如果是不要
		if (e.getSource() == publishCard[1]) {
			this.nextPlayer = true;
			currentList[1].clear();
			time[1].setText("不要");
		}
		// 如果是出牌按钮
		if (e.getSource() == publishCard[0]) {
			List<Card> c = new ArrayList<Card>();
			// 点选出牌
			for (int i = 0; i < playerList[1].size(); i++) {
				Card card = playerList[1].get(i);
				if (card.clicked) {
					c.add(card);
				}
			}
			int flag = 0;

			// 如果我主动出牌
			if (time[0].getText().equals("不要") && time[2].getText().equals("不要")) {
				if (Remove.jugdeType(c) != CardType.c0)
					flag = 1;// 表示可以出牌
			} // 如果我跟牌
			else {
				flag = Remove.checkCards(c, currentList, this);
			}
			// 判断是否符合出牌
			if (flag == 1) {
				currentList[1] = c;
				playerList[1].removeAll(currentList[1]);// 移除走的牌
				// 定位出牌
				Point point = new Point();
				point.x = (770 / 2) - (currentList[1].size() + 1) * 15 / 2;
				;
				point.y = 300;
				for (int i = 0, len = currentList[1].size(); i < len; i++) {
					Card card = currentList[1].get(i);
					Remove.move(card, card.getLocation(), point);
					point.x += 15;
				}
				// 抽完牌后重新整理牌
				Remove.rePosition(panel, playerList[1], 1);
				time[1].setVisible(false);
				this.nextPlayer = true;
			}
		}
	}
}
