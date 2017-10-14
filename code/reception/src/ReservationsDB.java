import java.sql.*;


class ReservationsDB {
    private static String reservationSearchQueryPrefix = "SELECT * FROM Reservation JOIN Client ON ";
    private static String reservationSearchQuerySuffix = "AND Reservation.client = Client.id AND Reservation.debut >= CURDATE()";

    private DB dataBase;


	ReservationsDB(){
        this.dataBase = new DB("projetihm", "projetihm", "mhitejorp");
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
        return Reservation.resultSetToReservations(this.dataBase.executeQuery(reservationSearchQueryPrefix + query + reservationSearchQuerySuffix, args));
    }


	boolean closeConnection(){
        return this.dataBase.closeConnection();
	}
}