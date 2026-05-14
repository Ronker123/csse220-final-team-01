package finalProject2026;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter{
	 public boolean[] buttons = new boolean[3];
	 public boolean[] depricated = {false, false, false};
	
	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		switch(button) {
		case MouseEvent.BUTTON1:
			buttons[0] = true;
			break;
		case MouseEvent.BUTTON2:
			buttons[1] = true;
			break;
		case MouseEvent.BUTTON3:
			buttons[2] = true;
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int button = e.getButton();
		switch(button) {
		case MouseEvent.BUTTON1:
			buttons[0] = false;
			depricated[0] = false;
			break;
		case MouseEvent.BUTTON2:
			buttons[1] = false;
			depricated[1] = false;
			break;
		case MouseEvent.BUTTON3:
			buttons[2] = false;
			depricated[2] = false;
			break;
		}
	}
}
