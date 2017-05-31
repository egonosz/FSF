package friendlyspacefight.game;

public class Explosion extends BaseGameObject implements Collidable {
	private double power;
	private double hitCircleRadius;
	private double dmg;
	private boolean boom;
	private SpaceCraft spacecraft;
	
	public Explosion(Coordinate coordinates,double dmg,SpaceCraft spacecraft) {
		super();
		this.setPower(dmg*10);
		this.setDmg(dmg);
		setCollisionHitCicrcle(dmg*10);
		setCoordinates(coordinates);
		setIsAlive(true);
		this.spacecraft=spacecraft;
		boom=false;
	}
	@Override
	public void update(double deltatime) {
		if(boom){
			setIsAlive(false);
		}
		boom=true;
	}
	
	public double getDmg() {
		return dmg;
	}
	
	public void setDmg(double dmg){
		this.dmg=dmg;
	}
	
	@Override
	public void onCollision(Collision collision) {
	}

	@Override
	public Collision isCollideWith(Collidable collidable) {
		return new Collision(false);
	}

	@Override
	public void setCollisionHitCicrcle(double radius) {
		hitCircleRadius=radius;
	}

	@Override
	public double getCollisionHitCircle() {
		return hitCircleRadius;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public boolean boom() {
		return boom;
	}
	public SpaceCraft getSpacecraft() {
		return spacecraft;
	}
	public void setSpacecraft(SpaceCraft spacecraft) {
		this.spacecraft = spacecraft;
	}
	

}
