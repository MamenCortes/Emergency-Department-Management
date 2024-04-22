package urgency.db.jdbc;

import urgency.db.interfaces.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionManager {

	private Connection connection;
	private DoctorManager docMan;
	private PatientManager patientMan; 
	private BoxManager boxManager; 
	private TriageManager triageManager; 
	private SpecialityManager specialityManager; 
	
	public Connection getConnection() {
		return connection;
	}
	
	public ConnectionManager() {
		this.connect();
		this.docMan = new JDBCDoctorManager(this);
		/*this.patientMan = new JDBCPatientManager(this); 
		this.boxManager = new JDBCBoxManager(this); 
		this.triageManager = new JDBCTriageManager(this); 
		this.specialityManager = new JDBCSpecialityManager(this); */
	}

	private void connect() {
		// TODO Auto-generated method stub
		
	}
	//Creates a connection with the database
	private void createConnection() {
		//TODO if the tables are already created, skip this step. 
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:./db/EmergencyDB.db"); //The folder must exist. If the database doesn't exist, it will be created. 
			connection.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Connection and table created");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public DoctorManager getDocMan() {
		return docMan;
	}

	public PatientManager getPatientMan() {
		return patientMan;
	}

	public void setPatientMan(PatientManager patientMan) {
		this.patientMan = patientMan;
	}

	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager(); 
		conMan.createConnection();
		conMan.closeConnection();
	}
}
