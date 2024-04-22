package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.*;
import java.util.List;

import urgency.db.interfaces.DoctorManager;


public class JDBCDoctorManager implements DoctorManager {
	
	private Connection c;

	@Override
	public void assignBox(int Doctor_id, int Box_id) {
		// TODO Auto-generated method stub
        
	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(boolean in_box) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Doctor> searchDoctorsBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM doctors WHERE ID = " + id;
			Statement st;
			st=c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
		}catch(SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(String speciality_type) {
		// TODO Auto-generated method stub
		return null;
	}


}
