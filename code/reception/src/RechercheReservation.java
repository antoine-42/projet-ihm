import javax.swing.*;
import java.awt.*;
import java.text.*;


class RechercheReservation {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Insets marginDefault = new Insets(5, 5, 5, 5);
    private static Insets marginNone = new Insets(0, 0, 0, 0);
    private static Insets marginRight = new Insets(0, 0, 0, 20);
    private static Insets marginLeft = new Insets(0, 20, 0, 0);

    private static GridBagConstraints buttonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, marginNone, 10, 10);


	int step = 0;
	private JFrame window;
	private CardLayout mainPanelCard;
    private JPanel mainPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
    private JPanel resultPanel = new JPanel();
    private JPanel panelTableauResultats;

	private TextField lastNameTextField;
	private TextField nameTextField;
	private TextField referenceTextField;

	private Reservation[] reservations = {};

	private BoutonRetoursListener boutonRetoursListener = new BoutonRetoursListener(this);


	RechercheReservation() {
		this.window = new JFrame();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.add(this.mainPanel);

        this.mainPanelCard = new CardLayout();
        this.mainPanel.setLayout(this.mainPanelCard);

        this.makeSearchPanel();
        this.makeResultPanel();

        this.mainPanel.add(this.searchPanel, "search");
        this.mainPanel.add(this.resultPanel, "results");

		this.setStep(0);

        this.window.setVisible(true);
	}

	void setStep(int i){
		this.step = i;

		if (this.step == 0) {
            this.window.setSize(700, 200);
            this.window.setTitle("Recherche de reservation");

            this.mainPanelCard.show(this.mainPanel, "search");
		}
		else if (this.step == 1) {
            this.window.setSize(700, 600);
            this.window.setTitle("Resultats de la recherche");

		    this.refreshResultPanel();
            this.mainPanelCard.show(this.mainPanel, "results");
		}
	}

	private void makeSearchPanel(){
        this.searchPanel.setLayout(new GridBagLayout());

		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.ipadx = 15;
		labelConstraints.ipady = 15;
		labelConstraints.weightx = 0;
		labelConstraints.fill = GridBagConstraints.HORIZONTAL;
		labelConstraints.insets = marginDefault;

		GridBagConstraints textFieldConstraints = new GridBagConstraints();
		textFieldConstraints.ipadx = 10;
		textFieldConstraints.ipady = 10;
		textFieldConstraints.weightx = 1;
		textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		textFieldConstraints.insets = marginDefault;

		GridBagConstraints panelConstraints = new GridBagConstraints();
		panelConstraints.ipadx = 0;
		panelConstraints.ipady = 0;
		panelConstraints.weightx = 1;
		panelConstraints.fill = GridBagConstraints.HORIZONTAL;


		JPanel fullNamePanel = new JPanel();
		fullNamePanel.setLayout(new GridBagLayout());
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 0;
		panelConstraints.insets = marginRight;
        this.searchPanel.add(fullNamePanel, panelConstraints);


		JLabel lastNameLabel = new JLabel("Nom du client", JLabel.LEFT);
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		fullNamePanel.add(lastNameLabel, labelConstraints);

		this.lastNameTextField = new TextField("Carpentier");
		textFieldConstraints.gridx = 1;
		textFieldConstraints.gridy = 0;
		fullNamePanel.add(lastNameTextField, textFieldConstraints);

		JLabel nameLabel = new JLabel("Prenom du client", JLabel.LEFT);
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 1;
		fullNamePanel.add(nameLabel, labelConstraints);

		this.nameTextField = new TextField("Marine");
		textFieldConstraints.gridx = 1;
		textFieldConstraints.gridy = 1;
		fullNamePanel.add(nameTextField, textFieldConstraints);


		JPanel panelReference = new JPanel();
		panelReference.setLayout(new GridBagLayout());
		panelConstraints.gridx = 1;
		panelConstraints.gridy = 0;
		panelConstraints.insets = marginLeft;
        this.searchPanel.add(panelReference, panelConstraints);


		JLabel labelReferenceReservation  = new JLabel("Reference de la reservation", JLabel.LEFT);
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		panelReference.add(labelReferenceReservation, labelConstraints);

		this.referenceTextField = new TextField();
		textFieldConstraints.gridx = 1;
		textFieldConstraints.gridy = 0;
		panelReference.add(referenceTextField, textFieldConstraints);


		JButton bouttonRecherche = new JButton("Chercher");
		RechercheReservationListener reservationListener = new RechercheReservationListener(this);
		bouttonRecherche.addActionListener(reservationListener);
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.gridwidth = 2;
        buttonConstraints.anchor = GridBagConstraints.CENTER;
        this.searchPanel.add(bouttonRecherche, buttonConstraints);
	}
    private void makeResultPanel() {
        this.resultPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();


        JButton bouttonRetours = new JButton("←");
        bouttonRetours.addActionListener(boutonRetoursListener);
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        buttonConstraints.gridwidth = 1;
        buttonConstraints.anchor = GridBagConstraints.LINE_START;
        this.resultPanel.add(bouttonRetours, buttonConstraints);

        this.panelTableauResultats = new JPanel();
        this.panelTableauResultats.setLayout(new GridBagLayout());
        this.panelTableauResultats.setBackground(Color.GRAY);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = marginNone;
        constraints.anchor = GridBagConstraints.NORTH;
        this.resultPanel.add(this.panelTableauResultats, constraints);
    }
	private void refreshResultPanel() {
		GridBagConstraints cellConstraints = new GridBagConstraints();
		cellConstraints.ipadx = 15;
		cellConstraints.ipady = 15;
		cellConstraints.weightx = 1;
		cellConstraints.weighty = 0;
		cellConstraints.fill = GridBagConstraints.BOTH;
		cellConstraints.insets = marginNone;


        this.panelTableauResultats.removeAll();

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
			cellConstraints.gridx = i;
			cellConstraints.gridy = 0;
			this.panelTableauResultats.add(labelLegende, cellConstraints);
		}

		for (int i = 0; i < this.reservations.length; i++) {
			String[] content = {
                this.reservations[i].client.lastName,
                this.reservations[i].client.name,
                this.reservations[i].reference,
				TypeChambre.TYPE[this.reservations[i].category],
				dateFormat.format(this.reservations[i].start),
				String.valueOf(this.reservations[i].length)
			};

			for (int j = 0; j < content.length; j++) {
				JLabel contentLabel = new JLabel(content[j], JLabel.LEFT);

				if (i % 2 == 0) {
					contentLabel.setOpaque(true);
					contentLabel.setBackground(Color.BLUE);
					contentLabel.setForeground(Color.WHITE);
				}

				cellConstraints.gridx = j;
				cellConstraints.gridy = i +1;

                this.panelTableauResultats.add(contentLabel, cellConstraints);
			}
		}
	}

	
	private String getReservation(){
		return this.referenceTextField.getText();
	}
	private String getLastName(){
        return this.lastNameTextField.getText();
	}
    private String getName(){
        return this.nameTextField.getText();
    }

	static Boolean isNullOrEmpty(String str){
		return str == null || str.isEmpty();
	}
	void searchReservation(){
		DBReservations db = new DBReservations();

		if (!isNullOrEmpty(this.getReservation())) {
			this.reservations = db.searchReservationRef(this.getReservation());
		}
		else if (!isNullOrEmpty(this.getLastName())) {
			if (!isNullOrEmpty(this.getName())) {
				this.reservations = db.searchReservationFullName(this.getLastName(), this.getName());
			}
			else {
				this.reservations = db.searchReservationLastName(this.getLastName());
			}
		}
		else {
			this.reservations = db.searchReservationName(this.getName());
		}

		db.closeConnection();

		System.out.println("Results: " + this.reservations.length);
		if (this.reservations.length > 0) {
			this.setStep(1);
		}
	}
}