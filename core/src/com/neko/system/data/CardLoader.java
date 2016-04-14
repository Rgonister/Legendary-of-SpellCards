package com.neko.system.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.neko.game.item.CardData;

public class CardLoader {

	public static List<CardData> load(String path){

		String[] str = Gdx.files.internal(path).readString().replaceAll(" ", "").replaceAll("\r\n", "").replaceAll("\t", "").replaceAll("\\{", "").split("\\}");
		return loadData(str);
	}
	
	
	private static List<CardData> loadData(String[] ss) {
		List<CardData> l = new ArrayList<CardData>();
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
						field.set(c, Integer.parseInt(str));// only for
															// string & int 
															// need furthur develop
					}
				}
			} catch (Exception e) {
				System.out.println("×°ÔØfieldÊ§°Ü:" + e.getMessage());
			}
			l.add(c);
		}
		return l;
	}
}
