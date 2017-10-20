import javax.swing.* ;
import java.awt.* ;


public class MenuDeroulant extends JComponent
{
	private JComboBox<String> listeJour ;
	private JComboBox<String> listeMois ;
	private JComboBox<String> listeAnnee ;

	public MenuDeroulant()
	{
	} 

	public JPanel createConteneur()
	{

		JPanel panneau = new JPanel() ;

		int nbrJourParMois = 31 ;
		int nbrMoisParAn = 12 ;
		int anneeEnCours = 2017 ; //MODIFIER POUR PLUS TARD --> AUTOMATISATION

		/*
		String[] mois = new String[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"} ;
		*/

		this.listeJour = new JComboBox<String>() ;
		this.listeMois = new JComboBox<String>() ;
		this.listeAnnee = new JComboBox<String>() ;
		int i ;

		//Rempli une liste de jours
		for(i=1 ; i<=nbrJourParMois ; i++)
			this.listeJour.addItem(String.valueOf(i)) ;

		//Rempli une liste de mois
		for(i=1 ; i<=nbrMoisParAn ; i++)
			this.listeMois.addItem(String.valueOf(i)) ;

		//Rempli une liste d'années
		for(i=-1 ; i<=1 ; i++)
			this.listeAnnee.addItem(String.valueOf(anneeEnCours + i)) ;


		MenuActionListener controleur = new MenuActionListener(this, listeJour, listeMois, listeAnnee) ;

		JButton valider = new JButton("Valider") ;
		valider.addActionListener(controleur) ;


		panneau.add(listeJour) ;
		panneau.add(listeMois) ;
		panneau.add(listeAnnee) ;
		panneau.add(valider) ;

		return panneau ;
	}

	public JComboBox<String> getListeJour()
	{
		return listeJour ;
	}

	public JComboBox getListeMois()
	{
		return listeMois ;
	}

	public JComboBox<String> getListeAnnee()
	{
		return listeAnnee ;
	}

	public String dateSelected(Object jour, Object mois, Object annee)
	{
		String currentDate = annee+"-"+mois+"-"+jour ;
		return currentDate ;
	}


}