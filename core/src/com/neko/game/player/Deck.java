package com.neko.game.player;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.neko.Start;
import com.neko.game.item.CardData;

public class Deck implements Serializable{
	
	private static final long serialVersionUID = 8495720839174513076L;
	
	public String DeckName;
	public String Hero;
	public Map<Integer, Integer> data = new TreeMap<Integer, Integer>();
	public int number = 0;

	public Deck(String deckName, String hero) {
		this.DeckName = deckName;
		this.Hero = hero;
	}

	public boolean remove(Integer i) {
		if (!data.containsKey(i)) {
			System.out.println("移除卡组卡牌异常");
			return false;
		} else {
			int num = data.get(i);
			if (num == 1)
				data.remove(i);
			else
				data.put(i, num - 1);
		}
		number -= 1;
		return true;
	}

	public boolean add(Integer i) {
		if (number >= 30) {
			System.out.println("--------超过上限--------");
			return false;
		}
		CardData c = Start.cards.get(i).data;
		if (c.GROUP.equals(Hero) || c.GROUP.equals("COMMON")) {
			if (!data.keySet().contains(i)) {
				data.put(i, 1);
				number += 1;
			} else if (data.get(i) >= 2 || (data.get(i) == 1 && c.RARITY.equals("LEGENDARY"))) {
				System.out.println("--------超过上限--------");
			} else {
				number += 1;
				data.put(i, 2);
			}
		} else {
			System.out.println("--------职业不匹配--------");
		}
		return true;
	}
}
