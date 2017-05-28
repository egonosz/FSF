package friendlyspacefight.game;

public class Projectile extends WeaponParticle {
	Projectile(){
	super();	
	}

	@Override
	public void initialize() {
		setDmg(1);
		setMaxSpeed(1000);
		setMoveVector(Coordinate.multiple(getDirection(),1000));	
	}



	
	
	
}
