package no.myhreims.steinspillet.domain;


public class Koordinater {
    private final int y;
    private final int x;

    public Koordinater(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Koordinater transform(Retning retning) {
        int nyY=y+retning.getYtransform();
        int nyX=x+retning.getXtransform();
        return new Koordinater(nyY, nyX);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Koordinater{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
