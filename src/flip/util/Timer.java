package flip.util;


import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Timer
{

	long lastFrame;

	int fps;
	long lastFPS;
	
	int delta;
	
	int fpsToReturn;
	
	

	public Timer()
	{
		lastFPS = getTime();
	}

	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getDelta()
	{
		long time = getTime();
		delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public void updateFPS()
	{
		if (getTime() - lastFPS > 1000)
		{

			System.out.println("Game | " + "fps: " + fps + " | delta: " + delta);
			
			fpsToReturn = fps;
			
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public int getFPS()
	{
		return fpsToReturn;
	}
}
