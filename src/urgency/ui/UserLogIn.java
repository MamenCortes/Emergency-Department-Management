package urgency.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;
import urgency.ui.components.ChangePassword;
import urgency.ui.components.MyButton;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.ui.components.PanelCoverLogIn;
import urgency.ui.components.PanelLoginAndRegister;

public class UserLogIn extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private PanelCoverLogIn panelCoverLogIn; 
	private PanelLoginAndRegister panelLogIn; 
	private MyButton applyLogIn; 
	private MyButton applyRegister; 
	private MyButton changePanels; 
	private MyButton changePassword; 
	private Application appMenu; 
	private MyTextField userNameTxF; 
	private MyTextField emailTxF; 
	private MyTextField passwordTxF; 
	private MyTextField emailTxFLogIn; 
	private MyTextField passwordTxFLogIn; 
	private MyComboBox<String> roleCB; 

	/**
	 * Create the panel.
	 */
	public UserLogIn(Application appMenu) {
		this.appMenu = appMenu; 
		this.setLayout(new MigLayout("fill, inset 0, gap 0", "[30]0px[70:pref]", "[]")); 
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
		changePassword = new MyButton(); 
		changePassword.addActionListener(this);
		
		userNameTxF = new MyTextField(); 
		userNameTxF.addActionListener(this);
		emailTxF = new MyTextField(); 
		emailTxF.addActionListener(this);
		passwordTxF = new MyTextField(); 
		passwordTxF.addActionListener(this);
		emailTxFLogIn = new MyTextField(); 
		emailTxFLogIn.addActionListener(this);
		passwordTxFLogIn = new MyTextField(); 
		passwordTxFLogIn.addActionListener(this);
		roleCB = new MyComboBox<String>(); 
		roleCB.addActionListener(this);
		//roleCB.addItem("Doctor");
		roleCB.addItem("Recepcionist");
		roleCB.addItem("Triage Nurse");
		roleCB.addItem("Manager");
		
		panelCoverLogIn = new PanelCoverLogIn(changePanels); 
		panelLogIn = new PanelLoginAndRegister(applyLogIn, applyRegister, changePassword, userNameTxF,
				emailTxF, passwordTxF, roleCB, emailTxFLogIn, passwordTxFLogIn); 
		//Dimensiones del panel.
		
		this.add(panelCoverLogIn, "grow"); 
		this.add(panelLogIn, "grow"); 

	}
	
	private void showLogIn() {
		System.out.println("Show Log IN");
		panelLogIn.setLoginVisible(true);
	}
	private void showRegister() {
		panelLogIn.setLoginVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == changePanels) {
			panelLogIn.hideErrorMessage();
			if(changePanels.getText()=="REGISTER") {
				changePanels.setText("LOG IN"); 
				showRegister();
			}else {
				changePanels.setText("REGISTER");
				showLogIn(); 
			}
		}else if(e.getSource() == applyLogIn) {
			System.out.println("LogIn");
			logIn();
			
		}else if(e.getSource() == applyRegister) {
			System.out.println("Register");
			register(); 
			
		}else if(e.getSource() == changePassword) {
			if(canChangePassword()) {
				showChangePasswordPane(appMenu);
			}else {
				panelLogIn.showErrorMessage("Invalid user or password");
			}
			
		}
	}
    

    private static void showChangePasswordPane(JFrame parentFrame) {
    	MyTextField password1 = new MyTextField(); 
    	MyTextField password2 = new MyTextField(); 
        MyButton okButton = new MyButton("Aceptar");
        MyButton cancelButton = new MyButton("Cancelar");
        
        ChangePassword panel = new ChangePassword(password1, password2, okButton, cancelButton); 
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(400, 300));

        // Crear el JDialog para contener el panel personalizado
        JDialog dialog = new JDialog(parentFrame, "Change Password", true);
        dialog.getContentPane().add(panel);
        dialog.getContentPane().setBackground(Color.white);
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        //dialog.setSize(400, 200);

        // Añadir acción a los botones
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String pass1 = password1.getText();
            	String pass2 = password2.getText(); 
                if(pass1 != null && pass1.equals(pass2) && !pass1.isBlank()) {
                	dialog.dispose();
                }else{
                	panel.showErrorMessage("Passwords do not match");
                }
                
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }
    
    private void logIn() {
    	String email = emailTxFLogIn.getText(); 
    	String password = passwordTxFLogIn.getText(); 
    	//TODO create userManager in connectionJPA
    	User user = appMenu.userMan.login(email, password); 
    	if(user != null) {
    		appMenu.setUser(user); 
    	}else {
    		panelLogIn.showErrorMessage("Invalid user or password");
    	}
    }
    
    private void register() {
    	String userName = userNameTxF.getText(); 
    	String email = emailTxF.getText(); 
    	String password = passwordTxF.getText(); 
    	String role; 
    	if(roleCB.getModel().getSelectedItem() != null) {
    		role = roleCB.getModel().getSelectedItem().toString(); 
    	}else {
    		panelLogIn.showErrorMessage("Select a role");
    	}
    	
    	//TODO create userManager in connectionJPA
    	try {
			appMenu.userMan.register(new User(email, password, new Role(role)));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			panelLogIn.showErrorMessage();
			e.printStackTrace();
		}
    	showLogIn(); 
    	
    }

    public Boolean canChangePassword() {
    	String email = emailTxFLogIn.getText();
    	if(email != null && !email.isBlank()){
    		//return appMenu.userMan.getUserManager().checkUser(String email); 
    		return true; 
    	}else {
    		panelLogIn.showErrorMessage("Write the email first");
    		return false; 
    	}
    	
    }

}
