package flip.game.entities.terrain;

import java.awt.Color;
import java.awt.Rectangle;

import flip.game.Game;

public class AbstractWall
{
	protected double x = 0, y = 0;
	protected Rectangle bounds = new Rectangle(0, 0);
	public static final int ID = 0;
	
	public static final int R = 0;
	public static final int G = 255;
	public static final int B = 0;
	
	public void update(Game game)
	{
		draw();
	}
	
	public void draw()
	{
		
	}

	public Rectangle getBoundingBox()
	{
		return bounds;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public boolean intersects(double otherx, double othery)
	{
		if(otherx >= (x - (bounds.width / 2)) && otherx <= (x + (bounds.width / 2)))
		{
			if(othery >= (y - (bounds.height / 2)) && othery <= (y + (bounds.height / 2)))
			{
				return true;
			}
			return false;
		}
		
		return false;
	}
}
