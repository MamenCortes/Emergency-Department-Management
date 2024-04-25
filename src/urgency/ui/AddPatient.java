package urgency.ui;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import urgency.ui.components.PanelCoverForMenu;
import urgency.ui.components.patientForm;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

public class AddPatient extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Color titleColor2 = new Color(24, 116, 67);
	private final Color titleColor = new Color(7, 164, 121); 
	private JLabel title; 
	private JLabel option1; 
	private JLabel option2; 
	private JLabel option3; 
	private JPanel patientForm1; 
	private JPanel patientForm2; 
	private JPanel patientForm3; 

	public AddPatient() {
		//TODO que la letra aumente con la pantalla
		this.setLayout(new MigLayout("fill, inset 20, gap 0", "[][][]", "[][][][][][][][][][]"));
		
		/*JTextPane lbl = new JTextPane();
		lbl.setBackground(Color.white);
		lbl.setText("Urgency Management");
		lbl.setAlignmentY(TOP_ALIGNMENT);
		lbl.insertIcon(new ImageIcon(AddPatient.class.getResource("/urgency/ui/icon/medical_logo_03.png")));
		lbl.setForeground(new Color(24, 116, 67));
		lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(lbl, "cell 0 0 5 1, top, grow");*/
		
		//Add Title
		title = new JLabel(" Add Patient");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/medical-chechup.png")));
		add(title, "cell 0 0 3 1, alignx left");
		
		
		//Add Options
		option1 = new JLabel("    1. General details");
		option1.setForeground(titleColor2);
		option1.setFont(new Font("sansserif", 1, 12));
		option1.setAlignmentX(CENTER_ALIGNMENT);
		add(option1, "cell 0 1, alignx center, grow");

		
		option2 = new JLabel("    2. Medical Details");
		option2.setForeground(titleColor2);
		option2.setFont(new Font("sansserif", 1, 12));
		option2.setAlignmentX(CENTER_ALIGNMENT);
		add(option2, "cell 0 2, alignx center, grow");
		enableBackground(option2);
		
		option3 = new JLabel("    3. Urgency Details");
		option3.setForeground(titleColor2);
		option3.setFont(new Font("sansserif", 1, 12));
		option3.setAlignmentX(CENTER_ALIGNMENT);
		add(option3, "cell 0 3, alignx center, grow");
		
		patientForm1 = new patientForm(); 
		//patientForm1.setBackground(Color.white);
		add(patientForm1, "cell 1 1 2 7, grow, gap 10"); 

	}
	
	private void enableBackground(JLabel lbl) {
		lbl.setBackground(Color.WHITE);
		Border greenLine = BorderFactory.createLineBorder(titleColor);
		lbl.setBorder(greenLine);
		lbl.setOpaque(true); 
	}

}
