package urgency.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.Box;
import urgency.db.pojos.Doctor;

public class DoctorCell implements ListCellRenderer<Doctor> {
	
	private final Color titleColor = new Color(7, 164, 121); //Bluish
	private final Font titleFont = new Font("sansserif", 3, 12);
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(122, 140, 141); //Gris
    private Color backgroundColor = new Color(230, 245, 241); 
    private final Color darkGreen = new Color(24, 116, 67);

	@Override
	public Component getListCellRendererComponent(JList<? extends Doctor> list, Doctor value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel listCell = new JPanel(); 
		listCell.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 4", "[][]5[][]", "[][][][]"));
		listCell.setBackground(Color.white);
        Border border = javax.swing.BorderFactory.createLineBorder(darkGreen); 
        listCell.setBorder(border);
		
		//Name
		JLabel name = new JLabel("Name:"); 
		name.setForeground(titleColor);
		name.setFont(titleFont); 
		//JLabel patientName = new JLabel("Nombre Random"); 
		JLabel doctorName = new JLabel(value.getName()); 
		doctorName.setForeground(contentColor);
		doctorName.setFont(contentFont);
		listCell.add(name, "grow, left"); 
		listCell.add(doctorName, "grow, left"); 
		
		
		//Surname
		/*JLabel surname = new JLabel("Surname:");
		surname.setForeground(titleColor);
		surname.setFont(titleFont); 
		
		//JLabel patientSurname= new JLabel("Apellido Random");
		JLabel patientSurname  = new JLabel(value.getSurname()); 
		
		
		patientSurname.setForeground(contentColor);
		patientSurname.setFont(contentFont);
		listCell.add(surname, "grow, left"); 
		listCell.add(patientSurname, "grow, left");*/
		
		//Speciality
		JLabel spec = new JLabel("Medical Speciality:");
		spec.setForeground(titleColor);
		spec.setFont(titleFont); 
		
		JLabel doctorSpec = new JLabel(value.getSpeciality_type());
		doctorSpec.setForeground(contentColor);
		doctorSpec.setFont(contentFont);
		listCell.add(spec, "grow, left, skip 2"); 
		listCell.add(doctorSpec, "grow, left");
		
		//Box assigned
		JLabel box = new JLabel("Last Box assigned:");
		box.setForeground(titleColor);
		box.setFont(titleFont); 

		
		List<Box> boxes = value.getBoxes(); 
		JLabel doctorBox;
		if(!boxes.isEmpty()) {
			Box lastBox = boxes.get(boxes.size()-1); 
			doctorBox = new JLabel(lastBox.getId().toString());
		}else {
			doctorBox = new JLabel("No Box assigned"); 
		}
		 
		doctorBox.setForeground(contentColor);
		doctorBox.setFont(contentFont);
		listCell.add(box, "grow, left, skip 2"); 
		listCell.add(doctorBox, "grow, left");
		
		if(isSelected)
		{
			listCell.setBackground(backgroundColor);
		}else {
			listCell.setBackground(Color.white);
		}
		return listCell;
	}

}