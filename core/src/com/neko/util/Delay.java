package com.neko.util;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

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
	}

	public void call() {

	}
}
