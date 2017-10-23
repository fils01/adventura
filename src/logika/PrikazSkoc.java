/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazSkoc} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
class PrikazSkoc implements IPrikaz{
    private static final String NAZEV = "skoč";
    private HerniPlan plan;
    private Inventar inventar;

    /***************************************************************************
     *
     */
    public PrikazSkoc(HerniPlan plan, Inventar inventar)
    {
        this.plan = plan;
        this.inventar = inventar;
    }
    
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            if(plan.getAktualniProstor().getNazev().equals("první_zkouška")){
                //skákat do jiné místnosti je možné jen u místnosti s trny
                Prostor prostorPoSkoku = plan.getAktualniProstor().vratSousedniProstor("druhá_zkouška");
                plan.setAktualniProstor(prostorPoSkoku);
                return plan.getAktualniProstor().dlouhyPopis();
            } else {
                return "Skočil jsi.\n";
            }
        }
        if(parametry.length >= 1){
            return "\nTento příkaz nepotřebuje další parametry";
        } else {
            return "";
        }
    }
    
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
