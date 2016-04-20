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

		int[] inter = { 29, 28, 27 };
		GameBoard_Window.game.period = new Turn_Before_Start(0);
		GameBoard_Window.getInstance().refresh();

		initSelectorImage(0);
	}

	public static void initSelectorImage(int count) {
		if (count <= 2)
			GameBoard_Window.getInstance()
					.addActor(new SelectorImage(l.get(count), 1367, 80, (413.5f + 270 * count), 275, count));
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
							System.out.println("done");
							g.refresh(g);
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
						g.click = !g.click;
						g.refresh(g);
					}
				});
				this.addActor(img);
			}
		}
	}

}
