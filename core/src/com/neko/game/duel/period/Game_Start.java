package com.neko.game.duel.period;

import com.neko.game.duel.Game;
import com.neko.ui.window.GameBoard.GameBoard_Window;

public class Game_Start extends Period {

	public void act() {
		acting = true;
		System.out.println("gamestart init");
		Game.player_me.drawCard();
		Game.player_op.drawCard();
		Game.player_me.drawCard();
		Game.player_op.drawCard();
		Game.player_me.drawCard();
		Game.player_op.drawCard();
		if (Math.random() <= 0.5) {
			Game.player_me.drawCard();
			GameBoard_Window.game.period = new Turn_Before_Start(1);
		} else {
			Game.player_op.drawCard();
			GameBoard_Window.game.period = new Turn_Before_Start(0);
		}
		

		GameBoard_Window.getInstance().refresh();
	}

}
