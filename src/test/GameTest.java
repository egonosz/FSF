package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import friendlyspacefight.game.Collision;
import friendlyspacefight.game.Coordinate;
import friendlyspacefight.game.DoubleDmg;
import friendlyspacefight.game.Effect;
import friendlyspacefight.game.EffectType;
import friendlyspacefight.game.Explosion;
import friendlyspacefight.game.Game;
import friendlyspacefight.game.GameObject;
import friendlyspacefight.game.GameWorld;
import friendlyspacefight.game.Joy;
import friendlyspacefight.game.PowerUp;
import friendlyspacefight.game.Projectile;
import friendlyspacefight.game.Rapidfire;
import friendlyspacefight.game.Rocket;
import friendlyspacefight.game.SpaceCraft;
import friendlyspacefight.game.Game.GameState;

public class GameTest {
	private Game game;
	private GameWorld gw;
	
	private void getNewGame(){
		this.game=new Game(1000, 800, "test2", "test2");
		this.gw= game.getGameWorld();
	}
	@Test
	public void ShootTest() {
		getNewGame();
		
		List<SpaceCraft> sps=gw.getSpaceCrafts();
		List<GameObject> go=gw.getGameObjects();
		int numOfobjects=go.size();
		sps.get(0).shootParticle();
		assertTrue(go.get(go.size()-1) instanceof Projectile); 
		assertTrue(go.get(go.size()-2) instanceof Projectile);
		numOfobjects=go.size();
		sps.get(0).shootRocket();
		assertTrue(go.get(go.size()-1) instanceof Rocket); 
	}
	public boolean ContainsInstanceOf(List<GameObject> gos, Class<?> cl){
		for(GameObject e: gos){
			if(cl.isInstance(e)){
				return true;
			}
		}
		return false;
	}
	@Test
	public void CreateExplosionTest(){
		getNewGame();
		List<SpaceCraft> sps=gw.getSpaceCrafts();
		List<GameObject> go=gw.getGameObjects();
		sps.get(0).setCoordinates(100,100);
		Projectile proj=new Projectile();
		proj.setCollisionHitCicrcle(5);
		proj.setCoordinates(110,110);
		proj.setSpacecraft(sps.get(1));
		gw.addGameObject(proj);
		game.start();
		game.updateWorld(0.1);
		assertTrue(ContainsInstanceOf(gw.getGameObjects(), Explosion.class));

		getNewGame();
		sps=gw.getSpaceCrafts();
		go=gw.getGameObjects();
		sps.get(0).setCoordinates(100,100);
		Rocket rock=new Rocket();
		rock.setCollisionHitCicrcle(5);
		rock.setCoordinates(110,110);
		rock.setSpacecraft(sps.get(1));
		gw.addGameObject(rock);
		game.start();
		game.updateWorld(0.1);
		assertTrue(ContainsInstanceOf(gw.getGameObjects(), Explosion.class));
	
	}
	@Test
	public void shipdmgTest(){
		getNewGame();
		List<SpaceCraft> sps=gw.getSpaceCrafts();
		gw.addGameObject(new Explosion(new Coordinate(100,100), 10, sps.get(0)));
		double life=sps.get(1).getHealth();
		sps.get(1).setCoordinates(90,90);
		game.start();
		game.updateWorld(0.1);
		assertTrue(Math.abs((life-10)-sps.get(1).getHealth())<0.0001);
	}
	
