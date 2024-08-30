import java.io.*;
import java.util.Scanner;

public class Labyrint {
    private int rad;
    private int kol;
    private Rute[][] kordinat;
   

    public Labyrint(String file) throws FileNotFoundException {
        
        File filnavn= new File(file);
        Scanner scan = new Scanner(filnavn);
        String[] lengde = scan.nextLine().strip().split(" ");
        kordinat = new Rute[Integer.parseInt(lengde[0])][Integer.parseInt(lengde[1])];
        int counter = 1;
        int yPunkt= 0;
    
        rad = Integer.parseInt(lengde[0]);
        kol = Integer.parseInt(lengde[1]);
        while (scan.hasNextLine()) {
            String[] seksjon = scan.nextLine().strip().split("");
            for (int i = 0; i < seksjon.length; i++) {
                Rute punkt = null;
                
                if (counter == 1){
                    if (seksjon[i].equals("#")) {
                    punkt = new SortRute(i, yPunkt, this);
                    }
                    else if (seksjon[i].equals(".")){
                     punkt = new Aapning(i, yPunkt, this); 
                    }
                
                if  (counter == hentRad() + 1){
                    if (seksjon[i].equals("#")){ 
                    punkt = new SortRute(i, yPunkt, this);
                    }
                    else if (seksjon[i].equals(".")) {
                    punkt = new Aapning(i, yPunkt, this); 
                    }
                }
                }
                else {
                    if (seksjon[i].equals(".")) {
                        punkt = new HvitRute(i, yPunkt, this);
                    }
                    if (seksjon[i].equals("#")) {
                        punkt = new SortRute(i, yPunkt, this);
                    }
                    else if (seksjon[i].equals(".")&&(i == seksjon.length - 1)) {
                            punkt = new Aapning(i, yPunkt, this);
                    
                        }
                    else if (seksjon[i].equals(".")&&(i == 0)) {
                            punkt = new Aapning(i, yPunkt, this);
                    
                        
                    }
    
                }
    
                kordinat[yPunkt][i] = punkt;
            }
            counter+=1;
            yPunkt+=1;
        }
        
    
        finnNabo();;
    }  

    
    public void finnUtveiFra(int radPAR, int kolPAR) {
        System.out.println("Aapninger:\n");
        if (!(hentPunkt(radPAR, kolPAR) instanceof SortRute)){ 
            kordinat[radPAR][kolPAR]=hentPunkt(radPAR, kolPAR);
            kordinat[radPAR][kolPAR].finn(null);
            
        }
        else{
            System.out.println("Kan ikke starte i sort rute."); 
            return;
        }
    }

    public int hentRad() {
        return rad;
    }
    public int hentkolonner() {
        return kol;
    }



    
    public Rute hentPunkt(int y, int x) {
        Rute map=kordinat[y][x];
        return map;
    }


    
    @Override
    public String toString() {
        String tekst="";
        int verdi=hentRad() - 1;
        for (int i = 0; i < hentRad(); i++) {
            for (int j = 0; j < hentkolonner(); j++) {
                tekst+= Character.toString(kordinat[i][j].endreType());
            }
            if (i < verdi){
                 tekst += "\n";
            }
            else{
                tekst+="";

            }
        }
        

        return tekst;
    }
    
                    
    
    public void finnNabo () {
        Rute over  = null;
        Rute under = null;
        Rute hoyre= null;
        Rute venstre = null;
        for (int i = 0; i < hentRad(); i++) {
            for (int j = 0; j < hentkolonner(); j++) {
                Rute punkt =kordinat[i][j];
                int kord1=hentkolonner() - 1;
                int kord2=hentRad()-1;
                
                
                int verdi1=i-1;
                if (0<=verdi1) {
                    over = kordinat[verdi1][j];
                }

                int verdi3=j+1;
                if (kord1>=verdi3) {
                    hoyre = kordinat[i][verdi3];
                }

                int verdi2=i+1;
                if (kord2>=verdi2 ) {
                    under = kordinat[verdi2][j];
                }

                int verdi4=j-1;
                if (0 <= verdi4) {
                    venstre = kordinat[i][verdi4];
                }
                punkt.leggInnOmraade(over, under, hoyre, venstre);
            }
        }
                
    }
}