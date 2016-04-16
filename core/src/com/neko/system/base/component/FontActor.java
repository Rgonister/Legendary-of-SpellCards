package com.neko.system.base.component;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.neko.util.LazyBitmapFont;

public class FontActor extends Actor {

	private static String font = "SJ";
	private static int fontsize = 18;
	private String s;
	private LazyBitmapFont lbf;
	private static Map<String, LazyBitmapFont> Bitfont = new HashMap<String, LazyBitmapFont>();

	public FontActor(String s, float X, float Y, String key) {
		this.s = s;
		this.setX(X);
		this.setY(Y);
		if (!Bitfont.containsKey(key)) {
			LazyBitmapFont lbfs = new LazyBitmapFont(new FreeTypeFontGenerator(Gdx.files.internal(font + ".ttf")),
					fontsize);
			Bitfont.put(key, lbfs);
		}
		lbf = Bitfont.get(key);

	}

	public void draw(Batch batch, float parentAlpha) {
		lbf.draw(batch, s, this.getX(), this.getY());
	}

	public static void addlbf(String fstring, int size, String key) {
		LazyBitmapFont lbfs = new LazyBitmapFont(new FreeTypeFontGenerator(Gdx.files.internal(fstring + ".ttf")),
				size);
		Bitfont.put(key, lbfs);
	}

	public void dispose() {
		lbf.dispose();
	}

	public void setcolor(Color c) {
		LazyBitmapFont.c = c;
	}

	public static int getfontsize() {
		return fontsize;
	}

}
