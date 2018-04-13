package rbadia.voidspace.main;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javafx.scene.layout.Background;
import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.BigBullet;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

/**
 * Level very similar to LevelState1.  
 * Platforms arranged in triangular form. 
 * Asteroids travel at 225 degree angle
 */
public class Level3State extends Level1State {

	private static final long serialVersionUID = -2094575762243216079L;
//	int adjustedY = rand.nextInt((this.getHeight() - 64) - ((this.getHeight() - 64)/3)*2) + ((this.getHeight() - 64)/3)*2;
//	int adjustedY = rand.nextInt(this.getHeight());
	protected Asteroid mooshroom;
	protected Rectangle mooshroomExplosion;
//	protected boolean mooshroomDestroyed = false;
	protected double lastmooshroomTime;
	protected boolean canDraw = false;
	protected int mooshroomDelay = 10000;
	protected int side = 1;
	protected int side2 = 1;
	protected int randomSpeedx1 = rand.nextInt(2) + 1;
	protected int randomSpeedy1 = rand.nextInt(2) + 1;
	protected int randomSpeedx2 = rand.nextInt(2) + 1;
	protected int randomSpeedy2 = rand.nextInt(2) + 1;
	protected Asteroid asteroid2;
	

	// Constructors
	public Level3State(int level, MainFrame frame, GameStatus status, 
			LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
	}

	@Override
	public void doStart() {	
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
		NewPowerUpLives(this);
		newAsteroid2(this);
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
		drawPowerUpLives();
		checkPowerUpLivesCollisions();
		drawAsteroid2();
	}
	
	@Override
	public Platform[] newPlatforms(int n){
		platforms = new Platform[n];
		for(int i=0; i<n; i++){
			this.platforms[i] = new Platform(0,0);
			if(i<4)	platforms[i].setLocation(50+ i*50, SCREEN_HEIGHT/2 + 140 - i*40);
			if(i==4) platforms[i].setLocation(200 +i*50, SCREEN_HEIGHT/2 + 140 - 3*40);
			if(i>4){	
				int k=4;
				platforms[i].setLocation(200 + i*50, SCREEN_HEIGHT/2 + 20 + (i-k)*40 );
				k=k+2;
			}
		}
		return platforms;
	}

