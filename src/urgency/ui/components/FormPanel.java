package urgency.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComponent;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import net.miginfocom.swing.MigLayout;

public class FormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Font titleFont = new Font("sansserif", 3, 15);
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(24, 116, 67); //Verde oscuro
	private final Color titleColor = new Color(7, 164, 121); //Más azulado
    private Color textFieldBg = new Color(230, 245, 241); 
	private ArrayList<JTextField> textFields;
	
	private MyTextField name; 
	private MyTextField surname; 
	private MyTextField weight; 
	private MyTextField height; 
	//private JComboBox<String> speciality; 
	private MyComboBox<String> speciality; 
	private MyComboBox<String> sex; 
	private JDateChooser dateChooser; 
	private MyComboBox<String> emergency; 

	public FormPanel() {
		textFields = new ArrayList<JTextField>(); 
		initPanel(); 
	    
	    JLabel title1 = new JLabel("General Info"); 
	    title1.setFont(titleFont);
	    title1.setForeground(titleColor);
	    add(title1, "cell 0 0, grow");
	    
	    //Name and surname
	    JLabel nameText = new JLabel("Name");
	    nameText.setFont(contentFont);
	    nameText.setForeground(contentColor);
	    add(nameText, "skip 1, grow");
	    
	    JLabel surnameText = new JLabel("Surname");
	    surnameText.setFont(contentFont);
	    surnameText.setForeground(contentColor);
	    add(surnameText, "grow");
	    
	    name = new MyTextField("Name*");
	    add(name, "grow");
	    
	    surname = new MyTextField("Surname*");
	    add(surname, "grow");
	    
	    //Sex and dateOfBirth
	    JLabel sexText = new JLabel("Sex*");
	    sexText.setFont(contentFont);
	    sexText.setForeground(contentColor);
	    add(sexText, "grow");
	    
	    JLabel spec = new JLabel("Date of Birth*");
	    spec.setFont(contentFont);
	    spec.setForeground(contentColor);
	    add(spec, "grow");
	    
	    sex =new MyComboBox<String>();
	    sex.addItem("...");
        sex.addItem("Man");
        sex.addItem("Woman");
        add(sex, "grow");
	    
        //https://datojava.blogspot.com/2015/11/jcalendarJavaSwingEjemploTutorial.html
        dateChooser = new JDateChooser();
        for( Component c : dateChooser.getComponents()){
            ((JComponent)c).setBackground(textFieldBg); 
            }
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.getJCalendar().setMaxSelectableDate(new Date());
        
        IDateEditor dateEditor = dateChooser.getDateEditor();
        if (dateEditor instanceof JTextFieldDateEditor) {
            JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dateEditor;
            txtFld.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
            txtFld.setBackground(textFieldBg);
            txtFld.addPropertyChangeListener("foreground", event -> {
                if (Color.BLACK.equals(event.getNewValue())) {
                    txtFld.setForeground(Color.decode("#7A8C8D"));
                }
            });
        }

        add(dateChooser, "grow"); 
        
        JLabel emergencyText = new JLabel("Emergency*");
	    emergencyText.setFont(contentFont);
	    emergencyText.setForeground(contentColor);
	    add(emergencyText, "grow");
        
	    emergency =new MyComboBox<String>();
	    emergency.addItem("...");
        emergency.addItem("Low");
        emergency.addItem("High");
        add(emergency, "grow, skip 1, span");

	}
	
	/**
	 * @param Title: a String with the title of the panel
	 * @param Name: A MyTextField to input the name of the patient
	 * @param Surname: A MyTextField to input the surname of the patient
	 * @param Sex: A MyComboBox<String> to input the sex of the patient
	 * @param DateChooser: A JDateChooser to input the date of birth of the patient
	 * @param Emergency: A MyComboBox<String> to input the level of emergency. (Low/High)
	 */
	public FormPanel(String Title, MyTextField Name, MyTextField Surname, MyComboBox<String> Sex, JDateChooser DateChooser, MyComboBox<String> Emergency) {
		textFields = new ArrayList<JTextField>(); 
		initPanel(); 
	    
	    JLabel title1 = new JLabel(Title); 
	    title1.setFont(titleFont);
	    title1.setForeground(titleColor);
	    add(title1, "cell 0 0, grow");
	    
	    //Name and surname
	    JLabel nameText = new JLabel("Name");
	    nameText.setFont(contentFont);
	    nameText.setForeground(contentColor);
	    add(nameText, "skip 1, grow");
	    
	    JLabel surnameText = new JLabel("Surname");
	    surnameText.setFont(contentFont);
	    surnameText.setForeground(contentColor);
	    add(surnameText, "grow");
	    
	    name = Name; 
	    name.setHint("Name*"); 
	    add(name, "grow");
	    
	    surname = Surname; 
	    surname.setHint("Surname*");
	    add(surname, "grow");
	    
	    //Sex and dateOfBirth
	    JLabel sexText = new JLabel("Sex*");
	    sexText.setFont(contentFont);
	    sexText.setForeground(contentColor);
	    add(sexText, "grow");
	    
	    JLabel spec = new JLabel("Date of Birth*");
	    spec.setFont(contentFont);
	    spec.setForeground(contentColor);
	    add(spec, "grow");
	    
	    sex =Sex; 
	    sex.addItem("...");
        sex.addItem("Man");
        sex.addItem("Woman");
        add(sex, "grow");
	    
        //https://datojava.blogspot.com/2015/11/jcalendarJavaSwingEjemploTutorial.html
        dateChooser = DateChooser;
        for( Component c : dateChooser.getComponents()){
            ((JComponent)c).setBackground(textFieldBg); 
            }
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.getJCalendar().setMaxSelectableDate(new Date());
        
        IDateEditor dateEditor = dateChooser.getDateEditor();
        if (dateEditor instanceof JTextFieldDateEditor) {
            JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dateEditor;
            txtFld.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
            txtFld.setBackground(textFieldBg);
            txtFld.addPropertyChangeListener("foreground", event -> {
                if (Color.BLACK.equals(event.getNewValue())) {
                    txtFld.setForeground(Color.decode("#7A8C8D"));
                }
            });
        }

        add(dateChooser, "grow"); 
        
        JLabel emergencyText = new JLabel("Emergency*");
	    emergencyText.setFont(contentFont);
	    emergencyText.setForeground(contentColor);
	    add(emergencyText, "grow");
        
	    emergency = Emergency; 
	    emergency.addItem("...");
        emergency.addItem("Low");
        emergency.addItem("High");
        add(emergency, "grow, skip 1, span");

	}
	
	public FormPanel(String Title, MyTextField Name, MyTextField Surname, MyComboBox<String> specialities, List<String> specialitiesList) {
		textFields = new ArrayList<JTextField>(); 
		initPanel(); 
	    
	    JLabel title1 = new JLabel(Title); 
	    title1.setFont(titleFont);
	    title1.setForeground(titleColor);
	    add(title1, "cell 0 0, grow");
	    
	    //Name and surname
	    JLabel nameText = new JLabel("Name*");
	    nameText.setFont(contentFont);
	    nameText.setForeground(contentColor);
	    add(nameText, "skip 1, grow");
	    
	    JLabel surnameText = new JLabel("Surname*");
	    surnameText.setFont(contentFont);
	    surnameText.setForeground(contentColor);
	    add(surnameText, "grow");
	    
	    name = Name; 
	    name.setHint("Name*"); 
	    add(name, "grow");
	    
	    surname = Surname; 
	    surname.setHint("Surname*");
	    add(surname, "grow");
	    
	    JLabel specText = new JLabel("Medical Speciality*");
	    specText.setFont(contentFont);
	    specText.setForeground(contentColor);
	    add(specText, "grow");
	    
        //Speciality
	    List<String> specialityList = specialitiesList; 
        speciality = specialities; 
        for (String string : specialityList) {
			speciality.addItem(string);
		}
        add(speciality, "skip 1, grow, span");
	    
	}
	
	public FormPanel(String Title, MyTextField speciality) {
		textFields = new ArrayList<JTextField>(); 
		initPanel(); 
	    
	    JLabel title1 = new JLabel(Title); 
	    title1.setFont(titleFont);
	    title1.setForeground(titleColor);
	    add(title1, "cell 0 0, grow");
	    
	    //Name and surname
	    JLabel specText = new JLabel("Speciality*");
	    specText.setFont(contentFont);
	    specText.setForeground(contentColor);
	    add(specText, "skip 1, grow, span, center");
	    
	    MyTextField specTextField = speciality; 
	    specTextField.setHint("Ex: Family medicine..."); 
	    add(specTextField, "grow, span");
	    
	}

	public FormPanel(String Title, MyComboBox<String> roomType, MyComboBox<String> speciality2,
			List<String> specialityList) {
		textFields = new ArrayList<JTextField>(); 
		initPanel(); 
	    
	    JLabel title1 = new JLabel(Title); 
	    title1.setFont(titleFont);
	    title1.setForeground(titleColor);
	    add(title1, "cell 0 0, grow");
	    

	    JLabel roomTypeText = new JLabel("Room Type*");
	    roomTypeText.setFont(contentFont);
	    roomTypeText.setForeground(contentColor);
	    add(roomTypeText, "skip 1, grow, span, center");
	    
        roomType.addItem("Triage");
        roomType.addItem("Box");
        roomType.addItem("Emergency Room");
        add(roomType, "grow, span, grow");
	    
	    JLabel specText = new JLabel("Speciality*");
	    specText.setFont(contentFont);
	    specText.setForeground(contentColor);
	    add(specText, "grow, span, center");
	    
	    List<String> specialitiesList = specialityList; 
	    speciality = speciality2; 
        for (String string : specialitiesList) {
			speciality.addItem(string);
		}
        add(speciality, "grow, span");
	}

	public void initPanel() {
		this.setBackground(Color.white);
		this.setLayout(new MigLayout("fill, inset 10, gap 5, wrap 2", "[][]", "[][][][][][][]push"));
		
	    /*JScrollPane scrollPane1 = new JScrollPane();
	    scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane1.setViewportView(this);
	    JScrollBar sb = new JScrollBar();
        sb.setUnitIncrement(30);
        sb.setForeground(new Color(180, 180, 180));
        scrollPane1.setVerticalScrollBar(sb);
        add(scrollPane1); */
	}
}
