import javax.swing.*;


class Reception {
	int step = 0;
	private JFrame window = new JFrame();
	private PanelWindow windowPanel = new PanelWindow(this);

	private PanelSearch searchPanel = new PanelSearch(this);
    private PanelResult resultPanel = new PanelResult(this);
    private PanelRoomSelect roomSelectPanel = new PanelRoomSelect(this);
    private PanelFinalValidation finalValidationPanel = new PanelFinalValidation(this);

    private DBReservations reservationsDB;
    private DBInternal internalDB;

	private Reservation[] reservations = {};
    private Reservation[] additionalReservations = {};

    private Reservation selectedReservation;
    private Room suggestedRoom;
    private Room[] alternativeRooms;

    private Room selectedRoom;


	Reception() {
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.window.add(this.windowPanel);

        this.windowPanel.addPanel(this.searchPanel);
        this.windowPanel.addPanel(this.resultPanel);
        this.windowPanel.addPanel(this.roomSelectPanel);
        this.windowPanel.addPanel(this.finalValidationPanel);

		this.setStep(0);

        this.window.setVisible(true);
        this.connectionCheck();
	}
    private void connectionCheck(){
        Boolean error = false;

        try{
            this.reservationsDB = new DBReservations();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this.window,
                "Connection a la base de donnees de reservations impossible.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            error = true;
        }

        try{
            this.internalDB = new DBInternal();
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
            this.windowPanel.setBackButtonVisible(false);

            this.windowPanel.setVisiblePanel(0);
		}
		else if (this.step == 1) {
            this.window.setSize(800, 350);
            this.window.setTitle("Resultats de la recherche");
            this.windowPanel.setBackButtonVisible(true);

		    this.resultPanel.refresh(this.reservations, this.additionalReservations);
            this.windowPanel.setVisiblePanel(1);
		}
        else if (this.step == 2) {
            this.window.setSize(800, 650);
            this.window.setTitle("Attribuer une chambre");
            this.windowPanel.setBackButtonVisible(true);

            this.roomSelectPanel.refresh(this.selectedReservation, this.suggestedRoom, this.alternativeRooms);
            this.windowPanel.setVisiblePanel(2);
        }
        else if (this.step == 3) {
            this.window.setSize(500, 150);
            this.window.setTitle("Confirmation");
            this.windowPanel.setBackButtonVisible(false);

            this.finalValidationPanel.refresh(this.selectedRoom);
            this.windowPanel.setVisiblePanel(3);
        }
	}


	void searchReservation(){
		if (!this.searchPanel.reservationNullOrEmpty()) {
			this.reservations = this.reservationsDB.searchActiveReservationRef(this.searchPanel.getReservation());
		}
		else if (!Utils.isNullOrEmpty(this.searchPanel.getLastName())) {
			if (!Utils.isNullOrEmpty(this.searchPanel.getFirstName())) {
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
        this.internalDB.validReservation(this.selectedReservation.reference, String.valueOf(this.selectedRoom.number));
        //todo: put that in a function called when closing window
        this.closeConnections();
	    this.setStep(3);
    }
    void reset(){
        this.searchPanel.reset();

        this.setStep(0);
    }

    private void closeConnections(){
        this.internalDB.closeConnection();
        this.reservationsDB.closeConnection();
    }
}