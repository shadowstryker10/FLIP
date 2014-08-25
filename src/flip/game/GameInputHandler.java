package flip.game;

import org.lwjgl.input.Keyboard;

import flip.main.GameState;
import flip.main.Main;
import flip.util.Settings;

public class GameInputHandler
{
	private Game game;

	public GameInputHandler(Game game)
	{
		this.game = game;
	}

	public void update()
	{
		if (!game.gameCompleted)
		{
			if (game.inPauseMenu)
			{
				if (game.pause_menu.inOptions)
				{
					while (Keyboard.next())
					{
						if (Keyboard.getEventKeyState())
						{
							if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
							{
								game.pause_menu.leaveOptions();
							}
							if (Keyboard.getEventKey() == Keyboard.KEY_RETURN)
							{
								if (game.pause_menu.options.getButton() == 0)
								{
									Settings.MUTE = !Settings.MUTE;
								}
								if (game.pause_menu.options.getButton() == 1)
								{
									game.pause_menu.leaveOptions();
								}
							}
							if (Keyboard.getEventKey() == Keyboard.KEY_W
									|| Keyboard.getEventKey() == Keyboard.KEY_UP)
							{
								if (game.pause_menu.options.getButton() > 0)
									game.pause_menu.options
											.setButton(game.pause_menu.options
													.getButton() - 1);
								else
									game.pause_menu.options.setButton(1);
							}
							if (Keyboard.getEventKey() == Keyboard.KEY_S
									|| Keyboard.getEventKey() == Keyboard.KEY_DOWN)
							{
								if (game.pause_menu.options.getButton() < 1)
									game.pause_menu.options
											.setButton(game.pause_menu.options
													.getButton() + 1);
								else
									game.pause_menu.options.setButton(0);
							}
						}
					}
				}
				else
				{
					while (Keyboard.next())
					{
						if (Keyboard.getEventKeyState())
						{
							if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
							{
								game.leavePauseMenu();
							}
							if (Keyboard.getEventKey() == Keyboard.KEY_W
									|| Keyboard.getEventKey() == Keyboard.KEY_UP)
							{
								if (game.pause_menu.getButton() > 0)
									game.pause_menu.setButton(game.pause_menu
											.getButton() - 1);
								else
									game.pause_menu.setButton(3);
							}
							if (Keyboard.getEventKey() == Keyboard.KEY_S
									|| Keyboard.getEventKey() == Keyboard.KEY_DOWN)
							{
								if (game.pause_menu.getButton() < 3)
									game.pause_menu.setButton(game.pause_menu
											.getButton() + 1);
								else
									game.pause_menu.setButton(0);
							}
							if (Keyboard.getEventKey() == Keyboard.KEY_RETURN)
							{
								if (game.pause_menu.getButton() == 0)
								{
									game.leavePauseMenu();
								}
								if (game.pause_menu.getButton() == 1)
								{
									game.wonLevel = true;
									game.leavePauseMenu();
								}
								if (game.pause_menu.getButton() == 2)
								{
									game.pause_menu.enterOptions();
								}
								if (game.pause_menu.getButton() == 3)
								{
									Main.state = GameState.PREINIT;
								}
							}
						}
					}
				}
			}
			else if (game.wonLevel)
			{
				while (Keyboard.next())
				{
					if (Keyboard.getEventKeyState())
					{
						game.wonLevelContinue = true;
					}
				}
			}
			else
			{
				if (Keyboard.isKeyDown(Keyboard.KEY_W)
						|| Keyboard.isKeyDown(Keyboard.KEY_UP))
				{
					game.p1.translateY(-1);
					game.p2.translateY(1);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_S)
						|| Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				{
					game.p1.translateY(1);
					game.p2.translateY(-1);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_A)
						|| Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				{
					game.p1.translateX(-1);
					game.p2.translateX(1);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_D)
						|| Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				{
					game.p1.translateX(1);
					game.p2.translateX(-1);
				}

				while (Keyboard.next())
				{
					if (Keyboard.getEventKeyState())
					{
						if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
						{
							game.enterPauseMenu();
						}
					}
				}
			}
		}
		else
		{
			while(Keyboard.next())
			{
				if(Keyboard.getEventKeyState())
				{
					Main.state = GameState.PREINIT;
				}
			}
		}
	}
}
