package urgency.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private UserLogIn logInPanel;
	private RecepcionistMenu recepcionistMenu; 
	private ManagerMenu managerMenu; 


	/**
	 * Create the frame.
	 */
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 436);
		logInPanel = new UserLogIn(); 
		logInPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		recepcionistMenu = new RecepcionistMenu(); 
		managerMenu = new ManagerMenu(); 

		setContentPane(managerMenu);
	}

}
