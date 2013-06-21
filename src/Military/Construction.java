package Military;


@SuppressWarnings("serial")
public abstract class Construction extends Military {
	public int onCollision (){
		int thisCenterX = x + imageWidth / 2;
		int thisCenterY = y + imageHeight / 2;
		int enemyCenterX;
		int enemyCenterY;
		for (int i = 0 ; i < military.size() ; i++){			
				if (military.get(i) != null){
					enemyCenterX = military.get(i).x + military.get(i).imageWidth/2;
					enemyCenterY = military.get(i).y + military.get(i).imageHeight/2;
					if ( Math.abs( thisCenterX - enemyCenterX ) <= (imageWidth + military.get(i).imageWidth + 200) / 2 &&
							Math.abs( thisCenterY - enemyCenterY  )  <= (imageHeight + military.get(i).imageHeight + 200) / 2 &&
							this.team != military.get(i).team && military.get(i).hp >= 0){
						return i;
					}
				}
		}
		return -1;
	}

}
