
//importerer innebygde java klasser 
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import java.io.PrintWriter;
//oppretter klassen hovedprogram
public class Legesystem { 
//private instansvariabler
  private static IndeksertListe<Pasient> pasienter;
  private static IndeksertListe<Legemiddel> legemidler;
  private static Prioritetskoe<Lege> leger;
  private static IndeksertListe<Resept> resepter;
  
//konstruktør
  public static void main(String[] args)throws FileNotFoundException, UlovligUtskrift { 
    //oppretter listeobjekter
    pasienter=new IndeksertListe<>();
    legemidler=new IndeksertListe<>();
    leger= new Prioritetskoe<>();
    resepter= new IndeksertListe<>();
    // kaller på metode som leser inn fil
    lesFraFil("legedata.txt");
    // kaller på metoden hovedmeny
  try {
      Meny();
  } catch (InputMismatchException x) {
      System.out.println("Du har tastet et tall som det ikke er et valg for");
  }
}
//hovedmeny metode som som gjør at hele programmet kjører 
public static void Meny()throws InputMismatchException, UlovligUtskrift { 
//oppretter scanner objekt og printer ut meny med valgmuligheter
    Scanner scan = new Scanner(System.in);
    System.out.println("Velkommen til legesystemet!");
    System.out.println();
    System.out.println("1: Skriv ut oversikt");
    System.out.println("2: Legg til nye elementer");
    System.out.println("3: Bruk en resept");
    System.out.println("4: Skriv ut statistikk");
    System.out.println("5: Skriv til fil");
    System.out.println("0: avslutt");
    int d = scan.nextInt(); 
//løkke som gjør at så lenge data ikke er 0
    while (d!=0) {
      if(d<0){
        System.out.println("\nprøv igjen, du har tastet feil ");
        d = scan.nextInt(); 
    }
      if (d>6){
        System.out.println("\nprøv igjen, du har tastet feil ");
        d = scan.nextInt(); 
     
      }
     
      if (d==1) {
        System.out.println("Skriv ut oversikt valgt\n");
        System.out.println("1 - Skriv ut Pasienter");
        System.out.println("2 - Skriv ut Lege");
        System.out.println("3 - Skriv ut legemiddel");
        System.out.println("4 - Skriv ut resepter");
        System.out.println("0 - Tilbake til Hovedmeny");
        
        
        skrivUtOversikt();
        
       
                
      } else if (d==2) {
        System.out.println("Legg til nye elementer valgt\n");
        leggTilNyeElementer();
      } else if (d==3) {
        try {
          brukResept(); 
      } catch (NumberFormatException x) {
          System.out.println("Tastet feil");
      }
      } else if (d==4) {
        System.out.println("Skriv ut statistikk valgt \n");
        skrivUtStatistikk();
        System.out.println();
      
      }else if (d==5);
      
      try {
        skrivDataTilFil();
        System.out.println("nytt valg: ");
        d = scan.nextInt();
    } catch (NumberFormatException x) {
        System.out.println("Tastet feil");
    }
    }
    System.out.println("System avsluttet");
    System.exit(0);


    
  }
  // metode som skriver data fra dette legesystemet over til en ny fil
  public static void skrivDataTilFil() throws InputMismatchException, UlovligUtskrift { 
    PrintWriter printW = null;
 
    try {
        printW = new PrintWriter("NyLegesystem.txt");
    } catch (FileNotFoundException e) {
        System.out.println("ikke mulig å skrive ny fil");
        System.exit(1);
    }


    printW.println("# Pasienter (navn, fnr)");
    for (Pasient pas : pasienter) {
        printW.println(pas.hentNavn() + ", " + pas.hentFoedselsnummer());
    }
    
    printW.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
    for (Legemiddel legmid : legemidler) {
        printW.println(legmid.hentNavn() + ", " + legmid.getClass() + ", " + legmid.hentPris() + ", " + legmid.hentVirkestoff());
    }

    
    printW.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
    for (Lege lege : leger) {
        printW.println(lege.hentNavn());
      }
    
  
      
    

  
    printW.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
    for (Resept res : resepter) {
        printW.println(res.hentLegemiddel().hentId() + ", " + res.hentLege().hentNavn() + ", " + res.hentPasient().hentId()
                + ", " + res.getClass() + ", " + res.hentReit());
    }
    printW.close();
    System.out.println("Et Nytt legesystem er skrevet med data fra dette systemet, til filen NyLegesystem.txt\n");
    System.out.println("Tilbake i hovedmeny\n");
    System.out.println();
    System.out.println("1: Skriv ut oversikt");
    System.out.println("2: Legg til nye elementer");
    System.out.println("3: Bruk en resept");
    System.out.println("4: Skriv ut statistikk");
    System.out.println("5: Skriv til fil");
    System.out.println("0: avslutt");
  }
  



// metode som skriver ut statistikk på leger og pasienters narkotiske misbruk, antall resepter skrevet ut
    public static void skrivUtStatistikk() throws InputMismatchException, UlovligUtskrift {
      int antallNarkotiskRes = 0;
      int antallVanedanRes = 0;
      IndeksertListe<Lege> narkotiskLege = new IndeksertListe<Lege>();
      IndeksertListe<Pasient> narkotiskpas = new IndeksertListe<Pasient>();
      IndeksertListe<Integer> legerNarkAntResept = new IndeksertListe<Integer>(); //antallet resepter legene har skrevet ut
      IndeksertListe<Integer> pasienterNarkAntResept = new IndeksertListe<Integer>(); //antallet gyldige resepter pasientene har

      for (Resept resept : resepter) {
          if (resept.hentLegemiddel() instanceof Vanedannende) {
              antallVanedanRes ++;
          } else if (resept.hentLegemiddel() instanceof Narkotisk) {
              antallNarkotiskRes ++;
          }
      }
      boolean skrevetgyldignarkotiskresept = false;
      int antallresepter=0;
      for (Lege lege : leger) {
        antallresepter=0;
        skrevetgyldignarkotiskresept = false;
        Lenkeliste<Resept> l = lege.hentResepter();
        for (Resept resept : l) {
            if (resept.hentLegemiddel() instanceof Narkotisk) {
                skrevetgyldignarkotiskresept = !false;
                antallresepter++;
            }
        }
        if (skrevetgyldignarkotiskresept) {
          legerNarkAntResept.leggTil(antallresepter);
          narkotiskLege.leggTil(lege);
        }
    }
    boolean hargyldignarkotiskresept=false;
    int antgyldignarkotiskresept=0;
    for (Pasient pasient : pasienter) {
      hargyldignarkotiskresept=false;
      antgyldignarkotiskresept=0;
      Stabel<Resept> l = pasient.hentResepter();
      for (Resept res : l) {
              if (res.hentLegemiddel() instanceof Narkotisk) {
                hargyldignarkotiskresept=!false;
                antgyldignarkotiskresept++;
              }
              else if (res.hentReit() != 0){
                hargyldignarkotiskresept=!false;
                antgyldignarkotiskresept++;
              }
          }
          if (hargyldignarkotiskresept) {
            pasienterNarkAntResept.leggTil(antgyldignarkotiskresept);
            narkotiskpas.leggTil(pasient);
          }
      }

    System.out.println("Totalt antall utskrevne resepter paa vanedannende legemidler:"  + antallVanedanRes);
    System.out.println("Totalt antall utskrevne resepter paa vanedannende legemidler:"  + antallNarkotiskRes);
    System.out.println();
    String format = "%-25.25s %-10.10s";
    System.out.println("Resepter skrevet paa narkotika:");
    System.out.printf(format, "legenavn", "antall");
    System.out.println();
    
        for (Lege lege : narkotiskLege) {
            System.out.printf(format, lege.hentNavn(), legerNarkAntResept.hent());
            System.out.println();
        }

  
    System.out.println();
    System.out.println("Gyldige resepter paa narkotika:");
    System.out.printf(format, "pasientnavn", "antall");
    System.out.println();
    
      for (Pasient pas : narkotiskpas) {
        System.out.printf(format, pas.hentNavn(), pasienterNarkAntResept.hent());
        System.out.println();
        }

    }
  
      
    //metode som bruker resept 
  public static void brukResept() throws NullPointerException, InputMismatchException, UlovligUtskrift {
    Scanner scan = new Scanner(System.in);
    int inndata; 
    Resept resept;


    System.out.println("Du kan nå bruke en resept.");
    skrivUtPasienter();
    System.out.println("\nVelg en pasient eller tast 0 for å gå tilbake til menyen");

    
    inndata = scan.nextInt();
    if (inndata == 0) {
      Meny();
    }
    
    
    Pasient pasient = hentPasient(inndata);
    if (pasient == null) {
      System.out.println("Pasient finnes ikke. Id: " + inndata);
      return;
    }
    System.out.println();

  
    System.out.println("Velg en resept:");
    int teller = 1; 
    HashMap<Integer, Resept> hash = new HashMap<>(); 
    for (Resept res : pasient.hentResepter()) {
      System.out.println(teller + ": " + res.hentLegemiddel().hentNavn() + " (" + res.hentReit() + " reit)");
      hash.put(teller, res);
      teller++;
    }
    System.out.print("valg: ");
    inndata= scan.nextInt();
    
    
    try {
      resept = hash.get(inndata);
    } catch (Exception e) {
      System.out.println("Resept finnes ikke. Valg: " + inndata);
      return;
    }
    
    if (resept!=null){
      if (resept.bruk()) {
      System.out.println("Resept brukt. Gjenvaerende reit: " + resept.hentReit() + ".");
      return;
    } else {
      System.out.println("Kunne ikke bruke resept paa " + resept.hentLegemiddel().hentNavn() + ". (ingen gjenvaerende reit)");
    }
  }
  
}

  

  

 
  


/// metode som legger til nye elementer avhengig av dine valg
  private static void leggTilNyeElementer() throws UlovligUtskrift, NumberFormatException {{
    
    System.out.println("1: Ny pasient");
    System.out.println("2: Nytt legemiddel");
    System.out.println("3: Ny lege");
    System.out.println("4: Ny resept");
    System.out.println("0: tilbake");

    
    System.out.println("Du kan nå legge til nye elementer.");
    int inndata;
    Scanner scan = new Scanner(System.in);
    
    inndata = scan.nextInt();
    
    if (inndata==1) {
        System.out.println("ny pasient er valgt\n");
        leggTilNyPasient();
      } else if (inndata==2) {
        System.out.println( "nytt legemiddel er valgt\n");
        leggTilNyttLegemiddel();
      } else if (inndata==3) {
        System.out.println("ny lege er valgt\n");
        leggTilNyLege();
      } else if (inndata==4) {
        System.out.println("ny resept er valgt\n");
        leggTilNyResept();
      }
      else if (inndata==0){
        Meny();
      }
      else{
        System.out.println(" vennligst velg mellom 1-4");
    }
    System.out.println("Du er nå ferdig med å legge til nye elementer.");
  }
}

//metode som legger til ny resept
  private static void leggTilNyResept()throws UlovligUtskrift {
    Scanner scanTall = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);
    skrivUtPasienter();;
    System.out.println("Hva er IDen til pasient: ");
    int PasientID = scanTall.nextInt();
    System.out.println("Hva er reit til pasient: ");
    int Reit = scanTall.nextInt();

