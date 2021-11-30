package panelAK;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * klasa główna
 */
public class MyWindow extends JFrame {

    private final ArrayList<MyPanel> komunikaty = new ArrayList<>();
    private final ArrayList<JLabel> etykietki = new ArrayList<>();

    private final JTextField name = new JTextField();
    private final JPasswordField pass = new JPasswordField();

    private final LogiUzytkownika ania = new LogiUzytkownika("ania", "123");


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
    }

    public MyWindow() throws HeadlessException {
        this("Okno Logowania");
    }

    public MyWindow(String title){
        super (title);
        buildOkineko();
    }

    /**
     * Buduje okienko
     */
    protected void buildOkineko(){
        setBounds(100,100, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = buildPanel();
        buildMenu(contentPane);

        addKomunikat(contentPane,"Udało Ci się zalogować");
        addKomunikat(contentPane, "Podano niepoprawne dane logowania");

        //Pola do wpisania

        name.setBounds(10, 30, 300, 50);
        contentPane.add(name);
        addEtykietka(name, "Nazwa użytkownika", contentPane);

        pass.setBounds(320, 30, 300, 50);
        contentPane.add(pass);
        addEtykietka(pass, "Hasło", contentPane);

        //Przyciski

        JButton logujButton = new JButton("Loguj");

        logujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               doLoguj(contentPane);
            }
        });
        logujButton.setBounds(10, 200, 100, 60);


        JButton czyscButton = new JButton("Czyść");

        contentPane.add(logujButton);
        czyscButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               doCzysc(contentPane);
            }
        });
        czyscButton.setBounds(120, 200, 100, 60);
        contentPane.add(czyscButton);

    }

    /**
     * Buduje Menu które loguje lub czyści
     * @param contentPane - w którym panelu ma odbyć się logowanie
     */
    protected void buildMenu(JPanel contentPane){
        JMenuBar myBar = new JMenuBar();
        setJMenuBar(myBar);

            JMenu logujMenu = new JMenu("Opcje logowania");
            myBar.add(logujMenu);

            JMenuItem zaloguj = new JMenuItem("Zaloguj");
            zaloguj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doLoguj(contentPane);

                }
            });
            logujMenu.add(zaloguj);


            JMenu czyscMenu = new JMenu("Opcje czyszczenia");
            myBar.add(czyscMenu );

            JMenuItem wyczysc = new JMenuItem("Wyczyść");
            wyczysc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doCzysc(contentPane);
                }
            });
            czyscMenu.add(wyczysc);

    }

    /**
     * Buduje główny panel
     * @return zwracam główny panel który właśnie utworzyłam
     */
    protected JPanel buildPanel(){
        JPanel contentPane = new JPanel();
        // Poniżej: Ustawienia panelu
        {
            contentPane.setBorder(new LineBorder(new Color(255, 105, 44), 5, true));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
        return contentPane;
    }

    /**
     * Buduje Panel wyświetlający komunikat
     * @param contentPane Panel główny w którym wyświetlane będą komunikaty
     * @param tresc treść jaką wyświetlać ma komunikat
     */
    protected void addKomunikat(JPanel contentPane, String tresc){
        MyPanel panel = new MyPanel(tresc);
        panel.setBounds(10,120, 600, 70);
        contentPane.add(panel);
        panel.setVisible(false);
        komunikaty.add(panel);
    }

    /**
     * Dodaję etykietke dla okreslonego komponentu, np pola tekstowego
     * @param komponent  do czego odnosi sie etykietka
     * @param opisKomponentu  opis który wyswietla etykietka
     * @param panel  główny panel w którym wyświetla sie etykietka
     */
    protected void addEtykietka(JComponent komponent, String opisKomponentu, JPanel panel){
        JLabel komponentLabel = new JLabel(opisKomponentu);

        Rectangle pozycja = komponent.getBounds();
        pozycja.setLocation((int)pozycja.getX(),(int) (pozycja.getY()+ pozycja.getHeight()));

        komponentLabel.setBounds(pozycja);
        panel.add(komponentLabel);
        komponentLabel.setLabelFor(komponent);
        etykietki.add(komponentLabel);
    }

    /**
     * Zmieniam kolor etykietek w celu zachowania odpowiedniego kontrastu
     * @param kolor na ten kolor zmieniam kolor etykietek
     */
    protected void kolorEtykietek(Color kolor){
        for (JLabel jLabel : etykietki) jLabel.setForeground(kolor);
    }

    /**
     * Sprawdzam czy podane dane logowania są poprawne
     * @param pane panel główny, przez zmiane koloru informujemy użytkowników czy zalogowanie się powiodło
     */
    private void doLoguj(JPanel pane){

        String nameTresc = name.getText();
        char[] passTresc = pass.getPassword();

        ArrayList<Character> passA = new ArrayList<>();

        for (char c : passTresc) passA.add(c);

        if(ania.checkPass(passA) && Objects.equals(ania.getNazwa(), nameTresc)){
            System.out.println("hurra!!! haslo poprawne");
            pane.setBackground(new Color(76, 255, 0));
            kolorEtykietek(Color.black);
            komunikaty.get(0).setVisible(true);
            komunikaty.get(1).setVisible(false);

        } else {
            System.out.println("podano złe dane logowania");
            pane.setBackground(new Color(225, 9, 9));
            kolorEtykietek(Color.WHITE);
            komunikaty.get(1).setVisible(true);
            komunikaty.get(0).setVisible(false);
        }

        /*
         * czyszczę array żeby hasło nie wypłyneło
         */
        passA.clear();
    }

    /**
     * Resetuje panel podany w parametrze
     * @param contentPane
     */
    private void doCzysc(JPanel contentPane){
        contentPane.setBackground(null);
        name.setText("");
        pass.setText("");
        for (MyPanel myPanel : komunikaty) myPanel.setVisible(false);
        kolorEtykietek(Color.black);
    }
}
