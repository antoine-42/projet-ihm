class InternalDB {
    private DB dataBase;


    @SuppressWarnings("SpellCheckingInspection")
    InternalDB(){
        dataBase = new DB("bohl", "bohl", "bohl");
    }


    @SuppressWarnings("SpellCheckingInspection")
    Room[] searchRoom(int roomType){
        String query = "SELECT * FROM Chambre WHERE Categorie = ? AND Nettoyee AND !Occupee";
        String[] args = {String.valueOf(roomType +1)};

        return Room.resultSetToRooms(dataBase.executeQuery(query, args));
    }
    @SuppressWarnings("SpellCheckingInspection")
    void affectRoom(int roomNumber){
        String query = "UPDATE Chambre SET Occupee=1 WHERE Numero = ?";
        String[] args = {String.valueOf(roomNumber)};

        dataBase.executeQuery(query, args);
    }


    boolean closeConnection(){
        return this.dataBase.closeConnection();
    }
}
