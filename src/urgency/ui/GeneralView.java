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
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(122, 140, 141);
    private final Color darkGreen = new Color(24, 116, 67);
	private MyButton assignButton; 
	private JLabel explanationText2; 
	
	public GeneralView(Application appMain) {
		this.appMain = appMain;
		initGeneralView();
	} 
	
	protected void initGeneralView() {
		this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3", "[][]5[][]", "[][][][][][][][][][][][]"));
		//Add Title
		title = new JLabel("Assign doctors to boxes");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(icon);
		add(title, "cell 0 0 3 1, alignx left");
		
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(this.getPreferredSize());
        scrollPane1.addMouseListener(this);
        //System.out.println(appMain.conMan.getBoxManager().getBoxes());
        showBoxes(appMain.conMan.getBoxManager().getBoxes());
        
		JLabel explanationText = new JLabel("Select A Box"); 
	    explanationText.setFont(contentFont);
	    explanationText.setForeground(contentColor);
	    explanationText.setAlignmentY(CENTER_ALIGNMENT);
	    add(explanationText, "cell 0 1 1 1, alignx center");
	    
		explanationText2 = new JLabel("Select maximum 3 doctors using ctrl"); 
	    explanationText2.setFont(contentFont);
	    explanationText2.setForeground(contentColor);
	    add(explanationText2, "cell 2 1 1 1, alignx center");
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setOpaque(false);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(this.getPreferredSize());
        scrollPane2.addMouseListener(this);
        showDoctors(appMain.conMan.getDocMan().getDoctorsBySpeciality(null));
        
        //Mostrar el panel en una ventana emergente
        add(scrollPane1,  "cell 0 2 1 8, grow, gap 10");
        add(scrollPane2,  "cell 2 2 1 8, grow, gap 10");
		
        cancelButton = new MyButton("GO BACK"); 
        cancelButton.setBackground(new Color(7, 164, 121));
        cancelButton.setForeground(new Color(250, 250, 250));
        cancelButton.addActionListener(this);
	    add(cancelButton, "cell 0 10, center, gapy 5, gapx 10, grow");
	    
	    assignButton = new MyButton("ASSIGN"); 
        assignButton.setBackground(new Color(7, 164, 121));
        assignButton.setForeground(new Color(250, 250, 250));
        assignButton.addActionListener(this);
	    add(assignButton, "cell 2 10, center, gapy 5, gapx 10, grow");
	    assignButton.setVisible(false);
			    
	}

	@Override
	protected void showErrorMessage(String text) {
		explanationText2.setText(text);
		explanationText2.setForeground(Color.RED);
	}
	
	private void hideErrorMessage() {
		explanationText2.setText("Select maximum 3 doctors using ctrl");
		explanationText2.setForeground(contentColor);
	}
	
	private void showFeedbackMessage(String text) {
		explanationText2.setText(text);
		explanationText2.setForeground(darkGreen);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == assignButton) {
			List<Doctor> doctors = doctorList.getSelectedValuesList(); 
			Integer boxId = boxList.getSelectedValue().getId(); 
			if(doctors.size()>3) {
				showErrorMessage("More than 3 doctors cannot be selected");
			}else if(doctors.size() == 0){
				showErrorMessage("Select at least 1 doctor to assign to Box "+boxId);
			}else {
				showFeedbackMessage("Doctors assigned to Box "+boxId);
				for (Doctor doctor : doctors) {
					appMain.conMan.getDocMan().assignBox(doctor.getid(), boxId);
				}
				showDoctors(appMain.conMan.getDocMan().getDoctorsBySpeciality(boxList.getSelectedValue().getSpeciality().getType()));
			}
		}else if(e.getSource() == cancelButton) {
			appMain.changeToManagerMenu();
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		hideErrorMessage();
		assignButton.setVisible(true);
		if(e.getSource() == boxList) {
			Box box = boxList.getSelectedValue();
			if(box != null) {
				System.out.println(box);
				List<Doctor> doctors = appMain.conMan.getDocMan().getDoctorsBySpeciality(box.getSpeciality().getType());
				System.out.println(doctors);
				showDoctors(doctors);
			}else {
				showErrorMessage("No box selected");
			}

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
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        doctorList.setCellRenderer(new DoctorCell());
        doctorList.addMouseListener(this);
        scrollPane2.setViewportView(doctorList);

    }
	
	public void updateView() {
		//initGeneralView();
		updateBoxDefModel(appMain.conMan.getBoxManager().getBoxes());
		doctorDefListModel.removeAllElements(); 
	}
}
