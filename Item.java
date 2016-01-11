import java.awt.Graphics;


public interface Item {
	
	//draws the item
	void draw(Graphics g);
	
	//drops item from brick
	void drop();
	
	//sets type of item
	void setType(int type);
	
	//gets type of item
	int getType();
	
	boolean intersects(GameObj game);

}
