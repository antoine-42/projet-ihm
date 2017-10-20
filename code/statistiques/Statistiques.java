import java.awt.* ;
import java.sql.* ;

public class Statistiques
{
	InternalDB interne ;


	Statistiques()
	{
		 interne = new InternalDB()	;
	}

	public void getOccupation1Day(String day)
	{
		String occupation ;

		occupation = getOccupationRate(day) ;

		System.out.println(occupation) ;
	}

	public void getOccupationRangeDay(String init, String last)
	{
		
	}


	



	//interne.closeConnection() ;

}