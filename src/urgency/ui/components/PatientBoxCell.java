package urgency.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.PatientBox;

public class PatientBoxCell implements ListCellRenderer<PatientBox> {

	private final Color titleColor = new Color(7, 164, 121); //Bluish
	private final Font titleFont = new Font("sansserif", 3, 12);
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(122, 140, 141);
    private Color backgroundColor = new Color(230, 245, 241); 
    private final Color darkGreen = new Color(24, 116, 67);

	@Override
	public Component getListCellRendererComponent(JList<? extends PatientBox> list, PatientBox value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel listCell = new JPanel(); 
		listCell.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 2", "[][]", "[][][][]"));
		listCell.setBackground(Color.white);
        Border border = javax.swing.BorderFactory.createLineBorder(darkGreen); 
        listCell.setBorder(border);
		
		//Name
		JLabel speciality = new JLabel("Speciality:"); 
		speciality.setForeground(titleColor);
		speciality.setFont(titleFont); 
		//JLabel patientName = new JLabel("Nombre Random"); 
		JLabel patientSpeciality = new JLabel(value.getBox().getSpeciality().getType()); 
		
		
		patientSpeciality.setForeground(contentColor);
		patientSpeciality.setFont(contentFont);
		listCell.add(speciality, "grow, left"); 
		listCell.add(patientSpeciality, "grow, left"); 
		
		
		//Surname
		JLabel timeText = new JLabel("When:");
		timeText.setForeground(titleColor);
		timeText.setFont(titleFont); 
		
		//JLabel patientSurname= new JLabel("Apellido Random");
		JLabel patientRecordDate  = new JLabel(value.getDate().toString()); 
		
		
		patientRecordDate.setForeground(contentColor);
		patientRecordDate.setFont(contentFont);
		listCell.add(timeText, "grow, left"); 
		listCell.add(patientRecordDate, "grow, left");
		
		//Sex
		JLabel commentsTxt = new JLabel("Comments:");
		commentsTxt.setForeground(titleColor);
		commentsTxt.setFont(titleFont); 
		
		//JLabel patientSex = new JLabel("Male/Female"); 
		JLabel patientComm = new JLabel(value.getComments());
		patientComm.setForeground(contentColor);
		patientComm.setFont(contentFont);
		listCell.add(commentsTxt, "grow, left"); 
		listCell.add(patientComm, "grow, left");
		
		if(isSelected)
		{
			listCell.setBackground(backgroundColor);
		}else {
			listCell.setBackground(Color.white);
		}
		return listCell;
	}

}
