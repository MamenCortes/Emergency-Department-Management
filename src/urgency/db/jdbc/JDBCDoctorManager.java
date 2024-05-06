package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.*;
import java.time.LocalDate;
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
	public void assignBox(int Doctor_id, int Box_id) { //habria q pasarle el date?
		//ESTE METODO ES NECESARIO O DONDE SE UTILIZA?
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO BoxDoctor (box_id, doctor_id, date) VALUES (?,?,?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, Box_id);
			pstmt.setInt(2, Doctor_id);
			pstmt.setDate(3, Date.valueOf(LocalDate.now()));
			pstmt.executeUpdate();
			System.out.println("Doctor assigned to box");
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in asingning the doctor to the box");
			e.printStackTrace();
		}
		  
	}

	@Override
	public void deleteDoctor(int id) { //WORKS CORRECTLY
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try {
			String template = "INSERT INTO Doctors (name, surname, speciality_type, in_box) VALUES "
					+ "(?,?,?,?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
			pstmt.setString(1, doctor.getName());
			pstmt.setString(2, doctor.getSurname());
			pstmt.setString(3, doctor.getSpeciality_type().getType());
			pstmt.setBoolean(4, doctor.getIn_box());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in adding the doctor.");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> searchDoctorsBySurname(String surname) {
		// TODO Auto-generated method stub
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
	                doctor.setSurname(surname);
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
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM Doctors WHERE ID = " + id;
			Statement st;
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Doctor d = new Doctor(rs.getInt("ID"), rs.getString("name"), rs.getString("surname"), 
					    rs.getString("speciality_type"),  rs.getBoolean("in_box"));
			rs.close();
			return d;
		}catch(SQLException e) {
			System.out.println("Error in getting the doctor.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateDoctor(Doctor doctor) { 
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Doctors SET name=?, surname=?, speciality_type=?, in_box=?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, doctor.getName());
			pstmt.setString(2, doctor.getSurname());
			pstmt.setString(3, doctor.getSpeciality_type().getType());
			pstmt.setBoolean(4,  doctor.getIn_box());
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("Error in updating the doctor.");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(String speciality_type){ 
		// TODO Auto-generated method stub
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
				String doctorSpeciality = rs.getString("speciality_type");
				Boolean inbox = rs.getBoolean("in_box");
				Doctor d = new Doctor(id, doctorName, doctorSurname, doctorSpeciality, inbox);
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
	
	
	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager();
		JDBCDoctorManager docMan = new JDBCDoctorManager(conMan); 
		JDBCSpecialityManager spe = new JDBCSpecialityManager(conMan);
		Speciality s = new Speciality("Psychiatry");
		spe.addSpeciality(s);
		Doctor d;
		d = new Doctor(1,"Jorge", "Fernandez", s, true);
		docMan.addDoctor(d);
		System.out.println("Doctor added");
		Doctor d2 = new Doctor(2, "Maria", "Perez", s, false);
		docMan.addDoctor(d2);
		docMan.deleteDoctor(d2.getid());
		docMan.changeStatus(1, false);
		List<Doctor> doctors = docMan.getDoctorsBySpeciality(s.getType());
		Doctor d3 = docMan.getDoctor(1);
		System.out.println(d3);
		List<Doctor> doctors2 = docMan.searchDoctorsBySurname("Fernandez");
		System.out.println(doctors2);
		d2.setName("Juan");
		docMan.updateDoctor(d2);
		System.out.println(d2);
		
		conMan.closeConnection();								
	}
	


}
