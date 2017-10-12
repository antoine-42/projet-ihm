import java.util.*;
import java.text.*;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Reservation {
	Client client;
	String reference;
	int categorie;
	Date debut;
	Date fin;
	int duree;

	public Reservation(Client client_, String reference_, int categorie_, Date debut_, int duree_){
		client = client_;
		reference = reference_;
		categorie = categorie_;
		debut = debut_;
		duree = duree_;

		fin = new Date(debut.getTime() + 86400*duree);
	}


	static Reservation[] resultSetToReservations(ResultSet result){
		try{
			result.last();
			int resultNumber = result.getRow();
			Reservation[] reservations = new Reservation[resultNumber];

			result.absolute(1);
			for (int i = 0; i < resultNumber; i++) {
				String resultat_nom = result.getString("nom");
				String resultat_prenom = result.getString("prenom");
				Client client = new Client(resultat_nom, resultat_prenom);

				String reference = result.getString("reference");
				int categorie = result.getInt("categorie") -1;
				int duree = result.getInt("nuits");

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = new java.util.Date();
				try{
					date = dateFormat.parse(result.getString("debut"));
				}
				catch(ParseException e){
					System.out.println("[FATAL] could not parse DB output");
					e.printStackTrace();
					return null;
				}

				reservations[i] = new Reservation(client, reference, categorie, date, duree);
				result.next();
			}

			return reservations;
		}
		catch(SQLException e){
			System.out.println("[FATAL] could not parse DB output");
			e.printStackTrace();
			return null;
		}
	}
}