package com.neko.ui.window.DeckManage;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;
import com.neko.game.item.Card;
import com.neko.util.ImageUtil;

public class DeckView_Window extends Group{
	
	
	private static DeckView_Window instance = null;
	private Image bg;
	private List<Image> cardimage = new ArrayList<Image>();
	private List<Card> cards = new ArrayList<Card>();
	private int page = 1;
	
	public static DeckView_Window getInstance() {
		if (instance == null) {
			synchronized (DeckView_Window.class) {
				if (instance == null) {
					instance = new DeckView_Window();
				}
			}
		}
		return instance;
	}

	private DeckView_Window() {
		bg = new Image(ImageUtil.getTexture("graphics/StoryMode/gray.png"));
		bg.setWidth(1150 * Config.Scale);
		bg.setHeight(800 * Config.Scale);
		bg.setPosition(50 * Config.Scale, 40 * Config.Scale);
		bg.setColor(80, 80, 80, 0.65f);
		Card c = new Card();
		c.picPath = "data/card/card1.png";
		for(int i = 0;i<16;i++){
			cards.add(c);
		}
		this.refresh();
	}
	
	public void refresh(){
		this.clear();
		this.addActor(bg);
	}
	
	private void initcardimage(){
		cardimage = new ArrayList<Image>();
		for(int i = 0;i<4;i++){
		Image img = ImageUtil.getImage(cards.get(i).picPath);
		}
	}
}
