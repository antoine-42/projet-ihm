import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RechercheReservationListener implements ActionListener{
	RechercheReservation reception;

	public RechercheReservationListener(RechercheReservation reception_){
		reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		reception.rechercher();
	}
}