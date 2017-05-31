package friendlyspacefight.game;

public abstract class SolidObject extends BaseGameObject implements Collidable {
private double collisionCircleRadius;
private Coordinate collisionnewmovevec,collisionnewcoor;
private boolean iscollidedonlastcheck;
	public SolidObject(){
		super();
		collisionCircleRadius=0;
	}
	
	public SolidObject(Coordinate coordinates, Coordinate direction, double maxspeed) {
		super(coordinates, direction, maxspeed);
		collisionCircleRadius=0;
		iscollidedonlastcheck=false;
	}
	
	public void update(double deltatime){
		if(iscollidedonlastcheck){
			setMoveVector(collisionnewmovevec);
			setCoordinates(collisionnewcoor);
			iscollidedonlastcheck=false;
		}
		super.update(deltatime);
	}
	
	@Override
	public void onCollision(Collision collision) {
		
			if(collision.getCollided() instanceof SolidObject){
				SolidObject collided=(SolidObject)(collision.getCollided());
				Coordinate moveVector=collided.getMoveVector();
				collisionnewmovevec=Coordinate.multiple(moveVector,0.3);
				iscollidedonlastcheck=true;
				collisionnewcoor=Coordinate.sub(getCoordinates(),collision.getCollisioncoor());
				double distance=Coordinate.length(collisionnewcoor);
				collisionnewcoor=Coordinate.multiple(collisionnewcoor,1-distance/getCollisionHitCircle());
				collisionnewcoor=Coordinate.add(collisionnewcoor, getCoordinates());
				/*double distance=Coordinate.length(Coordinate.sub(c,getCoordinates()));
				if(distance<(collided.getCollisionHitCircle()+getCollisionHitCircle())){
					double scale=((collided.getCollisionHitCircle()+getCollisionHitCircle())/distance)-
							((collided.getCollisionHitCircle()+getCollisionHitCircle()));
					Coordinate vec=Coordinate.sub(getCoordinates(),c);		
					vec=Coordinate.multiple(vec, scale);
					vec=Coordinate.add(vec,getCoordinates());
					setCoordinates(vec);
					}*/
				
			}else 
		if(collision.getCollided()==null){
			Coordinate newvec=getMoveVector();
			Coordinate newcoord=getCoordinates();
				
				if(collision.getCollisioncoor().x==0){
					newvec.x=-newvec.x;
					newvec=Coordinate.multiple(newvec, 0.8d);
					newcoord.x=getCollisionHitCircle()+3.0d;
					setCoordinates(newcoord);
					setMoveVector(newvec);
				}
				if(collision.getCollisioncoor().x==getWorld().getWorldsizex()){
					
					newvec.x=-newvec.x;
					newvec=Coordinate.multiple(newvec, 0.8);
					newcoord.x=getWorld().getWorldsizex()-getCollisionHitCircle()-3.0d;
					setCoordinates(newcoord);
					setMoveVector(newvec);
				}
				if(collision.getCollisioncoor().y==0){
					newcoord.y=getCollisionHitCircle()+3.0d;
					newvec.y=-newvec.y;
					newvec=Coordinate.multiple(newvec, 0.8);
					setCoordinates(newcoord);
					setMoveVector(newvec);
					
				}
				if(collision.getCollisioncoor().y==getWorld().getWorldsizey()){
					newcoord.y=getWorld().getWorldsizey()-getCollisionHitCircle()-3.0d;
					newvec.y=-newvec.y;
					newvec=Coordinate.multiple(newvec, 0.8);
					setCoordinates(newcoord);
					setMoveVector(newvec);
				}
			}
		return;
	}

	@Override
	public Collision isCollideWith(Collidable collidable) {
		if(collidable!=null){
			if(collidable instanceof SolidObject){
				SolidObject collobj=(SolidObject)collidable;
				Coordinate collisionCoord=Collision.CalcCollisionCoordinates(getCoordinates(),
						collobj.getCoordinates(), getCollisionHitCircle(), collobj.getCollisionHitCircle());
			
					if(collisionCoord!=null){
						Collision collision=new Collision(collidable, collisionCoord);
						collision.setIsCollided(true);
						return collision;
					}
			}
		} else {
			double xsize=getWorld().getWorldsizex();
			double ysize=getWorld().getWorldsizey();
			Coordinate coor=getCoordinates();
			if(coor.x<getCollisionHitCircle()){
				return new Collision(null,new Coordinate(0,coor.y));
			} else if(coor.y<getCollisionHitCircle()){
				return new Collision(null,new Coordinate(coor.x,0));
			} else if(coor.y+getCollisionHitCircle()>ysize){
				return new Collision(null,new Coordinate(coor.x,ysize));
			} else if(coor.x+getCollisionHitCircle()>xsize){
				return new Collision(null,new Coordinate(xsize,coor.y));
			}
		}
		
		return new Collision(false);
	}

	@Override
	public void setCollisionHitCicrcle(double radius) {
		collisionCircleRadius=radius;

	}

	@Override
	public double getCollisionHitCircle() {
		return collisionCircleRadius;
	}

	public Coordinate getCollisionnewmovevec() {
		return collisionnewmovevec;
	}

	public void setCollisionnewmovevec(Coordinate collisionnewmovevec) {
		this.collisionnewmovevec = collisionnewmovevec;
	}

	public Coordinate getCollisionnewcoor() {
		return collisionnewcoor;
	}

	public void setCollisionnewcoor(Coordinate collisionnewcoor) {
		this.collisionnewcoor = collisionnewcoor;
	}

	

}
