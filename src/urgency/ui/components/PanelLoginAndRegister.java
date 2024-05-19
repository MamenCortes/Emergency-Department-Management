package urgency.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends JPanel{

	//Taken from: https://github.com/DJ-Raven/java-swing-login-ui-001/tree/main
	private static final long serialVersionUID = 1L;
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
	private MyTextField txtUser; 
	private MyTextField txtEmailReg; 
	private  MyTextField txtPassReg;
	private MyTextField txtEmailLI; 
	private  MyTextField txtPassLI;
	private MyComboBox<String> roleCB; 
	private JLabel errorMessage; 
	private JLabel errorMessage2; 
    
    //Components
    private MyButton registerButton; 
    private MyButton logInButton; 
    private MyButton cmdForget; 


	public PanelLoginAndRegister(MyButton logIn, MyButton register, MyButton changepassword, MyTextField userNameTxf, 
			MyTextField emailTxf, MyTextField passwordTxf, MyComboBox<String> roleCb, MyTextField emailTxf2, MyTextField passwordTxf2) {
		logInButton = logIn; 
		registerButton = register; 
		
		txtUser = userNameTxf; 
		txtEmailReg = emailTxf; 
		txtPassReg = passwordTxf; 
		roleCB = roleCb; 
		cmdForget = changepassword; 
		txtEmailLI = emailTxf2; 
		txtPassLI = passwordTxf2;  
		setOpaque(false); 
		initComponents(); 
		initLogin();
		initRegister(); 
		
		login.setVisible(false);
		//register.setVisible(true);

	}

	public void setLoginVisible(Boolean bool) {
		login.setVisible(bool);
		register.setVisible(!bool);
	}

	public ArrayList<JButton> getButtons(){
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(logInButton);
		buttons.add(registerButton);
		return buttons; 
	}
	
    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]15[]5[]5[]5[]5[]10[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        
        //txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/user.png")));
        txtUser.setHint("UserName");
        register.add(txtUser, "w 60%");
        
        //txtEmail = new MyTextField();
        txtEmailReg.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/mail.png")));
        txtEmailReg.setHint("Email");
        register.add(txtEmailReg, "w 60%");
        
        //MyPasswordField txtPass = new MyPasswordField();
        //txtPass = new MyTextField();
        txtPassReg.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/pass.png")));
        txtPassReg.setHint("Password");
        register.add(txtPassReg, "w 60%");
        
        //roleCB = new MyComboBox<String>(); 
        roleCB.getModel().setSelectedItem("Select your role..."); 
        register.add(roleCB, "w 60%"); 
        
        errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    errorMessage.setVisible(false); 
	    register.add(errorMessage); 
	    
        registerButton.setText("REGISTER");
        registerButton.setBackground(new Color(7, 164, 121));
        registerButton.setForeground(new Color(250, 250, 250));
        register.add(registerButton, "w 40%, h 40");
        
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[][]15[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        
        //txtEmail = new MyTextField();
        txtEmailLI.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/mail.png")));
        txtEmailLI.setHint("Email");
        login.add(txtEmailLI, "w 60%");
        //MyPasswordField txtPass = new MyPasswordField();
        
        //txtPass = new MyTextField();
        txtPassLI.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/pass.png")));
        txtPassLI.setHint("Password");
        login.add(txtPassLI, "w 60%");
        
        cmdForget.setText("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        
        errorMessage2 = new JLabel(); 
	    errorMessage2.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage2.setForeground(Color.red);
	    errorMessage2.setText("Error message test");
	    errorMessage2.setVisible(false); 
	    login.add(errorMessage2); 
        
        logInButton.setText("LOG IN"); 
        logInButton.setBackground(new Color(7, 164, 121));
        logInButton.setForeground(new Color(250, 250, 250));
        logInButton.setUI(new StyledButtonUI());
        login.add(logInButton, "w 40%, h 40");
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }
    
	public void showErrorMessage(String text) {
		errorMessage.setVisible(true);
		errorMessage.setText(text);
		errorMessage2.setVisible(true);
		errorMessage2.setText(text);
	}
	
	public void hideErrorMessage() {
		errorMessage.setVisible(false);
		errorMessage2.setVisible(false);
	}

}
