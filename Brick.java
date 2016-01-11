import java.awt.Color;
import java.awt.Graphics;

public class Brick extends GameObj {
	public static final int WIDTH = 35;
	public static final int HEIGHT = 10;
	public static final int VEL_Y = 0;
	public static final int VEL_X = 0;
	private int points;
	public boolean alive = true;
	public ItemObj newItem;
	public boolean item1 = false;
	public boolean item2 = false;
	
	public Brick(int pos_x, int pos_y, int type) {
		super(VEL_X, VEL_Y, pos_x, pos_y, WIDTH, HEIGHT, GameCourt.COURT_WIDTH,
				GameCourt.COURT_HEIGHT);
		points = 100;
		
		//creates item inside the brick 
		newItem = new ItemObj(pos_x + (width / 4), pos_y + (height / 4), 
				WIDTH - 20, HEIGHT - 7, type);
		
		if (type == 1) {
			newItem = new LoseLife(pos_x + (width / 4), pos_y + (height / 4));
		}
		
		if (type == 2) {
			newItem = new ExtendPaddle(pos_x + (width / 4), pos_y + (height / 4));
		}
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setAlive() {
		alive = false;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public boolean isItem1() {
		if (newItem.getType() == 1) {
			item1 = true;
		}
		return item1;
	}
	
	public boolean isItem2() {
		if (newItem.getType() == 2) {
			item2 = true;
		}
		return item2;
	}
		
	
	@Override
	public void draw(Graphics g) {
		if (alive) {
			if (this.pos_y > 50) g.setColor(new Color(150, 130, 215));
			else if (this.pos_y <= 50 && this.pos_y > 25) 
				g.setColor(new Color(126,171,227));
			else g.setColor(new Color(230, 140, 170));
			g.fillRect(pos_x, pos_y, WIDTH, HEIGHT);
		}
		//g.setColor(Color.RED);
		//g.fillRect(pos_x, pos_y, width, height);
	}
}
