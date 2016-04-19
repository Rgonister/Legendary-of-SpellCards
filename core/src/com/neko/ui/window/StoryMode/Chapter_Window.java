package com.neko.ui.window.StoryMode;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.config.enums.WindowState;
import com.neko.ui.screen.Screen_StoryMode;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class Chapter_Window extends Group {

	private static Chapter_Window instance = null;

	private ArrayList<Image> chapter_Image = new ArrayList<Image>();

	private int page_number = 1;

	private int page_size = 4;

	public static Chapter_Window getInstance() {
		if (instance == null) {
			synchronized (Chapter_Window.class) {
				if (instance == null) {
					instance = new Chapter_Window();
				}
			}
		}
		return instance;
	}

	private Chapter_Window() {
		for (int i = 1; i <= Config.Chapter_Number; i++) {
			String path = "";
			if (i <= Start.global.data.chapter_unlocked)
				path = Config.Chapter_Image_Path + "/Chapter" + i + ".jpg";
			else
				path = Config.Chapter_Image_Path + "/Chapter" + i + "_locked.jpg";
			chapter_Image.add(ImageUtil.getImage(path));
		}

		this.refresh();
	}

	public void refresh() {
		this.clear();
		Image im = new Image(ImageUtil.getTexture("graphics/StoryMode/gray.png"));
		im.setWidth(830);
		im.setHeight(790);
		im.setPosition(110, 55);
		im.setColor(80, 80, 80, 0.65f);
		this.addActor(im);
		for (int i = 1; i <= page_size; i++) {
			final int chapter_number = (page_number - 1) * page_size + i;
			if (chapter_number > Config.Chapter_Number)
				break;
			Image image = chapter_Image.get(chapter_number - 1);
			image.setPosition(125 , (page_size - i) * 185 + 110);
			if (chapter_number <= Config.Current_Chapter) {
				image.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						SEControler.play(1, "Click");
						Stage_Window.getInstance();
						Stage_Window.chapter_number = chapter_number;
						Screen_StoryMode.setStage_group(Stage_Window.getInstance());
					}
				});
			}
			this.addActor(image);
		}
		add_window_button();
	}

	private void add_window_button() {
		final Image back;
		if (page_number == 1) {
			back = ImageUtil.getImage(Config.Icon_Path + "goback1.png");
		} else {
			back = ImageUtil.getImage(Config.Icon_Path + "goback0.png");
			back.addListener(new ClickListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					if (super.touchDown(event, x, y, pointer, button)) {
						back.setSize(back.getWidth() * 0.92f, back.getHeight() * 0.92f);
						back.setPosition(back.getX() + back.getWidth() * 0.04f, back.getY() + back.getHeight() * 0.04f);
						return true;
					} else {
						return false;
					}
				}

				@Override
				public void clicked(InputEvent event, float x, float y) {
					SEControler.play(1, "Click");
					Chapter_Window.getInstance().page_number -= 1;
					Chapter_Window.getInstance().refresh();

					back.setSize(80, 35);
					back.setPosition(125, 65);
				}
			});
		}
		back.setPosition(125, 65);
		this.addActor(back);

		final Image front;
		if (page_number * page_size >= Config.Chapter_Number) {
			front = ImageUtil.getImage(Config.Icon_Path + "gofront1.png");
		} else {
			front = ImageUtil.getImage(Config.Icon_Path + "gofront0.png");
			front.addListener(new ClickListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					if (super.touchDown(event, x, y, pointer, button)) {
						front.setSize(front.getWidth() * 0.92f, front.getHeight() * 0.92f);
						front.setPosition(front.getX() + front.getWidth() * 0.04f,
								front.getY() + front.getHeight() * 0.04f);
						return true;
					} else {
						return false;
					}
				}

				@Override
				public void clicked(InputEvent event, float x, float y) {

					SEControler.play(1, "Click");
					Chapter_Window.getInstance().page_number += 1;
					Chapter_Window.getInstance().refresh();

					front.setSize(80, 35);
					front.setPosition(220, 65);
				}
			});
		}
		front.setPosition(220, 65);
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
				Start.windowstate = WindowState.Cover;
				page_number = 1;
				refresh();
				toCover.setSize(80, 35);
				toCover.setPosition(845, 65);
			}
		});
		toCover.setPosition(845, 65);
		this.addActor(toCover);
	}

}
