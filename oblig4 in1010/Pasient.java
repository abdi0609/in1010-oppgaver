public class Pasient {

    private String navn;
    private String foedselsnummer;
    private int id;
    private Stabel<Resept> resepter = new Stabel<>();
    static int id_teller = 1;
  
    public Pasient(String navnPAR, String foedselsnummerPAR) {
      navn = navnPAR;
      foedselsnummer = foedselsnummerPAR;
      id = id_teller;
      id_teller ++;
    }
  
    public void nyResept(Resept resept) {
      resepter.leggTil(resept);
    }
  
    public Stabel<Resept> hentResepter() {
      return resepter;
    }
  
    public int hentId() {
      return id;
    }
  
    public String hentNavn() {
      return navn;
    }
  
    public String hentFoedselsnummer() {
      return foedselsnummer;
    }
  
    public String toString() {
      return "Pasient. Navn: " + navn + ", Foedselsnummer: " + foedselsnummer + ", id: " + id + ", Resepter: " + resepter;
    }
  
  }
