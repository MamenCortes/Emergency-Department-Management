package urgency.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import urgency.db.jpa.JPAUserManager;
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
		roleCB.addItem("Nurse");
		roleCB.addItem("Manager");
		
		panelCoverLogIn = new PanelCoverLogIn(changePanels); 
		panelLogIn = new PanelLoginAndRegister(applyLogIn, applyRegister, changePassword,
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
			if(logIn()) {
				resetPanel();
			}
			
		}else if(e.getSource() == applyRegister) {
			System.out.println("Register");
			if(register()) {
				showLogIn(); 
			}
			
		}else if(e.getSource() == changePassword) {
			if(canChangePassword()) {
				showChangePasswordPane(appMenu);
			}
			
		}
	}
    

    private void showChangePasswordPane(JFrame parentFrame) {
    	String emailString = emailTxFLogIn.getText(); 
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
                	//TODO get User
                	//User u = appMenu.jpaUserMan.getUser(); 
                	appMenu.jpaUserMan.changePassword(null, pass2);
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
    	
    
    
    private Boolean logIn() {
    	String email = emailTxFLogIn.getText(); 
    	String password = passwordTxFLogIn.getText(); 
    	if(!email.isBlank() && !password.isBlank()) {
    		
    		User user = appMenu.jpaUserMan.login(email, password); 
    		System.out.println(user);
        	
        	//User is null if it doesn't exist
        	if(user != null) {
        		appMenu.setUser(user); 
        		return true; 
        	}else {
        		panelLogIn.showErrorMessage("Invalid user or password");
        		return false; 
        	}
    		
    	}else {
    		showErrorMessage("Complete all fields");
    		return false; 
    	}
    }
    
    private Boolean register() {
    	String email = emailTxF.getText(); 
    	String password = passwordTxF.getText(); 
    	String roleText = ""; 
    	if(roleCB.getModel().getSelectedItem() != "Select your role...") {
    		roleText = roleCB.getModel().getSelectedItem().toString(); 
    	}else {
    		panelLogIn.showErrorMessage("Select a role");
    		return false; 
    	}
    	
    	//Register a user
    	try {
    		Role role = appMenu.jpaRoleMan.getRole(roleText); 
    		System.out.println("Password valid = "+validatePassword(password));
    		if(validateEmail(email) && validatePassword(password)) {
    			if(appMenu.jpaUserMan.register(new User(email, password, role))) {
    				return true; 
    			}else {
    				showErrorMessage("User already exists");
        			return false; 
    			}
    		}else {
    			return false; 
    		}
    		
		} catch (NoSuchAlgorithmException e) {
			showErrorMessage("Invalid password"); 
			return false; 
		}   	
    }

    public Boolean canChangePassword() {
    	String email = emailTxFLogIn.getText();
    	if(email != null && !email.isBlank()){
    		Boolean isUser = appMenu.jpaUserMan.isUser(email); 
    		if(isUser) {
    			return true; 
    		}else {
    			showErrorMessage("Invalid user or password");
    			return false; 
    		}
    	}else {
    		showErrorMessage("Write the email first");
    		return false; 
    	}
    	
    }
    
    private Boolean validatePassword(String password) {
		boolean passwordVacia = (Objects.isNull(password)) || password.isEmpty();
		boolean goodPassword=false;
		System.out.println("password vacía "+passwordVacia);
		if(!passwordVacia && password.length() >= 8) {
			for(int i=0; i<password.length(); i++) {
				
				//The password must contain at least one number
				if(Character.isDigit(password.charAt(i))) {
				goodPassword = true;
				}
			}
			if(!goodPassword) {
				showErrorMessage("The password must contain at least one number.");
				return false; 
			}
		 }else {
			 showErrorMessage("Password's minimum lenght is of 8 characters");
			 return false; 
		 }
		return true;
		
	 }
    
    
    
    private void showErrorMessage(String text) {
    	panelLogIn.showErrorMessage(text); 
    }
    
    private void resetPanel() {
    	emailTxF.setText(null);
    	emailTxFLogIn.setText(null);
    	passwordTxF.setText(null);
    	passwordTxFLogIn.setText(null);
    	panelLogIn.hideErrorMessage();
    }
    
	public Boolean validateEmail(String email) {
    	if(!email.isBlank() && email.contains("@")) {
    		String[] emailSplit = email.split("@"); 
        	if(emailSplit.length >1 && emailSplit[1].equals("hospital.com")){
        		return true; 
        	}
    	}
    	//System.out.println("Valid email? "+validEmail);
    	showErrorMessage("Invalid Email");
    	return false; 
    }

}
