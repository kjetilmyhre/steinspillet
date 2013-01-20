package no.myhreims.steinspillet.service;

import no.myhreims.steinspillet.domain.Brett;
import no.myhreims.steinspillet.domain.Flytt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpillTjeneste {

    private int losningerSjekket=0;
    private Date finnLosningStartet;
    private Date timestampTeller;
    private int besteResultat=999;
    private String besteBrett="";
    private BrettTjeneste brettTjeneste;

    /**
     * @param brett
     * @return Liste av flytt som løser brettet eller null dersom det ikke finnes noen løsning
     */
    public List<Flytt> finnEnLosning(Brett brett) {
        if (finnLosningStartet==null){
            finnLosningStartet=new Date();
            timestampTeller=new Date();
        }

        losningerSjekket++;
        if (losningerSjekket%1000000==0){
            Date now = new Date();
            long millisPer1000 = now.getTime()-timestampTeller.getTime();
            float losningerPerSekund = 60000000/(float)millisPer1000;
            System.out.println("Sjekket "+losningerSjekket + " Sjekker "+losningerPerSekund+" løsninger i sekundet. Beste gjenstående: "+besteResultat );
            System.out.println(besteBrett);
            timestampTeller = new Date();
        }

        int gjenstaende = brettTjeneste.tellAntalFylteHull(brett);

        if (gjenstaende==1) {
            return new ArrayList<Flytt>();
        }

        List<Flytt> lovligeFlytt = brettTjeneste.finnLovligeFlytt(brett);

        if (lovligeFlytt.size()==0) {

            if (gjenstaende<besteResultat) {
                besteResultat = gjenstaende;
                besteBrett = brett.skrivBrett();
            }
        }

        List<Flytt> losningsFlytt = null;
        for (Flytt flytt : lovligeFlytt) {
            if (losningsFlytt == null) {
                brett.flyttKule(flytt, false);
                List<Flytt> losningsFlyttTre = finnEnLosning(brett);
                if (losningsFlyttTre != null) {
                    //Losning funnet!
                    losningsFlytt = losningsFlyttTre;
                    losningsFlytt.add(flytt);
                }
                brett.flyttKule(flytt, true);
            }
        }

        return losningsFlytt;
    }

    public String lagFlyttListe(List<Flytt> flytt, Brett utgangsbrett){
        StringBuffer sBuf = new StringBuffer();
        sBuf.append(utgangsbrett.skrivBrett());
        sBuf.append('\n');
        for (int x=flytt.size()-1;x>=0;x--){
            Flytt stepFlytt = flytt.get(x);
            sBuf.append(stepFlytt.toString());
            sBuf.append('\n');
            utgangsbrett.flyttKule(stepFlytt, false);
            sBuf.append(utgangsbrett.skrivBrett());
            sBuf.append('\n');
        }

        return sBuf.toString();
    }
}
