/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import logika.HerniPlan;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 *
 * @author sf
 */
public class PanelVychod extends ListView implements Observer{
    
    private ListView<String> seznamVychodu;
    ObservableList<String> mistnosti;
    private IHra hra;
    private HerniPlan plan;
    
    public PanelVychod(HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        init();
    }
    
    private void init() {
        seznamVychodu = new ListView<>();
        mistnosti = FXCollections.observableArrayList();
        seznamVychodu.setItems(mistnosti);
        seznamVychodu.setPrefWidth(200);
        
        String vychody = plan.getAktualniProstor().popisVychodu();
        
        String[] oddeleneVychody = vychody.split(" ");
        
        for (int i = 0; i < oddeleneVychody.length; i++) {
            mistnosti.add(oddeleneVychody[i]);
        }
        
    }
    
    
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
    
    @Override
    public void update() {
        String vychody = plan.getAktualniProstor().popisVychodu();
        mistnosti.clear();
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 0; i < oddeleneVychody.length; i++) {
            mistnosti.add(oddeleneVychody[i]);
        }
    }

}
