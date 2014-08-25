package flip.mainmenu;

import static org.lwjgl.opengl.GL11.*;

import flip.files.Textures;

public class MainMenu
{
	private int button;
	private MainMenuInputHandler input;
	
	private boolean startGame, inCredits;
	
	public MainMenu()
	{
		button = 0;
		input = new MainMenuInputHandler(this);
		startGame = false;
		inCredits = false;
	}
	
	public void update()
	{
		input.update();
		render();
	}
	
	public void render()
	{
		renderBackground();
		if(!inCredits)
			renderButtonOverlays();
	}
	
	private void renderButtonOverlays()
	{
		if(button == 0)
		{
			glBindTexture(GL_TEXTURE_2D, Textures.OVERLAY_BUTTON_START.getTextureID());
			
			glPushMatrix();
			glBegin(GL_QUADS);
			{
				Textures.OVERLAY_BUTTON_START.bind();
				
				glTexCoord2d(0, 0);
				glVertex2d(398, 319);

				glTexCoord2d(0, 1);
				glVertex2d(398, 319 + Textures.OVERLAY_BUTTON_START.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(398 + Textures.OVERLAY_BUTTON_START.getImageWidth(), 319 + Textures.OVERLAY_BUTTON_START.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(398 + Textures.OVERLAY_BUTTON_START.getImageWidth(), 319);
			}
			glEnd();
			glPopMatrix();	
		}

		if(button == 1)
		{
			glBindTexture(GL_TEXTURE_2D, Textures.OVERLAY_BUTTON_ABOUT.getTextureID());
			
			glPushMatrix();
			glBegin(GL_QUADS);
			{
				Textures.OVERLAY_BUTTON_ABOUT.bind();
				
				glTexCoord2d(0, 0);
				glVertex2d(397, 354);

				glTexCoord2d(0, 1);
				glVertex2d(397, 354 + Textures.OVERLAY_BUTTON_ABOUT.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(397 + Textures.OVERLAY_BUTTON_ABOUT.getImageWidth(), 354 + Textures.OVERLAY_BUTTON_ABOUT.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(397 + Textures.OVERLAY_BUTTON_ABOUT.getImageWidth(), 354);
			}
			glEnd();
			glPopMatrix();	
		}		
		
		if(button == 2)
		{
			glBindTexture(GL_TEXTURE_2D, Textures.OVERLAY_BUTTON_EXIT.getTextureID());
			
			glPushMatrix();
			glBegin(GL_QUADS);
			{
				Textures.OVERLAY_BUTTON_EXIT.bind();
				
				glTexCoord2d(0, 0);
				glVertex2d(408, 393);

				glTexCoord2d(0, 1);
				glVertex2d(408, 393 + Textures.OVERLAY_BUTTON_EXIT.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(408 + Textures.OVERLAY_BUTTON_EXIT.getImageWidth(), 393 + Textures.OVERLAY_BUTTON_EXIT.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(408 + Textures.OVERLAY_BUTTON_EXIT.getImageWidth(), 393);
			}
			glEnd();
			glPopMatrix();	
		}
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private void renderBackground()
	{
		if(!inCredits)
		{
			glPushMatrix();
			glBindTexture(GL_TEXTURE_2D, Textures.MAIN_MENU.getTextureID());
			glBegin(GL_QUADS);
			{	
				glTexCoord2d(0, 0);
				glVertex2d(0, 0);

				glTexCoord2d(0, 1);
				glVertex2d(0, Textures.MAIN_MENU.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(Textures.MAIN_MENU.getImageWidth(), Textures.MAIN_MENU.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(Textures.MAIN_MENU.getImageWidth(), 0);
			}
			glEnd();
			glPopMatrix();
			
			glBindTexture(GL_TEXTURE_2D, 0);
		}
		else if(inCredits)
		{
			glPushMatrix();
			glBindTexture(GL_TEXTURE_2D, Textures.MAIN_MENU_ABOUT.getTextureID());
			glBegin(GL_QUADS);
			{	
				glTexCoord2d(0, 0);
				glVertex2d(0, 0);

				glTexCoord2d(0, 1);
				glVertex2d(0, Textures.MAIN_MENU_ABOUT.getImageHeight());

				glTexCoord2d(1, 1);
				glVertex2d(Textures.MAIN_MENU_ABOUT.getImageWidth(), Textures.MAIN_MENU_ABOUT.getImageHeight());

				glTexCoord2d(1, 0);
				glVertex2d(Textures.MAIN_MENU_ABOUT.getImageWidth(), 0);
			}
			glEnd();
			glPopMatrix();
			
			glBindTexture(GL_TEXTURE_2D, 0);
		}
	}
	
	public boolean isInCredits()
	{
		return inCredits;
	}
	
	public void setInCredits(boolean bool)
	{
		inCredits = bool;
	}
	
	public void startGame()
	{
		startGame = true;
	}
	
	public boolean isStartRequested()
	{
		return startGame;
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
