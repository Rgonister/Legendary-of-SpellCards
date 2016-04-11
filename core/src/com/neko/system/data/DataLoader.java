package com.neko.system.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.neko.game.storymode.StageInfo;

public class DataLoader {

	public static StageInfo loadStageData(String path) {
		StageInfo sinfo = new StageInfo();
		String[] string = Gdx.files.internal(path).readString().split("#");
		Map<String, String> data = new HashMap<String, String>();
		for (String s : string) {
			String[] str = s.split("\\|");
			data.put(str[0], str[1]);
		}
		Field[] f = StageInfo.class.getFields();
		try {
			for (Field field : f) {
				String str = data.get(field.getName());
				if (field.getType().equals(String.class)) {
					field.set(sinfo, str);
				} else {
					field.set(sinfo, Integer.parseInt(str));
				}
			}
		} catch (Exception e) {
			System.out.println("×°ÔØfieldÊ§°Ü:" + e.getMessage());
		}
		return sinfo;
	}


}
