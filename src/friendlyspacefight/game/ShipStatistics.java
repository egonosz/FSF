package friendlyspacefight.game;

public class ShipStatistics {
	private long projectilesfired, rocketsfired, shothits, dmgtaken,dmgdone,poweruppickups;

	public ShipStatistics() {
	}

	public long getProjectilesfired() {
		return projectilesfired;
	}

	public ShipStatistics addProjectilesfired(long projectilesfired) {
		this.projectilesfired += projectilesfired;
		return this;
	}

	public long getRocketsfired() {
		return rocketsfired;
	}

	public ShipStatistics addRocketsfired(long rocketsfired) {
		this.rocketsfired += rocketsfired;
		return this;
	}

	public long getDmgtaken() {
		return dmgtaken;
	}

	public ShipStatistics addDmgtaken(long dmgtaken) {
		this.dmgtaken += dmgtaken;
		return this;
	}

	public long getDmgdone() {
		return dmgdone;
	}

	public ShipStatistics addDmgdone(long dmgdone) {
		this.dmgdone+= dmgdone;
		return this;
	}

	public long getPoweruppickups() {
		return poweruppickups;
	}

	public ShipStatistics addPoweruppickups(long poweruppickups) {
		this.poweruppickups += poweruppickups;
		return this;
	}

	public long getShothits() {
		return shothits;
	}

	public ShipStatistics addShothits(long shothits) {
		this.shothits += shothits;
		return this;
	}
	
	

}
