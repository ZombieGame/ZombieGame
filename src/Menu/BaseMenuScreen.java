/*	Zombie game
*/

package Menu;

// imports
import java.util.*;

import AudioEngine.IAudioEngine;
import Engine.IGameEngine;
import TextureEngine.ITextureEngine;

import Menu.IMenuWidget;
import Menu.IMenuController;



public class BaseMenuScreen implements IMenuScreen
{
	//
	// public methods
	//
	
	public BaseMenuScreen()
	{
		widgets_ = new ArrayList<IMenuWidget>();
		focusedWidget_ = null;
	}
	
	
	
	//
	// IMenuScreen methods
	//
	
	public void	Init(IMenuController menuController) throws Exception
	{
		menuController_ = menuController;
		
		// create widgets here in child classes
	}
	
	public void	Quit()
	{
		// shutdown all widgets
		for (int i=0; i < widgets_.size(); i++)
		{
			widgets_.get(i).Quit();
		}
	}
	
	public void	Pop()
	{
		//
	}
	
	public void	Push()
	{
		//
	}
	
	public void	Update()
	{
		if (focusedWidget_ != null)
		{
			focusedWidget_.Update();
		}
		else
		{
			for (int i=0; i < widgets_.size(); i++)
			{
				widgets_.get(i).Update();
			}
		}
	}
	
	public void	Draw(float delta)
	{
		for (int i=0; i < widgets_.size(); i++)
		{
			widgets_.get(i).Draw(delta);
		}
	}
	
	public void	RequestFocus(IMenuWidget widget)
	{
		focusedWidget_ = widget;
	}
	
	public void	ReleaseFocus()
	{
		focusedWidget_ = null;
	}
	
	//
	// protected members
	//
	
	protected IMenuController			menuController_;
	protected ArrayList<IMenuWidget>	widgets_;
	protected IMenuWidget				focusedWidget_;
	
	
	
	//
	// protected methods
	//
	
	/**
	 * Adds a widget to the widget list, and initializes it.
	 * @param widget
	 * @throws Exception
	 */
	protected void	AddWidget(IMenuWidget widget) throws Exception
	{
		widgets_.add(widget);
		widget.Init(menuController_, this);
	}
}