package friendlyspacefight.game;

public class Weapon {
	private Class<?> particleclass;
	private SpaceCraft spacecraft;
	private double dmg,firerate,timefromLastShot;
	private boolean canfire;
	private Coordinate weaponOffSet;
	
	Weapon(SpaceCraft spaceCraft, Class<?> particle, double dmg,double firerate){ 
		this.spacecraft=spaceCraft;
		this.particleclass=particle;
		this.dmg=dmg;
		this.canfire=true;
		this.timefromLastShot=0;
		this.firerate=firerate;
		this.weaponOffSet=new Coordinate(0,0);
		this.weaponOffSet.x=0;
		this.weaponOffSet.y=0;
	}
	
	public void update(double deltatime){
		if(!canfire){
			if(timefromLastShot<firerate){
				timefromLastShot+=deltatime;
			} else {
				canfire=true;
				timefromLastShot=0;
			}
		}
	}
	
	public void Shoot(){
		try {
			if(!canfire) return; 
			canfire=false;
			WeaponParticle wp=(WeaponParticle) particleclass.newInstance();
			wp.setDmg(dmg);
			wp.setDirection(spacecraft.getDirection());
			wp.setSpacecraft(spacecraft);
			Coordinate xnorm=new Coordinate(spacecraft.getDirection().y,-(spacecraft.getDirection().x));
			xnorm=Coordinate.multiple(xnorm, weaponOffSet.x);
			Coordinate ynorm=Coordinate.multiple(spacecraft.getDirection(),weaponOffSet.y);
			Coordinate wpcoor=Coordinate.add(Coordinate.add(xnorm, ynorm),getSpacecraft().getCoordinates());
			wp.setCoordinates(wpcoor);
			wp.initialize();
			spacecraft.getWorld().addGameObject(wp);
		
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public Class<?> getParticleclass() {
		return particleclass;
	}

	public void setParticleclass(Class<?> particleclass) {
		this.particleclass = particleclass;
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

	public double getFirerate() {
		return firerate;
	}

	public void setFirerate(double firerate) {
		this.firerate = firerate;
	}

	public double getTimefromLastShot() {
		return timefromLastShot;
	}

	public void setTimefromLastShot(double timefromLastShot) {
		this.timefromLastShot = timefromLastShot;
	}

	public boolean canfire() {
		return canfire;
	}

	public void setCanfire(boolean canfire) {
		this.canfire = canfire;
	}

	public Coordinate getWeaponOffSet() {
		return new Coordinate(weaponOffSet);
	}

	public void setWeaponOffSet(Coordinate weaponOffSet) {
		this.weaponOffSet = weaponOffSet;
	}



}
