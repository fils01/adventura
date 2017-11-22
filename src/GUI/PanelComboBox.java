/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;

/**
 *
 * @author sf
 */
public class PanelComboBox extends ComboBox {
    
    private IHra hra;
    private TextField zadejPrikazTextField;
    private Label comboBoxLabel;
    private String textSPrikazy; //neumím pojmenovat proměnné 
    private String[] oddelenePrikazy;
    
    public PanelComboBox(IHra hra, TextField text) {
        zadejPrikazTextField = text;
        this.hra = hra;
        init();
    }
    
    private void init() {
        this.setPrefWidth(200);
        comboBoxLabel = new Label("Příkazy: ");
        getComboBoxLabel().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getComboBoxLabel().setPrefWidth(200);
        
        /**
         * potřeba trochu modifikací ve třídě SeznamPrikazu, např převést na public,
         * což je unchecked or unsafe operation
         * navíc metoda vratNazvyPrikazu vrací String příkazů, který je potřeba oddělit
         * například regexem, v definici metody je vidět že je dělí jen mezerou
         */
        textSPrikazy = hra.getPlatnePrikazy().vratNazvyPrikazu();
        /**
         * zmíněný split regexem
         */
        oddelenePrikazy = textSPrikazy.split(" ");
        for (String prikaz : oddelenePrikazy) {
            this.getItems().addAll(prikaz);
        }
    }
    

    /**
     * @return the comboBoxLabel
     */
    public Label getComboBoxLabel() {
        return comboBoxLabel;
    }
}
