package urgency.ui;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;
import urgency.db.pojos.Triage;
import urgency.ui.components.MyButton;
import urgency.ui.components.PanelCoverForMenu;
import urgency.ui.components.PatientCell;
import urgency.ui.components.SearchTemplate;

public class NurseView extends SearchTemplate{

	private static final long serialVersionUID = -2669058682988216845L;
	private Application appMain; 
	private MyButton selectButton; 
	private MyButton logOutButton; 
	private Triage triage;
	private JPanel selectPanel; 
	private JPanel nurseViewPanel; 
	private PanelCoverForMenu cover;
	private Patient patient; 

	public NurseView(Application appMain) {
		this.appMain = appMain; 
		this.setLayout(new MigLayout("fill, wrap 3", "[]", "[][][][][][][][][][][][]"));
		
		cover = new PanelCoverForMenu(); 
		add(cover, "cell 0 0, grow"); 
		initSelectPanel(); 
		//TODO init nurse view panel
		
	}
	
	private void initSelectPanel() {
		selectPanel = new JPanel(); 
		selectPanel.setLayout(new MigLayout("fill, wrap 3", "[10%][80%][10%]", "[][][][]"));
		add(selectPanel, "cell 0 1 0 11, grow");
		
		title = new JLabel("Select the triage you are workin on");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 15));
		title.setAlignmentY(CENTER_ALIGNMENT);
		selectPanel.add(title, "cell 1 1, alignx center, grow");
		
		
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(this.getPreferredSize());
        scrollPane1.addMouseListener(this);
        showTriages(appMain.conMan.getTriageManager().getTriages());
		selectPanel.add(scrollPane1, "cell 1 2, alignx center, grow"); 
		
		
        selectButton = new MyButton("SELECT"); 
        selectButton.setBackground(new Color(7, 164, 121));
        selectButton.setForeground(new Color(250, 250, 250));
        selectButton.addActionListener(this);
	    
        logOutButton = new MyButton("LOG OUT"); 
        logOutButton.setBackground(new Color(7, 164, 121));
        logOutButton.setForeground(new Color(250, 250, 250));
        logOutButton.addActionListener(this);
        
	    selectPanel.add(logOutButton, "cell 1 3, split 2, center, gapy 5, gapx 10");
	    selectPanel.add(selectButton, "cell 1 3, center, gapy 5, gapx 10");
	    
        errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    selectPanel.add(errorMessage, "cell 1 4, center"); 
	    errorMessage.setVisible(false); 
	}
	
	private void initNurseView() {
		
		cover.setTitle("Urgency Management: TRIAGE "+triage.getId());
		
		nurseViewPanel = new JPanel(); 
		nurseViewPanel.setLayout(new MigLayout("fill, wrap 3", "[10%][80%][10%]", "[][][][]"));
		add(nurseViewPanel, "cell 0 1 0 11, grow");
		
		title.setText("Patient in Triage"); 
		nurseViewPanel.add(title, "cell 1 1, alignx center, grow");
		errorMessage.setVisible(false);
        //showPatients();
		
		//patient = appMain.conMan.getTriageManager().getPatientInTriage(triage.getId());
		patient = appMain.conMan.getPatientMan().getPatient(1);
		System.out.println("Patient in triage "+triage.getId()+": "+patient);
		
		if(patient != null) {
			List<Patient> patientInTriage = new ArrayList<Patient>(); 
			patientInTriage.add(patient);
			showPatients(patientInTriage);
		}else {
			showErrorMessage("No patient assigned to triage");
			triageDefListModel.removeAllElements();
		}


		nurseViewPanel.add(scrollPane1, "cell 1 2, alignx center, grow"); 
	    
        logOutButton = new MyButton("LOG OUT"); 
        logOutButton.setBackground(new Color(7, 164, 121));
        logOutButton.setForeground(new Color(250, 250, 250));
        logOutButton.addActionListener(this);
        
        openFormButton = new MyButton("OPEN"); 
        openFormButton.setBackground(new Color(7, 164, 121));
        openFormButton.setForeground(new Color(250, 250, 250));
        openFormButton.addActionListener(this);
        
	    nurseViewPanel.add(logOutButton, "cell 1 3, split 2, center, gapy 5, gapx 10");
	    nurseViewPanel.add(openFormButton, "cell 1 3, center, gapy 5, gapx 10");
	    
	    nurseViewPanel.add(errorMessage, "cell 1 4, center"); 
        
	    selectPanel.setVisible(false);
	    remove(selectPanel);
	    add(nurseViewPanel, "cell 0 1 0 11, grow");
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO change to patient form when triage selected
		if(e.getSource() == selectButton) {
			Triage triage = triageList.getSelectedValue(); 
			if(triage != null) {
				this.triage = triage; 
				initNurseView();
			}else {
				showErrorMessage("Please select a Triage");
			}
			
		}else if(e.getSource() == logOutButton) {
			resetPanel();
			appMain.changeToUserLogIn();
		}else if(e.getSource() == openFormButton) {
			if(patient == null) {
				showErrorMessage("Please select a patient");
			}else {
				appMain.changeToPatientDoctorFor(patient);
			}
		}

	}
	
	@Override
	protected void resetPanel() {
		if(nurseViewPanel != null && nurseViewPanel.isShowing()) {
			nurseViewPanel.setVisible(false);
		    remove(nurseViewPanel);
		    initSelectPanel();
		    add(selectPanel, "cell 0 1 0 11, grow");
			triage = null;
			patient = null; 
		}

	}
	
	@Override
	protected void showPatients(List<Patient> patients) {
        patientDefListModel = new DefaultListModel<>(); 
        if(patients != null) {
            for (Patient patient : patients) { 
                patientDefListModel.addElement(patient);
                
    		} 
        } 
        patientList = new JList<Patient>(patientDefListModel);
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setCellRenderer(new PatientCell());
        patientList.addMouseListener(this);
        scrollPane1.setViewportView(patientList);
	}
	


	
	
}
