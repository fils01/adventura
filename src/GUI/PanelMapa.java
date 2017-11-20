/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author sf
 */
public class PanelMapa extends AnchorPane implements Observer{
    
    private IHra hra;
    private Label mapaLabel;
    
    ImageView princ = new ImageView(new Image(
                Main.class.getResourceAsStream("/zdroje/princ.png"),
                20,40,true,true));
    
    public PanelMapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    private void init() {
        ImageView obrazekImageView = new ImageView(new Image(
                Main.class.getResourceAsStream("/zdroje/mapa.png"),
                200,450,true,true));
        
        mapaLabel = new Label("Mapa:");
        getMapaLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getMapaLabel().setPrefWidth(200);
        //tecka = new Circle(8, Paint.valueOf("red"));
        
        //this.setTopAnchor(tecka, 0.0);
        //this.setLeftAnchor(tecka, 0.0);

        
        //obrazekFlowPane.setMaxWidth(230);
        //obrazekFlowPane.setAlignment(Pos.CENTER);
        this.getChildren().addAll(obrazekImageView, princ);
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
        this.setTopAnchor(princ, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(princ, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

    /**
     * @return the mapaLabel
     */
    public Label getMapaLabel() {
        return mapaLabel;
    }
    
}
