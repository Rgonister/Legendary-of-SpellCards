package com.neko.config;

public class Config {

	public static final String Version = "0.01.02.2016.4.7";
	public static final String Title = "Legendary Of SpellCards";

	public static final boolean Filter_Linear = false;

	public static final int Window_Size_Width = 1600;
	public static final int Window_Size_Height = 900;

//	 public static final int Window_Size_Width = 800;
//	 public static final int Window_Size_Height = 450;

	// public static final int Window_Size_Width = 1920;
	// public static final int Window_Size_Height = 1080;

	public static  float ScaleX = (Window_Size_Width / 1600f);
	public static  float ScaleY = (Window_Size_Height / 900f);
	
	public static final String Cover_Bg_Image_Path = "graphics/Reisen.jpg";
	
	public static final String StoryMode_Path = "graphics/StoryMode/";
	public static final String Chapter_Image_Path = "graphics/StoryMode/Chapter";
	public static final String StoryMode_Data_Path = "data/storymode/chapter";
	
	public static final int Chapter_Number = 6;
	public static final int Current_Chapter = 1;

	public static final String Icon_Path = "graphics/icon/";

	public static final String Save_Path = "data.neko";
	
	public static final String SE_Path ="SE/";
	public static final boolean SE_ON = true;
	
	public static final String Data_Path_Storymode = "data/storymode/";
	
	public static String getSMode_Data_Path(int Chapter,int Stage){
		return (StoryMode_Data_Path+Chapter+"stage"+Stage+".neko");
	}
}
