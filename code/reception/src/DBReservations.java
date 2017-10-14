import java.sql.*;

class DBReservations{
	boolean offline = false;
	private Connection connection;


	DBReservations(){
		try{
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			this.offline = true;
			System.out.println("[FATAL] could not load db driver");
			e.printStackTrace();
		}

		try{
			connection = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
		}
		catch(SQLException e) {
			offline = true;
			System.out.println("[FATAL] could not connect to db");
			e.printStackTrace();
		}
	}

	Reservation[] searchReservationFullName(String lastName, String name){
		String query = "SELECT * FROM Reservation JOIN Client ON Client.nom = ? AND Client.prenom = ? AND Reservation.client = Client.id";
		String[] args = {lastName, name};

		return executeQuery(query, args);
	}
	Reservation[] searchReservationLastName(String lastName){
		String query = "SELECT * FROM Reservation JOIN Client ON Client.nom = ? AND Reservation.client = Client.id";
		String[] args = {lastName};

		return executeQuery(query, args);
	}
	Reservation[] searchReservationName(String name){
		String query = "SELECT * FROM Reservation JOIN Client ON Client.prenom = ? AND Reservation.client = Client.id";
		String[] args = {name};

		return executeQuery(query, args);
	}
	Reservation[] searchReservationRef(String reference){
		String query = "SELECT * FROM Reservation JOIN Client ON Reservation.reference = ? AND Reservation.client = Client.id";
		String[] args = {reference};

		return executeQuery(query, args);
	}

	private Reservation[] executeQuery(String query, String[] args){
		ResultSet results = null;
		try{
			PreparedStatement statement = connection.prepareStatement(query);
			for (int i = 0; i < args.length; i++) {
				statement.setString(i+1, args[i]);
			}
			results = statement.executeQuery();

			try{
				statement.close();
			}
			catch(Exception e){
				System.out.println("could not close connection");
			}
		}
		catch(SQLException e) {
			offline = true;
			System.out.println("[FATAL] could not connect to db");
			e.printStackTrace();
		}

		if (offline) {
			return null;
		}
		return Reservation.resultSetToReservations(results);
	}


	boolean closeConnection(){
		try{
			connection.close();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
}