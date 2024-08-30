//oppretter klassen stabel som arver fra lenkeliste
class Stabel<T> extends Lenkeliste<T> {

    //metode som redefineres, slik at nye elementer blir lagt til først i lista og ikke sist
    @Override
    public void leggTil(T x) {
      Node ny = new Node(x);
      if ( start != null) {
        ny.neste = start;
        start.forrige = ny;
      } else {
        start = ny;
        slutt =ny;
        
      }
      start = ny;
      stoerrelse ++;
    }
  }
  
