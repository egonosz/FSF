package friendlyspacefight.game;

public class Game {
	public enum GameState{
		PLAYER1WINS, PLAYER2WON, DRAW, RUNNING, PAUSED
	}
	
	private GameWorld gameworld;
	private Player player1,player2;
	private double maxspeed;
	private GameState gamestate;
	
	public Game(int worldsizex,int worldsizey,String playername1,String playername2) {
		maxspeed =400.0;
		gameworld=new GameWorld(worldsizex, worldsizey,this);
		gamestate=GameState.PAUSED;
		SpaceCraft sc1=new SpaceCraft(new Coordinate(100,worldsizey/2.0), new Coordinate(0,1), maxspeed);
		sc1.setMaxhealth(100);
		sc1.refillHealt();
		sc1.setCollisionHitCicrcle(25);
		SpaceCraft sc2=new SpaceCraft(new Coordinate(worldsizex-100,worldsizey/2.0), new Coordinate(0,-1), maxspeed);
		sc2.setMaxhealth(100);
		sc2.refillHealt();
		sc2.setCollisionHitCicrcle(25);
		gameworld.addShip(sc1);
		gameworld.addShip(sc2);
		player1=new Player(playername1,sc1 );
		player2=new Player(playername2,sc2 );
	}	
	
	public void updateWorld(double deltatime){
		if(gamestate.equals(GameState.RUNNING)){
			gameworld.update(deltatime);
			updateGameState();
		}
	}
	
	public void start(){
		if(gamestate.equals(GameState.PAUSED)){
			gamestate=GameState.RUNNING;
		}
	}
	
	public void stop(){
		if(gamestate.equals(GameState.RUNNING)){
			gamestate=GameState.PAUSED;
		}
	}
	
	private void updateGameState(){
		long aliveships=gameworld.countAliveShips();
		
		if(aliveships==0) {
			gamestate=GameState.DRAW;
		}else if(aliveships>1){
			return;
		}
		if(player1.getShip().isAlive()){
			gamestate=GameState.PLAYER1WINS;
		}else if(player2.getShip().isAlive()){
			gamestate=GameState.PLAYER2WON;
		}
	}
	
	public GameState getGameState(){
		return gamestate;
	}
	public GameWorld getGameWorld(){
		return gameworld;
	}
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	
	
}
