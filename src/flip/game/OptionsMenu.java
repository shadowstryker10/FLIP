package flip.game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.newdawn.slick.opengl.Texture;

import flip.files.Textures;
import flip.util.Settings;

public class OptionsMenu
{
	private Game game;
	
	private int button;
	private GameInputHandler input;
	
	public OptionsMenu(Game game)
	{
		button = 0;
		this.game = game;
		input = game.input;
	}
	
	public void update()
	{
		input.update();
		render();
	}
	
	public void render()
	{
		renderBackground();
		renderButtonOverlays();
	}
	
	private void renderButtonOverlays()
	{
		double x = 0, y = 0;
		Texture texture = null;
		
		if(button == 0)
		{
			x = 348;
			y = 441;
			texture = Textures.OVERLAY_OPTIONS_MUTE;
		}
		if(button == 1)
		{
			x = 406;
			y = 674;
			texture = Textures.OVERLAY_OPTIONS_BACK;
		}
		
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		glBegin(GL_QUADS);
		{
			glTexCoord2d(0, 0);
			glVertex2d(x, y);
			glTexCoord2d(0, 1);
			glVertex2d(x, y + texture.getImageHeight());
			glTexCoord2d(1, 1);
			glVertex2d(x + texture.getImageWidth(), y + texture.getImageHeight());
			glTexCoord2d(1, 0);
			glVertex2d(x + texture.getImageWidth(), y);
		}
		glEnd();
		glPopMatrix();
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		if(Settings.MUTE)
		{
			glPushMatrix();
			glBindTexture(GL_TEXTURE_2D, Textures.OVERLAY_OPTIONS_CHECK.getTextureID());
			glBegin(GL_QUADS);
			{
				x = 520;
				y = 445;
				
				glTexCoord2d(0, 0);
				glVertex2d(x, y);
				glTexCoord2d(0, 1);
				glVertex2d(x, y + Textures.OVERLAY_OPTIONS_CHECK.getImageHeight());
				glTexCoord2d(1, 1);
				glVertex2d(x + Textures.OVERLAY_OPTIONS_CHECK.getImageWidth(), y + Textures.OVERLAY_OPTIONS_CHECK.getImageHeight());
				glTexCoord2d(1, 0);
				glVertex2d(x + Textures.OVERLAY_OPTIONS_CHECK.getImageWidth(), y);
			}
			glEnd();
			glPopMatrix();
			
			glBindTexture(GL_TEXTURE_2D, 0);
		}
	}
	
	private void renderBackground()
	{
			glPushMatrix();
			glBindTexture(GL_TEXTURE_2D, Textures.OPTIONS_MENU.getTextureID());
			glBegin(GL_QUADS);
			{	
				glTexCoord2d(0, 0);
				glVertex2d(0, 0);

				glTexCoord2d(0, 1);
				glVertex2d(0, Textures.OPTIONS_MENU.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(Textures.OPTIONS_MENU.getImageWidth(), Textures.OPTIONS_MENU.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(Textures.OPTIONS_MENU.getImageWidth(), 0);
			}
			glEnd();
			glPopMatrix();
			
			glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public int getButton()
	{
		return button;
	}
	
	public void setButton(int button)
	{
		this.button = button;
	}
}