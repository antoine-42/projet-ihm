import javax.swing.* ;
import java.awt.* ;


public class MenuDeroulant extends JComponent
{
	private JComboBox<String> listeJour ;
	private JComboBox<String> listeMois ;
	private JComboBox<String> listeAnnee ;
	private String currentDate ;

	public MenuDeroulant()
	{
	} 

	public JPanel createConteneur()
	{

		JPanel panneau = new JPanel() ;

		JLabel labelDebut = new JLabel("Debut") ;

		/*Liste déroulante*/
		int nbrJourParMois = 31 ;
		int nbrMoisParAn = 12 ;
		int anneeEnCours = 2017 ; //MODIFIER POUR PLUS TARD --> AUTOMATISATION

		this.listeJour = new JComboBox<String>() ;
		this.listeMois = new JComboBox<String>() ;
		this.listeAnnee = new JComboBox<String>() ;
		
		int i ; //Compteur

		//Rempli une liste de jours
		for(i=1 ; i<=nbrJourParMois ; i++)
			this.listeJour.addItem(String.valueOf(i)) ;

		//Rempli une liste de mois
		for(i=1 ; i<=nbrMoisParAn ; i++)
			this.listeMois.addItem(String.valueOf(i)) ;

		//Rempli une liste d'années
		for(i=-1 ; i<=1 ; i++)
			this.listeAnnee.addItem(String.valueOf(anneeEnCours + i)) ;

		/*Contrôleur*/
		MenuActionListener controleur = new MenuActionListener(this, listeJour, listeMois, listeAnnee) ;

		/*Bouton de validation*/
		JButton valider = new JButton("Valider") ;
		valider.addActionListener(controleur) ;

		/*Ajouts au panneau*/
		panneau.add(labelDebut) ;
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

	public void setDateSelected(Object jour, Object mois, Object annee)
	{
		 currentDate = annee+"-"+mois+"-"+jour ;
	}

	public String getDateSelected()
	{
		return currentDate ;
	}

	public void getStats()
	{
		//System.out.println(Statistiques.getOccupation1Day(this.currentDate)) ;
	}


}


/*
	String[] mois = new String[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"} ;
*/