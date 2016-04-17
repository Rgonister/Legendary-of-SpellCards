package com.neko.game.player;

import java.util.Map;
import java.util.TreeMap;

import com.neko.Start;
import com.neko.game.item.CardData;

public class Deck {
	public String DeckName;
	public String Hero;
	public Map<Integer, Integer> data = new TreeMap<Integer, Integer>();

	public Deck(String deckName, String hero) {
		this.DeckName = deckName;
		this.Hero = hero;
	}

	public void remove(Integer i) {
		if (!data.containsKey(i))
			System.out.println("移除卡组卡牌异常");
		else {
			int num = data.get(i);
			if (i == 1)
				data.remove(i);
			else
				data.put(i, num - 1);
		}
	}

	public void add(Integer i) {
		CardData c = Start.cards.get(i).data;
		if (c.GROUP.equals(Hero) || c.GROUP.equals("COMMON")) {
			int num = data.get(i);
			if (num == 0)
				data.put(i, 1);
			else if (num > 2 || (num == 1 && c.RARITY.equals("LEGENDARY"))) {
				System.out.println("--------超过上限--------");
			} else {
				data.put(i, 2);
			}
		} else {
			System.out.println("--------职业不匹配--------");
		}
	}
}
