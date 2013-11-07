
package GameStates;


import org.lwjgl.util.vector.Vector2f;

import Actions.CollidablePhysics;
import Actions.ObjectFollower;
import Actions.Physics;
import AudioEngine.IAudioEngine;
import Drawing.DrawText;
import Drawing.SimpleDraw;
import Drawing.TileDraw;
import Engine.IGameEngine;
import GameObjects.ExampleObject;
import GameObjects.GameObject;
import Geometry.AABB;
import TextureEngine.ITexture;
import TextureEngine.ITextureEngine;
import Utility.BitmapFont;

/// example of making a scene. Creates ExampleObject and adds action to it

/**
 * StartGame a sandbox state showing how to create objects using nodes
 * 
 * 
 */
public class StartGame extends EventListenerState
{
	public StartGame()
	{
		
	}
	
	@Override
	public void Init(ITextureEngine gfx, IAudioEngine snd, IGameEngine game) throws Exception 
	{
		// TODO Auto-generated method stub
		super.Init(gfx, snd, game);
		
		buildUniverse();
		
		//game.EndGameLoop(); // quits immediately.
	}
	
	@Override
	public void Quit() {
		// TODO Auto-generated method stub
		
	}

	// maybe this need to have delta for adjustable update frequency?
	@Override
	public void Update() {
		// TODO Auto-generated method stub]
		super.Update();
		universe.update((float)1/game.GetTickFrequency());
		game.SetWindowTitle(""+game.GetFrameRate());
	}

	@Override
	public void Draw(float delta) 
	{
		gfx.ClearScreen();
		universe.draw((float)delta/game.GetTickFrequency());
		HUD.draw(delta);
	}



	// sets up an enviroment with to collidable objects
	public void buildUniverse()
	{
		universe = new GameObject(); // create universe
		test = gfx.LoadTexture("image3.bmp", 1);
		universe.setDrawingInterface(new TileDraw(universe, test));
		universe.translate(0, -.5f); 
		universe.scale(.2f, .2f);
		ExampleObject obj = new ExampleObject(universe, game, gfx, this);
		obj.getHandle().translate(0, 0);
		//obj.getHandle().setCollidable(true);
		ObjectFollower objFollower = new ObjectFollower(universe, obj.getHandle(), game);
		objFollower.setMass(.1f);
		objFollower.setFrictionConstant(1f);
		universe.translate(-obj.getHandle().getGlobalX(), -obj.getHandle().getGlobalY());
		
		universe.addAction(objFollower);
		
		
		for(int i = 0; i < 3; i++)
		{
			GameObject obj2 = new GameObject();
			obj2.translate(i*2, 0);
			//obj2.rotate((float)(Math.random()*2*Math.PI));
			obj2.setDrawingInterface(new SimpleDraw(gfx.LoadTexture("image2.png", 0)));
			obj2.setCollidable(true);
			obj2.setStatic(false);
			obj2.setBoundingBox(new AABB(1f, 1f));
			obj2.addAction(new CollidablePhysics(obj2, universe, game));
			universe.addChild(obj2);
		}

		DrawText textrenderer = new DrawText(gfx, "font1.png");
	
		HUD = new GameObject();
		HUD.setDrawingInterface(textrenderer);
		textrenderer.setText("right click to move.\n(go right!)");
		HUD.scale(.05f, .05f);
		HUD.translate(-1f, .65f);
	}
	ITexture test;
	

	
	
	GameObject universe;
	GameObject HUD;
	
	
}
