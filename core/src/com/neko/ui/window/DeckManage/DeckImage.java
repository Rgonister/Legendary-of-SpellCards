package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.game.player.Deck;
import com.neko.system.base.component.FontActor;
import com.neko.system.base.component.ShortCut;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

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
		this.addActor(new FontActor(name, 1190f,735f, "textur55"));
		int f = deck.number > 9 ? 15 : 0;
		this.addActor(new FontActor(deck.number + "/30",1470f - f,830f, "textur32"));
		int count = 0;
		for (Integer i : deck.data.keySet()) {
			count++;
			ShortCut img = new ShortCut(Start.cards.get(i).data, deck.data.get(i));
			if (count <= 15)
				img.setPosition(1185f, 665f - 37 * count);
			else
				img.setPosition(1360, 665f - 37 * (count-15));
			final Integer id = Start.cards.get(i).ID;
			img.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					SEControler.play(1, "Click");
					DeckView_Window.getInstance().deckImage.deck.remove(id);
					DeckView_Window.getInstance().deckImage.refresh();
					DeckView_Window.getInstance().refresh();
				}
			});
			this.addActor(img);
		}
	}

}
