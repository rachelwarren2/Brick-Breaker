import java.awt.Color;
import java.awt.Graphics;


public class ExtendPaddle extends ItemObj {
	public static final int ITEMWIDTH = 15;
	public static final int ITEMHEIGHT = 3;
	public static final int TYPE = 2;

	public ExtendPaddle(int x, int y) {
		super(x, y, ITEMWIDTH, ITEMHEIGHT, TYPE);
	}
	
	public void changePaddle(Paddle p) {
		p.setWidth(p.getWidth() + 1);
	}
	
	@Override
    public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
