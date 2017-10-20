import javax.swing.* ;
import java.awt.* ;


public class MenuDeroulant extends JFrame
{
	private JComboBox listeJour ;
	private JComboBox listeMois ;
	private JComboBox listeAnnee ;

	public MenuDeroulant()
	{
		super("Statistiques") ;

		Dimension taille = new Dimension(750, 750) ;
		Dimension tailleMini = new Dimension(250, 250) ;
		Dimension tailleMaxi = new Dimension(1500, 1500) ;
		//Dimension position = new Dimension(20, 20) ;

		this.setMinimumSize(tailleMini) ;
		this.setMaximumSize(tailleMaxi) ;
		this.setSize(taille) ;
		this.setLocation(20, 20) ;

		this.setContentPane(createConteneur()) ;

	} 

	private JPanel createConteneur()
	{

		JPanel panneau = new JPanel() ;

		int nbrJourParMois = 31 ;
		int anneeEnCours = 2017 ; //MODIFIER POUR PLUS TARD --> AUTOMATISATION
		//Object[] jour = new Object[nbrJourParMois];
		Object[] jour = new Object[]{1, 2, 3} ;
		Object[] mois = new Object[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"} ;
		//Object[] annee = new Object[3] ;
		Object[] annee = new Object[]{2016, 2017, 2018} ;

		int i ;

		/*
		//Rempli une liste de jours
		for(i=1 ; i<=nbrJourParMois ; i++)
			jour[i] = i ;
		*/

		/*
		//Rempli une liste d'années
		for(i=-1 ; i<=1 ; i++)
			annee[i] = anneeEnCours + i ;
		*/


		this.listeJour = new JComboBox(jour) ;
		this.listeMois = new JComboBox(mois) ;
		this.listeAnnee = new JComboBox(annee) ;

		panneau.add(listeJour) ;
		panneau.add(listeMois) ;
		panneau.add(listeAnnee) ;

		return panneau ;
	}

	public JComboBox getListeJour()
	{
		return listeJour ;
	}

	public JComboBox getListeMois()
	{
		return listeMois ;
	}

	public JComboBox getListeAnnee()
	{
		return listeAnnee ;
	}


}