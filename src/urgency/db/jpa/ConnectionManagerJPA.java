package urgency.db.jpa;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManagerJPA {
	
	
	public static void main(String [] args) {
	try {
		// Open database connection
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:./db/urgency.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		
		// Here is where I do things with the database
		
		// Close database connection
		c.close();
		System.out.println("Database connection closed.");
	} catch (Exception e) {
		e.printStackTrace();
	}

 }
}
