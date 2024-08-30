// oppretter subklasse av klassen hvitresept
public class PResept extends HvitResept{
// konstruktør med super() som gjør at konstruktøren fra forelder blir arvet
    public PResept(Legemiddel legemiddelPAR, Lege legePAR, Pasient pasientPAR,int reitPAR) {
        super(legemiddelPAR, legePAR, pasientPAR, reitPAR);
    }
 //metode med override som overskriver lik metode fra superklassen
 // i dette tilfelle skal metoden fjerne 108 kr fra prisen, men ikke hvis prisen blir lavere en 0.
    @Override
    public int prisAaBetale(int pris) {
      if (pris-108 >0) {
            return pris;
        } else {
            return 0;
        }
    }
    // tostring metode som returnerer alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Type: P Resept. Rabatt: 108kr.";
    }

}
