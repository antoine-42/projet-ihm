import javax.swing.*;
import java.awt.event.*;

public class ActionSearchReservation extends AbstractAction{
	private Reception reception;

	ActionSearchReservation(Reception reception_){
		this.reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		if (this.reception.step == 0){
            this.reception.searchReservation();
        }
	}
}