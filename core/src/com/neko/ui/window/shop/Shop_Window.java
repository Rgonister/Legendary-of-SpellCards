package com.neko.ui.window.shop;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.game.item.CardImage;
import com.neko.ui.screen.Screen_Shop;
import com.neko.util.ImageUtil;

public class Shop_Window extends Group {
	private static Shop_Window instance = null;

	public List<Actor> cards = new ArrayList<Actor>();

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
		for (Actor a : cards) {
			this.addActor(a);
		}
		this.addbuttom();
	}

	private void addbuttom() {

		final Image back;

		back = ImageUtil.getImage(Config.Icon_Path + "confirm.png");
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Screen_Shop.g = null;
				Screen_Shop.getInstance().show();
			}
		});

		back.setScale(0.75f);
		back.setPosition(750, 150);
		this.addActor(back);
	}

	public void showCards(List<Integer> l) {
		cards = new ArrayList<Actor>();
		int num = 0;
		for (Integer i : l) {
			Actor a = new CardImage(Start.cards.get(i).data);
			Cardgroup cg = new Cardgroup(a);
			cg.setPosition(200 + 250 * num, 300);
			cards.add(cg);
			num += 1;
		}
		this.refresh();
	}

	class Cardgroup extends Group {
		public Actor card;
		public Actor Back;
		public Boolean flag = false;

		public Cardgroup(Actor a) {
			card = a;
			refresh(this);
		}

		public void refresh(final Cardgroup g) {
			if (!flag) {
				Image img = ImageUtil.getImage("graphics/card/back.png");

				ClickListener c = new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						Action act = Actions.scaleTo(0.01f, 1f, 0.5f);
						Action move = Actions.moveBy(116.5f, 0, 0.5f);
						ParallelAction Paction = Actions.parallel(act, move);
						Action end = Actions.run(new Runnable() {
							@Override
							public void run() {
								g.flag = true;
								g.refresh(g);
							}
						});
						SequenceAction seq = Actions.sequence(Paction, end);
						g.addAction(seq);
					}
				};
				img.addListener(c);
				this.addActor(img);
			} else {
				this.addActor(card);
				Action act = Actions.scaleTo(1f, 1f, 0.5f);
				Action move = Actions.moveBy(-116.5f, 0, 0.5f);
				ParallelAction Paction = Actions.parallel(act, move);
				this.addAction(Paction);
			}
		}

	}
}
