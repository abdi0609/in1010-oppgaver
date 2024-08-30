
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;
//klasse Oblig5Del2A som skriver du ut subsekvensen som har flest forekomster (antall). 
//I TestDataLitenLike bør dette være ASS med 3 forekomster 
//og i TestDataLike, QYF med 9 forekomster.
//for å velge mappen du skal kjøre programmet på tast inn i linje 15.
public class Oblig5Del2A {
    private static Monitor2 monitor;
    CountDownLatch vegg;

    public static void main(String[] args) throws InterruptedException {
        monitor= new Monitor2();
        String[] filer = {"testdatalike"}; 
        String Mappe = filer[0];
        File mappe = new File(Mappe);
        int lengde=mappe.list().length - 1;
        int antfiler = lengde;
        CountDownLatch vegg = new CountDownLatch(antfiler);

        hentData(Mappe, vegg);
        vegg.await();
        if(filer[0].equals("testdatalitenlike")||filer[0].equals("testdatalike") ){
            leggSammenAlle();
            FlestForekomster();
        }
        else{
            System.out.println("du har gitt feil mappenavn, sjekk igjen om mappenavn er riktig");
        }
    }

        
    

    private static void hentData(String mappe1, CountDownLatch vegg) throws InterruptedException {
        
        try { 

             String metafilvei = String.format("%s/metadata.csv", mappe1);
            
             File fil= new File(metafilvei);
             Scanner scan = new Scanner(fil);
             
             
             while (scan.hasNextLine()) {
                
                 String filnvn = scan.nextLine();
                 String filvei = String.format("%s/%s", mappe1, filnvn);
                 
                 LeseTrad lestraad= new LeseTrad(filvei, monitor,vegg);
                 Thread nytrad = new Thread(lestraad);
                 
        
                 
                 nytrad.start();
             }
             
             scan.close();
         
        } catch (FileNotFoundException e) { 
            System.out.println("Fant ikke fil."); 
    }
    }
       
    private static void FlestForekomster() {
        Subsekvens Flest = null;
        if (monitor.hentAntall() == 1) {
            int ant = 0;
            
            for (Subsekvens sub : monitor.hentHash().values()){
                if (sub.hentAntall() > ant){
                    ant = sub.hentAntall();
                    Flest=sub;
                }
            }
        }
            System.out.println( "flest antall forekomster: "+Flest+"");
        }

        private static void leggSammenAlle() {
            if (monitor.hentAntall() > 1) {
                monitor.leggtil(SubsekvensRegister.leggSammen(monitor.hentHash(), monitor.hentHash()));
                leggSammenAlle();
            }
        }
    }
  
