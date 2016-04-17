package com.neko.system.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.neko.Start;

public class CardFilter {
	public static final String path = "data/filter.neko";

	public static Map<String, List<Integer>> filter = new HashMap<String, List<Integer>>();

	public static void init() {
		String[] ss = Gdx.files.internal(path).readString().replaceAll("\r\n", "").split(";");
		for (String s : ss) {
			String[] s0 = s.split(":");
			List<Integer> l = new ArrayList<Integer>();
			if (s0[1].contains("-")) {
				int min = Integer.parseInt(s0[1].split("-")[0]);
				int max = Integer.parseInt(s0[1].split("-")[1]);
				for (int i = min; i <= max; i++) {
					l.add(new Integer(i));
				}
			} else {
				for (String s1 : s0[1].split(",")) {
					l.add(Integer.parseInt(s1));
				}
			}
			filter.put(s0[0], l);
		}
		List<Integer> ls = new ArrayList<Integer>();
		for (Integer i = 1; i < Start.global.data.card_no.size(); i++) {
			if (Start.global.data.card_no.get(i) > 0)
				ls.add(i);
		}
		System.out.println(ls.size());
		filter.put("My", ls);
		System.out.println("filter ×°ÔØ³É¹¦");
		System.out.println("----------------");
		for (String k : filter.keySet()) {
			List<Integer> l = filter.get(k);
			System.out.println(k + " : " + l.get(0) + " - " + l.get(l.size() - 1));
		}
		System.out.println("----------------");
	}

	public static void refresh(){
		List<Integer> ls = new ArrayList<Integer>();
		for (Integer i = 1; i < Start.global.data.card_no.size(); i++) {
			if (Start.global.data.card_no.get(i) > 0)
				ls.add(i);
		}
		filter.put("My", ls);
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

	public static List<Integer> getlistwithfilter(List<String> ss) {
		if (ss.size() == 1)
			return filter.get(ss.get(0));
		List<Integer> l = new ArrayList<Integer>();
		for (Integer integ : filter.get(ss.get(0))) {
			boolean flag = true;
			for (int i = 1; i < ss.size(); i++) {
				if (ss.get(i).equals("") || ss.get(i).length() <= 0)
					continue;
				if (!filter.get(ss.get(i)).contains(integ)) {
					flag = false;
					break;
				}
			}
			if (flag)
				l.add(integ);
		}
		return l;
	}
}
