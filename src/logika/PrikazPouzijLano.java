/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazPouzijLano} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazPouzijLano implements IPrikaz
{
    private static final String NAZEV = "použij_lano";
    private HerniPlan plan;
    private Inventar inventar;
    /***************************************************************************
     *
     */
    public PrikazPouzijLano(HerniPlan plan, Inventar inventar){
        this.plan = plan;
        this.inventar = inventar;
    }
    
    @Override
    public String provedPrikaz(String... parametry){
        if(inventar.obsahujeVec("lano")){//příkaz lze použít jen když je v inventáři lano
            if(parametry.length == 0){//bez parametru
                if(plan.getAktualniProstor().getNazev().equals("most")){//jen na mostě
                    Prostor prostorPoSkoku = plan.getAktualniProstor().vratSousedniProstor("věž");
                    plan.setAktualniProstor(prostorPoSkoku);
                    return "\nŠikovně jsi hodil lano na hák na druhé straně, pověsil se a"
                    +"\nvyšlphal nahoru!"
                    + plan.getAktualniProstor().dlouhyPopis();
                } else {
                    return "Není důvod použít lano.";
                }
            } else {
                return "\nTento příkaz nepotřebuje další parametry.\n";
            }
        } else {
            return "\nNemáš u sebe lano!\n";
        }
    }
    
    
    @Override
    public String getNazev(){
        return NAZEV;
    }
}
