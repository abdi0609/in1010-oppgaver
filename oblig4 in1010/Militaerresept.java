// oppretter subklasse av hvitresept
public class Militaerresept extends HvitResept{
////konstruktør med super() som gjør at konstruktøren fra forelder blir arvet
    public Militaerresept(Legemiddel legemiddelPAR, Lege legePAR, Pasient pasientPAR) {
        super(legemiddelPAR, legePAR, pasientPAR, 3);
    }

    //metode med override som overskriver lik metode fra superklassen


    @Override
    public int prisAaBetale(int pris) {
        return 0;
    }
    // tostring metode som returnerer alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Type: Militaerresept" + ". Rabatt: 100 PROSENT";
    }
}
