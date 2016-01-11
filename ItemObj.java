
import java.awt.Color;
import java.awt.Graphics;


public class ItemObj implements Item {
	public int type;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public ItemObj(int x, int y, int width, int height, int type) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	public void drop() {
		y += 3;
	}

	public void setType(int type) {
		this.type = type;
		
	}

	public int getType() {
		return type;
	}
	
	public boolean intersects(GameObj game) {
		return (x + width >= game.pos_x
				&& y + height >= game.pos_y
				&& game.pos_x + game.width >= x 
				&& game.pos_y + game.height >= y);
	}
	
	public void draw(Graphics g) {
	}
	
	public int getY(){
		return y;
	}
	
	public int getX() {
		return x;
	}

}
