import javax.swing.*;
import java.awt.*;

public class RoomSelectPanel extends JPanel {
    private RechercheReservation window;


    RoomSelectPanel(RechercheReservation window_){
        this.window = window_;

        this.drawPanel();
    }


    private void drawPanel(){
        this.setLayout(new GridBagLayout());




    }

    void refresh(Reservation reservation){

    }
}
