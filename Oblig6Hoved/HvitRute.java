public class HvitRute extends Rute {
    

    public HvitRute(int xPAR, int yPAR, Labyrint labyrintPAR) {
        super(xPAR, yPAR, labyrintPAR);
    }
   

    // oversskrider finn metode
    @Override
    public void finn(Rute fra) {
        if (fra != null) {
            for (Rute omraade:omraader){
                if (omraade != null) {
                    if (omraade != fra){
                        omraade.finn(this);
                    }   
                }
            }
        } else {
            for (Rute omraade:omraader){
                omraade.finn(this);
            
                    }
                }

        return;
    }

    // implementerer tilTegn metode
    public char endreType() {
        return '.';
    }
}