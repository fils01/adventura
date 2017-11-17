/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazProzkoumej} představují ...
 *
 * @author    Svetoslav Filev
 * @version   0.00.000
 */
class PrikazProzkoumej implements IPrikaz{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;
    private Inventar inventar;
    
    /***************************************************************************
     *
     */
    public PrikazProzkoumej(HerniPlan plan, Inventar inventar)
    {
        this.plan = plan;
        this.inventar = inventar;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return plan.getAktualniProstor().popisVeci();
        }
        
        String nazevZkoumaneVeci = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        if(nazevZkoumaneVeci.equals("inventář")){ //když je parametr inventář tak je vypsán
            return inventar.nazvyVeci() + "\n";
        }
        
        if(aktualniProstor.jeVecVProstoru(nazevZkoumaneVeci)){//když je věc v prostoru tak je vrácen popis
            return aktualniProstor.vyberVec(nazevZkoumaneVeci).getPopisVeci() + "\n";
        } else {
            return "Taková věc tu není." + "\n";
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
