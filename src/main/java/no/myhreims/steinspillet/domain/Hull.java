package no.myhreims.steinspillet.domain;


public class Hull {
    public enum Verdi {
        FYLT('F', 'X'), TOMT('T', 'O'), IKKE_TILGJENGELIG('I', ' ');
        private final char inputSymbol;
        private final char printSymbol;

        private Verdi(char inputSymbol, char printSymbol) {
            this.inputSymbol = inputSymbol;
            this.printSymbol = printSymbol;
        }

        public static Verdi fraInputSymbol(char symbol) {
            for (Verdi verdi : Verdi.values()) {
                if (verdi.inputSymbol == symbol) {
                    return verdi;
                }
            }
            return null;
        }

        public char getInputSymbol() {
            return inputSymbol;
        }

        public char getPrintSymbol() {
            return printSymbol;
        }
    }
    
    private Verdi verdi;
    
    public Hull(char symbol) {
        verdi = Verdi.fraInputSymbol(symbol);
        if (verdi == null) {
            throw new IllegalArgumentException("Finner ikke hullverdi med symbol '"+symbol+"'");
        }        
    }
    
    public void byttVerdi(){
        switch (verdi) {
            case FYLT:
                verdi=Verdi.TOMT;
                break;
            case TOMT:
                verdi=Verdi.FYLT;
                break;
            case IKKE_TILGJENGELIG:
                throw new IllegalStateException("Kan ikke endre verdi på hull som ikke er tilgjengelig");
        }
    }

    public Verdi getVerdi() {
        return verdi;
    }
}
