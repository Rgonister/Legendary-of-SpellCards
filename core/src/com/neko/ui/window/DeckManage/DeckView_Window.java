package com.neko.ui.window.DeckManage;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.neko.Start;
import com.neko.config.Config;
import com.neko.config.enums.WindowState;
import com.neko.game.item.Card;
import com.neko.game.item.CardImage;
import com.neko.game.player.Player;
import com.neko.system.data.CardFilter;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class DeckView_Window extends Group {

	private static DeckView_Window instance = null;
	private Image bg;
	private Image bg1;
	public List<String> cfilter = new ArrayList<String>();
	private List<Integer> cardID = new ArrayList<Integer>();
	private List<Actor> cardimage = new ArrayList<Actor>();
	private int page = 1;
	public Card_Info_Window cfw;
	public HeroSelecter_Window selecter;
	public boolean viewstate = false;
	public boolean editmode = false;
	public DeckImage deckImage;
	public MyDeck_Window mdw;
	public static Image smallimg;
	public static float smallimgx;
	public static float smallimgy;

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
		bg = BackgroundUtil.getImage("gray", 50, 45, 1030, 795, 0.65f);
		bg1 = BackgroundUtil.getImage("gray", 1170, 45, 380, 795, 0.65f);
		cfilter.add("Alice");
		cfilter.add("");
		cfilter.add("My");
		this.refresh();
	}

	public void refresh() {
		this.clear();
		this.addActor(bg);
		this.addActor(bg1);
		initcardimage();
		for (Actor i : cardimage) {
			this.addActor(i);
		}
		this.add_window_button();
		this.add_groupfilter_button();
		this.add_cost_filter_button();
		if (cfw != null)
			this.addActor(cfw);
		if (editmode)
			this.addActor(deckImage);
		else {
			MyDeck_Window.getInstance().refresh();
			this.addActor(MyDeck_Window.getInstance());
		}
		if (selecter != null)
			this.addActor(selecter);
		// this.addActor(new FontActor(String.valueOf(Start.global.data.faith),
		// Config.Scale * 700, Config.Scale * 60,
		// "textur30"));
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
				final int px = 65 + 255 * i;
				final int py = 480;

				img.addListener(new DragListener() {
					@Override
					public void dragStart(InputEvent event, float x, float y, int pointer) {
						if (editmode) {
							DeckView_Window.smallimg = ImageUtil.getImage(c.data.picPath);
							DeckView_Window.smallimg.setColor(1, 1, 1, 0.8f);
							DeckView_Window.smallimg.setScale(0.5f);
							DeckView_Window.smallimg.setPosition(px + x / 2, py + y / 2);
							DeckView_Window.smallimgx = x / 2;
							DeckView_Window.smallimgy = y / 2;
							DeckView_Window.getInstance().addActor(smallimg);
						}
					}

					@Override
					public void drag(InputEvent event, float x, float y, int pointer) {
						if (editmode) {
							DeckView_Window.smallimg.setPosition(px - smallimgx + x, py - smallimgy + y);
						}
					}

					@Override
					public void dragStop(InputEvent event, float x, float y, int pointer) {
						if (editmode) {
							float X = px + x;
							float Y = py + y;
							if (X > 1170 && X < 1550 && Y > 45 && Y < 690){							
								DeckView_Window.getInstance().deckImage.deck.add(c.ID);
								DeckView_Window.getInstance().deckImage.refresh();
								DeckView_Window.getInstance().refresh();
							}
							DeckView_Window.smallimg = null;	
							DeckView_Window.getInstance().refresh();
						}
					}
				});

				img.flag = true;

			}
			cardimage.add(img);
			if (Start.global.data.card_no.get(cardID.get(i + (page - 1) * 8)) == 0) {
				Image lost = ImageUtil.getImage("graphics/icon/lost.png");
				lost.setColor(75, 50, 50, 0.7f);
				lost.setPosition((106.5f + 255 * i) * Config.Scale, 630 * Config.Scale);
				cardimage.add(lost);
			}
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
				
				final int px = 65 + 255 * i;
				final int py = 100;
				
				img.addListener(new DragListener() {
					@Override
					public void dragStart(InputEvent event, float x, float y, int pointer) {
						if (editmode) {
							DeckView_Window.smallimg = ImageUtil.getImage(c.data.picPath);
							DeckView_Window.smallimg.setColor(1, 1, 1, 0.8f);
							DeckView_Window.smallimg.setScale(0.5f);
							DeckView_Window.smallimg.setPosition(px + x / 2, py + y / 2);
							DeckView_Window.smallimgx = x / 2;
							DeckView_Window.smallimgy = y / 2;
							DeckView_Window.getInstance().addActor(smallimg);
						}
					}

					@Override
					public void drag(InputEvent event, float x, float y, int pointer) {
						if (editmode) {
							DeckView_Window.smallimg.setPosition(px - smallimgx + x, py - smallimgy + y);
						}
					}

					@Override
					public void dragStop(InputEvent event, float x, float y, int pointer) {
						if (editmode) {
							float X = px + x;
							float Y = py + y;
							if (X > 1170 && X < 1550 && Y > 45 && Y < 690){							
								DeckView_Window.getInstance().deckImage.deck.add(c.ID);
								DeckView_Window.getInstance().deckImage.refresh();
								DeckView_Window.getInstance().refresh();
							}
							DeckView_Window.smallimg = null;	
							DeckView_Window.getInstance().refresh();
						}
					}
				});
				
			}
			cardimage.add(img);
			if (Start.global.data.card_no.get(cardID.get(i + 4 + (page - 1) * 8)) == 0) {
				Image lost = ImageUtil.getImage("graphics/icon/lost.png");
				lost.setColor(75, 50, 50, 0.7f);
				lost.setPosition((106.5f + 255 * i) * Config.Scale, 250 * Config.Scale);
				cardimage.add(lost);
			}
		}
	}

	private void add_window_button() {
		Image back;
		if (page == 1) {
			back = ImageUtil.getImage(Config.Icon_Path + "goback1.png");
		} else {
			back = ImageUtil.getImage(Config.Icon_Path + "goback0.png");
			back.addListener(new ClickListener() {
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

		Image front;
		if (page * 8 >= cardID.size()) {
			front = ImageUtil.getImage(Config.Icon_Path + "gofront1.png");
		} else {
			front = ImageUtil.getImage(Config.Icon_Path + "gofront0.png");
			front.addListener(new ClickListener() {
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

		Image toCover = ImageUtil.getImage(Config.Icon_Path + "return0.png");
		toCover.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				Start.windowstate = WindowState.Cover;
			}
		});
		toCover.setPosition(Config.Scale * 1455, Config.Scale * 55);
		this.addActor(toCover);

		if (!editmode) {
			Image groupmode = ImageUtil.getImage("graphics/icon/new.jpg");
			groupmode.setPosition(Config.Scale * 1310, Config.Scale * 55);
			groupmode.setColor(100, 100, 100, 0.85f);
			groupmode.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (Start.global.decks.data.size() < 4) {
						selecter = new HeroSelecter_Window();
						DeckView_Window.getInstance().cfilter.set(2, "My");
						DeckView_Window.getInstance().refresh();
					} else {
						System.out.println("卡组已满");
					}
				}
			});
			this.addActor(groupmode);
		} else {
			Image groupmode = ImageUtil.getImage("graphics/icon/save.jpg");
			groupmode.setPosition(Config.Scale * 1310, Config.Scale * 55);
			groupmode.setColor(100, 100, 100, 0.85f);
			groupmode.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Player p = Start.global;
					DeckView_Window dvw = DeckView_Window.getInstance();
					System.out.println(dvw.deckImage.id);
					if (dvw.deckImage.id >= 0)
						p.decks.data.set(dvw.deckImage.id, dvw.deckImage.deck);
					else
						p.decks.data.add(dvw.deckImage.deck);
					System.out.println("卡组储存成功");
					p.savedata();
					dvw.deckImage.clear();
					dvw.deckImage = null;
					dvw.editmode = false;
					dvw.refresh();
				}
			});
			this.addActor(groupmode);
		}
	}

	private void add_groupfilter_button() {
		Image alice = ImageUtil.getImage("graphics/icon/Alice.jpg");
		alice.setColor(1, 1, 1, 0.85f);
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

		Image cirno = ImageUtil.getImage("graphics/icon/Cirno.jpg");
		cirno.setColor(1, 1, 1, 0.85f);
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

		Image pachi = ImageUtil.getImage("graphics/icon/Pachi.jpg");
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

		Image common = ImageUtil.getImage("graphics/icon/Public.jpg");
		common.setColor(1, 1, 1, 0.85f);
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

		Image makemode;

		if (!editmode) {
			this.addActor(BackgroundUtil.getImage("gray", 1090, 590, 70, 250, 0.65f));
			alice.setPosition(Config.Scale * 1100, Config.Scale * 780);
			cirno.setPosition(Config.Scale * 1100, Config.Scale * 720);
			pachi.setPosition(Config.Scale * 1100, Config.Scale * 660);
			common.setPosition(Config.Scale * 1100, Config.Scale * 600);
			this.addActor(alice);
			this.addActor(cirno);
			this.addActor(pachi);
			this.addActor(common);

			if (viewstate)
				makemode = ImageUtil.getImage("graphics/icon/makeb.jpg");
			else
				makemode = ImageUtil.getImage("graphics/icon/makew.jpg");
			makemode.setPosition(Config.Scale * 1190, Config.Scale * 55);
			makemode.setColor(100, 100, 100, 0.85f);
			makemode.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					SEControler.play(1, "Click");
					if (cfilter.get(2) == null || cfilter.get(2).length() <= 0)
						cfilter.set(2, "My");
					else
						cfilter.set(2, "");
					viewstate = !viewstate;
					refresh();
				}
			});

		} else {
			this.addActor(BackgroundUtil.getImage("gray", 1090, 710, 70, 130, 0.65f));
			String h = deckImage.deck.Hero;
			if (h.equals("ALICE")) {
				alice.setPosition(Config.Scale * 1100, Config.Scale * 780);
				this.addActor(alice);
			} else if (h.equals("CIRNO")) {
				cirno.setPosition(Config.Scale * 1100, Config.Scale * 780);
				this.addActor(cirno);
			} else {
				pachi.setPosition(Config.Scale * 1100, Config.Scale * 780);
				this.addActor(pachi);
			}
			common.setPosition(Config.Scale * 1100, Config.Scale * 720);
			this.addActor(common);

			makemode = ImageUtil.getImage("graphics/icon/delete.jpg");
			makemode.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (deckImage.id != -1) {
						Start.global.decks.data.remove(deckImage.id);
						Start.global.savedata();
					}
					deckImage.clear();
					deckImage = null;
					DeckView_Window.getInstance().editmode = false;
					DeckView_Window.getInstance().refresh();
				}
			});
			makemode.setPosition(Config.Scale * 1190, Config.Scale * 55);
			makemode.setColor(100, 100, 100, 0.85f);
		}

		this.addActor(makemode);

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
