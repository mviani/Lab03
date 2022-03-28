package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;
import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLingua;

    @FXML
    private TextArea txtCorrezioni;

    @FXML
    private TextArea txtDaControllare;

    @FXML
    private Label txtErrori;

    @FXML
    private Label txtPrestazioni;

    @FXML
    void doClearText(ActionEvent event) {
        txtCorrezioni.clear();
        txtDaControllare.clear();
        txtErrori.setText("");
        txtPrestazioni.setText("");   
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	int cnt=0;
    	txtCorrezioni.clear();
    	if(cmbLingua.getValue()==null) {
    		txtErrori.setText("Selezionare una lingua");
    		return;
    	}
    	if(cmbLingua.getValue().equals("Italiano")) {
    		model.loadDictionary("src/main/resources/Italian.txt");
    	}
    	if(cmbLingua.getValue().equals("English")) {
    		model.loadDictionary("src/main/resources/English.txt");
    	}
    	double startTime = System.nanoTime();
    	for(RichWord r:model.spellCheckTextDichotomic(model.formatta(txtDaControllare.getText()))) {
    		if(r.isCorretta()==false) {
    			txtCorrezioni.setText(txtCorrezioni.getText()+r.getParola()+"\n");
    			cnt++;
    		}
    	}
    	double estimatedTime = (System.nanoTime() - startTime)/1000000000;
    	txtPrestazioni.setText("Spell check completato in: "+estimatedTime+" secondi");
    	txtErrori.setText("Il testo contiene "+cnt+" errori");
    	
    	
    	
    }
    
    public void setModel(Dictionary model) {
    	this.model=model;
    }

    @FXML
    void initialize() {
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorrezioni != null : "fx:id=\"txtCorrezioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaControllare != null : "fx:id=\"txtDaControllare\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPrestazioni != null : "fx:id=\"txtPrestazioni\" was not injected: check your FXML file 'Scene.fxml'.";
        cmbLingua.getItems().clear();
        cmbLingua.getItems().add("English");
        cmbLingua.getItems().add("Italiano");
    }

}
