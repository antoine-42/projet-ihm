import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;


public class RechercheReservation {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	int etape = 0;
	JFrame fenetre;

	TextField textFieldNom;
	TextField textFieldPrenom;
	TextField textFieldReferenceReservation;
	
	Reservation[] reservations = null;

	BoutonRetoursListener boutonRetoursListener = new BoutonRetoursListener(this);


	public RechercheReservation() {
		this.setEtape(0);
	}

	void setEtape(int i){
		this.etape = i;

		if (this.etape == 0) {
			this.fenetre = this.fenetreRecherche();
		}
		else if (this.etape == 1) {
			this.fenetre = this.fenetreResultatsRecherche(this.reservations);
		}

		this.fenetre.repaint();
		this.fenetre.revalidate();
		this.fenetre.setVisible(true);
	}

	JFrame fenetreRecherche(){
		JFrame fenetreRech = new JFrame();
	    fenetreRech.setSize(700, 200);
		fenetreRech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreRech.setTitle("Recherche de reservation");

		fenetreRech.setLayout(new GridBagLayout());
		GridBagConstraints contraintes = new GridBagConstraints();

		Insets marginDefault = new Insets(5, 5, 5, 5);
		Insets marginNone = new Insets(0, 0, 0, 0);
		Insets marginRight = new Insets(0, 0, 0, 20);
		Insets marginLeft = new Insets(0, 20, 0, 0);

		GridBagConstraints contraintesLabel = new GridBagConstraints();
		contraintesLabel.ipadx = 15;
		contraintesLabel.ipady = 15;
		contraintesLabel.weightx = 0;
		contraintesLabel.fill = GridBagConstraints.HORIZONTAL;
		contraintesLabel.insets = marginDefault;

		GridBagConstraints contraintesTextField = new GridBagConstraints();
		contraintesTextField.ipadx = 10;
		contraintesTextField.ipady = 10;
		contraintesTextField.weightx = 1;
		contraintesTextField.fill = GridBagConstraints.HORIZONTAL;
		contraintesTextField.insets = marginDefault;

		GridBagConstraints contraintesPanel = new GridBagConstraints();
		contraintesPanel.ipadx = 0;
		contraintesPanel.ipady = 0;
		contraintesPanel.weightx = 1;
		contraintesPanel.fill = GridBagConstraints.HORIZONTAL;


		JPanel panelNomPrenom = new JPanel();
		panelNomPrenom.setLayout(new GridBagLayout());
		contraintesPanel.gridx = 0;
		contraintesPanel.gridy = 0;
		contraintesPanel.insets = marginRight;
		fenetreRech.add(panelNomPrenom, contraintesPanel);


		JLabel labelNom = new JLabel("Nom du client", JLabel.LEFT);
		contraintesLabel.gridx = 0;
		contraintesLabel.gridy = 0;
		panelNomPrenom.add(labelNom, contraintesLabel);

		this.textFieldNom = new TextField("Carpentier");
		contraintesTextField.gridx = 1;
		contraintesTextField.gridy = 0;
		panelNomPrenom.add(textFieldNom, contraintesTextField);

		JLabel labelPrenom = new JLabel("Prenom du client", JLabel.LEFT);
		contraintesLabel.gridx = 0;
		contraintesLabel.gridy = 1;
		panelNomPrenom.add(labelPrenom, contraintesLabel);

		this.textFieldPrenom = new TextField("Marine");
		contraintesTextField.gridx = 1;
		contraintesTextField.gridy = 1;
		panelNomPrenom.add(textFieldPrenom, contraintesTextField);


		JPanel panelReference = new JPanel();
		panelReference.setLayout(new GridBagLayout());
		contraintesPanel.gridx = 1;
		contraintesPanel.gridy = 0;
		contraintesPanel.insets = marginLeft;
		fenetreRech.add(panelReference, contraintesPanel);


		JLabel labelReferenceReservation  = new JLabel("Reference de la reservation", JLabel.LEFT);
		contraintesLabel.gridx = 0;
		contraintesLabel.gridy = 0;
		panelReference.add(labelReferenceReservation, contraintesLabel);

		this.textFieldReferenceReservation = new TextField();
		contraintesTextField.gridx = 1;
		contraintesTextField.gridy = 0;
		panelReference.add(textFieldReferenceReservation, contraintesTextField);


		JButton bouttonRecherche = new JButton("Chercher");
		RechercheReservationListener reservationListener = new RechercheReservationListener(this);
		bouttonRecherche.addActionListener(reservationListener);
		contraintes.gridx = 0;
		contraintes.gridy = 1;
		contraintes.ipadx = 10;
		contraintes.ipady = 10;
		contraintes.gridwidth = 2;
		contraintes.weightx = 0;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.insets = marginNone;
		fenetreRech.add(bouttonRecherche, contraintes);

		return fenetreRech;
	}
	JFrame fenetreResultatsRecherche(Reservation[] reservations) {
		JFrame fenetreResRech = new JFrame();
	    fenetreResRech.setSize(700, 600);
		fenetreResRech.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreResRech.setTitle("Resultats de la recherche");

		fenetreResRech.setLayout(new GridBagLayout());

		Insets marginDefault = new Insets(5, 5, 5, 5);
		Insets marginNone = new Insets(0, 0, 0, 0);
		Insets marginRight = new Insets(0, 0, 0, 20);
		Insets marginLeft = new Insets(0, 20, 0, 0);

		GridBagConstraints contraintes = new GridBagConstraints();

		GridBagConstraints contraintesBouton = new GridBagConstraints();
		contraintesBouton.ipadx = 10;
		contraintesBouton.ipady = 10;
		contraintesBouton.weightx = 0;
		contraintesBouton.weighty = 0;
		contraintesBouton.fill = GridBagConstraints.NONE;
		contraintesBouton.insets = marginNone;

		GridBagConstraints contraintesLabelTitre = new GridBagConstraints();	
		contraintesLabelTitre.ipadx = 1;
		contraintesLabelTitre.ipady = 1;
		contraintesLabelTitre.weightx = 1;
		contraintesLabelTitre.weighty = 0;
		contraintesLabelTitre.fill = GridBagConstraints.BOTH;
		contraintesLabelTitre.insets = marginDefault;


		JButton bouttonRetours = new JButton("←");
		bouttonRetours.addActionListener(boutonRetoursListener);
		contraintesBouton.gridx = 0;
		contraintesBouton.gridy = 0;
		contraintesBouton.anchor = GridBagConstraints.LINE_START;
		fenetreResRech.add(bouttonRetours, contraintesBouton);

		JPanel panelTableauResultats = new JPanel();
		panelTableauResultats.setLayout(new GridBagLayout());
		panelTableauResultats.setBackground(Color.GRAY);
		contraintes.gridx = 0;
		contraintes.gridy = 1;
		contraintes.ipadx = 0;
		contraintes.ipady = 0;
		contraintes.weightx = 1;
		contraintes.weighty = 1;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.insets = marginNone;
		contraintes.anchor = GridBagConstraints.NORTH;
		fenetreResRech.add(panelTableauResultats, contraintes);


		GridBagConstraints contraintesCellule = new GridBagConstraints();
		contraintesCellule.ipadx = 15;
		contraintesCellule.ipady = 15;
		contraintesCellule.weightx = 1;
		contraintesCellule.weighty = 0;
		contraintesCellule.fill = GridBagConstraints.BOTH;
		contraintesCellule.insets = marginNone;

		String legendes[] = {
			"Nom",
			"Prenom",
			"Reference",
			"Type de chambre",
			"Debut du sejour",
			"Duree du sejour"
		};
		for (int i = 0; i < legendes.length; i++) {
			JLabel labelLegende = new JLabel(legendes[i], JLabel.LEFT);
			contraintesCellule.gridx = i;
			contraintesCellule.gridy = 0;
			panelTableauResultats.add(labelLegende, contraintesCellule);
		}

		for (int i = 0; i < reservations.length; i++) {
			String[] contenu = {
				reservations[i].client.nom,
				reservations[i].client.prenom,
				reservations[i].reference,
				TypeChambre.TYPE[reservations[i].categorie],
				dateFormat.format(reservations[i].debut),
				String.valueOf(reservations[i].duree)
			};

			for (int j = 0; j < contenu.length; j++) {
				JLabel labelContenu = new JLabel(contenu[j], JLabel.LEFT);

				if (i % 2 == 0) {
					labelContenu.setOpaque(true);
					labelContenu.setBackground(Color.BLUE);
					labelContenu.setForeground(Color.WHITE);
				}

				contraintesCellule.gridx = j;
				contraintesCellule.gridy = i +1;

				panelTableauResultats.add(labelContenu, contraintesCellule);
			}
		}

		return fenetreResRech;
	}

	
	String getReservation(){
		return this.textFieldReferenceReservation.getText();
	}
	String getNom(){
		return this.textFieldNom.getText();
	}
	String getPrenom(){
		return this.textFieldPrenom.getText();
	}

	Boolean isNullOrEmpty(String str){
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}
	void rechercher(){
		DBReservations db = new DBReservations();

		if (!isNullOrEmpty(this.getReservation())) {
			this.reservations = db.chercherReservationRef(this.getReservation());
		}
		else if (!isNullOrEmpty(this.getNom())) {
			if (!isNullOrEmpty(this.getPrenom())) {
				this.reservations = db.chercherReservationNomPrenom(this.getNom(), this.getPrenom());
			}
			else {
				this.reservations = db.chercherReservationNom(this.getNom());
			}
		}
		else {
			this.reservations = db.chercherReservationPrenom(this.getPrenom());
		}

		db.closeConnection();

		System.out.println("Resultats: " + this.reservations.length);
		if (this.reservations.length > 0) {
			this.setEtape(1);
		}
	}
}