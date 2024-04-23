package urgency.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class UserLogIn extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private PanelCoverLogIn panelCoverLogIn; 
	private PanelLoginAndRegister panelLogIn; 
	private final double addSize = 30;
	private final double coverSize = 30;
	private final double logInSize = 60; 
	private List<JButton> buttons; 

	/**
	 * Create the panel.
	 */
	public UserLogIn() {
		this.setLayout(new MigLayout("fill, inset 0, debug, gap 0", "[30]0px[70:pref]", "[]")); 
		//this.setLayout(new MigLayout("fill, inset 0, debug"));  
		buttons = new ArrayList<JButton>(); 
		init();
	}
	
	private void init() {
		panelCoverLogIn = new PanelCoverLogIn(); 
		panelLogIn = new PanelLoginAndRegister(); 
		//Dimensiones del panel.
		
		this.add(panelCoverLogIn, "grow"); 
		this.add(panelLogIn, "grow"); 
		
		panelLogIn.setActionListener(this);
		panelLogIn.setPanelCover(panelCoverLogIn);
		panelCoverLogIn.setActionListener(this);
		panelCoverLogIn.setLogInRegister(panelLogIn);
		
		
		buttons.addAll(panelCoverLogIn.getButtons());
		buttons.addAll(panelLogIn.getButtons());
		//System.out.println(buttons);
	}
	
	private void showLogIn() {
		panelLogIn.setLoginVisible(true);
	}
	private void showRegister() {
		panelLogIn.setLoginVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
		if(e.getSource().equals(buttons.get(0))){
			System.out.println("0: "+buttons.get(0).getText());
		}else if(e.getSource().equals(buttons.get(1))) {
			System.out.println("1: "+buttons.get(1).getText());
		}else if(e.getSource().equals(buttons.get(2))) {
			System.out.println("2: "+buttons.get(2).getText());
		}
		
		
	}

}
