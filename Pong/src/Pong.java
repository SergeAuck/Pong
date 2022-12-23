import javax.swing.JFrame;

public class Pong extends JFrame {
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;
	public final static String WINDOW_TITLE = "Pong";
	public Pong() {
			setTitle(WINDOW_TITLE);
			setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
			setResizable(false);
			
			add(new PongPanel());
			
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	public static void main(String[] args) {
		
		// using the SwingUtilities.invokeLater() method will delay the GUI creation task 
		// until the initial thread's tasks have been completed. It also ensures the GUI creation takes place inside the EDT
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Pong();
			}
		});
		
		
		
		
		

	}

}
