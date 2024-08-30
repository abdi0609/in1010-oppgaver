public class SortRute extends Rute {
    

    public SortRute(int xPAR, int yPAR, Labyrint labyrintPAR) {
        super(xPAR, yPAR, labyrintPAR);
    }

    @Override
    public void finn(Rute fra) {
        return;
    }

    public char endreType() {
        return '#';
    }
}