/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.layout.VBox;
import logika.HerniPlan;
import logika.IHra;
import logika.Inventar;
import utils.Observer;

/**
 *
 * @author sf
 */
public class PanelInventar extends VBox implements Observer{
    private IHra hra;
    private HerniPlan plan;
    private Inventar inventar;  
    
    public PanelInventar(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    private void init() {
        
    }
    
    
    
    @Override
    public void update() {
        
    }
}
