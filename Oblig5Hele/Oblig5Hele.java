import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

//klasse Oblig5Hele som brukertestdataene i mappene TestDataLiten og TestData og skriv ut de subsekvensene som
//forekommer oftest blant de som har vært smittet i forhold til de som ikke har vært smittet.
// i tillegg skriver den  ut alle subsekvenser som forekommer 7 flere ganger eller ofteren blant de som har hatt viruset enn de
//som har vært friske, ved bruk av mappen Data
// for å velge mappen du skal kjøre programmet på tast inn i linje 24.
public class Oblig5Hele {
    private static Monitor2 friskepers;
    private static Monitor2 sykepers;
    private static CountDownLatch vegg2;
    private static int antsyke = 0;
    private static int antikkesyke = 0;
    private static int antfiler;
    private static CountDownLatch vegg1=new CountDownLatch(antfiler);
    private static File mapper;
    

    public static void main(String[] args) throws InterruptedException {
        String[] filer = {"data"}; 
        String Mappe = filer[0];
        sykepers=new Monitor2();
        friskepers=new Monitor2();

        henterDATA(Mappe);
        kjoererData(Mappe);
        System.out.println("dette kan ta litt tid...");
        VentLitt();
       System.out.println("program har kjørt ferdig");
       if(filer[0].equals("testDataLiten")||filer[0].equals("testData") ){
           System.out.println("subsekvensene som forekommer oftest blant de som har vært smittet vs de som ikke har vært smittet");
           sammenlignAntall(sykepers, friskepers);
        
       }else{
            System.out.println("\nDominante sekvenser:");
           sammenlignData();
       }
    }

    
    private static void henterDATA(String mappe1) {
        int nyanttraader;
        mapper = new File(mappe1);
        antfiler = mapper.list().length - 1;
        int anttraader1 = antfiler - 2;
        int anttraader2 = antfiler - 1;

        
        try {
            File fil= new File(String.format("%s/metadata.csv", mapper));
            Scanner scan = new Scanner(fil);
            while (scan.hasNextLine()) {
                String[] linje = scan.nextLine().strip().split(",");
                String verdi=linje[1];
                if (verdi.equals("True")){
                     antsyke += 1;
                }
                if (verdi.equals("False")) {
                    antikkesyke +=1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        if (antsyke > 1){
            if (antikkesyke > 1){
                nyanttraader=anttraader1;
        }
        else {
            nyanttraader=anttraader2;
        }
        vegg2 = new CountDownLatch(nyanttraader);
    }
        
    }

    private static void kjoererData(String mappe1) {
        mapper = new File(mappe1);
        
        try {
            File fil= new File(String.format("%s/metadata.csv", mapper));
            Scanner scan = new Scanner(fil);
            while (scan.hasNextLine()) {
                String[] linje = scan.nextLine().split(",");
                String info=linje[1];
                String navnpaafil=linje[0];
                
                if (info.equals("False")) {
                    Thread trad = new Thread(new LeseTrad(mapper + "/" + navnpaafil, friskepers, vegg1));
                    trad.start();
                }
                if (info.equals("True")) {
                    Thread trad = new Thread(new LeseTrad(mapper + "/" + navnpaafil, sykepers, vegg1));
                    trad.start();
                }                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       
        if (antsyke <= 1) {
            Thread trad = new Thread(new FletteTrad(sykepers, vegg2, antsyke));
            trad.start();
            
        } 
        else {
            int max=antsyke - 1;
            for (int i = 0; i < max; i++) {
                Thread trad = new Thread(new FletteTrad(sykepers, vegg2, antsyke));
                trad.start();
        }
    }

        if (antikkesyke <= 1) {
            Thread trad = new Thread(new FletteTrad(friskepers, vegg2, antikkesyke));
            trad.start();
            
        } else {
            int max=antikkesyke - 1;
            for (int i = 0; i < max; i++) {
                Thread trad = new Thread(new FletteTrad(friskepers, vegg2, antikkesyke));
                trad.start();
        }
    }
    }

    private static void sammenlignData() {
        ArrayList<Subsekvens> dominant = new ArrayList<>();
        int count=0;
        HashMap<String, Subsekvens> friskmap = friskepers.hentHash();
        HashMap<String, Subsekvens> sykmap = sykepers.hentHash();
        for (Subsekvens syksub: sykmap.values()) {
            for (Subsekvens frisksub : friskmap.values()) {
                if (syksub.hentSub().equals(frisksub.hentSub())) {
                    count=syksub.hentAntall() - frisksub.hentAntall();
                    if(count>=7){
                        dominant.add(syksub);
    
                    }
                }
            }
        }
        
        for (Subsekvens sub: dominant) {
            System.out.print(sub);
        }
    }
    private static void VentLitt() throws InterruptedException {
        vegg1.await();
        vegg2.await();
        
    } 

    private static void sammenlignAntall(Monitor2 sykePAR, Monitor2 friskePAR) {
        ArrayList<Subsekvens> flest;
        ArrayList<Subsekvens> midlertidig;
        HashMap<String, Subsekvens> syke;
        HashMap<String, Subsekvens> friske;
        int ant;
        if (sykePAR.hentAntall() == 1) {
        flest = new ArrayList<>();
        midlertidig = new ArrayList<>();
        syke = sykePAR.hentHash();
        friske = friskePAR.hentHash();
        ant = 0;
            for (Subsekvens sub : syke.values()) {
                if (sub.hentAntall() > ant) {
                    ant = sub.hentAntall();
                    
                }
            }
            for (Subsekvens sub : syke.values()){
                if (sub.hentAntall() == ant) {
                    flest.add(sub);
                }
            }

            for (Subsekvens nokkel : flest) {
                String verdi1=nokkel.hentSub();
                for (Subsekvens subsekvens : friske.values()) {
                    String verdi2=subsekvens.hentSub();
                    if (verdi1.equals(verdi2)) {
                        midlertidig.add(nokkel);
                    }
                }
            }
            for (Subsekvens nokkel : midlertidig){
                flest.remove(nokkel);
            }
            for (Subsekvens sekvens : flest) {
                System.out.println(sekvens);
            }
        
        }
    }
}
    

       
        
    


   
      