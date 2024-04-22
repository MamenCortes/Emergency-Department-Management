package urgency.db.jdbc;

import java.sql.Connection;

import urgency.db.interfaces.DoctorManager;

public class ConnectionManager {

	private Connection c;
	private DoctorManager docMan;
	
	public Connection getConnection() {
		return c;
	}
	
	public ConnectionManager() {
		this.connect();
		this.docMan = new JDBCDoctorManager(this);
}

	private void connect() {
		// TODO Auto-generated method stub
		
	}

	public DoctorManager getDocMan() {
		return docMan;
	}

	
}
