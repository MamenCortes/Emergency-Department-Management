package urgency.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;
import urgency.ui.components.DoctorCell;
import urgency.ui.components.MyButton;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.ui.components.SearchTemplate;

public class GeneralView extends SearchTemplate{

	private static final long serialVersionUID = 1760715007022398845L;
	private Application appMain;
	private JScrollPane scrollPane2;
	
	public GeneralView(Application appMain) {
		this.appMain = appMain;
		initGeneralView();
	} 
	
	protected void initGeneralView() {
		this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3, debug", "[][]5[][]", "[][][][][][][][][][]"));
		//Add Title
		title = new JLabel("Assign doctors to boxes");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(icon);
		add(title, "cell 0 0 3 1, alignx left");
		
		
		
		/*
		
		//Initialize search panel
		JLabel typeTitle = new JLabel("Select Type"); 
	    typeTitle.setFont(titleFont);
	    typeTitle.setForeground(titleColor);
	    add(typeTitle, "cell 0 1 2 1, alignx center, grow");
	    
	    typeCB = new MyComboBox<String>(); 
        typeCB.addItem("Triage");
        typeCB.addItem("Box");
        typeCB.addActionListener(this);
        add(typeCB, "cell 0 2 2 1, alignx center, grow");
	    
	    //
		JLabel idTitle = new JLabel("Search by Room ID"); 
	    idTitle.setFont(titleFont);
	    idTitle.setForeground(titleColor);
	    add(idTitle, "cell 0 3 2 1, alignx center, grow");
	    
	    searchByTextField = new MyTextField("ex. Doe..."); 
	    searchByTextField.setBackground(Color.white);
	    add(searchByTextField, "cell 0 4 2 1, alignx center, grow");
	    
        cancelButton = new MyButton("CANCEL"); 
        cancelButton.setBackground(new Color(7, 164, 121));
        cancelButton.setForeground(new Color(250, 250, 250));
        cancelButton.addActionListener(this);
	    add(cancelButton, "cell 0 5, left, gapy 5, grow");
	    
		searchButton = new MyButton("SEARCH"); 
        searchButton.setBackground(new Color(7, 164, 121));
        searchButton.setForeground(new Color(250, 250, 250));
        searchButton.addActionListener(this);
	    add(searchButton, "cell 1 5, right, gapy 5, grow");
	    
		openFormButton = new MyButton("OPEN FILE"); 
        openFormButton.setBackground(new Color(7, 164, 121));
        openFormButton.setForeground(new Color(250, 250, 250));
        openFormButton.addActionListener(this);
	    add(openFormButton, "cell 0 6, center, gapy 5, span 2, grow");
	    openFormButton.setVisible(false);
	    
        errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    this.add(errorMessage, "cell 0 7, span 2, left"); 
	    errorMessage.setVisible(false); */
			    
	    showEmptyScrollPane();
	}
	
	@Override
    protected void showEmptyScrollPane() {
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(this.getPreferredSize());
        scrollPane1.addMouseListener(this);
        showBoxes(appMain.conMan.getBoxManager().getBoxes());
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setOpaque(false);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(this.getPreferredSize());
        scrollPane2.addMouseListener(this);
        showDoctors(appMain.conMan.getDocMan().getDoctorsBySpeciality(null));
        
        //Mostrar el panel en una ventana emergente
        add(scrollPane1,  "cell 0 1 1 8, grow, gap 10");
        add(scrollPane2,  "cell 2 1 1 8, grow, gap 10");
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(boxList != null) {
			Box box = boxList.getSelectedValue(); 
			System.out.println(box);
			List<Doctor> doctors = appMain.conMan.getDocMan().getDoctorsBySpeciality(box.getSpeciality());
		}
		
	}
	
	@Override
	protected void showDoctors(List<Doctor> doctors) {
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
        scrollPane2.setViewportView(doctorList);

    }
}
