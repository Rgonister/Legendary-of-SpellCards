package com.neko.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.neko.config.Config;

public class SEControler {

	private static Sound s;

	public static void play(float degree, String name) {
		if (!Config.SE_ON)
			return;
		if (s != null) {
			s.dispose();
		}
		String path = Config.SE_Path + name + ".wav";
		s = Gdx.audio.newSound(Gdx.files.internal(path));
		s.play(degree);
	}
	
	public static void play(float degree, String name,String name1) {
		if (!Config.SE_ON)
			return;
		if (s != null) {
			s.dispose();
		}
		String path = Config.SE_Path + name + "name1";
		s = Gdx.audio.newSound(Gdx.files.internal(path));
		s.play(degree);
	}
}
