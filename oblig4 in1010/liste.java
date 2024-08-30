//oppgitt interface med oppgitte metoder 
interface Liste <T>  extends Iterable<T> { 
    int stoerrelse ();
    void leggTil (T x);
    T hent ();
    T fjern ();
    }
    