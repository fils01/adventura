/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import GUI.PanelVeciVProstoru;





/*******************************************************************************
 * Instance třídy {@code PrikazZahod} představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahoď";
    private HerniPlan plan;
    private Inventar inventar;
    /***************************************************************************
     *
     */
    public PrikazZahod(HerniPlan plan, Inventar inventar)
    {
        this.plan = plan;
        this.inventar = inventar;
        
    }


    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "\nCo chceš zahodit?\n";
        }
        
        String nazevZahozeneVeci = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = inventar.vyhodVec(nazevZahozeneVeci);
        //použit podobný princip jako u příkazu seber
        if(vec == null){
            return "\nTaková věc v inventáři není\n";
        } else {
            
            aktualniProstor.vlozVec(vec);
            return "\nZahodil jsi " + nazevZahozeneVeci + "\n";
        }
    }
    
    @Override
    public String getNazev(){
        return NAZEV;
    }
}
