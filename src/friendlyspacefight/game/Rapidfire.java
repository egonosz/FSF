package friendlyspacefight.game;

import java.util.ArrayList;
import java.util.List;

public class Rapidfire extends ShipEffect {
	private double effectpower;
	List<Double> originalfirerate;
	
	public Rapidfire(double expiretime) {
		super(expiretime);
		effectpower=1.2;
	}
	
	@Override
	public void applyEffect() {
		List<Weapon>weapons=getSpacecraft().getWeapons();
		originalfirerate=new ArrayList<>();
		for(Weapon w :weapons){
			originalfirerate.add(w.getFirerate());	
			w.setFirerate(w.getFirerate()*effectpower);
		}
	}

	@Override
	public void restore() {
		List<Weapon>weapons=getSpacecraft().getWeapons();
		for(int i=0;i<weapons.size();i++){
			weapons.get(i).setFirerate(originalfirerate.get(i));
		}

	}

	@Override
	public EffectType getEffectType() {
		return EffectType.RAPIDFIRE;
	}

	public double getEffectpower() {
		return effectpower;
	}

	public void setEffectpower(double effectpower) {
		this.effectpower = effectpower;
	}


}