    skrivUtLeger(false);;
    System.out.println("Hva er navnet til legen: ");
    String LegeNavn = scanString.nextLine();

    
    skrivUtLegemidler();
    System.out.println("Hva er legemiddel IDen: ");
    int LegemiddelID = scanTall.nextInt();

      
      Pasient pasient = null;
      Lege lege = null;
      Legemiddel legemiddel = null;
      for (Pasient nyPasient : pasienter) {
          if (nyPasient.hentId() == PasientID) {
              pasient = nyPasient;
          }
      }

      
      for (Lege nyLege : leger) {
          if (nyLege.hentNavn().equals(LegeNavn)) {
              lege = nyLege;
          }
      }

      
      for (Legemiddel nyLegemiddel : legemidler) {
          if (nyLegemiddel.hentId() == LegemiddelID) {
              legemiddel = nyLegemiddel;
          }
      }
      System.out.println("1 - P-Resept");
      System.out.println("2 - BlåResept");
      System.out.println("3 - MillitærResept");
      System.out.println("4 -  HvitResepet");
      System.out.println("Hvilken type resept er det nye reseptet, velg mellom de over: ");
      int Reseptinput = scanTall.nextInt();

      if (Reseptinput == 1 && pasient != null && lege != null && legemiddel != null) {
          resepter.leggTil(lege.skrivPResept(legemiddel, pasient, Reit));
          return;
      } else if (Reseptinput == 2 && pasient != null && lege != null && legemiddel != null) {
          resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, Reit));
          return;
      } else if (Reseptinput == 3 && pasient != null && lege != null && legemiddel != null) {
          resepter.leggTil(lege.skrivMilResept(legemiddel, pasient));
          return;
      } else if (Reseptinput == 4 && pasient != null && lege != null && legemiddel != null) {
          resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, Reit));
          return;
      }
    else {
      System.out.println("Du har tastet feil, du kan velge mellom 1-4");
    }
  }




