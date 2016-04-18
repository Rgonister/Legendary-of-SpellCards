package com.neko.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.neko.config.Config;
import com.neko.config.enums.WindowState;
import com.neko.system.base.component.Screen_Window;
import com.neko.ui.button.Redirect_Button;
import com.neko.util.ImageUtil;

public class Screen_Cover extends Screen_Window {
	
	private static Screen_Cover instance = null;
	

	public static Screen_Cover getInstance() {
		if (instance == null) {
			synchronized (Screen_Cover.class) {
				if (instance == null) {
					instance = new Screen_Cover();
				}
			}
		}
		return instance;
	}

	private Screen_Cover() {
	};

	@Override
	public void show() {             
		stage = new Stage();		
		stage.addActor(ImageUtil.getImage(Config.Cover_Bg_Image_Path,false));
		stage.addActor(new Redirect_Button("storymode",WindowState.StoryMode,0,330));
		stage.addActor(new Redirect_Button("deckmanage",WindowState.DeckManage,0,270));
		stage.addActor(new Redirect_Button("netduel",WindowState.GameBoard,0,210));
		stage.addActor(new Redirect_Button("options",WindowState.Shop,0,150));
		stage.addActor(new Redirect_Button("shutdown",WindowState.DeckManage,0,90));
		Gdx.input.setInputProcessor(stage);
	}
	
}
