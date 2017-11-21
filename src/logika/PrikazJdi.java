package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Inventar inventar;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Inventar inventar) {
        this.plan = plan;
        this.inventar = inventar;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "\nKam mám jít? Musíš zadat jméno východu\n";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        //kdy nemůžeme použít příkaz a skončí hra, nelze použít return protože setProhra je return
        Prostor aktualniProstor = plan.getAktualniProstor();
        if(aktualniProstor.getNazev().equals("první_zkouška") && smer.equals("druhá_zkouška")){
            System.out.println("\nPropíchly tě hroty. \n");
            plan.setProhra(true);
        }
        if(aktualniProstor.getNazev().equals("druhá_zkouška") && smer.equals("třetí_zkouška")){
            return "\nDveře jsou zamčeny.\n";
        }
        if(aktualniProstor.getNazev().equals("třetí_zkouška") && smer.equals("most") 
        && aktualniProstor.jePostavaVProstoru("Pěšák")){
            System.out.println("\nZkusil ses protáhnout kolem pěšáka, ale ten si tě všiml a zabil tě. \n");
            plan.setProhra(true);
        }
        if(aktualniProstor.getNazev().equals("třetí_zkouška") && smer.equals("tajná_skrýš")
        && aktualniProstor.jePostavaVProstoru("Pěšák")){
            System.out.println("\nZkusil ses protáhnout kolem pěšáka, ale ten si tě všiml a zabil tě. \n");
            plan.setProhra(true);
        }
        if(aktualniProstor.getNazev().equals("most") && smer.equals("věž")){
            System.out.println("\nSpadl jsi do propasti. \n");
            plan.setProhra(true);
        }
        if(aktualniProstor.getNazev().equals("věž") && smer.equals("komnata_pricezny")
        && aktualniProstor.jePostavaVProstoru("Vezír")){
            System.out.println( "\nZabil tě Vezír. \n");
            plan.setProhra(true);
        }
        if(aktualniProstor.getNazev().equals("věž") && smer.equals("komnata_princezny") 
        && !inventar.obsahujeVec("klíč")){
            return "Dveře jsou zamčeny";
        }
        if(aktualniProstor.getNazev().equals("věž") && smer.equals("komnata_princezny")
        && inventar.obsahujeVec("klíč")){
            plan.setVyhra(true);
        }
        if (sousedniProstor == null) {
            return "\nTam se odsud jít nedá!\n";
        }
        else {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
