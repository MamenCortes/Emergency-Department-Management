package testpackage.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import urgency.db.interfaces.BoxManager;
import urgency.db.interfaces.DoctorManager;
import urgency.db.interfaces.PatientManager;
import urgency.db.interfaces.SpecialityManager;
import urgency.db.interfaces.TriageManager;
import urgency.db.jdbc.ConnectionManager;

public class Menu {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private static DoctorManager docMan;
	private static PatientManager patMan;
	private static BoxManager boxMan;
	private static TriageManager triMan;
	private static SpecialityManager speMan;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Welcome to the urgency area!");
		// Manager setup
		ConnectionManager conMan = new ConnectionManager();
		docMan = conMan.getDocMan();
		patMan = conMan.getPatientMan();
		boxMan = conMan.getBoxManager();
		triMan = conMan.getTriageManager();
		speMan = conMan.getSpecialityManager();
		// TODO ...
		
		System.out.println("Choose your desired option");
		System.out.println("1....");
		System.out.println("2....");
		System.out.println("0. Exit");
		int choice = Integer.parseInt(r.readLine());
		switch (choice) {
		case 1: {
			
			break;
		}
		case 2: {
			
			break;
		}
		case 3: {
			
			break;
		}
		case 4: {
			
			break;
		}
		case 0: {
			conMan.close();
			return;
		}
		}
	}

}
