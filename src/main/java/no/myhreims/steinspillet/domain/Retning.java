package no.myhreims.steinspillet.domain;


public enum Retning {
    OPP(-1,0),NED(1,0),VENSTRE(0,-1),HOYRE(0,1);

    private final int xtransform;
    private final int ytransform;

    private Retning(int ytransform, int xtransform) {
        this.xtransform = xtransform;
        this.ytransform = ytransform;
    }

    public int getXtransform() {
        return xtransform;
    }

    public int getYtransform() {
        return ytransform;
    }
}
