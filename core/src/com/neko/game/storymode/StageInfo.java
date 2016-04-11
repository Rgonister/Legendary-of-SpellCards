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
	
	public static StageInfo getInstance(String path){
		return (StageInfo) DataLoader.loadData(path, StageInfo.class);
	}
	
}
