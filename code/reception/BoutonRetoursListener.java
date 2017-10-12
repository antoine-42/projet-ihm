import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonRetoursListener implements ActionListener{
	RechercheReservation reception;

	public BoutonRetoursListener(RechercheReservation reception_){
		reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		reception.setEtape(reception.etape -1);
	}
}