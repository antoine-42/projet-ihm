import java.awt.event.*;

public class ReservationSearchListener implements ActionListener{
	private Reception reception;

	ReservationSearchListener(Reception reception_){
		this.reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		this.reception.searchReservation();
	}
}