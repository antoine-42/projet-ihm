import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.*;


class Reception {
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static Insets marginDefault = new Insets(5, 5, 5, 5);
    static Insets marginNoDown = new Insets(5, 5, 0, 5);
    static Insets marginNone = new Insets(0, 0, 0, 0);
    static Insets marginRight = new Insets(0, 0, 0, 20);
    static Insets marginLeft = new Insets(0, 20, 0, 0);

    static GridBagConstraints buttonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, marginNone, 10, 10);
    static GridBagConstraints labelTitleConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, marginNone, 25, 25);
    static GridBagConstraints cellConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, marginNone, 0, 0);

    static Font defaultFont = new Font("SansSerif", Font.PLAIN, 15);
    static Font titleFont = new Font("SansSerif", Font.PLAIN, 23);

    static Color primaryColor = Color.decode("#EAEAEA");
    static Color secondaryColor = Color.black;
    static Color thirdColor = Color.WHITE;


	int step = 0;
	private JFrame window;
	private CardLayout mainPanelCard;
    private JPanel mainPanel = new JPanel();
    private JPanel buttonWrapper;

	private SearchPanel searchPanel = new SearchPanel(this);
    private ResultPanel resultPanel = new ResultPanel(this);
    private RoomSelectPanel roomSelectPanel = new RoomSelectPanel(this);
    private FinalValidationPanel finalValidationPanel = new FinalValidationPanel(this);

    private ReservationsDB reservationsDB;
    private InternalDB internalDB;

	private Reservation[] reservations = {};
    private Reservation[] additionalReservations = {};

    private Reservation selectedReservation;
    private Room suggestedRoom;
    private Room[] alternativeRooms;

    private Room selectedRoom;


	Reception() {
		this.window = new JFrame();
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
        windowPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        windowPanel.setBackground(primaryColor);
        this.window.add(windowPanel);

        JButton backButton = new JButton("←");
        ReturnButtonListener returnButtonListener = new ReturnButtonListener(this);
        backButton.addActionListener(returnButtonListener);
        backButton.setFont(Reception.defaultFont);

        this.buttonWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.buttonWrapper.add(backButton);
        this.buttonWrapper.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.buttonWrapper.setOpaque(false);

        Reception.buttonConstraints.gridx = 0;
        Reception.buttonConstraints.gridy = 0;
        Reception.buttonConstraints.gridwidth = 1;
        Reception.buttonConstraints.anchor = GridBagConstraints.LINE_START;
        windowPanel.add(buttonWrapper);
        windowPanel.add(this.mainPanel);

        this.mainPanelCard = new CardLayout();
        this.mainPanel.setLayout(this.mainPanelCard);
        this.mainPanel.setOpaque(false);

        this.mainPanel.add(this.searchPanel, "search");
        this.mainPanel.add(this.resultPanel, "results");
        this.mainPanel.add(this.roomSelectPanel, "roomSelect");
        this.mainPanel.add(this.finalValidationPanel, "finalValidation");

		this.setStep(0);

        this.window.setVisible(true);
        this.connectionCheck();
	}

    void connectionCheck(){
        Boolean error = false;
        try{
            this.reservationsDB = new ReservationsDB();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this.window,
                "Connection a la base de donnees de reservations impossible.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            error = true;
        }

        try{
            this.internalDB = new InternalDB();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this.window,
                "Connection a la base de donnees interne impossible.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            error = true;
        }


        if (error) {
            System.exit(0);
        }
    }

    void setStep(int i){
		this.step = i;

		if (this.step == 0) {
            this.window.setSize(600, 250);
            this.window.setTitle("Recherche de reservation");
            this.buttonWrapper.setVisible(false);

            this.mainPanelCard.show(this.mainPanel, "search");
		}
		else if (this.step == 1) {
            this.window.setSize(800, 350);
            this.window.setTitle("Resultats de la recherche");
            this.buttonWrapper.setVisible(true);

		    this.resultPanel.refresh(this.reservations, this.additionalReservations);
            this.mainPanelCard.show(this.mainPanel, "results");
		}
        else if (this.step == 2) {
            this.window.setSize(800, 650);
            this.window.setTitle("Attribuer une chambre");
            this.buttonWrapper.setVisible(true);

            this.roomSelectPanel.refresh(this.selectedReservation, this.suggestedRoom, this.alternativeRooms);
            this.mainPanelCard.show(this.mainPanel, "roomSelect");
        }
        else if (this.step == 3) {
            this.window.setSize(500, 150);
            this.window.setTitle("Confirmation");
            this.buttonWrapper.setVisible(false);

            this.finalValidationPanel.refresh(this.selectedRoom);
            this.mainPanelCard.show(this.mainPanel, "finalValidation");
        }
	}


	void searchReservation(){
		if (!this.searchPanel.reservationNullOrEmpty()) {
			this.reservations = this.reservationsDB.searchActiveReservationRef(this.searchPanel.getReservation());
		}
		else if (!isNullOrEmpty(this.searchPanel.getLastName())) {
			if (!isNullOrEmpty(this.searchPanel.getFirstName())) {
				this.reservations = this.reservationsDB.searchActiveReservationFullName(this.searchPanel.getLastName(), this.searchPanel.getFirstName());
                this.additionalReservations = this.reservationsDB.searchAllReservationFullName(this.searchPanel.getLastName(), this.searchPanel.getFirstName());
			}
			else {
				this.reservations = this.reservationsDB.searchActiveReservationLastName(this.searchPanel.getLastName());
                this.additionalReservations = this.reservationsDB.searchAllReservationLastName(this.searchPanel.getLastName());
			}
		}
		else {
			this.reservations = this.reservationsDB.searchActiveReservationName(this.searchPanel.getName());
            this.additionalReservations = this.reservationsDB.searchAllReservationName(this.searchPanel.getFirstName());
		}

        int reservationsNumber = this.additionalReservations.length + this.reservations.length;
		System.out.println("Results: " + reservationsNumber);
		if (reservationsNumber > 0) {
			this.setStep(1);
		}
        else {
            JOptionPane.showMessageDialog(this.window,
                "Aucun resultat.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
	}

	private void searchRooms(){
        if(this.internalDB.checkReservationValidated(this.selectedReservation.reference)){
            JOptionPane.showMessageDialog(this.window,
                "Cette reservation a deja ete utilisee.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Room[] rooms = this.internalDB.searchRoom(this.selectedReservation.category);

        if (rooms.length > 0){
            this.suggestedRoom = rooms[0];

            if (rooms.length > 1){
                this.alternativeRooms = new Room[rooms.length -1];
                System.arraycopy(rooms, 1, this.alternativeRooms, 0, rooms.length - 1);
            }

            this.setStep(2);
        }
    }

	void selectReservation(Reservation reservation){
        this.selectedReservation = reservation;

        this.searchRooms();
    }
    void selectRoom(Room room){
	    this.selectedRoom = room;

        this.internalDB.affectRoom(room.number);
        this.internalDB.validReservation(this.selectedReservation.reference);
        this.closeConnections();
	    this.setStep(3);
    }
    void reset(){
        this.searchPanel.reset();

        this.setStep(0);
    }

    void closeConnections(){
        this.internalDB.closeConnection();
        this.reservationsDB.closeConnection();
    }



    static Boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
}