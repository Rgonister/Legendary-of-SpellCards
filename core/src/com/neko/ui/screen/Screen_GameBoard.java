package com.neko.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.neko.config.Config;
import com.neko.system.base.component.Screen_Window;
import com.neko.ui.window.GameBoard.Hand_Screen;

public class Screen_GameBoard extends Screen_Window {
	private static Screen_GameBoard instance = null;

	public static Screen_GameBoard getInstance() {
		if (instance == null) {
			synchronized (Screen_GameBoard.class) {
				if (instance == null) {
					instance = new Screen_GameBoard();
				}
			}
		}
		return instance;
	}

	private Screen_GameBoard() {
	}
	
	@Override
	public void show() {
		stage = new Stage();
		Hand_Screen hs = new Hand_Screen();
		hs.setScale(Config.ScaleX, Config.ScaleY);
		stage.addActor(hs);
		
		Gdx.input.setInputProcessor(stage);
	}
}
