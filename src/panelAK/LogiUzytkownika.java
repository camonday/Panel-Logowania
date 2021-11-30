package panelAK;

import java.util.ArrayList;

/**
 * Klasa pomocnicza przechowujaca dane logowania
 */
public class LogiUzytkownika {

    private final String nazwa;
    private final ArrayList<Character> pass = new ArrayList<>();

    /**
     * utworzenie nowych danych logowania, przy haśle podanym w Stringu
     * @param nazwa  nazwa użytkownika
     * @param pass  hasło użytkownika
     */
    public LogiUzytkownika (String nazwa, String pass){
        this.nazwa = nazwa;
        int len = pass.length();
        for (int i=0; i<len; i++) this.pass.add(pass.charAt(i));
    }

    /**
     * utworzenie nowych danych logowania, przy haśle podanym w ArrayList.
     * Narazie nie używana, ale można wykorzystać jeśli dodamy opcję rejestrowania
     * @param nazwa  nazwa użytkownika
     * @param pass  hasło użytkownika
     */
    public LogiUzytkownika (String nazwa, ArrayList<Character> pass){
        this.nazwa=nazwa;
        this.pass.addAll(pass) ;
    }

    public String getNazwa() {
        return nazwa;
    }

    public boolean checkPass(ArrayList<Character> podane){
        return pass.equals(podane);
    }
}
