package application;

import java.io.IOException;
import java.util.List;

import friendlyspacefight.game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewGameController {

@FXML
private Button play;

@FXML
private Button back;
@FXML
private Label player1;
@FXML
private Label player2;

private Scene parent;

Stage stage;
List<Player> players;

@FXML
void playActionHandler(ActionEvent event){
	
	try {
	if(player1.getTextFill()!=Color.BLUE) return;
		FXMLLoader loader =new FXMLLoader(getClass().getResource("Game.fxml"));
	BorderPane root;
	root = loader.load();	
	Scene scene = new Scene(root,1000,800);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	Stage menuStage = (Stage) play.getScene().getWindow();
	menuStage.setScene(scene);
	menuStage.show();
    scene.getRoot().requestFocus();

	WorldControl gamecontroller =loader.getController();
	gamecontroller.startGame(player1.getText(), player2.getText());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
@FXML
void choosePlayerActionHandler(ActionEvent event) throws IOException{
	 FXMLLoader loader =new FXMLLoader(getClass().getResource("PlayerChooser.fxml"));
	 AnchorPane root = loader.load();	
		ChooserController choosercontroller =loader.getController();

	 final Stage playerChooser = new Stage();
	  final Stage stage=(Stage) play.getScene().getWindow();
	  playerChooser.initModality(Modality.APPLICATION_MODAL);
	  playerChooser.initOwner(stage);
      Scene dialogScene = new Scene(root,310, 100);
      playerChooser.setScene(dialogScene);
      choosercontroller.setMainController(this);

      playerChooser.show();
    
}

@FXML
void backActionHandler(ActionEvent event) throws IOException{
	FXMLLoader loader =new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	AnchorPane root = loader.load();
	Scene scene = new Scene(root,1000,800); 
	
	final Stage stage=(Stage) play.getScene().getWindow();
	  stage.setScene(scene);
	  stage.show();
}

public void setParentScene(Scene scene){
	this.parent=scene;
	
}
public void setPlayers(String p1,String p2){
	 player1.setText(p1);
	 player1.setTextFill(Color.BLUE);
	 player2.setText(p2);
	 player2.setTextFill(Color.BLUE);
}
@FXML
private void initialize(){
	
}
}
