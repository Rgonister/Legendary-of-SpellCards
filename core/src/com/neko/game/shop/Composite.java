package com.neko.game.shop;

import com.neko.Start;
import com.neko.game.item.CardData;

public class Composite {
	public static boolean ifcomposite(CardData c) {
		boolean flag = false;
		int faith = 20;
		if (c.RARITY.equals("NORMAL"))
			faith = 20;
		else if (c.RARITY.equals("RARE"))
			faith = 100;
		else
			faith = 2000;
		if (Start.global.data.faith >= faith)
			flag = true;
		return flag;
	}

	public static void composite(CardData c) {
		int faith;
		if (c.RARITY.equals("NORMAL"))
			faith = 20;
		else if (c.RARITY.equals("RARE"))
			faith = 100;
		else
			faith = 2000;
		Start.global.data.faith -= faith;
		Start.global.data.card_no.set(c.ID, Start.global.data.card_no.get(c.ID) + 1);
		Start.global.savedata();
	}

	public static void decomposite(CardData c) {
		int faith;
		if (c.RARITY.equals("NORMAL"))
			faith = 5;
		else if (c.RARITY.equals("RARE"))
			faith = 20;
		else
			faith = 500;
		Start.global.data.faith += faith;
		Start.global.data.card_no.set(c.ID, Start.global.data.card_no.get(c.ID) - 1);
		Start.global.savedata();
	}
}
