package urgency.ui;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import java.util.ArrayList;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelCoverMenu panelCoverMenu; 
	//private JPanel secondPanel; 
	private PanelMenu panelMenu; 
	public Menu() {
		//this.setLayout(new MigLayout("fill, inset 0, debug, gap 0, wrap", "[]", "[5][95:pref]"));
		this.setLayout(new MigLayout("fill, inset 0, debug, gap 0, wrap", "[][][][][]", "[][][][][]"));
		
		init(); 
	
	}
	
	private void init() {
		panelCoverMenu = new PanelCoverMenu();

		ArrayList<JButton> buttons = new ArrayList<JButton>(); 
		
		buttons.add(new JButton("b1")); 
		buttons.add(new JButton("b2")); 
		buttons.add(new JButton("b3")); 
		buttons.add(new JButton("b4")); 
		buttons.add(new JButton("b5")); 
		buttons.add(new JButton("b6")); 
		buttons.add(new JButton("b7")); 
		buttons.add(new JButton("b8"));
		buttons.add(new JButton("b9"));
		/*buttons.add(new JButton("b10"));
		buttons.add(new JButton("b11"));*/

		panelMenu = new PanelMenu(buttons); 
		panelMenu.setBackground(Color.WHITE);
		
		this.add(panelCoverMenu, "grow, span"); 
		this.add(panelMenu, "grow, span 5 4");
		

		
		
	}

}
