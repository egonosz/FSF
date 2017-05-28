package application;


import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	private Button newgame;
	@FXML
	private Button statistics;
	@FXML
	private Button exit;
	
	@FXML
	void onNewGameClick(ActionEvent event){
		 	try {
		 	FXMLLoader loader =new FXMLLoader(getClass().getResource("NewGameMenu.fxml"));
			AnchorPane root = loader.load();
			
			Scene scene = new Scene(root,1000,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage menuStage = (Stage) newgame.getScene().getWindow();
			menuStage.setScene(scene);
			menuStage.show();
	        scene.getRoot().requestFocus();

			NewGameController menucontroller =loader.getController();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@FXML
	void onExitAction(ActionEvent event){
		Platform.exit();
	}
	@FXML
	private void initialize(){
		
	}
	
}
