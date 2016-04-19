package com.neko.game.duel.period;

import com.neko.game.duel.Game;

public class Game_Start extends Period {

	public void init() {
		System.out.println("gamestart init");
		Game.player_me.drawCard();
		Game.player_me.drawCard();
		Game.player_me.drawCard();
	}

}
