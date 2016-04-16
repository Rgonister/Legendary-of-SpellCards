package com.neko.game.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.neko.config.Config;

public class Player {

	public Player_Data data;

	public void savedata() {
		try {
			ObjectOutputStream oo = new ObjectOutputStream(
					new FileOutputStream(Gdx.files.getLocalStoragePath() + Config.Save_Path));
			oo.writeObject(data);
			System.out.println("data 存储成功");
			System.out.println("gold : " + data.gold + "faith : " + data.faith);
			for (int i = 0; i < data.card_no.size(); i++) {
				if (data.card_no.get(i) != 0) {
					//System.out.print(" ID" + i + ": " + data.card_no.get(i) + " ");
				}
			}
			oo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Player loaddata() {
		Player p = new Player();
		if (!Gdx.files.local(Config.Save_Path).exists()) {
			System.out.println("存档文件不存在，初始化data对象");
			p.data = new Player_Data();
			p.data.gold = 2500;
			return p;
		}
		try {
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File(Gdx.files.getLocalStoragePath() + Config.Save_Path)));
			p.data = (Player_Data) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("data 读取成功");
		return p;
	}

}
