package com.neko.ui.window.GameBoard;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.neko.game.duel.Game;
import com.neko.game.item.CardData;
import com.neko.util.ImageUtil;

public class GameBoard_Window extends Group {

	private static GameBoard_Window instance = null;

	public static Game game;

	public ArrayList<HandCard_Actor> myhand = new ArrayList<HandCard_Actor>();

	public static GameBoard_Window getInstance() {
		if (instance == null) {
			synchronized (GameBoard_Window.class) {
				if (instance == null) {
					instance = new GameBoard_Window();
				}
			}
		}
		return instance;
	}

	private GameBoard_Window() {
	}

	public void refresh() {
		this.clear();

		Image myHero = ImageUtil.getImage("graphics/deck/" + Game.player_me.Hero + ".jpg");
		myHero.setPosition(0, 0);
		this.addActor(myHero);

		Image opHero = ImageUtil.getImage("graphics/deck/" + Game.player_op.Hero + ".jpg");
		opHero.setPosition(1250, 740);
		this.addActor(opHero);

		if (Game.player_me.mydeck.size() > 0) {
			Image img = ImageUtil.getImage("graphics/card/back.png");
			img.setPosition(1367, 80);
			this.addActor(img);
		}
		if (Game.player_op.mydeck.size() > 0) {
			Image img = ImageUtil.getImage("graphics/card/back.png");
			img.setPosition(0, 470);
			this.addActor(img);
		}		
		
		// ---------º”‘ÿ ÷≈∆«¯-----------
		// this.addActor(new Hand_View(Game.player_me.hand));
		for (HandCard_Actor a : myhand) {
			this.addActor(a);
		}

		Group g = new Hand_View(Game.player_op.hand);
		g.setPosition(g.getX(), g.getY() + 750);
		this.addActor(g);
		// this.addActor(new Hand_View(Game.player_op.hand));


		game.check();
	}

	public static void init(Game g) {
		GameBoard_Window.game = g;
	}

	public void handrefresh() {
		myhand.clear();
		List<CardData> l = Game.player_me.hand;
		for (int i = 0; i < l.size(); i++) {
			final HandCard_Actor a = new HandCard_Actor(l.get(i), i, l.size());
			a.addListener(new DragListener() {
				@Override
				public void dragStart(InputEvent event, float x, float y, int pointer) {
					a.setColor(1, 1, 1, 0.5f);
					a.startdragx = x - a.image.getX();
					a.startdragy = y - a.image.getY();
					a.image.setRotation(0f);
				}

				@Override
				public void drag(InputEvent event, float x, float y, int pointer) {
					a.image.setPosition(x - a.startdragx, y - a.startdragy);
				}

				@Override
				public void dragStop(InputEvent event, float x, float y, int pointer) {
					a.setColor(1, 1, 1, 1f);
					a.reset();
				}
			});
			myhand.add(a);
		}
		refresh();
	}

}
