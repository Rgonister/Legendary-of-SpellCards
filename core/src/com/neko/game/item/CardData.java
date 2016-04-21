package com.neko.game.item;

public class CardData implements Cloneable {
	public int ID;
	public String NAME;
	public String RARITY;
	public String GROUP;
	public String TYPE;
	public String RACE = "";
	public int COST;
	public int ATK;
	public int LIFE;
	public String DIS = "";
	public String picPath;
	public Ability[] ability;

	public Object clone() {
		CardData o = null;
		try {
			o = (CardData) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
