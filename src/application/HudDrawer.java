package application;
import java.util.List;

import friendlyspacefight.game.Coordinate;
import friendlyspacefight.game.Effect;
import friendlyspacefight.game.EffectType;
import friendlyspacefight.game.Player;
import friendlyspacefight.game.SpaceCraft;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HudDrawer {
	private GraphicsContext graphicalcontext;
	private Canvas canvas;
	private Player player1, player2;
	public HudDrawer(Canvas canvas,Player player1,Player player2){
		this.canvas=canvas;
		this.player1=player1;
		this.player2=player2;
		this.graphicalcontext=canvas.getGraphicsContext2D();
	}
	public void drawInfobar(Player player,double x,double y,Color color){
		SpaceCraft sc=player.getShip();
		double shiphealthScale=sc.getHealth()/sc.getMaxhealth();
		graphicalcontext.setFill(Color.DARKGREY);
		graphicalcontext.fillRoundRect(x, y, 125.0d, 25.0d, 10.0d, 10.0d);
		graphicalcontext.setFill(color);
		graphicalcontext.fillRoundRect(x, y, 125.0d*shiphealthScale, 25.0d, 10.0d, 10.0d);
		List<Effect> effects=sc.getEffects();
		for(int i=0;i<effects.size();i++){
			graphicalcontext.setFill(color);
			EffectType type=effects.get(i).getEffectType();
			graphicalcontext.fillRoundRect(x+30*i, y-30, 25, 25, 10, 10);
			graphicalcontext.setFill(Color.BLACK);
			switch(type){
			case DOUBLEDMG:
				graphicalcontext.fillText("DD", x+30*i,y-10);
				break;
			case JOKES:
				graphicalcontext.fillText("JK", x+30*i,y-30);
				break;
			case RAPIDFIRE:
				graphicalcontext.fillText("RF", x+30*i,y-30);
				break;
			case REFILL:
				graphicalcontext.fillText("RE", x+30*i,y-30);
				break;
			case ULTRARAPIDFIRE:
				graphicalcontext.fillText("URF", x+30*i,y-30);
				break;
			default:
				break;
			
			}
		}
	}
	public void draw(double deltatime){
		drawInfobar(player1,10,canvas.getHeight()-65,Color.RED);
		drawInfobar(player2,canvas.getWidth()-135,canvas.getHeight()-65,Color.BLUE);
		
	}
}
