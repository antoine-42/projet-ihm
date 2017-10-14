import java.awt.event.*;

public class BoutonRetoursListener implements ActionListener{
	private RechercheReservation reception;

	BoutonRetoursListener(RechercheReservation reception_){
		this.reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		this.reception.setStep(reception.step -1);
	}
}