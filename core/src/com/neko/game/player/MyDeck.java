package com.neko.game.player;

import java.util.ArrayList;
import java.util.List;

public class MyDeck {
	
	public static List<Deck> decks = new ArrayList<Deck>();
	
	public static void removedeck(int index){
		decks.remove(index);
	}
	
	public static void addDeck(String deckname,String hero){
		decks.add(new Deck(deckname,hero));
	}
	
}
