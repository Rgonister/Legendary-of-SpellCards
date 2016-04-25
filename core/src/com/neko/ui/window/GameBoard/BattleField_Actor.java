package com.neko.ui.window.GameBoard;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.neko.game.item.CardData;
import com.neko.system.base.component.FontActor;
import com.neko.util.ImageUtil;

public class BattleField_Actor extends Group {

	public CardData data;
	public Actor actor;
	public static final int width = 250;
	public float scale = 0.65f;
	public float ox;
	public float oy;

	public BattleField_Actor(CardData c, int index, int owner) {
		data = c;
		actor = new Summon(c);
		float size;
		if (owner == 0)
			size = GameBoard_Window.getInstance().mysummon.size();
		else
			size = GameBoard_Window.getInstance().opsummon.size();
		ox = (width * scale) * (index - 0.5f - size / 2f) + 800;
		oy = 202.5f + owner * 247.5f;
		actor.setScale(scale);
		resetposition();
		this.addActor(actor);
	}

	public void setalpha(float f) {
		actor.setColor(1, 1, 1, f);
	}

	static class Summon extends Group {
		// 需要重写，先暂时用着
		public static String border = "graphics/card/";

		public Summon(CardData data) {
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
	}

	public void resetposition() {
		actor.setPosition(ox, oy);
	}
}
