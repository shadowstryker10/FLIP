package flip.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import flip.game.entities.terrain.PlayerSpawnBlack;
import flip.game.entities.terrain.PlayerSpawnWhite;
import flip.game.entities.terrain.WallBlack;
import flip.game.entities.terrain.WallGoal;
import flip.game.entities.terrain.WallWhite;
import flip.game.world.Level;

public class FileHandler
{
	public static Texture loadTexture(String key)
	{
		try
		{
			return TextureLoader.getTexture("png", FileHandler.class.getClassLoader().getResourceAsStream(key + ".png"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Level readLevel(String key)
	{	
		try
		{	
			File level_file = new File(FileHandler.class.getClassLoader().getResource("game/levels/" + key + ".png").getFile());
			BufferedImage image = ImageIO.read(FileHandler.class.getClassLoader().getResource("game/levels/" + key + ".png"));
			
			int width = image.getWidth();
			int height = image.getHeight();
			int[] dim = new int[width * height];
			int[] picture = image.getRGB(0,  0, width, height, dim, 0, width);
			int[][] arr = new int[width][height];
			
			int xLoc = -1, yLoc = 0;
			
			for(int i = 0; i < picture.length; i++)
			{	
				yLoc = i / image.getWidth();
				
				xLoc += 1;
				if(xLoc >= image.getWidth()) xLoc = 0;

				int r = new Color(picture[i]).getRed();
				int g = new Color(picture[i]).getGreen();
				int b = new Color(picture[i]).getBlue();
				
				if(r == WallBlack.R && g == WallBlack.G && b == WallBlack.B)
					arr[xLoc][yLoc] = WallBlack.ID;
				else if(r == WallWhite.R && g == WallWhite.G && b == WallWhite.B)
				{
					arr[xLoc][yLoc] = WallWhite.ID;
				}
				else if(r == WallGoal.R && g == WallGoal.G && b == WallGoal.B)
				{
					arr[xLoc][yLoc] = WallGoal.ID;
				}
				else if(r == PlayerSpawnWhite.R && g == PlayerSpawnWhite.G && b == PlayerSpawnWhite.B)
				{
					arr[xLoc][yLoc] = PlayerSpawnWhite.ID;
				}
				else if(r == PlayerSpawnBlack.R && g == PlayerSpawnBlack.G && b == PlayerSpawnBlack.B)
				{
					arr[xLoc][yLoc] = PlayerSpawnBlack.ID;
				}
				else
				{
					arr[xLoc][yLoc] = 0;
				}
			}
			
			Level level = new Level(arr, width, height);
			return level;
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
