package urgency.db.jdbc;

import urgency.db.pojos.*;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import urgency.db.interfaces.DoctorManager;

public class JDBCDoctorManager implements DoctorManager {
	
	private Connection connection;
	private ConnectionManager conMan;
	
	public JDBCDoctorManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.connection = conMan.getConnection();
		
	}
	
	@Override
	public Doctor getDoctorByEmail(String email) {
		
		try {
			String sql = "SELECT * FROM Doctors WHERE email = ?";
			PreparedStatement pstm;
			pstm=connection.prepareStatement(sql); 
			pstm.setString(1, email);
			ResultSet rs = pstm.executeQuery();
			rs.next();
			Doctor d = new Doctor(rs.getInt("ID"), rs.getString("name"), rs.getString("surname"), rs.getString("email"), 
					    rs.getString("speciality_type"),  rs.getBoolean("in_box"));
			rs.close();
			System.out.println("Doctor has been got");
			return d;
		}catch(SQLException e) {
			System.out.println("Error in getting the doctor.");
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void assignBox(int Doctor_id, int Box_id) {
		try {
			String sql = "INSERT INTO BoxDoctor (box_id, doctor_id, date) VALUES (?,?,?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Box_id);
			pstmt.setInt(2, Doctor_id);
			pstmt.setTimestamp(3, Timestamp.from(Instant.now()));
			pstmt.executeUpdate();
			System.out.println("Doctor assigned to box");
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in asingning the doctor to the box");
			e.printStackTrace();
		}
		  
	}

	@Override
	public void deleteDoctor(int id) { //WORKS CORRECTLY
		//Doctor d = conMan.getDocMan().getDoctor(id);
		try {
		String template = "DELETE FROM Doctors WHERE id = ? ";
		PreparedStatement pstmt;
		pstmt = connection.prepareStatement(template);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		System.out.println("Doctor deleted");
		pstmt.close();
	} catch (SQLException e) {
		System.out.println("Error in deleting the doctor.");
		e.printStackTrace();
	}

	}

	@Override
	public void changeStatus(int id, boolean in_box) { //WORKS CORRECTLY
		//Doctor d = conMan.getDocMan().getDoctor(id);
		try{
		String template = "UPDATE Doctors SET in_box = ? WHERE id = ?";
		PreparedStatement pstmt;
		pstmt = connection.prepareStatement(template);
		pstmt.setBoolean(1, in_box);
		pstmt.setInt(2, id);
		pstmt.executeUpdate();
		System.out.println("Status changed");
		pstmt.close();
	} catch (SQLException e) {
		System.out.println("Error in changing the status.");
		e.printStackTrace();
	}
		
    }  
	

	@Override
	public void addDoctor(Doctor doctor) { //WORKS CORRECTLY
		try {
			String template = "INSERT INTO Doctors (name, surname, email, speciality_type, in_box) VALUES "
					+ "(?,?,?,?,?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setString(1, doctor.getName());
			pstmt.setString(2, doctor.getSurname());
			pstmt.setString(3, doctor.getEmail());
			pstmt.setString(4, doctor.getSpeciality_type().getType());
			pstmt.setBoolean(5, doctor.getIn_box());
			System.out.println("Doctor added");
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in adding the doctor.");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> searchDoctorsBySurname(String surname) { //WORKS CORRECTLY
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM Doctors WHERE surname LIKE ?";
			PreparedStatement search = connection.prepareStatement(sql);
			search.setString(1, "%" + surname + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Doctor doctor = new Doctor();
				    doctor.setid(rs.getInt("ID"));
	                doctor.setName(rs.getString("name"));
	                doctor.setSurname(rs.getString("surname"));
	                doctor.setEmail(rs.getString("email"));
	                Speciality speciality = new Speciality();
	                speciality.setType(rs.getString("speciality_type"));
	                doctor.setSpeciality_type(speciality);
	                doctor.setIn_box(rs.getBoolean("in_box"));
	                doctors.add(doctor);
			}
			return doctors;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public Doctor getDoctor(int id) { //WORKS CORRECTLY
		try {
			String sql = "SELECT * FROM Doctors WHERE ID = " + id; 
			Statement st;
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Doctor d = new Doctor(rs.getInt("ID"), rs.getString("name"), rs.getString("surname"), rs.getString("email"), 
					    rs.getString("speciality_type"),  rs.getBoolean("in_box"));
			rs.close();
			System.out.println("Doctor has been got");
			return d;
		}catch(SQLException e) {
			System.out.println("Error in getting the doctor.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateDoctor(Doctor doctor) { //WORKS CORRECTLY
		try {
			String sql = "UPDATE Doctors SET name=?, surname=?, email=?, speciality_type=?, in_box=? WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, doctor.getName());
			pstmt.setString(2, doctor.getSurname());
			pstmt.setString(3, doctor.getEmail());
			pstmt.setString(4, doctor.getSpeciality_type().getType());
			pstmt.setBoolean(5,  doctor.getIn_box());
			pstmt.setInt(6,  doctor.getid());
			System.out.println("Doctor updated.");
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("Error in updating the doctor.");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(String speciality_type){ //WORKS CORRECTLY
		List<Doctor> doctors = new ArrayList<Doctor> ();
		try {
			String sql = "SELECT * FROM Doctors WHERE speciality_type LIKE ?";
			PreparedStatement p;
			p = connection.prepareStatement(sql);
			p.setString(1, "%" + speciality_type + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String doctorName = rs.getString("name");
				String doctorSurname = rs.getString("surname");
				String doctorEmail = rs.getString("email");
				String doctorSpeciality = rs.getString("speciality_type");
				Boolean inbox = rs.getBoolean("in_box");
				Doctor d = new Doctor(id, doctorName, doctorSurname, doctorEmail, doctorSpeciality, inbox);
				doctors.add(d);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in getting the doctor by the speciality.");
			e.printStackTrace();
		}
		return doctors;
	}
}
