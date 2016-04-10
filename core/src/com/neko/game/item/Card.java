package com.neko.game.item;

import com.neko.config.enums.Rarity;

public abstract class Card {
	public int id;
	public Rarity rarity;
	public int group;
	public int Type;
	public int raceID;
	public String raceString;
	public int cost;
	public int atk;
	public int life;
	public String picPath;
	public Ability[] ability;
}
