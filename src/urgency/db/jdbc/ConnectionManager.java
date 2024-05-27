package urgency.db.jdbc;

import urgency.db.interfaces.*;
import urgency.db.jpa.JPARoleManager;
import urgency.db.jpa.JPAUserManager;
import urgency.db.pojos.Doctor;
import urgency.db.pojos.Role;
import urgency.db.pojos.Speciality;
import urgency.db.pojos.User;
import urgency.xml.utils.XmlManager;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.persistence.RollbackException;



public class ConnectionManager {

	private Connection connection;
	private DoctorManager docMan;
	private PatientManager patientMan; 
	private BoxManager boxManager; 
	private TriageManager triageManager; 
	private SpecialityManager specialityManager;
	private XmlManager xmlMan; 
	private JPAUserManager userMan; 
	private JPARoleManager roleMan; 
	
	public Connection getConnection() {
		return connection;
	}
	
	public ConnectionManager() {
		this.createConnection();
		createTables();
		this.specialityManager = new JDBCSpecialityManager(this); //se tiene q crear antes sino da error
	    this.patientMan = new JDBCPatientManager(this); 
		this.boxManager = new JDBCBoxManager(this); 
		this.docMan = new JDBCDoctorManager(this);
		this.triageManager = new JDBCTriageManager(this); 
		this.xmlMan = new XmlManager(this); 
		userMan = new JPAUserManager(); 
		roleMan = new JPARoleManager();
		specialityManager.addRandomSpecialities();
		createUsers(); 
		patientMan.createRandomPatients();
		triageManager.createRandomTriages();
		boxManager.createRandomBoxes(); 
		
	}
	
	