/// metode som legger til ny lege 
  private static void leggTilNyLege() {
    Scanner scanTall = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);
    String tekstsvar;
  
    System.out.println("Du kan nå legge til en ny lege");
    
    System.out.print("Navn: ");
    tekstsvar = scanString.nextLine().strip();
    
    System.out.println("(Mulige alternativer: 1, 2)");
    System.out.println("er legen Spesialist tast 1 og 2 for lege");
    int svar= scanTall.nextInt();
    
    if (svar!=1) {
      Lege nyLege = new Lege(tekstsvar);
      leger.leggTil(nyLege);
      System.out.println("lege lagt til");
      
  }
  else{
    System.out.println("tast inn kontroll id til spesialist");
    String kontrollid= scanString.nextLine().strip();
    Spesialist nySpesialist = new Spesialist(tekstsvar, kontrollid);
    leger.leggTil(nySpesialist);
    System.out.println("spesialist lagt til");
  }
    
  }
  

    
/// metode som legger til nytt legemiddel
  private static void leggTilNyttLegemiddel() {
    Scanner scanTall = new Scanner(System.in);
    Scanner scanString = new Scanner(System.in);
  

    System.out.println("Navnet til legemidellet: ");
    String inputNavn = scanString.nextLine();
    System.out.println("Hva er prisen til legemidellet: ");
    int inputPris = scanTall.nextInt();
    System.out.println("Hva er virkestoffet i mg: ");
    double inputVirkestoff = scanTall.nextDouble();

  
    System.out.println("1:vanlig , 2: narkotisk , 3:vanedannende");
    System.out.println("velg ditt legemiddel, velg et av tallene ovenfor: ");
    int type= scanTall.nextInt();
    if(type==1){
      legemidler.leggTil(new Vanlig(inputNavn, inputPris, inputVirkestoff));
      return;
    } else if (type== 2) {
      System.out.println("Hva er styrken på narkotiske legemiddelet: ");
      int inpStyrke = scanTall.nextInt();
      legemidler.leggTil(new Narkotisk(inputNavn, inputPris, inputVirkestoff, inpStyrke));
      return;
  } else if (type == 3) {
      System.out.println("Hva er styrken på det vanedannende legemiddelet: ");
      int inpStyrke = scanTall.nextInt();
      legemidler.leggTil(new Vanedannende(inputNavn, inputPris, inputVirkestoff, inpStyrke));
      return;
  }
  System.out.println("Du har tastet feil tall, ikke noe valg for " + type);

}
//metode som legger til ny pasient
  private static void leggTilNyPasient() {
    Scanner scanString = new Scanner(System.in);
    String tekst; //mellomledd for å sjekke format på input
    System.out.println("legg til en ny pasient");
    //navn
    System.out.print("Navn: ");
    tekst = scanString.nextLine().strip();
    //foedselsnummer
    System.out.print("Foedselsnummer: ");
    String foedselnummr= scanString.next().strip();
    Pasient nyPasient = new Pasient(tekst, foedselnummr);
    //sjekker at foedselsnummer har riktig format
    
      if (foedselnummr.length() == 11) {
        pasienter.leggTil(nyPasient);
        System.out.println("Pasient lagt til.");
      } else {
        System.out.println("Feil lengde på foedselsnummer");
      }
    
    }
  /// metode som skriver ut oversikt
    private static void skrivUtOversikt()throws InputMismatchException, UlovligUtskrift { 
      Scanner scanTall = new Scanner(System.in);
      System.out.println("hva vil du skrive ut");
      int svar = scanTall.nextInt();
      while (svar > 5 && svar <= 0) {
          System.out.println("Du har tastet et et tall som det ikke finnes valg for. Prøv på nytt!");
          svar = scanTall.nextInt();
        }
         
            
      
      if(svar==0){
        Meny();

      }
      else if(svar==1){
        System.out.println("Pasienter:");
        skrivUtPasienter();
        System.out.println();
      }
      

      else if(svar==2){
         System.out.println("Leger:");
         skrivUtLeger(false);
         System.out.println();
      }
      

      else if(svar==3){
      System.out.println("Legemidler:");
      skrivUtLegemidler();
      System.out.println();
      }
      
      
      else if ( svar==4){
        System.out.println("Resepter:");
        skrivUtResepter();
        System.out.println();
      }
      }
        private static void skrivUtResepter() {
        System.out.printf("id", "legemiddel-id", "legenavn", "pasient-id", "type", "reit");
        System.out.println();
        String avstand = "%-10.10s %-15.15s %-25.25s %-10.10s %-10.0s %-10.10s";
        for (Resept res : resepter) {
           String type;
           if (res instanceof PResept) {
             type = "p";
            } else if (res instanceof BlaaResept) {
              type = "blaa";
            } else if (res instanceof Militaerresept) {
              type = "militaer";
            } else if (res instanceof HvitResept) {
              type = "hvit";
            } else {
              type = "finnes ikke";
            }
          
          System.out.printf(avstand, res.hentId(), res.hentLegemiddel().hentId(), res.hentLege().hentNavn(), res.hentPasient().hentId(), type, res.hentReit());
          System.out.println();
        }
      
    }
      

        
     
    
