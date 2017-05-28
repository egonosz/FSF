package friendlyspacefight.game;

public abstract class ShipEffect implements Effect {
	private SpaceCraft spacecraft;
	private double timefromstart,expirationtime;
	boolean isactive;

	
	public ShipEffect(double expiretime) {
		expirationtime=expiretime;
		isactive=true;
	}
	@Override
	public void update(double deltatime) {
		if(timefromstart<expirationtime){
			timefromstart+=deltatime;
		} else {
			restore();
			isactive=false;
		}

	}

	
	public SpaceCraft getSpacecraft() {
		return spacecraft;
	}

	
	public void setSpacecraft(SpaceCraft spacecraft) {
		this.spacecraft=spacecraft;

	}




	@Override
	public boolean isActive() {
		return isactive;
	}


	@Override
	public void refill() {
		expirationtime=0;
		
	}


	@Override
	public void setIsActive(boolean isactive) {
		this.isactive=isactive;
		
	}


}
