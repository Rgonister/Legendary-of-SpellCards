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
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;
import com.neko.game.shop.shop;
import com.neko.system.base.component.FontActor;
import com.neko.ui.screen.Screen_Shop;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class Sell_Window extends Group {
	private static Sell_Window instance = null;

	public static Sell_Window getInstance() {
		if (instance == null) {
			synchronized (Sell_Window.class) {
				if (instance == null) {
					instance = new Sell_Window();
				}
			}
		}
		return instance;
	}

	private Sell_Window() {
		this.refresh();
	};

	public void refresh() {
		this.addActor(BackgroundUtil.getImage("gray", 75, 75, 700, 350, 0.65f));
		this.addActor(BackgroundUtil.getImage("gray", 75, 475, 700, 350, 0.65f));
		this.addActor(BackgroundUtil.getImage("gray", 825, 75, 700, 350, 0.65f));
		this.addActor(BackgroundUtil.getImage("gray", 825, 475, 700, 350, 0.65f));

		addcover();
		addsells();
		addcostimg();

		Image toCover = ImageUtil.getImage(Config.Icon_Path + "return0.png");
		toCover.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				Sell_Window.getInstance().clear();
				Screen_Shop.g = null;
				Start.windowstate = WindowState.Cover;
			}
		});
		toCover.setPosition(1445, 30);
		this.addActor(toCover);
	}

	static class cardactor extends Group {
		public static String border = "graphics/card/";
		public static float scale = 0.85f;

		public cardactor(CardData data, float x, float y) {
			this.addActor(ImageUtil.getImage(data.picPath));
			this.addActor(ImageUtil.getImage(border + data.RARITY + ".png"));
			this.setPosition(x, y);
			this.setScale(scale);
		}
	}

	private void addcover() {
		Image i1 = ImageUtil.getImage("graphics/shop/all.png");
		i1.setPosition(90, 520);
		i1.setScale(0.85f);
		this.addActor(i1);
		this.addActor(new FontActor("经典收集组", 117, 507, "sj28"));
		Actor i2 = new cardactor(Start.cards.get(18).data, 90, 120);
		this.addActor(i2);
		this.addActor(new FontActor("妖精集结组", 117, 107, "sj28"));
		Actor i3 = new cardactor(Start.cards.get(1).data, 840, 520);
		this.addActor(i3);
		this.addActor(new FontActor("人偶战争组", 867, 507, "sj28"));
		Actor i4 = new cardactor(Start.cards.get(86).data, 840, 120);
		this.addActor(i4);
		this.addActor(new FontActor("符卡咏唱组", 867, 107, "sj28"));
	}

	private void addsells() {
		Actor img;

		img = ImageUtil.getImage(Config.Icon_Path + "all.png");
		img.setPosition(400, 525);
		this.addActor(img);

		img = new CardImage(Start.cards.get(19).data);
		img.setScale(0.85f);
		img.setPosition(350, 120);
		this.addActor(img);
		img = new CardImage(Start.cards.get(90).data);
		img.setScale(0.85f);
		img.setPosition(560, 120);
		this.addActor(img);

		img = new CardImage(Start.cards.get(7).data);
		img.setScale(0.85f);
		img.setPosition(1100, 520);
		this.addActor(img);
		img = new CardImage(Start.cards.get(14).data);
		img.setScale(0.85f);
		img.setPosition(1310, 520);
		this.addActor(img);

		img = new CardImage(Start.cards.get(28).data);
		img.setScale(0.85f);
		img.setPosition(1100, 120);
		this.addActor(img);
		img = new CardImage(Start.cards.get(41).data);
		img.setScale(0.85f);
		img.setPosition(1310, 120);
		this.addActor(img);

		this.addActor(new FontActor("可以获得", 500, 107, "sj28"));
		this.addActor(new FontActor("可以获得", 1250, 107, "sj28"));
		this.addActor(new FontActor("可以获得", 500, 507, "sj28"));
		this.addActor(new FontActor("可以获得", 1250, 507, "sj28"));
	}

	private void addcostimg() {
		Image img1 = ImageUtil.getImage(Config.Icon_Path + "100.jpg");
		img1.setPosition(139, 643.75f);
		img1.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				List<Integer> l = shop.drawcards(shop.NORMAL);
				Shop_Window.getInstance().showCards(l);
				Screen_Shop ss = Screen_Shop.getInstance();
				Screen_Shop.g = Shop_Window.getInstance();
				ss.show();
			}

		});
		this.addActor(img1);

		Image img2 = ImageUtil.getImage(Config.Icon_Path + "120.jpg");
		img2.setPosition(139, 243.75f);
		img2.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				List<Integer> l = shop.drawcardsbygroup(shop.NORMAL, "Yaosei");
				Shop_Window.getInstance().showCards(l);
				Screen_Shop ss = Screen_Shop.getInstance();
				Screen_Shop.g = Shop_Window.getInstance();
				ss.show();
			}

		});
		this.addActor(img2);

		Image img3 = ImageUtil.getImage(Config.Icon_Path + "120.jpg");
		img3.setPosition(889, 643.75f);
		img3.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				List<Integer> l = shop.drawcardsbygroup(shop.NORMAL, "Renxin");
				Shop_Window.getInstance().showCards(l);
				Screen_Shop ss = Screen_Shop.getInstance();
				Screen_Shop.g = Shop_Window.getInstance();
				ss.show();
			}

		});
		this.addActor(img3);

		Image img4 = ImageUtil.getImage(Config.Icon_Path + "120.jpg");
		img4.setPosition(889, 243.75f);
		img4.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				List<Integer> l = shop.drawcardsbygroup(shop.NORMAL, "SP");
				Shop_Window.getInstance().showCards(l);
				Screen_Shop ss = Screen_Shop.getInstance();
				Screen_Shop.g = Shop_Window.getInstance();
				ss.show();
			}

		});
		this.addActor(img4);
	}
}
