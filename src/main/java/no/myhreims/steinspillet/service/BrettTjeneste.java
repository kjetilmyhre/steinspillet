package no.myhreims.steinspillet.service;

import no.myhreims.steinspillet.domain.*;

import java.util.ArrayList;
import java.util.List;

public class BrettTjeneste {
    public static final String STANDARD_BRETT = "IIFFFII;IFFFFFI;FFFFFFF;FFFTFFF;FFFFFFF;IFFFFFI;IIFFFII";

    public Brett byggBrett(String monster) {
        String[] linjer = monster.split(";");
        int linjeLengde = linjer[0].length();
        int antallLinjer = linjer.length;
        Hull[][] hullene = new Hull[antallLinjer][linjeLengde];
        for (int y = 0; y < linjer.length; y++) {
            if (linjer[y].length() != linjeLengde) {
                throw new IllegalArgumentException("Linjene er ikke av samme lengde");
            }

            char[] linje = linjer[y].toCharArray();
            for (int x = 0; x < linjeLengde; x++) {
                char symbolVerdi = linje[x];
                hullene[y][x] = new Hull(symbolVerdi);
            }
        }
        return new Brett(hullene);
    }

    public List<Flytt> finnLovligeFlytt(Brett brett) {
        Hull[][] hullene = brett.getHullene();
        List<Flytt> lovligeFlytt = new ArrayList<Flytt>();
        for (int y = 0; y < hullene.length; y++) {
            for (int x = 0; x < hullene[y].length; x++) {
                Koordinater koordinater = new Koordinater(y, x);
                for (Retning retning : Retning.values()) {
                    Flytt flytt = new Flytt(koordinater, retning);
                    if (erLovligFlytt(flytt, brett)) {
                        lovligeFlytt.add(flytt);
                    }
                }
            }
        }
        return lovligeFlytt;
    }

    public int tellAntalFylteHull(Brett brett) {
        Hull[][] hullene = brett.getHullene();
        int fylteHull = 0;
        for (int y = 0; y < hullene.length; y++) {
            for (int x = 0; x < hullene[y].length; x++) {
                if (hullene[y][x].getVerdi() == Hull.Verdi.FYLT) {
                    fylteHull++;
                }
            }
        }
        return fylteHull;
    }

    public String skrivLovligeFlytt(Brett brett) {
        StringBuffer sBuf = new StringBuffer();
        List<Flytt> lovligeFlytt = finnLovligeFlytt(brett);
        sBuf.append("Lovlige flytt:\n");
        for (Flytt flytt : lovligeFlytt) {
            sBuf.append(flytt);
            sBuf.append('\n');
        }

        return sBuf.toString();
    }

    public boolean erLovligFlytt(Flytt flytt, Brett brett) {
        if (flytt == null || flytt.getKoordinater() == null || flytt.getRetning() == null) {
            return false;
        }
        if (!brett.finnesPaaBrettet(flytt.getKoordinater())) {
            return false;
        }

        Koordinater hopperOver = flytt.hopperOver();
        if (!brett.finnesPaaBrettet(hopperOver)) {
            return false;
        }
        Koordinater landerPaa = flytt.landerPaa();

        if (!brett.finnesPaaBrettet(landerPaa)) {
            return false;
        }

        return brett.hullErFylt(flytt.getKoordinater()) && brett.hullErFylt(hopperOver) && brett.hullErTomt(landerPaa);
    }

    public boolean erVunnet(Brett brett){
        return tellAntalFylteHull(brett)==1;
    }

}
