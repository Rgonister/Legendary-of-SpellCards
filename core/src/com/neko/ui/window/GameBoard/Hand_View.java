package com.neko.ui.window.GameBoard;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.game.item.CardData;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class Hand_View extends Group {

	private static final float x = 800;
	private static final float y = -1300;
	private static final float r = 1300;
	private static final float scale = 0.5f;

	public Hand_View(List<CardData> l) {
		int length = l.size();
		for (int i = 0; i < length; i++) {
			Image border = ImageUtil.getImage("graphics/card/border.png");
			border.setScale(scale);
			positoncaculate(border, (10 - length * 0.5f) * (i - (length - 1f) / 2f));
			border.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
			Image img = ImageUtil.getImage(l.get(i).picPath);
			img.setScale(scale);
			positoncaculate(img, (10 - length * 0.5f) * (i - (length - 1f) / 2f));
			img.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
			this.addActor(img);
			this.addActor(border);
			String name = l.get(i).NAME;
			int h = 18;
			String s = name.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
			int w = s.length() * h + (name.length() - s.length()) * 5;
			Actor a = new FontActor(name, (233 - w) / 2, 61, "SJ");
			positoncaculate(a, (10 - length * 0.5f) * (i - (length - 1f) / 2f));
			a.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
			this.addActor(a);
		}
	}

	private void positoncaculate(Actor img, double degree) {
		float dx = (float) (r * Math.sin(degree * Math.PI / 360)
				- Math.abs(scale * img.getWidth() / 2 * Math.cos(degree * Math.PI / 360)));
		float dy = (float) (r * Math.cos(degree * Math.PI / 360)
				+ scale * img.getWidth() / 2 * Math.sin(degree * Math.PI / 360));
		img.setPosition(x + dx, y + dy);
	}
}