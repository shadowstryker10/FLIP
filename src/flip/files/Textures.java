package flip.files;


import org.newdawn.slick.opengl.Texture;

public class Textures
{
	public static Texture WHITE_1, WHITE_2, WHITE_3, WHITE_4, WHITE_5, WHITE_6, WHITE_7, WHITE_8, WHITE_9, WHITE_0;
	public static Texture BLACK_1, BLACK_2, BLACK_3, BLACK_4, BLACK_5, BLACK_6, BLACK_7, BLACK_8, BLACK_9, BLACK_0;
	public static Texture MAIN_MENU, MAIN_MENU_ABOUT, PAUSE_MENU, OPTIONS_MENU;
	public static Texture OVERLAY_BUTTON_START, OVERLAY_BUTTON_ABOUT, OVERLAY_BUTTON_EXIT;
	public static Texture GAME_BACKGROUND, GAME_COMPLETION;
	public static Texture PLAYER_DEATH, PLAYER_LIFE;
	public static Texture WALL_WHITE, WALL_BLACK, WALL_GOAL;
	public static Texture OVERLAY_WIN_LEVEL;
	public static Texture OVERLAY_LEVEL1_TEXT, OVERLAY_LEVEL2_TEXT, OVERLAY_LEVEL3_TEXT, OVERLAY_LEVEL4_TEXT, OVERLAY_LEVEL5_TEXT, OVERLAY_LEVEL10_TEXT;
	public static Texture OVERLAY_PAUSE_RESUME, OVERLAY_PAUSE_SKIP, OVERLAY_PAUSE_OPTIONS, OVERLAY_PAUSE_BACK;
	public static Texture OVERLAY_OPTIONS_MUTE, OVERLAY_OPTIONS_BACK, OVERLAY_OPTIONS_CHECK;
	
	public static void loadTextures()
	{
		MAIN_MENU = FileHandler.loadTexture("menu/mainmenu");
		MAIN_MENU_ABOUT = FileHandler.loadTexture("menu/mainmenu_about");
		OVERLAY_BUTTON_START = FileHandler.loadTexture("menu/overlays/start");
		OVERLAY_BUTTON_ABOUT = FileHandler.loadTexture("menu/overlays/about");
		OVERLAY_BUTTON_EXIT = FileHandler.loadTexture("menu/overlays/exit");

		GAME_BACKGROUND = FileHandler.loadTexture("game/terrain/background");
		GAME_COMPLETION = FileHandler.loadTexture("game/terrain/completion");

		PAUSE_MENU = FileHandler.loadTexture("menu/pausemenu");
		OPTIONS_MENU = FileHandler.loadTexture("menu/optionsmenu");

		PLAYER_DEATH = FileHandler.loadTexture("game/characters/player_black");
		PLAYER_LIFE = FileHandler.loadTexture("game/characters/player_white");

		WALL_WHITE = FileHandler.loadTexture("game/terrain/wall_white");
		WALL_BLACK = FileHandler.loadTexture("game/terrain/wall_black");
		WALL_GOAL = FileHandler.loadTexture("game/terrain/wall_goal");

		OVERLAY_WIN_LEVEL = FileHandler.loadTexture("game/terrain/overlays/win");
		OVERLAY_LEVEL1_TEXT = FileHandler.loadTexture("game/terrain/overlays/level1_text");
		OVERLAY_LEVEL2_TEXT = FileHandler.loadTexture("game/terrain/overlays/level2_text");
		OVERLAY_LEVEL3_TEXT = FileHandler.loadTexture("game/terrain/overlays/level3_text");
		OVERLAY_LEVEL4_TEXT = FileHandler.loadTexture("game/terrain/overlays/level4_text");
		OVERLAY_LEVEL5_TEXT = FileHandler.loadTexture("game/terrain/overlays/level5_text");
		OVERLAY_LEVEL10_TEXT = FileHandler.loadTexture("game/terrain/overlays/level10_text");

		OVERLAY_PAUSE_RESUME = FileHandler.loadTexture("menu/overlays/pause/resume");
		OVERLAY_PAUSE_SKIP = FileHandler.loadTexture("menu/overlays/pause/skip_level");
		OVERLAY_PAUSE_OPTIONS = FileHandler.loadTexture("menu/overlays/pause/options");
		OVERLAY_PAUSE_BACK = FileHandler.loadTexture("menu/overlays/pause/back_to_menu");

		OVERLAY_OPTIONS_MUTE = FileHandler.loadTexture("menu/overlays/pause/options_mute");
		OVERLAY_OPTIONS_BACK = FileHandler.loadTexture("menu/overlays/pause/options_back");
		OVERLAY_OPTIONS_CHECK = FileHandler.loadTexture("menu/overlays/pause/options_check");

		WHITE_1 = FileHandler.loadTexture("game/terrain/overlays/numbers/1_white");
		WHITE_2 = FileHandler.loadTexture("game/terrain/overlays/numbers/2_white");
		WHITE_3 = FileHandler.loadTexture("game/terrain/overlays/numbers/3_white");
		WHITE_4 = FileHandler.loadTexture("game/terrain/overlays/numbers/4_white");
		WHITE_5 = FileHandler.loadTexture("game/terrain/overlays/numbers/5_white");
		WHITE_6 = FileHandler.loadTexture("game/terrain/overlays/numbers/6_white");
		WHITE_7 = FileHandler.loadTexture("game/terrain/overlays/numbers/7_white");
		WHITE_8 = FileHandler.loadTexture("game/terrain/overlays/numbers/8_white");
		WHITE_9 = FileHandler.loadTexture("game/terrain/overlays/numbers/9_white");
		WHITE_0 = FileHandler.loadTexture("game/terrain/overlays/numbers/0_white");

		BLACK_1 = FileHandler.loadTexture("game/terrain/overlays/numbers/1_black");
		BLACK_2 = FileHandler.loadTexture("game/terrain/overlays/numbers/2_black");
		BLACK_3 = FileHandler.loadTexture("game/terrain/overlays/numbers/3_black");
		BLACK_4 = FileHandler.loadTexture("game/terrain/overlays/numbers/4_black");
		BLACK_5 = FileHandler.loadTexture("game/terrain/overlays/numbers/5_black");
		BLACK_6 = FileHandler.loadTexture("game/terrain/overlays/numbers/6_black");
		BLACK_7 = FileHandler.loadTexture("game/terrain/overlays/numbers/7_black");
		BLACK_8 = FileHandler.loadTexture("game/terrain/overlays/numbers/8_black");
		BLACK_9 = FileHandler.loadTexture("game/terrain/overlays/numbers/9_black");
		BLACK_0 = FileHandler.loadTexture("game/terrain/overlays/numbers/0_black");
	}
}
