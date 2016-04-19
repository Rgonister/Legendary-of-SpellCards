package com.neko.ui.window.DeckManage;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.game.player.Deck;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class MyDeck_Window extends Group {

	private static MyDeck_Window instance = null;

	public static MyDeck_Window getInstance() {
		if (instance == null) {
			synchronized (DeckView_Window.class) {
				if (instance == null) {
					instance = new MyDeck_Window();
				}
			}
		}
		return instance;
	}

	private MyDeck_Window() {
	}

	public void refresh() {
		this.clear();
		final List<Deck> ds = Start.global.decks.data;
		for (int i = 0; i < ds.size(); i++) {
			final int num = i;

			Image hero = ImageUtil.getImage("graphics/deck/" + ds.get(i).Hero + ".jpg");
			hero.setPosition(1185f, 670f - 175 * i);
			this.addActor(hero);
			this.addActor(new FontActor(ds.get(i).DeckName, 1190f, 735f - 175 * i, "textur55"));

			hero.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					DeckView_Window dvw = DeckView_Window.getInstance();
					if (ds.get(num).Hero.equals("ALICE"))
						dvw.cfilter.set(0, "Alice");
					else if (ds.get(num).Hero.equals("CIRNO"))
						dvw.cfilter.set(0, "Cirno");
					else
						dvw.cfilter.set(0, "Pachi");
					dvw.deckImage = new DeckImage(ds.get(num));
					dvw.deckImage.id = num;
					dvw.editmode = true;
					dvw.refresh();
				}
			});
		}
	}

}
