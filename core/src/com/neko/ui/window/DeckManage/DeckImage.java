package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.game.player.Deck;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class DeckImage extends Group {

	public Deck deck;
	public int id = -1;

	public DeckImage(Deck d) {
		this.deck = d;
		this.refresh();
	};

	public void refresh() {
		this.clear();
		Image hero = ImageUtil.getImage("graphics/deck/" + deck.Hero + ".jpg");
		hero.setPosition(1185f, 670f);
		this.addActor(hero);
		if (deck.data == null)
			return;
		String name = deck.DeckName;
		this.addActor(new FontActor(name, Config.Scale * 1190f, Config.Scale * 735f, "textur55"));
		int f = deck.number > 9 ? 15 : 0;
		this.addActor(new FontActor(deck.number + "/30", Config.Scale * (1470f - f), Config.Scale * 830f, "textur32"));
		int count = 0;
		for (Integer i : deck.data.keySet()) {
			count++;
			this.addActor(new FontActor(String.valueOf(Start.cards.get(i).data.NAME), Config.Scale * 1200f,
					Config.Scale * (660f - 35 * count), "sf25"));
		}
	}

}
