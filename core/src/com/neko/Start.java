package com.neko;

import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.neko.config.enums.WindowState;
import com.neko.game.item.Card;
import com.neko.game.player.Player;
import com.neko.system.base.ApplicationGame;
import com.neko.system.data.CardFilter;
import com.neko.system.data.CardLoader;
import com.neko.ui.screen.Screen_Cover;
import com.neko.ui.screen.Screen_DeckManage;
import com.neko.ui.screen.Screen_Shop;
import com.neko.ui.screen.Screen_StoryMode;
import com.neko.util.FontUtil;
import com.neko.util.MyTextInputListener;

public class Start extends ApplicationAdapter {

	public static Screen screen;
	public static ApplicationGame ag;
	public static WindowState windowstate = WindowState.Cover;
	private WindowState temp = WindowState.Cover;
	public static Player global = new Player();
	public static Map<Integer,Card> cards;

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
		//���ؿ�����Ϣ
		cards = CardLoader.load();
		FontUtil.init();
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
			case Shop :
				screen = Screen_Shop.getInstance();		break;
			}
			ag.setScreen(screen);
			temp = windowstate; // ©�����ֱ�ӱ�ը�����õ��Կ��Ը�
		}
		ag.render();
	}

}
