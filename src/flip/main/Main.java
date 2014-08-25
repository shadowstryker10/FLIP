package flip.main;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import flip.files.FileHandler;
import flip.files.Textures;
import flip.game.Game;
import flip.game.sound.Sound;
import flip.game.world.Levels;
import flip.mainmenu.MainMenu;
import flip.util.Settings;
import flip.util.Timer;

public class Main
{
	public static GameState state;
	public Timer timer;
	public MainMenu menu;
	public Game game;
	public static Main instance;

	public void start()
	{
		init();
		if(!Settings.MUTE) Sound.playSound(Sound.MAIN_MUSIC);
		mainLoop();
	}

	public void mainLoop()
	{
		
		while (!Display.isCloseRequested())
		{
			if (state == GameState.INTRO)
			{
				state = GameState.PREINIT;
			}
			
			if(state == GameState.PREINIT)
			{
				menu = new MainMenu();
				state = GameState.MAIN_MENU;
			}

			if (state == GameState.MAIN_MENU)
			{
				glClear(GL_COLOR_BUFFER_BIT);
				menu.update();
				
				if(menu.isStartRequested())
				{
					game = new Game();
					state = GameState.GAME;
				}
			}

			if (state == GameState.GAME)
			{
				glClear(GL_COLOR_BUFFER_BIT);
				game.update();
			}

			update();
		}

		dispose();
	}

	public void update()
	{
		timer.updateFPS();
		Sound.update();
		
		Display.update();
		Display.sync(120);
	}

	public void init()
	{
		initWindow();
		initDisplay();
		initObjects();

		timer.getDelta();
	}

	private void initDisplay()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	private void initObjects()
	{
		instance = this;
		state = GameState.INTRO;
		timer = new Timer();
		Textures.loadTextures();
		Levels.loadLevels();
		Settings.MUTE = false;
	}

	private void initWindow()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(896, 768));
			Display.setTitle("FLIP!");
			//Display.setIcon(new ByteBuffer[] {new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("res/menu/icon.png")), false, false, null)});
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}

	public static void dispose()
	{
		Display.destroy();
		Sound.closeClips();
		System.exit(0);
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.start();
	}
}
