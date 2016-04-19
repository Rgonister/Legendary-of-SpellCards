package com.neko.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.util.ImageUtil;

public class Cover extends Image{
	public Cover(){
		super(ImageUtil.getTexture("graphics/StoryMode/black.png"));
		this.setColor(0,0,0,0);
//		(Screen_Window) Start.ag.getScreen();
//		new Screen_Window();
	}
	
	public static void removeself(){
		
	}
}
