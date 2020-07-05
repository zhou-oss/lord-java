package com;

import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class AICardType.AI出牌
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class AICardType {
	
	/**
	 * Ai 3.
	 *
	 * @param model the model
	 * @param player the player
	 * @param list the list
	 * @param role the role
	 */
	// 顺子
	public static void AI_3(List<String> model, List<Card> player, List<String> list, int role) {

		for (int i = 0, len = model.size(); i < len; i++) {
			String[] s = model.get(i).split(",");
			if (s.length == player.size() && getValueInt(model.get(i)) > Remove.getValue(player.get(0))) {
				list.add(model.get(i));
				return;
			}
		}
	}

	/**
	 * Ai 4.
	 *
	 * @param model1 the model 1
	 * @param model2 the model 2
	 * @param player the player
	 * @param list the list
	 * @param role the role
	 */
	// 飞机带单，双
	public static void AI_4(List<String> model1, List<String> model2, List<Card> player, List<String> list, int role) {
		// 排序按重复数
		player = Remove.getOrder2(player);
		int len1 = model1.size();
		int len2 = model2.size();

		if (len1 < 1 || len2 < 1)
			return;
		for (int i = 0; i < len1; i++) {
			String[] s = model1.get(i).split(",");
			String[] s2 = model2.get(0).split(",");
			if ((s.length / 3 <= len2) && (s.length * (3 + s2.length) == player.size())
					&& getValueInt(model1.get(i)) > Remove.getValue(player.get(0))) {
				list.add(model1.get(i));
				for (int j = 1; j <= s.length / 3; j++)
					list.add(model2.get(len2 - j));
			}
		}
	}

	/**
	 * Ai 5.
	 *
	 * @param model1 the model 1
	 * @param model2 the model 2
	 * @param player the player
	 * @param list the list
	 * @param role the role
	 */
	// 4带1，2
	public static void AI_5(List<String> model1, List<String> model2, List<Card> player, List<String> list, int role) {
		// 排序按重复数
		player = Remove.getOrder2(player);
		int len1 = model1.size();
		int len2 = model2.size();

		if (len1 < 1 || len2 < 2)
			return;
		for (int i = 0; i < len1; i++) {
			if (getValueInt(model1.get(i)) > Remove.getValue(player.get(0))) {
				list.add(model1.get(i));
				for (int j = 1; j <= 2; j++)
					list.add(model2.get(len2 - j));
			}
		}
	}

	/**
	 * Ai 1.
	 *
	 * @param model the model
	 * @param player the player
	 * @param list the list
	 * @param role the role
	 */
	// 单牌，对子，3个，4个,通用
	public static void AI_1(List<String> model, List<Card> player, List<String> list, int role) {

		for (int len = model.size(), i = len - 1; i >= 0; i--) {
			if (getValueInt(model.get(i)) > Remove.getValue(player.get(0))) {
				list.add(model.get(i));
				break;
			}
		}

	}

	/**
	 * Ai 2.
	 *
	 * @param model1 the model 1
	 * @param model2 the model 2
	 * @param player the player
	 * @param list the list
	 * @param role the role
	 */
	// 3带1,2,4带1,2
	public static void AI_2(List<String> model1, List<String> model2, List<Card> player, List<String> list, int role) {
		// model1是主牌,model2是带牌,player是玩家出的牌,,list是准备回的牌
		// 排序按重复数
		player = Remove.getOrder2(player);
		int len1 = model1.size();
		int len2 = model2.size();
		// 如果有王直接炸了
		if (len1 > 0 && model1.get(0).length() < 10) {
			list.add(model1.get(0));
			System.out.println("王炸");
			return;
		}
		if (len1 < 1 || len2 < 1)
			return;
		for (int len = len1, i = len - 1; i >= 0; i--) {
			if (getValueInt(model1.get(i)) > Remove.getValue(player.get(0))) {
				list.add(model1.get(i));
				break;
			}
		}
		list.add(model2.get(len2 - 1));
		if (list.size() < 2)
			list.clear();
	}
	
	/**
	 * Gets the value int.
	 *
	 * @param n the n
	 * @return the value int
	 */
	public static int getValueInt(String n) {
		String name[] = n.split(",");
		String s = name[0];
		int i = Integer.parseInt(s.substring(2, s.length()));
		if (s.substring(0, 1).equals("5"))
			i += 3;
		if (s.substring(2, s.length()).equals("1")
				|| s.substring(2, s.length()).equals("2"))
			i += 13;
		return i;
	}


}
