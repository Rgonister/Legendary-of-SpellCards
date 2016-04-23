package com.neko.util;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.neko.Start;
import com.neko.system.base.component.Screen_Window;

public class Delay extends Actor {

	public float time;

	public Delay(float delay) {
		super();
		time = delay;
		Action acy = Actions.delay(time);
		Action end = Actions.run(new Runnable() {
			@Override
			public void run() {
				call();
			}
		});
		SequenceAction seq = Actions.sequence(acy, end);
		this.addAction(seq);
		
		Screen_Window sc = (Screen_Window) Start.ag.getScreen();
		sc.stage.addActor(this);
	}

	public void call() {

	}
}
