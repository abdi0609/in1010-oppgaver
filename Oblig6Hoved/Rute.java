abstract class Rute {
    protected Rute[] omraader = new Rute[4];
    protected int x;
    protected int y;
    protected Labyrint labyrint;
    
    
    
    public Rute(int xPAR, int yPAR, Labyrint labyrintPAR) {
        x = xPAR;
        y = yPAR;
        labyrint = labyrintPAR;
    }
    public Lagring hentPunkter() { 
        Lagring kord=new Lagring(y, x);
        return kord; 
    }
 

    public void sjekkOmraade() {
        for (Rute omraade : omraader) {
             if(omraade != null){
                char tegn=omraade.endreType();
                String tekst=Character.toString(tegn);
                System.out.println(tekst);
                
             }
             
             String tekst="ingenting å se her";
             System.out.println(tekst);
             
        }
    }
    public void leggInnOmraade(Rute over, Rute under, Rute hoyre, Rute venstre) {
        Rute[] retning = new Rute[4];
        retning[0] = over;
        retning[1] = under;
        retning[2] = hoyre;
        retning[3] = venstre;
        for (int i = 0; i < omraader.length; i++) {
        omraader = retning;
        }
    }

    abstract void finn(Rute fra);

    
    abstract char endreType();
}