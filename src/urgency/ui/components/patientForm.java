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
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComponent;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import net.miginfocom.swing.MigLayout;

public class patientForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Font titleFont = new Font("sansserif", 3, 15);
	private final Font contentFont = new Font("sansserif", 1, 12);
	private final Color contentColor = new Color(24, 116, 67); //Más claro
	private final Color titleColor = new Color(7, 164, 121); //Más oscuro
    private Color textFieldBg = new Color(230, 245, 241); 
	private ArrayList<JTextField> textFields;
	
	private JTextField name; 
	private JTextField surname; 
	private JTextField age; //TODO should we add a birthDate instead of date?
	private JTextField weight; 
	private JTextField height; 
	private JComboBox<String> speciality; 
	private JComboBox<String> sex; 
	private JDateChooser dateChooser; 

	public patientForm() {
		textFields = new ArrayList<JTextField>(); 
		this.setBackground(Color.white);
		this.setLayout(new MigLayout("fill, inset 10, gap 5, wrap 2", "[][]", "[][][][][][][]push"));
		
	    JScrollPane scrollPane1 = new JScrollPane();
	    scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane1.setViewportView(this);
	    
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
	    
	    name = new MyTextField(); 
	    name.setText("Name*");
	    add(name, "grow");
	    
	    surname = new MyTextField();
	    surname.setText("Surname*");
	    add(surname, "grow");
	    
	    //Sex and dateOfBirth
	    JLabel sexText = new JLabel("Sex");
	    sexText.setFont(contentFont);
	    sexText.setForeground(contentColor);
	    add(sexText, "grow");
	    
	    JLabel spec = new JLabel("Date of Birth");
	    spec.setFont(contentFont);
	    spec.setForeground(contentColor);
	    add(spec, "grow");
	    
	    sex =new MyComboBox();
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

        
        
        
        //Speciality
	    speciality =new JComboBox<String>();
	    speciality.setToolTipText("hello");
        speciality.addItem("Traumatología");
        speciality.addItem("Medicina general");
        add(speciality, "grow");
	    
		
	    /*//Genera un panel con la información de cada ratón y los añade a un contendor
	    while (iterator.hasNext()) {
	        gridPanel.add(ratonCell(iterator.next()));
	    }

	    //Ajustar el ScrollView para que no se amplíe al añadir muchos elementos
	    scrollPane1.setPreferredSize(this.getPreferredSize());*/

	}
	
	  /**
     * Genera un JOptionPanel que permite al usuario introducir los
     * valores necesarios para crear un ratón.
     * @return el ratón creado
     * @throws IllegalArgument si el formato de la fecha no es correcto (IllegalDate),
     *          si alguno de los valores de entrada no es del tipo correcto y no se consigue convertir (IllegalFormat),
     *          si el peso o la temperatura no son válidos (IllegalPeso e IllegalTemperatura)
     * @throws GUIExceptions si se cancela la operación (CanceledOperation) o si no se rellenan todos los campos de texto (EmptyTextField)
     */
    /*public Raton createRaton() throws IllegalArgument, GUIExceptions {
        //Array para almacenar los textFields
        ArrayList<JTextField> textFields = new ArrayList<>();
        String[] parametros = {"Codigo de referencia", "Nacimiento (YYYY-MM-DD)", "Peso", "Temperatura", "Información Adicional"};

        //Se inicializa el panel donde se dispondrán los componentes
        JPanel gridPanel = new JPanel(new GridLayout(9, 2));

        //Crear los label, textfields de los parametros y añadirlos al panel
        for (String param:parametros) {
            JLabel text = new JLabel(param);
            text.setVerticalAlignment(JLabel.CENTER);
            text.setHorizontalAlignment(JLabel.CENTER);
            JTextField textInput = new JTextField();
            gridPanel.add(text);
            gridPanel.add(textInput);
            textFields.add(textInput);
        }


        //Crear ComboBoxes para limitar la selección de algunos atributos
        //Sex ComboBox
        JLabel sexText = new JLabel("Sexo");
        sexText.setVerticalAlignment(JLabel.CENTER);
        sexText.setHorizontalAlignment(JLabel.CENTER);
        JComboBox<String> sex =new JComboBox<String>();
        sex.addItem("HEMBRA");
        sex.addItem("MACHO");
        gridPanel.add(sexText);
        gridPanel.add(sex);

        //Cromosoma 1
        JLabel cr1Text = new JLabel("Cromosoma 1");
        cr1Text.setVerticalAlignment(JLabel.CENTER);
        cr1Text.setHorizontalAlignment(JLabel.CENTER);
        JComboBox<String> cr1 =new JComboBox<String>();
        cr1.addItem("Sin Mutación");
        cr1.addItem("Mutado");
        gridPanel.add(cr1Text);
        gridPanel.add(cr1);

        //Cromosoma 2
        JLabel cr2Text = new JLabel("Cromosoma 2");
        cr2Text.setVerticalAlignment(JLabel.CENTER);
        cr2Text.setHorizontalAlignment(JLabel.CENTER);
        JComboBox<String> cr2 =new JComboBox<String>();
        cr2.addItem("Sin Mutación");
        cr2.addItem("Mutado");
        gridPanel.add(cr2Text);
        gridPanel.add(cr2);

        //Se crea el cuadro de diálogo con el panel
        int opcionResultado = JOptionPane.showConfirmDialog(null, gridPanel, "Crear nuevo ratón", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(opcionResultado == JOptionPane.OK_OPTION){
            try{
                int codigoRef = Integer.parseInt(textFields.get(0).getText());
                LocalDate nacimiento = parseNacimiento(textFields.get(1).getText());
                Sexo sexo = Sexo.valueOf((String) sex.getSelectedItem());
                Boolean c1mut = null;
                Boolean c2mut = null;
                String cr1String = (String) cr1.getSelectedItem();
                if(cr1String.equals("Mutado")){
                    c1mut = true;
                } else if (cr1String.equals("Sin Mutación")) {
                    c1mut = false;
                }
                String cr2String = (String) cr2.getSelectedItem();
                if(cr2String.equals("Mutado")){
                    c2mut = true;
                } else if (cr1String.equals("Sin Mutación")) {
                    c2mut = false;
                }

                float peso = Float.parseFloat(textFields.get(2).getText());
                float temp = Float.parseFloat(textFields.get(3).getText());
                String campoTexto = textFields.get(4).getText();
                return new Raton(codigoRef, nacimiento, peso, temp, sexo, Boolean.TRUE.equals(c1mut), Boolean.TRUE.equals(c2mut), campoTexto);
            }catch (NullPointerException npex){
                throw new GUIExceptions(GUIError.EmptyTextField,"Todos los campos deben rellenarse para poder crear una población");
            }catch (NumberFormatException numFex){
                throw new IllegalArgument("Alguno de los valores introducidos don del tipo correcto", ErrorType.IllegalFormat);
            }

        }else{
            throw new GUIExceptions(GUIError.CanceledOperation, "Operación Cancelada");
        }
    }*/
	
}
