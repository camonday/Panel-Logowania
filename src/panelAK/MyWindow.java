package panelAK;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * klasa główna, zawiera metode
 * statyczna main
 */
public class MyWindow extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MyWindow okno = new MyWindow();
                    okno.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });

        //System.exit(0); Zamiast system exit mamy exit on close
    }

    public MyWindow() throws HeadlessException {
        this("Okno Logowania");
    }

    public MyWindow(String title){
        super (title);
        buildOkineko();
    }
    LogiUzytkownika ania = new LogiUzytkownika("ania", "123");
    /**
     * Buduje okienko
     */
    protected void buildOkineko(){
        setBounds(100,100, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar myBar = new JMenuBar();
        setJMenuBar(myBar);


        /*
         Poniżej: Ustawienie list rozwijalnych Menu1 Menu2
         Kod jest brzydki, "kopiuj wklej" bo chciałam zrozumieć jak to działa,
         Przy kodzie komercyjnym utworzyłabym metodę tworzącą opcję i poprostu
         wywołała ją 3 razy.
        */
        {
            JMenu myDo = new JMenu("Menu 1");
            myBar.add(myDo);

            JMenuItem myItemADo1 = new JMenuItem("opcja 1a");
            myItemADo1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doPracuje('A');
                }
            });
            myDo.add(myItemADo1);

            JMenuItem myItemBDo1 = new JMenuItem("opcja 1b");
            myItemBDo1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doPracuje('B');
                }
            });
            myDo.add(myItemBDo1);

            JMenu myDo2 = new JMenu("Menu 2");
            myBar.add(myDo2);

            JMenuItem myItemCDo2 = new JMenuItem("opcja 2c");
            myItemCDo2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doPracuje('C');
                }
            });
            myDo2.add(myItemCDo2);
        }

        JPanel contentPane = new JPanel();
        // Poniżej: Ustawienia panelu
        {
            contentPane.setBorder(new LineBorder(new Color(255, 105, 44), 5, true));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }

        MyPanel panelP = new MyPanel("Udało Ci się zalogować");
        panelP.setBounds(10,110, 600, 70);
        contentPane.add(panelP);
        panelP.setVisible(false);

        MyPanel panelF = new MyPanel("Podano niepoprawne dane logowania");
        panelF.setBounds(10,110, 600, 70);
        contentPane.add(panelF);
        panelF.setVisible(false);

        //Passy

        JTextField name = new JTextField();
        name.setBounds(10, 30, 300, 50);
        contentPane.add(name);
        JLabel nameLabel = new JLabel("nazwa");
        nameLabel.setBounds(10, 70, 300, 50);
        contentPane.add(nameLabel);
        nameLabel.setLabelFor(name);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(320, 30, 300, 50);
        contentPane.add(pass);
        JLabel passLabel = new JLabel("Haslo");
        passLabel.setBounds(320, 70, 300, 50);
        contentPane.add(passLabel);
        passLabel.setLabelFor(pass);

        //Przyciski
        JButton logujButton = new JButton("Loguj");
        JButton czyscButton = new JButton("Czysc");

        logujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameTresc = name.getText();
                char[] passTresc = pass.getPassword();
                if(sprawdzPassy(contentPane, nameTresc, passTresc)){
                    panelP.setVisible(true);
                    panelF.setVisible(false);
                    nameLabel.setForeground(Color.black);
                    passLabel.setForeground(Color.black);

                }else{
                    panelF.setVisible(true);
                    panelP.setVisible(false);
                    nameLabel.setForeground(Color.WHITE);
                    passLabel.setForeground(Color.WHITE);
                }
            }
        });
        logujButton.setBounds(10, 200, 100, 60);
        contentPane.add(logujButton);

        czyscButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.setBackground(null);
                name.setText("");
                pass.setText("");
                panelP.setVisible(false);
                panelF.setVisible(false);
                nameLabel.setForeground(Color.black);
                passLabel.setForeground(Color.black);
            }
        });
        czyscButton.setBounds(120, 200, 100, 60);
        contentPane.add(czyscButton);

    }



    private boolean sprawdzPassy(JPanel pane, String name, char[] pass){
        System.out.println("sprawdzam passy "+name);

        ArrayList<Character> passA = new ArrayList<>();

        int len = pass.length;

        for (char c : pass) passA.add(c);

        if(ania.checkPass(passA) && Objects.equals(ania.getNazwa(), name)){
            System.out.println("hurra!!! haslo poprawne");
            pane.setBackground(new Color(76, 255, 0));
            return true;
        } else {
            System.out.println("podano złe dane logowania");
            pane.setBackground(new Color(225, 9, 9));
            return false;
        }


    }
    /**
     * Metoda wypisuje w konsoli którą opcję z menu wybrał użytkownik
     * @param znak przekazuje pojedyńczy znak identyfikujący opcję, którą wybrał użytkownik
     */
    private void doPracuje(char znak) {
        System.out.println("Użytkownik wybrał opcje "+ znak);

    }
}
