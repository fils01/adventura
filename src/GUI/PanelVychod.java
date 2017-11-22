/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private Label vychodLabel;
    
    /**
     * Konstruktor Panelu Východů
     * @param hra 
     */
    public PanelVychod(IHra hra) {
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
        getSeznamVychodu().setMaxHeight(120);
        vychodLabel = new Label("Východy:");
        getVychodLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getVychodLabel().setPrefWidth(200);
        /**
         * forEach vygeneruje východy 
         */
        for (Prostor prostor : sousedniMistnosti) {
            mistnosti.add(prostor.getNazev());
            
        }
        update();
    }
    
    /**
     * opět přeregistrování observerů v případě nové hry pro aktualizaci východů
     * stejné u inventáře a věcí v prostoru
     * @param novaHra 
     */
    
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

    /**
     * @return the vychodLabel
     */
    public Label getVychodLabel() {
        return vychodLabel;
    }

}
