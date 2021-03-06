package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan implements Subject{
    
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private boolean vyhra = false;
    private boolean prohra = false;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor uvodniMistnost = new Prostor("úvodní_místnost","malá chodba na začátku paláce", 90, 418);
        Prostor hlavniSal = new Prostor("hlavní_sál","velký sál, který ti nabízí čtyři možné cesty dál", 90, 380);
        Prostor vezeniPrincezny = new Prostor("vězení_princezny","y", 30, 30);
        Prostor zbrojnice = new Prostor("zbrojnice","vyklizená zbrojnice, většinu věcí si vzal"
        + " Sultán na své válečné tažení", 90, 335);
        Prostor mucirna = new Prostor("mučírna","deprimující mučírna, kde byli pro informace" 
        + " i potěšení trápeni mnozí lidé", 150, 335);
        Prostor rozbiteSchodiste = new Prostor("rozbité_schodiště","staré rozpalé schodiště, které kdysi"
        + " vedlo nahoru do místnosti ohňů", 30, 335);
        Prostor chodbaTriZkousek = new Prostor("chodba_tří_zkoušek", "táhlá chodba, rozdělená na tři"
        + " části, které mají různé barvy stěn", 150, 310);
        Prostor prvniZkouska = new Prostor("první_zkouška","zhruba třímetrová část, "
        + "\nkde ze země před tebou vyskakují ostré "
        + "\nmetrové hroty. To jen tak neprojdeš.", 150, 290);
        Prostor druhaZkouska = new Prostor("druhá_zkouška","další třímetrová část, bohužel v cestě dál "
        + "\nti brání zamčené dveře, na kterých je zámek s číslicemi 1-9, "
        + "\nu každé číslice je malá páčka, kterou se zřejmě volí vstupní číslo", 150, 245);
        Prostor tretiZkouska = new Prostor("třetí_zkouška","bráněna pěšákem, který vypadá velmi nepřátelsky", 150, 185);
        Prostor tajnaSkrys = new Prostor("tajná_skrýš","tajná místnost, která byla používána jako úkryt", 110, 185);
        Prostor most = new Prostor("most", "rozpadlý most přes propast. To jen tak nepřeskočíš", 140, 150);
        Prostor vez = new Prostor("věž", "první poschodí věže, kde je uvězněná princezna, \n bohužel ti "
        + "v cestě dál brání zlý Vezír!", 75, 75);
        Prostor komnataPrincezny = new Prostor("komnata_princezny", "cela na vrcholu věže, kde je zamčená" 
        + " princezna", 75, 20);
        
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        uvodniMistnost.setVychod(hlavniSal);
        hlavniSal.setVychod(uvodniMistnost);
        hlavniSal.setVychod(zbrojnice);
        hlavniSal.setVychod(mucirna);
        hlavniSal.setVychod(rozbiteSchodiste);
        zbrojnice.setVychod(rozbiteSchodiste);
        zbrojnice.setVychod(hlavniSal);
        rozbiteSchodiste.setVychod(hlavniSal);
        rozbiteSchodiste.setVychod(zbrojnice);
        mucirna.setVychod(hlavniSal);
        mucirna.setVychod(chodbaTriZkousek);
        chodbaTriZkousek.setVychod(mucirna);
        chodbaTriZkousek.setVychod(prvniZkouska);
        prvniZkouska.setVychod(chodbaTriZkousek);
        prvniZkouska.setVychod(druhaZkouska);
        druhaZkouska.setVychod(prvniZkouska);
        druhaZkouska.setVychod(tretiZkouska);
        tretiZkouska.setVychod(druhaZkouska);
        tretiZkouska.setVychod(tajnaSkrys);
        tretiZkouska.setVychod(most);
        tajnaSkrys.setVychod(tretiZkouska);
        most.setVychod(tretiZkouska);
        most.setVychod(vez);
        vez.setVychod(most);
        vez.setVychod(komnataPrincezny);
        
        //konstrukce věcí
        Vec lano = new Vec("lano", true, "Lehké a bytelné lano", "/zdroje/lano.png");
        Vec malyMec = new Vec("malý_meč", true, "Krátká perská šavle, kterou můžeš použít pro boj", "/zdroje/malyMec.png");
        Vec koberec = new Vec("koberec", false, "Krásný koberec na kterém jsou vyšité horské scenérie", "/zdroje/koberec.png");
        Vec velkyMec = new Vec("velký_meč", true, "Krásný ostrý meč, na kterém je citát o věrnosti Sultánovi", "/zdroje/velkyMec.png");
        Vec pergamen = new Vec("pergamen", true, "Malý útržek pergamenu, na kterém je rozmazané číslo 125", "/zdroje/pergamen.png");
        Vec hak = new Vec("hák", false, "Hák, který vypadá jako kdyby se na něj dalo zavěsit lano s očkem", "/zdroje/hak.png");
        
        //vložení věcí do prostorů
        zbrojnice.vlozVec(lano);
        zbrojnice.vlozVec(malyMec);
        hlavniSal.vlozVec(koberec);
        tajnaSkrys.vlozVec(velkyMec);
        druhaZkouska.vlozVec(pergamen);
        most.vlozVec(hak);
        
        //konstrukce postav
        Postava pesak = new Postava("Pěšák", "\n\n\"Tady cesta končí Princi!! Ukaž co v tobě je!!\"\n", true);
        Postava vezir = new Postava("Vezír", "\n\n\"Jsi na špatné straně, ale za chvíli\n"
        + " si to uvědomíš sám. Připrav se na smrt\"\n", true);
        Postava princezna = new Postava("Princezna", "\n\n\"Myslela jsem že už nikdo nedorazí a hrozně\n"
        + " jsem se bála! Děkuji ti moc Princi!\"\n", false);
        
        //vložení postav do prostorů
        tretiZkouska.vlozPostavu(pesak);
        vez.vlozPostavu(vezir);
        komnataPrincezny.vlozPostavu(princezna);
        
        
                
        aktualniProstor = uvodniMistnost;  // hra začíná v domečku  
        viteznyProstor = komnataPrincezny;
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }
    
    public boolean jeVyhra(){
        return vyhra;
    }
    
    public void setVyhra(boolean stav){
        this.vyhra = stav;
    }
    
    public boolean jeProhra(){
        return prohra;
    }
    
    public void setProhra(boolean stav){
        this.prohra = stav;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
