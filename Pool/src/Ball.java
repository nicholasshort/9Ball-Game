import processing.core.PApplet;

public class Ball {
	
	PApplet parent;
	
	private float x, y;
	private float veloX, veloY;
	private float radius;
	private float mass;
	
	private String type; //
	
	public Ball(float x, float y, float radius, float mass, float veloX, float veloY, String type, PApplet parent) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.radius = radius;
		this.veloX = veloX;
		this.veloY = veloY;
		this.parent = parent;
		this.type = type;
	}
	
	public Ball() {
		
	}
	
	/**
	 * Every 60 seconds, this method is called to change the coordinates and velocities (friction)
	 * Equation used, a = ug
	 * @param t The table object
	 */
	public void tick(Table t) {
		x+=veloX;
		y+=veloY;
		float veloTot = (float)Math.sqrt(veloX*veloX+veloY*veloY);//total velocity
		veloTot = veloTot-t.getCoFriction()>0 ? veloTot-t.getCoFriction() : 0;//a = ug (g in this case is 1, maybe try to add more gravity in the future)
		float tayta = veloX != 0 ? Math.abs((float)Math.atan(veloY/veloX)) : (float)Math.PI/2;//angle of movement (from horizontal)
		veloX = veloX > 0 ? (float)(veloTot*Math.cos(tayta)) : (float)(veloTot*Math.cos(tayta))*-1;
		veloY = veloY > 0 ? (float)(veloTot*Math.sin(tayta)) : (float)(veloTot*Math.sin(tayta))*-1;
		
	}
	
	/**
	 * Sets the x coordinate of ball
	 * @param n x coordinate
	 */
	public void setX(float n) {
		x = n;
	}
	/**
	 * Sets the y coordinate of ball
	 * @param n y coordinate
	 */
	public void setY(float n) {
		y = n;
	}
	/**
	 * Changes x velocity
	 * @param n The change
	 */
	public void setVeloX(float n) {
		veloX+=n;
	}
	
	/**
	 * Changes y velocity
	 * @param n The change
	 */
	public void setVeloY(float n) {
		veloY+=n;
	}
	
	/**
	 * Sets x velocity
	 * @param n New velocity
	 */
	public void hardSetVeloX(float n) {
		veloX = n;
	}
	
	/**
	 * Sets the y velocity
	 * @param n New velocity
	 */
	public void hardSetVeloY(float n) {
		veloY = n;
	}
	
	/**
	 * Method simulates what happens when a ball is pocketed
	 * @param p Pool instance
	 */
	public void pocket(Pool p) {
		if(type.equals("Cue")) {
			if(!p.getState().equals("move ball"))
				p.setNumberOfHits(3);
			x = 412;
			y = 415;
			veloX = 0;
			veloY = 0;
			return;
		}
		if(p.getFirstBallHit()) {
			System.out.println("Game over, you lost");
			p.setGameState("Menu");
			Pool.profile.loseGame();//Polymorphism
			p.triggerNewGame();
			return;
		}
		p.setBall(Integer.parseInt(type)+1);
		p.triggerRemoval(this);
	}
	
	/**
	 * Sets the mass of the ball
	 * @param n New mass
	 */
	public void setMass(float n) {
		mass = n;
	}
	
	/**
	 * Sets the radius of the ball
	 * @param n New radius
	 */
	public void setRadius(float n) {
		radius = n;
	}
	
	/**
	 * Gets the x position of the ball
	 * @return x position
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Gets the y position of the ball
	 * @return y position
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Gets the x velocity of the ball
	 * @return x velocity
	 */
	public float getVeloX() {
		return veloX;
	}
	
	/**
	 * Gets the y velocity
	 * @return y velocity
	 */
	public float getVeloY() {
		return veloY;
	}
	
	/**
	 * Gets the radius of the ball
	 * @return Ball radius
	 */
	public float getRadius() {
		return radius;
	}
	
	/**
	 * Gets the mass of the ball
	 * @return Ball mass
	 */
	public float getMass() {
		return mass;
	}
	
	/**
	 * The angle at which the ball is moving (Related Acute)
	 * @return The angle
	 */
	public float getTayta() {
		return (float)Math.atan2((double)veloY,(double)veloX);
	}
	
	/**
	 * The velocity magnitude of the ball
	 * @return total velocity
	 */
	public float getMag() {
		return (float)Math.sqrt(veloX*veloX+veloY*veloY);
	}
	
	/**
	 * Gets the kind of ball
	 * @return Type of ball
	 */
	public String getType() {
		return type;
	}
	
	
}
