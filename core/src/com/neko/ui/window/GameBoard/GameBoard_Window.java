package com.neko.ui.window.GameBoard;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.neko.game.duel.Game;

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
		//---------º”‘ÿ ÷≈∆«¯-----------
		this.addActor(new Hand_View(Game.player_me.hand));
		this.addActor(new Hand_View(Game.player_op.hand));
	}

	public static void init(Game g) {
		GameBoard_Window.game = g;
		GameBoard_Window.getInstance();
	}
	
}
