package application;

import java.util.ArrayList;

import dao.DAOHandler;
import dao.PlayerGameStatistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ChooserController {
	
	private DAOHandler daoHandler;
	private ArrayList<PlayerGameStatistics> players;
	@FXML
	private Button ok;
	@FXML
	private Button cancel;
	
	@FXML
	private ComboBox<String> player1combo;

	@FXML
	private ComboBox<String> player2combo;
	
	
	private NewGameController controller;
	@FXML
	private void onCancel(ActionEvent event){
		Stage stage=(Stage)ok.getScene().getWindow();
		stage.close();

	}
	
	@FXML
	private void onOk(ActionEvent event){
		
		String player1name=player1combo.getValue();
		String player2name=player2combo.getValue();
		PlayerGameStatistics player1;
		PlayerGameStatistics player2;
		
		if(player1name==null||player2name==null||player1name.trim().isEmpty()||player1name.trim().isEmpty()){
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Noname!");
			alert.setContentText("Anonymus not allowed!");
			alert.show();
			return;
		}
		if(player1name.trim().toLowerCase().equals(player2name.trim().toLowerCase())){
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Name problem!");
			alert.setContentText("You can't play vs you!");
			alert.show();
			return;
		}
		
		if(!daoHandler.containsPlayer(player2name)){
			player2=new PlayerGameStatistics();
			player2.setName(player2name);
			players.add(player2);
		}
		if(!daoHandler.containsPlayer(player1name)){
			player1=new PlayerGameStatistics();
			player1.setName(player1name);
			players.add(player1);
		}
	
		daoHandler.savePlayers();
		controller.setPlayers(player1name, player2name);
		Stage stage=(Stage)ok.getScene().getWindow();
		stage.close();
	}
	public String getPlayer1(){
		return player1combo.getValue();
	}
	public String getPlayer2(){
		return player2combo.getValue();
	}
	
	public void setMainController(NewGameController controller){
		this.controller=controller;
	}
	
	@FXML
	private void initialize(){
		daoHandler=DAOHandler.getInstance();
		daoHandler.loadPlayers();
		players=daoHandler.getPlayers();
		for(PlayerGameStatistics p:players){
			player1combo.getItems().add(p.getName());
			player2combo.getItems().add(p.getName());
		}

		player1combo.setEditable(true);
		player2combo.setEditable(true);
	}
}
