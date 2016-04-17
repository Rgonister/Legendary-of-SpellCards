package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;
import com.neko.game.shop.Composite;
import com.neko.system.base.component.FontActor;
import com.neko.system.sound.SEControler;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;

public class Card_Info_Window extends Group {

	private CardData data;

	public Card_Info_Window(CardData c) {
		data = c;
		final Image cover = BackgroundUtil.getImage("gray", 1600, 900, 0.4f);
		Image bg = BackgroundUtil.getImage("black", 490, 200, 658, 525, 0.6f);
		Image bg1 = BackgroundUtil.getImage("white", 490, 200, 658, 525, 0.2f);
		cover.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Card_Info_Window cfw = DeckView_Window.getInstance().cfw;
				cfw.clear();
				cfw = null;
				DeckView_Window.getInstance().refresh();
			}
		});

		this.addActor(cover);
		this.addActor(bg);
		this.addActor(bg1);
		this.addCardImage();
		this.addCardDescription();
		this.addBottom();
	}

	private void addCardImage() {
		Actor a = new CardImage(data, false);
		a.setScale(1.3f);
		a.setPosition(505 * Config.Scale, 255 * Config.Scale);

		String name = data.NAME;
		int h = 23;
		String s = name.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
		int w = s.length() * h + (name.length() - s.length()) * 5;
		Actor a1 = new FontActor(name, Config.Scale * (1313 - w) / 2, Config.Scale * 334, "sj23");

		String str = "";
		if (data.TYPE.equals("SPELLCARD"))
			str = "Cost - " + data.COST;
		else
			str = data.COST + "-" + data.ATK + "-" + data.LIFE + " ";
		Actor a2 = new FontActor(str, Config.Scale * (1304 - 9 * str.length()) / 2, Config.Scale * 297, "st25");

		this.addActor(a);
		this.addActor(a1);
		this.addActor(a2);
	}

	private void addCardDescription() {
		String name = data.NAME;
		int h = 30;
		String s = name.replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
		int w = s.length() * h + (name.length() - s.length()) * 5;
		Actor a = new FontActor(data.NAME, Config.Scale * (980 - w / 2), 695, "sf30");
		// 846 - 1149
		this.addActor(a);
		this.addActor(new FontActor("Type:\nGroup:\nSkill:", Config.Scale * 830, Config.Scale * 666, "textur25"));
		String s1;
		if (data.TYPE.equals("SUMMON"))
			s1 = "子机";
		else if (data.TYPE.equals("SPELLCARD"))
			s1 = "符卡";
		else
			s1 = "装备";

		this.addActor(new FontActor(s1, Config.Scale * 910, Config.Scale * 657, "sf25"));
		String ss;
		if (data.RACE == null || data.RACE.length() <= 0)
			ss = "无";
		else
			ss = data.RACE;
		this.addActor(new FontActor(ss, Config.Scale * 910, Config.Scale * 629, "sf25"));

		String s2;
		String str = "";
		if (data.DIS.equals(null) || data.DIS.length() <= 0)
			s2 = "无";
		else
			s2 = data.DIS;
		int line = s2.length() / 9;
		for (int i = 0; i <= line; i++) {
			if (s2.length() >= 9) {
				str += s2.substring(0, 9);
				if (s2.length() > 9)
					str += "\n";
				s2 = s2.substring(9);
			} else
				str += s2;
		}
		this.addActor(new FontActor(str, Config.Scale * 910, Config.Scale * 601, "sf25"));

		if (!data.TYPE.equals("SPELLCARD")) {
			this.addActor(new FontActor("Cost:\nAtk:\nDef:", Config.Scale * 830, Config.Scale * (581 - 35 * line),
					"textur25"));
			this.addActor(new FontActor(String.valueOf(data.COST), Config.Scale * 910, Config.Scale * (580 - 35 * line),
					"textur25"));
			this.addActor(new FontActor(String.valueOf(data.ATK), Config.Scale * 910, Config.Scale * (551 - 35 * line),
					"textur25"));
			this.addActor(new FontActor(String.valueOf(data.LIFE), Config.Scale * 910, Config.Scale * (522 - 35 * line),
					"textur25"));
		} else {
			this.addActor(new FontActor("Cost:", Config.Scale * 830, Config.Scale * (581 - 35 * line), "textur25"));
			this.addActor(new FontActor(String.valueOf(data.COST), Config.Scale * 910, Config.Scale * (580 - 35 * line),
					"textur25"));
		}
		int num = Start.global.data.card_no.get(data.ID);
		Image img;
		if (num < 7) {
			img = ImageUtil.getImage("graphics/numbers/" + num + "w.jpg");
			img.setWidth(0.75f * img.getWidth() * Config.Scale);
			img.setHeight(35 * Config.Scale);
			img.setPosition(Config.Scale * 639, Config.Scale * 210);

		} else {
			img = ImageUtil.getImage("graphics/numbers/" + 7 + "w.jpg");
			img.setWidth(0.75f * img.getWidth() * Config.Scale);
			img.setHeight(35 * Config.Scale);
			img.setPosition(Config.Scale * 631.5f, Config.Scale * 210);
		}
		this.addActor(img);
	}

	private void addBottom() {
		final Image toCover = ImageUtil.getImage(Config.Icon_Path + "return0.png");
		toCover.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (super.touchDown(event, x, y, pointer, button)) {
					toCover.setSize(toCover.getWidth() * 0.92f, toCover.getHeight() * 0.92f);
					toCover.setPosition(toCover.getX() + toCover.getWidth() * 0.04f,
							toCover.getY() + toCover.getHeight() * 0.04f);
					return true;
				} else {
					return false;
				}
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				toCover.setSize(Config.Scale * 80, Config.Scale * 35);
				toCover.setPosition(Config.Scale * 845, Config.Scale * 210);
				Card_Info_Window cfw = DeckView_Window.getInstance().cfw;
				cfw.clear();
				cfw = null;
				DeckView_Window.getInstance().refresh();
			}
		});
		toCover.setPosition(Config.Scale * 1030, Config.Scale * 210);
		this.addActor(toCover);

		Image decompose;
		if (Start.global.data.card_no.get(data.ID) <= 0)
			decompose = ImageUtil.getImage("graphics/icon/2b.jpg");
		else {
			decompose = ImageUtil.getImage("graphics/icon/2w.jpg");
			decompose.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Composite.decomposite(data);
					DeckView_Window.getInstance().cfw = new Card_Info_Window(data);
					DeckView_Window.getInstance().refresh();
				}
			});
		}
		decompose.setPosition(Config.Scale * 930, Config.Scale * 210);

		Image composite;
		if (Composite.ifcomposite(data)) {
			composite = ImageUtil.getImage("graphics/icon/1w.jpg");
			composite.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Composite.composite(data);
					DeckView_Window.getInstance().cfw = new Card_Info_Window(data);
					DeckView_Window.getInstance().refresh();
				}
			});
		} else
			composite = ImageUtil.getImage("graphics/icon/1b.jpg");
		composite.setPosition(Config.Scale * 830, Config.Scale * 210);

		this.addActor(decompose);
		this.addActor(composite);
	}

}
