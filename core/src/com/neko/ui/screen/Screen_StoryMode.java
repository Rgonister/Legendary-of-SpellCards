package com.neko.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;
import com.neko.system.base.component.Screen_Window;
import com.neko.ui.window.StoryMode.Chapter_Window;
import com.neko.util.ImageUtil;

public class Screen_StoryMode extends Screen_Window {

	private static Screen_StoryMode instance = null;
	private Group select_window;

	public static Screen_StoryMode getInstance() {
		if (instance == null) {
			synchronized (Screen_StoryMode.class) {
				if (instance == null) {
					instance = new Screen_StoryMode();
				}
			}
		}
		return instance;
	}

	private Screen_StoryMode() {
		select_window = Chapter_Window.getInstance();
	}

	@Override
	public void show() {
		stage = new Stage();

		stage.addActor(ImageUtil.getImage("graphics/bg.jpg", false));
		select_window.setScale(Config.ScaleX, Config.ScaleY);
		stage.addActor(select_window);
		Gdx.input.setInputProcessor(stage);
	}

	public static void setStage_group(Group stage_group) {
		Screen_StoryMode ss = Screen_StoryMode.getInstance();
		ss.select_window = stage_group;
		ss.stage.dispose();
		ss.show();
	}

}
