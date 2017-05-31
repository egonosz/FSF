package friendlyspacefight.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SpaceCraft extends SolidObject {
	private double health,maxhealth,AccPower,deltarotate;
	private Weapon rocketlauncher, particlecannon1, particlecannon2;
	private List<Effect> effects;
	private List<Weapon> weapons;
	private ShipStatistics shipstats;
	public SpaceCraft(Coordinate coordinates, Coordinate direction, double maxspeed) {
		super(coordinates, direction, maxspeed);
		health=1000;
		maxhealth=1000;
		AccPower=0;
		deltarotate=1;
		setupWeapons();
		effects= new LinkedList<>();
		shipstats= new ShipStatistics();
	}
	
	private void setupWeapons(){
		weapons=new LinkedList<>();
		rocketlauncher=new Weapon(this, Rocket.class, 5.0, 0.1);
		weapons.add(rocketlauncher);
		particlecannon1=new Weapon(this, Projectile.class, 1, 0.2);
		particlecannon1.setWeaponOffSet(new Coordinate(-17,0));
		weapons.add(particlecannon1);
		particlecannon2=new Weapon(this, Projectile.class, 1, 0.2);
		particlecannon2.setWeaponOffSet(new Coordinate(17,0));
		weapons.add(particlecannon2);
	}
	
	@Override
	public void onCollision(Collision collision) {
		if(collision==null) return;
		if(collision.getCollided() instanceof WeaponParticle){
			return;
		}
				
		if(collision.getCollided() instanceof PowerUp){
			PowerUp pu=(PowerUp)collision.getCollided();
			shipstats.addPoweruppickups(1);
			addEffect(pu.getEffect());
			pu.setIsAlive(false);
			return;
		}
		super.onCollision(collision);
	}
	@Override
	public Collision isCollideWith(Collidable collidable){
		Collision collision= super.isCollideWith(collidable);
		if(collision==null) return collision;
		if((collidable instanceof WeaponParticle)){
				WeaponParticle wp=(WeaponParticle)collidable;
				
				if(wp.getSpacecraft()!=this){
					collision.setIsCollided(false);
				} else {
					collision.setIsCollided(false);
				}
				return collision;
			}
		
		if(collision.getCollided() instanceof PowerUp){
			collision.setIsCollided(true);
			return collision;
		}
		return collision;
}
	
	@Override
	public void update(double deltatime) {
		Iterator<Effect> iter=effects.iterator();
		while(iter.hasNext()){
			Effect effect=iter.next();
			effect.update(deltatime);
			if(!effect.isActive()){
				iter.remove();
			}
		}
		super.update(deltatime);
		updateWeapons(deltatime);
		List<Explosion> explosions=getWorld().getExplosions();
		if(!explosions.isEmpty()){
			explosions.get(0);
		}
		for(Explosion e: explosions){
			Coordinate self=Coordinate.sub(getCoordinates(),e.getCoordinates());
			double distance=Coordinate.length(self);
			if(distance<getCollisionHitCircle()+e.getCollisionHitCircle()){
				dmgship(e.getDmg());
				self.normalize();
				self=Coordinate.add(Coordinate.multiple(self,e.getPower()),getMoveVector());
				setMoveVector(self);
				long dmg=(long)e.getDmg();
				e.getSpacecraft().getShipstats().addShothits(1).addDmgdone(dmg);
			}
		}
		Coordinate olddir=getDirection();
		
		
		Coordinate newdir=new Coordinate(0, 0);
		newdir.x=olddir.x*Math.cos(deltarotate*deltatime)-olddir.y*Math.sin(deltarotate*deltatime);
		newdir.y=olddir.y*Math.cos(deltarotate*deltatime)+olddir.x*Math.sin(deltarotate*deltatime);
		setDirection(newdir);
		deltarotate=0;
		
		
		
		Coordinate newMove=Coordinate.add(getMoveVector(),Coordinate.multiple(getDirection(), AccPower*deltatime));
		setMoveVector(newMove);
		AccPower=0;
		

	}
	private void updateWeapons(double deltatime){
		particlecannon1.update(deltatime);
		particlecannon2.update(deltatime);
		rocketlauncher.update(deltatime);
	}
	private void dmgship(double dmg){
		health-=dmg;
		shipstats.addDmgtaken((long)dmg);
		if(health<0){
			setIsAlive(false);
		}
	}
	public void addEffect(Effect effect){
		boolean isrefilled=false;
		for(Effect e:effects){
			if(e.getEffectType()==effect.getEffectType()){
				e.refill();
				isrefilled=true;
				continue;
			}
		}
		if(!isrefilled){
			effects.add(effect);
			switch(effect.getEffectType()){
			case DOUBLEDMG:
				 DoubleDmg e5=(DoubleDmg)effect;
				 e5.setSpacecraft(this);
				 e5.applyEffect();
				 
				break;
			case JOKES:
				Joy e4=(Joy)effect;
				e4.setSpacecraft(this);
				e4.applyEffect();
				break;
			case RAPIDFIRE:
				Rapidfire e1=(Rapidfire)effect;
				 e1.setSpacecraft(this);
				 e1.applyEffect();
				break;
			case REFILL:
				Refill e2=(Refill)effect;
				e2.setSpacecraft(this);
				e2.applyEffect();
				break;
			case ULTRARAPIDFIRE:
				UltraRapidFire e3=(UltraRapidFire)effect;
				 e3.setSpacecraft(this);
				 e3.applyEffect();
				break;
			default:
				break;
				
			}
			
		}
	 
	}

	public void accelerate(){
		AccPower=300;
	}
	
	public void decelerate(){
		AccPower=-300;
	}
	
	public void turnLeft(){
		deltarotate=-160*(Math.PI/180.0);
	}
	public void turnRight(){
		deltarotate=160*(Math.PI/180.0);
	}
	public void shootRocket(){
		rocketlauncher.Shoot();
	}
	
	public void shootParticle(){
		particlecannon1.Shoot();
		particlecannon2.Shoot();
	}
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public double getMaxhealth() {
		return maxhealth;
	}
	public void refillHealt(){
		health=maxhealth;
	}
	public void setMaxhealth(double maxhealth) {
		this.maxhealth = maxhealth;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}
	public List<Effect> getEffects(){
		return effects;
	}

	public ShipStatistics getShipstats() {
		return shipstats;
	}

	public void setShipstats(ShipStatistics shipstats) {
		this.shipstats = shipstats;
	}
	
}
