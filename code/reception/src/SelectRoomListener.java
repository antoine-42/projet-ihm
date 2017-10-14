import java.awt.event.*;

public class SelectRoomListener implements ActionListener {
    private Reception reception;

    private Room room;

    SelectRoomListener(Reception reception_, Room room_){
        this.reception = reception_;
        this.room = room_;
    }

    public void actionPerformed(ActionEvent e) {
        this.reception.selectRoom(this.room);
    }
}
