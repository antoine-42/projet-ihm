import java.awt.event.*;

public class ReturnButtonListener implements ActionListener{
	private Reception reception;

	ReturnButtonListener(Reception reception_){
		this.reception = reception_;
	}

	public void actionPerformed(ActionEvent e) {
		this.reception.setStep(reception.step -1);
	}
}