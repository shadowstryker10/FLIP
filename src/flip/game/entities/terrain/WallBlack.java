package flip.game.entities.terrain;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import flip.files.Textures;
import flip.game.Game;

import static org.lwjgl.opengl.GL11.*;

public class WallBlack extends AbstractWall
{
	public static final int ID = 1;
	
	public static final int R = 0;
	public static final int G = 0;
	public static final int B = 0;
	
	public WallBlack(double x, double y)
	{	
		bounds = new Rectangle(32, 32);
		if(validLocation(x, y))
		{
			this.x = x;
			this.y = y;
		}
		;
		Obstacles.addWhiteWorldWall(this);
	}
	
	@Override
	public void update(Game game)
	{
		super.update(game);
	}
	
	@Override
	public void draw()
	{
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, Textures.WALL_BLACK.getTextureID());
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
	
	public boolean validLocation(double x, double y)
	{
		if((x +  - (bounds.width / 2)) < 0) return false;
		if((x + (bounds.width / 2)) > Display.getWidth()) return false;

		if(y - (bounds.height / 2) > (Display.getHeight() / 2)) return false;
		if(y + (bounds.height / 2) < 0) return false;
		
		return true;
	}
}
