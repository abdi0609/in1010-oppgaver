import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.io.*;

public class SubsekvensRegister {
    protected ArrayList<HashMap<String, Subsekvens>> subregister = new ArrayList<>();
    
    
    public HashMap<String, Subsekvens> hentHash() { 
        int stoerrelse=subregister.size();
        if (stoerrelse > 0){
            subregister.get(0);
         }
         HashMap<String, Subsekvens>map=subregister.remove(0);
        return map;
    }

    
    public void leggtil(HashMap<String, Subsekvens> map) {
        subregister.add(map); 
       }
      
    
   
    public int hentAntall() { 
        return subregister.size(); 
    }

    
    public static HashMap<String, Subsekvens> filInnleser(String filnavn) {
        
        HashMap<String, Subsekvens> filkart = new HashMap<>();
        
        try {
            
            File fil = new File(filnavn);
            Scanner scan = new Scanner(fil);

            while (scan.hasNextLine()) {
                String linje = scan.nextLine();
                int lengde=linje.length();

               
                if (lengde < 3) {
                    System.out.println("feil, linjen er for kort");
                    System.exit(1);
                }

                if (lengde >= 3) {
                    for (int i = 0; i < lengde-2; i++) {
                        String subsekvens = linje.substring(i, i+3);
                        
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

   
    public static HashMap<String, Subsekvens> leggSammen(HashMap<String, Subsekvens> hmap1, HashMap<String, Subsekvens> hmap2) {
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
            return nyhmap;
          }
        }
        
    