

import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private String filnavn;
    private Monitor2 monitor;
    private CountDownLatch vegg;

    public LeseTrad(String filnavnPAR, Monitor2 monitorPAR, CountDownLatch veggPAR) {
        filnavn = filnavnPAR;
        monitor = monitorPAR;
        vegg = veggPAR;
    }

    @Override
    public void run() {

        monitor.leggtil(Monitor2.filInnleser(filnavn));
        vegg.countDown();
    }
}