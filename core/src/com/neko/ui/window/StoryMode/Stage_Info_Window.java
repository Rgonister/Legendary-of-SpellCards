package com.neko.ui.window.StoryMode;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;
import com.neko.util.ImageUtil;

public class Stage_Info_Window extends Group {
	private static Stage_Info_Window instance = null;

	private int stage_number = 1;

	private Image bg;
	private Image border;
	private List<Image> stageImage = new ArrayList<Image>();
	
	public static Stage_Info_Window getInstance() {
		if (instance == null) {
			synchronized (Stage_Info_Window.class) {
				if (instance == null) {
					instance = new Stage_Info_Window();
				}
			}
		}
		return instance;
	}

	private Stage_Info_Window() {
		bg = new Image(ImageUtil.getTexture("graphics/StoryMode/gray.png"));
		bg.setWidth(520 * Config.Scale);
		bg.setHeight(790 * Config.Scale);
		bg.setPosition(970 * Config.Scale, 55 * Config.Scale);
		bg.setColor(80, 80, 80, 0.65f);
		
		for(int i = 1;i<=6;i++){
			Image img = ImageUtil.getImage("graphics/StoryMode/Chapter"+Stage_Window.chapter_number+"/Stage"+i+"/hero.png");
			img.setPosition(1020, 325);
			stageImage.add(img);
		}		
		
		border = ImageUtil.getImage("graphics/StoryMode/border.png");
		border.setPosition(1005 * Config.Scale, 315 * Config.Scale);
	}

	public void refresh() {
		this.clear();
		this.addActor(bg);
		this.addActor(border);
		this.addActor(stageImage.get(stage_number-1));
		Level_Window.init(Stage_Window.chapter_number, stage_number);
		this.addActor(Level_Window.getInstance());
	}

	public void setStage_number(int stage_number) {
		this.stage_number = stage_number;
	}
}
