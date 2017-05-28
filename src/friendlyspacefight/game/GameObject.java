package friendlyspacefight.game;

public interface GameObject {

	public void update(double deltatime);
	
	public Coordinate getCoordinates();
	public void setCoordinates(Coordinate coordinate);
	public void setCoordinates(double x,double y);
	

	public Coordinate getDirection();
	public void setDirection(Coordinate direction);
	
	public boolean isAlive();
	public void setIsAlive(boolean isalive);
	
	public void setWorld(GameWorld world);
	public GameWorld getWorld();
}
