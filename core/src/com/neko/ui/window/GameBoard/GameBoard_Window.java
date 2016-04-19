package com.neko.ui.window.GameBoard;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.neko.game.duel.Game;
import com.neko.util.ImageUtil;
import com.neko.util.SEControler;

public class GameBoard_Window extends Group {

	private static GameBoard_Window instance = null;

	public static Game game;

	public static GameBoard_Window getInstance() {
		if (instance == null) {
			synchronized (GameBoard_Window.class) {
				if (instance == null) {
					instance = new GameBoard_Window();
				}
			}
		}
		return instance;
	}

	private GameBoard_Window() {
	}

	public void refresh() {
		this.clear();

		this.addActor(ImageUtil.getImage("graphics/bg.jpg", false));

		Image myHero = ImageUtil.getImage("graphics/deck/" + Game.player_me.Hero + ".jpg");
		myHero.setPosition(0, 0);
		this.addActor(myHero);

		Image opHero = ImageUtil.getImage("graphics/deck/" + Game.player_op.Hero + ".jpg");
		opHero.setPosition(1250, 740);
		this.addActor(opHero);

		// ---------¼ÓÔØÊÖÅÆÇø-----------
		this.addActor(new Hand_View(Game.player_me.hand));
		Group g = new Hand_View(Game.player_op.hand);
		g.setPosition(g.getX(), g.getY() + 750);
		this.addActor(g);
		// this.addActor(new Hand_View(Game.player_op.hand));
		if (Game.player_me.mydeck.size() > 0) {
			Image img = ImageUtil.getImage("graphics/card/back.png");
			img.setPosition(1367, 80);
			this.addActor(img);
		}
		if (Game.player_op.mydeck.size() > 0) {
			Image img = ImageUtil.getImage("graphics/card/back.png");
			img.setPosition(0, 470);
			this.addActor(img);
		}

		// Image img1 = ImageUtil.getImage("graphics/card/border.png");
		// img1.setPosition(606.1821f,-13.210327f);
		// img1.setScale(0.5f);
		// img1.setRotation(-20);
		// this.addActor(img1);

		// int length = Game.player_me.hand.size();
		//
		// Image border1 = ImageUtil.getImage("graphics/card/border.png");
		// this.addActor(border1);

		// for (int i = 0 ; i<=3;i++){
		// Image border = ImageUtil.getImage("graphics/card/border.png");
		// border.setPosition(i*150, 100);
		// this.addActor(border);
		// }
		// System.out.println("length:"+length);
		// for (int i = 0; i < length; i++) {
		// System.out.println("---------------------------------------------------");
		// Image border = ImageUtil.getImage("graphics/card/border.png");
		// border.setPosition(i*150, 100);
		// border.setScale(0.5f);
		// positoncaculate(border, (10 - length * 0.5f) * (i - (length - 1f) /
		// 2f));
		// border.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
		// Image img = ImageUtil.getImage(Game.player_me.hand.get(i).picPath);
		// img.setScale(0.5f);
		// positoncaculate(img, (10 - length * 0.5f) * (i - (length - 1f) /
		// 2f));
		// img.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
		// this.addActor(img);
		// this.addActor(border);
		// this.addActor(ImageUtil.getImage("graphics/card/border.png"));
		// String name = l.get(i).NAME;
		// int h = 18;
		// String s = name.replaceAll("-", "").replaceAll("\\(",
		// "").replaceAll("\\)", "");
		// int w = s.length() * h + (name.length() - s.length()) * 5;
		// Actor a = new FontActor(name, (233 - w) / 2, 61, "SJ");
		// positoncaculate(a, (10 - length * 0.5f) * (i - (length - 1f) /
		// 2f));
		// a.setRotation(-(10 - length * 0.5f) * (i - (length - 1f) / 2f));
		// this.addActor(a);
		// }

		// System.out.println(this.getChildren().size + "---");
		//
		//
		// for (int i = 0; i <= 7; i++) {
		// final int num = i;
		// String path = "graphics/numbers/" + i + "w.jpg";
		// Image image = ImageUtil.getImage(path);
		// image.setWidth(0.75f * image.getWidth());
		// image.setHeight(35);
		// image.setPosition(65 + i * 42, 55);
		// this.addActor(image);
		// }

		game.check();
	}

	public static void init(Game g) {
		GameBoard_Window.game = g;
	}

	private void positoncaculate(Image img, double degree) {
		float x = 800;
		float y = -1300;
		float r = 1300;
		float scale = 0.5f;

		float dx = (float) (r * Math.sin(degree * Math.PI / 360)
				- Math.abs(scale * img.getWidth() / 2 * Math.cos(degree * Math.PI / 360)));
		float dy = (float) (r * Math.cos(degree * Math.PI / 360)
				+ scale * img.getWidth() / 2 * Math.sin(degree * Math.PI / 360));

		img.setPosition(x + dx, y + dy);
	}

}
