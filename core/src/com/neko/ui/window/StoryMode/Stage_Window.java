package com.neko.ui.window.StoryMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.config.Config;
import com.neko.system.sound.SEControler;
import com.neko.ui.screen.Screen_StoryMode;
import com.neko.util.ImageUtil;

public class Stage_Window extends Group {
	private static Stage_Window instance = null;

	public static int chapter_number = 1;

	Map<Integer, ArrayList<Image>> stage_image;

	public static Stage_Info_Window stageinfo = Stage_Info_Window.getInstance();

	public static Stage_Window getInstance() {
		if (instance == null) {
			synchronized (Stage_Window.class) {
				if (instance == null) {
					instance = new Stage_Window();
				}
			}
		}
		return instance;
	}

	private Stage_Window() {
		stage_image = new HashMap<Integer, ArrayList<Image>>();
		ArrayList<Image> al = new ArrayList<Image>();
		for (int i = 1; i <= Config.Chapter_Number; i++) {
			String path = Config.Chapter_Image_Path + "1/Stage" + i + ".png";
			Image image = new Image(new Texture(path));
			ImageUtil.resize(image);
			al.add(image);
		}
		stage_image.put(1, al);
		this.refresh();
	}

	public void refresh() {
		this.clear();
		ArrayList<Image> al = stage_image.get(chapter_number);
		for (int i = 1; i <= 6; i++) {
			final int num = i;
			Image image = al.get(i - 1);
			image.setPosition(125 * Config.Scale, ((6 - i) * 122.6f + 110) * Config.Scale);
			image.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					Stage_Info_Window.getInstance().setStage_number(num);
					Stage_Window.getInstance().addActor(Stage_Info_Window.getInstance());
					Stage_Info_Window.getInstance().refresh();
				}
			});
			this.addActor(image);
		}

		add_window_button();
	}

	private void add_window_button() {
		Image back = ImageUtil.getImage(Config.Icon_Path + "goback1.png");
		back.setPosition(Config.Scale * 125, Config.Scale * 65);
		this.addActor(back);

		Image front = ImageUtil.getImage(Config.Icon_Path + "gofront1.png");
		front.setPosition(Config.Scale * 220, Config.Scale * 65);
		this.addActor(front);

		final Image toCover = ImageUtil.getImage(Config.Icon_Path + "return0.png");
		toCover.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (super.touchDown(event, x, y, pointer, button)) {
					toCover.setSize(toCover.getWidth() * 0.92f, toCover.getHeight() * 0.92f);
					toCover.setPosition(toCover.getX() + toCover.getWidth() * 0.04f,
							toCover.getY() + toCover.getHeight() * 0.04f);
					return true;
				} else {
					return false;
				}
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {

				SEControler.play(1, "Click");
				Screen_StoryMode.getInstance();
				Screen_StoryMode.setStage_group(Chapter_Window.getInstance());

				Stage_Info_Window.getInstance().clear();
				toCover.setSize(Config.Scale * 80, Config.Scale * 35);
				toCover.setPosition(Config.Scale * 845, Config.Scale * 65);
			}
		});
		toCover.setPosition(Config.Scale * 845, Config.Scale * 65);
		this.addActor(toCover);
	}

}
