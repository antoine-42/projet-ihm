import java.awt.event.*;

public class SelectRoomListener implements ActionListener {
    private RechercheReservation reception;

    private Chambre room;

    SelectRoomListener(RechercheReservation reception_, Chambre room_){
        this.reception = reception_;
        this.room = room_;
    }

    public void actionPerformed(ActionEvent e) {
        this.reception.selectRoom(this.room);
    }
}
