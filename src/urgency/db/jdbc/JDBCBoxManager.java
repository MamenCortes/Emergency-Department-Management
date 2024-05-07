package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import urgency.db.interfaces.BoxManager;


public class JDBCBoxManager implements BoxManager {

	private ConnectionManager conManager;
	private Connection connection;
	
	public JDBCBoxManager(ConnectionManager conManager) {
		this.conManager = conManager;
		this.connection = conManager.getConnection();
	}
	
	@Override
	public void deleteBox(int id) {
		try {
			String sql = "DELETE FROM Boxes WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,id); 
			pstmt.executeUpdate();
			pstmt.close();
			}
		catch (SQLException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
	}
	
	
	@Override
	public void addBox(Box box) {
	    try {
	        JDBCSpecialityManager specialityManager = new JDBCSpecialityManager(conManager);
	        List<String> existingSpecialities = specialityManager.getSpecialities(); 
	        Speciality speciality = box.getSpeciality();
	        if (speciality == null) {
	            System.out.println("Speciality nula");
	            return; 
	        }   
	        String speciality_type = speciality.getType();
	        if (speciality_type == null || !existingSpecialities.contains(speciality_type)) {
	            System.out.println("La especialidad '" + speciality_type + "' no es válida.");
	            return;
	        }
	        
	        
	        String sql = "INSERT INTO Boxes (available, speciality_type) VALUES (?, ?)";
	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setBoolean(1, box.getAvailable());
	        pstmt.setString(2, speciality_type);
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        System.out.println("Error");
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Box> getBoxes(Speciality speciality) {
	    List<Box> boxes = new ArrayList<>();
	    String sql = "SELECT * FROM Boxes WHERE speciality_type = ?";
	    try (PreparedStatement st = connection.prepareStatement(sql)) {
	        st.setString(1, speciality.getType());
	        try (ResultSet rs = st.executeQuery()) {
	            while (rs.next()) {
	                Integer boxId = rs.getInt("id");
	                boolean available = rs.getBoolean("available");
	                String specialityName = rs.getString("speciality_type");
	                Speciality spec = new Speciality(specialityName);         
	                Box b = new Box(boxId, available, spec);
	                boxes.add(b);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error");
	        e.printStackTrace();
	    }
	    return boxes;
	}


	@Override
	public PatientBox getPatientInBox(int Box_id) {
	    PatientBox pb = null;;
		try {
			String sql = "SELECT Patients.id, Patients.name, Patients.surname, Patients.weight, Patients.height, Patients.status, Patients.urgency, Patients.sex, Patients.birthDate, "
                    + "PatientBox.date AS boxDate, PatientBox.comments " 
                    + "FROM Patients JOIN PatientBox ON Patients.id = PatientBox.patient_id " 
                    + "JOIN Boxes ON Boxes.id = PatientBox.box_id " 
                    + "WHERE Boxes.id = ? ORDER BY Patients.birthDate DESC";
			
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Box_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Integer weight = rs.getInt("weight");
			Integer height = rs.getInt("height");
			String status = rs.getString("status");
			Integer urgency = rs.getInt("urgency");
			String sex = rs.getString("sex");
			Date birthDate = rs.getDate("birthDate");
			Date boxDate = rs.getDate("boxDate");
            String comments = rs.getString("comments");
			
			Patient patient = new Patient(id, name, surname, weight, height, status, urgency, sex, birthDate);
			
            pb = new PatientBox(patient, getBox(Box_id), boxDate, comments);
			
			
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		} 
		return pb; 
	}
	
	
	public void assignPatientToBox(int Box_id, int Patient_id) {
		try {
			String sql = "INSERT INTO PatientBox (patient_id, box_id, date) VALUES (?,?,?)"; 
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Box_id);
			pstmt.setInt(2, Patient_id);
			pstmt.setDate(3, Date.valueOf(LocalDate.now()));
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		} 
	}
	
	
	
	@Override
	public Box getBox(int id) {
	    String sql = "SELECT Boxes.* FROM Boxes " +
                "LEFT JOIN Specialities ON Boxes.speciality_type = Specialities.type WHERE Boxes.id = ?";
	    try (PreparedStatement st = connection.prepareStatement(sql)) {
	        st.setInt(1, id);
	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                Integer boxId = rs.getInt("id");
	                boolean available = rs.getBoolean("available");
	                String specialityName = rs.getString("speciality_type");
	                Speciality speciality = new Speciality(specialityName);         
	                Box b = new Box(boxId, available, speciality);
	                rs.close();
	                return b;
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving box");
	        e.printStackTrace();
	    }
	    return null;
	}

	public static void main (String [] args) {
		ConnectionManager conManager = new ConnectionManager();
		JDBCBoxManager conBox = new JDBCBoxManager(conManager);
		
	
		Box box1 = new Box(1, true, null);
		Box box2 = new Box(2, true, null);
		Box box3 = new Box(3, false, null);
		Box box4 = new Box(4, true, null);
		Box box5 = new Box(5, false, null);
		
		
		System.out.print(box1);
		System.out.print(box2);
		System.out.print(box3);
		System.out.print(box4);
		System.out.print(box5);

		
		//conBox.addBox(box1);
		//conBox.addBox(box2);
		//conBox.addBox(box3);
		//conBox.getBox(1);
		//conBox.getBoxes(1);
		//conBox.deleteBox(1);
		//conBox.assignPatientToBox(1, 1);
		//conBox.getPatientInBox(1);
		
		
		
	}
	
	
}
