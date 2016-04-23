package com.neko.game.duel.period;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.neko.Start;
import com.neko.game.duel.Game;
import com.neko.game.item.CardData;
import com.neko.ui.window.GameBoard.GameBoard_Window;
import com.neko.ui.window.GameBoard.HandCard_Actor;
import com.neko.util.Delay;

public class Before_Turn extends Period {
	public void act() {
		acting = true;
		System.out.println("before_turn");
		// if (Game.turn == 0) {
		// Game.player_me.drawCard();
		// } else {
		// Game.player_op.drawCard();
		// }
	}

	public static void getCoin() {
		CardData c = Start.cards.get(1001).data;
		if (Game.turn == 0) {
			Game.player_op.hand.add(c);

			System.out.println(Game.player_op.hand.size());
			HandCard_Actor aca = new HandCard_Actor(c, Game.player_op.hand.size() - 1, 1);
			aca.orgy += 750;
			aca.update();
			aca.image.setColor(1, 1, 1, 0.01f);
			GameBoard_Window.getInstance().addActor(aca);
			GameBoard_Window.getInstance().ophand.add(aca);
			GameBoard_Window.getInstance().handreset();
			Action alphah = Actions.alpha(1f, 1);
			aca.image.addAction(alphah);
			new Delay(1.5f) {
				@Override
				public void call() {
					Game.player_me.drawCard();
				}
			};
		} else {
			Game.player_me.hand.add(c);
			HandCard_Actor aca = new HandCard_Actor(c, Game.player_me.hand.size() - 1, 0);
			GameBoard_Window.getInstance().myhand.add(aca);
			aca.update();
			aca.image.setColor(1, 1, 1, 0.01f);
			GameBoard_Window.getInstance().addActor(aca);
			GameBoard_Window.getInstance().myhand.add(aca);
			GameBoard_Window.getInstance().handreset();
			Action alphah = Actions.alpha(1f, 1);
			aca.image.addAction(alphah);
			new Delay(1.5f) {
				@Override
				public void call() {
					Game.player_op.drawCard();
				}
			};
		}

	}
}
