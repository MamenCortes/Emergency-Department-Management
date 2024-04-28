package urgency.ui.components;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage; 

public class MyButton extends JButton {

	private static final long serialVersionUID = 5848952178038888829L;
	private final Color backgroundColor = new Color(0xE6F1F4); 
	private final Color foregroundColor = new Color(35, 166, 97); 
	private ImageIcon image; 
	private final Font font = new Font("sansserif", 1, 15);
	
	public MyButton(String text, String imageSource){
		  try {
		    Image img = ImageIO.read(getClass().getResource(imageSource));
		    image = new ImageIcon(img);
		    this.setIcon(image);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		  this.setText(text); 
	      this.setBackground(backgroundColor);
	      this.setForeground(foregroundColor.darker());
	      System.out.println(foregroundColor.darker());
	      this.setFont(font);
	      this.setUI(new StyledButtonUI());
	}
	
	public MyButton(String text) {
		this.setBackground(backgroundColor);
		this.setForeground(foregroundColor.darker());
		this.setText(text);
		this.setFont(font);
		this.setUI(new StyledButtonUI());
	}
	
	public MyButton() {
		this.setBackground(backgroundColor);
		this.setForeground(foregroundColor.darker());
		this.setText("");
		this.setFont(font);
		this.setUI(new StyledButtonUI());
	}
	
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, height, height);
        /*if (pressedPoint != null) {
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2), (int) animatSize, (int) animatSize);
        }*/
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }
	

}
