package com.neko.ui.window.DeckManage;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.neko.config.Config;
import com.neko.game.item.CardData;
import com.neko.util.ImageUtil;

public class DeckView_Window extends Group {

	private static DeckView_Window instance = null;
	private Image bg;
	private List<Image> cardimage = new ArrayList<Image>();
	private List<CardData> cards = new ArrayList<CardData>();
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
		bg.setWidth(1100 * Config.Scale);
		bg.setHeight(800 * Config.Scale);
		bg.setPosition(50 * Config.Scale, 40 * Config.Scale);
		bg.setColor(80, 80, 80, 0.65f);
		CardData c = new CardData();
		c.picPath = "data/card/card1.png";
		for (int i = 0; i < 16; i++) {
			cards.add(c);
		}
		this.refresh();
	}

	public void refresh() {
		this.clear();
		this.addActor(bg);
		initcardimage();
		for (Image i : cardimage) {
			this.addActor(i);
		}
	}

	private void initcardimage() {
		cardimage = new ArrayList<Image>();
		for (int i = 0; i < 4; i++) {
			Image img = ImageUtil.getImage(cards.get(i + page * 8).picPath);
			img.setPosition((80 + 255 * i) * Config.Scale, 470 * Config.Scale);
			cardimage.add(img);
		}
		for (int i = 0; i < 4; i++) {
			Image img = ImageUtil.getImage(cards.get(i + 4 + page * 8).picPath);
			img.setPosition((80 + 255 * i) * Config.Scale, 90 * Config.Scale);
			cardimage.add(img);
		}
	}
}
