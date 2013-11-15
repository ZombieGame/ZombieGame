package GameObjects;

import org.lwjgl.util.vector.Vector2f;

import TextureEngine.ITexture;
import Actions.CollidablePhysics;
import Drawing.DrawBoundingBox;
import Drawing.SimpleDraw;
import Geometry.AABB;

public class Box extends Entity
{

	public Box(Universe universe) {
		super(universe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createObject(Universe universe)
	{
		ITexture boxTexture = universe.getTextureEngine().LoadTexture("gfx/Environment/box_whole.png", 0);
		CollidablePhysics col = new CollidablePhysics(rootNode, universe.getHandle(), universe.getGameEngine());
		rootNode.addAction(col);
		rootNode.setMass(.1f);
		rootNode.setDrawingInterface(new SimpleDraw(boxTexture));
		rootNode.setBoundingBox(new AABB(1f,1f));
		rootNode.setCollidable(true);
		universe.addEntity(this);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}