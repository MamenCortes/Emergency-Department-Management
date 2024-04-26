package urgency.ui;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import urgency.ui.components.MyButton;
import urgency.ui.components.PanelCoverForMenu;
import urgency.ui.components.patientForm;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DrbgParameters.NextBytes;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

public class AddPatient extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final Color titleColor2 = new Color(24, 116, 67);
	private final Color titleColor = new Color(7, 164, 121); 
	private JLabel title; 
	protected String titleText = " Add Patient"; 
	private JLabel option1; 
	protected String option1Text = "    General details"; 
	private JLabel option2; 
	protected String option2Text =  "    Medical Details"; 
	private JLabel option3; 
	protected String option3Text = "    Urgency Details";
	private ArrayList<JLabel> optionTexts; 
	private ArrayList<JPanel> patientForms; 
	private JPanel patientForm1; 
	private JPanel patientForm2; 
	private JPanel patientForm3;
	private JLayeredPane patientFormsContainer; 
	private JButton backButton; 
	private JButton nextButton;
	private JButton applyChanges; 
	private int panelShowed = 1; 

	public AddPatient() {
		//TODO enableBackground of shown option
		//Initialize panel
		init();  
	}
	
	private void init() {
		//TODO que la letra aumente con la pantalla
		this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3", "[][][]", "[][][][][][][][][][]"));
		patientForms = new ArrayList<JPanel>(); 
		optionTexts = new ArrayList<JLabel>(); 
		
		
		//Add Title
		title = new JLabel(" Add Patient");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(new ImageIcon(getClass().getResource("/urgency/ui/icon/medical-chechup.png")));
		add(title, "cell 0 0 3 1, alignx left");
		
		//Add Options
		if(option1Text != null) {
			option1 = new JLabel(option1Text);
			option1.setForeground(titleColor2);
			option1.setFont(new Font("sansserif", 1, 12));
			optionTexts.add(option1);
			option1.setAlignmentX(CENTER_ALIGNMENT);
			
			if(optionTexts.get(0).equals(option1)) {
				add(option1, "cell 0 1, alignx center, grow");
				enableBackground(option1);
				initPatientForm1(true); 
			}else {
				add(option2, "skip 2, alignx center, grow");
				initPatientForm1(false); 
			}

		}


		if(option2Text != null) {
			option2 = new JLabel(option2Text);
			option2.setForeground(titleColor2);
			option2.setFont(new Font("sansserif", 1, 12));
			option2.setAlignmentX(CENTER_ALIGNMENT);
			optionTexts.add(option2);

			if(optionTexts.get(0).equals(option2)) {
				add(option2, "cell 0 1, alignx center, grow");
				enableBackground(option2);
				initPatientForm2(true); 
			}else {
				add(option2, "skip 2, alignx center, grow");
				initPatientForm2(false); 
			}
		}
		

		if(option3Text != null) {
			option3 = new JLabel(option3Text);
			option3.setForeground(titleColor2);
			option3.setFont(new Font("sansserif", 1, 12));
			option3.setAlignmentX(CENTER_ALIGNMENT);
			optionTexts.add(option3);
			
			if(optionTexts.get(0).equals(option3)) {
				add(option3, "cell 0 1, alignx center, grow");
				enableBackground(option3);
				initPatientForm3(true); 
			}else {
				add(option3, "skip 2, alignx center, grow");
				initPatientForm3(false); 
			}
			
			
		}

		
		backButton = new MyButton("BACK"); 
        backButton.setBackground(new Color(7, 164, 121));
        backButton.setForeground(new Color(250, 250, 250));
        backButton.addActionListener(this); 
		add(backButton,"cell 1 7, left, gapx 10, gapy 5"); 
		nextButton = new MyButton("NEXT"); 
        nextButton.setBackground(new Color(7, 164, 121));
        nextButton.setForeground(new Color(250, 250, 250));
        nextButton.addActionListener(this);
		add(nextButton, "right, gapy 5");
		applyChanges = new MyButton("APPLY"); 
        applyChanges.setBackground(new Color(7, 164, 121));
        applyChanges.setForeground(new Color(250, 250, 250));
        applyChanges.addActionListener(this);
        
        if(panelShowed == patientForms.size()) {
			nextButton.setVisible(false);
			this.remove(nextButton);
			applyChanges.setVisible(true);
			add(applyChanges, "right, gapy 5"); 
		}
	}
	
	private void enableBackground(JLabel lbl) {
		lbl.setBackground(Color.WHITE);
		Border greenLine = BorderFactory.createLineBorder(titleColor);
		lbl.setBorder(greenLine);
		lbl.setOpaque(true); 
	}
	private void disableBackground(JLabel lbl) {
		lbl.setBackground(Color.WHITE);
		Border greenLine = BorderFactory.createLineBorder(titleColor);
		lbl.setBorder(greenLine);
		lbl.setOpaque(true); 
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()== backButton) {
    		if(panelShowed>1) {
        		panelShowed--;
    		}
    		 
    		System.out.println(panelShowed);
    		if(applyChanges.isVisible() && panelShowed != patientForms.size()) {
    			applyChanges.setVisible(false);
    			this.remove(applyChanges);
    			nextButton.setVisible(true);
    			add(nextButton, "right, gapy 5"); 
    		}
    		changePanel();
    		
    	}else if(e.getSource() == nextButton) {
    		panelShowed++; 
    		
    		if(panelShowed == patientForms.size()) {
    			nextButton.setVisible(false);
    			this.remove(nextButton);
    			applyChanges.setVisible(true);
    			add(applyChanges, "right, gapy 5"); 
    		}
    		changePanel(); 

    		//patientFormsContainer.add(patientForms.get(panelShowed-1));
    		
    		//TODO meter los paneles en un array y hacer que la variable op coincida con los índices del array. 
    		//Mostrar el panel en la posición op. 
    	}else if(e.getSource()==applyChanges) {
    	}
    }
    
    protected void initPatientForm1(Boolean isVisible) {
		patientForm1 = new patientForm();
		patientForms.add(patientForm1);
		patientForm1.setVisible(isVisible);
		
		if(isVisible) {
	    	add(patientForm1,  "cell 1 1 2 6, grow, gap 10");
		}
    }
    
    protected void initPatientForm2(Boolean isVisible) {
		patientForm2 = new patientForm();
		patientForm2.setVisible(isVisible);
		patientForm2.setBackground(Color.BLUE);
		patientForms.add(patientForm2);
		
		if(isVisible) {
	    	add(patientForm2,  "cell 1 1 2 6, grow, gap 10");
		}
    }
    protected void initPatientForm3(Boolean isVisible) {
		patientForm3 = new patientForm();
		patientForm3.setVisible(isVisible);
		patientForm3.setBackground(Color.RED);
		patientForms.add(patientForm3);

		if(isVisible) {
	    	add(patientForm3,  "cell 1 1 2 6, grow, gap 10");
		}
    }
    
    protected void changePanel() {
    	for (JPanel jPanel : patientForms) {
			if(jPanel.isVisible()) {
				jPanel.setVisible(false);
				this.remove(jPanel);
			}
		}
    	
    	patientForms.get(panelShowed-1).setVisible(true);
    	add(patientForms.get(panelShowed-1),  "cell 1 1 2 6, grow, gap 10");
    }

}
