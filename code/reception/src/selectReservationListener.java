import java.awt.event.*;

public class selectReservationListener implements ActionListener{
    private RechercheReservation reception;
    private Reservation reservation;

    selectReservationListener(RechercheReservation reception_, Reservation reservation_){
        this.reception = reception_;
        this.reservation = reservation_;
    }

    public void actionPerformed(ActionEvent e) {
        this.reception.selectReservation(this.reservation);
    }
}
