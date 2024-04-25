package urgency.db.jdbc;

import urgency.db.pojos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			String template = "DELETE FROM Box WHERE id = ?";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
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
			String template = "INSERT INTO Box (available) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(template);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getPatientInBox(int Box_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Box getBox(int id) {
		try {
			String sql = "SELECT * FROM Box WHERE id = " + id;
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Box b = new Box (rs.getInt("id"), rs.getBoolean("available"));
			return b;
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return null;
	}


}
