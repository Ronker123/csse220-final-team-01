package finalProject2026;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Main {
		
	public static JFrame f = new JFrame();
	
	public static void main(String[] args) {
		// gets scale before running to decide whether to run:
		double xScale = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .getDefaultTransform()
                .getScaleX();
		
		if(xScale != 1.0) {
			javax.swing.JOptionPane.showMessageDialog(new JFrame(), "Window Display Zoom Must Be 100%");
			System.exit(0);
		}
		
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
