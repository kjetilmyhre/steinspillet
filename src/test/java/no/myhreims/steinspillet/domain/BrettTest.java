package no.myhreims.steinspillet.domain;


import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BrettTest {

    @Test
    public void testSkrivBrett() throws Exception {
        String monster = "IIFFFII;IFFFFFI;FFFFFFF;FFFTFFF;FFFFFFF;IFFFFFI;IIFFFII";
        Brett brett = new Brett(monster);
        System.out.println(brett.skrivBrett());
    }

    @Test
    public void testSkrivLovligeFlytt() {
        String monster = "IIFFFII;IFFFFFI;FFFFFFF;FFFTFFF;FFFFFFF;IFFFFFI;IIFFFII";
        Brett brett = new Brett(monster);
        System.out.print(brett.skrivLovligeFlytt());
    }

    @Test
    public void testFinnLovligeFlytt() {
        String monster = "IIFFFII;IFFFFFI;FFFFFFF;FFFTFFF;FFFFFFF;IFFFFFI;IIFFFII";
        Brett brett = new Brett(monster);
        List<Flytt> lovligeFlytt = brett.finnLovligeFlytt();
        assertEquals(4, lovligeFlytt.size());
    }

    @Test
    public void testFlyttKule() {
        String monster = "IITTTII;ITTTTTI;TTTTTTT;FFTTTTT;TTTTTTT;ITTTTTI;IITTTII";
        Brett brett = new Brett(monster);
        List<Flytt> lovligeFlytt = brett.finnLovligeFlytt();
        assertEquals(1, lovligeFlytt.size());
        System.out.println(brett.skrivBrett());
        brett.flyttKule(lovligeFlytt.get(0), false);
        System.out.println(brett.skrivBrett());

    }

    @Test
    public void testFlyttKuleTilbake(){
        String monster = "IITTTII;ITTTTTI;TTTTTTT;FFTTTTT;TTTTTTT;ITTTTTI;IITTTII";
        Brett brett = new Brett(monster);
        String utgangsbrett = brett.skrivBrett();
        List<Flytt> lovligeFlytt = brett.finnLovligeFlytt();
        assertEquals(1, lovligeFlytt.size());
        System.out.println(utgangsbrett);
        brett.flyttKule(lovligeFlytt.get(0), false);
        System.out.println(brett.skrivBrett());
        brett.flyttKule(lovligeFlytt.get(0), true);
        System.out.println(brett.skrivBrett());
        assertEquals(utgangsbrett, brett.skrivBrett());
    }
}