	@Test
	public void SolidObjColltest() {
		getNewGame();
		List<SpaceCraft> scs=gw.getSpaceCrafts();
		SpaceCraft testcraft=scs.get(0);
		testcraft.setCoordinates(2,100);
		Collision coll=testcraft.isCollideWith(null);
		assertTrue(coll.isCollided());
		testcraft.onCollision(coll);
		assertTrue(Math.abs(testcraft.getCollisionHitCircle()+3-testcraft.getCoordinates().getX())<0.0001);
		testcraft.setCoordinates(gw.getWorldsizex()-2,100);
		coll=testcraft.isCollideWith(null);
		assertTrue(coll.isCollided());
		testcraft.onCollision(coll);
		assertTrue(Math.abs(-testcraft.getCollisionHitCircle()+gw.getWorldsizex()-3-testcraft.getCoordinates().getX())<0.0001);
		testcraft.setCoordinates(100,gw.getWorldsizey());
		coll=testcraft.isCollideWith(null);
		assertTrue(coll.isCollided());
		testcraft.onCollision(coll);
		assertTrue(Math.abs(-testcraft.getCollisionHitCircle()+gw.getWorldsizey()-3-testcraft.getCoordinates().getY())<0.0001);
		testcraft.setCoordinates(100,2);
		coll=testcraft.isCollideWith(null);
		assertTrue(coll.isCollided());
		testcraft.onCollision(coll);
		assertTrue(Math.abs(testcraft.getCollisionHitCircle()+3-testcraft.getCoordinates().getY())<0.0001);
		
		getNewGame();
		testcraft=scs.get(0);
		testcraft.setCoordinates(100,100);
		testcraft.setMoveVector(10,0);
		
		SpaceCraft testcraft2=scs.get(1);
		testcraft2.setMoveVector(-10,0);
		testcraft2.setCoordinates(110,100);
		
		coll=testcraft.isCollideWith(testcraft2);
		assertTrue(coll.isCollided());
		testcraft.onCollision(coll);
		assertTrue((testcraft.getMoveVector().getX()!=testcraft.getCollisionnewmovevec().getX())||(testcraft.getMoveVector().getY()!=testcraft.getCollisionnewmovevec().getY() ));
	}
	
	
	@Test
	public void TestPowerUps(){
		getNewGame();
		assertFalse(ContainsInstanceOf(gw.getGameObjects(),PowerUp.class));
		gw.spawnPoweUp(EffectType.DOUBLEDMG);
		assertTrue(ContainsInstanceOf(gw.getGameObjects(),PowerUp.class));
		assertTrue(((PowerUp)gw.getGameObjects().get(gw.getGameObjects().size()-1)).getEffect().getEffectType()==EffectType.DOUBLEDMG);
		gw.spawnPoweUp(EffectType.RAPIDFIRE);
		assertTrue(((PowerUp)gw.getGameObjects().get(gw.getGameObjects().size()-1)).getEffect().getEffectType()==EffectType.RAPIDFIRE);
		gw.spawnPoweUp(EffectType.ULTRARAPIDFIRE);
		assertTrue(((PowerUp)gw.getGameObjects().get(gw.getGameObjects().size()-1)).getEffect().getEffectType()==EffectType.ULTRARAPIDFIRE);
		gw.spawnPoweUp(EffectType.REFILL);
		assertTrue(((PowerUp)gw.getGameObjects().get(gw.getGameObjects().size()-1)).getEffect().getEffectType()==EffectType.REFILL);
		gw.spawnPoweUp(EffectType.JOKES);
		assertTrue(((PowerUp)gw.getGameObjects().get(gw.getGameObjects().size()-1)).getEffect().getEffectType()==EffectType.JOKES);
		getNewGame();
		SpaceCraft sc=gw.getSpaceCrafts().get(0);
		sc.setCoordinates(100,100);
		PowerUp powerup=new PowerUp(new DoubleDmg(30),new Coordinate(100, 110),new Coordinate(1,0));
		Collision coll=sc.isCollideWith(powerup);
		assertTrue(coll.isCollided());
		assertTrue(sc.getEffects().size()==0);
		sc.onCollision(coll);
		assertTrue(sc.getEffects().size()==1);
		assertTrue(sc.getEffects().get(0).getEffectType()==EffectType.DOUBLEDMG);
	}
	@Test
	public void TestEffects(){
		getNewGame();
		game.start();
		SpaceCraft sc=gw.getSpaceCrafts().get(0);
		sc.addEffect(new DoubleDmg(30));
		Effect effect=sc.getEffects().get(0);
		game.updateWorld(29);
		assertTrue(effect.isActive());
		game.updateWorld(5);
		game.updateWorld(5);
		assertFalse(effect.isActive());
		
			
		getNewGame();
		sc=game.getPlayer1().getShip();
		sc.addEffect(new Joy(30));
		effect=sc.getEffects().get(0);
		assertFalse(effect.isActive());
		assertTrue(game.getPlayer1().getShip()!=sc);
	}	
	
	
}
