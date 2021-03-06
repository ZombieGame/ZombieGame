/*	Zombie Game
*/

package Menu;

import Engine.IGameEngine;
import TextureEngine.ITexture;
// imports
import TextureEngine.ITextureEngine;
import Utility.BitmapFont;



public class CreditsMenuScreen extends BaseMenuScreen
{
	public CreditsMenuScreen()
	{
		super();
	}
	
	
	
	//
	// IMenuScreen interface methods
	//
	
	public void	Init(IMenuController menuController) throws Exception
	{
		super.Init(menuController);
		
		float[] imageArea;
		
		// get resources
		gfx_ = menuController_.GetGraphicsController();
		
		// load menu graphics
		ITexture background		= gfx_.LoadTexture("gfx/menu/menu-background4-center.png", 0x00202020);
		ITexture titleImage		= gfx_.LoadTexture("gfx/menu/credits-title.png", 1);
		ITexture backImage 		= gfx_.LoadTexture("gfx/menu/back.png", 1);
		
		//ITexture fontImage 		= gfx_.LoadTexture("gfx/font.png", 0x00FFFFFF);
		ITexture fontImage = gfx_.LoadGrayscaleFont("gfx/font.png", true);
		BitmapFont font = new BitmapFont();
		font.SetFont(fontImage);
		font.SetKerning(0.45f);
		
		//		[credits]
		// title	names
		// 		[back]
		
		// background image (covers lines)
		ImageWidget back = new ImageWidget(background, -1.0f, -1.0f, 2.0f, 2.0f);
		AddWidget(back);
		
		// title image
		ImageWidget title = new ImageWidget(titleImage, -0.5f, 0.3f, 1.0f, 1.0f);
		imageArea = title.GetAreaOnScreen();
		AddWidget(title);
		
		TextWidget credits = new TextWidget(
			"Art               Will Bahnmiller  \n" +
			"\n"+
			"Documentation     Craig Camacho    \n"+
			"                  Erwin Holzhauser \n"+
			"                  Cort Lyons       \n"+
			"\n"+
			"Programming       Jacob Millsaps   \n"+
			"                  Jackson Ritchey  \n"+
			"\n"+
			"Sound Effects     Will Bahnmiller  \n"+
			"                  Erwin Holzhauser \n"+
			"                  Cort Lyons       \n"+
			"\n"+
			"Developed for COP4331C Fall 2013 at UCF",
			font,
			-0.5f, 0.2f,
			0.045f, 0.045f
			);
		AddWidget(credits);
		
		// back button
		ImageWidget backButton = new ImageWidget(backImage, -0.10f, -0.55f, 0.2f, 0.2f);
		imageArea = backButton.GetAreaOnScreen();
		
		PreviousMenuWidgetAction prevAction = new PreviousMenuWidgetAction();
		prevAction.SetArea(imageArea[0], imageArea[1], imageArea[2], imageArea[3]);
		backButton.AddAction(prevAction);
		
		AddWidget(backButton);
	}
	
	public void	Draw(float delta)
	{
		// draw a background
		
		
		// then draw the widgets on top
		super.Draw(delta);
	}
	
	
	
	//
	//
	//
	
	protected ITextureEngine	gfx_;
}