package Military;


@SuppressWarnings("serial")
public abstract class Troops extends Military {
	
	protected double speedX;
	protected double speedY;
	abstract protected void moveX();
	abstract protected void moveYNegative();
	abstract protected void moveYPositive();
	protected boolean onCastleCollision (int x){	
		int thisCenterX =x;
		int thisCenterY = y + imageHeight / 2;
		int castleCenterX = military.get(0).x + military.get(0).imageWidth / 2;
		int castleCenterY = military.get(0).y + military.get(0).imageHeight / 2;		
		if ( Math.abs( thisCenterX - castleCenterX ) <= (imageWidth + military.get(0).imageWidth) / 2 &&
				Math.abs( thisCenterY - castleCenterY ) <= (imageHeight + military.get(0).imageHeight) / 2 
				&& team != military.get(0).team){
		
			return true;
		}
		return false;
		
	}
}
