package panelAK;

import java.util.ArrayList;

public class LogiUzytkownika {


    private final String nazwa;
    private final ArrayList<Character> pass = new ArrayList<>();

    public LogiUzytkownika (String nazwa, String pass){
        this.nazwa = nazwa;
        int len = pass.length();
        for (int i=0; i<len; i++) this.pass.add(pass.charAt(i));
    }

    public String getNazwa() {
        return nazwa;
    }

    public boolean checkPass(ArrayList<Character> podane){
        return pass.equals(podane);
    }
}
