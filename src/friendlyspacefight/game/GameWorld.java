package friendlyspacefight.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameWorld {
  private double worldsizex,worldsizey;
  private List<GameObject> gameobjects;
  private List<SpaceCraft> spaceships;
  private double timefromLastPowerUp,powerUpSpawnDelay;
  private Game game;

  public GameWorld(double worldsizex, double worldsizey,Game game) {
	super();
	this.worldsizex = worldsizex;
	this.worldsizey = worldsizey;
	gameobjects=new LinkedList<>();
	spaceships=new LinkedList<>();
	this.game=game;
	timefromLastPowerUp=0;
	powerUpSpawnDelay=3;
 }
 protected List<Explosion> getExplosions(){
	List<Explosion> explosions=new LinkedList<>();
	 for(GameObject e:gameobjects){
		if(e instanceof Explosion){
			explosions.add((Explosion)e);
		}
	}
	 return explosions;
	 
 }
protected void addShip(SpaceCraft sc){
	addGameObject(sc);
	spaceships.add(sc);
 }

public void addGameObject(GameObject gameobject){
	gameobjects.add(gameobject);
	gameobject.setWorld(this);
}

protected void updateCollisions(){
	List<GameObject>tempgameobjects=new LinkedList<>(gameobjects);
	for(int i=0;i<tempgameobjects.size();i++){
		if(!tempgameobjects.get(i).isAlive()) continue;
		if(tempgameobjects.get(i) instanceof Collidable){
		Collidable obj=(Collidable) tempgameobjects.get(i);
		Collision col=obj.isCollideWith(null);
		if(col.isCollided()){
			obj.onCollision(col);
		}
		for(int j=0;j<tempgameobjects.size();j++)
		{	
			if(!tempgameobjects.get(j).isAlive()) continue;
			if(tempgameobjects.get(j) instanceof Collidable){
				if(i==j) continue;
				
				Collidable obj2=(Collidable)tempgameobjects.get(j);
				col=obj.isCollideWith(obj2);
				if(col.isCollided()){
					obj.onCollision(col);
				}
				
			}
		}
	}
	}

}
public void update(double deltatime){
	updateCollisions();
	Iterator<GameObject> iter=gameobjects.iterator();
	while(iter.hasNext()){
		
		GameObject o=iter.next();
		o.update(deltatime);
		if(o.getCoordinates().x < -30||o.getCoordinates().y<-30
			||o.getCoordinates().x>worldsizex+30||o.getCoordinates().y>worldsizey+30){
			o.setIsAlive(false);
		}
		
	}
	iter=gameobjects.iterator();
	while(iter.hasNext()){
		GameObject go=iter.next();
		if(!go.isAlive()){
			iter.remove(); 
		}
		
		
	}
	timefromLastPowerUp+=deltatime;
	if(powerUpSpawnDelay<timefromLastPowerUp){
		timefromLastPowerUp=0;
		spawnRandPowerUp();
	}
	
}
private void spawnRandPowerUp(){
	Random rand=new Random();
	int random=rand.nextInt(5);
	if(random==0){
		spawnPoweUp(EffectType.DOUBLEDMG);
	}
	if(random==1){
		spawnPoweUp(EffectType.RAPIDFIRE);
	}
	if(random==2){
		spawnPoweUp(EffectType.REFILL);
	}
	if(random==3){
		spawnPoweUp(EffectType.ULTRARAPIDFIRE);
	}
	if(random==3){
		spawnPoweUp(EffectType.JOKES);
	}
	
}
public void spawnPoweUp(EffectType effect){
	Random rand=new Random();
	int side=rand.nextInt(4);
	if(side==0){
		double pos=rand.nextDouble()*worldsizex;
		Coordinate dir=new Coordinate(rand.nextDouble()*worldsizex,worldsizey);
		addGameObject(new PowerUp(Effect.getInstanceOf(effect),new Coordinate(pos,0),dir.normalize()));
	}else if(side==1){
		double pos=rand.nextDouble()*worldsizex;
		Coordinate dir=new Coordinate(rand.nextDouble()*worldsizex,0);
		addGameObject(new PowerUp(Effect.getInstanceOf(effect),new Coordinate(pos,worldsizey),dir.normalize()));
	}else if(side==2){
		double pos=rand.nextDouble()*worldsizey;
		Coordinate dir=new Coordinate(0,rand.nextDouble()*worldsizey);
		addGameObject(new PowerUp(Effect.getInstanceOf(effect),new Coordinate(worldsizex,pos),dir.normalize()));
	}else if(side==3){
		double pos=rand.nextDouble()*worldsizey;
		Coordinate dir=new Coordinate(worldsizex,rand.nextDouble()*worldsizey);
		addGameObject(new PowerUp(Effect.getInstanceOf(effect),new Coordinate(0,pos),dir.normalize()));
	}
	
}
public double getWorldsizex() {
	return worldsizex;
}

protected void setWorldsizex(double worldsizex) {
	this.worldsizex = worldsizex;
}

public List<GameObject> getGameObjects(){
	return gameobjects;
}


public double getWorldsizey() {
	return worldsizey;
}
public List<SpaceCraft> getSpaceCrafts(){
	return spaceships;
}
public long countAliveShips(){
	return spaceships.stream()
			.filter(ship->ship.isAlive()==true).count();
}
protected void setWorldsizey(double worldsizey) {
	this.worldsizey = worldsizey;
}
public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}

}