//metode som skriver ut pasienter
  private static void skrivUtPasienter() {
    String avstand1 = "%-15.15s %-25.25s %-25.25s";
    System.out.printf(avstand1, "id", "navn", "foedselsnummer");
    System.out.println();
    String avstand2 = "%-15.15s %-25.25s %-25.25s";
    for (Pasient pasient : pasienter) {
      System.out.printf(avstand2, pasient.hentId(), pasient.hentNavn(), pasient.hentFoedselsnummer());
      System.out.println();
    }
  }
// metode som skriver ut legemidler
  private static void skrivUtLegemidler() {
    String avstand = "%-10.10s %-20.20s %-10.10s %-10.10s %-10.10s";
    System.out.printf(avstand, "id", "navn", "pris", "virkestoff", "type", "styrke");
    System.out.println();
    for (Legemiddel lm : legemidler) {
      if (lm instanceof Vanlig) {
        System.out.printf(avstand, lm.hentId(), lm.hentNavn(), lm.hentPris(), lm.hentVirkestoff(), "vanlig", "");
        System.out.println();
      } else if (lm instanceof Narkotisk){
        Narkotisk nark = (Narkotisk)lm;
        System.out.printf(avstand, lm.hentId(), lm.hentNavn(), lm.hentPris(), lm.hentVirkestoff(), "narkotisk", nark.hentNarkotiskStyrke());
        System.out.println();
      } else if (lm instanceof Vanedannende) {
        Vanedannende vane = (Vanedannende) lm;
        System.out.printf(avstand, lm.hentId(), lm.hentNavn(), lm.hentPris(), lm.hentVirkestoff(), "vanedannende", vane.hentVanedannendeStyrke());
        System.out.println();;
      }
    }
  }
