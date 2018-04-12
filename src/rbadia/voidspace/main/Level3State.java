package rbadia.voidspace.main;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
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
	int adjustedY = rand.nextInt((this.getHeight() - 64) - ((this.getHeight() - 64)/3)*2) + ((this.getHeight() - 64)/3)*2;
//	int adjustedY = rand.nextInt(this.getHeight());
	protected Asteroid mooshroom;
	protected Rectangle mooshroomExplosion;
	protected boolean mooshroomDestroyed = false;
	protected double lastmooshroomTime;
	protected boolean canDraw = false;
	protected int mooshroomDelay = 10000;
//	protected boolean mooshroomDestroyed
	
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
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
		drawPowerUpLives();
		checkPowerUpLivesCollisions();
	}

	@Override
	protected void drawAsteroid() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid.getX() + asteroid.getPixelsWide() >  0)) {
			asteroid.translate(-asteroid.getSpeed(), asteroid.getSpeed()/2);
			getGraphicsManager().drawAsteroid(asteroid, g2d, this);	
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){

				asteroid.setLocation(SCREEN_WIDTH - asteroid.getPixelsWide(),
						rand.nextInt(SCREEN_HEIGHT - asteroid.getPixelsTall() - 32));
			}
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}

	@Override
	public Platform[] newPlatforms(int n){
		platforms = new Platform[n];
		for(int i=0; i<n; i++){
			this.platforms[i] = new Platform(0,0);
			if(i<4)	platforms[i].setLocation(50+ i*50, SCREEN_HEIGHT/2 + 140 - i*40);
			if(i==4) platforms[i].setLocation(50 +i*50, SCREEN_HEIGHT/2 + 140 - 3*40);
			if(i>4){	
				int k=4;
				platforms[i].setLocation(50 + i*50, SCREEN_HEIGHT/2 + 20 + (i-k)*40 );
				k=k+2;
			}
		}
		return platforms;
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
		int xPos = (int) (screen.getWidth() - Asteroid.WIDTH);
		adjustedY = rand.nextInt((this.getHeight() - 64) - ((this.getHeight() - 64)/3)*2) + ((this.getHeight() - 64)/3)*2;
		mooshroom = new Asteroid(xPos, adjustedY);
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
			mooshroomDestroyed = true;
			removePowerUpLives(mooshroom);
			GameStatus status = getGameStatus();
			getGraphicsManager().drawPowerUpLivesExplosion(mooshroomExplosion, g2d, this);
			status.setLivesLeft(status.getLivesLeft() + 5);
			return;
			
		}
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(mooshroom.intersects(bullet)){
				mooshroomDestroyed = true;
				removePowerUpLives(mooshroom);
				getGraphicsManager().drawPowerUpLivesExplosion(mooshroomExplosion, g2d, this);
				bullets.remove(i);
				GameStatus status = getGameStatus();
				status.setLivesLeft(status.getLivesLeft() + 5);
				break;
			}
		}
	}
}
