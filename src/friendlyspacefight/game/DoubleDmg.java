package friendlyspacefight.game;

import java.util.ArrayList;
import java.util.List;

public class DoubleDmg extends ShipEffect {

	private List<Double> originaldamage;
	
	public DoubleDmg(double expiretime) {
		super(expiretime);
	}

	@Override
	public void applyEffect() {
		List<Weapon>weapons=getSpacecraft().getWeapons();
		originaldamage=new ArrayList<>();
		for(Weapon w :weapons){
			originaldamage.add(w.getDmg());	
			w.setDmg(w.getDmg()*1.5);
		}
	}

	@Override
	public void restore() {
		List<Weapon>weapons=getSpacecraft().getWeapons();
		for(int i=0;i<weapons.size();i++){
			weapons.get(i).setDmg(originaldamage.get(i));
		}

	}

	@Override
	public EffectType getEffectType() {
		return EffectType.DOUBLEDMG;
	}

}
