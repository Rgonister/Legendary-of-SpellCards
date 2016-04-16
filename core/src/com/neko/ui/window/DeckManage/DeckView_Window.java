package com.neko.ui.window.DeckManage;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.game.item.Card;
import com.neko.game.item.CardImage;
import com.neko.system.data.CardFilter;
import com.neko.system.sound.SEControler;
import com.neko.util.ImageUtil;

public class DeckView_Window extends Group {

	private static DeckView_Window instance = null;
	private Image bg;
	private List<String> cfilter = new ArrayList<String>();
	private List<Integer> cardID = new ArrayList<Integer>();
	private List<Actor> cardimage = new ArrayList<Actor>();
	private int page = 1;
	public Card_Info_Window cfw;

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
		bg.setWidth(1030 * Config.Scale);
		bg.setHeight(800 * Config.Scale);
		bg.setPosition(50 * Config.Scale, 40 * Config.Scale);
		bg.setColor(80, 80, 80, 0.65f);
		cfilter.add("Alice");
		cfilter.add("");
		this.refresh();
	}

	public void refresh() {
		this.clear();
		this.addActor(bg);
		initcardimage();
		for (Actor i : cardimage) {
			this.addActor(i);
		}
		this.add_window_button();
		this.add_groupfilter_button();
		add_cost_filter_button();
		if (cfw != null) {
			this.addActor(cfw);
		}
	}

	private void initcardimage() {
		cardID = CardFilter.getlistwithfilter(cfilter);
		cardimage = new ArrayList<Actor>();
		for (int i = 0; i < 4; i++) {
			if ((i + (page - 1) * 8) >= cardID.size())
				break;
			final Card c = Start.cards.get(cardID.get(i + (page - 1) * 8));
			CardImage img = c.getActor();
			img.setPosition((65 + 255 * i) * Config.Scale, 480 * Config.Scale);
			if (!img.flag) {
				img.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						SEControler.play(1, "Click");
						cfw = new Card_Info_Window(c.data);
						DeckView_Window.getInstance().refresh();
					}

				});
				img.flag = true;
			}
			cardimage.add(img);
		}
		for (int i = 0; i < 4; i++) {
			if ((i + 4 + (page - 1) * 8) >= cardID.size())
				break;
			final Card c = Start.cards.get(cardID.get(i + 4 + (page - 1) * 8));
			CardImage img = c.getActor();
			img.setPosition((65 + 255 * i) * Config.Scale, 100 * Config.Scale);
			if (!img.flag) {
				img.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						SEControler.play(1, "Click");
						cfw = new Card_Info_Window(c.data);
						DeckView_Window.getInstance().refresh();
					}

				});
				img.flag = true;
			}
			cardimage.add(img);
		}
	}

	private void add_window_button() {
		final Image back;
		if (page == 1) {
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
					page = page - 1;
					refresh();
				}
			});
		}
		back.setPosition(Config.Scale * 886, Config.Scale * 55);
		this.addActor(back);

		final Image front;
		if (page * 8 >= cardID.size()) {
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
					page = page + 1;
					refresh();
				}
			});
		}
		front.setPosition(Config.Scale * 981, Config.Scale * 55);
		this.addActor(front);
	}

	private void add_groupfilter_button() {
		Image bimg = new Image(ImageUtil.getTexture("graphics/StoryMode/gray.png"));
		bimg.setWidth(70 * Config.Scale);
		bimg.setHeight(250 * Config.Scale);
		bimg.setPosition(1090 * Config.Scale, 590 * Config.Scale);
		bimg.setColor(80, 80, 80, 0.65f);
		this.addActor(bimg);
		
		Image alice = ImageUtil.getImage("graphics/icon/Alice.jpg");
		alice.setPosition(Config.Scale * 1100, Config.Scale * 780);
		alice.setColor(100, 100, 100, 0.85f);
		alice.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!"Alice".equals(cfilter.get(0))) {
					SEControler.play(1, "Click");
					page = 1;
					cfilter.set(0, "Alice");
					refresh();
				}
			}
		});
		this.addActor(alice);

		Image cirno = ImageUtil.getImage("graphics/icon/Cirno.jpg");
		cirno.setPosition(Config.Scale * 1100, Config.Scale * 720);
		cirno.setColor(100, 100, 100, 0.85f);
		cirno.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!"Cirno".equals(cfilter.get(0))) {
					SEControler.play(1, "Click");
					page = 1;
					cfilter.set(0, "Cirno");
					refresh();
				}
			}
		});
		this.addActor(cirno);

		Image pachi = ImageUtil.getImage("graphics/icon/Pachi.jpg");
		pachi.setPosition(Config.Scale * 1100, Config.Scale * 660);
		pachi.setColor(100, 100, 100, 0.85f);
		pachi.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!"Pachi".equals(cfilter.get(0))) {
					SEControler.play(1, "Click");
					page = 1;
					cfilter.set(0, "Pachi");
					refresh();
				}
			}
		});
		this.addActor(pachi);

		Image common = ImageUtil.getImage("graphics/icon/Public.jpg");
		common.setPosition(Config.Scale * 1100, Config.Scale * 600);
		common.setColor(100, 100, 100, 0.85f);
		common.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!"Common".equals(cfilter.get(0))) {
					SEControler.play(1, "Click");
					page = 1;
					cfilter.set(0, "Common");
					refresh();
				}
			}
		});
		this.addActor(common);
	}

	private void add_cost_filter_button() {
		for (int i = 0; i <= 7; i++) {
			final int num = i;
			String path = "graphics/numbers/" + i + "w.jpg";
			if (cfilter.get(1).equals("Cost" + num))
				path = "graphics/numbers/" + i + "b.jpg";
			Image image = ImageUtil.getImage(path);
			image.setWidth(0.75f * image.getWidth() * Config.Scale);
			image.setHeight(35 * Config.Scale);
			image.setPosition(Config.Scale * (65 + i * 42), Config.Scale * (55));
			image.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					if (cfilter.get(1).equals("Cost" + num)) {
						SEControler.play(0.8f, "Click");
						cfilter.set(1, "");
					} else {
						SEControler.play(0.8f, "Click");
						cfilter.set(1, "Cost" + num);
					}
					refresh();
				}
			});
			this.addActor(image);
		}

	}
}
