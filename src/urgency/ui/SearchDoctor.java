package urgency.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import urgency.db.pojos.Doctor;
import urgency.ui.components.SearchTemplate;

public class SearchDoctor extends SearchTemplate {

	private static final long serialVersionUID = 1467210386557643859L;
	private List<Doctor> randomDoctors; 
	private Application appMain; 

	public SearchDoctor(Application appMain) {
		initSearchTemplate();
		this.appMain = appMain; 
		title.setText("Search Doctor");
		randomDoctors = createRandomDoctors(); 
		System.out.println(randomDoctors);
		showDoctors(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			errorMessage.setVisible(false);
			String input = searchByTextField.getText();  
			List<Doctor> doctorsSubSet = new ArrayList<Doctor>(); 
			for (Doctor doctor : randomDoctors) {
				if(doctor.getSurname().equals(input)) {
					doctorsSubSet.add(doctor); 
				}
			}
			updateDoctorDefModel(doctorsSubSet);
			if(doctorsSubSet.isEmpty()) {
				showErrorMessage("No doctor found");
			}else {
				openFormButton.setVisible(true);
			}
			
		}else if(e.getSource() == openFormButton){
			Doctor doctor = doctorList.getSelectedValue(); 
			if(doctor == null) {
				showErrorMessage("No Doctor Selected");
			}else {
				resetPanel();
				appMain.changeToModifyDoctor(doctor);
			}
		}else if(e.getSource() == cancelButton){
			resetPanel();
			appMain.changeToManagerMenu();
		}
	}
	
    protected List<Doctor> createRandomDoctors() {
    	List<Doctor> doctors = new ArrayList<Doctor>(); 
    	String[] names = {"Pepe", "Juan", "María", "Fulanita", "Marta", "Eustaquio", "Camino", "Cristina"};
    	String[] surnames = {"Palomo", "Navarro", "Blanco", "De las fuentes", "Vazquez", "Rodriguez", "Lopez", "Gamarra"};
    	ArrayList<String> specialities = (ArrayList<String>) appMain.specMan.getSpecialities(); 
    	
    	for(int i = 0; i<20; i++) {
    		int rd1 = ThreadLocalRandom.current().nextInt(0, names.length);
    		int rd2 = ThreadLocalRandom.current().nextInt(0, surnames.length);
    		int rd3 = ThreadLocalRandom.current().nextInt(0, specialities.size());
    		doctors.add(new Doctor(i, names[rd1], surnames[rd2],specialities.get(rd3))); 
    	}
    	
		return doctors;
    	
    }
}
