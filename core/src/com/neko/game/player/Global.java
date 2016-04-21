package com.neko.game.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.neko.config.Config;
import com.neko.system.data.CardFilter;

public class Global {

	public Player_Data data;
	public MyDeck decks;

	public void savedata() {
		try {
			ObjectOutputStream oo = new ObjectOutputStream(
					new FileOutputStream(Gdx.files.getLocalStoragePath() + Config.Save_Path));
			oo.writeObject(data);
			oo.close();

			System.out.println(decks.data.size());
			ObjectOutputStream oo1 = new ObjectOutputStream(
					new FileOutputStream(Gdx.files.getLocalStoragePath() + "Decks.neko"));
			oo1.writeObject(decks);
			oo1.close();

//			for (int i = 0; i < data.card_no.size(); i++) {
//				if (data.card_no.get(i) != 0) {
//					 System.out.print(" ID" + i + ": " + data.card_no.get(i) +
//					 " ");
//				}
//			}
			System.out.println("data 存储成功");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CardFilter.refresh();
	}

	public static Global loaddata() {
		Global p = new Global();
		if (!Gdx.files.local(Config.Save_Path).exists()) {
			System.out.println("存档文件不存在，初始化data对象");
			p.data = new Player_Data();
			p.data.gold = 2500;
		} else {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(new File(Gdx.files.getLocalStoragePath() + Config.Save_Path)));
				p.data = (Player_Data) ois.readObject();
				ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("data 读取成功");

		if (!Gdx.files.local("Decks.neko").exists()) {
			System.out.println("存档文件不存在，初始化data对象");
			p.decks = new MyDeck();
			p.decks.data = new ArrayList<Deck>();
			return p;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File(Gdx.files.getLocalStoragePath() + "Decks.neko")));
			p.decks = (MyDeck) ois.readObject();
			System.out.println(	p.decks.data.size());
			System.out.println("卡组 读取成功");
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return p;
	}

}
