package dao;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="players")
@XmlAccessorType(XmlAccessType.NONE)
public class Players {
	@XmlElement(name = "player")
    private ArrayList<PlayerGameStatistics> playersList;

	public ArrayList<PlayerGameStatistics> getPlayers() {
		return playersList;
	}

	public void setPlayers(ArrayList<PlayerGameStatistics> players) {
		this.playersList = players;
	}
 
}
