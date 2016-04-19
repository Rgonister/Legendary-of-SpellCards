package com.neko.system.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ApplicationGame extends Game {


	@Override
	public void create() {
		setScreen(screen);
	}

	public ApplicationGame(Screen sc) {
		super();
		this.screen = sc;
	}

	@Override
	public void resume() {
		super.resume();
	}
	


}
