import java.awt.* ;
import java.sql.* ;
import java.lang.* ;

public class Statistiques
{
	InternalDB interne ;


	Statistiques()
	{
		 interne = new InternalDB()	;
	}

	public void getOccupation1Day(String day)
	{
		String occupation = null ;

		//occupation = interne.getOccupationRate(day) ;

		System.out.println(occupation) ;
	}

	public void getOccupationRangeDay(String init, String last)
	{
		int[] initInt = conversionDate(init) ;
		System.out.println(initInt[0]) ;
		System.out.println(initInt[1]) ;
		System.out.println(initInt[2]) ;

		int[] lastInt = conversionDate(last) ;
		System.out.println(lastInt[0]) ;
		System.out.println(lastInt[1]) ;
		System.out.println(lastInt[2]) ;


	}

	public int[] conversionDate(String dateStr)
	{
		int[] dateInt = new int[3] ;
		String tmp = null ;

		int i = 0 ; //Compteur de boucle
		int y = 0 ; //Indice tableau

		//Année
		while(!dateStr.substring(1, i).equals("-"))
		{
			tmp = tmp+dateStr.substring(1, i) ;
			i+=1 ;
		}
		i=+1 ;

		dateInt[y] = Integer.parseInt(tmp) ;
		y+=1 ;

		//Mois
		while(!dateStr.substring(1, i).equals("-"))
		{
			tmp = tmp+dateStr.substring(1, i) ;
			i+=1 ;
		}
		i=+1 ;

		dateInt[y] = Integer.parseInt(tmp) ;
		y+=1 ;

		//Jour
		while(!dateStr.substring(1, i).equals("-"))
		{
			tmp = tmp+dateStr.substring(1, i) ;
			i+=1 ;
		}
		dateInt[y] = Integer.parseInt(tmp) ;

		return dateInt ;

	}


	



	//interne.closeConnection() ;

}