	@Override
	protected void drawAsteroid() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid.getX() + asteroid.getPixelsWide() >  0) && (asteroid.getX() < this.getWidth())) {
			if (side == 1) { //move left
				asteroid.translate(-asteroid.getSpeed()*randomSpeedx1, asteroid.getSpeed()/randomSpeedy1);
			} else { //move right
				asteroid.translate(asteroid.getSpeed()*randomSpeedx2, asteroid.getSpeed()/randomSpeedy2);
			}
//			asteroid.translate(-asteroid.getSpeed(), asteroid.getSpeed()/2);
			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
		}
		else {
			side = rand.nextInt(2); //Generate new side for the asteroid
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){
			if (side == 1) { //move left
				asteroid.setLocation(SCREEN_WIDTH - asteroid.getPixelsWide(),
						rand.nextInt(SCREEN_HEIGHT - asteroid.getPixelsTall() - 32));
				}else { //move right
					asteroid.setLocation(0,
							rand.nextInt(SCREEN_HEIGHT - asteroid.getPixelsTall() - 32));
				}
			}
				
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}
	
	public Asteroid newAsteroid2(Level1State screen){
//		int xPos = (int)(screen.getWidth() - Asteroid.WIDTH);
		int xPos = rand.nextInt((int)(SCREEN_WIDTH - Asteroid.WIDTH));
		int yPos= rand.nextInt((int)(SCREEN_HEIGHT - Asteroid.HEIGHT- 32));
		
		asteroid2 = new Asteroid(xPos, yPos);
		return asteroid2;
	}
	
	protected void drawAsteroid2() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid2.getX() + asteroid2.getPixelsWide() >  0) && (asteroid2.getX() < this.getWidth())) {
			if (side2 == 1) { //move left
				asteroid2.translate(-asteroid2.getSpeed()*randomSpeedx1, asteroid2.getSpeed()/randomSpeedy1);
			} else { //move right
				asteroid2.translate(asteroid2.getSpeed()*randomSpeedx2, asteroid2.getSpeed()/randomSpeedy2);
			}
//			asteroid.translate(-asteroid.getSpeed(), asteroid.getSpeed()/2);
			getGraphicsManager().drawAsteroid(asteroid2, g2d, this);	
		}
		else {
			side2 = rand.nextInt(2); //Generate new side for the asteroid
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){
			if (side2 == 1) { //move left
				asteroid2.setLocation(SCREEN_WIDTH - asteroid2.getPixelsWide(),
						rand.nextInt(SCREEN_HEIGHT - asteroid2.getPixelsTall() - 32));
				}else { //move right
					asteroid2.setLocation(0,
							rand.nextInt(SCREEN_HEIGHT - asteroid2.getPixelsTall() - 32));
				}
			}
				
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}
	

	public boolean canPowerUpLives() {
		 double currentTime = System.currentTimeMillis();
		 if ((currentTime - lastmooshroomTime) >= mooshroomDelay) {
			 canDraw = true;
		 }
		 return canDraw;
		}
	
	//New Power Up
	public Asteroid NewPowerUpLives(Level1State screen){
//		int xPos = (int) (screen.getWidth() - Asteroid.WIDTH);
//		int xPos = (int) (screen.getWidth() - Asteroid.WIDTH);
//		adjustedY = rand.nextInt((this.getHeight() - 64) - ((this.getHeight() - 64)/3)*2) + ((this.getHeight() - 64)/3)*2;
		int xPos = rand.nextInt((this.getHeight() - 64) - ((this.getHeight() - 64)/3)*2) + ((this.getHeight() - 64)/3)*2;
		int yPos= rand.nextInt((this.getHeight() - 64) - ((this.getHeight() - 64)/3)*2) + ((this.getHeight() - 64)/3)*2;
		mooshroom = new Asteroid(xPos, yPos);
		return mooshroom;
	}
	
	//Draw Power Up
	protected void drawPowerUpLives() {
		if (canPowerUpLives()) {
			Graphics2D g2d = getGraphics2D();
			if((mooshroom.getX() + mooshroom.getPixelsWide() >  0)) {
				mooshroom.translate(-mooshroom.getSpeed()/4, -mooshroom.getSpeed()/4);
				getGraphicsManager().drawPowerUpLives(mooshroom, g2d, this);	
			}
			else {
				removePowerUpLives(mooshroom);
			}	
		}
	}
	
	//remove Power Up Lives
	public void removePowerUpLives(Asteroid mooshroom){
		mooshroomExplosion = new Rectangle(
				mooshroom.x,
				mooshroom.y,
				mooshroom.getPixelsWide(),
				mooshroom.getPixelsTall());
		mooshroom.setLocation(-mooshroom.getPixelsWide(), -mooshroom.getPixelsTall());
		this.getGameStatus().setNewAsteroid(false);
		NewPowerUpLives(this);
		lastmooshroomTime = System.currentTimeMillis();
		canDraw = false;
		// play asteroid explosion sound
//		this.getSoundManager().playAsteroidExplosionSound();
	}
	
	protected void checkPowerUpLivesCollisions() { 
		Graphics2D g2d = getGraphics2D();
		if (mooshroom.intersects(megaMan)) {
//			mooshroomDestroyed = true;
			removePowerUpLives(mooshroom);
			GameStatus status = getGameStatus();
			getGraphicsManager().drawPowerUpLivesExplosion(mooshroomExplosion, g2d, this);
			status.setLivesLeft(status.getLivesLeft() + 5);
			return;
			
		}
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(mooshroom.intersects(bullet)){
//				mooshroomDestroyed = true;
				removePowerUpLives(mooshroom);
				getGraphicsManager().drawPowerUpLivesExplosion(mooshroomExplosion, g2d, this);
				bullets.remove(i);
				GameStatus status = getGameStatus();
				status.setLivesLeft(status.getLivesLeft() + 5);
				break;
			}
		}
	}
	
	@Override
	protected void checkAsteroidFloorCollisions() {
		for(int i=0; i<9; i++){
			if(asteroid.intersects(floor[i])){
				removeAsteroid(asteroid);
			}else if(asteroid2.intersects(floor[i])){
				removeAsteroid(asteroid2);
			}
		}
	}
	
	@Override
	protected void checkMegaManAsteroidCollisions() {
		GameStatus status = getGameStatus();
		if(asteroid.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() - 1);
			removeAsteroid(asteroid);
		}
		else if(asteroid2.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() - 1);
			removeAsteroid(asteroid2);
		}
	}
	
	@Override
	protected void checkBigBulletAsteroidCollisions() {
		GameStatus status = getGameStatus();
		for(int i=0; i<bigBullets.size(); i++){
			BigBullet bigBullet = bigBullets.get(i);
			if(asteroid.intersects(bigBullet)){
				levelAsteroidsDestroyed++;
				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 100);
				removeAsteroid(asteroid);
				damage=0;
			}
			else if(asteroid2.intersects(bigBullet)){
				levelAsteroidsDestroyed++;
				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 100);
				removeAsteroid(asteroid2);
				damage=0;
			}
		}
	}

	@Override
	protected void checkBulletAsteroidCollisions() {
		GameStatus status = getGameStatus();
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(asteroid.intersects(bullet)){
				levelAsteroidsDestroyed++;
				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 100);
				removeAsteroid(asteroid);
				System.out.println("Asteroids Destroyed: " + levelAsteroidsDestroyed);
				damage=0;
				// remove bullet
				bullets.remove(i);
				break;
			}
			else if(asteroid2.intersects(bullet)){
				levelAsteroidsDestroyed++;
				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 100);
				removeAsteroid(asteroid2);
				System.out.println("Asteroids Destroyed: " + levelAsteroidsDestroyed);
				damage=0;
				// remove bullet
				bullets.remove(i);
				break;
			}
		}
	}
	//Added Background Image
	protected Background starrybakcgroundImg;
	protected void clearScreen() {
		// clear screen
		Graphics2D g2d = getGraphics2D();
		((GraphicsManager)getGraphicsManager()).drawstarryBackground(starrybakcgroundImg, g2d, this);
	}
}
