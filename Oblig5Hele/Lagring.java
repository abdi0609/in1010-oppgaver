
//klasse som brukes som lagringspunkt for to hashmaps.
public class Lagring<T> {
    private  T lagringsplass1;
    private T lagringsplass2;

    public Lagring(T lagringsplass1PAR, T lagringsplass2PAR) {
        lagringsplass1 = lagringsplass1PAR;
        lagringsplass2 = lagringsplass2PAR;
    }

    
    public T hentLagring1() {
         return lagringsplass1; 
        }

    
    public T hentLagring2() { 
        return lagringsplass2; 
    }    
}
