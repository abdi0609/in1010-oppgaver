public class Lege implements Comparable<Lege>{

    String navn;
    IndeksertListe<Resept> utskrevedeResepter;

    public Lege(String navnPAR) {
        navn = navnPAR;
        utskrevedeResepter = new IndeksertListe<>();
    }

    public String hentNavn() {
        return navn;
    }
    

    public IndeksertListe<Resept> hentResepter() {
      return utskrevedeResepter;
    }
   //metode som oppretter resept, legger til i listen, legger til hos pasient og returnerer
    HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)  {
      
      utskrevedeResepter.leggTil((Resept)new HvitResept(legemiddel, this, pasient, reit));
      pasient.nyResept(new HvitResept(legemiddel, this, pasient, reit));
      HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
      return hvitResept;
    }

    //metode som oppretter resept, legger til i listen, legger til hos pasient og returnerer
    Militaerresept skrivMilResept(Legemiddel legemiddel, Pasient pasient)  throws UlovligUtskrift{
    
     
      utskrevedeResepter.leggTil((Resept)new Militaerresept(legemiddel, this, pasient));
      pasient.nyResept(new Militaerresept(legemiddel, this, pasient));
      Militaerresept militaerresept = new Militaerresept(legemiddel, this, pasient);
      return militaerresept;
    }
     //metode som oppretter resept, legger til i listen, legger til hos pasient og returnerer
    PResept skrivPResept(Legemiddel legemiddel, Pasient pasient,int reit) throws UlovligUtskrift{
      
      utskrevedeResepter.leggTil((Resept)new PResept(legemiddel, this, pasient, reit));
      pasient.nyResept(new PResept(legemiddel, this, pasient, reit));
      PResept pResept = new PResept(legemiddel, this, pasient, reit);
      return pResept;
    }
     //metode som oppretter resept, legger til i listen, legger til hos pasient og returnerer
    BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{

      utskrevedeResepter.leggTil((Resept)new BlaaResept(legemiddel, this, pasient, reit));
      pasient.nyResept(new BlaaResept(legemiddel, this, pasient, reit));
      BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
      return blaaResept;
    }


    @Override
    public String toString() {
        return "Lege. Navn: " + navn + ".";
    }

    @Override
    public int compareTo(Lege andre) {
      return this.navn.compareTo(andre.navn);
    }

    

    
    

}
