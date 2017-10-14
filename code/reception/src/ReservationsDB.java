import java.sql.*;


class ReservationsDB {
    private static String reservationSearchQueryPrefix = "SELECT * FROM Reservation JOIN Client ON ";
    private static String reservationSearchQuerySuffix = "AND Reservation.client = Client.id AND Reservation.debut >= CURDATE()";

	boolean offline = false;
	private Connection connection;


	ReservationsDB(){
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
		String query = "Client.nom = ? AND Client.prenom = ? ";
		String[] args = {lastName, name};

		return executeReservationSearchQuery(query, args);
	}
	Reservation[] searchReservationLastName(String lastName){
		String query = "Client.nom = ? ";
		String[] args = {lastName};

		return executeReservationSearchQuery(query, args);
	}
	Reservation[] searchReservationName(String name){
        String query = "Client.prenom = ? ";
		String[] args = {name};

		return executeReservationSearchQuery(query, args);
	}
	Reservation[] searchReservationRef(String reference){
        String query = "Reservation.reference = ? ";
		String[] args = {reference};

		return executeReservationSearchQuery(query, args);
	}

    private Reservation[] executeReservationSearchQuery(String query, String[] args){
        return Reservation.resultSetToReservations(executeQuery(reservationSearchQueryPrefix + query + reservationSearchQuerySuffix, args));
    }

	private ResultSet executeQuery(String query, String[] args){
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
		return results;
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