/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.PanelMapa;
import GUI.MenuLista;
import GUI.PanelVychod;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;

/**
 *
 * @author fils01
 */
public class Main extends Application {
    
    private TextArea centralText;
    private IHra hra;
    private HerniPlan plan;
    private TextField zadejPrikazTextField;
    
    private PanelVychod panelVychod;
    private PanelMapa panelMapa;
    private MenuLista menuLista;
    
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
        setHra(new Hra());
        
        panelVychod = new PanelVychod(hra);
        panelMapa = new PanelMapa(hra);
        menuLista = new MenuLista(hra, this);
        BorderPane borderPane = new BorderPane();
        
        
        centralText = new TextArea();
        getCentralText().setFont(Font.font("Avenir Next", FontWeight.BOLD, 14));
        getCentralText().setText(hra.vratUvitani());
        getCentralText().setEditable(false);
        borderPane.setCenter(getCentralText());
        
        Label zadejPrikazLabel = new Label("Zadej příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        
        zadejPrikazTextField = new TextField("");
        zadejPrikazTextField.setMinWidth(670);
        zadejPrikazTextField.requestFocus();
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextField.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                appendCentralText(vstupniPrikaz);
                
                zadejPrikazTextField.clear();

                if (hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                    appendCentralText(hra.vratEpilog());
                }
            }
        });
        //tohle začalo fungovat ve 2:39 ráno, děkuji pěkně
        //no a co že main má milion řádků
        getPanelVychod().getSeznamVychodu().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String jmenoVychodu = getPanelVychod().getSeznamVychodu().
                        getSelectionModel().getSelectedItem();
                String vytvorenyPrikaz = "jdi " + jmenoVychodu;
                String odpovedNaVytvorenyPrikaz = hra.zpracujPrikaz(vytvorenyPrikaz);
                //prostě to vypisování v centraltextu nefunguje a nevím ani v 2:01 ráno proč
                //PanelVychod.this.main.centralVstupniPrikaz(vytvorenyPrikaz);
                //PanelVychod.this.main.centralOdpovedHry(odpovedNaVytvorenyPrikaz);
                appendCentralText(odpovedNaVytvorenyPrikaz);
            }
        });
        
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextField);
        
        FlowPane pravaLista = new FlowPane();
        pravaLista.setAlignment(Pos.TOP_CENTER);
        pravaLista.setPrefWidth(200);
        pravaLista.getChildren().addAll(getPanelVychod().getSeznamVychodu());
        
        FlowPane levaLista = new FlowPane();
        levaLista.setAlignment(Pos.TOP_CENTER);
        levaLista.setPrefWidth(200);
        levaLista.getChildren().addAll(getPanelMapa());
        
        
        borderPane.setLeft(levaLista);
        borderPane.setRight(pravaLista);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        Scene scene = new Scene(borderPane, 1000, 520);
        
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void appendCentralText(String vstupniPrikaz) {
        this.getCentralText().appendText("\n" + vstupniPrikaz + "\n");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * @return the centralText
     */
    public TextArea getCentralText() {
        return centralText;
    }

    /**
     * @param hra the hra to set
     */
    public void setHra(IHra hra) {
        this.hra = hra;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the panelMapa
     */
    public PanelMapa getPanelMapa() {
        return panelMapa;
    }

    /**
     * @return the panelVychod
     */
    public PanelVychod getPanelVychod() {
        return panelVychod;
    }
    
}