// metode som skriver ut leger
  private static void skrivUtLeger(boolean Spesialister) {
    String avstand = "%-25.25s %-25.25s %-25.25s";
    System.out.printf(avstand, "navn", "type", "kontroll-id");
    System.out.println();
    for (Lege lege : leger) {
      if (lege instanceof Spesialist) {
        Spesialist spesialist = (Spesialist) lege;
        String avstand1 = "%-25.25s %-25.25s %-25.25s";
        System.out.printf(avstand1, lege.hentNavn(), "spesialist", spesialist.hentKontrollID());
        System.out.println();
       
        
      } else if (!Spesialister){
        String avstand2 = "%-25.25s %-25.25s %-25.25s";
        System.out.printf(avstand2, lege.hentNavn(), "vanlig", "0");
        System.out.println();
        
       
      }
    }
  }
  //metode som leser fra oppgit fil
  public static void lesFraFil(String filnavn) throws FileNotFoundException, UlovligUtskrift {
    File fil = new File(filnavn);
    try (Scanner scan = new Scanner(fil)) {
        String info = "ingen";
        while (scan.hasNextLine()) {
            String linje = scan.nextLine();
            String[] bit = linje.trim().split(" ");
            if (bit[0].startsWith("#")) {
                if (bit[1].equals("Leger")) {
                    info = "Leger";
                } else if (bit[1].equals("Legemidler")) {
                    info = "Legemidler";
                } else if (bit[1].equals("Pasienter")) {
                    info = "Pasienter";
                } else if (bit[1].equals("Resepter")) {
                    info = "Resepter";
                }
            } else {
                String[] linje1 = linje.trim().split(",");
                if (info.equals("Pasienter")) {
                  Pasient nypasient=new Pasient(linje1[0], linje1[1]);
                    pasienter.leggTil(nypasient);
                }

                else if (info.equals("Legemidler")) {
                    if (linje1[1].equals("narkotisk")) {
                      Legemiddel nynarko=new Narkotisk(linje1[0], Integer.parseInt(linje1[2]),
                      Double.parseDouble(linje1[3]), Integer.parseInt(linje1[4]));
                        legemidler.leggTil(nynarko);
                    } else if (linje1[1].equals("vanedannende")) {
                      Legemiddel nyvane=new Vanedannende(linje1[0], Integer.parseInt(linje1[2]),
                      Double.parseDouble(linje1[3]), Integer.parseInt(linje1[4]));
                        legemidler.leggTil(nyvane);
                    } else if (linje1[1].equals("vanlig")){
                      Legemiddel nyvanlig=new Vanlig(linje1[0], Integer.parseInt(linje1[2]), Double.parseDouble(linje1[3]));
                        legemidler.leggTil(nyvanlig);
                    }
                }

                else if (info.equals("Leger")) {
                    if (linje1[1].equals("0")) {
                      Lege nylege=new Lege(linje1[0]);
                        leger.leggTil(nylege);
                    } else if (!linje1[1].equals("0")) {
                      Spesialist nyspesialist=new Spesialist(linje1[0], (linje1[1]));
                        leger.leggTil(nyspesialist);
                    }
                }

                else {
                    Lege lege = null;
                    for (Lege l : leger) {
                        if (l.hentNavn().equals(linje1[1])) {
                            lege = l;
                        }
                    }
                    Pasient pasient = null;
                    for (Pasient pas : pasienter) {
                        if (pas.hentId() == Integer.parseInt(linje1[2])) {
                            pasient = pas;
                        }
                    }
                    Legemiddel legemiddel = null;
                    for (Legemiddel legemid : legemidler) {
                        if (legemid.hentId() == Integer.parseInt(linje1[0])) {
                            legemiddel = legemid;
                        }
                    }

                    if (linje1[3].equals("hvit")) {
                        resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, Integer.parseInt(linje1[4])));
                    } else if (linje1[3].equals("blaa")) {
                        resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, Integer.parseInt(linje1[4])));
                    } else if (linje1[3].equals("p")) {
                        resepter.leggTil(lege.skrivPResept(legemiddel, pasient, Integer.parseInt(linje1[4])));
                    } else if (linje1[3].equals("militaer")) {
                        resepter.leggTil(lege.skrivMilResept(legemiddel, pasient));
                    }
                }
            }
        }
    } catch (NumberFormatException e) {
        System.out.println("Fant ikke filen " + filnavn);
    }
}
 
  
// metode for å finne fram til riktig pasient
  private static Pasient hentPasient(int inpid) {
    for (Pasient pas : pasienter) {
      if (pas.hentId() == inpid) {
        return pas;
      }
    }
    return null;
  }

}




 




 