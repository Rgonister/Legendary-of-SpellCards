package com.neko.ui.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.config.enums.WindowState;
import com.neko.game.duel.Game;
import com.neko.ui.screen.Screen_GameBoard;
import com.neko.ui.window.GameBoard.GameBoard_Window;
import com.neko.util.ImageUtil;

public class Redirect_Button extends Image {
	private static final String path = "graphics/Menu/";

	public Redirect_Button(String ImgPath, final WindowState ws, float x, float y) {
		super(ImageUtil.getTexture(path + ImgPath + ".png"));
		ImageUtil.resize(this);
		if (x == 0)
			this.setX(Config.ScaleX * (800 - this.getWidth() / 2));
		else
			this.setX(Config.ScaleX * x);
		this.setY(Config.ScaleY * y);
		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Start.windowstate = ws;
//				
//				GameBoard_Window.getInstance().setScale(Config.ScaleX, Config.ScaleY);
//				GameBoard_Window.init(new Game(Start.global.decks.data.get(0), Start.global.decks.data.get(0)));		
//				Screen_GameBoard.getInstance().stage.addActor(GameBoard_Window.getInstance());
			}
		});
		this.setScale(Config.ScaleX, Config.ScaleY);
	}
}
