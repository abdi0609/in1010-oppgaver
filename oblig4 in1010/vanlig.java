// subklasse av legemiddel, som arver egenskapene derfra
class Vanlig extends Legemiddel{
    // konstruktør
    public Vanlig(String navnPAR, int prisPAR, double virkestoffPAR) {
        super(navnPAR, prisPAR, virkestoffPAR);
        
        
    }
    // tostring metode som returner alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Type: Vanlig.";
  }

    
}

    
