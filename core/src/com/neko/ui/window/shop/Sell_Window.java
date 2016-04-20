package com.neko.ui.window.shop;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;

public class Sell_Window extends Group {
	private static Sell_Window instance = null;

	public static Sell_Window getInstance() {
		if (instance == null) {
			synchronized (Sell_Window.class) {
				if (instance == null) {
					instance = new Sell_Window();
				}
			}
		}
		return instance;
	}

	private Sell_Window() {
		this.refresh();
	};

	public void refresh() {
		Image bg = BackgroundUtil.getImage("gray", 50, 45, 1030, 795, 0.65f);
		this.addActor(bg);
		Image i1 = ImageUtil.getImage("graphics/shop/all.png");
		i1.setPosition(200, 400);
		this.addActor(i1);
		Image i2 = ImageUtil.getImage("graphics/shop/shanghai.png");
		i2.setPosition(600, 400);
		i2.setScale(1.6f);
		this.addActor(i2);
		Image i3 = ImageUtil.getImage("graphics/shop/fairy.png");
		i3.setPosition(1000, 400);
		i3.setScale(0.8f);
		this.addActor(i3);
	}
}
