import java.awt.Color;
import java.awt.Graphics;


public class Paddle extends GameObj {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 10;
	public static final int Y_POS = 270;
	public static int INIT_X_POS = GameCourt.COURT_WIDTH/2 - WIDTH/2;;
	public static final int X_VEL = 0;
	public static final int Y_VEL = 0;

	public Paddle(int court_width, int court_height) {
		super(X_VEL, Y_VEL, INIT_X_POS, Y_POS, WIDTH, HEIGHT, 
				court_width, court_height);
	}
	
	public int getXPOS() {
		return pos_x;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}

	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos_x, pos_y, width, height);
	}
}