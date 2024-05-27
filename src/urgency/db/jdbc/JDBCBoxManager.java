package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
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
	public List<Box> getBoxesBySpeciality(Speciality speciality) {
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
	public List<Box> getBoxes(){
		List<Box> boxes = new ArrayList<Box>();
		try {
			String sql = "SELECT * FROM Boxes";
			Statement st;
			st = connection.createStatement(); 
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
                Integer boxId = rs.getInt("id");
                boolean available = rs.getBoolean("available");
                String specialityName = rs.getString("speciality_type");
                Speciality spec = new Speciality(specialityName);         
                Box b = new Box(boxId, available, spec);
                boxes.add(b);
            }
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		System.out.println(boxes);
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
                    + "WHERE Boxes.id = ? AND status = 'assistedInBox' ORDER BY PatientBox.date DESC";
			
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Box_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Float weight = rs.getFloat("weight"); 
			Float height = rs.getFloat("height"); 
			String status = rs.getString("status");
			Integer urgency = rs.getInt("urgency");
			String sex = rs.getString("sex");
			Date birthDate = rs.getDate("birthDate");
			//Date boxDate = rs.getDate("boxDate");
			Timestamp boxDate = rs.getTimestamp("boxDate"); 
			/*java.util.Date sdfDate1 = dateTimeFormat.parse(rs.getString("boxDate"));
			Timestamp boxDate = new Timestamp(sdfDate1.getTime());*/
			
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
		String template = "SELECT b.*, bd.date FROM \r\n"
				+ "Boxes AS b JOIN BoxDoctor AS bd ON bd.box_id = b.id \r\n"
				+ "JOIN Doctors AS d ON bd.doctor_id = d.id\r\n"
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
	
	@Override
	public void assignPatientToBox(int Patient_id, int Box_id) { //Funciona si no hay una asignación previa
		try {
			String sql = "INSERT INTO PatientBox (patient_id, box_id, date) VALUES (?,?,?)"; 
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Patient_id);
			pstmt.setInt(2, Box_id);
			//Date date = Date.valueOf(LocalDate.now());
			Timestamp tsp = Timestamp.from(Instant.now());
			//pstmt.setDate(3, date);
			pstmt.setTimestamp(3, tsp);
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
	
	@Override
	public void updateBox(Box box) {
		String sql = "UPDATE Boxes SET speciality_type = ?, available = ? WHERE id = ?"; 

		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, box.getSpeciality().getType());
			pstmt.setBoolean(2, box.getAvailable());
			pstmt.setInt(3, box.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error updating Box "+box.getId());
			e.printStackTrace();
		}
	}

	@Override
	public void setAvailability(Boolean available, Integer box_id) {
		String sql = "UPDATE Boxes SET available = ? WHERE id = ?"; 

		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setBoolean(1, available);
			pstmt.setInt(2, box_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error changing availability of Box "+box_id);
			e.printStackTrace();
		}
		
	}

	
	@Override
	public List<Box> getDoctorBoxes(Doctor doctor) {
		List<Box> boxes = new ArrayList<Box>(); 
		String sql = "SELECT * FROM BoxDoctor JOIN Boxes ON box_id = id WHERE doctor_id = ?"; 
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, doctor.getid());
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				Integer id = rs.getInt("box_id"); 
				Boolean available = rs.getBoolean("available"); 
				Speciality spec = new Speciality(rs.getString("speciality_type")); 
				boxes.add(new Box(id, available, spec)); 
			}
			return boxes; 
		} catch (SQLException e) {
			System.out.println("Error retrieving boxes from doctor "+doctor.getName()+doctor.getSurname());
			e.printStackTrace();
		} 
		return null; 
		
	}
	
	@Override
	public List<Patient> getPatientsFromBox(Box box) {
		List<Patient> patients = new ArrayList<Patient>(); 
		String sql = "SELECT * FROM PatientBox JOIN Patients ON id = patient_id WHERE box_id = ?"; 
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, box.getId());
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String namePatient = rs.getString("name");
				String surnamePatient = rs.getString("surname");
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String status = rs.getString("status");
				Integer urgency = rs.getInt("urgency");
				String sex = rs.getString("sex");
				Date birthDate = rs.getDate("birthdate");
				Patient newPatient = new Patient(id, namePatient, surnamePatient, weight, height, status, urgency, sex, birthDate);
				patients.add(newPatient);
			}
			return patients; 
		} catch (SQLException e) {
			System.out.println("Error retrieving patients from box "+box.getId());
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public void createRandomBoxes() {
		//Specialities should be created before the boxes
		String sql = "SELECT COUNT(*) FROM Boxes"; 
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			rs.next(); 
			int numBoxes = rs.getInt(1); 
			rs.close();
			if(numBoxes == 0) {
				List<String> specs = conManager.getSpecialityManager().getSpecialities();
				for (String spec : specs) {
					addBox(new Box(false, new Speciality(spec)));
				}
				System.out.println("Random Boxes created");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
