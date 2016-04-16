package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.config.Config;
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;
import com.neko.system.base.component.FontActor;
import com.neko.system.sound.SEControler;
import com.neko.util.ImageUtil;

public class Card_Info_Window extends Group {

	private CardData data;

	public Card_Info_Window(CardData c) {
		data = c;
		Image cover = new Image(ImageUtil.getTexture("graphics/StoryMode/gray.png"));
		cover.setWidth(1600 * Config.Scale);
		cover.setHeight(900 * Config.Scale);
		cover.setColor(125, 125, 125, 0.4f);
		Image bg = new Image(ImageUtil.getTexture("graphics/StoryMode/black.png"));
		bg.setWidth(658 * Config.Scale);
		bg.setHeight(525 * Config.Scale);
		bg.setPosition(490 * Config.Scale, 200 * Config.Scale);
		bg.setColor(40, 40, 40, 0.6f);

		Image bg1 = new Image(ImageUtil.getTexture("graphics/StoryMode/white.png"));
		bg1.setWidth(658 * Config.Scale);
		bg1.setHeight(525 * Config.Scale);
		bg1.setPosition(490 * Config.Scale, 200 * Config.Scale);
		bg1.setColor(40, 40, 40, 0.2f);

		this.addActor(cover);
		this.addActor(bg);
		this.addActor(bg1);
		this.addCardImage();
		this.addCardDescription();
		this.addBotton();
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
		if (data.TYPE.equals("SUMMON"))			s1 = "子机";
		else if (data.TYPE.equals("SPELLCARD"))	s1 = "符卡";
		else									s1 = "装备";				
	
		this.addActor(new FontActor(s1, Config.Scale * 910, Config.Scale * 657, "sf25"));
		String ss;
		if (data.RACE == null || data.RACE.length() <= 0) 	ss = "无";
		else 												ss = data.RACE;
		this.addActor(new FontActor(ss, Config.Scale * 910, Config.Scale * 629, "sf25"));

		String s2;
		String str = "";
		if (data.DIS.equals(null) || data.DIS.length() <= 0) 	s2 = "无";
		else 													s2 = data.DIS;
		int line = s2.length()/9;
		for(int i = 0;i <= line;i++){
			if(s2.length()	>= 9){
				str += s2.substring(0, 9)+"\n";
				s2 = s2.substring(9);
			}else
				str += s2;			
		}
		s2.replace("\\.", " ");
		this.addActor(new FontActor(str, Config.Scale * 910, Config.Scale * 601, "sf25"));
	}

	private void addBotton() {
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
				toCover.setPosition(Config.Scale * 845, Config.Scale * 65);
				Card_Info_Window cfw = DeckView_Window.getInstance().cfw;
				cfw.clear();
				cfw = null;
				DeckView_Window.getInstance().refresh();
			}
		});
		toCover.setPosition(Config.Scale * 845, Config.Scale * 210);
		this.addActor(toCover);

	}
}
