package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import urgency.db.pojos.Doctor;
import urgency.db.interfaces.DoctorManager;

public class JDBCDoctorManager implements DoctorManager {
	
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCDoctorManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void assignBox(int Doctor_id, int Box_id) {
		// TODO Auto-generated method stub
        
	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub
		String template = "DELETE Doctor WHERE id = ? ";

	}

	@Override
	public void changeStatus(boolean in_box) {
		// TODO Auto-generated method stub
		String template = "UPDATE doctors SET InBox = ? WHERE id = ? AND name = ?";

	}

	@Override
	public void addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub, IT IS ONLY MECESSARY THE NAME TO ADD A DOCTOR??
		try {
			String template = "INSERT INTO doctors (name) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, doctor.getName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> searchDoctorsBySurname(String surname) {
		// TODO Auto-generated method stub
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors WHERE surname LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + surname + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("ID");
				String name = rs.getString("name");
				String speciality = rs.getString("speciality_type");
				Boolean in_box = rs.getBoolean("In_Box");
				Doctor d = conMan.getDocMan().getDoctor(id);
				Doctor newDoctor = new Doctor(id, name, speciality, in_box);
				doctors.add(newDoctor);
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
			String sql = "SELECT * FROM doctors WHERE ID = " + id;
			Statement st;
			st=c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Doctor d = new Doctor(rs.getInt("ID"), rs.getString("name"), rs.getString("speciality"), 
					    rs.getBoolean("InBox"));
			return d;
		}catch(SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub, WHAT WE UPDATE HERE??
		
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(String name, String speciality_type) {
		// TODO Auto-generated method stub
		List<Doctor> doctors = new ArrayList<Doctor> ();
		try {
			String sql = "SELECT * FROM doctors WHERE name LIKE ? AND speciality LIKE ?";
			PreparedStatement p;
			p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			p.setString(2, "%" + speciality_type + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String doctorName = rs.getString("name");
				String doctorSpeciality = rs.getString("speciality");
				Doctor d = new Doctor(id, doctorName, doctorSpeciality);
				doctors.add(d);
			}
			rs.close();
			p.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return doctors;
	}


}
