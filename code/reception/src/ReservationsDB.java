import java.sql.SQLException;


class ReservationsDB {
    //test timespamp: 1515193200
    private static String reservationSearchQueryPrefix = "SELECT * FROM Reservation JOIN Client ON ";
    private static String activeReservationSearchQuerySuffix = "AND Reservation.client = Client.id AND 1515193200 >= UNIX_TIMESTAMP(Reservation.debut) AND 1515193200 < UNIX_TIMESTAMP(Reservation.debut) + Reservation.nuits *86400 ORDER BY Reservation.debut ASC, Reservation.nuits DESC";
    private static String allReservationSearchQuerySuffix = "AND Reservation.client = Client.id AND (1515193200 < UNIX_TIMESTAMP(Reservation.debut) OR 1515193200 >= UNIX_TIMESTAMP(Reservation.debut) + Reservation.nuits *86400) ORDER BY Reservation.debut ASC, Reservation.nuits DESC";

    private DB dataBase;


	ReservationsDB() throws ClassNotFoundException, SQLException{
        this.dataBase = new DB("projetihm", "projetihm", "mhitejorp");
        this.dataBase.testDB("Reservation");
	}

	Reservation[] searchActiveReservationFullName(String lastName, String name){
		String query = "Client.nom = ? AND Client.prenom = ? ";
		String[] args = {lastName, name};

		return executeActiveReservationSearchQuery(query, args);
	}
	Reservation[] searchActiveReservationLastName(String lastName){
		String query = "Client.nom = ? ";
		String[] args = {lastName};

		return executeActiveReservationSearchQuery(query, args);
	}
	Reservation[] searchActiveReservationName(String name){
        String query = "Client.prenom = ? ";
		String[] args = {name};

		return executeActiveReservationSearchQuery(query, args);
	}
	Reservation[] searchActiveReservationRef(String reference){
        String query = "Reservation.reference = ? ";
		String[] args = {reference};

		return executeActiveReservationSearchQuery(query, args);
	}

    private Reservation[] executeActiveReservationSearchQuery(String query, String[] args){
        return Reservation.resultSetToReservations(this.dataBase.executeQuery(reservationSearchQueryPrefix + query + activeReservationSearchQuerySuffix, args));
    }

    Reservation[] searchAllReservationFullName(String lastName, String name){
        String query = "Client.nom = ? AND Client.prenom = ? ";
        String[] args = {lastName, name};

        return executeAllReservationSearchQuery(query, args);
    }
    Reservation[] searchAllReservationLastName(String lastName){
        String query = "Client.nom = ? ";
        String[] args = {lastName};

        return executeAllReservationSearchQuery(query, args);
    }
    Reservation[] searchAllReservationName(String name){
        String query = "Client.prenom = ? ";
        String[] args = {name};

        return executeAllReservationSearchQuery(query, args);
    }

    private Reservation[] executeAllReservationSearchQuery(String query, String[] args){
        return Reservation.resultSetToReservations(this.dataBase.executeQuery(reservationSearchQueryPrefix + query + allReservationSearchQuerySuffix, args));
    }


	boolean closeConnection(){
        return this.dataBase.closeConnection();
	}
}