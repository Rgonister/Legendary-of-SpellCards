package com.neko.ui.window.GameBoard;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.game.duel.Game;
import com.neko.game.item.CardData;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;

public class GameBoard_Window extends Group {

	private static GameBoard_Window instance = null;

	public static Game game;

	public ArrayList<HandCard_Actor> myhand = new ArrayList<HandCard_Actor>();

	public ArrayList<HandCard_Actor> ophand = new ArrayList<HandCard_Actor>();

	public BattleField_Actor tempcard;

	public ArrayList<BattleField_Actor> mysummon = new ArrayList<BattleField_Actor>();

	public ArrayList<BattleField_Actor> opsummon = new ArrayList<BattleField_Actor>();

	public static GameBoard_Window getInstance() {
		if (instance == null) {
			synchronized (GameBoard_Window.class) {
				if (instance == null) {
					instance = new GameBoard_Window();
				}
			}
		}
		return instance;
	}

	private GameBoard_Window() {
	}

	public void refresh() {
		this.clear();

		Image myHero = ImageUtil.getImage("graphics/deck/" + Game.player_me.Hero + ".jpg");
		myHero.setPosition(0, 0);
		this.addActor(myHero);

		Image opHero = ImageUtil.getImage("graphics/deck/" + Game.player_op.Hero + ".jpg");
		opHero.setPosition(1250, 740);
		this.addActor(opHero);

		this.addActor(BackgroundUtil.getImage("gray", 234, 160, 1132, 580, 0.65f));

		if (Game.player_me.mydeck.size() > 0) {
			Image img = ImageUtil.getImage("graphics/card/back.png");
			img.setPosition(1367, 80);
			this.addActor(img);
		}
		if (Game.player_op.mydeck.size() > 0) {
			Image img = ImageUtil.getImage("graphics/card/back.png");
			img.setPosition(0, 470);
			this.addActor(img);
		}

		// ---------加载手牌区-----------
		for (HandCard_Actor a : myhand) {
			this.addActor(a);
		}

		for (HandCard_Actor a : ophand) {
			this.addActor(a);
		}

		// ---------加载战场区-----------
		for (BattleField_Actor a : mysummon) {
			this.addActor(a);
		}

		for (BattleField_Actor a : opsummon) {
			this.addActor(a);
		}

		game.check();
	}

	public static void init(Game g) {
		GameBoard_Window.game = g;
	}

	public void handrefresh() {
		// 刷新自己手牌区
		myhand.clear();
		List<CardData> l = Game.player_me.hand;
		for (int i = 0; i < l.size(); i++) {
			final HandCard_Actor a = new HandCard_Actor(l.get(i), i, 0);
			a.update();
			myhand.add(a);
		}

		// 刷新敌方手牌区
		ophand.clear();
		List<CardData> lop = Game.player_op.hand;
		for (int i = 0; i < lop.size(); i++) {
			final HandCard_Actor a = new HandCard_Actor(lop.get(i), i, 1);
			a.orgy += 750;
			a.update();
			ophand.add(a);
		}

		refresh();
	}

	public void handreset() {
		for (HandCard_Actor hca : myhand) {
			hca.postioncaculate();
			hca.reset();
		}

		for (HandCard_Actor hca : ophand) {
			hca.postioncaculate();
			hca.orgy += 750;
			hca.reset();
		}
	}

}
