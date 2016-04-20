package com.neko.ui.window.GameBoard;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.game.duel.Game;
import com.neko.game.duel.Player;
import com.neko.game.player.Deck;
import com.neko.system.base.component.FontActor;
import com.neko.ui.screen.Screen_GameBoard;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class DeckSelecter_Window extends Group {
	private static DeckSelecter_Window instance = null;

	public static DeckSelecter_Window getInstance() {
		if (instance == null) {
			synchronized (GameBoard_Window.class) {
				if (instance == null) {
					instance = new DeckSelecter_Window();
				}
			}
		}
		return instance;
	}

	private DeckSelecter_Window() {
		this.refresh();
	}

	public void refresh() {
		this.clear();
		Image bg = BackgroundUtil.getImage("black", 400, 200, 800, 525, 0.6f);
		Image bg1 = BackgroundUtil.getImage("white", 400, 200, 800, 525, 0.2f);
		
		this.addActor(bg);
		this.addActor(bg1);
		
		ArrayList<Deck> ds = Start.global.decks.data;
		for (int i = 0; i < ds.size(); i++) {
			Deck d = ds.get(i);
			Deckimage di = new Deckimage(d);
			if (i < 2)
				di.setPosition(440 + 380 * i, 460);
			else
				di.setPosition(440 + 380 * (i - 2), 290);
			this.addActor(di);
		}		
		this.addActor(new FontActor("Deck Select", 688, 700, "textur55"));
		
		final Image toCover = ImageUtil.getImage(Config.Icon_Path + "return0.png");
		toCover.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				Game g = new Game();
				GameBoard_Window.init(g);
				Screen_GameBoard.g= GameBoard_Window.getInstance();	
				GameBoard_Window.getInstance().refresh();
				Screen_GameBoard.getInstance().show();
			}
	

		});
		toCover.setPosition(1030, 210);
		this.addActor(toCover);
	}

	class Deckimage extends Group {
		public Deckimage(final Deck d) {
			Image hero = ImageUtil.getImage("graphics/deck/" + d.Hero + ".jpg");
			String name = d.DeckName;
			this.addActor(hero);
			this.addActor(new FontActor(name, 5f, 63f, "textur55"));
			this.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					Game.player_me = new Player(d);
				}

			});
		}
	}
}
