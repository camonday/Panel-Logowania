package panelAK;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Klasa pomocnicza tworząca panele komunikatów. Dodana by informacje nie byly przekazywane użytkownikowi jedynie
 * w formie wizualnej
 *
 */
public class MyPanel extends JPanel {
    public MyPanel(String tresc) {
        setBorder(new LineBorder(new Color(9, 92, 225), 4, true));
        setLayout(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        JTextArea komunikat = new JTextArea();
        komunikat.setText(tresc);
        komunikat.setBounds(7,10,500,40);
        komunikat.setBackground(null);
        this.add(komunikat);
    }

}
