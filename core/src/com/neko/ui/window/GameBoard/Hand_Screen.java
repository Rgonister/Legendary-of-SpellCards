package com.neko.ui.window.GameBoard;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.game.item.Card;
import com.neko.util.ImageUtil;

public class Hand_Screen extends Group {

	private List<Card> hand;

	public Hand_Screen() {
		for (int i = 0; i < 5; i++) {
			Image border = ImageUtil.getImage("graphics/card/border.png");
			border.setScale(0.6f);
			border.setPosition(150 * i, 100);
			
			//Math.cos(arg0)
			
			Image img = ImageUtil.getImage("graphics/card/72.jpg");
			img.setScale(0.6f);
			img.setPosition(150 * i, 100);
			this.addActor(img);
			this.addActor(border);
		}
	}
}
