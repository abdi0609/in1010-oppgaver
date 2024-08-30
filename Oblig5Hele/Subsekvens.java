public class Subsekvens {
 
    public final String subsekvens;
    private int antall;

    public Subsekvens(String subsekvensPAR, int antallPAR) {
        subsekvens = subsekvensPAR;
        antall = antallPAR;
    }

    public int hentAntall() {
        return antall;
    }
    
    public String hentSub() { 
        return subsekvens;
     }
     
    public int Antalloek(int tall) {
        antall+=tall;
        return antall;
    }

    
    public void nyttAntall(int nyantall) {
        antall = nyantall;
    }

    @Override
    public String toString() {
        return "("+subsekvens + ": " + antall+")";
    }
}