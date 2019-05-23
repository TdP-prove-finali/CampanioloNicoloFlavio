package it.polito.tdp.provafinale.supermarket.db;

	import java.sql.Connection;
	import java.sql.SQLException;

	import com.zaxxer.hikari.HikariDataSource;

	/**
	 * Utility class for connecting to the database
	 * 
	 * Uses the HikariCP library for managing a connection pool
	 * @see <a href="https://brettwooldridge.github.io/HikariCP/">HikariCP</a>
	 */
	public class ConnectDB {

		private static final String jdbcURL = "jdbc:mysql://localhost/gestione_supermercati?serverTimezone=Europe/Rome";
		private static HikariDataSource ds = null;

		public static Connection getConnection() {

		
			
			if (ds == null) {
				
				ds = new HikariDataSource();

				ds.setJdbcUrl(jdbcURL);
				ds.setUsername("root");
				

				

				
				
			}

			try {

				return ds.getConnection();

			} catch (SQLException e) {
				System.err.println("Errore connessione al DB");
				//throw new RuntimeException(e);
				
			}
			return null;
		}
}
