/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String popisVeci;
    private String adresaObrazkuVeci;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost, String popisVeci, String adresaObrazkuVeci)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.popisVeci = popisVeci;
        this.adresaObrazkuVeci = adresaObrazkuVeci;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
        return nazev;
    }
    
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    public String getPopisVeci(){
        return popisVeci;
    }
    /**
     * @return the adresaObrazkuVeci
     */
    public String getAdresaObrazkuVeci() {
        return adresaObrazkuVeci;
    }

}
