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


	int step = 0;
	private JFrame window;
	private CardLayout mainPanelCard;
    private JPanel mainPanel = new JPanel();

	private SearchPanel searchPanel = new SearchPanel(this);
    private ResultPanel resultPanel = new ResultPanel(this);
    private RoomSelectPanel roomSelectPanel = new RoomSelectPanel(this);

	private Reservation[] reservations = {};
    private Reservation selectedReservation;


	RechercheReservation() {
		this.window = new JFrame();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.add(this.mainPanel);

        this.mainPanelCard = new CardLayout();
        this.mainPanel.setLayout(this.mainPanelCard);

        this.mainPanel.add(this.searchPanel, "search");
        this.mainPanel.add(this.resultPanel, "results");
        this.mainPanel.add(this.roomSelectPanel, "roomSelect");

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

		    this.resultPanel.refresh(reservations);
            this.mainPanelCard.show(this.mainPanel, "results");
		}
        else if (this.step == 2) {
            this.window.setSize(700, 600);
            this.window.setTitle("Attribuer une chambre");

            this.roomSelectPanel.refresh(this.selectedReservation);
            this.mainPanelCard.show(this.mainPanel, "roomSelect");
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
        this.setStep(2);
    }



    static Boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
}