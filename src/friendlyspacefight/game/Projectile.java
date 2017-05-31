package friendlyspacefight.game;

public class Projectile extends WeaponParticle {
	public Projectile(){
	super();	
	}

	@Override
	public void initialize() {
		getSpacecraft().getShipstats().addProjectilesfired(1);
		setDmg(1);
		setMaxSpeed(1000);
		setMoveVector(Coordinate.multiple(getDirection(),1000));	
	}



	
	
	
}
