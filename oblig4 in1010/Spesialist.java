//oppretter subklasse av klassen lege,som også har tilgang til interfacet godkjenningsfritak via implements
public class Spesialist extends Lege implements Godkjenningsfritak {
//instans variabel
    private String kontrollID;
// kostruktør
    public Spesialist(String navn, String kontrollIDPAR) {
        super(navn);
        kontrollID = kontrollIDPAR;
    }
// metode som returnerer konrtollid
    @Override
    public String hentKontrollID() {
        return kontrollID;
    }
    //metode som returnerer alt til typen string.
    @Override
    public String toString() {
        return super.toString() + " Type: Spesialist. KontrollID: " + kontrollID;
    }
}

