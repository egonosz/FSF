package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewGameController {
@FXML
Button play;
@FXML
Button back;

@FXML
void playActionHandler(ActionEvent event){
	
	try {
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
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


@FXML
private void initialize(){
	
}
}
