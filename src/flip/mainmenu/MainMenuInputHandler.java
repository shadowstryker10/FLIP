package flip.mainmenu;

import org.lwjgl.input.Keyboard;

import flip.main.Main;

public class MainMenuInputHandler
{
	MainMenu menu;
	
	public MainMenuInputHandler(MainMenu menu)
	{
		this.menu = menu;
	}
	
	public void update()
	{
		
		
		while(Keyboard.next())
		{
			if(Keyboard.getEventKeyState())
			{
				if(!menu.isInCredits())
				{
					if(Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN)
					{
						if(menu.getButton() < 2) menu.setButton(menu.getButton() + 1);
						else menu.setButton(0);
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP)
					{
						if(menu.getButton() > 0) menu.setButton(menu.getButton() - 1);
						else menu.setButton(2);
					}
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && menu.isInCredits()))
				{
					if(menu.getButton() == 0)
						menu.startGame();
					if(menu.getButton() == 1)
					{
						menu.setInCredits(!menu.isInCredits());
					}
					if(menu.getButton() == 2)
						Main.dispose();
				}
				else if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					Main.dispose();
				}
			}
		}
	}
}
