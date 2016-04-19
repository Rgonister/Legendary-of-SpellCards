package com.neko.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;

public class ImageUtil {
	// private static int route = 0; // 0-------internal 1-------local

	public static Texture getTexture(String Path) {
		Texture tx = new Texture(Path);
		if (Config.Filter_Linear)
			tx.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return tx;
	}

	public static Texture getTexture(String Path, TextureFilter txFilter) {
		Texture tx;
		tx = new Texture(Path);
		tx.setFilter(txFilter, txFilter);
		return tx;

	}

	public static Image resize(Image image) {
		image.setSize(image.getWidth(), image.getHeight());
		return image;
	}

	public static Image resize(Image image, boolean b) {
		image.setSize(1600 * Config.ScaleX, 900 * Config.ScaleY);
		return image;
	}

	public static Image getImage(String Path) {
		return new Image(getTexture(Path));
	}

	public static Image getImage(String Path, boolean b) {
		return resize(new Image(getTexture(Path)), b);
	}

	public static Image setpostion(Image image, float X, float Y) {
		image.setPosition(X, Y);
		return image;
	}

	public static Actor setpostion(Actor actor, float X, float Y) {
		actor.setPosition(X, Y);
		return actor;
	}
}
