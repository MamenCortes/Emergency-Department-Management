package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			String sql = "DELETE FROM Box WHERE id = ?";
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
			String sql = "INSERT INTO Boxes (available) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setBoolean(1,box.getAvailable()); 
			pstmt.executeUpdate();
			pstmt.close();
			}
		catch (SQLException e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		
	}

	@Override
	public List<Box> getBoxes() {
		List<Box> boxes = new ArrayList<Box>();

		try {
			String sql = "SELECT * FROM Boxes";
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				Boolean availability = rs.getBoolean("Available");
				String speciality = rs.getString("speciality");
				Box b = new Box(id, availability, speciality);
				boxes.add(b);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return boxes;
	}


	@Override
	public Patient getPatientInBox(int Box_id) {
		try {
	        String sql = "SELECT * FROM Patients WHERE Box_id = ?";
	        PreparedStatement p;
			p = connection.prepareStatement(sql);
			p.setInt(1, Box_id);
			ResultSet rs = p.executeQuery();

	        if (rs.next()) {
	        	Integer id = rs.getInt("ID");
				String namePatient = rs.getString("name");
				String surnamePatient = rs.getString("surname");
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String status = rs.getString("status");
				Integer urgency = rs.getInt("urgency");
				String sex = rs.getString("sex");
				Date birthDate = rs.getDate("birthdate");
				
				rs.close();
				Patient newPatient = new Patient(id, namePatient, surnamePatient, weight, height, status, urgency, sex, birthDate);
	            return newPatient;
	        } else {
	        	rs.close();
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la base de datos");
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public Box getBox(int id) {
		try {
			String sql = "SELECT * FROM Boxes WHERE id = " + id;
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Box b = new Box (rs.getInt("ID"), rs.getBoolean("Available"), rs.getString("speciality"));
			rs.close(); 
			return b;
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
			return null; 
		}
	}



	
	
}
