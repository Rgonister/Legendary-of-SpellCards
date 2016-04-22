package com.neko.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.neko.Start;
import com.neko.config.Config;

public class DesktopLauncher {

	// -----------------------WARNING--------------------------
	// ǰ���ڰ�����
	// ����ǰ����׼��
	// -----------------------WARNING--------------------------

	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = init();
		if (Config.samples)
			config.samples = 8;// ���������
		new LwjglApplication(new Start(), config);
	}

	private static LwjglApplicationConfiguration init() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Config.Window_Size_Height;
		config.width = Config.Window_Size_Width;
		config.title = Config.Title;
		return config;
	}
}
