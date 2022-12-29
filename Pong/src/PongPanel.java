import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
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
	private final static int BALL_MOVEMENT_SPEED = 2; //not sure if it is a correct place
	private final static int POINTS_TO_WIN = 3;
	int player1Score =0, player2Score = 0;
	Player gameWinner;
	
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
		addKeyListener(this); //program is listening to the keyboard
		setFocusable(true); //??
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
			paintScores(g);
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
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setyVelocity(-1);
		}
		else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(1);
		}
		
		if(event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setyVelocity(-1);
		}
		else if(event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(1);
		}
				
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setyVelocity(0);
		}
		
		if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setyVelocity(0);
		}
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
			ball.setxVelocity(BALL_MOVEMENT_SPEED);
			ball.setyVelocity(BALL_MOVEMENT_SPEED);
			break;
		}
		case PLAYING: {
			moveObject(paddle1);
			moveObject(paddle2);
			moveObject(ball); //move ball
			checkWallBounce(); //check for wall bounce
			checkPaddleBounce(); //check for interactions with paddles
			checkWin();
			break;
		}
		case GAMEOVER: {
			break;
		}
		}
	}
	
	public void moveObject(Sprite obj) {
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(),getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(),getHeight());
	}
	
	public void checkWallBounce() {
		if(ball.getxPosition() <= 0) {
			//hit the left side of the screen
			//ball.setxVelocity(-ball.getxVelocity());  - to bounce the ball. not needed
			addScore(Player.Two);
			resetBall();
		}
		else if (ball.getxPosition() >= getWidth() - ball.getWidth()) {
			//hit the right side of the screen
			//ball.setxVelocity(-ball.getxVelocity());
			addScore(Player.One);
			resetBall();
		}
		if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
			//hit top or bottom of screen
			ball.setyVelocity(-ball.getyVelocity());
		}
	}
	
	public void resetBall() {
		ball.resetToInitialPositions();
	}
	
	private void checkPaddleBounce() {
		if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setxVelocity(BALL_MOVEMENT_SPEED);
		}
		if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setxVelocity(-BALL_MOVEMENT_SPEED);
		}
	}
	
	public void addScore(Player player) {
		if(player == Player.One) {
			player1Score++;
		}
		else if(player == Player.Two) {
			player2Score++;
		}
		
	}
	
	public void checkWin() {
		if(player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GAMEOVER;
		}
		else if(player2Score == 3) {
			gameWinner = Player.Two;
			gameState = GameState.GAMEOVER;
		}
	}
	
	private void paintScores(Graphics g) {
		int xPadding = 100;
		int yPadding = 100;
		int fontSize = 50;
		Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, xPadding, yPadding);
		g.drawString(rightScore, xPadding, yPadding);
	}

}
