public class Aapning extends HvitRute {

    public Aapning(int xPAR, int yPAR, Labyrint labyrintPAR) {
        super(xPAR, yPAR, labyrintPAR);
    }

    // overskrider finn metode
    @Override
    public void finn(Rute fra) {
        System.out.println("////kordinat///");
        System.out.println("(" + y + "," + x +")");
        return;
    }
    
}