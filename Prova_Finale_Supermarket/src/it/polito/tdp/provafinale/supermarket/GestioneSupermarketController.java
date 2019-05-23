package it.polito.tdp.provafinale.supermarket;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.provafinale.supermarket.model.Comune;
import it.polito.tdp.provafinale.supermarket.model.ComuneCandidato;
import it.polito.tdp.provafinale.supermarket.model.Model;
import it.polito.tdp.provafinale.supermarket.model.Supermarket;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GestioneSupermarketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> ChoiceBoxRegione;

    @FXML
    private ChoiceBox<String> ChoiceBoxCatena;

    @FXML
    private RadioButton radioRicerca;

    @FXML
    private TextField txtCercaSuper;

    @FXML
    private TextField txtNumber;

    @FXML
    private RadioButton radio0;

    @FXML
    private RadioButton radio25;

    @FXML
    private RadioButton radio50;

    @FXML
    private RadioButton radio100;

    @FXML
    private RadioButton radioEscludi;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnEspandi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    private Label txtMessage;
    
    @FXML
    private RadioButton radioOBC;

    @FXML
    private RadioButton radioOBN;
    
    @FXML
    private TextField txtRicercaComune;

    @FXML
    private Label txtTime;

	private Model model;
	
	private static final double ZERO = 0.0;
	private static final double VENTICINQUE = 0.25;
	private static final double CINQUANTA = 0.5;
	private static final double CENTO = 1.0;
	

	  @FXML
	void selectOBC(ActionEvent event) {

		  if(!this.radioOBC.isSelected()) 
			  this.radioOBC.setSelected(true);
		  if(this.radioOBN.isSelected())
			  this.radioOBN.setSelected(false);
	    }

	    @FXML
	void selectOBN(ActionEvent event) {
	    	
			  if(this.radioOBC.isSelected()) 
				  this.radioOBC.setSelected(false);
			  if(!this.radioOBN.isSelected()) 
				  this.radioOBN.setSelected(true);

	    }
	
	private void stampaSupermercatoSuSchermo(List<Supermarket> result ) {
	
		if(result.isEmpty()) 
			this.txtResult.setText("Spiacenti, nessun risultato");
		else {
			
		if(this.radioOBN.isSelected())
            Collections.sort(result);			
		  for(Supermarket s : result)
			  this.txtResult.appendText(s+"\n");
		}
	}
	
    @FXML
    void doCerca(ActionEvent event) {

    	long start = System.currentTimeMillis();
    	this.txtTime.setText("");
    	this.txtMessage.setText("");
    	this.txtResult.clear();
    	
    	String regione = this.ChoiceBoxRegione.getValue();
    	
    	//se cerco un comune specifico
    	
    	String inputComune = this.txtRicercaComune.getText();
    	
    	if(inputComune!=null && !inputComune.trim().equals("")) {
    		
    		if(inputComune.matches("[a-zA-Z\\s]+"))
    		{
    			
    			List<Supermarket> result = model.getRicercaInComune(inputComune);
    			
    			if(result.isEmpty()) 
    				this.txtResult.setText("Spiacenti, nessun risultato");
    			else {
    			Comune c = result.get(0).getCitta();
    			
    			this.txtResult.appendText("  -Supermercati presenti a(d): "+c.getNomeComune()
    					+" (abitanti : "+c.getAbitanti()+")\n");
    			
    			this.stampaSupermercatoSuSchermo(result);
    			}
    		}
    			
    		else
    			this.txtMessage.setText("Errore! Inserire solo caratteri alfanumerici NON accentati");
    	}
    	
    	//se si ricerca manualmente
    	
    	else if(this.radioRicerca.isSelected()) {
 
    		String input =  this.txtCercaSuper.getText();
    		
    		if(input.matches("[0-9a-zA-Z]+")) {
    		
    			String[] daCercare = {"%"+input, "%"+input+"%", input+"%"};
    			
    			//se non si sceglie la regione
    			
    		if(regione==null || regione.equals("")) {
    			
    			List<Supermarket> result = model.getListaSupermercatiRicercati(daCercare);
    			this.stampaSupermercatoSuSchermo(result);
    			
    		}
    		 
    		   //se si sceglie la regione
    		   
    		else {
    			
    			List<Supermarket> result = model.getListaSupermercatiRicercati(regione,daCercare);
    			this.stampaSupermercatoSuSchermo(result);
    		}
    		
    	}
    		else
    		 this.txtMessage.setText("Errore! Inserire solo caratteri alfanumerici NON acccentati");
    		
    }
    	// se si sceglie attraverso il menu a tendina
    	
    	else {
    		
    		String catena = this.ChoiceBoxCatena.getValue();
    	    
    		//se si sceglie la regione
    		
    		if(regione!=null && !regione.equals("")) {
    			
    			//se si sceglie la catena
    			
    			if(catena!=null && !catena.equals("")) {
    				
    				List<Supermarket> result = model.getListaSupermercati(regione, catena);
    				this.stampaSupermercatoSuSchermo(result);
    				
    			} 
    			  // se non si sceglie la catena
    			
    			else {
    				
	                List<Supermarket> result = model.getListaSupermercatiIn(regione);
	                this.stampaSupermercatoSuSchermo(result);
    				
    			}
    			
    			
    		}
    		
    		// se non si sceglie la regione
    		
    		else {
    			
    			 //se si sceglie la catena
    			
                   if(catena!=null && !catena.equals("")) {
    				
    				List<Supermarket> result = model.getListaSupermercati(catena);
    				this.stampaSupermercatoSuSchermo(result);
    				
    			} 
                 
                //se non si sceglie nulla
                   
                   else 
                	   this.txtMessage.setText("Errore, inserire una catena di supermercati");   
                   
                   
    			
    		}
    		
    	}

    	this.txtTime.setText("Run time: "+(System.currentTimeMillis()-start)+" mills");
    	
    }

    private void stampaComuneSuSchermo(List<ComuneCandidato> result) {

		if(result.isEmpty()) 
			this.txtResult.setText("Spiacenti, nessun risultato");
		else {
	    
			Collections.sort(result);
		
			this.txtResult.appendText("La lista di comuni che ottimizza l'apertura di nuovi punti vendita (regione : "+result.get(0).getComune().getRegione()+"): \n\n");
			
		  for(ComuneCandidato cc : result)
			  this.txtResult.appendText(cc.getComune()+"\n");
		}	
    }
    
    @FXML
    void doEspandi(ActionEvent event) {


    	long start = System.currentTimeMillis();
    	this.txtTime.setText("");
    	this.txtMessage.setText("");
    	this.txtResult.clear();
    	
    	String regione = this.ChoiceBoxRegione.getValue();
    	String catena = this.ChoiceBoxCatena.getValue();
    	
    	String input = this.txtNumber.getText();
    	
    	double perc;
    	
    	if(this.radio25.isSelected())
    		perc=GestioneSupermarketController.VENTICINQUE;
    	else if(this.radio50.isSelected())
    		perc=GestioneSupermarketController.CINQUANTA;
    	else if(this.radio100.isSelected())
    		perc=GestioneSupermarketController.CENTO;
    	else
    		perc=GestioneSupermarketController.ZERO;
    	
    	
    	
    	if(input.matches("[0-9]+")) {
    		
    		int numero = Integer.parseInt(input);
    	
    	if(numero>0) {	
    	
    	if(catena!=null && !catena.equals("")) {
    		
    		//se non scelgo la regione
    	   if(regione==null || regione.equals("")) {
    		   
    		   // se escludo dove gia presente
    		   if(this.radioEscludi.isSelected()) {
    			List<ComuneCandidato> result =  model.espandiZeroPresenti(numero, catena, perc); 
    			this.stampaComuneSuSchermo(result);
    		   }
    		   // se considero tutti
    		   else {
    			List<ComuneCandidato> result =  model.espandi(numero, catena, perc); 
       			this.stampaComuneSuSchermo(result);
    		   }
    		   
    	   }
    	     //se scelgo la regione
    	   else {
    		   

    		   // se escludo dove gia presente
    		   if(this.radioEscludi.isSelected()) {
    			   List<ComuneCandidato> result =  model.espandiZeroPresenti(numero, catena, regione, perc);
       		    	this.stampaComuneSuSchermo(result);
    		   }
    		   // se considero tutti
    		   else {
    			   List<ComuneCandidato> result =  model.espandi(numero, catena, regione, perc);
          			this.stampaComuneSuSchermo(result);
    		   }
    		   
    	    }
    		
    	  }
    	
    	else 
    	 this.txtMessage.setText("Errore! Inserire una catena");
    	   
    	}
    	
    	else
    	 this.txtMessage.setText("Errore! Inserire un numero intero positivo");
    	
    }
    	else
       	 this.txtMessage.setText("Errore! Inserire il numero di punti vendita");
       	
    	
    	this.txtTime.setText("Run time: "+(System.currentTimeMillis()-start)+" mills");
    }

    @FXML
    void doRadio0(ActionEvent event) {

    	//this
    	if(!this.radio0.isSelected())
    		this.radio0.setSelected(true);
    	
    	if(this.radio25.isSelected())
    		this.radio25.setSelected(false);
    	if(this.radio50.isSelected())
    		this.radio50.setSelected(false);
    	if(this.radio100.isSelected())
    		this.radio100.setSelected(false);
    	if(this.radioEscludi.isSelected())
    		this.radioEscludi.setSelected(false);
    	
    }

    @FXML
    void doRadio100(ActionEvent event) {

    	if(this.radio0.isSelected())
    		this.radio0.setSelected(false);
    	if(this.radio25.isSelected())
    		this.radio25.setSelected(false);
    	if(this.radio50.isSelected())
    		this.radio50.setSelected(false);
    	//this
    	if(!this.radio100.isSelected())
    		this.radio100.setSelected(true);

if(this.radioEscludi.isSelected())
    		this.radioEscludi.setSelected(false);
    }

    @FXML
    void doRadio25(ActionEvent event) {
    	
    	if(this.radio0.isSelected())
    		this.radio0.setSelected(false);
    	//this
    	if(!this.radio25.isSelected())
    		this.radio25.setSelected(true);
    
    	if(this.radio50.isSelected())
    		this.radio50.setSelected(false);
    	if(this.radio100.isSelected())
    		this.radio100.setSelected(false);
    	if(this.radioEscludi.isSelected())
    		this.radioEscludi.setSelected(false);

    }

    @FXML
    void doRadio50(ActionEvent event) {

    	if(this.radio0.isSelected())
    		this.radio0.setSelected(false);
    	if(this.radio25.isSelected())
    		this.radio25.setSelected(false);
    	//this
    	if(!this.radio50.isSelected())
    		this.radio50.setSelected(true);
    
    	if(this.radio100.isSelected())
    		this.radio100.setSelected(false);
    	if(this.radioEscludi.isSelected())
    		this.radioEscludi.setSelected(false);
    	
    }

    @FXML
    void doRadioEscludi(ActionEvent event) {

    	if(this.radio0.isSelected())
    		this.radio0.setSelected(false);
    	if(this.radio25.isSelected())
    		this.radio25.setSelected(false);
    	if(this.radio50.isSelected())
    		this.radio50.setSelected(false);
    	if(this.radio100.isSelected())
    		this.radio100.setSelected(false);
    	//this
    	if(!this.radioEscludi.isSelected())
    		this.radioEscludi.setSelected(true);
    	
    }
    
    @FXML
    void doReset(ActionEvent event) {

    	if(this.radioRicerca.isSelected()) {
    		
    		this.radioRicerca.setSelected(false);
    		
    		this.txtCercaSuper.setDisable(true);
    		this.btnEspandi.setDisable(false);
    		this.txtNumber.setDisable(false);
    		this.radio0.setDisable(false);
    		this.radio25.setDisable(false);
    		this.radio50.setDisable(false);
    		this.radio100.setDisable(false);
    		this.radioEscludi.setDisable(false);
    		
    	}
    	
    	this.selectOBC(event);
    	this.doRadio0(event);
    	
    	this.txtNumber.clear();
    	this.txtCercaSuper.clear();
    	this.txtRicercaComune.clear();
    	
    	this.txtMessage.setText("");
    	this.txtTime.setText("");
    	
    	this.txtResult.clear();
    	
    	

    }

    @FXML
    void sbloccaRicerca(ActionEvent event) {
    	
    	if(this.radioRicerca.isSelected()) {
    		this.txtCercaSuper.setDisable(false);
    		this.btnEspandi.setDisable(true);
    		this.txtNumber.setDisable(true);
    		this.radio0.setDisable(true);
    		this.radio25.setDisable(true);
    		this.radio50.setDisable(true);
    		this.radio100.setDisable(true);
    		this.radioEscludi.setDisable(true);
    	}
    	else {
    		this.txtCercaSuper.setDisable(true);
    		this.btnEspandi.setDisable(false);
    		this.txtNumber.setDisable(false);
    		this.radio0.setDisable(false);
    		this.radio25.setDisable(false);
    		this.radio50.setDisable(false);
    		this.radio100.setDisable(false);
    		this.radioEscludi.setDisable(false);
    	}

    }

    @FXML
    void initialize() {
    	
        assert txtRicercaComune != null : "fx:id=\"txtRicercaComune\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
       	assert radioOBC != null : "fx:id=\"radioOBC\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radioOBN != null : "fx:id=\"radioOBN\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
    	assert ChoiceBoxRegione != null : "fx:id=\"ChoiceBoxRegione\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert ChoiceBoxCatena != null : "fx:id=\"ChoiceBoxCatena\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radioRicerca != null : "fx:id=\"radioRicerca\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtCercaSuper != null : "fx:id=\"txtCercaSuper\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtNumber != null : "fx:id=\"txtNumber\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radio0 != null : "fx:id=\"radio0\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radio25 != null : "fx:id=\"radio25\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radio50 != null : "fx:id=\"radio50\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radio100 != null : "fx:id=\"radio100\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert radioEscludi != null : "fx:id=\"radioEscludi\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert btnEspandi != null : "fx:id=\"btnEspandi\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'GestioneSupermarket.fxml'.";

    }

	public void setModel(Model model) {

		this.model=model;
		
	}

	public void caricaBox() {
	
		List<String> regioni = model.getRegioni();
		regioni.add("");
		List<String> catene = model.getListaCatene();
		catene.add("");
		
		this.ChoiceBoxRegione.setItems(FXCollections.observableList(regioni));
		this.ChoiceBoxCatena.setItems(FXCollections.observableList(catene));
	}
}
