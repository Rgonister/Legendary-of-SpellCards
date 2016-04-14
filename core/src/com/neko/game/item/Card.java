package com.neko.game.item;

public class Card {

	public int ID;
	public CardData data;
	private CardImage actor;

	public Card(CardData d) {
		d.picPath = "graphics/card/" + d.ID + ".png";
		data = d;		
		ID = d.ID;
	}

	public CardImage getActor() {
		if (actor == null) {
			actor = new CardImage(data);
		}
		return actor;
	}
}
