import java.util.Iterator;
// oppretter klassen lenkeliste som implementerer interfacet liste
public class Lenkeliste<T> implements Liste<T> {
    //oppretter en indre klasse kalt node, medinstansvariabler som skal brukes til å navigere seg rundt i liste og lagre data
        class Node {
          Node neste = null; 
          Node forrige = null; 
          T data;
          Node (T x) {
            data = x;
          }
          //oppretter string metode som lagrer data konvertert til string
          public String toString() {
            return data.toString();
          }
        }
        class LenkelisteIterator implements Iterator<T> {
            private Node peker = start;
            
            public boolean hasNext() {
              if (peker == null) {
                return false;
              } else {
                return true;
              }
            }
            public T next() {
              T data = peker.data;
              peker = peker.neste;
              return data;
            }
          }
    // oppretter  beskyttede instansvariabeler som skal hjelpe med å danne utgansgpunkt til listen
        protected Node start = null;
        protected Node slutt = null;
        protected int stoerrelse = 0;
    
        //metode som returnerer stoerrelse til lista altså antall noder i lista
        public int stoerrelse() {
          int stoerrelse = 0;
          Node peker= start;
          while(peker!=null){
            stoerrelse++;
            peker=peker.neste;
          }
    
          return stoerrelse;
        }
        
    
        
        
        //metode som legger til node på sluten av lista, hvis listen er tom så plasseres en node som den første noden
        public void leggTil(T x) {
          Node ny = new Node(x);
          if ( start != null) {
            ny.forrige = slutt;
            slutt.neste = ny;
            
          } else {
            start = ny;
          }
          slutt = ny;
          stoerrelse ++;
        }
    
       
        
        //finner fram til første element i listen og lagrer dette 
        public T hent() {
          Node peker= start;
          for(int i = 0; i <0; i++) {
            peker=peker.neste;
          }
            return peker.data;
          }
          
    
    
    
        
        //finner fram til første element i lista  og fjerner dette 
        public T fjern() throws UgyldigListeindeks{
          if (stoerrelse == 0) {
            throw new UgyldigListeindeks(0);
          }
          
          T lagretdata = start.data;
          if (stoerrelse>1){
            start.neste.forrige = null;
            start = start.neste;
          }
          else{
            start= null;
            slutt= null;
          
          }
          stoerrelse --;
          return lagretdata;
        }
        //metode som brukes for å finne og returnere en gitt posisjon
        public Node finnPosisjon(int pos) {
          if (pos < 0){
            return null;
          }
          if (pos > stoerrelse - 1 ){
            return null;
          } 
          if (pos == 0) {
            return start;
          }
          if (pos == stoerrelse) {
            return slutt;
          } 
          else{
            Node peker = start;
            for (int i = 0; i < pos; i ++) {
              peker = peker.neste;
            }
            return peker;
          }
         
           
          }
          public Iterator iterator() {
            return new LenkelisteIterator();
          }
    
       //string metode som kovereter alt til string, for å printe ut informasjon enkelt
       
       public String toString() {
        String string = "";
        string += "dette er en lenkeliste med lengde:"+ stoerrelse;
        string += "\nvidere informajon om liste er følgende:";
        Node peker = start;
        for(int i = 0; i < stoerrelse; i++) {
          string += "\nposisjon: " + String.valueOf(i) + ", datainnhold: ";
          if (i > 0) {
            peker = peker.neste;
          }
          string += peker.toString();
        }
        return string;
      }
    
    }
    
