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
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	protected PanelCoverForMenu panelCoverMenu; 
	protected PanelMenu panelMenu; 
	protected ArrayList<JButton> buttons; 
	

	public Menu() {
		this.setLayout(new MigLayout("fill, inset 0, debug, gap 0", "[][][][][]", "[][][][][]"));
		buttons = new ArrayList<JButton>();
	}
		
	protected void init() { 
		panelCoverMenu = new PanelCoverForMenu();
		
		/*buttons.add(new JButton("b1")); 
		buttons.add(new JButton("b2")); 
		buttons.add(new JButton("b3")); 
		buttons.add(new JButton("b4")); 
		buttons.add(new JButton("b5")); 
		buttons.add(new JButton("b6")); 
		buttons.add(new JButton("b7")); 
		buttons.add(new JButton("b8"));
		buttons.add(new JButton("b9"));
		buttons.add(new JButton("b10"));
		buttons.add(new JButton("b11"));*/
		
		panelMenu = new PanelMenu(buttons); 
		panelMenu.setBackground(Color.WHITE);
		this.add(panelCoverMenu, "cell 0 0 5 1,grow"); 
		this.add(panelMenu, "cell 0 1 5 5,grow");
	}
	
	public PanelCoverForMenu getPanelCoverMenu() {
		return panelCoverMenu;
	}

	public void setPanelCoverMenu(PanelCoverForMenu panelCoverMenu) {
		this.panelCoverMenu = panelCoverMenu;
	}

	public PanelMenu getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
}
