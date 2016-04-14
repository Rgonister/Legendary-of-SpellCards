package com.neko.game.item;

public class Card {

	public int ID;
	public CardData data;
	private CardImage actor;

	public Card(CardData d) {
		data = d;
		ID = d.ID;
	}

	public CardImage getActor() {
		return actor;
	}

}
