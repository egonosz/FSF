package friendlyspacefight.game;

public class UltraRapidFire extends Rapidfire {

	public UltraRapidFire(double expiretime) {
		super(expiretime);
		setEffectpower(0.5);
	}

	@Override
	public EffectType getEffectType() {
		return EffectType.ULTRARAPIDFIRE;
	}
	
}
