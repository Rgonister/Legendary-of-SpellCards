package com.neko;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.neko.config.enums.WindowState;
import com.neko.game.player.Player;
import com.neko.system.base.ApplicationGame;
import com.neko.system.data.CardFilter;
import com.neko.ui.screen.Screen_Cover;
import com.neko.ui.screen.Screen_DeckManage;
import com.neko.ui.screen.Screen_StoryMode;

public class Start extends ApplicationAdapter {

	public static Screen screen;
	public static ApplicationGame ag;
	public static WindowState windowstate = WindowState.Cover;
	private WindowState temp = WindowState.Cover;
	public static Player global = new Player();

	@Override
	public void create() {
		//ע�ᴰ�ڣ���ʼ��Screen
		screen = Screen_Cover.getInstance();
		ag = new ApplicationGame(null);			
		ag.setScreen(screen);	
		//��ȡ�û��浵��Ϣ
		global = Player.loaddata();
		//���ؿ��ƹ�����
		CardFilter.init();
	}

	@Override
	public void render() {
		if (temp != windowstate) {
			ag.getScreen().dispose();
			switch (windowstate) {
			case Cover:
				screen = Screen_Cover.getInstance();	break;
			case StoryMode:
				screen = Screen_StoryMode.getInstance();	break;
			case DeckManage:
				screen = Screen_DeckManage.getInstance(); 	break;
			}
			ag.setScreen(screen);
			temp = windowstate; // ©�����ֱ�ӱ�ը�����õ��Կ��Ը�
		}
		ag.render();
	}

}
