package com.neko.game.player;

import java.io.Serializable;
import java.util.ArrayList;

public class MyDeck implements Serializable {

	private static final long serialVersionUID = -3719301263601889052L;

	public ArrayList<Deck> data;

	public void removedeck(int index) {
		data.remove(index);
	}

	public void addDeck(String deckname, String hero) {
		data.add(new Deck(deckname, hero));
	}

}
