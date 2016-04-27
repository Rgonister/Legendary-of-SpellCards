package com.neko.game.shop;

import java.util.ArrayList;
import java.util.List;

import com.neko.Start;
import com.neko.system.data.CardFilter;

public class shop {
	public static final int NORMAL = 1;

	public static List<Integer> drawcards(int status) {
		List<Integer> l = new ArrayList<Integer>();
		if (status == 1) {
			Start.global.data.gold -= 100;
			for (int i = 0; i < 5; i++) {
				Integer integ = null;
				int d = new java.util.Random().nextInt(10000);
				if (d <= 8325)
					integ = CardFilter.getCard("Normal");
				else if (d <= 9885)
					integ = CardFilter.getCard("Rare");
				else
					integ = CardFilter.getCard("Legendary");
				Start.global.data.card_no.set(integ, Start.global.data.card_no.get(integ) + 1);
				l.add(integ);
			}
		}
		Start.global.savedata();
		return l;
	}
	
	public static List<Integer> drawcardsbygroup(int status,String group) {
		List<Integer> l = new ArrayList<Integer>();
		if (status == 1) {
			Start.global.data.gold -= 120;
			for (int i = 0; i < 5; i++) {
				Integer integ = null;
				int d = new java.util.Random().nextInt(10000);
				List<String> list = new ArrayList<String>();
				list.add(group);
				if (d <= 8325)
					list.add("Normal");
				else if (d <= 9885)
					list.add("Rare");
				else
					list.add("Legendary");
				integ = CardFilter.getCard(list);
				Start.global.data.card_no.set(integ, Start.global.data.card_no.get(integ) + 1);
				l.add(integ);
			}
		}
		Start.global.savedata();
		return l;
	}
}
