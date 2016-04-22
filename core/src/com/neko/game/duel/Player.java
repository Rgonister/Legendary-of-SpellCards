package com.neko.game.duel;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.Start;
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;
import com.neko.game.player.Deck;
import com.neko.ui.window.GameBoard.GameBoard_Window;
import com.neko.ui.window.GameBoard.HandCard_Actor;
import com.neko.util.Delay;
import com.neko.util.ImageUtil;

public class Player {
	public List<CardData> mydeck;

	public List<CardData> hand;

	public List<CardData> board;

	public String Hero;

	public int character;

	public Player(Deck deck) {
		Hero = deck.Hero;
		ArrayList<CardData> l = new ArrayList<CardData>();
		for (Integer i : deck.data.keySet()) {
			CardData c = Start.cards.get(i).data.clone();
			l.add(c);
			if (deck.data.get(i) > 1)
				l.add(c);
		}
		mydeck = new ArrayList<CardData>(l.size());
		do {
			int index =  new java.util.Random().nextInt(l.size());
			mydeck.add(l.get(index));
			l.remove(index);
		} while (l.size() > 0);

		hand = new ArrayList<CardData>();
		board = new ArrayList<CardData>();

	}

	public void drawCard() {
		int index = mydeck.size() - 1;
		CardData c = mydeck.get(index);
		act(c);
		mydeck.remove(index);
	}

	public void getCards(Integer ID) {
		CardData c = Start.cards.get(ID).data;
		hand.add(c);
		GameBoard_Window.getInstance().refresh();
	}

	public void getCards(List<Integer> l) {
		int index = new java.util.Random().nextInt(l.size());
		
		CardData c = Start.cards.get(index).data;
		hand.add(c);
		GameBoard_Window.getInstance().refresh();
	}

	public void shuffle() {
		List<CardData> l = new ArrayList<CardData>(mydeck.size());
		do {
			int index = new java.util.Random().nextInt(mydeck.size());
			l.add(mydeck.get(index));
			mydeck.remove(index);
		} while (mydeck.size() > 0);
		mydeck = l;
	}

	public void act(CardData c) {
		if (hand.size() < 10) {
			hand.add(c);
			if (character == 0) {
				GameBoard_Window.getInstance().addActor(new newcardimage(c, character, hand.size()));
			}
		}
	}

	static class newcardimage extends Group {
		public CardImage actor;
		public float ox;
		public float oy;
		public boolean flag = false;
		public CardData data;
		public int size;

		public int owner;

		public newcardimage(CardData c, int character, int size) {
			actor = new CardImage(c);
			data = c;
			if (character == 0) {
				ox = 1367;
				oy = 80;
			}
			this.size = size;
			owner = character;
			this.refresh();
		}

		public void refresh() {
			this.clear();
			if (!flag) {
				Image img = ImageUtil.getImage("graphics/card/back.png");
				img.setPosition(ox, oy);
				this.addActor(img);
				Action act = Actions.scaleTo(0.01f, 1f, 0.15f);
				Action move = Actions.moveTo((1000 + ox) / 2, (275 + oy) / 2, 0.15f);
				ParallelAction Paction = Actions.parallel(act, move);
				Action delay = Actions.delay(0.3f);
				Action end = Actions.run(new Runnable() {
					@Override
					public void run() {
						flag = true;
						refresh();
					}
				});
				SequenceAction seq = Actions.sequence(delay, Paction, end);
				img.addAction(seq);
			} else {
				this.addActor(actor);
				actor.setPosition((1000 + ox) / 2, (275 + oy) / 2);
				Action act = Actions.scaleTo(1f, 1f, 0.15f);
				Action move = Actions.moveTo(1000, 275, 0.15f);
				ParallelAction Paction = Actions.parallel(act, move);
				Action delay = Actions.delay(0.6f);
				Action end = Actions.run(new Runnable() {
					@Override
					public void run() {
						System.out.println("newcimg finished");
						movetohand();
					}
				});
				SequenceAction seq = Actions.sequence(Paction, delay, end);
				actor.addAction(seq);
			}
		}

		public void movetohand() {
			this.clear();
			HandCard_Actor ha = new HandCard_Actor(data, size - 1, owner);
			ha.image.setPosition(1000, 275);
			GameBoard_Window gw = GameBoard_Window.getInstance();
			gw.addActor(ha);
			System.out.println("-moved-");
			gw.myhand.add(ha);
			gw.handreset();

			gw.addActor(new Delay(0.3f) {
				@Override
				public void call() {
					System.out.println("-------called");
				}

			});
		}
	}

}
