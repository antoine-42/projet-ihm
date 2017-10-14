import java.sql.*;


public class InternalDB {
    private DB dataBase;


    InternalDB(){
        dataBase = new DB("bohl", "bohl", "bohl");
    }


    Room[] searchRoom(int roomType){
        String query = "SELECT * FROM Chambre WHERE Categorie = ? AND Nettoyee AND !Occupee";
        String[] args = {String.valueOf(roomType +1)};

        return Room.resultSetToRooms(dataBase.executeQuery(query, args));
    }


    boolean closeConnection(){
        return this.dataBase.closeConnection();
    }
}