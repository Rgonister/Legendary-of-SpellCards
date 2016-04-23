package com.neko.game.duel.period;

import com.neko.game.duel.Game;

public class Before_Turn extends Period {
	public void act() {
		acting = true;
		System.out.println("before_turn");
		if (Game.turn == 0) {
			Game.player_me.drawCard();
		} else {
			Game.player_op.drawCard();
		}
	}

}
