package urgency.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import urgency.db.interfaces.SpecialityManager;
import urgency.db.pojos.Speciality;

public class JDBCSpecialityManager implements SpecialityManager {
	
	//TODO Try the methods
	private ConnectionManager conManager;
	private Connection connection; 
	
	public void JDBCSpecilityManager(ConnectionManager conManager) {
		this.conManager = conManager; 
		this.connection = conManager.getConnection(); 
	}

	@Override
	public void assignPatientSpeciality(int Patient_id, String Speciality_type) {
		try {
			String command = "INSERT INTO PatientSpeciality (Patient_id, Speciality_Type) VALUES (?,?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Patient_id);
			prep.setString(2, Speciality_type);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a patient");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void assignDoctorSpeciality(int Doctor_id, String Speciality_type) {
		try {
			String command = "INSERT INTO DoctorSpeciality (Doctor_id, Speciality_Type) VALUES (?,?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Doctor_id);
			prep.setString(2, Speciality_type);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a doctor");
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void assignBoxSpecility(int Box_id, String Speciality_type) {
		try {
			String command = "INSERT INTO BoxSpeciality (Box_id, Speciality_Type) VALUES (?,?);";
			PreparedStatement prep = connection.prepareStatement(command);
			prep.setInt(1, Box_id);
			prep.setString(2, Speciality_type);
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
		} catch (SQLException e) {
			System.out.println("Error assigning the speciality to a Box");
			System.out.println(e.getMessage());
		}
	}


}
