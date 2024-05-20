package urgency.ui.components;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ChangePassword extends JPanel {

	private static final long serialVersionUID = 4890278966153276205L;
	private JLabel errorMessage; 

	public ChangePassword(MyTextField password1, MyTextField password2, MyButton okbutton, MyButton cancelbutton) {
		this.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Change Password");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        this.add(label);
        
        MyTextField password = password1; 
        password.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/pass.png")));
        password.setHint("Enter new password...");
        this.add(password, "w 60%");
        
        MyTextField againPassword = password2; 
        againPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/pass.png")));
        againPassword.setHint("Enter the password again...");
        this.add(againPassword, "w 60%");
        
        errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    errorMessage.setVisible(false); 
	    
        
        MyButton okButton = okbutton; 
        okButton.setText("Save"); 
        okButton.setBackground(new Color(7, 164, 121));
        okButton.setForeground(new Color(250, 250, 250));
        MyButton cancelButton = cancelbutton; 
        cancelButton.setText("Cancel"); 
        cancelButton.setBackground(new Color(7, 164, 121));
        cancelButton.setForeground(new Color(250, 250, 250));
        
        this.add(okButton, "split 2, grow, left"); 
        this.add(cancelButton, "grow, right"); 
        this.add(errorMessage,"w 10%" );
		
	}
	
	public void showErrorMessage(String text) {
		errorMessage.setVisible(true);
		errorMessage.setText(text);
	}


}
