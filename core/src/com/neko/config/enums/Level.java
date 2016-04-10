package com.neko.config.enums;

public enum Level {
	Easy, Normal, Hard, lunatic;

	public int getValue() {
		if (this.toString().equals("Easy"))
			return 1;
		if (this.toString().equals("Normal"))
			return 2;
		if (this.toString().equals("Hard"))
			return 3;
		return 4;
	}

}
