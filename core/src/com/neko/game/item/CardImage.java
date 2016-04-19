package com.neko.game.item;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class CardImage extends Group {

	public static String border = "graphics/card/";
	public boolean flag = false;

	public CardImage(CardData data) {
		String name = data.NAME;
		this.addActor(ImageUtil.getImage(data.picPath));
		this.addActor(ImageUtil.getImage(border + data.RARITY + ".png"));
		int h = 18;
		String s = name.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
		int w = s.length() * h + (name.length() - s.length()) * 5;
		Actor a = new FontActor(name, (233 - w) / 2, 61, "SJ");
		this.addActor(a);
		FontActor.addlbf("st", 20, "st20");
		String str = "";
		if (data.TYPE.equals("SPELLCARD"))
			str = "Cost - " + data.COST;
		else
			str = data.COST + "-" + data.ATK + "-" + data.LIFE + " ";
		Actor act = new FontActor(str, (231 - 9 * str.length()) / 2, 33, "st20");
		this.addActor(act);
	}

	public CardImage(CardData data, boolean flag) {
		this.addActor(ImageUtil.getImage(data.picPath));
		this.addActor(ImageUtil.getImage(border + data.RARITY + ".png"));
	}

}
