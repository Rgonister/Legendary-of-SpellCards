package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;
import com.neko.game.player.Deck;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class DeckImage extends Group {

	public Deck deck;

	public DeckImage(Deck d) {
		this.deck = d;
		Image hero = ImageUtil.getImage("graphics/deck/" + d.Hero + ".jpg");
		hero.setPosition(1200f, 700f);
		this.addActor(hero);
		if (d.data == null)
			return;
		int count = 0;
		for (Integer i : d.data.keySet()) {
			count++;
			this.addActor(new FontActor(String.valueOf(d.data.get(i)), Config.Scale * 1200f,
					Config.Scale * (1200f - 35 * count), "st30"));
		}
	};

}
