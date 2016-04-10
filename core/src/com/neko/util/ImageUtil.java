package com.neko.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
		image.setSize(image.getWidth() * Config.Scale, image.getHeight() * Config.Scale);
		return image;
	}

	public static Image resize(Image image, boolean b) {
		image.setSize(Config.Window_Size_Width, Config.Window_Size_Height);
		return image;
	}

	public static Image getImage(String Path) {
		return resize(new Image(getTexture(Path)));
	}	

	public static Image getImage(String Path, boolean b) {
		return resize(new Image(getTexture(Path)), b);
	}
}
