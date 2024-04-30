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
		return null;
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


	public static void main (String [] args) {
		ConnectionManager conManager = new ConnectionManager();
		JDBCBoxManager conBox = new JDBCBoxManager(conManager);
		
	
		Box box1 = new Box(1, true, "trauma");
		Box box2 = new Box(2, true, "gyno");
		Box box3 = new Box(3, false, "cardio");
		Box box4 = new Box(4, true, "ped");
		Box box5 = new Box(5, false, "trauma");
		
		
		System.out.print(box1);
		System.out.print(box2);
		System.out.print(box3);
		System.out.print(box4);
		System.out.print(box5);

		
		conBox.addBox(box1);
		conBox.addBox(box2);
		conBox.addBox(box3);
		conBox.getBox(1);
		conBox.getBoxes();
		conBox.deleteBox(1);
		
	}
	
	
}
