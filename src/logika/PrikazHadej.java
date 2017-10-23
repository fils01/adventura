/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazHadej} představují ...
 *
 * @author    Svetoslav Filev
 * @version   0.00.000
 */
public class PrikazHadej implements IPrikaz
{
    private static final String NAZEV = "hádej";
    private HerniPlan plan;
    private Inventar inventar;
    /***************************************************************************
     * konstruktor
     */
    public PrikazHadej(HerniPlan plan, Inventar inventar){
        this.plan = plan;
        this.inventar = inventar;
    }
    
    @Override
    public String provedPrikaz(String... parametry){
        if(parametry.length == 0){
            return "Co hádáš?";            
        }
        
        String vstupniCislo = parametry[0];
        //hádat lze jen v místnosti druhé zkoušky
        if(plan.getAktualniProstor().getNazev().equals("druhá_zkouška")){
            if(vstupniCislo.equals("125")){
                Prostor prostorPoSkoku = plan.getAktualniProstor().vratSousedniProstor("třetí_zkouška");
                plan.setAktualniProstor(prostorPoSkoku);
                System.out.println("Správně!!!");
                return plan.getAktualniProstor().dlouhyPopis();
            } else {
                return "\nŠpatné číslo.\n";
            }
        } else {
            return  "Není čas na hlouposti, máš úkol!";
        }
    }
    
    @Override
    public String getNazev(){
        return NAZEV;
    }
    
}
