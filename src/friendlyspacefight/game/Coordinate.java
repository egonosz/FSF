package friendlyspacefight.game;

public class Coordinate {

	double x,y;

	public static Coordinate multiple(Coordinate coor1,Coordinate coor2){
		return new Coordinate(coor1.x*coor2.x,coor1.y*coor2.y);
	}
	
	public static Coordinate multiple(Coordinate coor1,double scalar){
		return new Coordinate(coor1.x*scalar,coor1.y*scalar);
	}
	
	public static Coordinate add(Coordinate coor1,Coordinate coor2){
		return new Coordinate(coor1.x+coor2.x,coor1.y+coor2.y);
	}
	
	public static Coordinate sub(Coordinate coor1,Coordinate coor2){
		return new Coordinate(coor1.x-coor2.x,coor1.y-coor2.y);
	}
	
	public static double length(Coordinate coor){
		return Math.sqrt(coor.x*coor.x+coor.y*coor.y);
	}
	public Coordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Coordinate(Coordinate coor){
		this.x=coor.x;
		this.y=coor.y;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Coordinate normalize(){
		//if(Math.abs(x)<0.0001&&Math.abs(y)<0.0001) return this;
		double x =this.x/length(this);
		double y =this.y/length(this);
		this.x=x;
		this.y=y;
		return this;
		
	}
	public Coordinate multiple(Coordinate coordinate){
		x*= coordinate.x;
		y*= coordinate.y;
		return this;
	}
	public Coordinate multiple(double scalar){
		x*= scalar;
		y*= scalar;
		return this;
	}
	public Coordinate add(Coordinate coordinate){
		x+=coordinate.x;
		y+=coordinate.y;
		return this;
	}
	
 
}
