package urgency.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JLabel;

import urgency.db.pojos.*;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyComboBox;

public class AddRoom extends FormTemplate {

	private static final long serialVersionUID = -3380973585111621415L;
	private Application appMain; 
	private List<String> specialities; 
	private MyComboBox<String> roomType; 
	private Box box; 
	private Triage triage; 

	public AddRoom(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		
		speciality = new MyComboBox<String>(); 
		specialities = appMain.specMan.getSpecialities(); 
		this.titleText += "Add Room"; 
		
		roomType = new MyComboBox<String>(); 
		roomType.addActionListener(this);
		
		form1 = new FormPanel("Add Room", roomType, speciality, specialities); 
		initPatientForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel();
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			//TODO Create a Room
			resetPanel();
			appMain.changeToManagerMenu(); 
			//showErrorMessage(errorMessage); 
		}else if(e.getSource() == roomType) {
			String type = ""; 
			if(roomType.getSelectedItem() != null) {
				 type = roomType.getSelectedItem().toString();
			}	
			if (type.equals("Triage") || type.equals("Emergency Room")) {
				speciality.setEnabled(false);
			}else {
				speciality.setEnabled(true);
			}
		}else if(e.getSource() == deleteButton) {
			//TODO test delete functionality
			if(box != null) {
				appMain.conMan.getBoxManager().deleteBox(box.getId());
			}else if(triage != null) {
				appMain.conMan.getTriageManager().deleteTriage(triage.getId());
			}
		}
	}
	
	@Override 
	protected void resetPanel() {
		speciality.setSelectedItem(null);
		roomType.setSelectedItem(null);
		deleteButton.setVisible(false);
		box = null; 
		triage = null; 
	}
	
	public void showRoomData(Box box) {
		this.box = box; 
		String boxSpeciality = box.getSpeciality().getType(); 
		speciality.getModel().setSelectedItem(boxSpeciality);
		roomType.setSelectedItem("Box");
		roomType.setEnabled(false);
		
		JLabel idText = new JLabel("ID: "+box.getId()); 
	    idText.setFont(new Font("sansserif", 1, 12));
	    idText.setForeground(new Color(24, 116, 67));
	    form1.add(idText, "grow,center, span");
	    deleteButton.setVisible(true);
	}
	public void showRoomData(Triage triage) {
		this.triage = triage; 
		speciality.setEnabled(false); 
		roomType.setSelectedItem("Triage");
		roomType.setEnabled(false);
		
		JLabel idText = new JLabel("ID: "+triage.getId()); 
	    idText.setFont(new Font("sansserif", 1, 12));
	    idText.setForeground(new Color(24, 116, 67));
	    form1.add(idText, "grow,center, span");
	    deleteButton.setVisible(true);
	}
	
	
	
}
