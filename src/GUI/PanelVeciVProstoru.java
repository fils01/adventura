/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author sf
 */
public class PanelVeciVProstoru extends HBox implements Observer {

    private IHra hra;
    private Map<String, Vec> mapaVeciVProstoru;
    private Button tlacitkoVeci;
    private Label vecLabel;
    private TextArea centralText;
    /**
     * Konstruktor panelu věcí v prostoru
     * @param hra
     * @param text 
     */
    public PanelVeciVProstoru(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    private void init() {;
        vecLabel = new Label("Věci v prostoru:");
        getVecLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getVecLabel().setPrefWidth(200);
        mapaVeciVProstoru = hra.getHerniPlan().getAktualniProstor().getVeciVProstoru();
        /**
         * pro každou věc v prostoru se pomocí forEach vygeneruje obrázek
         * pomocná proměnná "pomocna" pro konkrétní objekt v iteraci
         */
        this.getChildren().clear();

        for (String vec : mapaVeciVProstoru.keySet()) {
            Vec pomocna = mapaVeciVProstoru.get(vec);
            tlacitkoVeci = new Button(pomocna.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 30, 30, false, false)));

            this.getChildren().add(getTlacitkoVeci());

            tlacitkoVeci.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    String vstupniPrikaz = "seber " + tlacitkoVeci.getText();
                    String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");

                    hra.getHerniPlan().notifyObservers();
                }
            });
            update();
        }
    }

    public void newGame(IHra novaHra) {
        hra.getHerniPlan().removeObserver(this);

        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    /**
     * Aktualizuje věci v místnosti Buttony s obrázkem a popiskem
     */
    @Override
    public void update() {
        this.getChildren().clear();
        mapaVeciVProstoru = hra.getHerniPlan().getAktualniProstor().getVeciVProstoru();

        for (String vec : mapaVeciVProstoru.keySet()) {
            /**
             * try-catch pro zablokování milovaného NullPointerException
             */
            try {
                Vec pomocna = mapaVeciVProstoru.get(vec);
                tlacitkoVeci = new Button(pomocna.getNazev(), new ImageView(new Image(
                        Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 30, 30, false, false)));

                this.getChildren().add(getTlacitkoVeci());
                tlacitkoVeci.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        /**
                        * Pozor na záměnu pomocna.getNazev() a tlacitkoVeci.getText()!!
                        * EventHandler pak sebere špatný item
                        */
                        String vstupniPrikaz = "seber " + pomocna.getNazev();
                        String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                        centralText.appendText("\n" + vstupniPrikaz + "\n");
                        centralText.appendText("\n" + odpovedHry + "\n");

                        hra.getHerniPlan().notifyObservers();

                    }
                });
            } catch (Exception exception) {
            }
        }
    }

    /**
     * @return the vecLabel
     */
    public Label getVecLabel() {
        return vecLabel;
    }

    /**
     * @return the tlacitkoVeci
     */
    public Button getTlacitkoVeci() {
        return tlacitkoVeci;
    }

}
