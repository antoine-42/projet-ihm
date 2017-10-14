import java.awt.event.*;

public class BoutonRetoursListener implements ActionListener{
	private RechercheReservation reception;

	BoutonRetoursListener(RechercheReservation reception_){
		reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		reception.setEtape(reception.etape -1);
	}
}