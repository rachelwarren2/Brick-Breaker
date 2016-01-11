/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Paddle paddle; 
	private Ball ball; 
	//private GameCourt game;
	
	public int rows = 4;
	public int cols = 6;
	public String s = null;
	private ArrayList<Brick> bricks;
	private ArrayList<LoseLife> item1;
	private ArrayList<ExtendPaddle> item2;
	
	public int lives;
	public int score;
	int high1 = 0;
	int high2 = 0;

	public boolean gameOver = false;
	public String input;
	Map<Integer, String> topScores = new TreeMap<>();

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 300;
	public static final int COURT_HEIGHT = 300;
	public static final int Paddle_VELOCITY = 5;
	public static final int Ball_Velx = 3;
	public static final int Ball_Vely = 2;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	//private String saveDataPath;
	private Timer timer;
	

	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		input = JOptionPane.showInputDialog("Please input name");
		readScores();
		
		

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the paddle to move as long
		// as an arrow key is pressed, by changing the paddle's
		// velocity accordingly. (The tick method below actually
		// moves the square.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					paddle.v_x = -Paddle_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					paddle.v_x = Paddle_VELOCITY;
			}

			public void keyReleased(KeyEvent e) {
				paddle.v_x = 0;
			}
		});

		this.status = status;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getLives() {
		return lives;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	
	public void reset() {
		paddle = new Paddle(COURT_WIDTH, COURT_HEIGHT);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
		item1 = new ArrayList<>();
		item2 = new ArrayList<>();
		bricks = new ArrayList<Brick>();
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j++) {
				Random rand = new Random();
				int type = rand.nextInt(3);
				Brick b = new Brick((i+1) *55, (j+1)*15, type);
				bricks.add(b);
				addItem(b.newItem);
			}
		}
		
		ball.v_x = Ball_Velx;
		ball.v_y = Ball_Vely;
		score = 0;
		lives = 3;
		status.setText("Score: " + score + " " + "Lives: " + lives);
		playing = true;
		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	public void resetLostLife() {
		paddle = new Paddle(COURT_WIDTH, COURT_HEIGHT);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
		ball.v_x = Ball_Velx;
    	ball.v_y = Ball_Vely;
    	status.setText("Score: " + score + " " + "Lives: " + lives);
		playing = true;
		requestFocusInWindow();
	}
	
	public void help() {
		timer.stop();
	}
	
	public void quitHelp() {
		timer.start();
		requestFocusInWindow();
	}
	
	//reading top 2 scores from the file and put them on leader board
	public void writeScores() {
		try {
		    FileWriter output = new FileWriter("highscores.txt");
		    BufferedWriter out = new BufferedWriter(output);
			out.write(topScores.get(high1) + " " +  high1);
			out.newLine();
			out.write(topScores.get(high2) + " " + high2);
			out.flush();
			out.close();
		} catch (IOException e) {
		    e.printStackTrace();
	    }
	}
	
	public String sc() {
		s = topScores.get(high1) + ": " + high1 + 
				"\n" + topScores.get(high2) + ": " + high2;
		return s;
	}
	
	public void readScores() {
		String[] cols = null;
		try {
			BufferedReader read = new BufferedReader
		    		(new InputStreamReader(new FileInputStream("highscores.txt")));
		    String line = read.readLine();
		    cols = line.split(" ");
		    high1 = Integer.valueOf(cols[1]);
		    topScores.put(high1, cols[0]);
		    line = read.readLine();
		    cols = line.split(" ");
		    high2 = Integer.valueOf(cols[1]);
		    topScores.put(high2, cols[0]);
		    read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addItem(ItemObj item) {
		if (item.getType() == 1) {
			item1.add((LoseLife) item);
		}
		if (item.getType() == 2) {
			item2.add((ExtendPaddle) item);
		}
	}
	
	/*public void dropItem(ItemObj item) {
		item.drop();
		/*if (item.intersects(paddle)) {
			changeState(item);
		}
		if (item.getType() == 1 && item.getY() >= COURT_HEIGHT) {
			item1.remove(item);
	    }
		else if (item.getType() == 2 && item.getY() >= COURT_HEIGHT) {
			item2.remove(item);
		}
	}*/
	    
	
	public void changeState(ItemObj item) {
		item.drop();
		    if (item.getType() == 1) {
		    	if (item.intersects(paddle)) {
			        ((LoseLife) item).lose(this);
			        
			        item1.remove(item);
		        }
		    	else if (item.getY() >= COURT_HEIGHT) {
		    		item1.remove(item);
		    	}
		     }
		    if (item.getType() == 2) {
		    	 if (item.intersects(paddle)) {
			        ((ExtendPaddle) item).changePaddle(paddle);
			        item2.remove(item);
		         }
		    	 else if (item.getY() >= COURT_HEIGHT) {
		    		item2.remove(item);
		    	 }
		     }
     }


	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
			
	void tick() {
		if (playing) {
			// advance the square and ball in their
			// current direction.
			paddle.move();
			ball.move();
			// make the ball bounce off walls...
			ball.bounce(ball.hitWall());
			// ...and the paddle... added collision detection
			if (ball.intersects(paddle)) {
				ball.setVY(-ball.getVY());
				ball.bounce(ball.hitObj(paddle));
				}
			//... and the bricks... added collision detection
			for (int i = 0; i < bricks.size(); i++) {
				if(!bricks.get(i).getAlive()) {
				   if (bricks.get(i).isItem1() || bricks.get(i).isItem2()) {
						   changeState(bricks.get(i).newItem);
					   }
				   }
				
					if (bricks.get(i).getAlive()) {
						if (ball.intersects(bricks.get(i))) {
							if (ball.pos_x == bricks.get(i).pos_x + bricks.get(i).width || 
									ball.pos_x + ball.width/2 == bricks.get(i).pos_x) {
								ball = new Ball(-ball.v_x, -ball.v_y,
										ball.pos_x, ball.pos_y, ball.height,
										ball.width, COURT_WIDTH, COURT_HEIGHT);
							}
							else {
							    ball = new Ball(ball.v_x, -ball.v_y,
									    ball.pos_x, ball.pos_y, ball.height,
									    ball.width, COURT_WIDTH, COURT_HEIGHT);
							}
							score += 100;
							status.setText("Score: " + score + " " + "Lives: " + lives);
							bricks.get(i).setAlive();
					    }
				    }
			}
			
			// check for the game end conditions
			int x = ball.getX();
			int y = ball.getY();
			int paddlex = paddle.getXPOS();
			int plus = paddlex + 40;
			int minus = paddlex - 40;
			
            if (y >= 290 && (x > plus || x < minus)) {
            	playing = false;
				lives -= 1;
				status.setText("You lost a life");
			}
            if (lives <= 0) {
            	playing = false;
            	gameOver = true;
            	status.setText("You Lost, " + "Final Score: " + score);
            }
            if (score == 2400) {
            	playing = false;
            	gameOver = true;
            	status.setText("You Won!!! " + "Final Score: " + score);
            }
            if (gameOver) {
            	if (score >= high1 ) {
            		high2 = high1;
            		high1 = score;
            		topScores.put(high1, input);
            	}
            	else if (score >= high2 && score < high1) {
            		high2 = score;
            		topScores.put(high2, input);
            	}
            	else {
            		
            	}
            }
			// update the display
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paddle.draw(g);
		ball.draw(g);
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).draw(g);
		}
		
		for (int i = 0; i < item1.size(); i++) {
			item1.get(i).draw(g);
		}
		
		for (int i = 0; i < item2.size(); i++) {
			item2.get(i).draw(g);
		}
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
