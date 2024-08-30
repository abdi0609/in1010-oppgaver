
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

//klasse Oblig5Del2B som bruker skriver du ut subsekvensen som har flest forekomster (antall). 
//I TestDataLitenLike bør dette være ASS med 3 forekomster 
//og i TestDataLike, QYF med 9 forekomster.
//for å velge mappen du skal kjøre programmet på tast inn i linje 21.

public class Oblig5Del2B {
    Monitor2 monitor;
    CountDownLatch vegg1;
    CountDownLatch vegg2;
    int antfiler=0;
    int anttraader=0;
  

 
    public static void main(String[] args) throws InterruptedException {
        String[] filer = {"testdatalike"};
        String Mappe = filer[0];
        Monitor2 monitor = new Monitor2();

        File mappe = new File(Mappe);
        int lengde = mappe.list().length - 1;
        int antfiler = lengde;
        int nylengde=antfiler-1;
        int anttraader = nylengde;
       

        CountDownLatch vegg1 = new CountDownLatch(antfiler);
        CountDownLatch vegg2 = new CountDownLatch(anttraader);


        hentData(Mappe, monitor, vegg1);

       

        for (int i = 0; i < anttraader; i++) {
            Thread trad = new Thread(new FletteTrad(monitor, vegg2, antfiler));
            trad.start();
        }

    
        vegg1.await();
        vegg2.await();
        if(filer[0].equals("testdatalitenlike")||filer[0].equals("testdatalike") ){
            FlestForekomster(monitor);
        }
        else{
            System.out.println("du har gitt feil mappenavn, sjekk igjen om mappenavn er riktig");
        }
    }

    private static void hentData(String mappe, Monitor2 monitor, CountDownLatch vegg) throws InterruptedException {
        try { 
             
            File fil=new File(String.format("%s/metadata.csv", mappe));
            Scanner scan = new Scanner(fil);
            
            while (scan.hasNextLine()) {
                String filnvn = scan.nextLine();
                String filvei = String.format("%s/%s", mappe, filnvn);
                LeseTrad lestrad=new LeseTrad(filvei, monitor,vegg);
                Thread nytrad = new Thread(lestrad);
                nytrad.start();
            }
            
            scan.close();
            
            
        } catch (FileNotFoundException e) { 
            System.out.println("Fant ikke fil."); }
    }

    
    private static void FlestForekomster(Monitor2 monitor) {
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
                    
                }
  
