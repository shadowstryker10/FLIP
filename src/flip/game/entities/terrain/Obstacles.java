package flip.game.entities.terrain;

import java.util.ArrayList;

import flip.game.Game;

public class Obstacles
{
	private static ArrayList<AbstractWall> white_world_walls = new ArrayList<AbstractWall>();
	private static ArrayList<AbstractWall> black_world_walls = new ArrayList<AbstractWall>();
	private static ArrayList<AbstractWall> all_world_walls = new ArrayList<AbstractWall>();

	public static void update(Game game)
	{
		for(int i = 0; i < white_world_walls.size(); i++)
		{
			white_world_walls.get(i).update(game);
		}
		for(int i = 0; i < black_world_walls.size(); i++)
		{
			black_world_walls.get(i).update(game);
		}
		for(int i = 0; i < all_world_walls.size(); i++)
		{
			all_world_walls.get(i).update(game);
		}
	}
	
	public static void clearAllObstacles()
	{
		white_world_walls.clear();
		black_world_walls.clear();
		all_world_walls.clear();
	}
	
	public static AbstractWall getAllWorldWall(int index)
	{
		return all_world_walls.get(index);
	}
	
	public static ArrayList<AbstractWall> getAllWorldWalls()
	{
		return all_world_walls;
	}
	
	public static void addAllWorldWall(AbstractWall wall)
	{
		all_world_walls.add(wall);
	}
	
	public static AbstractWall getWhiteWorldWall(int index)
	{
		return white_world_walls.get(index);
	}
	
	public static ArrayList<AbstractWall> getWhiteWorldWalls()
	{
		return white_world_walls;
	}
	
	public static void addWhiteWorldWall(AbstractWall wall)
	{
		white_world_walls.add(wall);
	}
	
	
	public static AbstractWall getBlackWorldWall(int index)
	{
		return black_world_walls.get(index);
	}
	
	public static ArrayList<AbstractWall> getBlackWorldWalls()
	{
		return black_world_walls;
	}
	
	public static void addBlackWorldWall(AbstractWall wall)
	{
		black_world_walls.add(wall);
	}
}
