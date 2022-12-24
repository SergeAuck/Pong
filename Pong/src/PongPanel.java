import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
//import java.awt.

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	private final static Color BACKGROUND_COLOR = Color.black;
	private static int TIMER_DELAY = 5;
	Ball ball;
	//private static boolean gameInitialised = false; not required anymore
	GameState gameState = GameState.INITIALISING;
	
	Paddle paddle1;
	Paddle paddle2;
		
	//constructor
	public PongPanel() {
		setBackground(BACKGROUND_COLOR);
		
		//The Timer class provides functionality to perform a task repeatedly
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
	}
	
	//creates the ball object
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if(gameState != GameState.INITIALISING) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
		}
		//g.setColor(Color.WHITE); DELETE
		//g.fillRect(20, 20, 100, 100); DELETE
	}
		
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() /2, 0, getWidth()/2, getHeight());
		g2d.dispose();
	}
	

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//where to put this update?
		update();
		// repaint been implemented because we use JPanel/JFrame to draw our graphics
		repaint();
	}
	
	
	
	public void update() {
		switch(gameState) {
		case INITIALISING : {
			createObjects();
			gameState = GameState.PLAYING;
			break;
		}
		case PLAYING: {
			break;
		}
		case GAMEOVER: {
			break;
		}
		}
	}

}
