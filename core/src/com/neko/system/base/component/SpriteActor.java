package com.neko.system.base.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.neko.config.Config;

public class SpriteActor extends Actor {
	private Sprite mSprite;

	public SpriteActor(Texture tx) {
		this.mSprite = new Sprite(tx);
		setWidth(mSprite.getWidth() * Config.Scale);
		setHeight(mSprite.getHeight() * Config.Scale);
	}

	public SpriteActor(Sprite s) {
		this.mSprite = s;
		setWidth(s.getWidth() * Config.Scale);
		setHeight(s.getHeight() * Config.Scale);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		mSprite.draw(batch);
		// Color color = getColor();
		// batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		// batch.draw(mSprite, getX(), getY());
	}
}
