/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
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
    
    @Override
    public void start(Stage primaryStage) {
        hra = new Hra();
        BorderPane borderPane = new BorderPane();
        
        
        centralText = new TextArea();
        centralText.setFont(Font.font("Avenir Next", FontWeight.BOLD, 14));
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        Label zadejPrikazLabel = new Label("Zadej příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        
        zadejPrikazTextField = new TextField("");
        zadejPrikazTextField.setMinWidth(670);
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String vstupniPrikaz = zadejPrikazTextField.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                if (hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });
        
        FlowPane obrazekFlowPane = new FlowPane();
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),230,230,false,true));
        obrazekFlowPane.setMaxWidth(230);
        obrazekFlowPane.setAlignment(Pos.CENTER);
        obrazekFlowPane.getChildren().add(obrazekImageView);
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.BOTTOM_RIGHT);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextField);
        
        borderPane.setLeft(obrazekFlowPane);
        borderPane.setBottom(dolniLista);
        Scene scene = new Scene(borderPane, 900, 450);
        
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
    
}
