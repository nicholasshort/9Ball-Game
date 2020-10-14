import java.io.Serializable;

public class Profile implements Serializable{
	
	private String name;
	private int gamesPlayed = 0;
	
	//Cue Ball Preferences
	private int cueMass = 50;
	private int cueRadius = 10;
	
	public Profile(String name) {
		this.name = name;
	}
	
	public Profile() {
		
	}
	
	/**
	 * Method adds to games played
	 * @param n Nothing
	 */
	public void winGame(int n) {
		gamesPlayed++;
	}
	
	/**
	 * Method adds to games played
	 */
	public void loseGame() {
		gamesPlayed++;
	}
	
	/**
	 * Changes the preferred mass of the cue ball
	 * @param n Preferred mass
	 */
	public void setCueMass(int n) {
		cueMass = n;
	}
	
	/**
	 * Changes the preferred radius of the cue ball
	 * @param n Preferred radius
	 */
	public void setCueRadius(int n) {
		cueRadius = n;
	}
	
	/**
	 * Gets the number of games played
	 * @return Games played
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	/**
	 * Gets the name of the user
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the preferred mass of the cue ball
	 * @return Preferred mass
	 */
	public int getCueMass() {
		return cueMass;
	}
	
	/**
	 * Gets the preferred radius of the cue ball
	 * @return Preferred radius
	 */
	public int getCueRadius() {
		return cueRadius;
	}
	
}
