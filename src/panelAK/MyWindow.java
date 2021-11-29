package panelAK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this("undefined");
    }

    public MyWindow(String title){
        super (title);
        buildOkineko();
    }

    /**
     * Buduje okienko
     */
    protected void buildOkineko(){
        setBounds(100,100, 500, 300);
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


    }

    /**
     * Metoda wypisuje w konsoli którą opcję z menu wybrał użytkownik
     * @param znak przekazuje pojedyńczy znak identyfikujący opcję, którą wybrał użytkownik
     */
    private void doPracuje(char znak) {
        System.out.println("Użytkownik wybrał opcje "+ znak);

    }
}
