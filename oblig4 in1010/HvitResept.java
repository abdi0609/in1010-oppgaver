// oppretter subklassen hvitresept som arver egenskaper fra Resept
public class HvitResept extends Resept{

   
   
    private String farge = "Hvit";


    //konstruktør med super() som gjør at konstruktøren fra forelder blir arvet
    public HvitResept(Legemiddel legemiddelPAR, Lege legePAR, Pasient pasientPAR, int reitPAR) {
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
        return pris;
    }
    // tostring metode som returnerer alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Farge: " + farge + ".";
    }
}
