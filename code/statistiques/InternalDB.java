import java.sql.*;

class InternalDB {
    private DB dataBase;


    InternalDB(){
        dataBase = new DB("bohl", "bohl", "bohl");
    }

    /*
    Room[] searchRoom(int roomType){
        String query = "SELECT * FROM Chambre WHERE Categorie = ? AND Nettoyee AND !Occupee";
        String[] args = {String.valueOf(roomType +1)};

        return Room.resultSetToRooms(dataBase.executeQuery(query, args));
    }
    void affectRoom(int roomNumber){
        String query = "UPDATE Chambre SET Occupee=1 WHERE Numero = ?";
        String[] args = {String.valueOf(roomNumber)};

        dataBase.executeQuery(query, args);
    }
    */


    void getOccupationRate(String day){}/*
    {
        String query = "SELECT taux_occupation FROM Historique WHERE jour = ? ;" ;
        String[] args = {day} ;
        Resultset resultat = dataBase.executeQuery(query, args)) ;

        return resultat.getInt.("taux_occupation") ;
    }
    */

    void getCurrentOccupationRate(int nbr_rooms)
    {
        String query = "SELECT COUNT(*)/?*100 FROM Chambre WHERE Occupee=1 ;" ;
        String[] args = {String.valueOf(nbr_rooms)} ;

        //return dataBase.executeQuery(query, args) ;
    }




    boolean closeConnection(){
        return this.dataBase.closeConnection();
    }
}
