/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/*******************************************************************************
 * Instance třídy {@code PrikazBojuj} představují ...
 *
 * @author    Svetoslav Filev
 * @version   0.00.000
 */
public class PrikazBojuj implements IPrikaz
{
    private static final String NAZEV = "bojuj";
    private HerniPlan plan;
    private Inventar inventar;
    /***************************************************************************
     * konstruktor
     */
    public PrikazBojuj(HerniPlan plan, Inventar inventar){
        this.plan = plan;
        this.inventar = inventar;
    }
    
    @Override
    public String provedPrikaz(String... parametry){
        if(parametry.length == 0){//provedeme příkaz který má jeden parametr
            return "\nS kým chceš bojovat?\n";            
        }
        
        String jmenoProtivnika = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();
        //určení kdy lze příkaz použít a jak dopadají boje podle obsahu inventáře, 
        if(aktualniProstor.jePostavaVProstoru(jmenoProtivnika)){
            if(aktualniProstor.jePostavaNepratelska(jmenoProtivnika)){
                Postava odebiranaPostava = aktualniProstor.vyberPostavu(jmenoProtivnika);
                System.out.println(odebiranaPostava.getProslov());
                if(aktualniProstor.getNazev().equals("třetí_zkouška") && inventar.obsahujeVec("malý_meč")){
                    return "\nZabil jsi pěšáka díky šikovné ráně mečem pod žebra.\n";
                }
                if(aktualniProstor.getNazev().equals("třetí_zkouška") && !inventar.obsahujeVec("malý_meč")){
                    aktualniProstor.vlozPostavu(odebiranaPostava);
                    System.out.println("\nBez meče tě pěšák snadno porazil.\n");
                    plan.setProhra(true);
                }
                if(aktualniProstor.getNazev().equals("věž") && inventar.obsahujeVec("velký_meč")){
                    Vec klic = new Vec("klíč", true, "Klíč do cely princezny");
                    aktualniProstor.vlozVec(klic);
                    return "\nZabil jsi Vezíra! Po jeho útoku dýkami jsi kontroval přesnou ranou do srdce."
                    + "\nZ Vezíra také vypadl klíč, který odemyká celu princezny.\n";
                }
                if(aktualniProstor.getNazev().equals("věž") && !inventar.obsahujeVec("velký_meč")){
                    aktualniProstor.vlozPostavu(odebiranaPostava);
                    System.out.println("\nVezír zlomil tvůj malý meč a porazil tě.\n");
                    plan.setProhra(true);
                }
            } else {
                return "\nNemůžeš útočit na přátelské postavy!!!\n";
            }
        } else {
            return "\nTaková postava tu není.\n";
        }
        return "";
    }
    
    @Override
    public String getNazev(){
        return NAZEV;
    }
}
