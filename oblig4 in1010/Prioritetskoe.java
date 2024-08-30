//oppretter klasse prioritetskoe som implemeterer klassen comparable og arver klassen lenkeliste
public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    //metode som legger til i en sortert rekkefølge fra minst til størst
    public void leggTil(T x) {
      Node ny= new Node(x);
        
        if (stoerrelse() == 0) {
            super.leggTil(x);
            return;
        }
        if (ny.data.compareTo(start.data)<= 0){
          ny.neste= start;
          start.forrige=ny;
          start=ny;
        }
        else if (ny.data.compareTo(slutt.data)>= 0){
          ny.forrige= slutt;
          slutt.neste=ny;
          slutt=ny;
        }
        else{
          Node pos = null;
          Node peker = start;
          for (int i = 0; i < stoerrelse; i++) {
            if (peker.data.compareTo(ny.data)>= 0) {
              pos = peker;
          }
          peker=peker.neste;
        }
         ny.forrige=pos.forrige;
         ny.neste=pos;
         pos.forrige.neste=ny;
         pos.forrige= ny;
          }
          stoerrelse+=1;
        }
        
    
    //metode som finner det minste elementet, som skal være det første, og lagrer det
    public T hent() {
      return super.hent();
    }

    
    // metode som finner det minste elementet, som skal være det første og fjerner det
    public T fjern() {
        return super.fjern();
    }

   

}


