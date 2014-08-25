package flip.game;

import static org.lwjgl.opengl.GL11.*;

import java.lang.reflect.Field;

import org.newdawn.slick.opengl.Texture;

import flip.files.Textures;
import flip.game.entities.player.PlayerBlack;
import flip.game.entities.player.PlayerWhite;
import flip.game.entities.terrain.Obstacles;
import flip.game.entities.terrain.WallGoal;
import flip.game.sound.Sound;
import flip.game.world.Level;
import flip.game.world.Levels;

public class Game
{
	GameInputHandler input;
	PauseMenu pause_menu;
	
	public PlayerBlack p1;
	public PlayerWhite p2;
	
	public boolean wonLevel, wonLevelContinue;
	public int currentLevel;
	public boolean inPauseMenu;
	public boolean gameCompleted;
	
	public Game()
	{
		input = new GameInputHandler(this);
		pause_menu = new PauseMenu(this);
		p1 = new PlayerBlack();
		p2 = new PlayerWhite();
		currentLevel = 0;
		wonLevel = false;
		wonLevelContinue = false;
		inPauseMenu = false;
		spawnNextLevel();
	}
	
	public void update()
	{
		input.update();
		if(!gameCompleted)
		{
			render();
			
			if(!inPauseMenu)
			{	
				if(((WallGoal) Obstacles.getAllWorldWall(0)).isPlayerOn() == true)
				{
					if(((WallGoal) Obstacles.getAllWorldWall(1)).isPlayerOn() == true)
					{
						wonLevel = true;
					}
				}

				if(wonLevel)
				{
					processWin();
				}
			}
			else
			{
				pause_menu.update();
			}
		}
		else
		{
			renderCompletionScreen();
		}
	}
	
	public void renderCompletionScreen()
	{
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, Textures.GAME_COMPLETION.getTextureID());
		glBegin(GL_QUADS);
		{	
			glTexCoord2d(0, 0);
			glVertex2d(0, 0);

			glTexCoord2d(0, 1);
			glVertex2d(0, Textures.GAME_COMPLETION.getImageHeight());

			glTexCoord2d(1, 1);
			glVertex2d(Textures.GAME_COMPLETION.getImageWidth(), Textures.GAME_COMPLETION.getImageHeight());

			glTexCoord2d(1, 0);
			glVertex2d(Textures.GAME_COMPLETION.getImageWidth(), 0);
		}
		glEnd();
		glPopMatrix();
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public void spawnNextLevel()
	{
		try
		{
			Obstacles.clearAllObstacles();
			currentLevel++;
			Level level = Levels.getLevel("level_" + Integer.toString(currentLevel));
			level.load();
			p1.resetTranslations();
			p2.resetTranslations();
			p1.setCoords(level.getPlayerBlackSpawn().width, level.getPlayerBlackSpawn().height);
			p2.setCoords(level.getPlayerWhiteSpawn().width, level.getPlayerWhiteSpawn().height);	
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
			gameCompleted = true;
			Sound.playCompletionSound();
		}
	}
	
	private void processWin()
	{
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, Textures.OVERLAY_WIN_LEVEL.getTextureID());
		glBegin(GL_QUADS);
		{	
			glTexCoord2d(0, 0);
			glVertex2d(0, 0);

			glTexCoord2d(0, 1);
			glVertex2d(0, Textures.GAME_BACKGROUND.getImageHeight());

			glTexCoord2d(1, 1);
			glVertex2d(Textures.GAME_BACKGROUND.getImageWidth(), Textures.GAME_BACKGROUND.getImageHeight());

			glTexCoord2d(1, 0);
			glVertex2d(Textures.GAME_BACKGROUND.getImageWidth(), 0);
		}
		glEnd();
		glPopMatrix();
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		drawNumericalString(92, 13, (currentLevel / 10), true);
		drawNumericalString(112, 13, (currentLevel % 10), true);
		
		if(wonLevelContinue)
		{
			spawnNextLevel();
			
			wonLevel = false;
			wonLevelContinue = false;
		}
	}
	
	private void render()
	{
		renderBackground();
		renderObstacles();
		renderPlayers();
		renderOverlays();
	}
	
	private void renderBackground()
	{
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, Textures.GAME_BACKGROUND.getTextureID());
		glBegin(GL_QUADS);
		{	
			glTexCoord2d(0, 0);
			glVertex2d(0, 0);

			glTexCoord2d(0, 1);
			glVertex2d(0, Textures.GAME_BACKGROUND.getImageHeight());

			glTexCoord2d(1, 1);
			glVertex2d(Textures.GAME_BACKGROUND.getImageWidth(), Textures.GAME_BACKGROUND.getImageHeight());

			glTexCoord2d(1, 0);
			glVertex2d(Textures.GAME_BACKGROUND.getImageWidth(), 0);
		}
		glEnd();
		glPopMatrix();
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	private void renderObstacles()
	{
		Obstacles.update(this);
	}
	
	private void renderPlayers()
	{
		p1.update();
		p2.update();
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private void renderOverlays()
	{	
		Field field;
		try
		{
			field = Textures.class.getDeclaredField("OVERLAY_LEVEL" + currentLevel + "_TEXT");
			Texture texture = (Texture) field.get(null);
		
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		glBegin(GL_QUADS);
		{	
			glTexCoord2d(0, 0);
			glVertex2d(0, 0);

			glTexCoord2d(0, 1);
			glVertex2d(0, texture.getImageHeight());

			glTexCoord2d(1, 1);
			glVertex2d(texture.getImageWidth(), texture.getImageHeight());

			glTexCoord2d(1, 0);
			glVertex2d(texture.getImageWidth(), 0);
		}
		glEnd();
		glPopMatrix();
		
		glBindTexture(GL_TEXTURE_2D, 0);
		}
		catch (NoSuchFieldException e)
		{
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	public void enterPauseMenu()
	{
		inPauseMenu = true;
		pause_menu.setButton(0);
	}

	public void leavePauseMenu()
	{
		inPauseMenu = false;
		pause_menu.setButton(0);
	}
	
	public void drawNumericalString(double x, double y, int number, boolean black)
	{
		try
		{
			String color;
			if(black == true) color = "BLACK_";
			else color = "WHITE_";
			
			Field field = Textures.class.getDeclaredField(color + number);
			Texture texture = (Texture) field.get(null);
			
			
			glPushMatrix();
			glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
			glBegin(GL_QUADS);
			{	
				glTexCoord2d(0, 0);
				glVertex2d(x, y);

				glTexCoord2d(0, 1);
				glVertex2d(x, y + texture.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(x + texture.getImageWidth(), y + texture.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(x + texture.getImageWidth(), y);
			}
			glEnd();
			glPopMatrix();
			
			glBindTexture(GL_TEXTURE_2D, 0);
		}
		catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
