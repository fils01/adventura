/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code Postava} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Postava
{
    private String jmeno;
    private String proslov;
    private boolean nepratelska;
    /**
     * Konstruktor postavy
     * @param jmeno -
     * @param proslov - 
     * @param nepratelska  - 
     */
    public Postava(String jmeno, String proslov, boolean nepratelska){
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.nepratelska = nepratelska;
    }
    
    public String getJmeno(){
        return jmeno;
    }
    
    public String getProslov(){
        return proslov;
    }
    
    public boolean jeNepratelska(){
        return nepratelska;
    }
}
