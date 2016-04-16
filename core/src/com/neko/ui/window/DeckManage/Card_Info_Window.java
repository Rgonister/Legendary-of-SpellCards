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
		this.addActor(cover);
		bg.setColor(40, 40, 40, 0.5f);
		this.addActor(bg);
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
		Actor a = new FontActor(data.NAME,1000,500,"st30");
		this.addActor(a);

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
