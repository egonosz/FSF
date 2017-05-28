package application;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import friendlyspacefight.game.Explosion;
import friendlyspacefight.game.Game;
import friendlyspacefight.game.Game.GameState;
import friendlyspacefight.game.GameObject;
import friendlyspacefight.game.Player;
import friendlyspacefight.game.PowerUp;
import friendlyspacefight.game.Projectile;
import friendlyspacefight.game.Rocket;
import friendlyspacefight.game.SpaceCraft;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class MainLoop extends AnimationTimer {
	private long lastTimeUpdate;
	private Game game;
	private Canvas worldmap;
	private GameObjectDrawer drawer;
	private HudDrawer huddrawer;
	private EnumSet<KeyCode> keydown;
	
	public MainLoop(Game game,Canvas canvas) {
		super();
		lastTimeUpdate= System.nanoTime();
		this.game=game;
		worldmap=canvas;
		drawer=new GameObjectDrawer(worldmap);
		huddrawer=new HudDrawer(canvas,game.getPlayer1(),game.getPlayer2());
	}
	public void addKeyDownSet(EnumSet<KeyCode> keydown){
		this.keydown=keydown;
	}
	@Override
	public void handle(long currentTime) {
		long deltatime=currentTime-lastTimeUpdate;
		double gamedeltatime= convertToGameTime(deltatime);
		updateShips();
		game.updateWorld(gamedeltatime);
		updateCanvas(gamedeltatime);
		lastTimeUpdate=currentTime;
		if(game.getGameState()!=GameState.RUNNING){
		try { 	
			FXMLLoader loader =new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			this.stop();	
			AnchorPane root = loader.load();
				Scene scene = new Scene(root,1000,800);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage mainMenuStage = (Stage) worldmap.getScene().getWindow();
				mainMenuStage.setScene(scene);
				mainMenuStage.show();
		        scene.getRoot().requestFocus();

				MenuController menucontroller =loader.getController();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	
	private void updateShips(){
		if(keydown.contains(KeyCode.UP)) game.getPlayer1().getShip().accelerate();
		if(keydown.contains(KeyCode.DOWN)) game.getPlayer1().getShip().decelerate();
		if(keydown.contains(KeyCode.RIGHT)) game.getPlayer1().getShip().turnRight();
		if(keydown.contains(KeyCode.LEFT)) game.getPlayer1().getShip().turnLeft();
		if(keydown.contains(KeyCode.SPACE)) game.getPlayer1().getShip().shootParticle();
		if(keydown.contains(KeyCode.CONTROL)) game.getPlayer1().getShip().shootRocket();
		if(keydown.contains(KeyCode.W)) game.getPlayer2().getShip().accelerate();
		if(keydown.contains(KeyCode.S)) game.getPlayer2().getShip().decelerate();
		if(keydown.contains(KeyCode.D)) game.getPlayer2().getShip().turnRight();
		if(keydown.contains(KeyCode.A)) game.getPlayer2().getShip().turnLeft();
		if(keydown.contains(KeyCode.E)) game.getPlayer2().getShip().shootParticle();
		if(keydown.contains(KeyCode.R)) game.getPlayer2().getShip().shootRocket();

	}
	private void updateCanvas(double deltatime){
		drawer.redrawCanvas();
		List<GameObject> gameobjects=game.getGameWorld().getGameObjects();
		for(GameObject e :gameobjects){
			if(e instanceof Rocket){
				drawer.drawRocket((Rocket)e, Color.BLUE);
			}
			if(e instanceof Projectile){
				drawer.drawProjectile((Projectile)e, Color.RED);
			}
			if(e instanceof Explosion){
				Explosion exp=(Explosion)e;
				if(exp.boom()){
					drawer.drawExplosion(exp, Color.ORANGE);
				}
			}
			if(e instanceof SpaceCraft){
				SpaceCraft sc= (SpaceCraft)e;
				if(sc==game.getPlayer1().getShip()){
					drawer.drawShip(sc, Color.RED);
				} else if(sc==game.getPlayer2().getShip()){
					drawer.drawShip(sc, Color.BLUE);
				}
			}
			if(e instanceof PowerUp){
				PowerUp pu=(PowerUp)e;
				drawer.drawPowerUp(pu.getEffect().getEffectType(), pu.getCoordinates(), Color.PURPLE);
			}
		}
		drawer.redrawExplosions(deltatime);
		huddrawer.draw(deltatime);
	}
	private double convertToGameTime(long deltaTime){
		return ((double) deltaTime)/1.0e9d;
		
	}

}
