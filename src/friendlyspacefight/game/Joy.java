package friendlyspacefight.game;

public class Joy extends ShipEffect {

	public Joy(double expiretime) {
		super(expiretime);
		
	}
	@Override
	public void applyEffect() {
		Game game=getSpacecraft().getWorld().getGame();
		SpaceCraft sc1=game.getPlayer1().getShip();
		SpaceCraft sc2=game.getPlayer2().getShip();
		game.getPlayer1().setShip(sc2);
		game.getPlayer2().setShip(sc1);
		setIsActive(false);
	}

	@Override
	public void restore() {

	}

	@Override
	public EffectType getEffectType() {
		
		return EffectType.JOKES;
	}

}
