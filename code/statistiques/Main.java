import java.awt.* ;

public class Main
{
	public static void main(String[] args)
	{
		Fenetre menu = new Fenetre() ;
		menu.setVisible(true) ;

		Statistiques test = new Statistiques() ;
		test.getOccupationRangeDay("2017-10-20", "2017-1-20") ;


	}
	
}