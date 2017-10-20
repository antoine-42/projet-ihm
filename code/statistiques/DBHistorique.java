import java.sql.ResultSet ;
import java.sql.SQLException ;



class DBHistorique
{
	int recuperation ;
	
    DBHistorique(int recuperation_)
    {
        this.recuperation = recuperation_ ;
    }


    static DBHistorique[] resultSetToRooms(ResultSet result)
    {
        try{
            result.last();
            int resultNumber = result.getRow();
            Room[] rooms = new Room[resultNumber];

            result.absolute(1);
            for (int i = 0; i < resultNumber; i++) {
                int number = result.getInt("Numero");
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
