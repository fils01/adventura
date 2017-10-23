package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, 
 *  která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private Inventar inventar;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        Inventar inventar = new Inventar();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazSkoc(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazBojuj(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazHadej(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazPouzijLano(herniPlan, inventar));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "\n\n\n" +
               "Jsi v horké Persii. Sultán podniká velké válečné tažení,\n" + 
               "jeho nepřítomnosti však využije zlý Vezír\n" + 
               "a unese a zamkne jeho dceru ve svém paláci. \n" + 
               "Sultán tě pověřuje úkolem zabít Vezíra a najít princeznu.\n" + 
               "Dokážeš to?\n\n" + 
               "Napiš 'nápověda', pro vypsání příkazů.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Konec hry.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry) + "\n********************************************"
            + "***********************";
        }
        else {
            textKVypsani="\nNevím co tím myslíš? Tento příkaz neznám. \n";
        }
        
        if(herniPlan.jeVyhra()){
            konecHry = true;
            textKVypsani = "\nÚspěšně jsi zachránil princeznu a vzal zpátky kontrolu nad palácem!\n"
            + "Sultán neudělal chybu když ti svěřil tento úkol!\n";
        }
        
        if(herniPlan.jeProhra()){
            konecHry = true;
            textKVypsani = "\nZemřel jsi a nezvládl jsi splnit úkol. Vezír převzal Sultánův palác a \n"
            + "nikdo teď už princeznu nezachrání.\n";
        }
        
        return textKVypsani;
    }
    
    public Inventar getInventar(){
        return inventar;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

