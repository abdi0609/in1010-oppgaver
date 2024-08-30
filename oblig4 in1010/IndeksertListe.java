//oppretter klassen indeksert liste som arver fra lenkeliste 
class IndeksertListe <T> extends Lenkeliste<T> {


    
      
  //metode som legger til node i angitt posisjon og forsyver resten av lista
     public void leggTil(int pos, T x) throws UgyldigListeindeks {
      Node ny = new Node(x);
      Node peker = finnPosisjon(pos); 
      if (pos > stoerrelse) { 
        throw new UgyldigListeindeks(pos);
      }
      if (pos<0){
        throw new UgyldigListeindeks(pos);

      } 
      if (pos == stoerrelse) { 
        leggTil(x);
        return;
      
      } 
      if(pos>0) {
        ny.neste = peker;
        ny.forrige = peker.forrige;
        peker.forrige.neste = ny;
        peker.forrige = ny;
      }
      else  { 
        ny.neste = start;
        start.forrige = ny;
        start = ny;
      }
      stoerrelse ++;
    }
      
      
      //metode som bytter ut elementet på angitt posisjonen med angitt elementet gitt som argument
      public void sett(int pos, T x) throws UgyldigListeindeks{
        Node ny = new Node(x);
        Node peker = finnPosisjon(pos);
        if (pos > stoerrelse - 1){
          throw new UgyldigListeindeks(pos);
        }
        if (pos<0){
          throw new UgyldigListeindeks(pos);

        }
        if (peker.neste == null) { 
          ny.forrige = slutt.forrige;
          slutt.forrige.neste = ny;
          slutt = ny;
        } 
        if (peker.forrige == null) { 
          ny.neste = start.neste;
          start.neste.forrige = ny;
          start = ny;
        } 
        if (pos>0) {
          ny.neste = peker.neste;
          ny.forrige = peker.forrige;
          peker.forrige.neste = ny;
          peker.neste.forrige = ny;
        }
        else  { 
          start = ny;
          slutt = ny;
        }
    }
    
      //metode som fjerner element på angitt posisjon
      public T fjern(int pos) throws UgyldigListeindeks{
        Node peker = finnPosisjon(pos); 
        if (pos > (stoerrelse - 1)){ 
          throw new UgyldigListeindeks(pos);
        }
        if (pos<0){
          throw new UgyldigListeindeks(pos);
        }
        if (stoerrelse==0){
          throw new UgyldigListeindeks(pos);

        } 
        if (pos>1){
          peker.neste.forrige = peker.forrige;
          peker.forrige.neste = peker.neste;
        }
        else if (peker.neste == null) { 
          slutt.forrige.neste = null;
          slutt = slutt.forrige;
        } 
        
        else if (peker.forrige == null) { 
          start.neste.forrige = null;
          start = start.neste;
        } 
        else{   
          start = null;
          slutt = null;
        }
        
        stoerrelse --;
        return peker.data;
      }
      
      //metode som henter og lagrer data på angitt posisjon
      public T hent(int pos) throws UgyldigListeindeks {
        if (pos<0){
          throw new UgyldigListeindeks(pos);
        }
        if (pos>stoerrelse){
          throw new UgyldigListeindeks(pos);
        }
        else{
          Node peker = finnPosisjon(pos); 
          return peker.data;
      }
    }
  }
      
      
        
    
    
    
    