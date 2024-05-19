package urgency.db.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import urgency.db.jdbc.JDBCDoctorManager;

import urgency.db.interfaces.UserManager;
import urgency.db.pojos.Role;
import urgency.db.pojos.User;

public class ConnectionManagerJPA {
	
	private Connection connection;
	
	public static UserManager userMan;
	
	public JDBCDoctorManager doctorMan;
	
	public ConnectionManagerJPA() {
	try {
	
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:./db/urgency.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		createTablesJPA();
		c.close();
		System.out.println("Database connection closed.");
	} catch (Exception e) {
		System.out.println("Error in the JPA database");
		e.printStackTrace();
	 }
	}
		
		private void createTablesJPA() {
			
			try {
				Statement s = connection.createStatement();
				//user = new JPAUserManager();
				
				String table1 = "CREATE TABLE users (username TEXT NOT NULL, " + "password TEXT NOT NULL, " + "role TEXT NOT NULL REFERENCES roles(id)";
				s.executeUpdate(table1);
				
				String insertTable1 = "INSERT INTO users (username, password, role) VALUES ('ramonperez@hospital.es', 'pasword1', 'Doctor')";
				String insertTable2 = "INSERT INTO users (username, password, role) VALUES ('elenagomez@hospital.es', 'elena123', 'Doctor')";
				s.executeUpdate(insertTable1);
				s.executeUpdate(insertTable2);
				
				s.close();
				
			} catch (SQLException e) {
				if (e.getMessage().contains("already exist")) {
					System.out.println("Tables already created.");
					return;
				}
				System.out.println("Database error.");
				e.printStackTrace();
			}
	
			
			
		}
		
		public static void main(String [] args) {
			User u = new User("ramonperez@hospital.es", "pasword1", new Role("Doctor"));
			User u2 = new User("elenagomez@hospital.es", "elena123", new Role("Recepcionist"));
			User u3 = new User("martagimenez@hospital.es", "martagm1", new Role("Manager"));
			User u4 = new User("gerardoprados@hospital.es", "user1234", new Role("Nurse"));
			UserManager userMan = new JPAUserManager();
			System.out.println(userMan.login(u.getUsername(), u.getPassword()));
			
			
		}
		
        
		

 }

