import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	private final static Color BACKGROUND_COLOR = Color.black;
	private static int TIMER_DELAY = 5;
	
	//constructor
	public PongPanel() {
		setBackground(BACKGROUND_COLOR);
		
		//The Timer class provides functionality to perform a task repeatedly
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.WHITE); DELETE
		//g.fillRect(20, 20, 100, 100); DELETE
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
		repaint();
	}
	
	
	
	public void update() {
		
	}

}
