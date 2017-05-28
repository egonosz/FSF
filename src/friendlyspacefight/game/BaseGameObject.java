package friendlyspacefight.game;

public abstract class BaseGameObject implements GameObject{
	private Coordinate coordinates;
	private Coordinate direction;
	private double maxspeed;
	private Coordinate movevector;
	private boolean isalive;
	private GameWorld world;
	
	public BaseGameObject(){
		this.coordinates=new Coordinate(0,0);
		this.direction=new Coordinate(1,0);
		this.maxspeed=0;
		this.movevector=new Coordinate(0,0);
		setIsAlive(true);
	}
	public BaseGameObject(double maxspeed){
		super();
		this.coordinates=new Coordinate(0,0);
		this.direction=new Coordinate(1,0);
		this.maxspeed=maxspeed;
		this.movevector=new Coordinate(0,0);
		setIsAlive(true);
	}
	public BaseGameObject(Coordinate coordinates, Coordinate direction, double maxspeed) {
		super();
		this.coordinates=coordinates;
		setDirection(direction);
		this.maxspeed=maxspeed;
		this.movevector=new Coordinate(0,0);
		setIsAlive(true);
	}	
	
	public void update(double deltatime){
		this.coordinates.add(Coordinate.multiple(this.movevector,deltatime));
		
	}
	
	public Coordinate getCoordinates(){
		return new Coordinate(this.coordinates);
	}
	
	public void setCoordinates(Coordinate coordinate){
		this.coordinates=new Coordinate(coordinate);
	}
	public void setCoordinates(double x,double y){
		this.coordinates=new Coordinate(x, y);
	}
	
	public double getMaxSpeed(){
		return this.maxspeed;
	}
	
	public void  setMaxSpeed(double maxspeed){
		this.maxspeed=maxspeed;
		levelingSpeedMaxSpeed();
	}
	
	public double getSpeed(){
		return Math.sqrt(movevector.x*movevector.x+movevector.y*movevector.y);
	}
	
	public Coordinate getMoveVector(){
		return new Coordinate(movevector);
	}
	
	public void setMoveVector(double x, double y){
		this.movevector.x=x;
		this.movevector.y=y;
	}
	public void setMoveVector(Coordinate vec){
		setMoveVector(vec.x,vec.y);
		levelingSpeedMaxSpeed();
	}
	
	public Coordinate getDirection(){
		return new Coordinate(this.direction);
	}

	public void setDirection(Coordinate coord){
		setDirection(coord.x,coord.y);
	}
	
	public void setDirection(double x,double y){
		
			this.direction=new Coordinate(x,y);
			this.direction.normalize();
			return;

		
	}
	public double getDirectionInDegree(){
		return Math.atan2(direction.x,direction.y)*180/Math.PI;
	}
	public boolean isAlive(){
		return this.isalive;
	}
	
	public void setIsAlive(boolean isalive){
		this.isalive=isalive;
	}
	
	private void levelingSpeedMaxSpeed(){
		double sp=getSpeed();
		if(sp>maxspeed&&Math.abs(sp)>0.0001){
			double scale=maxspeed/sp;
			movevector.x*=scale;
			movevector.y*=scale;
		}
	}
	@Override
	public void setWorld(GameWorld world) {
		this.world=world;

	}

	@Override
	public GameWorld getWorld() {
		return world;
	}

}
