package flip.game.entities.player;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import flip.files.Textures;
import flip.game.entities.terrain.AbstractWall;
import flip.game.entities.terrain.Obstacles;
import flip.game.entities.terrain.WallGoal;

public class PlayerBlack extends AbstractPlayer
{
	private boolean onWinTile = false;
	
	public PlayerBlack()
	{
		bounds = new Rectangle(32, 32);
		x = Display.getWidth() / 2;
		y = (Display.getHeight() / 2) - (bounds.height / 2);
	}
	
	@Override
	public void update()
	{
		super.update();
	}
	
	@Override
	public void draw()
	{
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, Textures.PLAYER_DEATH.getTextureID());
		glTranslated(xTrans, yTrans, 0);
		glBegin(GL_QUADS);
		{;
			glTexCoord2d(0, 0);
			glVertex2d(x - (Textures.PLAYER_DEATH.getImageWidth() / 2), y - (Textures.PLAYER_DEATH.getImageHeight() / 2));
			
			glTexCoord2d(0, 1);
			glVertex2d(x - (Textures.PLAYER_DEATH.getImageWidth() / 2), y +  + (Textures.PLAYER_DEATH.getImageHeight() / 2));
			
			glTexCoord2d(1, 1);
			glVertex2d(x + (Textures.PLAYER_DEATH.getImageWidth() / 2), y + (Textures.PLAYER_DEATH.getImageHeight() / 2));
			
			glTexCoord2d(1, 0);
			glVertex2d(x + (Textures.PLAYER_DEATH.getImageWidth() / 2), y - (Textures.PLAYER_DEATH.getImageHeight() / 2));
		}
		glEnd();
		glPopMatrix();

		glBindTexture(GL_TEXTURE_2D, 0);
	}
	

	@Override
	public void translateX(double trans)
	{
		double leftEdge = x + xTrans + trans - (bounds.width / 2);
		double rightEdge = x + xTrans + trans + (bounds.width / 2);
		
		if(leftEdge <= -1) return;
		if(rightEdge > Display.getWidth()) return;
		if(intersectsWall(trans, 0)) return;
		
		xTrans += trans;
	}
	
	public boolean intersectsWall(double transx, double transy)
	{
		double leftEdge = x + xTrans + transx - (bounds.width / 2) + 1.01;
		double rightEdge = x + xTrans + transx + (bounds.width / 2) - 1.01;
		double topEdge = y + yTrans + transy - (bounds.height / 2) + 1.01;
		double bottomEdge = y + yTrans + transy + (bounds.height / 2) - 1.01;
		
		ArrayList<AbstractWall> list = Obstacles.getWhiteWorldWalls();
		for(int i = 0; i < list.size(); i++)
		{
			AbstractWall wall = list.get(i);
			if(wall.intersects(leftEdge, topEdge)) return true;
			if(wall.intersects(rightEdge, topEdge)) return true;
			if(wall.intersects(leftEdge, bottomEdge)) return true;
			if(wall.intersects(rightEdge, bottomEdge)) return true;
		}
		
		return false;
	}
	
	@Override
	public void translateY(double trans)
	{
		if(y + yTrans + trans + (bounds.height / 2) > (Display.getHeight() / 2)) return;
		if(y + yTrans + trans - (bounds.height / 2) < 0) return;
		if(intersectsWall(0, trans)) return;
		
		yTrans += trans;
	}
	
	public static Rectangle getBounds()
	{
		return bounds;
	}
}
