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
import urgency.db.pojos.Patient;

public class PatientCell implements ListCellRenderer<Patient>{

	private final Color titleColor = new Color(7, 164, 121); //Bluish
	private final Font titleFont = new Font("sansserif", 3, 12);
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(122, 140, 141); //Gris
    private Color backgroundColor = new Color(230, 245, 241); 
    private final Color darkGreen = new Color(24, 116, 67);


	@Override
	public Component getListCellRendererComponent(JList<? extends Patient> list, Patient value, int index,
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
		JLabel patientName = new JLabel(value.getName()); 
		
		
		patientName.setForeground(contentColor);
		patientName.setFont(contentFont);
		listCell.add(name, "grow, left"); 
		listCell.add(patientName, "grow, left"); 
		
		
		//Surname
		JLabel surname = new JLabel("Surname:");
		surname.setForeground(titleColor);
		surname.setFont(titleFont); 
		
		//JLabel patientSurname= new JLabel("Apellido Random");
		JLabel patientSurname  = new JLabel(value.getSurname()); 
		
		
		patientSurname.setForeground(contentColor);
		patientSurname.setFont(contentFont);
		listCell.add(surname, "grow, left"); 
		listCell.add(patientSurname, "grow, left");
		
		//Sex
		JLabel sex = new JLabel("Sex:");
		sex.setForeground(titleColor);
		sex.setFont(titleFont); 
		
		//JLabel patientSex = new JLabel("Male/Female"); 
		JLabel patientSex = new JLabel(value.getSex());
		patientSex.setForeground(contentColor);
		patientSex.setFont(contentFont);
		listCell.add(sex, "grow, left"); 
		listCell.add(patientSex, "grow, left");
		
		//BirthDate
		JLabel birthDate = new JLabel("Date of birth:");
		birthDate.setForeground(titleColor);
		birthDate.setFont(titleFont); 
		
		//JLabel patientBirthDate = new JLabel("Random Birth Date");
		JLabel patientBirthDate = new JLabel(value.getBirthDate().toLocalDate().toString()); 
		patientBirthDate.setForeground(contentColor);
		patientBirthDate.setFont(contentFont);
		listCell.add(birthDate, "grow, left"); 
		listCell.add(patientBirthDate, "grow, left");
		
		if(isSelected)
		{
			listCell.setBackground(backgroundColor);
		}else {
			listCell.setBackground(Color.white);
		}
		return listCell;
	}

}
