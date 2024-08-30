import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable{
    private Monitor2 monitor;
    private CountDownLatch vegg;
    private int antfiler;
    Lagring<HashMap<String, Subsekvens>> lagring;

    public FletteTrad(Monitor2 monitorPAR, CountDownLatch veggPAR, int antfilerPAR) {
        monitor = monitorPAR;
        vegg = veggPAR;
        antfiler = antfilerPAR;
    }

    @Override
    public void run() {
        try {
            if (antfiler <1) {
                System.out.println("for faa filer å hente, kan ikke slaa sammen");
            }
           
            if (antfiler > 1) {
                lagring=monitor.hentToHash();
                HashMap<String, Subsekvens>retur1=lagring.hentLagring1();
                HashMap<String, Subsekvens>retur2=lagring.hentLagring2();
                monitor.leggSammenTo(retur1, retur2);
            }
            vegg.countDown();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }   
    }
}



