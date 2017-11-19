import java.awt.* ;

public class Main
{
	public static void main(String[] args)
	{
		DBHistorique test = new DBHistorique("2017-11-16", "2017-11-18") ;
		System.out.println(test.moy_occupation + "/" + test.moy_presentation) ;
		
		Fenetre menu = new Fenetre() ;
		menu.setVisible(true) ;
		
	}
	
}
