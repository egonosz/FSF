package friendlyspacefight.game;

public class Collision {
private Collidable collided;
private Coordinate collisioncoor;
private boolean isCollided;

public static Coordinate CalcCollisionCoordinates(Coordinate coor1,Coordinate coor2, double rad1,double rad2){
	
	double distance=Coordinate.length(Coordinate.sub(coor1,coor2));
	if(distance<(rad1+rad2)){
		Coordinate tempc=Coordinate.sub(coor1,coor2);
		double scale=(rad2/(rad2+rad1));
		tempc.x*=scale;
		tempc.y*=scale;
		Coordinate coor=Coordinate.add(tempc,coor2);
		return coor;
	}
	return null;
}


public Collision(Collidable collided, Coordinate collisioncoor) {
	super();
	this.collided=collided;
	this.collisioncoor = collisioncoor;
	this.isCollided=true;

}

public Collision(boolean isCollided) {
	super();
	this.isCollided=isCollided;
}


public Collidable getCollided() {
	return collided;
}

public void setCollidedWith(Collidable collided) {
	this.collided = collided;
}

public Coordinate getCollisioncoor() {
	return collisioncoor;
}

public void setCollisioncoor(Coordinate collisioncoor) {
	this.collisioncoor = collisioncoor;
}
public void setIsCollided(boolean isCollided) {
	this.isCollided = isCollided;
}


public boolean isCollided() {
	return isCollided;
}
}