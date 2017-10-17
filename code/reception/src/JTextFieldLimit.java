import javax.swing.text.*;
import javax.swing.*;


public class JTextFieldLimit extends PlainDocument {
    private int limit;
    private SearchPanel panel = null;

    JTextFieldLimit(int limit_, SearchPanel panel_) {
        super();
        this.limit = limit_;
        this.panel = panel_;
    }
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null){
            return;
        }

        if (getLength() + str.length() <= this.limit) {
            super.insertString(offset, str, attr);
        }

        if (getLength() + str.length() >= this.limit +1 && this.panel != null) {
            this.panel.nextReservationSubPanel();
        }
    }
}