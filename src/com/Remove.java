package com;

import java.awt.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import org.junit.BeforeClass;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class Remove.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class Remove {
	
	/**
	 * 卡牌移动.
	 *
	 * @param 卡牌对象
	 * @param 原来坐标
	 * @param 目的坐标
	 */
	public static void move(Card card, Point from, Point to) {
		if (to.x != from.x) {
			double k = (1.0) * (to.y - from.y) / (to.x - from.x);
			double b = to.y - to.x * k;
			int flag = 0;// 判断向左还是向右移动步幅
			if (from.x < to.x)
				flag = 20;
			else {
				flag = -20;
			}
			for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
				double y = k * i + b;
				card.setLocation(i, (int) y);
				try {
					Thread.sleep(5); //发牌延迟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		// 移动位置
		card.setLocation(to);
	}

	/**
	 * 手牌排序.
	 *
	 * @param 手牌
	 */
	public static void sortCard(List<Card> list) {
		Collections.sort(list, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				int a1 = Integer.parseInt(o1.name.substring(0, 1));// 花色
				int a2 = Integer.parseInt(o2.name.substring(0, 1));
				int b1 = Integer.parseInt(o1.name.substring(2, o1.name.length()));// 数值
				int b2 = Integer.parseInt(o2.name.substring(2, o2.name.length()));
				int flag = 0;
				// 如果是王的话
				if (a1 == 5)
					b1 += 100;
				if (a1 == 5 && b1 == 1)
					b1 += 50;
				if (a2 == 5)
					b2 += 100;
				if (a2 == 5 && b2 == 1)
					b2 += 50;
				// 如果是A或者2
				if (b1 == 1)
					b1 += 20;
				if (b2 == 1)
					b2 += 20;
				if (b1 == 2)
					b1 += 30;
				if (b2 == 2)
					b2 += 30;
				flag = b2 - b1;// 根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
				if (flag == 0)
					return a2 - a1;
				else {
					return flag;
				}
			}
		});
	}

	/**
	 * 重新定位.
	 *
	 * @param 容器
	 * @param 手牌
	 * @param 玩家
	 */
	// 重新定位 flag代表电脑1 ,2 或者是我
	public static void rePosition(JPanel frame, List<Card> list, int flag) {
//		Iterator<Card> iterator = list.iterator();
//		System.out.println("====迭代器遍历===="+flag);
//		while (iterator.hasNext()) {
//			Card current = iterator.next();
//			System.out.println(current);
//		}
		Point p = new Point();
		if (flag == 0) {
			p.x = 50;
			p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
		}
		if (flag == 1) {// 我的排序 y=450 width=830
			p.x = (800 / 2) - (list.size() + 1) * 21 / 2;
			p.y = 450;
		}
		if (flag == 2) {
			p.x = 700;
			p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
		}
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Card card = list.get(i);
			Remove.move(card, card.getLocation(), p);
			frame.setComponentZOrder(card, 0);
			if (flag == 1)
				p.x += 21;
			else
				p.y += 15;
		}
	}

	/**
	 * 收牌.
	 *
	 * @param 牌集合
	 */
	public static void hideCards(List<Card> list) {
		for (int i = 0, len = list.size(); i < len; i++) {
			list.get(i).setVisible(false);
		}
	}

	/**
	 * 判断牌型.
	 *
	 * @param 牌
	 * @return 牌的类型
	 */
	// 判断牌型
	public static CardType jugdeType(List<Card> list) {
		// 因为之前排序过所以比较好判断
		int len = list.size();
		// 单牌,对子，3不带，4个一样炸弹
		if (len <= 4) { // 如果第一个和最后个相同，说明全部相同
			if (list.size() > 0 && Remove.getValue(list.get(0)) == Remove.getValue(list.get(len - 1))) {
				switch (len) {
				case 1:
					return CardType.c1;
				case 2:
					return CardType.c2;
				case 3:
					return CardType.c3;
				case 4:
					return CardType.c4;
				}
			}
			// 双王,化为对子返回
			if (len == 2 && Remove.getColor(list.get(1)) == 5)
				return CardType.c2;
			// 当第一个和最后个不同时,3带1
			if (len == 4 && ((Remove.getValue(list.get(0)) == Remove.getValue(list.get(len - 2)))
					|| Remove.getValue(list.get(1)) == Remove.getValue(list.get(len - 1))))
				return CardType.c31;
			else {
				return CardType.c0;
			}
		}
		// 当5张以上时，连字，3带2，飞机，2顺，4带2等等
		if (len >= 5) {// 现在按相同数字最大出现次数
			Card_index card_index = new Card_index();
			for (int i = 0; i < 4; i++)
				card_index.a[i] = new ArrayList<Integer>();
			// 求出各种数字出现频率
			Remove.getMax(card_index, list); // a[0,1,2,3]分别表示重复1,2,3,4次的牌
			// 3带2 -----必含重复3次的牌
			if (card_index.a[2].size() == 1 && card_index.a[1].size() == 1 && len == 5)
				return CardType.c32;
			// 4带2(单,双)
			if (card_index.a[3].size() == 1 && len == 6)
				return CardType.c411;
			if (card_index.a[3].size() == 1 && card_index.a[1].size() == 2 && len == 8)
				return CardType.c422;
			// 单连,保证不存在王
			if ((Remove.getColor(list.get(0)) != 5) && (card_index.a[0].size() == len)
					&& (Remove.getValue(list.get(0)) - Remove.getValue(list.get(len - 1)) == len - 1))
				return CardType.c123;
			// 连队
			if (card_index.a[1].size() == len / 2 && len % 2 == 0 && len / 2 >= 3
					&& (Remove.getValue(list.get(0)) - Remove.getValue(list.get(len - 1)) == (len / 2 - 1)))
				return CardType.c1122;
			// 飞机
			if (card_index.a[2].size() == len / 3 && (len % 3 == 0)
					&& (Remove.getValue(list.get(0)) - Remove.getValue(list.get(len - 1)) == (len / 3 - 1)))
				return CardType.c111222;
			// 飞机带n单,n/2对
			if (card_index.a[2].size() == len / 4 && ((Integer) (card_index.a[2].get(len / 4 - 1))
					- (Integer) (card_index.a[2].get(0)) == len / 4 - 1))
				return CardType.c11122234;

			// 飞机带n双
			if (card_index.a[2].size() == len / 5 && card_index.a[2].size() == len / 5
					&& ((Integer) (card_index.a[2].get(len / 5 - 1)) - (Integer) (card_index.a[2].get(0)) == len / 5
							- 1))
				return CardType.c1112223344;

		}
		return CardType.c0;
	}

	/**
	 * 获得花色.
	 *
	 * @param 卡牌
	 * @return 花色
	 */
	// 返回花色
	public static int getColor(Card card) {
		return Integer.parseInt(card.name.substring(0, 1));
	}

	/**
	 * 获得权值.
	 *
	 * @param 卡牌
	 * @return 值
	 */
	// 返回值
	public static int getValue(Card card) {
		int i = Integer.parseInt(card.name.substring(2, card.name.length()));
		if (card.name.substring(2, card.name.length()).equals("2"))
			i += 13;
		if (card.name.substring(2, card.name.length()).equals("1"))
			i += 13;
		if (Remove.getColor(card) == 5)
			i += 2;// 是王
		return i;
	}

	/**
	 * 获得最大相同数.
	 *
	 * @param 单牌个数
	 * @param 牌集合
	 * @return 最大相同数
	 */
	// 得到最大相同数
	public static void getMax(Card_index card_index, List<Card> list) {
		int count[] = new int[14];// 1-13各算一种,王算第14种
		for (int i = 0; i < 14; i++)
			count[i] = 0;
		for (int i = 0, len = list.size(); i < len; i++) {
			if (Remove.getColor(list.get(i)) == 5)
				count[13]++;
			else
				count[Remove.getValue(list.get(i)) - 1]++;
		}
		for (int i = 0; i < 14; i++) {
			switch (count[i]) {
			case 1:
				card_index.a[0].add(i + 1);
				break;
			case 2:
				card_index.a[1].add(i + 1);
				break;
			case 3:
				card_index.a[2].add(i + 1);
				break;
			case 4:
				card_index.a[3].add(i + 1);
				break;
			}
		}
	}

	/**
	 * 获得牌型.
	 *
	 * @param 出牌
	 * @param 花色
	 * @return 牌型
	 */
	// 拆牌
	public static Model getModel(List<Card> list, int[] orders) {
		// 先复制一个list
		List list2 = new ArrayList<Card>(list);
		Model model = new Model();
		for (int i = 0; i < orders.length; i++)
			showOrders(orders[i], list2, model);
		return model;
	}

	/**
	 * Gets the 123.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the 123
	 */
	// 拆连子
	public static void get123(List<Card> list, Model model) {
		List<Card> del = new ArrayList<Card>();// 要删除的Cards
		if (list.size() > 0 && (Remove.getValue(list.get(0)) < 7 || Remove.getValue(list.get(list.size() - 1)) > 10))
			return;
		if (list.size() < 5)
			return;
		// 先要把所有不重复的牌归为一类，防止3带，对子影响
		List<Card> list2 = new ArrayList<Card>();
		List<Card> temp = new ArrayList<Card>();
		List<Integer> integers = new ArrayList<Integer>();
		for (Card card : list2) {
			if (integers.indexOf(Remove.getValue(card)) < 0) {
				integers.add(Remove.getValue(card));
				temp.add(card);
			}
		}
		Remove.sortCard(temp);
		for (int i = 0, len = temp.size(); i < len; i++) {
			int k = i;
			for (int j = i; j < len; j++) {
				if (Remove.getValue(temp.get(i)) - Remove.getValue(temp.get(j)) == j - i) {
					k = j;
				}
			}
			if (k - i >= 4) {
				String s = "";
				for (int j = i; j < k; j++) {
					s += temp.get(j).name + ",";
					del.add(temp.get(j));
				}
				s += temp.get(k).name;
				del.add(temp.get(k));
				model.a123.add(s);
				i = k;
			}
		}
		list.removeAll(del);
	}

	/**
	 * Gets the two two.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the two two
	 */
	// 拆双顺
	public static void getTwoTwo(List<Card> list, Model model) {
		List<String> del = new ArrayList<String>();// 要删除的Cards
		// 从model里面的对子找
		List<String> l = model.a2;
		if (l.size() < 3)
			return;
		Integer s[] = new Integer[l.size()];
		for (int i = 0, len = l.size(); i < len; i++) {
			String[] name = l.get(i).split(",");
			s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
		}
		// s0,1,2,3,4 13,9,8,7,6
		for (int i = 0, len = l.size(); i < len; i++) {
			int k = i;
			for (int j = i; j < len; j++) {
				if (s[i] - s[j] == j - i)
					k = j;
			}
			if (k - i >= 2)// k=4 i=1
			{// 说明从i到k是连队
				String ss = "";
				for (int j = i; j < k; j++) {
					ss += l.get(j) + ",";
					del.add(l.get(j));
				}
				ss += l.get(k);
				model.a112233.add(ss);
				del.add(l.get(k));
				i = k;
			}
		}
		l.removeAll(del);
	}

	/**
	 * Gets the plane.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the plane
	 */
	// 拆飞机
	public static void getPlane(List<Card> list, Model model) {
		List<String> del = new ArrayList<String>();// 要删除的Cards
		// 从model里面的3带找
		List<String> l = model.a3;
		if (l.size() < 2)
			return;
		Integer s[] = new Integer[l.size()];
		for (int i = 0, len = l.size(); i < len; i++) {
			String[] name = l.get(i).split(",");
			s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
		}
		for (int i = 0, len = l.size(); i < len; i++) {
			int k = i;
			for (int j = i; j < len; j++) {
				if (s[i] - s[j] == j - i)
					k = j;
			}
			if (k != i) {// 说明从i到k是飞机
				String ss = "";
				for (int j = i; j < k; j++) {
					ss += l.get(j) + ",";
					del.add(l.get(j));
				}
				ss += l.get(k);
				model.a111222.add(ss);
				del.add(l.get(k));
				i = k;
			}
		}
		l.removeAll(del);
	}

	/**
	 * Gets the boomb.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the boomb
	 */
	// 拆炸弹
	public static void getBoomb(List<Card> list, Model model) {
		List<Card> del = new ArrayList<Card>();// 要删除的Cards
		if (list.size() < 1)
			return;
		// 王炸
		if (list.size() >= 2 && Remove.getColor(list.get(0)) == 5 && Remove.getColor(list.get(1)) == 5) {
			model.a4.add(list.get(0).name + "," + list.get(1).name); // 按名字加入
			del.add(list.get(0));
			del.add(list.get(1));
		}
		// 如果王不构成炸弹咋先拆单
		if (Remove.getColor(list.get(0)) == 5 && Remove.getColor(list.get(1)) != 5) {
			del.add(list.get(0));
			model.a1.add(list.get(0).name);
		}
		list.removeAll(del);
		// 一般的炸弹
		for (int i = 0, len = list.size(); i < len; i++) {
			if (i + 3 < len && Remove.getValue(list.get(i)) == Remove.getValue(list.get(i + 3))) {
				String s = list.get(i).name + ",";
				s += list.get(i + 1).name + ",";
				s += list.get(i + 2).name + ",";
				s += list.get(i + 3).name;
				model.a4.add(s);
				for (int j = i; j <= i + 3; j++)
					del.add(list.get(j));
				i = i + 3;
			}
		}
		list.removeAll(del);
	}

	/**
	 * Gets the three.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the three
	 */
	// 拆3带
	public static void getThree(List<Card> list, Model model) {
		List<Card> del = new ArrayList<Card>();// 要删除的Cards
		// 连续3张相同
		for (int i = 0, len = list.size(); i < len; i++) {
			if (i + 2 < len && Remove.getValue(list.get(i)) == Remove.getValue(list.get(i + 2))) {
				String s = list.get(i).name + ",";
				s += list.get(i + 1).name + ",";
				s += list.get(i + 2).name;
				model.a3.add(s);
				for (int j = i; j <= i + 2; j++)
					del.add(list.get(j));
				i = i + 2;
			}
		}
		list.removeAll(del);
	}

	/**
	 * Gets the two.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the two
	 */
	// 拆对子
	public static void getTwo(List<Card> list, Model model) {
		List<Card> del = new ArrayList<Card>();// 要删除的Cards
		// 连续2张相同
		for (int i = 0, len = list.size(); i < len; i++) {
			if (i + 1 < len && Remove.getValue(list.get(i)) == Remove.getValue(list.get(i + 1))) {
				String s = list.get(i).name + ",";
				s += list.get(i + 1).name;
				model.a2.add(s);
				for (int j = i; j <= i + 1; j++)
					del.add(list.get(j));
				i = i + 1;
			}
		}
		list.removeAll(del);
	}

	/**
	 * Gets the single.
	 *
	 * @param list the list
	 * @param model the model
	 * @return the single
	 */
	// 拆单牌
	public static void getSingle(List<Card> list, Model model) {
		List<Card> del = new ArrayList<Card>();// 要删除的Cards
		// 1
		for (int i = 0, len = list.size(); i < len; i++) {
			model.a1.add(list.get(i).name);
			del.add(list.get(i));
		}
		list.removeAll(del);
	}

	/**
	 * Check cards.
	 *
	 * @param c the c
	 * @param current the current
	 * @param m the m
	 * @return the int
	 */
	// 检查牌的是否能出
	public static int checkCards(List<Card> c, List<Card>[] current, Begin m) {
		// 找出当前最大的牌是哪个电脑出的,c是点选的牌
		List<Card> currentlist;
		if (m.time[0].getText().equals("不要"))
			currentlist = current[2];
		else
			currentlist = current[0];
		CardType cType = Remove.jugdeType(c);
		CardType cType2 = Remove.jugdeType(currentlist);
		// 如果张数不同直接过滤
		if (cType != CardType.c4 && c.size() != currentlist.size())
			return 0;
		// 比较我的出牌类型
		if (cType != CardType.c4 && Remove.jugdeType(c) != Remove.jugdeType(currentlist)) {

			return 0;
		}
		// 比较出的牌是否要大
		// 我是炸弹
		if (cType == CardType.c4) {
			if (c.size() == 2)
				return 1;
			if (cType2 != CardType.c4) {
				return 1;
			}
		}

		// 单牌,对子,3带,4炸弹
		if (cType == CardType.c1 || cType == CardType.c2 || cType == CardType.c3 || cType == CardType.c4) {
			if (Remove.getValue(c.get(0)) <= Remove.getValue(currentlist.get(0))) {
				return 0;
			} else {
				return 1;
			}
		}
		// 顺子,连队，飞机裸
		if (cType == CardType.c123 || cType == CardType.c1122 || cType == CardType.c111222) {
			if (Remove.getValue(c.get(0)) <= Remove.getValue(currentlist.get(0)))
				return 0;
			else
				return 1;
		}
		// 按重复多少排序
		// 3带1,3带2 ,飞机带单，双,4带1,2,只需比较第一个就行，独一无二的
		if (cType == CardType.c31 || cType == CardType.c32 || cType == CardType.c411 || cType == CardType.c422
				|| cType == CardType.c11122234 || cType == CardType.c1112223344) {
			List<Card> a1 = Remove.getOrder2(c); // 我出的牌
			List<Card> a2 = Remove.getOrder2(currentlist);// 当前最大牌
			if (Remove.getValue(a1.get(0)) < Remove.getValue(a2.get(0)))
				return 0;
		}
		return 1;
	}

	/**
	 * Gets the order 2.
	 *
	 * @param list the list
	 * @return the order 2
	 */
	// 按照重复次数排序
	public static List getOrder2(List<Card> list) {
		List<Card> list2 = new ArrayList<Card>(list);
		List<Card> list3 = new ArrayList<Card>();
		List<Integer> list4 = new ArrayList<Integer>();
		int len = list2.size();
		int a[] = new int[20];
		for (int i = 0; i < 20; i++)
			a[i] = 0;
		for (int i = 0; i < len; i++) {
			a[Remove.getValue(list2.get(i))]++;
		}
		int max = 0;
		for (int i = 0; i < 20; i++) {
			max = 0;
			for (int j = 19; j >= 0; j--) {
				if (a[j] > a[max])
					max = j;
			}

			for (int k = 0; k < len; k++) {
				if (Remove.getValue(list2.get(k)) == max) {
					list3.add(list2.get(k));
				}
			}
			list2.remove(list3);
			a[max] = 0;
		}
		return list3;
	}

	/**
	 * Show orders.
	 *
	 * @param i the i
	 * @param list the list
	 * @param model the model
	 */
	// 拆牌循序
	public static void showOrders(int i, List<Card> list, Model model) {
		switch (i) {
		case 1:
			Remove.getSingle(list, model);
			break;
		case 2:
			Remove.getTwo(list, model);
			Remove.getTwoTwo(list, model);
			break;
		case 3:
			Remove.getThree(list, model);
			Remove.getPlane(list, model);
			break;
		case 4:
			Remove.getBoomb(list, model);
			break;
		case 5:
			Remove.get123(list, model);
			break;
		}
	}
}