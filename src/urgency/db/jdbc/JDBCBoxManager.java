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
	public List<Box> getBoxes(int speciality_type) {
	    List<Box> boxes = new ArrayList<>();
	    String sql = "SELECT * FROM Boxes WHERE speciality_type = ?";
	    try (PreparedStatement st = connection.prepareStatement(sql)) {
	        st.setInt(1, speciality_type);
	        try (ResultSet rs = st.executeQuery()) {
	            while (rs.next()) {
	                Integer boxId = rs.getInt("id");
	                boolean available = rs.getBoolean("available");
	                String specialityName = rs.getString("speciality_type");
	                Speciality speciality = new Speciality(specialityName);         
	                Box b = new Box(boxId, available, speciality);
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
			System.out.println("No patient assigned to box "+Box_id);
			//e.printStackTrace();
			return null; 
		} 
		return pb; 
	}
	
	@Override
	public DoctorBox getLastBoxAssignedToDoctor(Doctor doctor) {
		String template = "SELECT b.*, bd.date FROM"
				+ "Boxes AS b JOIN BoxDoctor AS bd ON bd.box_id = b.id"
				+ "JOIN Doctors AS d ON bd.doctor_id = d.id"
				+ "WHERE bd.doctor_id = ?"
				+ "ORDER BY bd.date DESC "; 
		try {
			PreparedStatement pstm = connection.prepareStatement(template);
			pstm.setInt(1, doctor.getid());
			ResultSet rs = pstm.executeQuery(); 
			rs.next(); 
			Integer id = rs.getInt("id"); 
			Boolean avaliable = rs.getBoolean("available"); 
			String speciality_type = rs.getString("speciality_type"); 
			//Date date = rs.getDate("date"); 
			Timestamp date = rs.getTimestamp("date"); 
			
			//System.out.println("ID: "+id+", Available: "+avaliable+", Speciality: "+speciality_type+", Date: "+date);
			Box box = new Box(id, avaliable, new Speciality(speciality_type));
			rs.close();
			pstm.close();
			return new DoctorBox(doctor, box, date); 
		} catch (SQLException e) {
			System.out.println("No box assigned to doctor "+doctor);
			//e.printStackTrace();
			return new DoctorBox(doctor, null, null); 
		} 
		
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
	
	@Override 
	public Boolean checkDoctorAssignedToBoxToday(int doctor_id, int box_id) {
		String sql = "SELECT date FROM BoxDoctor WHERE doctor_id = ? AND box_id = ? ORDER BY date DESC"; 
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, doctor_id);
			pstm.setInt(2, box_id);
			//pstm.setDate(3, Date.valueOf(LocalDate.now()));
			ResultSet rs = pstm.executeQuery(); 
			Date date = rs.getDate("date");
			if (date.toString().equals(LocalDate.now().toString())) {
				System.out.println("Assignation already made today");
				return true; 
			}else {
				System.out.println(date +"/="+LocalDate.now());
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			System.out.println("Error checking if doctor was already assigned to box "+box_id+" today");
			//e.printStackTrace();
			return false; 
		} 
		return false; 
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
		
		
		//PatientBox patientBox = conBox.getPatientInBox(1);
		//System.out.println(patientBox);
		/*conBox.assignPatientToBox(1, 1);
		conBox.assignPatientToBox(1, 2);
		conBox.assignPatientToBox(2, 1);
		conBox.assignPatientToBox(2, 3);*/
		//conBox.assignPatientToBox(1, 7);
		conBox.assignPatientToBox(2, 7);
		//System.out.println(conBox.getPatientInBox(4));
		
		
		//Doctor doctor = conManager.getDocMan().getDoctor(1); 
		//DoctorBox docBox = conBox.getLastBoxAssignedToDoctor(doctor); 
		//System.out.println(docBox);
		conManager.closeConnection();
		
		
		
	}
	
	
}
