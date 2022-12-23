import javax.swing.JFrame;

public class Pong extends JFrame {
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;
	public final static String WINDOW_TITLE = "Pong";
	public Pong() {
			setTitle(WINDOW_TITLE);
			setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Pong();
		
		
		

	}

}
