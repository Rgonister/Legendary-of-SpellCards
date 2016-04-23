package com.neko.ui.window.GameBoard;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.neko.game.duel.Game;
import com.neko.game.item.CardData;
import com.neko.game.item.CardImage;

public class HandCard_Actor extends Group {

	private static final float x = 800;
	private static final float y = -3500;
	private static final float r = 3500;
	private static final float scale = 0.5f;

	public float startdragx;
	public float startdragy;

	public float orgx;
	public float orgy;
	public float rotate;

	public CardImage image;
	public CardData cd;

	public int index;
	public int owner;

	public HandCard_Actor(CardData c, int index, int owner) {
		this.index = index;
		cd = c.clone();
		image = new CardImage(cd);
		image.setScale(scale);
		this.owner = owner;
		addActor(image);
		postioncaculate();
		adddraglistenner(this);
	}

	public void postioncaculate() {
		int size;
		if (owner == 0)
			size = Game.player_me.hand.size();
		else
			size = Game.player_op.hand.size();
		float degree = (2.8f - size * 0.1f) * (index - (size - 1f) / 2f);
		int dx = (int) (r * Math.sin(degree * Math.PI / 360)
				- Math.abs(scale * 233f / 2 * Math.cos(degree * Math.PI / 360)));
		int dy = (int) (r * Math.cos(degree * Math.PI / 360) + scale * 233f / 2 * Math.sin(degree * Math.PI / 360));
		rotate = -(2.8f - size * 0.1f) * (index - (size - 1f) / 2f);
		orgx = x + dx;
		orgy = y + dy;
	}

	public void reset() {
		Action move = Actions.moveTo(orgx, orgy, 0.15f);
		Action rotat = Actions.rotateTo(rotate, 0.15f);
		Action scalea = Actions.scaleTo(scale, scale, 0.15f);
		ParallelAction Paction = Actions.parallel(rotat, move, scalea);
		image.addAction(Paction);
	}

	public void update() {
		image.setRotation(rotate);
		image.setPosition(orgx, orgy);
	}

	public void adddraglistenner(final HandCard_Actor a) {
		this.addListener(new DragListener() {
			@Override
			public void dragStart(InputEvent event, float x, float y, int pointer) {
				if (Game.turn == a.owner) {
					a.setColor(1, 1, 1, 0.5f);
					a.startdragx = x - a.image.getX();
					a.startdragy = y - a.image.getY();
					a.image.setRotation(0f);
				}
			}

			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				if (Game.turn == a.owner) {
					GameBoard_Window gw = GameBoard_Window.getInstance();
					a.image.setPosition(x - a.startdragx, y - a.startdragy);
					if (gw.tempcard == null) {
						gw.tempcard =  new BattleField_Actor(a.cd, 0, 0);
						gw.addActor(gw.tempcard);
					}
				}
			}

			@Override
			public void dragStop(InputEvent event, float x, float y, int pointer) {
				if (Game.turn == a.owner) {
					a.setColor(1, 1, 1, 1f);
					a.reset();
				}
			}
		});
	}

}
