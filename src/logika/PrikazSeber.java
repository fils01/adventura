/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Inventar inventar;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan, Inventar inventar)
    {
        this.plan = plan;
        this.inventar = inventar;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {            
            return "Co chceš sebrat?" + "\n";
        }
        
        System.out.println(parametry[0]);
        
        String nazevSbiraneVeci = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();
        //sebereme z prostoru věc, pokud je místo v inventáři tak ji vložíme, jinak vrátíme zpět do prostoru
        if (aktualniProstor.jeVecVProstoru(nazevSbiraneVeci)) 
        {
            Vec odebiranaVec = aktualniProstor.vyberVec(nazevSbiraneVeci);
            if(!odebiranaVec.jePrenositelna())
            {
                aktualniProstor.vlozVec(odebiranaVec);
                return "Tohle nemůžeš sebrat" + "\n";
            }
            if(odebiranaVec.jePrenositelna() && inventar.vlozVec(odebiranaVec)){
                return "Sebral jsi " + odebiranaVec.getNazev() + ", jež se teď nachází v inventáři" + "\n";
            }
            else{
                aktualniProstor.vlozVec(odebiranaVec);
                return "Tvůj inventář je plný" + "\n";
            }
        }
        else {
            return "Taková věc tu není." + "\n";
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
