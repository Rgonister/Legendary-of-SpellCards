package com.neko.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.neko.config.Config;
import com.neko.system.base.component.Screen_Window;
import com.neko.ui.window.shop.Sell_Window;
import com.neko.util.ImageUtil;

public class Screen_Shop extends Screen_Window {
	private static Screen_Shop instance = null;

	public static Group g;
	
	public static Screen_Shop getInstance() {
		if (instance == null) {
			synchronized (Screen_Shop.class) {
				if (instance == null) {
					instance = new Screen_Shop();
				}
			}
		}
		return instance;
	}

	private Screen_Shop() {
		g = Sell_Window.getInstance();
	};

	@Override
	public void show() {
		stage = new Stage();
		stage.addActor(ImageUtil.getImage("graphics/bg.jpg", false));
		g.setScale(Config.ScaleX,Config.ScaleY);
		stage.addActor(g);
		Gdx.input.setInputProcessor(stage);
	}
}
