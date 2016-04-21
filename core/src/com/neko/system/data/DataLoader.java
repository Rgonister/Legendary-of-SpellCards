package com.neko.system.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class DataLoader {
	
	public static Object loadData(String path, Class<?> c) {
		Object o = new Object();
		try {
			o = c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String[] string = Gdx.files.internal(path).readString().split("#");
		Map<String, String> data = new HashMap<String, String>();
		for (String s : string) {
			String[] str = s.split("\\|");
			data.put(str[0], str[1]);
		}
		Field[] f = c.getFields();
		try {
			for (Field field : f) {
				String str = data.get(field.getName());
				if (str == null || str.length() <= 0)
					continue;
				if (field.getType().equals(String.class)) {
					field.set(o, str);
				} else {
					field.set(o, Integer.parseInt(str));
				}
			}
		} catch (Exception e) {
			System.out.println("×°ÔØfieldÊ§°Ü:" + e.getMessage());
		}
		return o;
	}

	public static List<Object> loadData(String[] ss, Class<?> c) {
		List<Object> l = new ArrayList<Object>();
		for (String strs : ss) {
			Object o = new Object();
			try {
				o = c.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			String[] string = strs.split("#");
			Map<String, String> data = new HashMap<String, String>();
			for (String s : string) {
				String[] str = s.split("\\|");
				data.put(str[0], str[1]);
			}
			Field[] f = c.getFields();
			try {
				for (Field field : f) {
					String str = data.get(field.getName());
					if (str == null || str.length() <= 0)
						continue;
					if (field.getType().equals(String.class)) {
						field.set(o, str);
					} else {
						field.set(o, Integer.parseInt(str));
					}
				}
			} catch (Exception e) {
				System.out.println("×°ÔØfieldÊ§°Ü:" + e.getMessage());
			}
			l.add(o);
		}
		return l;
	}
}
