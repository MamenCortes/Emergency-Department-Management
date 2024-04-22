package urgency.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    
    //Components
    private JButton signUpButton; 
    private JButton signInButton; 
    private ActionListener actionListener; 
    private PanelCover panelCover; 


	public PanelLoginAndRegister() {
		setOpaque(false); 
		initComponents(); 
		initLogin();
		initRegister(); 
		
		login.setVisible(false);
		register.setVisible(true);
		
		
		//setLoginVisible(false); 
		//setRegisterVisible(true); 

	}
	public void setPanelCover(PanelCover cover) {
		this.panelCover = cover; 
	}

	public void setLoginVisible(Boolean bool) {
		login.setVisible(bool);
		register.setVisible(!bool);
	}


	public void setActionListener(ActionListener al) {
		actionListener = al; 
	}
	public ArrayList<JButton> getButtons(){
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(signInButton);
		buttons.add(signUpButton);
		return buttons; 
	}
	
    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/user.png")));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        //MyPasswordField txtPass = new MyPasswordField();
        MyTextField txtPass = new MyTextField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        signUpButton = new JButton();
        signUpButton.setBackground(new Color(7, 164, 121));
        signUpButton.setForeground(new Color(250, 250, 250));
        signUpButton.setText("SIGN UP");
		signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.actionPerformed(e);
				
			}
			
		});
        register.add(signUpButton, "w 40%, h 40");
        
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        //MyPasswordField txtPass = new MyPasswordField();
        MyTextField txtPass = new MyTextField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        //cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        signInButton = new JButton();
        signInButton.setBackground(new Color(7, 164, 121));
        signInButton.setForeground(new Color(250, 250, 250));
        signInButton.setText("SIGN IN");
        signInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.actionPerformed(e);
				
			}
			
		});
        login.add(signInButton, "w 40%, h 40");
    }
    
    @SuppressWarnings("unchecked")
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
    }// </editor-fold>//GEN-END:initComponents

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(signUpButton)) {
			System.out.println("SignUp");
			
		}else if(e.getSource().equals(signInButton)){
			System.out.println("SignIn");
			
		}
		
	}

}
