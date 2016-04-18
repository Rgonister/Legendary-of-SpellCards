package com.neko.ui.window.shop;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.config.enums.WindowState;
import com.neko.game.shop.Shop;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class Shop_Window extends Group {
	private static Shop_Window instance = null;

	public static Shop_Window getInstance() {
		if (instance == null) {
			synchronized (Shop_Window.class) {
				if (instance == null) {
					instance = new Shop_Window();
				}
			}
		}
		return instance;
	}

	private Shop_Window() {
		this.refresh();
	}

	public void refresh() {
		this.clear();
		this.addbuttom();
	}

	private void addbuttom() {
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
				Shop_Window.instance.clear();
				Start.windowstate = WindowState.Cover;
			}
		});
		toCover.setPosition(Config.Scale * 845, Config.Scale * 210);
		this.addActor(toCover);

		final Image back;

		back = ImageUtil.getImage(Config.Icon_Path + "goback0.png");
		back.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (super.touchDown(event, x, y, pointer, button)) {
					back.setSize(back.getWidth() * 0.92f, back.getHeight() * 0.92f);
					back.setPosition(back.getX() + back.getWidth() * 0.04f, back.getY() + back.getHeight() * 0.04f);
					return true;
				} else {
					return false;
				}
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				List<Integer> l = Shop.drawcards(Shop.NORMAL);
				showCards(l);
				back.setSize(Config.Scale * 80, Config.Scale * 35);
				back.setPosition(Config.Scale * 125, Config.Scale * 65);
			}
		});

		back.setPosition(Config.Scale * 125, Config.Scale * 65);
		this.addActor(back);
	}

	private void showCards(List<Integer> l) {
		int num = 0;
		for (Integer i : l) {
			Actor a = Start.cards.get(i).getActor();
			System.out.println(Start.cards.get(i).data.RARITY+"  ");
			a.setPosition(200 + 250 * num, 300);
			this.addActor(a);
			num += 1;
		}

	}
}
