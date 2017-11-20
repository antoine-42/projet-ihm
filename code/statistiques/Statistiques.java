import java.awt.* ;
import java.sql.* ;
import java.lang.* ;

public class Statistiques
{

	Statistiques()
	{
	}

	public String getOccupation1Day(String day)
	{
		String occupation ;

		occupation = String.valueOf(new DBHistorique(day).taux_occupation) ;

		return occupation ;
	}
	
	public String getNonOccupation1Day(String day)
	{
		String non_presentation ;

		non_presentation = String.valueOf(new DBHistorique(day).taux_non_presentation) ;

		return non_presentation ;
	}

	public String getOccupationPeriode(String dayStart, String dayEnd)
	{
		String occupation ;

		occupation = String.valueOf(new DBHistorique(dayStart, dayEnd).moy_occupation) ;

		return occupation ;
	}

	public String getNonOccupationPeriode(String dayStart, String dayEnd)
	{
		String non_presentation ;

		non_presentation = String.valueOf(new DBHistorique(dayStart, dayEnd).moy_presentation) ;

		return non_presentation ;
	}
}
