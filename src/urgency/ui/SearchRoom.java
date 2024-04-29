package urgency.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.*;
import urgency.ui.components.MyButton;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.ui.components.SearchTemplate;

public class SearchRoom extends SearchTemplate {

	private static final long serialVersionUID = 2046290873154443207L;
	private Application appMain; 
	private MyComboBox<String> typeCB; 
	

	public SearchRoom(Application appMain) {
		this.appMain = appMain; 
		initRoomSearchPanel();
	}
	
	protected void initRoomSearchPanel() {
		this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3", "[]5[]5[][]", "[][][][][][][][][][]"));
		//Add Title
		title = new JLabel("Search Room");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(icon);
		add(title, "cell 0 0 3 1, alignx left");
		
		//Initialize search panel
		JLabel typeTitle = new JLabel("Select Type"); 
	    typeTitle.setFont(titleFont);
	    typeTitle.setForeground(titleColor);
	    add(typeTitle, "cell 0 1 2 1, alignx center, grow");
	    
	    //TODO finish search room panel
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
	    
	    idTextField = new MyTextField("ex. Doe..."); 
	    idTextField.setBackground(Color.white);
	    add(idTextField, "cell 0 4 2 1, alignx center, grow");
	    
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
	    errorMessage.setVisible(false); 
			    
	    showEmptyScrollPane();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			String type = (String) typeCB.getSelectedItem(); 
			if(type.equals("Triage")) {
				if(triageList == null) {
					showTriages(appMain.conMan.getTriageManager().getTriages()); 
					openFormButton.setVisible(true);
				}else {
					String idText = idTextField.getText(); 
					if(!idText.equals("")) {
						List<Triage> triages = new ArrayList<Triage>(); 
						Triage triage = appMain.conMan.getTriageManager().getTriage(Integer.parseInt(idText));
						if(triage == null) {
							if(boxDefListModel != null) boxDefListModel.removeAllElements();
							triageDefListModel.removeAllElements();
							showErrorMessage("No Triages with such Id");
						}else {
							triages.add(triage);
							showTriages(triages);
						}
					}else {
						showTriages(appMain.conMan.getTriageManager().getTriages()); 
					}
				}	
			}
			
			if(type.equals("Box")) {
				if(boxList == null) {
					showBoxes(createRandomBoxes());
					openFormButton.setVisible(true);
				}else {
					String idText = idTextField.getText(); 
					if(!idText.equals("")) {
						List<Box> boxes = new ArrayList<Box>();
						Box box = appMain.conMan.getBoxManager().getBox(Integer.parseInt(idText));
						if(box == null) {
							if(triageDefListModel!=null) triageDefListModel.removeAllElements();
							boxDefListModel.removeAllElements();
							showErrorMessage("No Boxes with such Id");
						}else {
							boxes.add(box);
							showBoxes(boxes);
						}
					}else {
						showBoxes(createRandomBoxes());
					}
				}
			}
				
		}else if(e.getSource() == openFormButton){
			if(boxList != null) {
				Box box = boxList.getSelectedValue(); 
				if(box == null) {
					showErrorMessage("No Box Selected");
				}else {
					resetPanel();
					appMain.changeToManagerMenu();
				}
			}else if(triageList != null) {
				Triage triage = triageList.getSelectedValue(); 
				if(triage == null) {
					showErrorMessage("No Box Selected");
				}else {
					resetPanel();
					appMain.changeToManagerMenu();
				}
			}
			

		}else if(e.getSource() == cancelButton){
			resetPanel();
			appMain.changeToManagerMenu();
		}else if(e.getSource() == typeCB) {
			/*System.out.println("type selected");
			String type = (String) typeCB.getSelectedItem(); 
			if(type.equals("Triage") && triageList == null) {
				
				showTriages(appMain.conMan.getTriageManager().getTriages()); 
				typeCB.setEnabled(false);
			}else if(type.equals("Box") && boxList == null) {
				System.out.println("Box selected");
				showBoxes(createRandomBoxes());
			}*/
		}
		
	}
	
	private List<Box> createRandomBoxes() {
		List<Box> boxes = new ArrayList<Box>(); 
		List<String> specialities = appMain.specMan.getSpecialities(); 
		List<Speciality> specs = new ArrayList<Speciality>(); 
		for (String string : specialities) {
			specs.add(new Speciality(string)); 
		}
		for(int i = 0; i<10; i++) {
    		int rd3 = ThreadLocalRandom.current().nextInt(0, specialities.size());
    		boxes.add(new Box(i, true, specs.get(rd3))); 
		}
		System.out.println(boxes);
		return boxes;
	}

}
