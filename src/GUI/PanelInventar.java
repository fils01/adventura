/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import logika.Vec;
import utils.Observer;

/**
 *
 * @author sf
 */

public class PanelInventar implements Observer{
    
    private HerniPlan plan;
    ListView<Object> list;
    ObservableList<Object> data;
    private IHra hra;
    private Label InventarLabel;

    
    private TextArea centralText;

    /*
    * Konstruktor pro panel kapsy.
    */
    public PanelInventar(HerniPlan plan,TextArea text, IHra hra) {
       this.plan = plan;
       plan.registerObserver(this);
       centralText = text;
       this.hra = hra;
        init();
    }

    /*
    * Metoda vytvoří list pro věci v kapse.
    */
    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(200);
        
        InventarLabel = new Label("Inventář:");
        InventarLabel.setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        InventarLabel.setPrefWidth(200);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Vec> seznam;
                    seznam = hra.getInventar().getSeznamVeci();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) 
                    {
                       if(pomocna == index)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }
                    
                    String vstupniPrikaz = "zahoď "+nazev;
                    String odpovedHry = hra.zpracujPrikaz("zahoď "+nazev);

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    plan.notifyObservers();
            }
        });
        
        
        
        update();
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return list;
    }
    
    /*
    * Metoda aktualizuje list věcí v kapse. Zobrazuje obrázky věcí, které má hráč u sebe.
    */
    @Override 
    public void update() 
    {        
        Map<String, Vec> seznam;
        seznam = hra.getInventar().getSeznamVeci();
        data.clear();
        for (String x : seznam.keySet()) 
        {
        Vec pomocna = seznam.get(x);
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 100, 100, false, false));
        data.add(obrazek);
        }
    }
    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }

    public Label getInventarLabel() {
        return InventarLabel;
    }

}