	//Creates a connection with the database
	private void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:./db/EmergencyDB.db"); //The folder must exist. If the database doesn't exist, it will be created. 
			connection.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Connection and table created");
			this.createTables();
		} catch (ClassNotFoundException e) {
			System.out.println("Error opening connection");
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error opening connection");
			//e.printStackTrace();
		} 
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			System.out.println("Error closing connection");
			//e.printStackTrace();
		} 
	}
	
	private void createUsers() {
		List<User> users = userMan.getAllUsers(); 
		try {	
			if(users.isEmpty()) {
				Role nurseRole = roleMan.getRole("Nurse"); 
				User u = new User("maria.gala@hospital.com", "12345678", nurseRole);
				userMan.register(u); 
				Role managerRole = roleMan.getRole("Manager"); 
				User u2 = new User("mamen.cortes@hospital.com", "12345678", managerRole);
				userMan.register(u2); 
				Role recepRole = roleMan.getRole("Recepcionist");
				User u3 = new User("carmen.navarro@hospital.com", "12345678", recepRole);
				userMan.register(u3); 
				System.out.println("Random users added");
			}
		}catch (RollbackException | NoSuchAlgorithmException e) {
			System.out.println("Error adding random Doctor users");
			//e.printStackTrace();
		} 
		try {	
			String sql = "SELECT COUNT(*) FROM Doctors"; 
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql); 
			rs.next(); 
			int numDoctors = rs.getInt(1); 
			rs.close();
			if(numDoctors == 0) {
				//Doctors 
				Role doctorRole = roleMan.getRole("Doctor"); 
				User u = new User("sam.bennett@hospital.com", "12345678", doctorRole); 
				Doctor d1 = new Doctor("Sam", "Bennett", new Speciality("Family medicine"), "sam.bennett@hospital.com");
				userMan.register(u);
				docMan.addDoctor(d1);
				User u2 = new User("meredith.grey@hospital.com", "12345678", doctorRole);
				Doctor d2 = new Doctor("Meredith", "Grey", new Speciality("Internal medicine"), "meredith.grey@hospital.com");
				userMan.register(u2); 
				docMan.addDoctor(d2);
				User u3 = new User("owen.hunt@hospital.com", "12345678", doctorRole);
				Doctor d3 = new Doctor("Owen", "Hunt", new Speciality("Emergency medicine"), "owen.hunt@hospital.com");
				userMan.register(u3); 
				docMan.addDoctor(d3);
				User u4 = new User("carina.deluca@hospital.com", "12345678", doctorRole);
				Doctor d4 = new Doctor("Carina", "DeLuca", new Speciality("Obstetrics and gynecology"), "carina.deluca@hospital.com");
				userMan.register(u4); 
				docMan.addDoctor(d4);
				User u5 = new User("alex.karev@hospital.com", "12345678", doctorRole);
				Doctor d5 = new Doctor("Alex", "Karev", new Speciality("Pediatrics"), "alex.karev@hospital.com");
				userMan.register(u5); 
				docMan.addDoctor(d5);
				User u6 = new User("raj.sen@hospital.com", "12345678", doctorRole);
				Doctor d6 = new Doctor("Raj", "Sen", new Speciality("Psychiatry"), "raj.sen@hospital.com");
				userMan.register(u6); 
				docMan.addDoctor(d6);
				System.out.println("Random doctors added");
			}
		} catch (RollbackException | NoSuchAlgorithmException |SQLException e) {
			System.out.println("Error adding random Doctor users");
			//e.printStackTrace();
		} 

	}
		

	
	public JPAUserManager getUserMan() {
		return userMan;
	}

	public void setUserMan(JPAUserManager userMan) {
		this.userMan = userMan;
	}

	public JPARoleManager getRoleMan() {
		return roleMan;
	}

	public void setRoleMan(JPARoleManager roleMan) {
		this.roleMan = roleMan;
	}

	public XmlManager getXmlMan() {
		return xmlMan;
	}

	public void setXmlMan(XmlManager xmlMan) {
		this.xmlMan = xmlMan;
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
					+ " status TEXT NOT NULL CHECK (status = 'waiting' OR status = 'waitingInLine' OR status = 'assisted' OR status = 'assistedInBox' OR status = 'emergency room' "
					+ "OR status = 'discharged' OR status = 'hospitalized'),"
					+ " urgency INTEGER CHECK (urgency = 1 OR urgency = 2 OR urgency = 3 OR urgency = 4 OR urgency = 5))";
			createTables1.executeUpdate(createPatients);
			createTables1.close();

			Statement createTables3 = connection.createStatement();
			String createSpecialities = "CREATE TABLE IF NOT EXISTS Specialities ( "
					+ " type TEXT PRIMARY KEY)";
			createTables3.executeUpdate(createSpecialities);
			createTables3.close();
			
			Statement createTables2 = connection.createStatement();
			String createDoctors = "CREATE TABLE IF NOT EXISTS Doctors ( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " surname TEXT NOT NULL,"
					+ " email TEXT NOT NULL,"
					+ " speciality_type TEXT NOT NULL REFERENCES Specialities(type) ON DELETE RESTRICT,"
					+ " in_box Boolean NOT NULL)"; 
			createTables2.executeUpdate(createDoctors);
			createTables2.close();
			
			
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
					+ " speciality_type TEXT NOT NULL REFERENCES Specialities(type) ON DELETE RESTRICT)";
			createTables5.executeUpdate(createBoxes);
			createTables5.close();
			
			Statement createTables6 = connection.createStatement();
			String createPatientSpeciality = "CREATE TABLE IF NOT EXISTS PatientSpeciality ( "
					+ " patient_id INTEGER REFERENCES Patients(id) ON DELETE CASCADE,"
					+ " speciality_type TEXT REFERENCES Specialities(type) ON DELETE RESTRICT,"
					+ " date DATETIME NOT NULL,"
					+ " PRIMARY KEY (patient_id, speciality_type, date))";
			createTables6.executeUpdate(createPatientSpeciality);
			createTables6.close();
			
			Statement createTables7 = connection.createStatement();
			String createPatientTriage = "CREATE TABLE IF NOT EXISTS PatientTriage ( "
					+ " patient_id INTEGER REFERENCES Patients(id) ON DELETE CASCADE,"
					+ " triage_id INTEGER REFERENCES Triages(id) ON DELETE SET NULL,"
					+ " date DATETIME NOT NULL,"
					+ " PRIMARY KEY (patient_id, triage_id, date))";
			createTables7.executeUpdate(createPatientTriage);
			createTables7.close();
			
			Statement createTables8 = connection.createStatement();
			String createPatientBox = "CREATE TABLE IF NOT EXISTS PatientBox ( "
					+ " patient_id INTEGER REFERENCES Patients(id) ON DELETE CASCADE,"
					+ " box_id INTEGER REFERENCES Boxes(id) ON DELETE SET NULL,"
					+ " date DATETIME NOT NULL,"
					+ " comments TEXT,"
					+ " PRIMARY KEY (patient_id, box_id, date))";
			createTables8.executeUpdate(createPatientBox);
			createTables8.close();
			
			
			Statement createTables9 = connection.createStatement();
			String createBoxDoctor = "CREATE TABLE IF NOT EXISTS BoxDoctor ( "
					+ " box_id INTEGER REFERENCES Boxes(id) ON DELETE CASCADE,"
					+ " doctor_id INTEGER REFERENCES Doctors(id) ON DELETE SET NULL,"
					+ " date DATETIME NOT NULL,"
					+ " PRIMARY KEY (box_id, doctor_id, date))";
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
	
}
