package urgency.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import urgency.ui.components.MyButton;
import urgency.ui.components.PanelCoverLogIn;
import urgency.ui.components.PanelLoginAndRegister;

public class UserLogIn extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private PanelCoverLogIn panelCoverLogIn; 
	private PanelLoginAndRegister panelLogIn; 
	private MyButton applyLogIn; 
	private MyButton applyRegister; 
	private MyButton changePanels; 
	private Application appMenu; 

	/**
	 * Create the panel.
	 */
	public UserLogIn(Application appMenu) {
		this.appMenu = appMenu; 
		this.setLayout(new MigLayout("fill, inset 0, gap 0", "[30]0px[70:pref]", "[]")); 
		//this.setLayout(new MigLayout("fill, inset 0, debug"));  
		init();
	}
	
	private void init() {
		//Initialize buttons
		applyLogIn = new MyButton();
		applyLogIn.addActionListener(this);
		applyRegister = new MyButton(); 
		applyRegister.addActionListener(this);
		changePanels = new MyButton(); 
		changePanels.addActionListener(this);
		
		panelCoverLogIn = new PanelCoverLogIn(changePanels); 
		panelLogIn = new PanelLoginAndRegister(applyLogIn, applyRegister); 
		//Dimensiones del panel.
		
		this.add(panelCoverLogIn, "grow"); 
		this.add(panelLogIn, "grow"); 

	}
	
	private void showLogIn() {
		panelLogIn.setLoginVisible(true);
	}
	private void showRegister() {
		panelLogIn.setLoginVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changePanels) {
			if(changePanels.getText()=="LOG IN") {
				//System.out.println("Log In --> Register");
				changePanels.setText("REGISTER"); 
				showRegister();
			}else {
				//System.out.println("Register --> LogIn");
				changePanels.setText("LOG IN");
				showLogIn(); 
			}
		}else if(e.getSource() == applyLogIn) {
			//System.out.println("LogIn");
			//appMenu.changeToRecepcionistMenu();
			appMenu.changeToNurseView();
			
		}else if(e.getSource() == applyRegister) {
			//System.out.println("Register");
			appMenu.changeToManagerMenu(); 
		}

		
	}

}
