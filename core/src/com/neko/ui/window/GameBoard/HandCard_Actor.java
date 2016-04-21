package com.neko.ui.window.GameBoard;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.neko.Start;
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;

public class HandCard_Actor extends Group {
	
	private static final float x = 800;
	private static final float y = -3500;
	private static final float r = 3500;
	private static final float scale = 0.5f;

	public CardImage image;

	public CardData cd;

	public int index;

	public HandCard_Actor(CardData c, int index, int size) {
		this.index = index;
		image = (CardImage) Start.cards.get(c.ID).getActor().clone();
		image.setScale(scale);
		cd = c;
		this.addActor(image);
		this.postioncaculate(size);
	}

	private void postioncaculate(int size) {
		
		float degree = (2.8f - size * 0.1f) * (index - (size - 1f) / 2f); 
										
		int dx = (int) (r * Math.sin(degree * Math.PI / 360)
				- Math.abs(scale * 233f / 2 * Math.cos(degree * Math.PI / 360)));
		int dy = (int) (r * Math.cos(degree * Math.PI / 360) + scale * 233f / 2 * Math.sin(degree * Math.PI / 360));
		image.setRotation(-(2.8f - size * 0.1f) * (index - (size - 1f) / 2f));
		image.setPosition(x + dx, y + dy);
	}
}
