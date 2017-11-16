import java.sql.* ;


class DBHistorique
{
    int taux_occupation;
    int taux_non_presentation;

    DB database;
    
    DBHistorique(int taux_occupation_, int taux_non_presentation_) {
        this.database = new DB("bohl", "bohl", "bohl");

        this.taux_occupation = taux_occupation_;
        this.taux_non_presentation = taux_non_presentation_;
    }
    DBHistorique(String date){
        this.database = new DB("bohl", "bohl", "bohl");

        String query = "SELECT * FROM Historique WHERE jour = ?";
        String[] args = {date};

        ResultSet result = this.database.executeQuery(query, args);
        try{
            if (result.next()){
                result.absolute(1) ;
                this.taux_occupation = result.getInt("taux_occupation");
                this.taux_non_presentation = result.getInt("taux_non_presentation");
            }
        }
        catch(SQLException e){
            System.out.println("[FATAL] could not parse DB output");
            e.printStackTrace();
        }
    }
}