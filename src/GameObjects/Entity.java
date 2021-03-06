package GameObjects;

import java.util.ArrayList;

/**
 * an Entity is anything in the game that gets drawn/update by the game engine.
 * 
 *
 */
public abstract class Entity 
{	
	public Entity(Universe universe)
	{
		this.universe = universe;
		rootNode = new GameObject();
		rootNode.setEntity(this);
		createObject(universe);
		entities.add(this);
	}
	/**
	 *  initializes the enitiy
	 * @param level
	 */
	public abstract void createObject(Universe universe);
	
	public void damage(float dp)
	{
		if(destroyable)
		{
			hp -= dp;
			if(hp < 0)
			{
				hp = 0;
				destroy();
			}
		}
	}
	
	
	public void setStartingLoc(float x, float y)
	{
		startingX = x;
		startingY = y;
		rootNode.setLocalX(x);
		rootNode.setLocalY(y);
	}
	/**
	 *  returns root node of the object
	 * @return
	 */
	public GameObject getRootNode()
	{
		return rootNode;
	}
	
	public Universe getUniverse()
	{
		return universe;
	}
	public void setHP(float hp)
	{
		this.hp = hp;
	}
	public void setMaxHp(float maxHp)
	{
		this.maxHp = maxHp;
	}
	public float getHp()
	{
		return hp;
	}
	public float getMaxHp()
	{
		return maxHp;
	}
	public boolean isDestroyable()
	{
		return destroyable;
	}
	public boolean isDestroyed()
	{
		return destroyed;
	}
	public static void garbageCollect()
	{
		entities = new ArrayList<Entity>();
		System.gc();
	}
	/**
	 * free up resources used by entity
	 */
	public abstract void destroy();
	
	boolean destroyable = false;
	boolean destroyed = false;
	Universe universe;
	GameObject rootNode;
	
	float startingX;
	float startingY;
	
	float hp = 1;
	float maxHp = 1;
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
}
