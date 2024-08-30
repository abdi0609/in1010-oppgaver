// subklasse av klasse legemiddel, arver egenskaper fra legemiddel
class Narkotisk extends Legemiddel {
    //privat instansvariabel
    private int styrke;
    // oppretter konstruktør
    public Narkotisk(String navnPAR, int prisPAR, double virkestoffPAR, int styrkePAR) {
        super(navnPAR, prisPAR, virkestoffPAR);
        styrke=styrkePAR;
       
}
// metode som returnerer variabelen styrke
public int hentNarkotiskStyrke() {
    return styrke;
}
// tostring metode som returner alt til typen string.
@Override
public String toString() {
        return super.toString() + " Type: Narkotisk. Styrke: " + styrke + ".";
    }

}


