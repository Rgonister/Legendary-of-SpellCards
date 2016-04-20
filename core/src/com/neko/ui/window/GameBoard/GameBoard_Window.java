package com.neko.ui.window.GameBoard;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.game.duel.Game;
import com.neko.util.ImageUtil;

public class GameBoard_Window extends Group {

	private static GameBoard_Window instance = null;

	public static Game game;

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

		// ---------¼ÓÔØÊÖÅÆÇø-----------
		this.addActor(new Hand_View(Game.player_me.hand));
		Group g = new Hand_View(Game.player_op.hand);
		g.setPosition(g.getX(), g.getY() + 750);
		this.addActor(g);
		// this.addActor(new Hand_View(Game.player_op.hand));
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

		game.check();
	}

	public static void init(Game g) {
		GameBoard_Window.game = g;
	}

}
