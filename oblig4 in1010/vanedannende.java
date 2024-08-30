// subklasse av klassen legemiddel,som arver egenskapene derfra
class Vanedannende extends Legemiddel {
    //privat instansvariabel
    private int styrke;
    // konstruktør
    public Vanedannende(String navnPAR, int prisPAR, double virkestoffPAR, int styrkePAR ) {
        super(navnPAR, prisPAR, virkestoffPAR);
        styrke=styrkePAR;

    }
    // metode som returnerer variabelen styrke.
    public int hentVanedannendeStyrke() {
        return styrke;
        
    }
    // tostring metode som returner alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Type: Vanedannende. Styrke: " + styrke;
    }

}
    

