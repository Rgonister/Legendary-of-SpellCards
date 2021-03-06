package com.neko.game.duel;

import com.neko.Start;
import com.neko.game.duel.period.Game_Start;
import com.neko.game.duel.period.Period;
import com.neko.game.player.Deck;

public class Game {

	public static Player player_me;
	public static Player player_op;

	public static int turn = 0;

	public Period period = new Game_Start();

	public Game(Deck mydeck, Deck opdeck) {
		player_me = new Player(mydeck);
		player_me.character = 0;
		player_op = new Player(opdeck);
		player_op.character = 1;
	}

	public Game() {
		if (player_me == null) {
			player_me = new Player(Start.global.decks.data.get(0));
			player_me.character = 0;
		}

		if (player_op == null) {
			player_op = new Player(Start.global.decks.data.get(0));
			player_op.character = 1;
		}

	}

	public void check() {
		if (!period.acting)
			period.act();
	}
}
