import java.io.FileNotFoundException;
import java.util.Scanner;

//hovedprogram som finner åpninger i labyrint utifra kordinater tastet inn som input
//ved endring av testfil, skriv inn filnavn linje 10
//mappe navn står på linje 12 

public class Oblig6 {
    
    public static void main(String[] args) throws FileNotFoundException {
        String[] filer={"7.in"};
        Labyrint labyrint = new Labyrint("labyrinter/"+filer[0]);
        String ordre="";
        System.out.println(labyrint);

        while (!ordre.equals("-1")) {
            System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte).");

            String inp = new Scanner(System.in).nextLine();
            
            
            if (inp.equals("-1")) {
                    break;
            }
            else {
                String[] punkter = inp.split(" ");
                int x=Integer.parseInt(punkter[0]);
                int y= Integer.parseInt(punkter[1]);
                labyrint.finnUtveiFra(x,y);
                    
            }

            
        }
        
    }

}