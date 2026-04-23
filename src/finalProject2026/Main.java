package finalProject2026;

import javax.swing.JFrame;

public class Main {
		
	public static JFrame f = new JFrame();
	
	public static void main(String[] args) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setUndecorated(true);
		f.setResizable(false);
		f.setTitle("finalProject2026");

		Display d = new Display();
		f.add(d);

		f.pack();

		f.setLocationRelativeTo(null);
		f.setVisible(true);

		d.startGameThread();

	}
	
}
