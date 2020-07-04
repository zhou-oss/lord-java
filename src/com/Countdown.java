package com;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import dao.gameDataDao;
import util.getTime;
import view.LoginUI;

public class Countdown extends Thread {
	gameDataDao dao=new gameDataDao();
	private Begin begin;
	private int time;
	boolean isRun = true;
	int dizhu;
	AICardType ai = new AICardType();

	public Countdown(Begin begin, int time) {
		this.time = time;
		this.begin = begin;
	}

	public void run() {
		// 自己抢地主
		while (time > -1 && isRun) {
			begin.time[1].setText("倒计时:" + time--);
			begin.time[1].setEditable(false);
			Sleep(1);
		}
		// 超时即不抢
		if (time == -1)
			begin.time[1].setText("不抢");
		// 抢地主按钮影藏
		begin.landlord[0].setVisible(false);
		begin.landlord[1].setVisible(false);
		// 自己抢地主
		if (begin.time[1].getText().equals("抢地主")) {
			dizhu = 1;
			begin.playerList[1].addAll(begin.lordList);
			openlord(true);
			Sleep(3);
			Remove.sortCard(begin.playerList[1]);
			Remove.rePosition(begin.panel, begin.playerList[1], 1);
			begin.publishCard[1].setEnabled(false);
			// 不抢地主
		} else if (begin.time[1].getText().equals("不抢")) {
			Sleep(2);
			begin.time[1].setVisible(false);
			Random random = new Random();
			while (true) {
				dizhu = random.nextInt(3);
				if (dizhu == 0) {
					begin.time[2].setText("不抢");
					begin.time[2].setVisible(true);
					Sleep(2);
					begin.time[2].setVisible(false);
					begin.time[0].setText("抢地主");
					begin.time[0].setVisible(true);
					begin.playerList[0].addAll(begin.lordList);
					openlord(true);
					Sleep(3);
					Remove.sortCard(begin.playerList[0]);
					Remove.rePosition(begin.panel, begin.playerList[0], 0);
					openlord(false);
					break;
				} else if (dizhu == 2) {
					begin.time[2].setText("抢地主");
					begin.time[2].setVisible(true);
					begin.playerList[2].addAll(begin.lordList);
					openlord(true);
					Sleep(3);
					Remove.sortCard(begin.playerList[2]);
					Remove.rePosition(begin.panel, begin.playerList[2], 2);
					openlord(false);
					break;
				}
			}
		}
		// 设置地主标志
		setPicLord(dizhu);
		for (int i = 0; i < 3; i++) {
			begin.time[i].setText("不要");
			begin.time[i].setVisible(false);
		}
		// 设置牌可以被点击
		for (Card card2 : begin.playerList[1]) {
			card2.canClick = true;// 可被点击
		}

		// 游戏开始
		while (true) {
			if (begin.now == 1) // 我
			{
				// 如果我主动出牌关闭不要按钮(地主先出或者AI要不起)
				if (begin.time[0].getText().equals("不要") && begin.time[2].getText().equals("不要"))
					begin.publishCard[1].setEnabled(false);
				else {
					begin.publishCard[1].setEnabled(true);
				}
				turnOn(true);// 出牌按钮 --我出牌
				timeWait(20, 1);// 我自己的定时器
				turnOn(false);// 选完关闭
				begin.now = (begin.now + 1) % 3;
				if (win()) {// 判断输赢
					daoInit1();
					break;
				}
			}
			if (begin.now == 0) {
				computer(0);
				begin.now = (begin.now + 1) % 3;
				if (win()) {// 判断输赢
					daoInit();
					break;
				}
			}
			if (begin.now == 2) {
				computer(2);
				begin.now = (begin.now + 1) % 3;
				if (win()) {// 判断输赢
					daoInit();
					break;
				}
			}
		}
	}
	public void daoInit1() {
		Random a = new Random();
		int b = a.nextInt(1000) + 1000;
		String getScore = String.valueOf(b);
		begin.ogame = getTime.nowTime();
		String sql = "insert into game values(?,?,?,?,?)";
		begin.dao.saveUserInfo(sql, begin.num, getScore, begin.bgame, 
				begin.ogame,LoginUI.userNameGame);
	}
	public void daoInit() {
		Random a = new Random();
		int b = a.nextInt(1000) + 1000;
		String getScore = "-"+String.valueOf(b);
		begin.ogame = getTime.nowTime();
		String sql = "insert into game values(?,?,?,?,?)";
		begin.dao.saveUserInfo(sql, begin.num, getScore, begin.bgame, 
				begin.ogame,LoginUI.userNameGame);
	}
	// 地主牌翻看
	public void openlord(boolean is) {
		for (int i = 0; i < 3; i++) {
			if (is)
				begin.lordList.get(i).turnFront(); // 地主牌翻看
			else {
				begin.lordList.get(i).turnRear(); // 地主牌闭合
			}
		}
	}

