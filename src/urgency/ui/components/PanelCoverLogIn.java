package urgency.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class PanelCoverLogIn extends JPanel{

	private static final long serialVersionUID = 1L;

	private MigLayout layout;
	
	private JLabel title;
    private JLabel description;
    private JLabel description1;
    private JButton button;
    JLabel picLabel; 
    private ActionListener actionListener;
    private PanelLoginAndRegister logInRegister; 

	
	public PanelCoverLogIn(MyButton changePanels) {
		button = changePanels; 
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]10[]10[]push"); 
        setLayout(layout);
        init();

	}

	public void setActionListener(ActionListener al) {
		actionListener = al; 
	}
	public void setLogInRegister(PanelLoginAndRegister lir) {
		this.logInRegister = lir; 
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Pintar un degradado verde
        GradientPaint gra = new GradientPaint(0, 0, new Color(35, 166, 97), 0, getHeight(), new Color(22, 116, 66));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
	}
	
	public ArrayList<JButton> getButtons(){
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(button);
		return buttons; 
	}
	
	
	private void init() {	

        picLabel = new JLabel(); 
        picLabel.setIcon(new ImageIcon(PanelCoverLogIn.class.getResource("/urgency/ui/icon/wardiere_3.png")));
        add(picLabel); 
        
        title = new JLabel("Welcome Back!");
        title.setFont(new Font("sansserif", 1, 25));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        
        
        /*description = new JLabel("To keep connected with us please");
        description.setForeground(new Color(245, 245, 245));
        add(description);
        description1 = new JLabel("login with your personal info");
        description1.setForeground(new Color(245, 245, 245));
        add(description1);*/
        
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(35, 166, 97));
        button.setText("REGISTER");
        button.setUI(new StyledButtonUI());
        add(button, "w 60%, h 40");
	}


}
