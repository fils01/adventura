/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
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
import logika.Hra;
import logika.IHra;

/**
 *
 * @author fils01
 */
public class Main extends Application {
    
    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextField;
    
    private Mapa mapa;
    private MenuLista menuLista;
    
    private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
        setHra(new Hra());
        mapa = new Mapa(hra);
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
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextField.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                getCentralText().appendText("\n" + vstupniPrikaz + "\n");
                getCentralText().appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextField.clear();

                if (hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                    getCentralText().appendText(hra.vratEpilog());
                }
            }
        });
        
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.BOTTOM_RIGHT);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextField);
        
        borderPane.setLeft(getMapa());
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);
        Scene scene = new Scene(borderPane, 900, 500);
        
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
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
    
}