	public void computer(int a) {
		timeWait(1, a); // 定时
		ShowCard(a); // 出牌
	}

	public void ShowCard(int role) {
		int orders[] = new int[] { 4, 3, 2, 1, 5 };
		Model model = Remove.getModel(begin.playerList[role], orders);
		// 待走的牌
		List<String> list = new ArrayList();
		// 如果是主动出牌
		if (begin.time[(role + 1) % 3].getText().equals("不要") && begin.time[(role + 2) % 3].getText().equals("不要")) {
			// 有顺子出顺子
			if (model.a123.size() > 0) {
				list.add(model.a123.get(model.a123.size() - 1));
			} // 有3带就出3带，没有就出光3
			else if (model.a3.size() > 0) {
				// 3带1,且非关键时刻不能带王，2
				if (model.a1.size() > 0) {
					list.add(model.a1.get(model.a1.size() - 1));
				} // 3带2
				else if (model.a2.size() > 0) {
					list.add(model.a2.get(model.a2.size() - 1));
				}
				list.add(model.a3.get(model.a3.size() - 1));
			} // 有双顺出双顺
			else if (model.a112233.size() > 0) {
				list.add(model.a112233.get(model.a112233.size() - 1));
			} // 有飞机出飞机
			else if (model.a111222.size() > 0) {
				String name[] = model.a111222.get(0).split(",");
				// 带单
				if (name.length / 3 <= model.a1.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a1.get(i));
				} // 带双
				else if (name.length / 3 <= model.a2.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a2.get(i));
				}

			} // 有单出单 (除开3带，飞机能带的单牌)
			else if (model.a1.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a1.get(model.a1.size() - 1));
			} // 有对子出对子 (除开3带，飞机)
			else if (model.a2.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a2.get(model.a2.size() - 1));
			} // 有炸弹出炸弹
			else if (model.a4.size() > 0) {
				// 4带2,1
				int sizea1 = model.a1.size();
				int sizea2 = model.a2.size();
				if (sizea1 >= 2) {
					list.add(model.a1.get(sizea1 - 1));
					list.add(model.a1.get(sizea1 - 2));
					list.add(model.a4.get(0));

				} else if (sizea2 >= 2) {
					list.add(model.a2.get(sizea1 - 1));
					list.add(model.a2.get(sizea1 - 2));
					list.add(model.a4.get(0));

				} else {// 直接炸
					list.add(model.a4.get(0));

				}

			}
		} // 如果是跟牌
		else {
			// 如果队友牌大则不出

			if (role != begin.lordFlag) {
				int f = 0;
				if (begin.time[begin.lordFlag].getText().equals("不要")) {
					f = 1;
				} // 如果我的下家是地主，且我的上家出的比较大了
				if ((role + 1) % 3 == begin.lordFlag) {
					if ((Remove.jugdeType(begin.currentList[(role + 2) % 3]) != CardType.c1
							|| Remove.jugdeType(begin.currentList[(role + 2) % 3]) != CardType.c2)
							&& begin.currentList[begin.lordFlag].size() < 1)
						f = 1;
					if (begin.currentList[(role + 2) % 3].size() > 0
							&& Remove.getValue(begin.currentList[(role + 2) % 3].get(0)) > 13)
						f = 1;
				}
				if (f == 1) {
					begin.time[role].setVisible(true);
					begin.time[role].setText("不要");
					return;
				}
			}
			// 拆牌打
			int can = 0;
			if (role == begin.lordFlag)// 如果自己是地主
			{// 当对手牌小于5张的时候炸
				if (begin.playerList[(role + 1) % 3].size() <= 5 || begin.playerList[(role + 2) % 3].size() <= 5)
					can = 1;
			} else {// 当地主牌小于5张的时候炸
				if (begin.playerList[begin.lordFlag].size() <= 5)
					can = 1;
			}
			// 如果can=1就要拆牌model得重新来
			List<Card> player;
			if (begin.time[(role + 2) % 3].getText().equals("不要"))
				player = begin.currentList[(role + 1) % 3];
			else
				player = begin.currentList[(role + 2) % 3];

			CardType cType = Remove.jugdeType(player);
			// 如果是单牌
			if (cType == CardType.c1) {
				if (can == 1)
					model = Remove.getModel(begin.playerList[role], new int[] { 1, 4, 3, 2, 5 });
				AICardType.AI_1(model.a1, player, list, role);
			} // 如果是对子
			else if (cType == CardType.c2) {
				if (can == 1)
					model = Remove.getModel(begin.playerList[role], new int[] { 2, 4, 3, 5, 1 });
				AICardType.AI_1(model.a2, player, list, role);
			} // 3带
			else if (cType == CardType.c3) {
				AICardType.AI_1(model.a3, player, list, role);
			} // 炸弹
			else if (cType == CardType.c4) {
				AICardType.AI_1(model.a4, player, list, role);
			} // 如果是3带1
			else if (cType == CardType.c31) {
				if (can == 1)
					model = Remove.getModel(begin.playerList[role], new int[] { 3, 1, 4, 2, 5 });
				AICardType.AI_2(model.a3, model.a1, player, list, role);
			} // 如果是3带2
			else if (cType == CardType.c32) {
				if (can == 1)
					model = Remove.getModel(begin.playerList[role], new int[] { 3, 2, 4, 5, 1 });
				AICardType.AI_2(model.a3, model.a2, player, list, role);
			} // 如果是4带11
			else if (cType == CardType.c411) {
				AICardType.AI_5(model.a4, model.a1, player, list, role);
			}
			// 如果是4带22
			else if (cType == CardType.c422) {
				AICardType.AI_5(model.a4, model.a2, player, list, role);
			}
			// 顺子
			else if (cType == CardType.c123) {
				if (can == 1)
					model = Remove.getModel(begin.playerList[role], new int[] { 5, 3, 2, 4, 1 });
				AICardType.AI_3(model.a123, player, list, role);
			}
			// 双顺
			else if (cType == CardType.c1122) {
				if (can == 1)
					model = Remove.getModel(begin.playerList[role], new int[] { 2, 4, 3, 5, 1 });
				AICardType.AI_3(model.a112233, player, list, role);
			}
			// 飞机带单
			else if (cType == CardType.c11122234) {
				AICardType.AI_4(model.a111222, model.a1, player, list, role);
			}
			// 飞机带对
			else if (cType == CardType.c1112223344) {
				AICardType.AI_4(model.a111222, model.a2, player, list, role);
			}
			if (list.size() == 0 && can == 1) {
				int len4 = model.a4.size();
				if (len4 > 0)
					list.add(model.a4.get(len4 - 1));
			}
		}

		// 定位出牌
		begin.currentList[role].clear();
		if (list.size() > 0) {
			Point point = new Point();
			if (role == 0)
				point.x = 200;
			if (role == 2)
				point.x = 550;
			if (role == 1) {
				point.x = (770 / 2) - (begin.currentList[1].size() + 1) * 15 / 2;
				point.y = 300;
			}
			point.y = (400 / 2) - (list.size() + 1) * 15 / 2;// 屏幕中部
			// 将name转换成Card
			// 把要出的牌放到里面
			List<Card> temp = new ArrayList<Card>();
			for (int i = 0, len = list.size(); i < len; i++) {
				List<Card> cards = getCardByName(begin.playerList[role], list.get(i));
				for (Card card : cards) {
					temp.add(card);
				}
			}
			temp = Remove.getOrder2(temp);
			for (Card card : temp) {
				Remove.move(card, card.getLocation(), point);
				point.y += 15;
				begin.panel.setComponentZOrder(card, 0);
				begin.currentList[role].add(card);
				begin.playerList[role].remove(card);
			}
			Remove.rePosition(begin.panel, begin.playerList[role], role);
		} else {
			begin.time[role].setVisible(true);
			begin.time[role].setText("不要");
		}
		for (Card card : begin.currentList[role])
			card.turnFront();
	}

	// 按name获得Card，方便从Model取出
	public List getCardByName(List<Card> list, String n) {
		String[] name = n.split(",");
		List cardsList = new ArrayList<Card>();
		int j = 0;
		for (int i = 0, len = list.size(); i < len; i++) {
			if (j < name.length && list.get(i).name.equals(name[j])) {
				cardsList.add(list.get(i));
				i = 0;
				j++;
			}
		}
		return cardsList;
	}

	// 延时，模拟时钟
	public void timeWait(int n, int player) {

		if (begin.currentList[player].size() > 0)
			Remove.hideCards(begin.currentList[player]);
		if (player == 1) {// 如果是我，20秒到后直接下一家出牌
			int i = n;

			while (begin.nextPlayer == false && i >= 0) {
				begin.time[player].setText("倒计时:" + i);
				begin.time[player].setVisible(true);
				Sleep(1);
				i--;
			}
			if (i == -1 && player == 1) {// 如果我超时
				ShowCard(1);
			}
			begin.nextPlayer = false;
		} else {
			for (int i = n; i >= 0; i--) {
				Sleep(1);
				begin.time[player].setText("倒计时:" + i);
				begin.time[player].setVisible(true);
			}
		}
		begin.time[player].setVisible(false);
	}

	// 判断输赢
	public boolean win() {
		for (int i = 0; i < 3; i++) {
			if (begin.playerList[i].size() == 0) {
				String s;
				if (i == 1) {
					s = "恭喜你，胜利了!";
				} else {
					s = "恭喜电脑" + i + ",赢了! 你的智商有待提高哦";
				}
				for (int j = 0; j < begin.playerList[(i + 1) % 3].size(); j++)
					begin.playerList[(i + 1) % 3].get(j).turnFront();
				for (int j = 0; j < begin.playerList[(i + 2) % 3].size(); j++)
					begin.playerList[(i + 2) % 3].get(j).turnFront();
				JOptionPane.showMessageDialog(null, s);
				return true;
			}
		}
		return false;
	}

	public void setPicLord(int a) {
		Point p = new Point();
		if (a == 1) {
			p.x = 120;
			p.y = 480;
		} else if (a == 0) {
			p.x = 60;
			p.y = 25;
		} else if (a == 2) {
			p.x = 720;
			p.y = 25;
		}
		begin.lordFlag = a;
		begin.now = a;
		begin.lordlbl.setLocation(p.x, p.y);
		begin.lordlbl.setVisible(true);
	}

	public void Sleep(int a) {
		try {
			Thread.sleep(a * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 打开出牌按钮
	public void turnOn(boolean flag) {
		begin.publishCard[0].setVisible(flag);
		begin.publishCard[1].setVisible(flag);
	}
}
