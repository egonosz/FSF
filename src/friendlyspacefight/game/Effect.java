package friendlyspacefight.game;

public interface Effect {
	static public Effect getInstanceOf(EffectType type) {
		switch(type){
		case RAPIDFIRE:
			return new Rapidfire(20.0d);
		case ULTRARAPIDFIRE:
			return new UltraRapidFire(20.0d);
		case DOUBLEDMG:
			return new DoubleDmg(20.0d);
		case REFILL:
			return new Refill(0);
		case JOKES :
			return new Joy(0);
		}
		return null;
	}
	
	public void applyEffect();
	public void restore();
	public void refill();
	public boolean isActive();
	public void setIsActive(boolean isactive);
	public void update(double deltatime);
	
	public EffectType getEffectType();
}
