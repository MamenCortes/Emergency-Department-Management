package urgency.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListCellRenderer;




public class MyComboBox<E> extends JComboBox<E> {

	private static final long serialVersionUID = 1696707481994733631L;
    private Color backgroundColor = new Color(230, 245, 241); 
    private boolean mouseOver;
    private String hint = ""; 
		
	public MyComboBox() {
		installUI(); 
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
    @Override
    public void updateUI() {
        super.updateUI();
        installUI();
    }

    private void installUI() {
        setUI(new StyledComboBoxUI(this));
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                Component com = super.getListCellRendererComponent(jlist, o, i, bln, bln1);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                if (bln) {
                	//color de selección de las celdas
                	com.setBackground(new Color(240, 240, 240));
                	com.setBackground(backgroundColor.darker());
                	
                }
                return com;
            }
        });
    }
    

}

