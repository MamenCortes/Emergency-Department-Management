package urgency.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import urgency.db.pojos.Patient;
import urgency.ui.*;

public class SearchTemplate extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 3613193513087146791L;
	private final Font titleFont = new Font("sansserif", 3, 15);
	//private final Color titleColor2 = new Color(24, 116, 67); //#187443
	private final Color titleColor = new Color(7, 164, 121); 
	private JLabel title; 
	protected String titleText = " Search Patient"; 
	protected ImageIcon icon  = new ImageIcon(getClass().getResource("/urgency/ui/icon/search-person-big.png"));
	protected JScrollPane scrollPane1; 
	protected String searchText = "Search By Surname"; 
	private MyTextField searchTextField; 
	private MyButton searchButton; 
	private MyButton cancelButton; 
	private Application appMain; 
	private JList<Patient> list; 

	public SearchTemplate(Application appMain) {
		this.appMain = appMain; 
		this.setLayout(new MigLayout("fill, inset 20, gap 0, wrap 3", "[]5[]5[][]", "[][][][][][][][][][]"));
		initSearchTemplate();

	}
	
	public void initSearchTemplate() {
		//Add Title
		title = new JLabel(titleText);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(titleColor);
		title.setFont(new Font("sansserif", Font.BOLD, 25));
		title.setAlignmentY(LEFT_ALIGNMENT);
		title.setIcon(icon);
		add(title, "cell 0 0 3 1, alignx left");
		
		//Initialize search panel
		JLabel searchTitle = new JLabel(searchText); 
	    searchTitle.setFont(titleFont);
	    searchTitle.setForeground(titleColor);
	    add(searchTitle, "cell 0 1 2 1, alignx center, grow");
	    
	    searchTextField = new MyTextField("ex. Doe..."); 
	    searchTextField.setBackground(Color.white);
	    add(searchTextField, "cell 0 2 2 1, alignx center, grow");
	    
        cancelButton = new MyButton("CANCEL"); 
        cancelButton.setBackground(new Color(7, 164, 121));
        cancelButton.setForeground(new Color(250, 250, 250));
        cancelButton.addActionListener(this);
	    add(cancelButton, "cell 0 3, left, gapy 5");
	    
		searchButton = new MyButton("SEARCH"); 
        searchButton.setBackground(new Color(7, 164, 121));
        searchButton.setForeground(new Color(250, 250, 250));
        searchButton.addActionListener(this);
	    add(searchButton, "cell 1 3, right, gapy 5");
	    
	    showPacientes(appMain.patientMan.searchPatientsBySurname("Blanco"));
        

	}


    private void showPacientes(List<Patient> patients) {

        //JPanel gridPanel = new JPanel(new GridLayout(patients.size(), 0));
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane1.setViewportView(gridPanel);
        
        DefaultListModel<Patient> defListModel = new DefaultListModel<>(); 
        for (Patient patient : patients) { 
            defListModel.addElement(patient);
            
		}
        
        list = new JList<Patient>(defListModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new PatientCell());
        list.addMouseListener(this);
        scrollPane1.setViewportView(list);

        //Ajustar el ScrollView para que no se amplíe al añadir muchos elementos
        //scrollPane1.setPreferredSize(this.getPreferredSize());

        //Mostrar el panel en una ventana emergente
        add(scrollPane1,  "cell 2 1 2 6, grow, gap 10");
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Patient patient = list.getSelectedValue(); 
		System.out.println(patient);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
