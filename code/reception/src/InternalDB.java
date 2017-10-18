import java.sql.SQLException;


class InternalDB {
    private DB dataBase;


    InternalDB() throws ClassNotFoundException, SQLException{
        this.dataBase = new DB("bohl", "bohl", "bohl");
        this.dataBase.testDB("Chambre");
    }


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


    boolean closeConnection(){
        return this.dataBase.closeConnection();
    }
}
