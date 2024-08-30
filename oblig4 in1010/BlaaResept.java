// oppretter subklasse av klassen resept
public class BlaaResept extends Resept {

    private String farge = "Blaa";

    //konstruktør med super() som gjør at konstruktøren fra forelder blir arvet

    public BlaaResept(Legemiddel legemiddelPAR, Lege legePAR, Pasient pasientPAR, int reitPAR) {
        super(legemiddelPAR, legePAR, pasientPAR, reitPAR);
    }

    //metode med override som overskriver lik metode fra superklassen

    @Override
    public String farge() {
        return farge;
    }

    //metode med override som overskriver lik metode fra superklassen

    @Override
    public int prisAaBetale(int pris) {
        return pris/100*25;
    }
    // tostring metode som returnerer alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Farge: " + farge + ". Rabatt: 75 PROSENT.";
    }

}
