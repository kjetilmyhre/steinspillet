package no.myhreims.steinspillet.domain;


import java.util.List;

public class Brett {

    private Hull[][] hullene;
    private List<Flytt> flyttSaaLangt;
    private Integer losningsVerdi = null;

    public Brett(Hull[][] hullene, List<Flytt> flyttSaaLangt, Integer losningsVerdi) {
        this.hullene = hullene;
        this.flyttSaaLangt = flyttSaaLangt;
        this.losningsVerdi = losningsVerdi;
    }

    public boolean finnesPaaBrettet(Koordinater koordinater) {
        return koordinater != null && koordinater.getY() >= 0 && koordinater.getY() < hullene.length && koordinater.getX() >= 0 && koordinater.getX() < hullene[0].length;
    }

    public void flyttKule(Flytt _flytt, boolean tilbake) {

        Flytt flytt = null;

        if (!tilbake) {
            flytt = _flytt;
            if (hullErTomt(flytt.getKoordinater()) || hullErTomt(flytt.hopperOver()) || hullErFylt(flytt.landerPaa())) {
                throw new IllegalStateException("Kan ikke utføre flytt: " + flytt + " på hullene \n" + skrivBrett());
            }
        } else {
            flytt = _flytt.lagTilbakeflytt();
            if (hullErTomt(flytt.getKoordinater()) || hullErFylt(flytt.hopperOver()) || hullErFylt(flytt.landerPaa())) {
                throw new IllegalStateException("Kan ikke utføre tilbakeflytt: " + flytt + " på hullene \n" + skrivBrett());
            }
        }

        byttVerdi(flytt.getKoordinater());
        byttVerdi(flytt.hopperOver());
        byttVerdi(flytt.landerPaa());

    }

    private void byttVerdi(Koordinater koordinater) {
        hullene[koordinater.getY()][koordinater.getX()].byttVerdi();
    }

    public boolean hullErFylt(Koordinater koordinater) {
        return hullene[koordinater.getY()][koordinater.getX()].getVerdi() == Hull.Verdi.FYLT;
    }

    public boolean hullErTomt(Koordinater koordinater) {
        return hullene[koordinater.getY()][koordinater.getX()].getVerdi() == Hull.Verdi.TOMT;
    }

    public String skrivBrett() {
        StringBuffer sBuf = new StringBuffer();

        for (int y = 0; y < hullene.length; y++) {
            Hull[] linje = hullene[y];
            for (int x = 0; x < linje.length; x++) {
                sBuf.append(hullene[y][x].getVerdi().getPrintSymbol());
                sBuf.append(" ");
            }
            sBuf.append('\n');
        }
        return sBuf.toString();
    }

    public Hull[][] getHullene() {
        return hullene;
    }
}
