import java.io.Serializable;

public class HardcorePlayer extends Profile implements Serializable{
	
	private int bestScore = 0;
	private int sucGames = 0;//Games Won
	private int unSucGames = 0;//Games Lost
	
	public HardcorePlayer(String name) {
		super(name);
	}
	
	/**
	 * Gets the number of games lost
	 * @return Games lost
	 */
	public int getUnSucGames() {
		return unSucGames;
	}
	
	/**
	 * Gets the number of games won
	 * @return Games won
	 */
	public int getSucGames() {
		return sucGames;
	}
	
	/**
	 * Gets the best score
	 * @return Best score
	 */
	public int getBestScore() {
		return bestScore;
	}
	
	/**
	 * Checks to see if best score has been broken
	 * Adds to wins
	 */
	public void winGame(int n) {
		if(n < bestScore || bestScore == 0) {
			bestScore = n;
		}
		sucGames++;
		
	}
	
	/**
	 * Adds to losses
	 */
	public void loseGame() {
		unSucGames++;
	}
	
}
