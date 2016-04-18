package com.neko.ui.window.DeckManage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.config.Config;
import com.neko.game.player.Deck;
import com.neko.system.base.component.FontActor;
import com.neko.util.BackgroundUtil;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class HeroSelecter_Window extends Group {

	public HeroSelecter_Window() {
		this.addActor(BackgroundUtil.getImage("gray", 1600, 900, 0.4f));
		this.addActor(BackgroundUtil.getImage("black", 250, 320, 1100, 280, 0.6f));
		this.addActor(BackgroundUtil.getImage("white", 250, 320, 1100, 280, 0.2f));
		Image img = ImageUtil.getImage("graphics/deck/Alice.jpg");
		img.setPosition(260, 350);
		img.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				DeckView_Window dvw = DeckView_Window.getInstance();
				Deck d = new Deck("Alice", "ALICE");
				dvw.cfilter.set(0, "Alice");
				dvw.deckImage = new DeckImage(d);
				dvw.selecter.clear();
				dvw.selecter = null;
				dvw.editmode = true;
				dvw.refresh();
			}
		});
		
		Image img1 = ImageUtil.getImage("graphics/deck/Cirno.jpg");
		img1.setPosition(620, 350);
		img1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				DeckView_Window dvw = DeckView_Window.getInstance();
				Deck d = new Deck("Cirno", "CIRNO");
				dvw.cfilter.set(0, "Cirno");
				dvw.deckImage = new DeckImage(d);
				dvw.selecter.clear();
				dvw.selecter = null;
				dvw.editmode = true;
				dvw.refresh();
			}
		});
		
		Image img2 = ImageUtil.getImage("graphics/deck/Pachi.jpg");
		img2.setPosition(990, 350);
		img2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SEControler.play(1, "Click");
				DeckView_Window dvw = DeckView_Window.getInstance();
				Deck d = new Deck("Pachi", "PACHI");
				dvw.cfilter.set(0, "Pachi");
				dvw.deckImage = new DeckImage(d);
				dvw.selecter.clear();
				dvw.selecter = null;
				dvw.editmode = true;
				dvw.refresh();
			}
		});
		
		this.addActor(img);
		this.addActor(img1);
		this.addActor(img2);

		this.addActor(new FontActor("×Ô  »ú  Ñ¡  Ôñ", Config.Scale * 695, Config.Scale * 560, "sf30"));
	}
}
