package com.neko.util;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundUtil {

	public static Image getImage(String color, float x, float y, float width, float height, float alpha) {
		Image img = ImageUtil.getImage("graphics/StoryMode/" + color + ".png");
		img.setSize(width, height);
		img.setPosition(x, y);
		img.setColor(1, 1, 1, alpha);
		return img;
	}

	public static Image getImage(String color, float width, float height, float alpha) {
		Image img = ImageUtil.getImage("graphics/StoryMode/" + color + ".png");
		img.setSize(width, height);
		img.setColor(1, 1, 1, alpha);
		return img;
	}
}
