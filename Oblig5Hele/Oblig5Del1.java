import java.io.*;
import java.util.Scanner;
//klasse Oblig5Del1 som skriver du ut subsekvensen som har flest forekomster (antall). 
//I TestDataLitenLike bør dette være ASS med 3 forekomster 
//og i TestDataLike, QYF med 9 forekomster.
//for å velge mappen du skal kjøre programmet på tast inn i linje 15.


public class Oblig5Del1 {
  
   
    private static SubsekvensRegister subreg;
    public static void main(String[] args) {
        subreg=new SubsekvensRegister();
        String[] filer = {"testdatalike"}; 
        String Mappe = filer[0];
       

        hentData(Mappe);
        if(filer[0].equals("testdatalitenlike")||filer[0].equals("testdatalike") ){
            leggSammenAlle();
            FlestForekomster();
        }
        else{
            System.out.println("du har gitt feil mappenavn, sjekk igjen om mappenavn er riktig");
        }
    }

    
    


    private static void hentData(String mappe1) {
        try {
            
             String metafilvei = String.format("%s/metadata.csv", mappe1);
            
             File fil= new File(metafilvei);
             Scanner scan = new Scanner(fil);
 
             
             while (scan.hasNextLine()) {
                 
                 String filnvn = scan.nextLine();
                 String filvei = String.format("%s/%s", mappe1, filnvn);
                 
                 subreg.leggtil(SubsekvensRegister.filInnleser(filvei));
             }
             
             scan.close();
 
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil.");
        }
    }


    private static void FlestForekomster() {
        Subsekvens Flest = null;
        if (subreg.hentAntall() == 1) {
            int ant = 0;
            
            for (Subsekvens sub : subreg.hentHash().values()){
                if (sub.hentAntall() > ant){
                    ant = sub.hentAntall();
                    Flest=sub;
                   
            }
        }
        
            
            }
            System.out.println( "flest antall forekomster:"+Flest+"");
        }
                    
                
        private static void leggSammenAlle() {
            if (subreg.hentAntall() > 1) {
                subreg.leggtil(SubsekvensRegister.leggSammen(subreg.hentHash(), subreg.hentHash()));
                leggSammenAlle();
            }
        }
}
            
        
    

          


        



    