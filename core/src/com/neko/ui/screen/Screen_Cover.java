package com.neko.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.neko.config.Config;
import com.neko.system.base.component.FontActor;
import com.neko.system.base.component.Screen_Window;
import com.neko.ui.button.Cover.Button_DeckManage;
import com.neko.ui.button.Cover.Button_Exit;
import com.neko.ui.button.Cover.Button_NetDuel;
import com.neko.ui.button.Cover.Button_Options;
import com.neko.ui.button.Cover.Button_StoryMode;
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
		stage.addActor(new Button_StoryMode());
		stage.addActor(new Button_DeckManage());
		stage.addActor(new Button_NetDuel());
		stage.addActor(new Button_Options());
		stage.addActor(new Button_Exit());
		Gdx.input.setInputProcessor(stage);
	}
	
}
