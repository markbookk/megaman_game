package rbadia.voidspace.graphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import javafx.scene.layout.Background;
import rbadia.voidspace.main.Level3State;
import rbadia.voidspace.model.Asteroid;
//import rbadia.voidspace.model.BigAsteroid;
import rbadia.voidspace.model.BigBullet;
//import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.Floor;
//import rbadia.voidspace.model.BulletBoss;
//import rbadia.voidspace.model.BulletBoss2;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.model.Platform;

/**
 * Manages and draws game graphics and images.
 */
public class GraphicsManager {
	private BufferedImage megaManImg;
	private BufferedImage megaManImg2;
	private BufferedImage megaFallRImg;
	private BufferedImage megaFallLImg;
	private BufferedImage megaFireLImg;
	private BufferedImage megaFireRImg;
	private BufferedImage floorImg;
	private BufferedImage platformImg;
	private BufferedImage bulletImg;
	private BufferedImage bigBulletImg;
	private BufferedImage asteroidImg;
	private BufferedImage asteroidExplosionImg;
	private BufferedImage megaManExplosionImg;
	private BufferedImage bigAsteroidExplosionImg;
	private BufferedImage powerUpLivesImg;
	private BufferedImage powerUpLivesExplosionImg;
	private BufferedImage asteroidBigImg;
	private BufferedImage megaBossImg;
	private BufferedImage megaBossBulletsImg;
	private BufferedImage starrybakcgroundImg;
	private BufferedImage planetsbakcgroundImg;
	private BufferedImage greenbakcgroundImg;
	private BufferedImage spacebackgroundImg;


	/**
	 * Creates a new graphics manager and loads the game images.
	 */
	public GraphicsManager(){
		// load images
		try {
			this.megaManImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaMan3.png"));
			this.megaManImg2 = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaMan4.png"));
			this.megaFallLImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFallLeft.png"));
			this.megaFallRImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFallRight.png"));
			this.megaFireLImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFireLeft.png"));
			this.megaFireRImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFireRight.png"));
			this.floorImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaFloor.png"));
			this.platformImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/platform3.png"));
			this.asteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroid.png"));
			this.asteroidBigImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bigAsteroid.png"));
			this.asteroidExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroidExplosion.png"));
			this.bulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bullet.png"));
			this.bigBulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bigBullet.png"));
			this.powerUpLivesImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/powerup.png"));
			this.powerUpLivesExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/powerupExplosion.png"));
			this.megaBossImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaBoss.png"));
			this.megaBossBulletsImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/megaBossBullets.png"));
			this.starrybakcgroundImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/starryback1.png"));
			this.planetsbakcgroundImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/Planetsbackground1.png"));
			this.greenbakcgroundImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/greenbackground3.png"));
			this.spacebackgroundImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/spacebackground1.png"));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"MegaMan!!! - Fatal Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Draws a MegaMan image to the specified graphics canvas.
	 * @param MegaMan the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */

	public void drawMegaMan (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaManImg, megaMan.x, megaMan.y, observer);	
	}
	
	public void drawMegaManLeft (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaManImg2, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaFallL (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFallLImg, megaMan.x, megaMan.y, observer);	
	}
	
	public void drawMegaFallR (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFallRImg, megaMan.x, megaMan.y, observer);	
	}
	
	public void drawMegaFireL (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFireLImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawMegaFireR (MegaMan megaMan, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaFireRImg, megaMan.x, megaMan.y, observer);	
	}

	public void drawFloor (Floor floor, Graphics2D g2d, ImageObserver observer, int i){
			g2d.drawImage(floorImg, floor.x, floor.y, observer);				
	}
	public void drawPlatform(Platform platform, Graphics2D g2d, ImageObserver observer, int i){
			g2d.drawImage(platformImg, platform.x , platform.y, observer);	
	}
	
	public void drawPlatform2 (Platform platform, Graphics2D g2d, ImageObserver observer, int i){
		g2d.drawImage(platformImg, platform.x , platform.y, observer);	
}
	
	public void drawgreenBackground(Background background, Graphics2D g2d, ImageObserver observer) { //NEW
		g2d.drawImage(this.greenbakcgroundImg, 0, 0, observer);
	}
	public void drawstarryBackground(Background background, Graphics2D g2d, ImageObserver observer) { //NEW
		g2d.drawImage(this.starrybakcgroundImg, 0, 0, observer);
	}
	public void drawplanetsBackground(Background background, Graphics2D g2d, ImageObserver observer) { //NEW
		g2d.drawImage(this.planetsbakcgroundImg, 0, 0, observer);
	}
	public void drawspaceBackground(Background background, Graphics2D g2d, ImageObserver observer) { //NEW
		g2d.drawImage(this.spacebackgroundImg, 0, 0, observer);
	}

	/**
	 * Draws a bullet image to the specified graphics canvas.
	 * @param bullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBullet(Bullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bulletImg, bullet.x, bullet.y, observer);
	}

	/**
	 * Draws a bullet image to the specified graphics canvas.
	 * @param bigBullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBigBullet(BigBullet bigBullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigBulletImg, bigBullet.x, bigBullet.y, observer);
	}

	/**
	 * Draws an asteroid image to the specified graphics canvas.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidImg, asteroid.x, asteroid.y, observer);
	}
	public void drawBigAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidBigImg, asteroid.x, asteroid.y, observer);
	}

	/**
	 * Draws a MegaMan explosion image to the specified graphics canvas.
	 * @param megaManExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawMegaManExplosion(Rectangle megaManExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(megaManExplosionImg, megaManExplosion.x, megaManExplosion.y, observer);
	}

	/**
	 * Draws an asteroid explosion image to the specified graphics canvas.
	 * @param asteroidExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroidExplosion(Rectangle asteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidExplosionImg, asteroidExplosion.x, asteroidExplosion.y, observer);
	}

	public void drawBigAsteroidExplosion(Rectangle bigAsteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigAsteroidExplosionImg, bigAsteroidExplosion.x, bigAsteroidExplosion.y, observer);
	}

	public void drawPowerUpLives(Asteroid powerUp, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(powerUpLivesImg, powerUp.x, powerUp.y, observer);
	}
	public void drawPowerUpLivesExplosion(Rectangle powerUpExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(powerUpLivesExplosionImg, powerUpExplosion.x, powerUpExplosion.y, observer);
	}
	
	//Boss Stuff
	public void drawMegaBoss (MegaMan megaBoss, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(megaBossImg, megaBoss.x, megaBoss.y, observer);	
	}
	public void drawMegaBossBullet(Bullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(megaBossBulletsImg, bullet.x, bullet.y, observer);
	}
	



}
