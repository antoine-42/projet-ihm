import java.awt.event.*;

public class ReservationSelectListener implements ActionListener{
    private Reception reception;
    private Reservation reservation;

    ReservationSelectListener(Reception reception_, Reservation reservation_){
        this.reception = reception_;
        this.reservation = reservation_;
    }

    public void actionPerformed(ActionEvent e) {
        this.reception.selectReservation(this.reservation);
    }
}
