/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import main.Main;
import utils.Observer;

/**
 *
 * @author sf
 */
public class PanelVychod extends ListView implements Observer{
    
    private IHra hra;
    private HerniPlan plan;
    private Main main;
    private ListView<String> seznamVychodu;
    ObservableList<String> mistnosti;
    
    
    public PanelVychod(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    private void init() {
        Collection<Prostor> sousedniMistnosti = hra.getHerniPlan()
                .getAktualniProstor().getVychody();
        seznamVychodu = new ListView<>();
        mistnosti = FXCollections.observableArrayList();
        getSeznamVychodu().setItems(mistnosti);
        getSeznamVychodu().setPrefWidth(200);
        getSeznamVychodu().setMaxHeight(200);
        
        
        for (Prostor prostor : sousedniMistnosti) {
            mistnosti.add(prostor.getNazev());
            
        }
        
        //ListCell vybere jméno prostoru a přesune se do něj
        update();
    }
    
    
    
    public void newGame(IHra novaHra) {
        hra.getHerniPlan().removeObserver(this);
        
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    @Override
    public void update() {
        Collection<Prostor> sousedniMistnosti = hra.getHerniPlan()
                .getAktualniProstor().getVychody();
        mistnosti.clear();
        
        sousedniMistnosti.forEach((prostor) -> {
            mistnosti.add(prostor.getNazev());
        });
        
    }
    
    
    
    /**
     * @return the seznamVychodu
     */
    public ListView<String> getSeznamVychodu() {
        return seznamVychodu;
    }

}
