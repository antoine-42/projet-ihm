import java.awt.event.*;

public class RechercheReservationListener implements ActionListener{
	private RechercheReservation reception;

	RechercheReservationListener(RechercheReservation reception_){
		reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		reception.searchReservation();
	}
}