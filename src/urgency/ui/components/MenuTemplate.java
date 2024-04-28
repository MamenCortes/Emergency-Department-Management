package urgency.ui.components;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuTemplate extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	protected PanelCoverForMenu panelCoverMenu; 
	protected PanelMenu panelMenu; 
	protected ArrayList<JButton> buttons; 
	private final Color backgroundColor2 = Color.WHITE; 
	

	public MenuTemplate() {
		this.setLayout(new MigLayout("fill, inset 0, gap 0", "[][][][][]", "[][][][][]"));
		buttons = new ArrayList<JButton>();
	}
		
	protected void init() { 
		panelCoverMenu = new PanelCoverForMenu();
		panelMenu = new PanelMenu(buttons); 
		for (JButton jButton : buttons) {
			jButton.addActionListener(this);
		}
		panelMenu.setBackground(backgroundColor2);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
