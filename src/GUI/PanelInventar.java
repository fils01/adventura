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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 *
 * @author sf
 */
public class PanelInventar extends HBox implements Observer {

    private HerniPlan plan;
    ObservableList<Object> data;
    private IHra hra;
    private Label InventarLabel;
    private Button tlacitkoInventare;

    private TextArea centralText;

    /**
     * Konstruktor panelu inventáře
     * @param plan
     * @param text - důležité kvůli vracení odpovědí hry
     * @param hra 
     */
    public PanelInventar(HerniPlan plan, TextArea text, IHra hra) {
        this.plan = plan;
        plan.registerObserver(this);
        centralText = text;
        this.hra = hra;
        init();
    }
    
    private void init() {
        data = FXCollections.observableArrayList();

        setInventarLabel(new Label("Inventář:"));
        getInventarLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getInventarLabel().setPrefWidth(200);

        Map<String, Vec> seznam = hra.getInventar().getSeznamVeci();
        /**
         * forEach vygeneruje Buttony pro každý objekt 
         */
        for (String vec : seznam.keySet()) {
            Vec pomocna = seznam.get(vec);
            tlacitkoInventare = new Button(pomocna.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 30, 30, false, false)));

            this.getChildren().add(getTlacitkoInventare());
            /**
             * EventHandler kliknutí, podobný postup jako u panelu věcí v prostoru
             */
            tlacitkoInventare.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    /**
                     * Pozor na záměnu pomocna.getNazev() a tlacitkoInventare.getText()!!
                     * EventHandler pak zahodí špatný item
                     */
                    String vstupniPrikaz = "zahoď " + pomocna.getNazev();
                    String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");

                    plan.notifyObservers();
                }
            });

            update();
        }
    }

    /*
    * Aktualizuje inventář pomocí Buttonů s obrázky a popiskem
     */
    @Override
    public void update() {
        this.getChildren().clear();
        Map<String, Vec> seznam;
        seznam = hra.getInventar().getSeznamVeci();
        data.clear();
        for (String vec : seznam.keySet()) {
            Vec pomocna = seznam.get(vec);
            tlacitkoInventare = new Button(pomocna.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocna.getAdresaObrazkuVeci()), 30, 30, false, false)));

            this.getChildren().addAll(getTlacitkoInventare());

            tlacitkoInventare.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String vstupniPrikaz = "zahoď " + pomocna.getNazev();
                    String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");

                    plan.notifyObservers();
                }
            });
        }
    }

    /**
     * @return the InventarLabel
     */
    public Label getInventarLabel() {
        return InventarLabel;
    }

    /**
     * @param InventarLabel the InventarLabel to set
     */
    public void setInventarLabel(Label InventarLabel) {
        this.InventarLabel = InventarLabel;
    }

    /**
     * @return the tlacitkoInventare
     */
    public Button getTlacitkoInventare() {
        return tlacitkoInventare;
    }
    /**
     * Při nové hře přeregistruje pozorovatele
     * @param plan
     * viz východy
     */
    public void newGame(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
    }
}
