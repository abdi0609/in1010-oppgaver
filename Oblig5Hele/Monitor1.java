import java.util.HashMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Monitor1 extends SubsekvensRegister {
    private static Lock locked;
    

    public Monitor1() {
        locked = new ReentrantLock(true);
    }

    @Override
    public void leggtil(HashMap<String, Subsekvens> map) {
        locked.lock();
        try { 
            subregister.add(map);
         } 
        finally { 
            locked.unlock(); 
        }
    }

   
}