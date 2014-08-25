package flip.game.world;

import java.util.HashMap;
import java.util.Map;

import flip.files.FileHandler;

public class Levels
{
	private static Map<String, Level> levels = new HashMap<String, Level>();
	
	public static void putLevel(String key, Level value)
	{
		levels.put(key, value);
	}
	
	public static Level getLevel(String key)
	{
		return levels.get(key);
	}
	
	public static void loadLevels()
	{
		for(int i = 1; i <= 12; i++)
		{
			levels.put("level_" + i, FileHandler.readLevel("level" + i));
		}
	}
}
