package rbadia.voidspace.model;

public class LeftBullet extends Bullet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeftBullet(int xPos, int yPos) {
		super(xPos, yPos);
		this.setSpeed(-12);
	}

}
