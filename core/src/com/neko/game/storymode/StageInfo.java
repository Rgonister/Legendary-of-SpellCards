package com.neko.game.storymode;

import com.neko.system.data.DataLoader;

public class StageInfo {
	public String heroname;
	public String heroimg;

	public int skillcost;
	public String skilldescription;
	public String skillimg;

	public String rewardtype;
	public int rewardnumber;

	public String deckString;
	public int[] deck;

	public static StageInfo getInstance(String path) {
		StageInfo sinfo = (StageInfo) DataLoader.loadData(path, StageInfo.class);
		if (sinfo.deckString != null && sinfo.deckString.length() > 0) {
			String[] s = sinfo.deckString.replaceAll("{", "").replaceAll("}", "").split(",");
			for (int i = 0; i < s.length; i++) {
				sinfo.deck[i] = Integer.parseInt(s[i]);
			}
		}
		return sinfo;
	}

}
