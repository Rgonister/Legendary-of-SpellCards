package com.neko.system.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.neko.game.item.Card;
import com.neko.game.item.CardData;

public class CardLoader {

	public static final String Path = "data/card/";
	
	public static List<Card> load() {
		List<Card> l = new ArrayList<Card>();
		l.addAll(loadData(read("alice")));
		return l;
	}

	private static String[] read(String s){
		String str = Gdx.files.internal(Path+s+".neko").readString();
		return str.replaceAll(" ", "").replaceAll("\r\n", "").replaceAll("\t", "").replaceAll("\\{", "").split("\\}");
	}
	
	private static List<Card> loadData(String[] ss) {
		List<Card> l = new ArrayList<Card>();
		for (String strs : ss) {
			CardData c = new CardData();
			String[] string = strs.split("#");
			Map<String, String> data = new HashMap<String, String>();
			for (String s : string) {
				String[] str = s.split("\\|");
				data.put(str[0], str[1]);
			}
			Field[] f = CardData.class.getFields();
			try {
				for (Field field : f) {
					String str = data.get(field.getName());
					if (str == null || str.length() <= 0)
						continue;
					if (field.getType().equals(String.class)) {
						field.set(c, str);
					} else {
						field.set(c, Integer.parseInt(str));
					}
				}
			} catch (Exception e) {
				System.out.println("×°ÔØfieldÊ§°Ü:" + e.getMessage());
			}
			l.add(new Card(c));
		}
		return l;
	}
	
	
}
