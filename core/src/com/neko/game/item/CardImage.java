package com.neko.game.item;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class CardImage extends Group{

	public CardImage(CardData data){
//		String atk = String.valueOf(data.atk);
//		String life = String.valueOf(data.life);
		String cost = String.valueOf(data.cost);
		
		this.addActor(ImageUtil.getImage(data.picPath));
		this.addActor(new FontActor(cost,50,30));
	}
}
