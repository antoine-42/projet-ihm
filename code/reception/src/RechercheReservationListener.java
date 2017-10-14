import java.awt.event.*;

public class RechercheReservationListener implements ActionListener{
	private RechercheReservation reception;

	RechercheReservationListener(RechercheReservation reception_){
		this.reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		this.reception.searchReservation();
	}
}