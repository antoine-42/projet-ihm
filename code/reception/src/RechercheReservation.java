import javax.swing.*;
import java.awt.*;
import java.text.*;


class RechercheReservation {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static Insets marginDefault = new Insets(5, 5, 5, 5);
    static Insets marginNone = new Insets(0, 0, 0, 0);
    static Insets marginRight = new Insets(0, 0, 0, 20);
    static Insets marginLeft = new Insets(0, 20, 0, 0);

    static GridBagConstraints buttonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, marginNone, 10, 10);
    static GridBagConstraints labelTitleConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, marginNone, 25, 25);
    static GridBagConstraints cellConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, marginNone, 0, 0);


	int step = 0;
	private JFrame window;
	private CardLayout mainPanelCard;
    private JPanel mainPanel = new JPanel();
    private JPanel buttonWrapper;

	private SearchPanel searchPanel = new SearchPanel(this);
    private ResultPanel resultPanel = new ResultPanel(this);
    private RoomSelectPanel roomSelectPanel = new RoomSelectPanel(this);
    private FinalValidationPanel finalValidationPanel = new FinalValidationPanel(this);

	private Reservation[] reservations = {};

    private Reservation selectedReservation;
    private Chambre suggestedRoom;
    private Chambre[] alternativeRooms;

    private Chambre selectedRoom;


	RechercheReservation() {
		this.window = new JFrame();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        this.window.add(windowPanel);

        JButton backButton = new JButton("←");
        BoutonRetoursListener boutonRetoursListener = new BoutonRetoursListener(this);
        backButton.addActionListener(boutonRetoursListener);

        this.buttonWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.buttonWrapper.add(backButton);
        this.buttonWrapper.setAlignmentX(Component.RIGHT_ALIGNMENT);

        RechercheReservation.buttonConstraints.gridx = 0;
        RechercheReservation.buttonConstraints.gridy = 0;
        RechercheReservation.buttonConstraints.gridwidth = 1;
        RechercheReservation.buttonConstraints.anchor = GridBagConstraints.LINE_START;
        windowPanel.add(buttonWrapper);
        windowPanel.add(this.mainPanel);

        this.mainPanelCard = new CardLayout();
        this.mainPanel.setLayout(this.mainPanelCard);

        this.mainPanel.add(this.searchPanel, "search");
        this.mainPanel.add(this.resultPanel, "results");
        this.mainPanel.add(this.roomSelectPanel, "roomSelect");
        this.mainPanel.add(this.finalValidationPanel, "finalValidation");

		this.setStep(0);

        this.window.setVisible(true);
	}

	void setStep(int i){
		this.step = i;

		if (this.step == 0) {
            this.window.setSize(700, 200);
            this.window.setTitle("Recherche de reservation");
            this.buttonWrapper.setVisible(false);

            this.mainPanelCard.show(this.mainPanel, "search");
		}
		else if (this.step == 1) {
            this.window.setSize(700, 600);
            this.window.setTitle("Resultats de la recherche");
            this.buttonWrapper.setVisible(true);

		    this.resultPanel.refresh(reservations);
            this.mainPanelCard.show(this.mainPanel, "results");
		}
        else if (this.step == 2) {
            this.window.setSize(700, 600);
            this.window.setTitle("Attribuer une chambre");
            this.buttonWrapper.setVisible(true);

            this.roomSelectPanel.refresh(this.selectedReservation, this.suggestedRoom, this.alternativeRooms);
            this.mainPanelCard.show(this.mainPanel, "roomSelect");
        }
        else if (this.step == 3) {
            this.window.setSize(700, 600);
            this.window.setTitle("Confirmation");
            this.buttonWrapper.setVisible(false);

            this.finalValidationPanel.refresh(this.selectedRoom);
            this.mainPanelCard.show(this.mainPanel, "finalValidation");
        }
	}

	
	private String getReservation(){
		return this.searchPanel.referenceTextField.getText();
	}
	private String getLastName(){
        return this.searchPanel.lastNameTextField.getText();
	}
    private String getName(){
        return this.searchPanel.nameTextField.getText();
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

	void selectReservation(Reservation reservation){
        this.selectedReservation = reservation;

        this.suggestedRoom = new Chambre(42, 1,false, true);

        this.alternativeRooms = new Chambre[]{
                new Chambre(69, 1,false, true),
                new Chambre(58, 2,false, true),
                new Chambre(34, 0,false, true),
                new Chambre(13, 1,false, true),
                new Chambre(37, 0,false, true),
                new Chambre(07, 1,false, true)
        };

        this.setStep(2);
    }

    void selectRoom(Chambre room){
	    this.selectedRoom = room;
	    this.setStep(3);
    }



    static Boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
}