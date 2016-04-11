package com.neko.game.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neko.config.Config;
import com.neko.config.enums.Level;

public class Player_Data implements Serializable {

	/*
	 * 用户数据
	 */
	private static final long serialVersionUID = 8681491468262183237L;

	public int gold;
	public int faith;

	// ---------------------Story Mode 进度 ------------------//
	public int chapter_unlocked;
	public Map<Integer, List<Level>> stage_progress;

	// ---------------------拥有的卡牌 -------------------------//
	public ArrayList<Integer> card_no;

	public Player_Data() {
		gold = 0;
		faith = 0;
		chapter_unlocked = 1;
		List<Level> l = new ArrayList<Level>();
		stage_progress = new HashMap<Integer, List<Level>>();
		for (int i = 0; i < 6; i++) {
			l.add(Level.Easy);
		}

		for (int i = 1; i <= Config.Chapter_Number; i++) {
			stage_progress.put(i, l);
		}

		card_no = new ArrayList<Integer>();		
		for (int i = 0;i < 100;i++){
			card_no.add(0);
		}
	}	
}
