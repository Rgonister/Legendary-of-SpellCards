package com.neko.ui.button.Cover;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;
import com.neko.util.ImageUtil;

public class Button_Options extends Image {

	public Button_Options() {
		super(ImageUtil.getTexture("graphics/Menu/options.png"));
		this.setPosition();
	}

	private void setPosition() {
		ImageUtil.resize(this);
		this.setX((Config.Window_Size_Width - this.getWidth()) / 2);
		this.setY(Config.Scale * 140);
	}
}
