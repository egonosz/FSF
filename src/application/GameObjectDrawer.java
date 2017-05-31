package application;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import friendlyspacefight.game.Coordinate;
import friendlyspacefight.game.EffectType;
import friendlyspacefight.game.Explosion;
import friendlyspacefight.game.Projectile;
import friendlyspacefight.game.Rocket;
import friendlyspacefight.game.SpaceCraft;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameObjectDrawer {
private GraphicsContext graphicalcontext;private Canvas canvas;
private Image shipimage;
private List<DrawableExplosion> explosions;
public GameObjectDrawer(Canvas canvas) {
	super();
	this.canvas=canvas;
	this.graphicalcontext=canvas.getGraphicsContext2D();
	this.shipimage= new Image("/SpaceCraft.png");
	explosions=new LinkedList<DrawableExplosion>();
}
public void drawPowerUp(EffectType type,Coordinate coor,Color color){
	graphicalcontext.setFill(color);
	graphicalcontext.save();
	graphicalcontext.translate(coor.getX()-5, coor.getY());
	graphicalcontext.fillOval(0.0d, 0.0d,24.0d,24.0d);
	graphicalcontext.setFill(Color.WHITE);

	switch(type){
	case DOUBLEDMG:
		graphicalcontext.fillText("DD", 0,16);
		break;
	case JOKES:
		graphicalcontext.fillText("JK", 0, 16);
		break;
	case RAPIDFIRE:
		graphicalcontext.fillText("RF", 0,16);
		break;
	case REFILL:
		graphicalcontext.fillText("RE", 0,16);
		break;
	case ULTRARAPIDFIRE:
		graphicalcontext.fillText("URF", 0, 16);
		break;
	default:
		break;
	
	}
	graphicalcontext.restore();
}
public void redrawCanvas(){
	graphicalcontext.setFill(Color.WHITE);
	graphicalcontext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
}
private void drawImage(Image img, double degree,Coordinate coords,Color color ){
	graphicalcontext.save();
	graphicalcontext.translate(coords.getX(), coords.getY());
	graphicalcontext.rotate(-degree+180);
	graphicalcontext.scale(0.5, 0.5);

	graphicalcontext.drawImage(img, -img.getHeight()/2, -img.getWidth()/2);
	graphicalcontext.restore();
}
public void drawShip(SpaceCraft go,Color color){
	drawImage(shipimage, go.getDirectionInDegree(), go.getCoordinates(),color);
}
public void redrawExplosions(double deltatime){
	Iterator<DrawableExplosion> iter=explosions.iterator();
	while(iter.hasNext()){
		DrawableExplosion explosion=iter.next();
		explosion.update(deltatime);
		explosion.draw();
		if(explosion.isDisapeared()){
			iter.remove();
		}
	}
}
public void drawRocket(Rocket go,Color color){
	Coordinate coords=go.getCoordinates();
	double degree=go.getDirectionInDegree();
	graphicalcontext.setFill(color);
	graphicalcontext.save();
	graphicalcontext.translate(coords.getX(), coords.getY());
	graphicalcontext.rotate(-degree+180);
	graphicalcontext.fillOval(0.0d, 0.0d,5.0d,10.0d);
	graphicalcontext.restore();
}

public void drawProjectile(Projectile go,Color color){
	Coordinate coords=go.getCoordinates();
	double degree=go.getDirectionInDegree();
	graphicalcontext.setFill(color);
	graphicalcontext.save();
	graphicalcontext.translate(coords.getX()-10, coords.getY());
	graphicalcontext.rotate(-degree+180);
	graphicalcontext.fillOval(-2.0d, 0.0d, 2.0d,10.0d);
	graphicalcontext.restore();
}

public void drawExplosion(Explosion go,Color color){
	explosions.add(new DrawableExplosion(go.getCollisionHitCircle(), go.getCoordinates(), Color.ORANGE, graphicalcontext));
}
}
