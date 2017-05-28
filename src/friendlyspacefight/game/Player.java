package friendlyspacefight.game;

public class Player {
	private String name;
	private SpaceCraft ship;

Player(String name,SpaceCraft ship){
	this.name=name;
	this.ship=ship;
	}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public SpaceCraft getShip() {
	return ship;
}

public void setShip(SpaceCraft ship) {
	this.ship = ship;
}


}
