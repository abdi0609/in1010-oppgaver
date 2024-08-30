// oppretter klassen legemiddel 
abstract class Legemiddel {
    //oppretter  instansvariabler av typen protected, 
    protected int id;
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected static int idCounter=1;
    
     // oppretter konstruktør med parametere
    public Legemiddel(String navnPAR, int prisPAR, double virkestoffPAR) {
    // gir disse variabelnavn
        navn=navnPAR;
        pris=prisPAR;
        virkestoff=virkestoffPAR;
        id=idCounter;
        idCounter++;
        


    }
    // oppretter metoder som returnerer intstansvariablene
    public String hentNavn() {
        return navn;
        
    }
    // oppretter metoder som returnerer instansvariablene
    public int hentId() {
        return id;
        
    }
    //oppretter metode som returnerer instansvariablene
    public int hentPris() {
        return pris;
        
    }
    //oppretter metode som returnerer instansvariablene
    public double hentVirkestoff() {
        return virkestoff;
        
    }
    // metode som setter ny pris framfor å bruke pris angitt i intansvariabelen.
    public void settNyPris(int prisPAR) {
        pris=prisPAR;
    }
    // tostring metode som returner alt til typen string.
    @Override
    public String toString() {
        return "Legemiddel. ID: " + id + ". Navn: " + navn + ". Pris: " + pris + " kr. Virkestoff: " + virkestoff + " mg.";
    }
}



    

