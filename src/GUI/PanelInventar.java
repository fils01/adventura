/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Inventar;
import logika.Vec;
import main.Main;
import utils.ObserverInventar;

/**
 *
 * @author sf
 */
public class PanelInventar extends HBox implements ObserverInventar{
    private IHra hra;
    private Inventar inventar;
    private Map<String, Vec> mapaVeciVInventari;
    private Label inventarLabel;
    private Button tlacitkoVeciVInventari;
    
    
    public PanelInventar(IHra hra) {
        this.hra = hra;
        hra.getInventar().registerObserver(this);
        init();
    }
    
    private void init() {
        inventarLabel = new Label("Inventář:");
        getInventarLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getInventarLabel().setPrefWidth(200);
        
        this.getChildren().clear();
        
        try {
            mapaVeciVInventari = hra.getInventar().getSeznamVeci();
            for (String vec : mapaVeciVInventari.keySet()) {
                Vec pomocna = mapaVeciVInventari.get(vec);
                tlacitkoVeciVInventari = new Button(pomocna.getNazev(), new ImageView(new Image(
                        Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 30, 30, false, false)));
                
                this.getChildren().add(getTlacitkoVeciVInventari());
            }
        } catch(Exception dalsi) {
        
        }
    }
    
    
    
    @Override
    public void update() {
        try {
            mapaVeciVInventari = hra.getInventar().getSeznamVeci();
            for (String vec : mapaVeciVInventari.keySet()) {
                Vec pomocna = mapaVeciVInventari.get(vec);
                tlacitkoVeciVInventari = new Button(pomocna.getNazev(), new ImageView(new Image(
                        Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 30, 30, false, false)));
                
                this.getChildren().add(getTlacitkoVeciVInventari());
                update();
            }
        } catch(Exception dalsi) {
        
        }
    }
    
    public void newGame(IHra novaHra) {
        hra.getInventar().removeObserver(this);
        
        hra = novaHra;
        hra.getInventar().registerObserver(this);
        update();
    }

    /**
     * @return the inventarLabel
     */
    public Label getInventarLabel() {
        return inventarLabel;
    }

    /**
     * @return the tlacitkoVeciVInventari
     */
    public Button getTlacitkoVeciVInventari() {
        return tlacitkoVeciVInventari;
    }
}
