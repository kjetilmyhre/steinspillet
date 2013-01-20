package no.myhreims.steinspillet.service;

import no.myhreims.steinspillet.domain.Brett;
import no.myhreims.steinspillet.domain.Flytt;
import org.junit.Test;

import java.util.List;


public class SpillTjenesteTest {

    private SpillTjeneste spillTjeneste = new SpillTjeneste();


    @Test
    public void testFinnEnLosning() throws Exception {
//        String monster = "IITTTII;ITTTTTI;TTTTTTT;FFTTTTT;TTTTTTT;ITTTTTI;IITTTII";
        Brett brett = new Brett(Brett.STANDARD_BRETT);
        String utgangsbrett = brett.skrivBrett();
        List<Flytt> losningsFlytt = spillTjeneste.finnEnLosning(brett);

        System.out.println(spillTjeneste.lagFlyttListe(losningsFlytt, brett));
    }
}
