package com.neko.system.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class CardFilter {
	public static final String path = "data/card/filter.neko";

	public static Map<String, List<Integer>> filter = new HashMap<String, List<Integer>>();

	public static void init() {
		String[] ss = Gdx.files.internal(path).readString().replaceAll("\r\n", "").split(";");
		for (String s : ss) {
			String[] s0 = s.split(":");
			List<Integer> l = new ArrayList<Integer>();
			for (String s1 : s0[1].split(",")) {
				l.add(Integer.parseInt(s1));
			}
			filter.put(s0[0], l);
		}
	}

	public static int getCard(String[] param) {
		int count = 0;
		do {
			boolean flag = true;
			count++;
			if (count >= 450)
				return 0;
			Integer i = getRandom(filter.get(param[0]));
			for (String s : param) {
				if (!filter.get(s).contains(i))
					flag = false;
			}
			if (flag == true)
				return i;
		} while (true);
	}

	public static int getCard(String param) {
		return getRandom(filter.get(param));
	}

	private static Integer getRandom(List<Integer> l) {
			return l.get((int) (Math.random() * l.size()));			
	}
	
}
