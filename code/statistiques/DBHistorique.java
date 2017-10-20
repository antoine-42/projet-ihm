import java.sql.ResultSet ;
import java.sql.SQLException ;


/*
class DBHistorique
{
	int recuperation ;
	
    DBHistorique(int recuperation_)
    {
        this.recuperation = recuperation_ ;
    }


    static DBHistorique setHistoriqueResult(ResultSet result)
    {
        try{
            result.last();
            int resultNumber = result.getRow();
            DBHistorique[] historique = new DBHistorique[resultNumber];

            result.absolute(1) ;

                int recuperation = result.getInt("Numero");
                int category = result.getInt("Categorie") -1;
                boolean clean = result.getBoolean("Nettoyee");
                boolean occupied = result.getBoolean("Occupee");
                String description = result.getString("Description");

                rooms[i] = new Room(number, category, occupied, clean, description);
                result.next();
            }

            return rooms;
        }
        catch(SQLException e){
            System.out.println("[FATAL] could not parse DB output");
            e.printStackTrace();
            return null;
        }
    }
*/