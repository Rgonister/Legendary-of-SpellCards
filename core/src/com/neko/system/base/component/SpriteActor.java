package com.neko.system.base.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpriteActor extends Actor {
	private Sprite mSprite;

	public SpriteActor(Texture tx) {
		this.mSprite = new Sprite(tx);
		setWidth(mSprite.getWidth());
		setHeight(mSprite.getHeight());
	}

	public SpriteActor(Sprite s) {
		this.mSprite = s;
		setWidth(s.getWidth());
		setHeight(s.getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		mSprite.draw(batch);
	}
}
