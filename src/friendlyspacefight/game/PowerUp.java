package friendlyspacefight.game;


public class PowerUp extends SolidObject implements Collidable {
private Effect effect;

public PowerUp(Effect effect,Coordinate coors,Coordinate dir) {
	super(coors,dir,100);
	this.setMoveVector(Coordinate.multiple(dir, 100));
	this.effect = effect;
	
	setCollisionHitCicrcle(30);
}

@Override
public void onCollision(Collision collision) {
	setIsAlive(false);
}

@Override
public Collision isCollideWith(Collidable collidable) {
	Collision coll=super.isCollideWith(collidable);
	if(collidable instanceof SpaceCraft){
		return coll;
	}
	coll.setIsCollided(false);
	return coll;
}

public Effect getEffect() {
	return effect;
}

public void setEffect(Effect effect) {
	this.effect = effect;
}
	
}
