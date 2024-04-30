package urgency.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.*;
import urgency.ui.*;

public class SearchTemplate extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 3613193513087146791L;
	protected final Font titleFont = new Font("sansserif", 3, 15);
	//private final Color titleColor2 = new Color(24, 116, 67); //#187443
	protected final Color titleColor = new Color(7, 164, 121); 
	protected JLabel title; 
	protected String titleText = " Search Patient"; 
	protected ImageIcon icon  = new ImageIcon(getClass().getResource("/urgency/ui/icon/search-person-big.png"));
	protected JScrollPane scrollPane1; 
	protected String searchText = "Search By Surname"; 
	protected MyTextField searchByTextField; 
	protected MyButton searchButton; 
	protected MyButton cancelButton; 
	protected MyButton openFormButton; 
	//protected Application appMain; 
	protected JList<Patient> patientList; 
	protected DefaultListModel<Patient> patientDefListModel;
	protected JList<Doctor> doctorList; 
	protected DefaultListModel<Doctor> doctorDefListModel;
	protected JList<Triage> triageList; 
	protected DefaultListModel<Triage> triageDefListModel;
	protected JList<Box> boxList; 
	protected DefaultListModel<Box> boxDefListModel;
	//TODO create methods for box and triage List
	protected JLabel errorMessage; 
	protected JScrollPane scrollPane2; 

	public SearchTemplate() {
		//this.appMain = appMain; 
		//this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3", "[]5[]5[][]", "[][][][][][][][][][]"));
		//initSearchTemplate();

	}
	
	protected void initSearchTemplate() {
		this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3", "[]5[]5[][]", "[][][][][][][][][][]"));
		//Add Title
		title = new JLabel(titleText);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(icon);
		add(title, "cell 0 0 3 1, alignx left");
		
		//Initialize search panel
		JLabel searchTitle = new JLabel(searchText); 
	    searchTitle.setFont(titleFont);
	    searchTitle.setForeground(titleColor);
	    add(searchTitle, "cell 0 1 2 1, alignx center, grow");
	    
	    searchByTextField = new MyTextField("ex. Doe..."); 
	    searchByTextField.setBackground(Color.white);
	    add(searchByTextField, "cell 0 2 2 1, alignx center, grow");
	    
        cancelButton = new MyButton("CANCEL"); 
        cancelButton.setBackground(new Color(7, 164, 121));
        cancelButton.setForeground(new Color(250, 250, 250));
        cancelButton.addActionListener(this);
	    add(cancelButton, "cell 0 3, left, gapy 5, grow");
	    
		searchButton = new MyButton("SEARCH"); 
        searchButton.setBackground(new Color(7, 164, 121));
        searchButton.setForeground(new Color(250, 250, 250));
        searchButton.addActionListener(this);
	    add(searchButton, "cell 1 3, right, gapy 5, grow");
	    
		openFormButton = new MyButton("OPEN FILE"); 
        openFormButton.setBackground(new Color(7, 164, 121));
        openFormButton.setForeground(new Color(250, 250, 250));
        openFormButton.addActionListener(this);
	    add(openFormButton, "cell 0 4, center, gapy 5, span 2, grow");
	    openFormButton.setVisible(false);
	    
        errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    this.add(errorMessage, "cell 0 5, span 2, left"); 
	    errorMessage.setVisible(false); 
	    
	    //showPatients(appMain.patientMan.searchPatientsBySurname("Blanco"));
	    //showDoctors(createRandomDoctors());
	}
	
	
	protected void showErrorMessage(String text) {
		errorMessage.setText(text);
		errorMessage.setVisible(true);
	}


    protected void showPatients(List<Patient> patients) {

        //JPanel gridPanel = new JPanel(new GridLayout(patients.size(), 0));
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane1.setViewportView(gridPanel);
        
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

        //Ajustar el ScrollView para que no se amplíe al añadir muchos elementos
        scrollPane1.setPreferredSize(this.getPreferredSize());

        //Mostrar el panel en una ventana emergente
        add(scrollPane1,  "cell 2 1 2 6, grow, gap 10");
    }
    
    protected void updatePatientDefModel(List<Patient> patients) {
    	patientDefListModel.removeAllElements();
        if(patients != null) {
            for (Patient patient : patients) { 
                patientDefListModel.addElement(patient);
                
    		}
        }
    }
    
    protected void showDoctors(List<Doctor> doctors) {
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        doctorDefListModel = new DefaultListModel<>(); 
        if(doctors != null) {
            for (Doctor doctor : doctors) { 
                doctorDefListModel.addElement(doctor);
                
    		}
        }
        
        doctorList = new JList<Doctor>(doctorDefListModel);
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setCellRenderer(new DoctorCell());
        doctorList.addMouseListener(this);
        scrollPane1.setViewportView(doctorList);

        //Ajustar el ScrollView para que no se amplíe al añadir muchos elementos
        //scrollPane1.setPreferredSize(this.getPreferredSize());

        //Mostrar el panel en una ventana emergente
        add(scrollPane1,  "cell 2 1 2 6, grow, gap 10");
    }
    
    protected void updateDoctorDefModel(List<Doctor> doctors) {
    	doctorDefListModel.removeAllElements();
        if(doctors != null) {
            for (Doctor doctor : doctors) { 
                doctorDefListModel.addElement(doctor);
                
    		}
        }
    }
    
    protected void showEmptyScrollPane() {
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(this.getPreferredSize());

        //Mostrar el panel en una ventana emergente
        add(scrollPane1,  "cell 2 1 2 8, grow, gap 10");
    }
    
    protected void showBoxes(List<Box> boxes) {
        
        boxDefListModel = new DefaultListModel<>(); 
        if(boxes != null) {
            for (Box box: boxes) { 
                boxDefListModel.addElement(box);
                
    		}
        }

        
        boxList = new JList<Box>(boxDefListModel);
        boxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        boxList.setCellRenderer(new BoxCell());
        boxList.addMouseListener(this);
        
        if(scrollPane1 != null){
        	scrollPane1.setViewportView(boxList);
        }else {
        	showEmptyScrollPane();
        	scrollPane1.setViewportView(boxList);
        }

    }
    
    protected void updateBoxDefModel(List<Box> boxes) {
    	boxDefListModel.removeAllElements();
        if(boxes != null) {
            for (Box box: boxes) { 
                boxDefListModel.addElement(box);
                
    		}
        }
    }
    

    protected void showTriages(List<Triage> triages) {
        
        triageDefListModel = new DefaultListModel<>(); 
        if(triages != null) {
            for (Triage triage: triages) { 
                triageDefListModel.addElement(triage);
                
    		}
        }

        
        triageList = new JList<Triage>(triageDefListModel);
        triageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        triageList.setCellRenderer(new TriageCell());
        triageList.addMouseListener(this);
        if(scrollPane1 != null){
        	scrollPane1.setViewportView(triageList);
        }else {
        	showEmptyScrollPane();
        	scrollPane1.setViewportView(boxList);
        }
        
    }
    
    protected void updateTriageDefModel(List<Triage> triages) {
    	triageDefListModel.removeAllElements();
        if(triages != null) {
            for (Triage triage: triages) { 
                triageDefListModel.addElement(triage);
                
    		}
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(patientList != null) {
			Patient patient = patientList.getSelectedValue(); 
			System.out.println(patient);
		}
		
		if(doctorList != null) {
			Doctor doctor = doctorList.getSelectedValue(); 
			System.out.println(doctor);
		}
		
	}
	
	protected void resetPanel() {
		if(patientList != null) {
			patientDefListModel.removeAllElements();
		}
		if(doctorList != null) {
			doctorDefListModel.removeAllElements();
		}
		errorMessage.setVisible(false);
		openFormButton.setVisible(false);
		searchByTextField.setText(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
