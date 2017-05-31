package friendlyspacefight.game;

public abstract class WeaponParticle extends SolidObject {
	private SpaceCraft spacecraft;
	private double dmg;
	
	public WeaponParticle(){
		super();
		spacecraft=null;
		dmg=0;
	}
	
	public void initialize(){
		
	}
	public WeaponParticle(Coordinate coordinates, Coordinate direction, double maxspeed,SpaceCraft spacecraft) {
		super(coordinates, direction, maxspeed);
		this.spacecraft=spacecraft;
	}
	
	@Override
	public void onCollision(Collision collision) {
		GameWorld wrld=getWorld();
		Explosion exp=new Explosion(collision.getCollisioncoor(), getDmg(),getSpacecraft());
		wrld.addGameObject(exp);
		this.setIsAlive(false);
	}
	
	@Override
	public Collision isCollideWith(Collidable collidable) {
		Collision collision=super.isCollideWith(collidable);
		if(collision.isCollided()){
			if(collidable instanceof SpaceCraft){
				SpaceCraft sc=(SpaceCraft)collidable;
				if(sc==getSpacecraft()){
					return new Collision(false);
				}
				return new Collision(sc,Collision.CalcCollisionCoordinates(sc.getCoordinates(), this.getCoordinates(),
							sc.getCollisionHitCircle(), this.getCollisionHitCircle()));
				}
			else if(collidable instanceof WeaponParticle){
				WeaponParticle wp=(WeaponParticle)collidable;
				if(wp.getSpacecraft()!=this.getSpacecraft()){
					return new Collision(false);
				}
			}
			
		} else {
			return new Collision(false);
		}
		return new Collision(false);
	} 
	
	public SpaceCraft getSpacecraft() {
		return spacecraft;
	}


	public void setSpacecraft(SpaceCraft spacecraft) {
		this.spacecraft = spacecraft;
	}


	public double getDmg() {
		return dmg;
	}
	
	public void setDmg(double dmg) {
		this.dmg = dmg;
	}
	
}
