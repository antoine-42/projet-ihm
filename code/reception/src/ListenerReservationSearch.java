import java.awt.event.*;

public class ListenerReservationSearch implements ActionListener{
	private Reception reception;

	ListenerReservationSearch(Reception reception_){
		this.reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		this.reception.searchReservation();
	}
}