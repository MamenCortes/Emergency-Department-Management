package urgency.ui;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<JButton> buttons; 
	private Integer numButtons; 
	private String stringRows = "[][]"; 
	private Integer numRows = 2; 
	private String stringColumns = "[][][]"; 
	private Integer numColumns = 3; 

	/**
	 * Create the panel.
	 * Queda mal si son 5 botones
	 */
	public PanelMenu(ArrayList<JButton> buttons) {
		//this.buttons = buttons; 
		this.numButtons = buttons.size(); 
		this.buttons = buttons; 
		setLayout(); 
		initComponents(); 
		
	}
	
	private void setLayout() {
		
		
		if(numButtons%3 == 0) { //numero de botones múltiplo de 3
			for(int i = 0; i<numButtons/3; i++) {
				stringRows = stringRows+"[]"; 
				numRows++; 
			}
			stringColumns = stringColumns + "[][]";
			numColumns = numColumns +2; 
			
		}else if(numButtons%3 == 1) { //número de botones múltiplo de 3+1
			for(int i = 0; i<1+numButtons/3; i++) {
				stringRows = stringRows+"[]"; 
				numRows++; 
			}
			if(numButtons/3 != 0) { //si el número de botones es mayor que 3
				stringColumns = stringColumns + "[][]";
				numColumns = numColumns + 2; 
			}
		} else { //número de botones múltiplo de 3+2
			//TODO revisar
			for(int i = 0; i<numButtons/3; i++) {
				stringRows = stringRows+"[]"; 
				numRows++; 
			}

			if(numButtons/3 != 0) { //Si el número de botones es mayor que 3
				stringColumns = stringColumns + "[][]";
				numColumns = numColumns + 2; 
				stringRows = stringRows+"[]"; 
				numRows++; 
			}else {
				stringRows = stringRows + "[]"; 
				stringColumns = stringColumns + "[]";
				numRows++;
				numColumns++; 
			}
		}
		this.setLayout(new MigLayout("fill, inset 0, debug, gap 0, wrap", stringColumns, stringRows));

	}
	
	private void initComponents() {
		
		//Botones múltiplo de 3
		System.out.println(numColumns+", "+numRows);
		int buttonOut = 0; 
		if(numButtons%3 == 0) { //numero de botones múltiplo de 3
			for(int i = 0; i<numRows-2; i++) {
				for(int j = 0; j<3; j++) { //Añadir 3 botones en cada fila
					this.add(buttons.get(buttonOut), "cell "+(j+1)+" "+(i+1)+", grow");
					buttonOut++; 
					System.out.println(buttons.get(0).getName()+" in Column: "+(j+1)+" in Row: "+(i+1));
				}
			}
		}else if(numButtons%3 == 1) {
			for(int i = 0; i<numRows-2; i++) { 
				int j = 0; 
				if(buttons.get(buttonOut) == buttons.get(numButtons-1)){ //Colocar el último botón centrado
					this.add(buttons.get(buttonOut), "cell "+(j+1)+" "+(i+1)+", span "+(numColumns-2)+", split 3, center, grow");
					buttonOut++; 
				}else if(buttonOut < numButtons-1) {
					for(j = 0; j<3; j++) {
					 //Añadir 3 botones en cada fila
						this.add(buttons.get(buttonOut), "cell "+(j+1)+" "+(i+1)+", grow");
						buttonOut++; 
						System.out.println(buttons.get(buttonOut).getName()+" in Column: "+(j+1)+" in Row: "+(i+1));
					}
				}
		}
			
		}else {//número de botones múltiplo de 3+2
			for(int i = 0; i<numRows-2; i++) { 
				int j = 0; 
				if(buttons.get(buttonOut) == buttons.get(numButtons-2)){ //Colocar el último botón centrado
					this.add(buttons.get(buttonOut), "cell "+(j+1)+" "+(i+1)+", span "+(numColumns-2)+", split 3, center, grow");
					System.out.println("penúltimo elemento, buttonOut "+buttonOut);
					buttonOut++; 
					
					this.add(buttons.get(buttonOut), "grow, gap 0");
					System.out.println("ahora sí, último elemento");
					buttonOut++;
				}else if(buttonOut < numButtons-3) {
					for(j = 0; j<3; j++) {
					 //Añadir 3 botones en cada fila
						this.add(buttons.get(buttonOut), "cell "+(j+1)+" "+(i+1)+", grow");
						buttonOut++; 
						System.out.println(buttons.get(buttonOut).getName()+" in Column: "+(j+1)+" in Row: "+(i+1));
					}
				}
			}
		}
	}
}
