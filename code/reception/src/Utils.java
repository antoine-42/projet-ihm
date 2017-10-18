import javax.swing.*;
import java.awt.*;
import java.text.*;

public class Utils {
    static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    static final Insets MARGIN_DEFAULT = new Insets(5, 5, 5, 5);
    static final Insets MARGIN_NONE = new Insets(0, 0, 0, 0);
    static final Insets MARGIN_NO_DOWN = new Insets(5, 5, 0, 5);
    static final Insets MARGIN_RIGHT = new Insets(0, 0, 0, 20);
    static final Insets MARGIN_LEFT = new Insets(0, 20, 0, 0);

    static GridBagConstraints buttonConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, MARGIN_NONE, 10, 10);
    static GridBagConstraints labelTitleConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, MARGIN_NONE, 25, 25);
    static GridBagConstraints cellConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, MARGIN_NONE, 0, 0);

    static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 15);
    static final Font TITLE_FONT = new Font("SansSerif", Font.PLAIN, 23);

    static final Color PRIMARY_COLOR = Color.decode("#EAEAEA");
    static final Color SECONDARY_COLOR = Color.black;
    static final Color THIRD_COLOR = Color.WHITE;


    static JLabel titleJLabel(){
        
    }


    static Boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }


    //make constructor unaccessible
    private Utils(){}
}
