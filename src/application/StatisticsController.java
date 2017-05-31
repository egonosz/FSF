package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.TabableView;

import com.sun.org.glassfish.external.statistics.Stats;

import dao.DAOHandler;
import dao.PlayerGameStatistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StatisticsController {
	
	@FXML
	private Button back;
	
	@FXML
	private ChoiceBox<String> playerchoice;
	
	@FXML
	private Label wins,losts,dmgdone,dmgtaken,projfire,rocketfire,powerups,hits;
	@FXML
	private void onBackAction(ActionEvent event) throws IOException{
		 FXMLLoader loader =new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root,1000,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage=(Stage)back.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
	        scene.getRoot().requestFocus();
	}
	
	
	@FXML
	private void initialize(){
		final DAOHandler daohandler=DAOHandler.getInstance();
		daohandler.loadPlayers();
		ArrayList<PlayerGameStatistics>players=daohandler.getPlayers();               
		daohandler.loadPlayers();
		players=daohandler.getPlayers();
		for(PlayerGameStatistics p:players){
			 playerchoice.getItems().add(p.getName());
			
		}
		playerchoice.setOnAction(value->{
			String playername=playerchoice.getValue();
			PlayerGameStatistics stats=daohandler.getPlayerByName(playername);
			wins.setText(Integer.toString(stats.getWonGames()));
			losts.setText(Integer.toString(stats.getLostGames()));
			dmgdone.setText(Double.toString(stats.getDmgdone()));
			dmgtaken.setText(Double.toString(stats.getDmgtaken()));
			projfire.setText(Long.toString(stats.getProjectilesfired()));
			rocketfire.setText(Long.toString(stats.getRocketsfired()));
			powerups.setText(Long.toString(stats.getPoweruppickups()));
			hits.setText(Long.toString(stats.getShothits()));
			
		});
		
		
	}
}
