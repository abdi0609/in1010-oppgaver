import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Monitor2 extends Monitor1{
    private static Lock locked = new ReentrantLock(true);
    private Condition notready = locked.newCondition();
    
   
    @Override
    public void leggtil(HashMap<String, Subsekvens> map) {
        locked.lock();
        try { 
            subregister.add(map);
            if (hentAntall() >= 2) {
                notready.signalAll();
            }
            
        } finally { 
            locked.unlock(); 
        }
    }
 


   

    
    public Lagring<HashMap<String, Subsekvens>> hentToHash() throws InterruptedException {
        locked.lock();
        try {
           
            while (hentAntall() < 2){
                 notready.await();
            }
             Lagring tofiler= new Lagring<HashMap<String,Subsekvens>>(hentHash(), hentHash());
            return  tofiler;

        } finally { 
            locked.unlock(); 
        }
    }
    public static HashMap<String, Subsekvens> filInnleser(String filnavn) {
        locked.lock();
        try { 
            HashMap<String, Subsekvens> filkart = new HashMap<>();
        
            try {
               
                File fil = new File(filnavn);
                Scanner scan = new Scanner(fil);
    
        
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    int lengde=line.length();
    
                  
                    if (lengde < 3) {
                        System.out.println("feil, linjen er for kort");
                        System.exit(1);
                    }
    
                   
                    if (lengde >= 3) {
                        for (int i = 0; i < lengde-2; i++) {
                            String subsekvens = line.substring(i, i+3);
                            
                            if (!filkart.containsKey(subsekvens)) {
                                Subsekvens nysubsekvens = new Subsekvens(subsekvens, 1);
                                filkart.put(subsekvens,nysubsekvens);
                        }
                    }
                    }
                }
                
             
                scan.close();
            }
            
           
            catch (FileNotFoundException e) {
                System.out.println("feil, fant ikke filen");
                System.exit(1);
            }
    
      
            return filkart;
        }
    
         
        finally { locked.unlock();}
    }


    public void leggSammenTo(HashMap<String, Subsekvens> hmap1, HashMap<String, Subsekvens> hmap2) {
        locked.lock();
        try {
            HashMap<String, Subsekvens> nyhmap = new HashMap<String, Subsekvens>();
            nyhmap.putAll(hmap1);
                for (String nok : hmap2.keySet()) {
                  if (!nyhmap.containsKey(nok)) {
                    nyhmap.put(nok, hmap2.get(nok));
                    
                  } else {
                    int antall = hmap2.get(nok).hentAntall();
                    nyhmap.get(nok).Antalloek(antall);
                    
                  }
                }
           
            
            leggtil(nyhmap);
        } finally {
             locked.unlock(); 
            }
    }
}

  
