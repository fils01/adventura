/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import java.util.*;

/*******************************************************************************
 * Instance třídy {@code Inventar} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Inventar{
    
   private Map<String, Vec> seznamVeci;
   private static final int VELIKOST = 2;
   
   private List<Observer> listObserveruInventare = new ArrayList<Observer>();
   
    /***************************************************************************
     *
     */
    public Inventar()
    {
        seznamVeci = new HashMap<String, Vec>();
    }
    
    public Vec getVec(String nazevVeci){
        return getSeznamVeci().get(nazevVeci);
    }
    
    public boolean jeMistoVInventari(){
        if(getSeznamVeci().size() < VELIKOST){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean vlozVec (Vec vec){
        if(jeMistoVInventari() && vec.jePrenositelna()){
            getSeznamVeci().put(vec.getNazev(), vec);
            return true;
        } else {
            return false;
        }
    }
    
    public Vec vyhodVec(String nazev){
        Vec vyhozenaVec = null;
        if(getSeznamVeci().containsKey(nazev)){
            vyhozenaVec = getSeznamVeci().get(nazev);
            getSeznamVeci().remove(nazev);
            
        }
        return vyhozenaVec;
    }
    
    public boolean obsahujeVec(String nazev){
        if(getSeznamVeci().containsKey(nazev)){
            return true;
        } else {
            return false;
        }
    }
    
    public String nazvyVeci(){
        String nazvy = "\nV inventáři se nachází ";
        if(getSeznamVeci().size() == 0){
            nazvy = "\nInventář je prázdný.";
        } else {
            for(String nazevVeci : getSeznamVeci().keySet()){
                nazvy += nazevVeci + ", ";
            }
        }
        return nazvy;
    }

    /**
     * @return the seznamVeci
     */
    public Map<String, Vec> getSeznamVeci() {
        return seznamVeci;
    }
}
