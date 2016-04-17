package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Group;

public class MyDeck_Window extends Group {

	private static MyDeck_Window instance = null;

	public static MyDeck_Window getInstance() {
		if (instance == null) {
			synchronized (DeckView_Window.class) {
				if (instance == null) {
					instance = new MyDeck_Window();
				}
			}
		}
		return instance;
	}

	private MyDeck_Window() {
	}	
	

}
