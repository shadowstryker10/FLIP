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

public class PauseMenu
{
	private Game game;
	
	private int button;
	private GameInputHandler input;
	public OptionsMenu options;
	
	public boolean inOptions;
	
	public PauseMenu(Game game)
	{
		button = 0;
		this.game = game;
		input = game.input;
		inOptions = false;
		options = new OptionsMenu(game);
	}
	
	public void update()
	{
		if(!inOptions)
		{
			input.update();
			render();
		}
		else
		{
			options.update();
		}
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
			x = 364;
			y = 429;
			texture = Textures.OVERLAY_PAUSE_RESUME;
		}
		if(button == 1)
		{
			x = 336;
			y = 486;
			texture = Textures.OVERLAY_PAUSE_SKIP;
		}	
		if(button == 2)
		{
			x = 361;
			y = 544;
			texture = Textures.OVERLAY_PAUSE_OPTIONS;
		}
		if(button == 3)
		{
			x = 294;
			y = 660;
			texture = Textures.OVERLAY_PAUSE_BACK;
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
	}
	
	private void renderBackground()
	{
			glPushMatrix();
			glBindTexture(GL_TEXTURE_2D, Textures.PAUSE_MENU.getTextureID());
			glBegin(GL_QUADS);
			{	
				glTexCoord2d(0, 0);
				glVertex2d(0, 0);

				glTexCoord2d(0, 1);
				glVertex2d(0, Textures.PAUSE_MENU.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(Textures.PAUSE_MENU.getImageWidth(), Textures.PAUSE_MENU.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(Textures.PAUSE_MENU.getImageWidth(), 0);
			}
			glEnd();
			glPopMatrix();
			
			glBindTexture(GL_TEXTURE_2D, 0);
		
			game.drawNumericalString(92, 13, (game.currentLevel / 10), false);
			game.drawNumericalString(108, 13, (game.currentLevel % 10), false);
	}
	
	public void enterOptions()
	{
		inOptions = true;
		options.setButton(0);
	}
	
	public void leaveOptions()
	{
		inOptions = false;
		options.setButton(0);
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