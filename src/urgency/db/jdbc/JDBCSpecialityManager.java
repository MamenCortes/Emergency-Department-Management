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
import urgency.db.interfaces.SpecialityManager;
import urgency.db.pojos.Speciality;

public class JDBCSpecialityManager implements SpecialityManager {
	
	private ConnectionManager conManager;
	private Connection connection; 
	
	public JDBCSpecialityManager(ConnectionManager conManager) {
		this.conManager = conManager; 
		this.connection = conManager.getConnection();
	}

	@Override
	public void addRandomSpecialities() {
		List<String> specialities = getSpecialities(); 
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
	public void assignPatientSpeciality(int Patient_id, String Speciality_type, Timestamp date) {
		try {
			String command = "INSERT INTO PatientSpeciality (Patient_id, Speciality_Type, date) VALUES (?,?,?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Patient_id);
			prep.setString(2, Speciality_type);
			prep.setTimestamp(3, date);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a patient");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void assignBoxSpeciality(int Box_id, String Speciality_type) {
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

}
