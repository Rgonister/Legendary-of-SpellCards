package com.neko.game.item;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.neko.config.Config;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class CardImage extends Group {

	public static String border = "graphics/card/border.png";

	public CardImage(CardData data) {
		// String atk = String.valueOf(data.atk);
		// String life = String.valueOf(data.life);
		// String cost = String.valueOf(data.COST);
		String name = data.NAME;
		this.addActor(ImageUtil.getImage(data.picPath));
		this.addActor(ImageUtil.getImage(border));
		int h = FontActor.getfontsize();
		String s = name.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
		int w = s.length() * h + (name.length() - s.length()) * 5;
		Actor a = new FontActor(name, Config.Scale * (233 - w) / 2, Config.Scale * 61);
		this.addActor(a);

		// this.addActor(new FontActor(cost,50,30));
	}
	

}
