package flip.game.entities.player;

import java.awt.Rectangle;

public class AbstractPlayer
{
	protected double xTrans = 0, yTrans = 0;
	protected double x = 0, y = 0;
	protected static Rectangle bounds = new Rectangle(0, 0);
	
	public void update()
	{
		draw();
	}
	
	public void draw()
	{
		
	}
	
	public void resetTranslations()
	{
		xTrans = 0;
		yTrans = 0;
	}
	
	public double getXPlusTrans()
	{
		return x + xTrans;
	}
	
	public double getYPlusTrans()
	{
		return y + yTrans;
	}

	public Rectangle getBoundingBox()
	{
		return bounds;
	}
	
	public void setCoords(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void translateX(double trans)
	{
		xTrans += trans;
	}
	
	public void translateY(double trans)
	{
		yTrans += trans;
	}
}
