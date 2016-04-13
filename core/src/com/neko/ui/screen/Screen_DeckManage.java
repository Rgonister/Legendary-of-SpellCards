package com.neko.ui.screen;

import com.neko.system.base.component.Screen_Window;

public class Screen_DeckManage extends Screen_Window {
	private static Screen_DeckManage instance = null;

	public static Screen_DeckManage getInstance() {
		if (instance == null) {
			synchronized (Screen_DeckManage.class) {
				if (instance == null) {
					instance = new Screen_DeckManage();
				}
			}
		}
		return instance;
	}

	private Screen_DeckManage() {
	}
}
