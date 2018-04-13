package rbadia.voidspace.main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.LeftBullet;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

public class Level5State extends Level3State{

	private static final long serialVersionUID = 1L;

	public Level5State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
	}
	
	protected MegaMan megaBoss;
	protected List<Bullet> bossBullets;
	
	@Override
	public void doStart() {	
		super.doStart();
		newPlatforms(getNumPlatforms());
		bossBullets = new ArrayList<Bullet>();
		newMegaBoss();
		
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
		drawPlatforms();
		drawMegaBoss();
//		fireBossBullet();
		randomBossShooting();
		drawMegaBossBullets();
	}
	
	@Override
	public Platform[] newPlatforms(int n){
		platforms = new Platform[n];
		for(int i=0; i<n; i++){
			this.platforms[i] = new Platform(0,0);
			if (i < 5) platforms[i].setLocation(20+ i*50, SCREEN_HEIGHT/2 + 140 - i*40);
			if (i == 5) platforms[i].setLocation(100+ i*50, SCREEN_HEIGHT/2 + 140 - i*30);
			if (i > 5) platforms[i].setLocation(100+ i*50, SCREEN_HEIGHT/2 + 140 - i*30);
		}
		return platforms;
	}
	
	public MegaMan newMegaBoss(){
//		this.megaBoss = new MegaMan((SCREEN_WIDTH- MegaMan.WIDTH) / 2, (SCREEN_HEIGHT - MegaMan.HEIGHT - MegaMan .Y_OFFSET) / 2);
		this.megaBoss = new MegaMan(315, 315);
		return megaBoss;
	}
	
	protected void drawMegaBoss() {
		//draw one of three possible MegaBoss poses according to situation
		Graphics2D g2d = getGraphics2D();
		getGraphicsManager().drawMegaBoss(megaBoss, g2d, this);
	}
	
	
	public void moveMegaBossUp(){
		if(megaBoss.getY() - megaBoss.getSpeed() >= 0){
			megaBoss.translate(0, -megaBoss.getSpeed()*2);
		}
	}
	
	/**
	 * Move the megaBoss down
	 * @param megaBoss the megaBoss
	 */
	public void movemegaBossDown(){
		for(int i=0; i<9; i++){
			if(megaBoss.getY() + megaBoss.getSpeed() + megaBoss.height < SCREEN_HEIGHT - floor[i].getHeight()/2 + 20){ //+15 since the picture is big and the bullets wouldn't hit megaman
				megaBoss.translate(0, 2);
			}
		}
	}

	/**
	 * Move the megaBoss left
	 * @param megaBoss the megaBoss
	 */
	public void movemegaBossLeft(){
		if(megaBoss.getX() - megaBoss.getSpeed() >= 0){
			megaBoss.translate(-megaBoss.getSpeed(), 0);
		}
	}

	/**
	 * Move the megaBoss right
	 * @param megaBoss the megaBoss
	 */
	public void movemegaBossRight(){
		if(megaBoss.getX() + megaBoss.getSpeed() + megaBoss.width < SCREEN_WIDTH){
			megaBoss.translate(megaBoss.getSpeed(), 0);
		}
	}

	public void speedUpmegaBoss() {
		megaBoss.setSpeed(megaBoss.getDefaultSpeed() * 2 +1);
	}

	public void slowDownmegaBoss() {
		megaBoss.setSpeed(megaBoss.getDefaultSpeed());
	}

	//BULLETS
	
	public void fireBossBullet(){
		Bullet bullet = new Bullet(megaBoss.x + megaBoss.width - Bullet.WIDTH/2-25,
				megaBoss.y + megaBoss.width/2 - Bullet.HEIGHT +2);
		bossBullets.add(bullet);
		this.getSoundManager().playBulletSound();
	}
	
	public void fireBossLeftBullet(){
		Bullet bullet = new LeftBullet(megaBoss.x + megaBoss.width - Bullet.WIDTH/2-25,
				megaBoss.y + megaBoss.width/2 - Bullet.HEIGHT +2);
		bossBullets.add(bullet);
		this.getSoundManager().playBulletSound();
	}
	
	protected void drawMegaBossBullets() {
		Graphics2D g2d = getGraphics2D();
		for(int i=0; i<bossBullets.size(); i++){
			Bullet bullet = bossBullets.get(i);
			getGraphicsManager().drawMegaBossBullet(bullet, g2d, this);
			boolean remove = this.moveMegaBossBullet(bullet);
			if(remove){
				bossBullets.remove(i);
				i--;
			}
		}
	}
	
	public boolean moveMegaBossBullet(Bullet bullet){
		if(bullet.getY() - bullet.getSpeed() >= 0){
			bullet.translate(bullet.getSpeed(), 0);
			return false;
		}
		else{
			return true;
		}
	}
	
	public void randomBossShooting() {
		Random r = new Random();
		int randomNum = r.nextInt(30);
		if (randomNum == 1) {
			fireBossBullet();
			fireBossLeftBullet();
		}else if (randomNum == 2) {
			movemegaBossRight();
			movemegaBossRight();
		}else if (randomNum == 3) {
			movemegaBossLeft();
			movemegaBossLeft();
		}else if (randomNum == 4) {
			movemegaBossDown();
			movemegaBossDown();
		}else if (randomNum == 5) {
			moveMegaBossUp();
			moveMegaBossUp();
		}
		else {
		}
	}
	

}
