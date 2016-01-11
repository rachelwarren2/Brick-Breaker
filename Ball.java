import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObj {
	public static int SIZE = 10;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public static final int INIT_POS_X = GameCourt.COURT_WIDTH/2 - SIZE;
	public static final int INIT_POS_Y = 258;

	public Ball(int court_width, int court_height) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE,
				court_width, court_height);
	}
	
	public Ball(int velX, int velY, int posx, int posy, 
			int size, int size2, int courtWidth, int courtHeight) {
	super(velX, velY, posx, posy, 
			SIZE, SIZE, courtWidth, courtHeight);
    }
	
	public int getX() {
		return pos_x;
	}
	
	public void setVX(int v_x) {
		this.v_x = v_x;
	}
	
	public int getY() {
		return pos_y;
	}
	
	public int getVY() {
		return v_y;
	}
	
	public void setVY(int v_y) {
		this.v_y = v_y;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(pos_x, pos_y, width, height);
	}

}
