package com.neko.system.base.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.game.item.CardData;
import com.neko.util.ImageUtil;

public class ShortCut extends Group {

	public ShortCut(CardData c, int number) {
		Image img = new Image(new TextureRegion(ImageUtil.getTexture(c.picPath), 0, 80, 233, 60));
		img.setSize(174f, 36f);
		this.addActor(img);
		this.addActor(ImageUtil.getImage("graphics/card/borders.png"));
		this.addActor(new FontActor(String.valueOf(number), 146, 38, "textur32"));
		this.addActor(new FontActor(c.NAME, 2, 24, "sj20"));
	}

}
