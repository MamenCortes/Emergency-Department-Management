package urgency.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import urgency.ui.components.MyComboBox;
import urgency.ui.components.MyTextField;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Patient;
import urgency.db.pojos.Role;
import urgency.db.pojos.Speciality;
import urgency.db.pojos.User;
import urgency.ui.components.ChangePassword;
import urgency.ui.components.FormPanel;
import urgency.ui.components.FormTemplate;
import urgency.ui.components.MyButton;

public class AddDoctor extends FormTemplate {


	private static final long serialVersionUID = 150788289055975404L;
	private Application appMain; 
	private List<String> specialities; 
	private MyTextField email; 
	private MyTextField password;  

	public AddDoctor(Application appMain) {
		this.option2Text = null; 
		this.option3Text = null; 
		this.appMain = appMain; 
		this.titleText = "Add Doctor"; 
		
		name = new MyTextField(); 
		surname = new MyTextField(); 
		speciality = new MyComboBox<String>(); 
		email = new MyTextField(); 
		password = new MyTextField(); 
		specialities = appMain.conMan.getSpecialityManager().getSpecialities(); 
		form1 = new FormPanel("Add New Doctor", name, surname, speciality, specialities, email, password); 
		initDoctorForm(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == goBackButton) {
			resetPanel(); 
			appMain.changeToManagerMenu();
		}else if(e.getSource() == applyChanges) {
			if(createDoctor()) {
				resetPanel(); 
				appMain.changeToManagerMenu(); 
			}
		}else if(e.getSource() == fromXMLButton) {
			//TODO open Dialog that allows the user to include the path of the file
			showInputFilePath(appMain);
		}
	}
	
	@Override
	protected void resetPanel() {
		name.setText(null);
		surname.setText(null);
		speciality.setSelectedItem(null);
	}
	
	
	private Boolean createDoctor() {
		String name = this.name.getText(); 
		String surname = this.surname.getText(); 
		
		if(name.equals("")||surname.equals("")||speciality.getSelectedItem()==null){
			showErrorMessage("Please complete all fields");
			return false;
		}
		
		String specText = speciality.getSelectedItem().toString(); 
		Speciality patientSpec = new Speciality(specText); 

		String email = this.email.getText();
		String password = this.password.getText(); 
		User u; 
		try {
			Role role = appMain.jpaRoleMan.getRole("Doctor"); 
			u = new User(email, password, role);
			Doctor doctor = new Doctor(name, surname, patientSpec, u.getEmail());
			appMain.jpaUserMan.register(u);
			appMain.conMan.getDocMan().addDoctor(doctor);
			System.out.println(doctor);
			return true; 
		}catch(NoSuchAlgorithmException e) {
			showErrorMessage("Password not valid");
			return false; 
		}

	}
	
	
    private static void showInputFilePath(JFrame parentFrame) {
    	MyTextField filePath = new MyTextField(); 
        MyButton okButton = new MyButton();
        MyButton cancelButton = new MyButton();
        
        JPanel panel = new JPanel(); 
        panel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        
        JLabel label = new JLabel("Enter XML file path");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        panel.add(label);
        
        filePath.setHint("Enter file path...");
        panel.add(filePath, "w 60%");
        
        JLabel errorMessage = new JLabel(); 
	    errorMessage.setFont(new Font("sansserif", Font.BOLD, 12));
	    errorMessage.setForeground(Color.red);
	    errorMessage.setText("Error message test");
	    errorMessage.setVisible(false); 
	    
        okButton.setText("Save"); 
        okButton.setBackground(new Color(7, 164, 121));
        okButton.setForeground(new Color(250, 250, 250));
        
        cancelButton.setText("Cancel"); 
        cancelButton.setBackground(new Color(7, 164, 121));
        cancelButton.setForeground(new Color(250, 250, 250));
        
        panel.add(okButton, "split 2, grow, left"); 
        panel.add(cancelButton, "grow, right"); 
        panel.add(errorMessage,"w 10%" );
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(400, 300));

        // Crear el JDialog para contener el panel personalizado
        JDialog dialog = new JDialog(parentFrame, "Change Password", true);
        dialog.getContentPane().add(panel);
        dialog.getContentPane().setBackground(Color.white);
        dialog.pack();
        dialog.setLocationRelativeTo(parentFrame);
        //dialog.setSize(400, 200);

        // Añadir acción a los botones
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String filePathText = filePath.getText();
                if(filePathText != null && !filePathText.isBlank()) {
                	//TODO Rodear con try catch
                	File file = new File(filePathText);
                	System.out.println(file);
                	/*if(appMain.xmlMan.Xml2Java(file)) {
    				System.out.println("Turn into XML");
    			}else {
    				showErrorMessage("XML file couldn't be created");
    			}*/
                	dialog.dispose();
                
                }else{
                	errorMessage.setText("Passwords do not match");
                	errorMessage.setVisible(true); 
                }
                
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }
	
}
