package com.neko.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.neko.system.base.component.Screen_Window;
import com.neko.ui.window.DeckManage.DeckView_Window;
import com.neko.util.ImageUtil;

public class Screen_DeckManage extends Screen_Window {
	private static Screen_DeckManage instance = null;

	public static Screen_DeckManage getInstance() {
		if (instance == null) {
			synchronized (Screen_DeckManage.class) {
				if (instance == null) {
					instance = new Screen_DeckManage();
				}
			}
		}
		return instance;
	}

	private Screen_DeckManage() {
	}
	
	@Override
	public void show() {
		stage = new Stage();
		stage.addActor(ImageUtil.getImage("graphics/bg.jpg", false));
		stage.addActor(DeckView_Window.getInstance());
		Gdx.input.setInputProcessor(stage);
	}
}
