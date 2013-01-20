package no.myhreims.steinspillet.service;


import no.myhreims.steinspillet.domain.Brett;
import no.myhreims.steinspillet.domain.Flytt;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SpillTjeneste spillTjeneste = new SpillTjeneste();
        Brett brett = new Brett(Brett.STANDARD_BRETT);
        List<Flytt> flytt =
                spillTjeneste.finnEnLosning(brett);
        System.out.println(spillTjeneste.lagFlyttListe(flytt, brett));
    }
}
