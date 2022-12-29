import java.awt.Color;

public class Ball extends Sprite{
	private final static Color BALL_COLOUR = Color.WHITE;
	private final static int BALL_WIDTH = 25;
	private final static int BALL_HEIGHT = 25;
	//private final static int BALL_MOVEMENT_SPEED = 2;
	
	public Ball ( int panelWidth, int panelHeight) {
		setWidth(BALL_WIDTH);
		setHeight(BALL_HEIGHT);
		setColour(BALL_COLOUR);
		setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
		resetToInitialPositions();
	}
	
	//just a comment to check if new branch is working
	
	
	
	
	
	

}
