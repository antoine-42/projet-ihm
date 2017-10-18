import java.awt.* ;

public class Statistiques
{
	InternalDB interne


	Statistiques()
	{
		 interne = new InternalDB()	;
	}

	float getOccupation1Day(date day)
	{
		float occupation ;

		occupation = interne.getOccupationRate(day) ;

		return occupation ;
	}

	float getOccupationRangeDay(date init, date last)
	{
		float occupation = 0 ;
		date i ;

		for(i=init ; i<=last ; i++)
		{
			if (occupation==0)
				occupation+=getOccupation1Day(i) ;
			else
				occupation+=getOccupation1Day(i)/2 ;
		}

		return occupation ;


	}


	



	interne.closeConnection() ;

}