/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		// NOTE : recall that the 'final' keyword notes inmutability
		// even for local variables.

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		final JFrame frame = new JFrame("Brick Breaker");
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);

		// Main playing area
		final GameCourt court = new GameCourt(status);
		frame.add(court, BorderLayout.CENTER);

		// Start button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);
		
		final JPanel instructions = new JPanel();
		frame.add(instructions, BorderLayout.EAST);

		
		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton start = new JButton("Start Over");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(start);
		
		final JButton cont = new JButton("Continue");
		cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.resetLostLife();
			}
		});
		control_panel.add(cont);
		
		final JButton help = new JButton("Instructions");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.help();
				JOptionPane.showMessageDialog(instructions, 
			    "Instructions:" +
				"\n- Move left/right arrow keys to move paddle" +
			    "\n- If the paddle does not make contact with the ball a life is lost" +
				"\n- If you lose a life, press continue to rest the paddle and ball locations" +
			    "\n- After losing 3 lives the game is over, "
			    + "but you can press Start Over for a new game" +
			    "\n- Each time you hit a brick you gain that amount of points" +
			    "\n- The red powerUps make you lose a life, so try not to catch these" +
			    "\n- the blue powerUps extend the paddle size by 1" +
			    "\n- You win when you have hit every brick!");
				if(JOptionPane.OK_CANCEL_OPTION == JOptionPane.OK_CANCEL_OPTION) {
	      			  court.quitHelp();//game resumes
	      		  }
			}
		});
		instructions.add(help);
		
		final JButton scores = new JButton("High Scores");
		scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.help();
				court.writeScores();
				String s = court.sc();
				JOptionPane.showMessageDialog(instructions, s, 
						"High Scores", JOptionPane.YES_NO_CANCEL_OPTION);
				if(JOptionPane.OK_CANCEL_OPTION == JOptionPane.OK_CANCEL_OPTION) {
	      			  court.quitHelp();
	      		  }
			}
		});
		instructions.add(scores);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
