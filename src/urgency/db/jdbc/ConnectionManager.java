package urgency.db.jdbc;

import urgency.db.interfaces.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



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
		this.createConnection();
		this.docMan = new JDBCDoctorManager(this);
		this.triageManager = new JDBCTriageManager(this); 
		/*this.patientMan = new JDBCPatientManager(this); 
		this.boxManager = new JDBCBoxManager(this); 
		
		this.specialityManager = new JDBCSpecialityManager(this); */
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

	public BoxManager getBoxManager() {
		return boxManager;
	}


	public TriageManager getTriageManager() {
		return triageManager;
	}


	public SpecialityManager getSpecialityManager() {
		return specialityManager;
	}

	
	private void createTables() {
		try {
			// Create the tables
			Statement createTables1 = connection.createStatement();
			String createPatients = "CREATE TABLE IF NOT EXISTS Patients ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " surname TEXT NOT NULL,"
					+ " birthdate DATE NOT NULL,"
					+ " sex TEXT NOT NULL CHECK (sex = 'Man' OR sex ='Woman'),"
					+ " weight INTEGER,"
					+ " height INTEGER,"
					+ " status TEXT NOT NULL CHECK (status = 'waiting' OR status = 'assisted' OR status = 'emergency room' "
					+ "OR status = 'discharged' OR status = 'hospitalized'),"
					+ " urgency INTEGER CHECK (urgency = 1 OR urgency = 2 OR urgency = 3 OR urgency = 4 OR urgency = 5))";
			createTables1.executeUpdate(createPatients);
			createTables1.close();
			
			Statement createTables2 = connection.createStatement();
			String createDoctors = "CREATE TABLE IF NOT EXISTS Doctors ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " surname TEXT NOT NULL,"
					+ " speciality_type TEXT NOT NULL REFERENCES Speciality(type) ON DELETE RESTRICT,"
					+ " in_box Boolean NOT NULL)";
			createTables2.executeUpdate(createDoctors);
			createTables2.close();
			
			Statement createTables3 = connection.createStatement();
			String createSpecialities = "CREATE TABLE IF NOT EXISTS Specialities ( "
					+ " type TEXT PRIMARY KEY)";
			createTables3.executeUpdate(createSpecialities);
			createTables3.close();
			
			Statement createTables4 = connection.createStatement();
			String createTriages = "CREATE TABLE IF NOT EXISTS Triages ("
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " available Boolean NOT NULL)";
			createTables4.executeUpdate(createTriages);
			createTables4.close();
	
			Statement createTables5 = connection.createStatement();
			String createBoxes = "CREATE TABLE IF NOT EXISTS Boxes ("
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " available Boolean NOT NULL,"
					+ " speciality_type TEXT)";
			createTables5.executeUpdate(createBoxes);
			createTables5.close();
			
			Statement createTables6 = connection.createStatement();
			String createPatientSpeciality = "CREATE TABLE IF NOT EXISTS PatientSpeciality ( "
					+ " patient_id INTEGER REFERENCES Patients(id),"
					+ " speciality_type TEXT REFERENCES Specialities(type),"
					+ " date DATETIME NOT NULL,"
					+ " PRIMARY KEY (patient_id, speciality_type))";
			createTables6.executeUpdate(createPatientSpeciality);
			createTables6.close();
			
			Statement createTables7 = connection.createStatement();
			String createPatientTriage = "CREATE TABLE IF NOT EXISTS PatientTriage ( "
					+ " patient_id INTEGER REFERENCES Patients(id),"
					+ " triage_id INTEGER REFERENCES Triages(id),"
					+ " date DATETIME NOT NULL,"
					+ " PRIMARY KEY (patient_id, triage_id))";
			createTables7.executeUpdate(createPatientTriage);
			createTables7.close();
			
			Statement createTables8 = connection.createStatement();
			String createPatientBox = "CREATE TABLE IF NOT EXISTS PatientBox ( "
					+ " patient_id INTEGER REFERENCES Patients(id),"
					+ " box_id INTEGER REFERENCES Boxes(id),"
					+ " date DATETIME NOT NULL,"
					+ " comments TEXT,"
					+ " PRIMARY KEY (patient_id, box_id))";
			createTables8.executeUpdate(createPatientBox);
			createTables8.close();
			
			
			Statement createTables9 = connection.createStatement();
			String createBoxDoctor = "CREATE TABLE IF NOT EXISTS BoxDoctor ( "
					+ " box_id INTEGER REFERENCES Boxes(id),"
					+ " doctor_id INTEGER REFERENCES Doctors(id),"
					+ " date DATETIME NOT NULL,"
					+ " PRIMARY KEY (box_id, doctor_id))";
			createTables9.executeUpdate(createBoxDoctor);
			createTables9.close();
			
		} catch (SQLException sqlE) {
			if (sqlE.getMessage().contains("already exist")){
				System.out.println("No need to create the tables; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
				System.out.println(sqlE.getMessage());
			}
		}
	}
	
	
	public static void main(String[] args) {
		ConnectionManager conMan = new ConnectionManager(); 
		conMan.createConnection();
		conMan.createTables();
		conMan.closeConnection();
	}
	
}
