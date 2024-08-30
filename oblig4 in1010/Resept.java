//oppretter superklassen resept med instansvariabler
public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    protected int reit;
    protected int id;
    protected static int idCounter=1;
    
   //konstruktør med parametre 
    public Resept(Legemiddel legemiddelPAR, Lege legePAR, Pasient pasientPAR, int reitPAR) {
        legemiddel = legemiddelPAR;
        lege = legePAR;
        pasient = pasientPAR;
        reit = reitPAR;
        id=idCounter;
        idCounter++;
    }
    //  metoder som skal returnere pris og farge.
    abstract public int prisAaBetale(int pris);
    abstract public String farge();

    //metode som returnerer reseptens id
    public int hentId() {
        return id;
    }
    // metode som returnerer legemiddelet
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }
    //metode som returner lege
    public Lege hentLege() {
        return lege;
    }
    //metode som returnerer pasient 
    public Pasient hentPasient() {
        return pasient;
    }
    //metode som returnerer reit
    public int hentReit() {
        return reit;
    }
    //metode som når kalt bruker 1 reit, hvis reit er gyldig retuneres true og hvis reit er ugyldig returneres false
    public boolean bruk() {
        if (reit>= 1) {
            reit -= 1;
            return true;
        } else {
            return false;
        }
    }
    // tostring metode som returnerer alt til typen string.


public String toString() {
    return "Resept. ID: " + id + ". \n  Legemiddel: " + legemiddel + ". \n  Lege: " + lege + ". \n  PasientId: " + pasient + ". Reit: " + reit + ".";
}
}
