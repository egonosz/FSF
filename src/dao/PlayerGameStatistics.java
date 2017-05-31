package dao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "player")
public class PlayerGameStatistics {
	private String name;
	private int wonGames;
	private int lostGames;
	private long projectilesfired, rocketsfired, 
	shothits, dmgtaken,dmgdone,poweruppickups;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWonGames() {
		return wonGames;
	}
	public void setWonGames(int wonGames) {
		this.wonGames = wonGames;
	}
	
	public int getLostGames() {
		return lostGames;
	}
	public void setLostGames(int lostGames) {
		this.lostGames = lostGames;
	}
	
	public long getProjectilesfired() {
		return projectilesfired;
	}
	public void setProjectilesfired(long projectilesfired) {
		this.projectilesfired = projectilesfired;
	}
	public long getRocketsfired() {
		return rocketsfired;
	}
	public void setRocketsfired(long rocketsfired) {
		this.rocketsfired = rocketsfired;
	}
	public long getShothits() {
		return shothits;
	}
	public void setShothits(long shothits) {
		this.shothits = shothits;
	}
	public long getDmgtaken() {
		return dmgtaken;
	}
	public void setDmgtaken(long dmgtaken) {
		this.dmgtaken = dmgtaken;
	}
	public long getDmgdone() {
		return dmgdone;
	}
	public void setDmgdone(long dmgdone) {
		this.dmgdone = dmgdone;
	}
	public long getPoweruppickups() {
		return poweruppickups;
	}
	public void setPoweruppickups(long poweruppickups) {
		this.poweruppickups = poweruppickups;
	}

}
