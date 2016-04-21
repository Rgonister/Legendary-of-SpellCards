package com.neko.game.duel.period;

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
import com.neko.game.duel.Game;
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;
import com.neko.ui.window.GameBoard.GameBoard_Window;
import com.neko.util.ImageUtil;

public class Game_Start extends Period {
	static List<CardData> l;
	static int[] inter;
	static List<SelectorImage> lsi = new ArrayList<SelectorImage>();
	static boolean flag = true;

	public void act() {
		acting = true;
		System.out.println("gamestart init");
		Game.player_op.drawCard();
		Game.player_op.drawCard();
		Game.player_op.drawCard();

		l = new ArrayList<CardData>();
		l.add(Game.player_me.mydeck.get(29));
		l.add(Game.player_me.mydeck.get(28));
		l.add(Game.player_me.mydeck.get(27));

		inter = new int[] { 1, 1, 1 };

		GameBoard_Window.getInstance().refresh();

		initSelectorImage(0);

		final Actor a = ImageUtil.getImage("graphics/icon/lost.png");
		a.setPosition(725, 160);
		a.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Game_Start.flag = false;
				int count = 0;
				for (int i = 0; i <= 2; i++) {
					if (Game_Start.inter[i] == -1) {
						GameBoard_Window.getInstance().removeActor(a);
						CardData c = Game.player_me.mydeck.get(26 - count);
						l.set(i, c);
						count++;
						Game_Start.lsi.get(i).goback = true;
						Game_Start.lsi.get(i).flag = false;
						Game_Start.lsi.get(i).refresh(Game_Start.lsi.get(i));
					}
				}
			}
		});
		GameBoard_Window.getInstance().addActor(a);
	}

	public static void initSelectorImage(int count) {
		if (count <= 2) {
			SelectorImage si = new SelectorImage(l.get(count), 1367, 80, (413.5f + 270 * count), 275, count);
			lsi.add(si);
			GameBoard_Window.getInstance().addActor(si);
		}
	}

	public static void drawcontrol() {
		Game.player_me.hand.addAll(l);
		for (int i = 0; i <= 2; i++) {
			if (inter[i] == 1)
				Game.player_me.mydeck.remove(29 - i);
		}

		int num = Game.player_me.mydeck.size() - 27;
		for (int i = 0; i < num; i++) {
			Game.player_me.mydeck.remove(26 - i);
		}
		for (SelectorImage si : lsi) {
			si.clear();
		}
		System.out.println(Game.player_me.hand.size()+"------------");
		for (int i = 0; i <= 2; i++) {
			handImage a = new handImage(Game.player_me.hand.get(i), i);
			GameBoard_Window.getInstance().addActor(a);
			a.act();
		}

	}

	static class SelectorImage extends Group {
		public Actor card;
		public Actor Back;
		public Boolean flag = false;
		public float ox;
		public float oy;
		public float tox;
		public float toy;
		public int count;
		public boolean click = false;
		public boolean done = false;
		public boolean goback = false;

		public SelectorImage(CardData c, float ox, float oy, float tox, float toy, int count) {
			card = (CardImage) Start.cards.get(c.ID).getActor().clone();
			this.ox = ox;
			this.oy = oy;
			this.tox = tox;
			this.toy = toy;
			this.count = count;
			this.refresh(this);
		}

		public void refresh(final SelectorImage g) {
			this.clear();
			if (goback) {

				if (!flag) {
					this.addActor(card);
					Action act = Actions.scaleTo(0.01f, 1f, 0.2f);
					Action move = Actions.moveTo((tox + ox) / 2, (toy + oy) / 2, 0.2f);
					ParallelAction Paction = Actions.parallel(act, move);
					Action end = Actions.run(new Runnable() {
						@Override
						public void run() {
							flag = true;
							g.refresh(g);
						}
					});
					SequenceAction seq = Actions.sequence(Paction, end);
					this.addAction(seq);
				} else {
					Image img = ImageUtil.getImage("graphics/card/back.png");
					this.addActor(img);
					Action act = Actions.scaleTo(1f, 1f, 0.2f);
					Action move = Actions.moveTo(ox, oy, 0.2f);
					ParallelAction Paction = Actions.parallel(act, move);
					Action end = Actions.run(new Runnable() {
						@Override
						public void run() {
							g.clear();
							initSelectorImage(count);
						}
					});
					SequenceAction seq = Actions.sequence(Paction, end);
					g.addAction(seq);
				}
				return;
			}
			if (!done) {
				if (!flag) {
					Image img = ImageUtil.getImage("graphics/card/back.png");
					img.setPosition(ox, oy);
					this.addActor(img);
					Action act = Actions.scaleTo(0.01f, 1f, 0.2f);
					Action move = Actions.moveTo((tox + ox) / 2, (toy + oy) / 2, 0.2f);
					ParallelAction Paction = Actions.parallel(act, move);
					Action end = Actions.run(new Runnable() {
						@Override
						public void run() {
							g.flag = true;
							g.refresh(g);
							if (Game_Start.flag)
								initSelectorImage(g.count + 1);

						}
					});
					SequenceAction seq = Actions.sequence(Paction, end);
					g.addAction(seq);
				} else {
					this.addActor(card);
					Action act = Actions.scaleTo(1f, 1f, 0.2f);
					Action move = Actions.moveTo(tox, toy, 0.2f);
					ParallelAction Paction = Actions.parallel(act, move);
					Action end = Actions.run(new Runnable() {
						@Override
						public void run() {
							done = true;
							g.refresh(g);
							if (!Game_Start.flag) {
								int num = -1;
								for (int i = 0; i <= 2; i++) {
									if (inter[i] == -1)
										num = i;
								}
								System.out.println("num" + num);
								if (count == num) {
									drawcontrol();
								}
							}
						}
					});
					SequenceAction seq = Actions.sequence(Paction, end);
					this.addAction(seq);
					done = true;
				}
			} else {
				this.addActor(card);
				Image img = ImageUtil.getImage("graphics/game/kuang2.png");
				img.setPosition(-1, -1);
				if (!click)
					img.setColor(1, 1, 1, 0);
				if (click)
					img.setColor(1, 1, 1, 1);
				img.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						Game_Start.inter[count] = -Game_Start.inter[count];
						g.click = !g.click;
						g.refresh(g);
					}
				});
				this.addActor(img);
			}
		}
	}

	static class handImage extends Group {
		public Actor actor;
		public int position;
		private static final float x = 800;
		private static final float y = -3500;
		private static final float r = 3500;
		private static final float scale = 0.5f;
		private static final float time = 0.35f;

		public handImage(CardData ca, int i) {
			actor = (CardImage) Start.cards.get(ca.ID).getActor().clone();
			position = i;
			actor.setPosition(413.5f + 270 * position, 275);
			this.addActor(actor);
		}

		public void act() {
			float degree = (2.8f - 3 * 0.1f) * (position - (3 - 1f) / 2f);
			float dx = (float) (r * Math.sin(degree * Math.PI / 360)
					- Math.abs(scale * 233f / 2 * Math.cos(degree * Math.PI / 360)));
			float dy = (float) (r * Math.cos(degree * Math.PI / 360)
					+ scale * 233f / 2 * Math.sin(degree * Math.PI / 360));

			Action act = Actions.moveTo((int) (x + dx), (int) (y + dy), time);
			Action rotateto = Actions.rotateTo(-(2.8f - 3 * 0.1f) * (position - (3 - 1f) / 2f), time);
			Action scaleto = Actions.scaleTo(scale, scale, time);
			ParallelAction Paction = Actions.parallel(act, rotateto, scaleto);

			Action end = Actions.run(new Runnable() {
				@Override
				public void run() {
					if (position == 2) {
						GameBoard_Window.getInstance().handrefresh();
					}
				}
			});
			SequenceAction seq = Actions.sequence(Paction, end);
			actor.addAction(seq);

		}
	}

}
