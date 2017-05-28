package friendlyspacefight.game;

public class Refill extends ShipEffect {

	public Refill(double expiretime) {
		super(expiretime);
	}

	@Override
	public void applyEffect() {
		getSpacecraft().setHealth(getSpacecraft().getHealth());
		setIsActive(false);
	}
	@Override
	public void update(double deltatime) {
	}
	@Override
	public void restore() {
	}

	@Override
	public EffectType getEffectType() {
		return EffectType.REFILL;
	}

}
