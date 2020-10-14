//By Nicholas Short
//Started December 1 2018
//Completed January 26 2019




/*
 Notes:
 
 Although it might not look like much, it took a lot of math
 There Are a few glitches with hitboxes and collisions. I've tried my best to remove as many as I could, but there are still many present
 Enjoy
 
 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

public class Pool extends PApplet{
	
	PImage poolTable;
	PImage poolCue;
	PImage poolCarpet;
	PImage poolMenu;
	PImage cueBall;
	PImage oneBall;
	PImage twoBall;
	PImage threeBall;
	PImage fourBall;
	PImage fiveBall;
	PImage sixBall;
	PImage sevenBall;
	PImage eightBall;
	PImage nineBall;
	
	PFont menuFont;
	
	public static Profile profile;
	
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private Table table;
	
	//Rules
	private int ballToHit = 1;
	private int numberOfHits = 0;
	private String state = "move ball";
	private String toBeChanged = "move ball";
	private String gameState = "New Profile";
	private boolean firstBall = true;
	private boolean incorrectBall = false;
	
	//For the Menu
	private String clickedOn = "";
	
	//For Custom Menu
	private int cueMassStickX = 802;
	private int cueRadiusStickX = 802;
	private int initX = -1;
	private boolean customClicked = false;
	
	//For New Profile
	private boolean hardcore = false;
	
	//Adjusting shot power
	private float dragSquareX = 800f, dragSquareY = 50f, power = 0;
	
	//For Clicking of the mouse
	private boolean clicked = false, shooting = false, drag = false, initialClick = false;
	
	//Drawing the pool cue
	private float lineX = 0, lineY = 0, finalX = 0, finalY = 0, initialX = 0; 
	
	private boolean newGame = false;
	
	private Ball ballRemove = new Ball(); 
	
	
	
	public static void main(String[] args) {
		
		PApplet.main("Pool");
	}
	
	public void settings() {
		size(1280,824);
	}
	/**
	 * Where most initializing takes place
	 */
	public void setup() {
		frameRate(60);
		background(255);
		profile = new Profile();
		table = new Table(0.05f); 
		balls.add(new Ball(412, 415.01f, profile.getCueRadius(), profile.getCueMass(), 0, 0, "Cue", this));
		balls.add(new Ball(850, 415.01f, 10, 50, 0, 0, "1", this));
		balls.add(new Ball(868, 427, 10, 50, 0, 0, "2", this));
		balls.add(new Ball(868, 403, 10, 50, 0, 0, "3", this));
		balls.add(new Ball(887, 415.01f, 10, 50, 0, 0, "9", this));
		balls.add(new Ball(887, 437.01f, 10, 50, 0, 0, "4", this));
		balls.add(new Ball(887, 393.01f, 10, 50, 0, 0, "5", this));
		balls.add(new Ball(906, 427.01f, 10, 50, 0, 0, "6", this));
		balls.add(new Ball(906, 403, 10, 50, 0, 0, "7", this));
		balls.add(new Ball(925, 415.01f, 10, 50, 0, 0, "8", this));
		poolTable = loadImage("pool_table.png");
		poolCue = loadImage("pool_cue.png");
		poolMenu = loadImage("pool_menu.jpg");
		poolCarpet = loadImage("pool_carpet.jpg");
		poolCarpet.resize(1800,1024);
		cueBall = loadImage("cue ball.png");
		cueBall.resize(profile.getCueRadius()*2, profile.getCueRadius()*2);
		oneBall = loadImage("1 ball.png");
		oneBall.resize(20, 20);
		twoBall = loadImage("2 ball.png");
		twoBall.resize(20, 20);
		threeBall = loadImage("3 ball.png");
		threeBall.resize(20, 20);
		fourBall = loadImage("4 ball.png");
		fourBall.resize(20, 20);
		fiveBall = loadImage("5 ball.png");
		fiveBall.resize(20, 20);
		sixBall = loadImage("6 ball.png");
		sixBall.resize(20, 20);
		sevenBall = loadImage("7 ball.png");
		sevenBall.resize(20, 20);
		eightBall = loadImage("8 ball.png");
		eightBall.resize(20, 20);
		nineBall = loadImage("9 ball.png");
		nineBall.resize(20, 20);
		menuFont = createFont("Arial Bold", 64);
	}
	/**
	 * Where all of the graphics takes place
	 */
	public void draw() {
		if(gameState.equals("Play")) {
			background(255);
			image(poolMenu, 0,0);
			image(poolTable,150,150);
			fill(0,90,128);
			rect(150,20, 100, 100);
			rect(270,20, 100, 100);
			rect(402,20, 722, 90);
			image(poolCue,dragSquareX,dragSquareY);
			fill(255);
			textFont(menuFont);
			textSize(18);
			text("Quit", 1200, 42);
			text("Shot Count", 272, 42);
			textSize(30);
			textAlign(CENTER);
			text(Integer.toString(numberOfHits), 320, 90);
			textAlign(LEFT);
			textSize(20);
			text("Ball To Hit", 152, 42);
			if(mouseX >= 1200 && mouseX <= 1250 && mouseY >=20 && mouseY <= 40 && mousePressed && mouseButton==LEFT) {
				gameState = "Menu";
				newGame = true;
			}
			if(ballToHit==1) 
				image(oneBall, 190,70);
			else if(ballToHit==2)
				image(twoBall, 190,70);
			else if(ballToHit==3)
				image(threeBall, 190,70);
			else if(ballToHit==4)
				image(fourBall, 190,70);
			else if(ballToHit==5)
				image(fiveBall, 190,70);
			else if(ballToHit==6)
				image(sixBall, 190,70);
			else if(ballToHit==7)
				image(sevenBall, 190,70);
			else if(ballToHit==8)
				image(eightBall, 190,70);
			else if(ballToHit==9)
				image(nineBall, 190,70);
			for(Ball b: balls) {
				table.collisionX(b);
				table.collisionY(b);
				table.checkPockets(b, this);
				if(b.getType().equals("Cue")) {
					imageMode(CENTER);
					image(cueBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("1")) {
					imageMode(CENTER);
					image(oneBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("2")) {
					imageMode(CENTER);
					image(twoBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("3")) {
					imageMode(CENTER);
					image(threeBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("4")) {
					imageMode(CENTER);
					image(fourBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("5")) {
					imageMode(CENTER);
					image(fiveBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("6")) {
					imageMode(CENTER);
					image(sixBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("7")) {
					imageMode(CENTER);
					image(sevenBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("8")) {
					imageMode(CENTER);
					image(eightBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else if(b.getType().equals("9")) {
					imageMode(CENTER);
					image(nineBall, b.getX(), b.getY());
					imageMode(CORNER);
				}
				else {
					fill(10,50,90);
					ellipse((float)b.getX(), (float)b.getY(), (float)b.getRadius()*2, (float)b.getRadius()*2);
				}
				b.tick(table);
			}
			if(state.equals("Run")) {
				if(!ballRemove.equals(new Ball())) {
					removeBall();
				}
				for(Ball i: balls) {
					for(Ball j: balls) {
						if(checkCollision(i,j)&&!i.equals(j)) {
							System.out.println(i.getType() + " " + j.getType() + " " + ballToHit);
							if(i.getType().equals("Cue")&&!j.getType().equals(Integer.toString(ballToHit))&&firstBall) {
								System.out.println("Incorrect ball hit, 5 shot penalty");
								numberOfHits+=5;
								incorrectBall = true;
							}
							firstBall = false;
							setCollision(i,j);
							ballCollision(i,j);
						}
					}
				}
				if(drag&&mousePressed) {
					if(!initialClick) {
						initialX = mouseX;
					}
					initialClick = true;
					if(800+mouseX-initialX > 400 && 800+mouseX-initialX < 800) {
						dragSquareX = 800+mouseX-initialX;
					}else if(800+mouseX-initialX <= 400) {
						dragSquareX = 400;
					}else if(800+mouseX-initialX >= 800) {
						dragSquareX = 800;
					}
					shooting = true;
				}
				if(mousePressed&&notMoving()&&!drag) {//Pool cue is 200 pixels
					lineX = getCueCoords()[0];
					lineY = getCueCoords()[1];
					float distance = (float)Math.sqrt(Math.pow((mouseX-lineX),2)+Math.pow((mouseY-lineY),2));
					finalY = mouseY > lineY ? lineY+((Math.abs(mouseY-lineY))*(200/distance)) : lineY-((Math.abs(mouseY-lineY))*(200/distance)); 
					finalX = mouseX > lineX ? lineX+((Math.abs(mouseX-lineX))*(200/distance)) : lineX-((Math.abs(mouseX-lineX))*(200/distance));
					lineX+= (finalX-lineX)*-0.2;
					lineY+= (finalY-lineY)*-0.2;
					line(finalX, finalY, lineX, lineY);
					clicked = true;
				}else if(!mousePressed) {
					if(clicked) {
						drag = true;
					}
					initialClick = false;
					clicked = false;
					power = Math.abs(800-dragSquareX)/25.0f;
					dragSquareX = 800;
					if(shooting) {
						shoot(lineX, lineY, finalX, finalY, power);
						shooting = false;
						drag = false;
					}
					
				}
			}else if(state.equals("move ball")) {
				if(mousePressed) {
					placeBall();
					clicked = true;
				}else if(clicked && !mousePressed){
					toBeChanged = "Run";
					clicked = false;
				}
				
			}
			if(notMoving())
				state = toBeChanged;
		
		}else if(gameState.equals("Menu")) {
			fill(255);
			image(poolMenu, 0,0);
			textFont(menuFont);
			textSize(100);
			text("9 Ball", 530, 200);
			fill(255);
			textSize(30);
			text("Rules", 310, 430);
			text("Profile", 310, 530);
			text("Play", 310, 630);
			text("Custom Cue Ball", 310, 730);
			if(newGame) {
				newGame();
			}
			if(mouseX >= 300 && mouseX <= 500 && mouseY >= 600 && mouseY <= 650 && mousePressed && mouseButton == LEFT) {
				clickedOn = "Play";
			}
			if(mouseX >= 300 && mouseX <= 500 && mouseY >= 700 && mouseY <= 750 && mousePressed && mouseButton == LEFT) {
				clickedOn = "Customize Ball";
			}
			if(mouseX >= 300 && mouseX <= 500 && mouseY >= 500 && mouseY <= 550 && mousePressed && mouseButton == LEFT) {
				clickedOn = "Profile";
			}
			if(mouseX >= 300 && mouseX <= 500 && mouseY >= 400 && mouseY <= 450 && mousePressed && mouseButton == LEFT) {
				clickedOn = "Rules";
			}
			if(!mousePressed&&clickedOn!="") {
				gameState = clickedOn;
				clickedOn = "";
			}
		
		
		}else if(gameState.equals("Customize Ball")) {
			int cueMass = (int)((802-cueMassStickX)/5)+50;
			int cueRadius = (int)((802-cueRadiusStickX)/20)+10;
			fill(255);
			image(poolMenu, 0,0);
			textFont(menuFont);
			textSize(100);
			text("Custom Ball", 530, 200);
			fill(255);
			textSize(30);
			text("Back", 100, 100);
			noStroke();
			text("Mass Cue Ball: ", 10, 400);
			rect(250,350, 874, 90);
			text("Radius Cue Ball: ", 10, 600);
			rect(250,550, 874, 90);
			image(poolCue, cueMassStickX, 380);
			image(poolCue, cueRadiusStickX, 580);
			text(Integer.toString(cueMass), 1200, 400);
			text(Integer.toString(cueRadius), 1200, 600);
			if(mouseX >= cueMassStickX && mouseX <= cueMassStickX+(1123-802) && mouseY >=350 && mouseY <=440 && mousePressed && mouseButton == LEFT) {
				if(!customClicked) {
					initX = mouseX-cueMassStickX;
				}
				cueMassStickX = mouseX-initX;
				if(cueMassStickX > 802)
					cueMassStickX = 802;
				else if(cueMassStickX < 250) 
					cueMassStickX = 250;
				customClicked = true;
			}
			if(mouseX >= cueRadiusStickX && mouseX <= cueRadiusStickX+(1123-802) && mouseY >=550 && mouseY <=640 && mousePressed && mouseButton == LEFT) {
				if(!customClicked) {
					initX = mouseX-cueRadiusStickX;
				}
				cueRadiusStickX = mouseX-initX;
				if(cueRadiusStickX > 802)
					cueRadiusStickX = 802;
				else if(cueRadiusStickX < 250) 
					cueRadiusStickX = 250;
				customClicked = true;
			}
			if(mouseX >= 100 && mouseX <=175 && mouseY >= 78 && mouseY <= 100 && mousePressed && mouseButton == LEFT) {
				gameState = "Menu";
				profile.setCueMass(cueMass);
				profile.setCueRadius(cueRadius);
				cueBall.resize(profile.getCueRadius()*2, profile.getCueRadius()*2);
				balls.get(0).setMass(profile.getCueMass());
				balls.get(0).setRadius(profile.getCueRadius());
				stroke(0);
			}
			if(!mousePressed) {
				customClicked = false;
				initX = -1;
			}
		}
		
		else if(gameState.equals("New Profile")) {
			System.out.println(mouseX + " " + mouseY);
			fill(255);
			image(poolMenu, 0,0);
			textFont(menuFont);
			textSize(100);
			text("Welcome to 9 Ball", 200, 200);
			textSize(30);
			text("Load", 1100, 200);
			text("Name: ", 530, 400);
			text(TextInput.getText(), 630, 400);
			text("Harcore or Casual Player?", 530, 500);
			text("H", 530, 550);
			text("C", 600, 550);
			textSize(10);
			text(hardcore ? "Current Status: Hardcore" : "Current Status: Casual", 650, 550);
			if(mouseX >= 530 && mouseX <= 552 && mouseY >= 527 && mouseY <= 550 && mousePressed && mouseButton == LEFT) {
				hardcore = true;
			}else if(mouseX >= 600 && mouseX <= 622 && mouseY >= 527 && mouseY <= 550 && mousePressed && mouseButton == LEFT) {
				hardcore = false;
			}
			textSize(30);
			text("Complete Profile", 530, 650);
			if(mouseX >= 530 && mouseX <= 768 && mouseY >= 627 && mouseY <=652 && mousePressed && mouseButton == LEFT){
				profile = hardcore ? new HardcorePlayer(TextInput.getText()) : new Profile(TextInput.getText()); 
				gameState = "Menu";
			}
			if(mouseX >= 1100 && mouseX <= 1170 && mouseY >= 178 && mouseY <=200 && mousePressed && mouseButton == LEFT) {
				try {
					load();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				gameState = "Menu";
				
			}
			
		}
		
		else if(gameState.equals("Profile")) {
			fill(255);
			image(poolMenu, 0,0);
			textFont(menuFont);
			textSize(100);
			text(profile.getName() + "'s Profile", 200, 200);
			textSize(30);
			if(profile instanceof HardcorePlayer) {
				text("Games Won: " + Integer.toString(((HardcorePlayer)profile).getSucGames()), 200, 400);
				text("Games Lost: " + Integer.toString(((HardcorePlayer)profile).getUnSucGames()), 200, 500);
				text("Best Score: " + Integer.toString(((HardcorePlayer)profile).getBestScore()), 200, 600);
			}else {
				text("Games Played: " + Integer.toString(profile.getGamesPlayed()), 200, 400);
			}
			text("Back", 100, 100);
			if(mouseX >= 100 && mouseX <=175 && mouseY >= 78 && mouseY <= 100 && mousePressed && mouseButton == LEFT) {
				gameState = "Menu";
			}
			text("Save", 1100, 100);
			if(mouseX >= 1100 && mouseX <=1175 && mouseY >= 78 && mouseY <= 100 && mousePressed && mouseButton == LEFT) {
				try {
					save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		else if(gameState.equals("Rules")) {
			fill(255);
			image(poolMenu, 0,0);
			textFont(menuFont);
			textSize(100);
			text("Rules", 300, 200);
			textSize(20);
			text("This game is based off of the classic original game of \"9 Ball\" however it involves only one player.", 50, 300);
			text("The rules of the game are as follows:", 50, 350);
			text("- You must always hit the assigned ball first. Hitting a ball which is not assigned first will result in a 5 move penalty", 50, 400);
			text("- If the cue ball is pocketed, it will result in a 3 move penalty", 50, 450);
			text("- If a ball which is not assigned is hit first, and later during that turn any ball (excluding the cue ball) is to be pocketed, ", 50, 500);
			text("  the game is over", 50, 550);
			text("- Your goal is to finish the game with the fewest number of moves possible", 50, 600);
			text("- All other rules to 9 Ball pool apply", 50, 650);
			textSize(30);
			text("Back", 100, 100);
			if(mouseX >= 100 && mouseX <=175 && mouseY >= 78 && mouseY <= 100 && mousePressed && mouseButton == LEFT) {
				gameState = "Menu";
			}
		}
			
	}
	
	/**
	 * Checks for keyboard input
	 */
	public void keyReleased() {
        if(keyChecker(key).equals("Letter")) {
            TextInput.addText(key);
        }
        else if(keyCode==BACKSPACE) {
            TextInput.removeText();
        }
	}
	/**
	 * Checks to see whether a character is in the alphabet
	 * @param c The character to be checked
	 * @return The condition of that character (A letter or not)
	 */
	public String keyChecker(char c) {
	        if(c>='a' && c<='z' || c>='A' && c<='Z' ||  c==' ') {
	            return "Letter";
	        }
	        return "Other";
	}
	
	/**
	 * Saves the profile of the current user
	 * @throws IOException Exception
	 */
	public void save() throws IOException{
        FileOutputStream fout = new FileOutputStream("save.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(profile);
        oos.close();

	}
	
	/**
	 * Loads the profile information from a past user
	 * @throws IOException Exception
	 * @throws ClassNotFoundException Class not found
	 */
	public void load() throws IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream("save.ser");
        ObjectInputStream ois = new ObjectInputStream(fin);
        profile = (Profile) ois.readObject();
        ois.close();
    }
	
	/**
	 * Method used to create a new game (2 part Method)
	 * This is the first part, and is used so that no Concurrent Modification Exceptions occur
	 */
	public void triggerNewGame() {
		newGame = true;
	}
	/**
	 * Use to remove ball from balls List (2 part Method)
	 * This is the first part, and is used so that no Concurrent Modification Exceptions occur
	 * @param b Ball to be removed
	 */
	public void triggerRemoval(Ball b) {
		ballRemove = b;
	}
	/**
	 * Removes ball from List
	 */
	public void removeBall() {
		balls.remove(ballRemove);
		ballRemove = new Ball();
	}
	/**
	 * Sets many of the important game variables to their initial state 
	 */
	public void newGame() {
		balls.clear();
		balls.add(new Ball(412, 415.01f, profile.getCueRadius(), profile.getCueMass(), 0, 0, "Cue", this));
		balls.add(new Ball(850, 415.01f, 10, 50, 0, 0, "1", this));
		balls.add(new Ball(868, 427, 10, 50, 0, 0, "2", this));
		balls.add(new Ball(868, 403, 10, 50, 0, 0, "3", this));
		balls.add(new Ball(887, 415.01f, 10, 50, 0, 0, "9", this));
		balls.add(new Ball(887, 437.01f, 10, 50, 0, 0, "4", this));
		balls.add(new Ball(887, 393.01f, 10, 50, 0, 0, "5", this));
		balls.add(new Ball(906, 427.01f, 10, 50, 0, 0, "6", this));
		balls.add(new Ball(906, 403, 10, 50, 0, 0, "7", this));
		balls.add(new Ball(925, 415.01f, 10, 50, 0, 0, "8", this));
		newGame = false;
		numberOfHits = 0;
		ballToHit = 1;
	}
	
	/**
	 * Sets the assigned ball to a specific value
	 * Checks if the new value is greater than 10 to prompt a trasition to the menu 
	 * @param n The new value
	 */
	public void setBall(int n) {
		ballToHit = n > ballToHit ? n: ballToHit;
		if(ballToHit>9) {
			profile.winGame(numberOfHits);//Polymorphism
			triggerNewGame();
			gameState = "Menu";
		}
	}
	
	/**
	 * A setter method to change the state of the game
	 * @param n The state to be changed to
	 */
	public void setGameState(String n) {
		gameState = n;
	}
	
	/**
	 * Returns the assigned ball
	 * @return The assigned ball
	 */
	public int getBall() {
		return ballToHit;
	}
	
	/**
	 * Returns whether or not the first ball hit is the assigned ball
	 * @return Returns true for assigned ball
	 */
	public boolean getFirstBallHit() {
		return incorrectBall;
	}
	
	/**
	 * A setter which changes the game shot count
	 * @param n Change in hits
	 */
	public void setNumberOfHits(int n) {
		numberOfHits+=n;
	}
	
	/**
	 * Finds the coordinates to the cue ball
	 * @return The x and y coordinates
	 */
	public float[] getCueCoords() {
		float [] coords = new float[2];
		for(Ball b: balls) {
			if(b.getType().equals("Cue")) {
				coords[0] = b.getX();
				coords[1] = b.getY();
				return coords;
			}
		}
		return null;
	}
	
	/**
	 * Returns the in-game state
	 * @return in-game state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Method changes the x and y velocities for the cue ball depending on the input
	 * @param lineX The x1 position from the previous line drawn
	 * @param lineY The y1 position from the previous line drawn
	 * @param finalX The x2 position from the previous line drawn
	 * @param finalY The y2 position from the previous line drawn
	 * @param power The power of the shot
	 */
	public void shoot(float lineX, float lineY, float finalX, float finalY, float power) {
		for(Ball b: balls) {
			if(b.getType().equals("Cue")) {
				float dispX = finalX-lineX;
				float dispY = finalY-lineY;
				float angle = (float)Math.abs(Math.atan(dispY/dispX));
				int quad = getQuad(dispX, dispY);//quadrants are reversed (because y increases down)
				if(quad == 1) {
					b.setVeloX((float)(power*Math.cos(angle))*-1);
					b.setVeloY((float)(power*Math.sin(angle))*-1);
				
				}else if(quad == 2) {
					b.setVeloX((float)(power*Math.cos(angle)));
					b.setVeloY((float)(power*Math.sin(angle))*-1);
				
				}else if(quad == 3) {
					b.setVeloX((float)(power*Math.cos(angle)));
					b.setVeloY((float)(power*Math.sin(angle)));
				
				}else {
					b.setVeloX((float)(power*Math.cos(angle))*-1);
					b.setVeloY((float)(power*Math.sin(angle)));
				}
			}
		}
		numberOfHits++;
		incorrectBall = false;
		firstBall = true;
	}
	
	/**
	 * Method allows user to place the ball at the start of the game
	 */
	public void placeBall() {
		for(Ball b: balls) {
			if(b.getType().equals("Cue")) {
				if(mouseX < 178) {
					b.setX(178+b.getRadius());	
				}else if(mouseX > 414) {
					b.setX(414);
				}else {
					b.setX(mouseX);
				}
				if(mouseY < 181) {
					b.setY(181+b.getRadius());
				}else if(mouseY > 648) {
					b.setY(648-b.getRadius());
				}else {
					b.setY(mouseY);
				}
			}
		}
	}
	
	/**
	 * Gets the quadrant given x and y coordinates
	 * @param dispX X displacement
	 * @param dispY Y displacement
	 * @return The quadrant in which the coordinates reside
	 */
	public int getQuad(float dispX, float dispY) {
		if(dispX > 0 && dispY > 0) {
			return 1;
		}else if(dispX < 0 && dispY > 0) {
			return 2;
		}else if(dispX < 0 && dispY < 0) {
			return 3;
		}
		return 4;
	}
	
	/**
	 * Method checks to see whether any balls on the screen are moving
	 * @return True if all balls are stationary
	 */
	public boolean notMoving() {
		for(Ball b: balls) {
			if(b.getVeloX() != 0 || b.getVeloY() != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks to see if any balls are colliding
	 * @param ball1 The first ball
	 * @param ball2 The second ball
	 * @return True if one ball is inside the other
	 */
	public boolean checkCollision(Ball ball1, Ball ball2) {
		return sq(ball1.getX() - ball2.getX()) + sq(ball1.getY() - ball2.getY()) < sq(ball1.getRadius() + ball2.getRadius());
	}
	
	/**
	 * Method subtracts the velocities of each ball so that they do not become stuck inside eachother
	 * @param A The first ball
	 * @param B The second ball
	 */
	public void setCollision(Ball A, Ball B) {
		A.setX(A.getX()-A.getVeloX());
		A.setY(A.getY()-A.getVeloY());
		B.setX(B.getX()-B.getVeloX());
		B.setY(B.getY()-B.getVeloY());
	}
	
	/**
	 * The physics behind the kinetic energy/momentum transfer
	 * @param A First ball
	 * @param B Second ball
	 */
	//I tried my best to use what I had learned in my physics class to write this method myself
	//I used simple conservation of momentum equations, and eventually had a massive equation which was unsolvable (After 2 hours Unfortunately)
	//The following method uses equations which are taught in university/college, and credit goes to Sirna for the research, and writing of the code
	public void ballCollision (Ball A, Ball B) { //THIS IS AN ELASTIC COLLISION (KINETIC ENERGY IS CONSERVED)
		PVector vA = new PVector(A.getVeloX(), A.getVeloY());
		PVector vB = new PVector(B.getVeloX(), B.getVeloY());
		PVector preA = new PVector();
		PVector preB = new PVector();
		PVector finalA = new PVector();
		PVector finalB = new PVector();
		PVector newa = new PVector();
		PVector newb = new PVector();
		float dx, dy, t, magA, magB, d1, d2;

		dx = A.getX()-B.getX();//X displacement between Balls A and B
		dy = A.getY()-B.getY();//Y displacement between Balls A and B
		t = atan2(dy, dx);  //Angle of collision
		magA = sqrt(vA.x*vA.x + vA.y*vA.y);//Velocity of BallA (No components)
		magB = sqrt(vB.x*vB.x + vB.y*vB.y);//Velocity of BallB (No components)
		d1 = atan2(vA.y, vA.x); //Angle Direction of Ball A
		d2 = atan2(vB.y, vB.x); //Angle Direction of Ball B
		preA.set(magA*cos(d1-t), magA*sin(d1-t));//X and Y Velocities for Ball A
		preB.set(magB*cos(d2-t), magB*sin(d2-t));//X and Y Velocities for Ball B
		finalA.set(((A.getMass()-B.getMass())*preA.x + 2f*B.getMass()*preB.x) / (A.getMass()+B.getMass()), preA.y, 0);  //Kinetic energy transfer (Mixture of momentum and energy equations)
		finalB.set(((B.getMass()-A.getMass())*preB.x + 2f*A.getMass()*preA.x) / (A.getMass()+B.getMass()), preB.y, 0);  
		newa.set( (cos(t)*finalA.x + cos(t + HALF_PI)*finalA.y), (sin(t)*finalA.x + sin(t + HALF_PI)*finalA.y), 0 );
		newb.set( (cos(t)*finalB.x + cos(t + HALF_PI)*finalB.y), (sin(t)*finalB.x + sin(t + HALF_PI)*finalB.y), 0 );
		A.hardSetVeloX(newa.x); A.hardSetVeloY(newa.y);
		B.hardSetVeloX(newb.x); B.hardSetVeloY(newb.y);
	}
	
}
