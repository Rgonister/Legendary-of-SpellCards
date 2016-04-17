package com.neko.game.shop;

import java.util.ArrayList;
import java.util.List;

import com.neko.Start;
import com.neko.system.data.CardFilter;

public class Shop {
	public static final int NORMAL = 1;

	public static List<Integer> drawcards(int status) {
		List<Integer> l = new ArrayList<Integer>();
		if (status == 1) {
			Start.global.data.gold -= 100;
			for (int i = 0; i < 5; i++) {
				Integer integ = null;
				double d = Math.random();
				if (d <= 0.82)
					integ = CardFilter.getCard("Normal");
				else if (d <= 0.99)
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
}
