package panelAK;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyPanel extends JPanel {
    public MyPanel(String tresc) {
        setBorder(new LineBorder(new Color(9, 92, 225), 4, true));
       // setBackground(new Color(255, 183, 183, 255));
        setLayout(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        JTextArea komunikat = new JTextArea( tresc );
        komunikat.setBounds(7,10,500,40);
        komunikat.setBackground(null);
        this.add(komunikat);
    }

}
