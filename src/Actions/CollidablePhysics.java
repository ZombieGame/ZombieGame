package Actions;

import org.lwjgl.util.vector.Vector2f;

import Utility.Collision;
//import Utility.Collision;
import Utility.CollisionDetection;

import Engine.IGameEngine;
import GameObjects.GameObject;


/**
*
* processes collisions and responds to them. 
* 
*/
public class CollidablePhysics extends Physics
{

	public CollidablePhysics(GameObject obj, GameObject universe, IGameEngine eng) 
	{
		super(obj, eng);
		this.universe = universe;
	}
	
	@Override
	public void performAction()
	{
		
		super.performAction();
		Collision[] collisions = CollisionDetection.getCollisions(obj, universe);
		//Collision[] collisions = CollisionDetection.getCollisions(obj, universe);
		Vector2f moveTotal = new Vector2f(0.0f, 0.0f);
		Vector2f move;
		
		for (int i=0; i < collisions.length; i++)
		{
			move = collisions[i].escapeVector;
			if(!collisions[i].collidingObject.getStatic())
			//move = collisions[i].escapeVector;//obj.getBoundingBox().solveCollision(collisions[i].colgetBoundingBox());
			if(!collisions[i].collidingObject.getStatic())
			{
				// lower values make it "less" jittery,
				// and allows the zombies to "compress" more.
				// this seems to be a nice value.
				move.scale(.3f);
			}
			
			moveTotal.x += move.x;
			moveTotal.y += move.y;
		}
		
		if(collisions.length > 0)
		{
			iscolliding = true;
		}
		else 
		{
			iscolliding = false;
		}
		
		obj.translate(moveTotal.x, moveTotal.y);
	}
	boolean iscolliding = false;
	
    Vector2f u1 = new Vector2f();
    Vector2f u2 = new Vector2f();
    Vector2f v1 = new Vector2f();
    Vector2f v2 = new Vector2f();
    Vector2f result = new Vector2f();
    Vector2f prevVelocity = new Vector2f();
    
	float prevX = 0;
	float prevY = 0;
	GameObject universe;
	static final Vector2f zero = new Vector2f();
}
