package com.neko.util;

import com.badlogic.gdx.Input.TextInputListener;
import com.neko.game.player.Deck;
import com.neko.ui.window.DeckManage.DeckImage;
import com.neko.ui.window.DeckManage.DeckView_Window;

public class MyTextInputListener implements TextInputListener {   
	@Override
	public void input(String text) {
		DeckView_Window dvw = DeckView_Window.getInstance();
		dvw.deckImage = new DeckImage(new Deck(text, "ALICE"));
		dvw.cfilter.set(0, "Alice");
		dvw.selecter.clear();
		dvw.selecter = null;
		dvw.editmode = true;
		dvw.refresh();
	}

	@Override
	public void canceled() {
		DeckView_Window dvw = DeckView_Window.getInstance();
		dvw.deckImage = new DeckImage(new Deck("Alice", "ALICE"));
		dvw.cfilter.set(0, "Alice");
		dvw.selecter.clear();
		dvw.selecter = null;
		dvw.editmode = true;
		dvw.refresh();
	}
	}