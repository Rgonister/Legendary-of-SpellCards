package com.neko.system.base.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.neko.util.LazyBitmapFont;

public class FontActor extends Actor {

	private static String font = "SJ";
	private static int fontsize = 20;
	private String s;
	private static LazyBitmapFont lbf = new LazyBitmapFont(new FreeTypeFontGenerator(Gdx.files.internal(font + ".ttf")),
			fontsize);

	public FontActor(String s, float X, float Y) {
		this.s = s;
		this.setX(X);
		this.setY(Y);
	}

	public void draw(Batch batch, float parentAlpha) {
		lbf.draw(batch, s, this.getX(), this.getY());
	}

	public static void setfont(String fstring) {
		font = fstring;
		lbf = new LazyBitmapFont(new FreeTypeFontGenerator(Gdx.files.internal(font + ".ttf")), fontsize);
	}

	public static void setfontsize(int size) {
		fontsize = size;
		lbf = new LazyBitmapFont(new FreeTypeFontGenerator(Gdx.files.internal(font + ".ttf")), fontsize);
	}

	public void dispose() {
		lbf.dispose();
	}

	public void setcolor(Color c) {
		LazyBitmapFont.c = c;
	}
}
