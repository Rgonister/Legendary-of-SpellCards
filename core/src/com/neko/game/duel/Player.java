package com.neko.game.duel;

import java.util.ArrayList;
import java.util.List;

import com.neko.Start;
import com.neko.game.item.CardData;
import com.neko.game.player.Deck;
import com.neko.ui.window.GameBoard.GameBoard_Window;

public class Player {
	public List<CardData> mydeck;

	public List<CardData> hand;

	public List<CardData> board;

	public Player(Deck deck) {

		ArrayList<CardData> l = new ArrayList<CardData>();
		for (Integer i : deck.data.keySet()) {
			CardData c = Start.cards.get(i).data;
			l.add(c);
			if (deck.data.get(i) > 1)
				l.add(c);
		}
		mydeck = new ArrayList<CardData>(l.size());
		do {
			int index = (int) (Math.random() * l.size());
			mydeck.add(l.get(index));
			l.remove(index);
		} while (l.size() > 0);

		hand = new ArrayList<CardData>();
		board = new ArrayList<CardData>();

	}

	public void drawCard() {
		int index = mydeck.size() - 1;
		CardData c = mydeck.get(index);
		if (hand.size() < 10) {
			hand.add(c);
		}
		mydeck.remove(index);
		GameBoard_Window.getInstance().refresh();
	}

	public void getCards(Integer ID) {
		CardData c = Start.cards.get(ID).data;
		hand.add(c);
		GameBoard_Window.getInstance().refresh();
	}

	public void getCards(List<Integer> l) {
		int index = (int) (Math.random() * l.size());
		CardData c = Start.cards.get(index).data;
		hand.add(c);
		GameBoard_Window.getInstance().refresh();
	}
}
