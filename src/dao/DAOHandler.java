package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DAOHandler {
    private JAXBContext context;
    private static DAOHandler instance;
    private final static String xmlfilename="Playerstatistics.xml";
    private ArrayList<PlayerGameStatistics> players;
    
    public static DAOHandler getInstance(){
    	if(instance==null){
    		instance=new DAOHandler();
    	}
    	
    	return instance; 
    }
    private DAOHandler(){
    	try {
       			context = JAXBContext.newInstance(Players.class);     
	        } catch (JAXBException e) {
				e.printStackTrace();
			}

    }
    public void loadPlayers(){
    	 try {
    		 Unmarshaller um;
        	 um = context.createUnmarshaller();
        	 File f=new File(xmlfilename);
        	 if(!f.exists()){
        		 players=new ArrayList<>();
        		 return;
        	 }
        	 FileReader fr=new FileReader(f);
        	
        	 Players players = (Players) um.unmarshal(fr);
        	 this.players=players.getPlayers();	
    	 } catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		} 

    }
    public void savePlayers(){
    	try {
    		Marshaller m;
    		m = context.createMarshaller();
			Players players=new Players();
			players.setPlayers(this.players);
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File f=new File(xmlfilename);
    		m.marshal(players, f);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    }
	public boolean containsPlayer(String name){
		for(PlayerGameStatistics e:players){
			if(e.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) return true;
		}
		return false;
	}
	public PlayerGameStatistics getPlayerByName(String name){
		for(PlayerGameStatistics e:players){
			if(e.getName().trim().toLowerCase().equals(name.trim().toLowerCase())) return e;
		}
		return null;
	}
    public ArrayList<PlayerGameStatistics> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<PlayerGameStatistics> players) {
		this.players = players;
	}
    
}
