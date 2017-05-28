package friendlyspacefight.game;

import java.util.List;

import com.sun.media.jfxmedia.events.NewFrameEvent;

public class Rocket extends WeaponParticle {
	double rotatespeed;
	double accelerate;
	Rocket(){
	super();	
	}
	
	
	
	@Override
	public void initialize() {
		setDmg(1);
		rotatespeed=90*(Math.PI/180.0);
		setMoveVector(Coordinate.multiple(getDirection(),500));	
		accelerate=1000;
		setMaxSpeed(300);
	}



	@Override
	public void update(double deltatime) {
		super.update(deltatime);
		
		List<SpaceCraft> ships=getWorld().getSpaceCrafts();
		SpaceCraft nearestsc=null;
		double dist=0;
		for(SpaceCraft sc: ships){
			if(!sc.equals(getSpacecraft())){
				Coordinate sccoor=sc.getCoordinates();
				sccoor.normalize();
				double dist2=Coordinate.length(Coordinate.sub(sccoor, getDirection()));
				if(dist<dist2){
					dist=dist2;
					nearestsc=sc;
				}
			}
		}
		Coordinate mycoords=getDirection();
		Coordinate scoords=Coordinate.sub(nearestsc.getCoordinates(),getCoordinates()).normalize();
		double dot=mycoords.x*(-scoords.y)+mycoords.y*scoords.x;
		/*if(Math.abs(dot)<0.0001) {
			return;
		}*/
		if(dot<0){
			rotate(+rotatespeed,deltatime);
		} else if(dot>0){
			rotate(-rotatespeed,deltatime);
		}
		
		Coordinate oldmove=getMoveVector();
		oldmove=Coordinate.multiple(getDirection(),accelerate);
		setMoveVector(oldmove);
	}
	
	private void rotate(double rad,double deltatime){
		Coordinate newdir=new Coordinate(0,0);
		Coordinate olddir=getDirection();
		newdir.x=olddir.x*Math.cos(rad*deltatime)-olddir.y*Math.sin(rad*deltatime);
		newdir.y=olddir.y*Math.cos(rad*deltatime)+olddir.x*Math.sin(rad*deltatime);
		setDirection(newdir);
	}
	
}
