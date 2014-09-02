package flip.game.entities.terrain;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import flip.files.Textures;
import flip.game.Game;
import flip.game.entities.player.PlayerBlack;
import flip.game.entities.player.PlayerWhite;
import flip.main.Main;

public class WallGoal extends AbstractWall
{
	public static int ID = 3;

	public static final int R = 0;
	public static final int G = 255;
	public static final int B = 255;
	
	private boolean playerOn;
	
	public WallGoal(double x, double y)
	{	
		bounds = new Rectangle(32, 32);
		if(validLocation(x, y))
		{
			this.x = x;
			this.y = y;
			playerOn = false;
		}
		
		Obstacles.addAllWorldWall(this);
	}
	
	@Override
	public void update(Game game)
	{
		super.update(game);
		
		PlayerBlack p1 = game.p1;
		PlayerWhite p2 = game.p2;
		playerOn = false;
		
		if(y < Display.getHeight() / 2)
		{
			if(p1.getXPlusTrans() - ((p1.getBoundingBox().width / 2) - 1) >= x - (bounds.width / 2) - 0.01)
			{
				if(p1.getXPlusTrans() + ((p1.getBoundingBox().width / 2) - 1) <= x + (bounds.width / 2) + 0.01)
				{
					if(p1.getYPlusTrans() - ((p1.getBoundingBox().height / 2) - 1) >= y - (bounds.height / 2) - 0.01)
					{
						if(p1.getYPlusTrans() + ((p1.getBoundingBox().height / 2) - 1) <= y + (bounds.height / 2) + 0.01)
						{
							playerOn = true;
						}
					}
				}
			}	
		}
		
		if(y > Display.getHeight() / 2)
		{
			if(p2.getXPlusTrans() - ((p2.getBoundingBox().width / 2) - 1) > x - (bounds.width / 2) - 0.01)
			{
				if(p2.getXPlusTrans() + ((p2.getBoundingBox().width / 2) - 1) < x + (bounds.width / 2) + 0.01)
				{
					if(p2.getYPlusTrans() - ((p2.getBoundingBox().height / 2) - 1) > y - (bounds.height / 2) - 0.01)
					{
						if(p2.getYPlusTrans() + ((p2.getBoundingBox().height / 2) - 1) < y + (bounds.height / 2) + 0.01)
						{
							playerOn = true;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void draw()
	{
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, Textures.WALL_GOAL.getTextureID());
		glBegin(GL_QUADS);
		{
			glTexCoord2d(0, 0);
			glVertex2d(x - (bounds.width / 2), y - (bounds.height / 2));

			glTexCoord2d(0, 1);
			glVertex2d(x - (bounds.width / 2), y + (bounds.height / 2));

			glTexCoord2d(1, 1);
			glVertex2d(x + (bounds.width / 2), y + (bounds.height / 2));

			glTexCoord2d(1, 0);
			glVertex2d(x + (bounds.width / 2), y - (bounds.height / 2));
		}
		glEnd();
		glPopMatrix();
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public boolean isPlayerOn()
	{
		return playerOn;
	}
	
	public boolean validLocation(double x, double y)
	{
		if((x - (bounds.width / 2)) < 0) return false;
		if((x + (bounds.width / 2)) > Display.getWidth()) return false;

		if(y - (bounds.height / 2) < 0) return false;
		if(y + (bounds.height / 2) > Display.getHeight()) return false;
		
		return true;
	}
}
