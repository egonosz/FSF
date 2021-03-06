package application;


import java.util.EnumSet;

import friendlyspacefight.game.Game;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class WorldControl {
@FXML
private Canvas worldmap;

private Game game;
private MainLoop mainloop;
private EnumSet<KeyCode> keydown;
private NewGameController controller;
public WorldControl() {
	super();
}
@FXML
private void keyPressed(KeyEvent evt) {
    	keydown.add(evt.getCode());
}

@FXML
private void keyReleased(KeyEvent evt){
		keydown.remove(evt.getCode());

}
public void startGame(String player1,String player2){
	mainloop.setPlayerNames(player1, player2);
	mainloop.start();
	
}
@FXML
private void initialize(){
	keydown=EnumSet.noneOf(KeyCode.class);
	game=new Game(1000, 800, "Johny", "Sanyi");
	worldmap.getGraphicsContext2D();
	game.start();
	mainloop=new MainLoop(game,worldmap);
	mainloop.addKeyDownSet(keydown);
}
public void asd() {
	// TODO Auto-generated method stub
	
}

}
