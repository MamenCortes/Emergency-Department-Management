package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.*;
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
	public void assignBox(int Doctor_id, int Box_id) {
		// TODO Auto-generated method stub
		//insertar en la tabla box-doctor
        
	}

	@Override
	public void deleteDoctor(int id) {
		// TODO Auto-generated method stub
		Doctor d = conMan.getDocMan().getDoctor(id);
		try {
		String template = "DELETE FROM Doctors WHERE id = ? ";
		PreparedStatement pstmt;
		pstmt = connection.prepareStatement(template);
		pstmt.setInt(1, d.getid());
		pstmt.executeUpdate();
		System.out.println("Doctor deleted");
		pstmt.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}

	}

	@Override
	public void changeStatus(boolean in_box) {
		// TODO Auto-generated method stub
		/*try{
		String template = "UPDATE doctors SET InBox = ? WHERE id = ? AND name = ?";
		PreparedStatement pstmt;
		pstmt = connection.prepareStatement(template);
		//pstmt.setString(1, b.getName());
		//pstmt.setString(2, b.getSurname());
		pstmt.executeUpdate();
		pstmt.close();
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}*/
		
    }  
	

	@Override
	public void addDoctor(Doctor doctor) {
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
			System.out.println("Error in the database");
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
				Integer id = rs.getInt("ID");
				Doctor d = getDoctor(id);
				doctors.add(d);
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
					   (Speciality)rs.getObject("speciality"),  rs.getBoolean("InBox"));
			rs.close();
			return d;
		}catch(SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub, WHAT WE UPDATE HERE??, coge el id, busca el doctor con el mismo id
		
	}

	@Override
	public List<Doctor> getDoctorsBySpeciality(Speciality speciality_type){
		// TODO Auto-generated method stub
		List<Doctor> doctors = new ArrayList<Doctor> ();
		try {
			String sql = "SELECT * FROM Doctors WHERE speciality LIKE ?";
			PreparedStatement p;
			p = connection.prepareStatement(sql);
			p.setString(1, "%" + speciality_type + "%");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String doctorName = rs.getString("name");
				String doctorSurname = rs.getString("surname");
				Speciality doctorSpeciality = (Speciality)rs.getObject("speciality");
				Boolean inbox = rs.getBoolean("in_box");
				Doctor d = new Doctor(id, doctorName, doctorSurname, doctorSpeciality, inbox);
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
	
	
	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager();
		JDBCDoctorManager docMan = new JDBCDoctorManager(conMan); 
		JDBCSpecialityManager spe = new JDBCSpecialityManager(conMan);
		Speciality s = new Speciality("Psychiatry");
		Doctor d;
		d = new Doctor(1,"Jorge", "Fernandez", s, true);
		docMan.addDoctor(d);
		System.out.println("Doctor added");
		Doctor d2 = new Doctor(2, "Maria", "Perez", s, false);
		docMan.addDoctor(d2);
		//docMan.deleteDoctor(d2.getid());
		//List<Doctor> doctors = docMan.getDoctorsBySpeciality(s);
		
		conMan.closeConnection();								
	}
	


}
