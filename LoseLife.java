import java.awt.Color;
import java.awt.Graphics;


public class LoseLife extends ItemObj {
	public static final int ITEMWIDTH = 15;
	public static final int ITEMHEIGHT = 3;
	public static final int TYPE = 1;

	public LoseLife(int x, int y) {
		super(x, y, ITEMWIDTH, ITEMHEIGHT, TYPE);
	}
		
	public void lose(GameCourt game) {
		game.setLives((game.getLives() - 1));
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
