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
import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.DoctorBox;
import urgency.db.pojos.Patient;
import urgency.db.pojos.PatientBox;
import urgency.ui.components.MyButton;
import urgency.ui.components.PanelCoverForMenu;
import urgency.ui.components.PatientBoxCell;
import urgency.ui.components.PatientCell;
import urgency.ui.components.SearchTemplate;

public class DoctorView extends SearchTemplate {

	private static final long serialVersionUID = 1309433925342914959L;
	private Application appMain; 
	private MyButton logOutButton; 
	private JPanel mainPanel;  
	private PanelCoverForMenu cover;
	private Doctor doctor; 
	private Box box; 
	private PatientBox patientBox; 
	private JLabel title2; 
	private JList<PatientBox> patientRecordsList; 
	private DefaultListModel<PatientBox> patientRecordsDefListModel;

	public DoctorView(Application appMain) {
		this.appMain = appMain; 
	}
	
	public void updateDoctorPanel(Doctor doctor) {
		//getBoxFromDoctor();
		this.setLayout(new MigLayout("fill, wrap 3", "[]", "[][][][][][][][][][][][]"));
		cover = new PanelCoverForMenu(); 
		add(cover, "cell 0 0, grow");
		
		this.doctor = doctor; 
		//TODO create method get Doctor Box 
		DoctorBox boxDoctor = appMain.conMan.getBoxManager().getLastBoxAssignedToDoctor(doctor); 
		System.out.println(boxDoctor);
		//box = appMain.conMan.getBoxManager().getBox(2); 
		box = boxDoctor.getBox(); 
		System.out.println(box);
		patientBox = appMain.conMan.getBoxManager().getPatientInBox(box.getId());
		doctor.setIn_box(true);
		
		mainPanel = new JPanel(); 
		mainPanel.setLayout(new MigLayout("fill, wrap 3", "[10%][80%][10%]", "[][][][][][]"));
		add(mainPanel, "cell 0 1 0 11, grow");
		//cover.setTitle("Doctor "+doctor.getName()+" "+doctor.getSurname()+": BOX "+box.getId());
		cover.setTitle("Doctor "+doctor.getName()+" "+doctor.getSurname());
		
		title = new JLabel("PATIENT PREVIEW");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 15));
		title.setAlignmentY(CENTER_ALIGNMENT);
		mainPanel.add(title, "cell 1 1, alignx center, grow");
		
		
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(this.getPreferredSize());
        scrollPane1.addMouseListener(this);
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setOpaque(false);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(this.getPreferredSize());
        scrollPane2.addMouseListener(this);
        
        errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    errorMessage.setVisible(false); 
       
		if(patientBox != null) {
			List<Patient> patientInBox = new ArrayList<Patient>(); 
			patientInBox.add(patientBox.getPatient());
			
			List<PatientBox> patientRecords = appMain.conMan.getPatientMan().getPatientRecords(patientBox.getPatient());
			showPatientsInScrollPane(patientInBox, scrollPane1);
			showPatientRecordsInScrollPane(patientRecords, scrollPane2);
		}else {
			showErrorMessage("No patient assigned to Box");
			if (patientDefListModel != null)patientDefListModel.removeAllElements();
			if (patientRecordsDefListModel != null)patientRecordsDefListModel.removeAllElements();
		}
		mainPanel.add(scrollPane1, "cell 1 2, alignx center, grow"); 
		
		title2 = new JLabel("PATIENT'S URGENCY RECORD");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setForeground(titleColor);
		title2.setFont(new Font("sansserif", Font.BOLD, 15));
		title2.setAlignmentY(CENTER_ALIGNMENT);
		mainPanel.add(title2, "cell 1 3, alignx center, grow");
		mainPanel.add(scrollPane2, "cell 1 4, alignx center, grow");
		
        openFormButton = new MyButton("OPEN FORM"); 
        openFormButton.setBackground(new Color(7, 164, 121));
        openFormButton.setForeground(new Color(250, 250, 250));
        openFormButton.addActionListener(this);
	    
        logOutButton = new MyButton("LOG OUT"); 
        logOutButton.setBackground(new Color(7, 164, 121));
        logOutButton.setForeground(new Color(250, 250, 250));
        logOutButton.addActionListener(this);
        
	    mainPanel.add(logOutButton, "cell 1 5, split 2, center, gapy 5, gapx 10");
	    mainPanel.add(openFormButton, "cell 1 5, center, gapy 5, gapx 10");
	    
	    mainPanel.add(errorMessage, "cell 1 6, center"); 

	}
	
	protected void showPatientsInScrollPane(List<Patient> patients, JScrollPane scrollPane) {
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
        scrollPane.setViewportView(patientList);
	}
	
	
	protected void showPatientRecordsInScrollPane(List<PatientBox> patientRecords, JScrollPane scrollPane) {
        patientRecordsDefListModel = new DefaultListModel<>(); 
        if(patientRecords != null) {
            for (PatientBox patientBox : patientRecords) { 
                patientRecordsDefListModel.addElement(patientBox);
                
    		} 
        } 
        patientRecordsList = new JList<PatientBox>(patientRecordsDefListModel);
        patientRecordsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientRecordsList.setCellRenderer(new PatientBoxCell());
        patientRecordsList.addMouseListener(this);
        scrollPane.setViewportView(patientRecordsList);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == logOutButton) {
			doctor.setIn_box(false);
			resetPanel();
			appMain.changeToUserLogIn();
		}else if(e.getSource() == openFormButton) {
			if(!patientDefListModel.isEmpty()) {
				appMain.changeToPatientDoctorFor(patientBox);
			}
		}
	}
	
	@Override
	protected void resetPanel() {
	    this.removeAll();
		doctor = null; 
		box = null;
		scrollPane1 = null; 
		scrollPane2 = null; 
		patientBox = null; 
		if(patientDefListModel != null)patientDefListModel.removeAllElements();
		

	}
}
