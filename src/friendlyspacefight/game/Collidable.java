package friendlyspacefight.game;

public interface Collidable {
	void onCollision(Collision collision);
	Collision isCollideWith(Collidable collidable);
	
	void setCollisionHitCicrcle(double radius);
	double getCollisionHitCircle();

		
	
}
