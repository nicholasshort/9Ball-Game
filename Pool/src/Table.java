
public class Table {

	private float coFriction;
	private float [] xRange = {210,609,651,1048, 178, 1080};
	private float [] yRange = {212,617, 181, 648};
	
	public Table(float coFriction) {
		this.coFriction = coFriction;
	}
	
	/**
	 * Method gets the coefficient of kinetic friction of table
	 * @return coefficient
	 */
	public float getCoFriction() {
		return coFriction;
	}
	
	/**
	 * Checks to see if there has been a collision between the table and a ball along the x axis
	 * @param b The ball
	 */
	public void collisionX(Ball b) {
		if((b.getX()+b.getRadius()>=xRange[5]||b.getX()-b.getRadius()<=xRange[4])&&(b.getY()-b.getRadius()>=yRange[0]&&b.getY()+b.getRadius()<=yRange[1])) {
			b.hardSetVeloX(b.getVeloX()*-1);
			if(b.getX()+b.getRadius()>=xRange[5])
				b.setX(xRange[5]-b.getRadius()-0.001f);
			else
				b.setX(xRange[4]+b.getRadius()+0.001f);
		}
	}
	
	/**
	 * Checks to see if there has been a collision between the table and a ball along the y axis
	 * @param b The ball
	 */
	public void collisionY(Ball b) {
		if((b.getY()+b.getRadius()>=yRange[3]||b.getY()-b.getRadius()<=yRange[2])&&((b.getX()+b.getRadius()<=xRange[3]&&b.getX()-b.getRadius()>=xRange[2])||(b.getX()+b.getRadius()<=xRange[1]&&b.getX()-b.getRadius()>=xRange[0]))) { 
			b.hardSetVeloY(b.getVeloY()*-1);
			if(b.getY()+b.getRadius()>=yRange[3])
				b.setY(yRange[3]-b.getRadius()-0.001f);
			else
				b.setY(yRange[2]+b.getRadius()+0.001f);
		}
	}
	
	/**
	 * Checks to see whether a ball has entered a pocket
	 * @param b Ball instance
	 * @param p Pool instance
	 */
	public void checkPockets(Ball b, Pool p) {
		float d = (float)Math.sqrt(Math.pow(b.getX()-181,2)+Math.pow(b.getY()-185,2));
		float radPocket = 211-181;//topLeft 
		if(radPocket > d+b.getRadius()) {
			b.pocket(p);
		}
		d = (float)Math.sqrt(Math.pow(b.getX()-629,2)+Math.pow(b.getY()-171,2));
		radPocket = (653-605)/2.0f;//topMiddle 
		if(radPocket > d+b.getRadius()) {
			b.pocket(p);
		}
		d = (float)Math.sqrt(Math.pow(b.getX()-1077,2)+Math.pow(b.getY()-185,2));
		radPocket = 211-181;//topRight
		if(radPocket > d+b.getRadius()) {
			b.pocket(p);
		}
		d = (float)Math.sqrt(Math.pow(b.getX()-181,2)+Math.pow(b.getY()-640,2));
		radPocket = 211-181;//botLeft
		if(radPocket > d+b.getRadius()) {
			b.pocket(p);
		}
		d = (float)Math.sqrt(Math.pow(b.getX()-629,2)+Math.pow(b.getY()-655,2));
		radPocket = (653-605)/2.0f;//botMiddle 
		if(radPocket > d+b.getRadius()) {
			b.pocket(p);
		}
		d = (float)Math.sqrt(Math.pow(b.getX()-1077,2)+Math.pow(b.getY()-643,2));
		radPocket = 211-181;//botRight
		if(radPocket > d+b.getRadius()) {
			b.pocket(p);
		}
		if(!(b.getX()-b.getRadius()*1.<=xRange[5]&&b.getX()+b.getRadius()>=xRange[4]&&b.getY()-b.getRadius()<=yRange[3]&&b.getY()+b.getRadius()>=yRange[2])) {
			b.pocket(p);
		}
	}
}
