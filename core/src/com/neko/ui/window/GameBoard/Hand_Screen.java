package com.neko.ui.window.GameBoard;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.game.item.Card;
import com.neko.util.ImageUtil;

public class Hand_Screen extends Group {

	private List<Card> hand;

	private static final float x = 800;
	private static final float y = -1300;
	private static final float r = 1300;
	private static final float scale = 0.5f;

	public Hand_Screen() {
		int length = 3;
		for (int i = 0; i < length; i++) {
			Image border = ImageUtil.getImage("graphics/card/border.png");
			border.setScale(scale);
			positoncaculate(border, (10 - length * 0.5f) * (i - (length - 1f) / 2f));
			border.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));

			Image img = ImageUtil.getImage("graphics/card/72.jpg");
			img.setScale(scale);

			positoncaculate(img, (10 - length * 0.5f) * (i - (length - 1f) / 2f));
			img.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
			this.addActor(img);
			this.addActor(border);
		}
	}

	private void positoncaculate(Image img, double degree) {
		float dx = (float) (r * Math.sin(degree * Math.PI / 360)
				- Math.abs(scale * img.getWidth() / 2 * Math.cos(degree * Math.PI / 360)));
		float dy = (float) (r * Math.cos(degree * Math.PI / 360)
				+ scale * img.getWidth() / 2 * Math.sin(degree * Math.PI / 360));
		img.setPosition(x + dx, y + dy);
	}
}