//klasse laget som mellomlagring for x og y kordinat.
public class Lagring {
    private int ykordinat;
    private int xkordinat;

    public Lagring(int yPAR, int xPAR) {
        xkordinat = xPAR;
        ykordinat = yPAR;
    }

    public int hentX() { 
        return xkordinat; 
    }
    public int hentY() { 
        return ykordinat; 
    }
}