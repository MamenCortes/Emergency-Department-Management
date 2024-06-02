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
import urgency.db.pojos.Box;

public class BoxCell implements ListCellRenderer<Box> {
	
	private final Color titleColor = new Color(7, 164, 121); //Bluish
	private final Font titleFont = new Font("sansserif", 3, 12);
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(122, 140, 141); 
    private Color backgroundColor = new Color(230, 245, 241); 
    private final Color darkGreen = new Color(24, 116, 67);

	@Override
	public Component getListCellRendererComponent(JList<? extends Box> list, Box value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JPanel listCell = new JPanel(); 
		listCell.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 4", "push[]5[]20[]5[]push", "[][][][]"));
		listCell.setBackground(Color.white);
        Border border = javax.swing.BorderFactory.createLineBorder(darkGreen); 
        listCell.setBorder(border);
		
		//Id
		JLabel idLabel = new JLabel("ID:"); 
		idLabel.setForeground(titleColor);
		idLabel.setFont(titleFont); 
		
		JLabel boxId = new JLabel(value.getId().toString()); 
		boxId.setForeground(contentColor);
		boxId.setFont(contentFont);
		listCell.add(idLabel, "grow, left"); 
		listCell.add(boxId, "grow, left"); 
		
		
		//Speciality
		JLabel specLabel = new JLabel("Speciality:");
		specLabel.setForeground(titleColor);
		specLabel.setFont(titleFont); 
		
		JLabel boxSpec  = new JLabel(value.getSpeciality().getType()); 
		boxSpec.setForeground(contentColor);
		boxSpec.setFont(contentFont);
		listCell.add(specLabel, "grow, left"); 
		listCell.add(boxSpec, "grow, left");
		
		
		if(isSelected)
		{
			listCell.setBackground(backgroundColor);
		}else {
			listCell.setBackground(Color.white);
		}
		return listCell;
	}


	
}
