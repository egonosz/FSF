package application;

import friendlyspacefight.game.Coordinate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableExplosion {
	private double timeTodisapear;
	private double timeAlive;
	private double radius;
	private Coordinate coords;
	private Color color;
	private GraphicsContext graphicalcontext;
	public DrawableExplosion(double radius,Coordinate coords,Color color,GraphicsContext gc) {
		this.radius=radius;
		timeTodisapear=0.1;
		timeAlive=timeTodisapear;
		graphicalcontext=gc;
		this.color=color;
		this.coords=new Coordinate(coords.getX(),coords.getY());
	}
	public void draw(){
		double magicradius=magicFunction()*radius;
		graphicalcontext.setFill(color);
		graphicalcontext.fillOval(coords.getX()-magicradius,coords.getY()-magicradius , 2*magicradius,2*magicradius);
		
	}
	public boolean isDisapeared(){
		if(timeTodisapear<timeAlive) return true;
		else return false;
	}
	
	public void update(double deltatime){
		if(!isDisapeared()){
		timeAlive-=deltatime;
		}
	}
	
	private double magicFunction(){
		double x=timeAlive/timeTodisapear;
		double magicnumber=2.32d;
		return (1-Math.exp(x*magicnumber))*Math.exp(x*magicnumber)*1.72d;
	}
}
