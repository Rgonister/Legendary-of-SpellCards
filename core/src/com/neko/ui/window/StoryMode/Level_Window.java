package com.neko.ui.window.StoryMode;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.config.enums.Level;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class Level_Window extends Group {

	public static Level level;
	private static ArrayList<Image> startbutton = new ArrayList<Image>();
	private static ArrayList<Image> levelselector = new ArrayList<Image>();
	private static Level_Window instance = null;

	public static Level_Window getInstance() {
		if (instance == null) {
			synchronized (Chapter_Window.class) {
				if (instance == null) {
					instance = new Level_Window();
				}
			}
		}
		return instance;
	}

	private Level_Window() {
	};

	public static void init(final int Chapter, final int Stage) {
		Level_Window.getInstance();
		level = Start.global.data.stage_progress.get(Chapter).get(Stage - 1);
		int number = level.getValue();
		for (int i = 1; i <= 4; i++) {
			Image sbutton = ImageUtil.getImage(Config.StoryMode_Path + "Level/button" + i + ".png");
			// set position
			sbutton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					SEControler.play(1, "Click");
					System.out.println(Chapter + "-" + Stage + "-" + level.toString() + "Clicked");
				}
			});
			sbutton.setPosition(1005, 110 );
			startbutton.add(sbutton);

			Image lselector = ImageUtil.getImage(Config.StoryMode_Path + "Level/selector" + i + ".png");
			final int n = i;
			if (i - number <= 1)
				lselector.addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						SEControler.play(1, "Click");
						Level_Window.refresh(n);
					}
				});
			lselector.setPosition(893 + i * 112, 65 );
			levelselector.add(lselector);
		}
		Level_Window.refresh(1);
	}

	public static void refresh(int num) {
		Level_Window lw = Level_Window.getInstance();
		lw.clear();
		lw.addActor(startbutton.get(num - 1));
		for (Image i : levelselector) {
			lw.addActor(i);
		}
		//String[] text = Gdx.files.internal(Config.Data_Path_Storymode+"chapter1/stage1/1.neko").readString().split(";");
		//System.out.println(text[0]);
	}

	public static Level getLevel() {
		return level;
	}
}
