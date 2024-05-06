package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import urgency.db.interfaces.SpecialityManager;
import urgency.db.pojos.Patient;
import urgency.db.pojos.Speciality;

public class JDBCSpecialityManager implements SpecialityManager {
	
	//TODO Try the methods
	private ConnectionManager conManager;
	private Connection connection; 
	
	public JDBCSpecialityManager(ConnectionManager conManager) {
		this.conManager = conManager; 
		this.connection = conManager.getConnection();
		addSpecialities(); //las especialidades solo se añaden una vez en la aplicacion
		//no puede ser q se añadan constantemente xq da error en doctor
	}

	private void addSpecialities() {
		List<Speciality> specialities = getSpecialities1(); 
		if(specialities.isEmpty()) {
			Speciality spec1 = new Speciality("Internal medicine"); 
			addSpeciality(spec1);
			Speciality spec2 = new Speciality("Family medicine"); 
			addSpeciality(spec2);
			Speciality spec3 = new Speciality("Emergency medicine"); 
			addSpeciality(spec3);
			Speciality spec4 = new Speciality("Obstetrics and gynecology");
			addSpeciality(spec4);
			Speciality spec5 = new Speciality("Pediatrics");
			addSpeciality(spec5);
			Speciality spec6 = new Speciality("Psychiatry");
			addSpeciality(spec6);
			System.out.println("Speciality inserted");
		}
	}
	
	@Override
	public void assignPatientSpeciality(int Patient_id, String Speciality_type) {
		try {
			String command = "INSERT INTO PatientSpeciality (Patient_id, Speciality_Type, date) VALUES (?,?,?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Patient_id);
			prep.setString(2, Speciality_type);
			LocalDateTime dateTime = LocalDateTime.now();
			prep.setTimestamp(3, Timestamp.valueOf(dateTime));
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a patient");
			System.out.println(e.getMessage());
		}
	}

	/*
	@Override
	public void assignDoctorSpeciality(int Doctor_id, String Speciality_type) {
		//Try if it works correctly
		try {
			String command = "INSERT INTO DoctorSpeciality (Doctor_id, Speciality_Type, date) VALUES (?,?,?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Doctor_id);
			prep.setString(2, Speciality_type);
			LocalDateTime dateTime = LocalDateTime.now();
			prep.setTimestamp(3, Timestamp.valueOf(dateTime));
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a doctor");
			System.out.println(e.getMessage());
		}

	}*/

	@Override
	public void assignBoxSpecility(int Box_id, String Speciality_type) {
		//TODO try if it works correctly
		try {
			String command = "INSERT INTO BoxSpeciality (Box_id, Speciality_Type, date) VALUES (?,?, ?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Box_id);
			prep.setString(2, Speciality_type);
			LocalDateTime dateTime = LocalDateTime.now();
			prep.setTimestamp(3, Timestamp.valueOf(dateTime));
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a Box");
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<String> getSpecialities() {
		List<String> specialities = new ArrayList<String>(); 
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT Type FROM Specialities";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String type = rs.getString("type");
				specialities.add(type);
			}
			rs.close();
			stmt.close(); 
		} catch (SQLException e) {
			System.out.println("Error retrieving the specialities");
			System.out.println(e.getMessage());
		}
		return specialities;
	}
	
	public List<Speciality> getSpecialities1() {
		List<Speciality> specialities = new ArrayList<Speciality>(); 
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT Type FROM Specialities";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String type = rs.getString("type");
				specialities.add(new Speciality(type));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving the specialities");
			System.out.println(e.getMessage());
		}
		return specialities;
	}

	@Override
	public void addSpeciality(Speciality speciality) {
		try {
			String command = "INSERT INTO Specialities (Type) VALUES (?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setString(1, speciality.getType());
			prep.executeUpdate();
			prep.close();
			System.out.println(speciality.getType()+" inserted");
		} catch (SQLException e) {
			System.out.println("Error inserting a Speciality");
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager(); 
		JDBCSpecialityManager spMan = new JDBCSpecialityManager(conMan);
		JDBCPatientManager patMan = new JDBCPatientManager(conMan); 
		
		//Trying method addSpeciality
		Speciality spec1 = new Speciality("Internal medicine"); 
		spMan.addSpeciality(spec1);
		Speciality spec2 = new Speciality("Family medicine"); 
		spMan.addSpeciality(spec2);
		Speciality spec3 = new Speciality("Emergency medicine"); 
		spMan.addSpeciality(spec3);
		Speciality spec4 = new Speciality("Obstetrics and gynecology");
		spMan.addSpeciality(spec4);
		Speciality spec5 = new Speciality("Pediatrics");
		spMan.addSpeciality(spec5);
		Speciality spec6 = new Speciality("Psychiatry");
		spMan.addSpeciality(spec6);
		System.out.println("Speciality inserted");
		
		//Trying method getSpecialities
		List<String> specialities = spMan.getSpecialities(); 
		System.out.println(specialities);

		
		//trying method assignPatientSpeciality
		/*Integer rdm = ThreadLocalRandom.current().nextInt(0, specialities.size()-1);
		spMan.assignPatientSpeciality(3, specialities.get(rdm));
		System.out.println("3, "+rdm);
		rdm = ThreadLocalRandom.current().nextInt(0, specialities.size()-1);
		spMan.assignPatientSpeciality(2, specialities.get(rdm));
		System.out.println("2, "+rdm);
		rdm = ThreadLocalRandom.current().nextInt(0, specialities.size()-1);
		spMan.assignPatientSpeciality(4, specialities.get(rdm));
		System.out.println("4, "+rdm);*/
		
		conMan.closeConnection();
	}

}
