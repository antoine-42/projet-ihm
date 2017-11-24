import javax.swing.* ;
import java.awt.* ;


public class MenuDeroulant extends JComponent
{
	private final char pourcent = (char) 37 ;
	private final String message1 = "Pas de résultat pour la date souhaitée" ;

	private JComboBox<String> listeJour ;
	private JComboBox<String> listeMois ;
	private JComboBox<String> listeAnnee ;
	private String currentDate ;
	private String currentDateHuman ;
	private JLabel occupationTexte = new JLabel(message1) ;

	//Deuxième zone
	private JComboBox<String> listeJour2 ;
	private JComboBox<String> listeMois2 ;
	private JComboBox<String> listeAnnee2 ;
	private String currentDate2 ;
	private String currentDateHuman2 ;

	public MenuDeroulant()
	{
	} 

	public JPanel createConteneur()
	{

		JPanel panneau = new JPanel() ;

		JLabel labelDebut = new JLabel("Debut") ;
		JLabel labelFin = new JLabel("Fin") ;

		/*Liste déroulante*/
		final int NBR_JOUR_MOIS = 31 ;
		final int NBR_MOIS_AN = 12 ;
		final int ANNEE_EN_COURS = 2017 ; //MODIFIER POUR PLUS TARD --> AUTOMATISATION

		this.listeJour = new JComboBox<String>() ;
		this.listeMois = new JComboBox<String>() ;
		this.listeAnnee = new JComboBox<String>() ;

		this.listeJour2 = new JComboBox<String>() ;
		this.listeMois2 = new JComboBox<String>() ;
		this.listeAnnee2 = new JComboBox<String>() ;
		
		int i ; //Compteur

		//Rempli une liste de jours
		for(i=1 ; i<=NBR_JOUR_MOIS ; i++)
		{
			this.listeJour.addItem(String.valueOf(i)) ;
			this.listeJour2.addItem(String.valueOf(i)) ;
		}

		//Rempli une liste de mois
		for(i=1 ; i<=NBR_MOIS_AN ; i++)
		{
			this.listeMois.addItem(String.valueOf(i)) ;
			this.listeMois2.addItem(String.valueOf(i)) ;
		}

		//Rempli une liste d'années
		for(i=-1 ; i<=1 ; i++)
		{
			this.listeAnnee.addItem(String.valueOf(ANNEE_EN_COURS + i)) ;
			this.listeAnnee2.addItem(String.valueOf(ANNEE_EN_COURS + i)) ;
		}

		JCheckBox periode = new JCheckBox() ;

		/*Contrôleur*/
		MenuActionListener controleur = new MenuActionListener(this, listeJour, listeMois, listeAnnee, listeJour2, listeMois2, listeAnnee2, periode) ;
		CheckActionListener coche = new CheckActionListener(listeJour2, listeMois2, listeAnnee2, periode) ;

		/*Bouton de validation*/
		JButton valider = new JButton("Valider") ;
		valider.addActionListener(controleur) ;

		periode.addActionListener(coche) ;


		/*Ajouts au panneau*/
		panneau.add(labelDebut) ;
		panneau.add(listeJour) ;
		panneau.add(listeMois) ;
		panneau.add(listeAnnee) ;

		panneau.add(periode) ;
		panneau.add(labelFin) ;
		panneau.add(listeJour2) ;
		panneau.add(listeMois2) ;
		panneau.add(listeAnnee2) ;

		panneau.add(valider) ;
		panneau.add(occupationTexte) ;

		listeJour2.setEnabled(false) ;
		listeMois2.setEnabled(false) ;
		listeAnnee2.setEnabled(false) ;

		return panneau ;
	}

	/*Getters composants date*/
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


	/*Set Date of the selected day*/
	public void setDateSelected(Object jour, Object mois, Object annee)
	{
		 this.currentDate = annee+"-"+mois+"-"+jour ;
		 this.currentDateHuman = jour + "/" + mois + "/" + annee ;
	}

	public void setDateSelected(Object jour, Object mois, Object annee, Object jour2, Object mois2, Object annee2)
	{
		 this.currentDate = annee+"-"+mois+"-"+jour ;
		 this.currentDateHuman = jour + "/" + mois + "/" + annee ;

		 this.currentDate2 = annee2+"-"+mois2+"-"+jour2 ;
		 this.currentDateHuman2 = jour2 + "/" + mois2 + "/" + annee2 ;
	}

	/*Get Date of the selected day*/
	public String getDateSelected()
	{
		return currentDate ;
	}

	public void getStats()
	{
		Statistiques stats = new Statistiques() ;
		String occupation = stats.getOccupation1Day(this.currentDate) ;
		String nonOccupation = stats.getNonOccupation1Day(this.currentDate) ;
		final String message1 = String.format("<html>L\'occupation pour le %s est de %s%c<br>", currentDateHuman, occupation, this.pourcent) ;
		final String message2 = String.format("Les non-présentations représentent, pour le %s, %s%c</html>", currentDateHuman, nonOccupation, this.pourcent) ;

		this.occupationTexte.setText(message1+message2) ;
	}

	public void getStatsPeriode()
	{
		Statistiques stats = new Statistiques() ;
		String occupation = stats.getOccupationPeriode(this.currentDate, this.currentDate2) ;
		String nonOccupation = stats.getNonOccupationPeriode(this.currentDate, this.currentDate2) ;
		final String message1 = String.format("<html>L\'occupation du %s au %s est de %s%c<br>", currentDateHuman, currentDateHuman2, occupation, this.pourcent) ;
		final String message2 = String.format("Les non-présentations représentent, du %s au %s, %s%c</html>", currentDateHuman, currentDateHuman2, nonOccupation, this.pourcent) ;

		this.occupationTexte.setText(message1+message2) ;
	}


}


/*
	String[] mois = new String[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"} ;
*/

