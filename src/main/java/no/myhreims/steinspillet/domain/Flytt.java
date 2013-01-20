package no.myhreims.steinspillet.domain;


public class Flytt {
    private final Koordinater koordinater;
    private final Retning retning;

    public Flytt(Koordinater koordinater, Retning retning) {
        this.koordinater = koordinater;
        this.retning = retning;
    }

    public Koordinater getKoordinater() {
        return koordinater;
    }

    public Retning getRetning() {
        return retning;
    }

    public Koordinater hopperOver() {
        return koordinater.transform(retning);
    }

    public Koordinater landerPaa() {
        return koordinater.transform(retning).transform(retning);
    }

    public Flytt lagTilbakeflytt() {
        Retning tilbake;
        switch (retning) {
            case OPP:
                tilbake = Retning.NED;
                break;
            case NED:
                tilbake = Retning.OPP;
                break;
            case VENSTRE:
                tilbake = Retning.HOYRE;
                break;
            case HOYRE:
                tilbake = Retning.VENSTRE;
                break;
            default:
                throw new IllegalStateException("Ukjent retning: "+retning);
        }

        return new Flytt(landerPaa(), tilbake);
    }

    @Override
    public String toString() {
        return "Flytt y,x og retning: " + koordinater.toString() + " " + retning.toString();
    }
}
