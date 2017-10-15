import java.awt.EventQueue;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.text.*;

public class LimitTextField {

    public static void main(String[] args) {
        new LimitTextField();
    }

    LimitTextField() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JTextField pfPassword = new JTextField(20);
                ((AbstractDocument)pfPassword.getDocument()).setDocumentFilter(new LimitDocumentFilter(15));

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(new GridBagLayout());
                frame.add(pfPassword);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class LimitDocumentFilter extends DocumentFilter {

        private int limit;

        LimitDocumentFilter(int limit) {
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

    }

}
