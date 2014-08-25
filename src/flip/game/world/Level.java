package flip.game.world;

import java.awt.Rectangle;

import flip.game.entities.player.PlayerWhite;
import flip.game.entities.terrain.Obstacles;
import flip.game.entities.terrain.PlayerSpawnBlack;
import flip.game.entities.terrain.PlayerSpawnWhite;
import flip.game.entities.terrain.WallBlack;
import flip.game.entities.terrain.WallGoal;
import flip.game.entities.terrain.WallWhite;

public class Level
{
	private int width, height;
	private int[][] level;
	
	private Rectangle playerBlackSpawn;
	private Rectangle playerWhiteSpawn;
	
	public Level(int[][] level, int width, int height)
	{
		this.level = level;
		this.width = width;
		this.height = height;
	}
	
	public void load()
	{
		Obstacles.clearAllObstacles();
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if(getTileAt(x, y) == WallBlack.ID)
				{
					new WallBlack((x * 32) + 16, (y * 32) + 16);
				}
				if(getTileAt(x, y) == WallWhite.ID)
				{
					new WallWhite((x * 32) + 16, (y * 32) + 16);
				}
				if(getTileAt(x, y) == WallGoal.ID)
				{
					new WallGoal((x * 32) + 16, (y * 32) + 16);
				}
				if(getTileAt(x, y) == PlayerSpawnWhite.ID)
				{
					playerWhiteSpawn = new Rectangle((x * 32) + (PlayerWhite.getBounds().width / 2), (y * 32) + (PlayerWhite.getBounds().height / 2));
				}
				if(getTileAt(x, y) == PlayerSpawnBlack.ID)
				{
					playerBlackSpawn = new Rectangle((x * 32) + (PlayerWhite.getBounds().width / 2), (y * 32 )+ (PlayerWhite.getBounds().height / 2));
				}
			}
		}
	}
	
	public Rectangle getPlayerBlackSpawn()
	{
		return playerBlackSpawn;
	}
	
	public Rectangle getPlayerWhiteSpawn()
	{
		return playerWhiteSpawn;
	}
	
	public int[] getRow(int y)
	{
		int[] row = new int[width];
		
		for(int i = 0; i < width; i++)
		{
			row[i] = level[i][y];
		}
		
		return row;
	}
	
	public int[] getColumn(int x)
	{
		int[] row = new int[height];
		
		for(int i = 0; i < height; i++)
		{
			row[i] = level[x][i];
		}
		
		return row;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		
		for(int y = 0; y < getHeight(); y++)
		{
			for(int x = 0; x < getWidth(); x++)
			{
				s += "[" + getTileAt(x, y) + "] ";
			}
			s += "\n";
		}
		
		return s;
	}
	
	public int[][] getRawLevel()
	{
		return level;
	}
	
	public int getTileAt(int x, int y)
	{
		return level[x][y];
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
