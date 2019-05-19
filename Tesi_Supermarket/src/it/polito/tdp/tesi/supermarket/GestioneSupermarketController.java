package it.polito.tdp.tesi.supermarket;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GestioneSupermarketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> ChoiceBoxRegione;

    @FXML
    private ChoiceBox<?> ChoiceBoxCatena;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtCercaSuper;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnEspandi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

	private Model model;

    @FXML
    void doCerca(ActionEvent event) {

    }

    @FXML
    void doEspandi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ChoiceBoxRegione != null : "fx:id=\"ChoiceBoxRegione\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert ChoiceBoxCatena != null : "fx:id=\"ChoiceBoxCatena\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtNumber != null : "fx:id=\"txtNumber\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtCercaSuper != null : "fx:id=\"txtCercaSuper\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert btnEspandi != null : "fx:id=\"btnEspandi\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		
	}
}
