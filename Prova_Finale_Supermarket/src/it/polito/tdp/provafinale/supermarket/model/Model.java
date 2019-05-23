package it.polito.tdp.provafinale.supermarket.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.provafinale.supermarket.db.ComuniDAO;
import it.polito.tdp.provafinale.supermarket.db.SupermarketDAO;

public class Model {
	
	private ComuniDAO daoC;
	private SupermarketDAO daoS;
	
	private List<Comune> comuni;
	private Map<String, Comune> mappaComuni;
	
	public Model() {
		
		this.daoC = new ComuniDAO();
		this.mappaComuni = new HashMap<>();
		this.comuni = daoC.getTuttiComuni(mappaComuni);
		
		this.daoS = new SupermarketDAO(mappaComuni);
		
	}
	
	//Seguono dei metodi di ricerca
	
	/**
	 * Comunicando con la classe DAO restituisce un elenco di regioni
	 * @return List<String> contenente le regioni italiane
	 */
	public List<String> getRegioni() {
		List <String> result = new LinkedList<>(daoC.getRegioni());
		return result;
	}

	public List<String> getListaCatene(){
		List <String> result = new LinkedList<>(daoS.getListaCatene());
		
		return result;
		
		
	}

	public List<Supermarket> getListaSupermercati(String catena) {
	
		return this.daoS.getSupermercatiCatena(catena);
	}

	public List<Supermarket> getListaSupermercati(String regione, String catena) {
        
		return this.daoS.getSupermercatiCatena(regione, catena);
	}

	public List<Supermarket> getListaSupermercatiIn(String regione) {
		
		return this.daoS.getSupermercatiRegione(regione);
	}

	public List<Supermarket> getListaSupermercatiRicercati(String[] daCercare) {
		
		return this.daoS.getDaCercare(daCercare);
	}

	public List<Supermarket> getListaSupermercatiRicercati(String regione, String[] daCercare) {
		
		return this.daoS.getDaCercare(daCercare, regione);
	}
	
	//metodi ricorsivi 
	
	public List<ComuneCandidato> espandi(int L, String ragione, double perc){
		
		List<ComuneCandidato> result = new LinkedList<ComuneCandidato>();
	
		double punteggioMax = 0;
		
		for(String regione : this.getRegioni()) {
			
			
			List<ComuneCandidato> parziale = new LinkedList<ComuneCandidato>();
		
			Map<String, ComuneCandidato> mappaCandidati = daoC.getComuniCandidati(regione, ragione, mappaComuni, perc);
		    
			cerca(parziale, 0, mappaCandidati, L);
	
			double punteggio = calcolaPunteggio(parziale);
			
			if(punteggio>punteggioMax) {
				punteggioMax = punteggio;
				result = parziale;
			}
		
		}
		
		return result;
	}
	
	private double calcolaPunteggio(List<ComuneCandidato> parziale) {
		
		double result = 0.0;
				
		for(ComuneCandidato cc : parziale) {
			result+=cc.getPunteggio();
		}
		
		return result;
	}

	private void cerca(List<ComuneCandidato> parziale, int l, Map<String,ComuneCandidato> mappaCandidati, int lMax) {
		
		
		if(l==lMax) 
		 return;
		
		 //Ad ogni passo aggiungo il migliore possibile
		 
		 ComuneCandidato elemento = cercaMassimo(mappaCandidati);
		 parziale.add(elemento);
		 
		 cerca(parziale,l+1,mappaCandidati,lMax);
		
	}

    /**
     * Tale metodo si occupa di cercare il ComuneCandidato avente il massimo punteggio.
     * In tal senso si serve di una lista, dove sono caricati i valori della mappaCandidati, passata come parametro.
     * Una volta trovato il massimo, viene restituito il ComuneCandidato associato a tale massimo.
     * Tale metodo si occupa anche di aggiornare il punteggio di tale comune nella mappa dei comuni candidati
     * @param mappaCandidati
     * @return ComuneCandidato da inserire nella soluzione parziale
     */
	private ComuneCandidato cercaMassimo(Map<String,ComuneCandidato> mappaCandidati) {
		
		List<ComuneCandidato> listaCand = new LinkedList<>(mappaCandidati.values());
		
		ComuneCandidato cc = listaCand.get(0);
		
		for(ComuneCandidato candidato: listaCand) {
			if(candidato.getPunteggio()>cc.getPunteggio())
				cc=candidato;
		}
		
		ComuneCandidato result = cc;
		System.out.println(result);
		
	    cc.aggiornaPunteggio();
		mappaCandidati.put(cc.getComune().getNomeComune(), cc);
		//System.out.println(mappaCandidati.get(result.getComune().getNomeComune()));
		return result;
	}
	
      public List<ComuneCandidato> espandi(int L, String ragione, String regione, double perc){
		
		    List<ComuneCandidato> parziale = new LinkedList<ComuneCandidato>();
		
			Map<String, ComuneCandidato> mappaCandidati = daoC.getComuniCandidati(regione, ragione, mappaComuni, perc);
		    
			cerca(parziale, 0, mappaCandidati, L);
		
		    return parziale;
      }
      
      public List<ComuneCandidato> espandiZeroPresenti(int L, String ragione, double perc){
  		
  		List<ComuneCandidato> result = new LinkedList<ComuneCandidato>();
  	
  		double punteggioMax = 0;
  		
  		for(String regione : this.getRegioni()) {
  			
  			List<ComuneCandidato> parziale = new LinkedList<ComuneCandidato>();
  		
  			Map<String, ComuneCandidato> mappaCandidati = daoC.getComuniCandidatiZeroPresenti(regione, ragione, mappaComuni, perc);
  		    
  			if(!mappaCandidati.isEmpty()) {
  			
  			cerca(parziale, 0, mappaCandidati, L);
  	
  			double punteggio = calcolaPunteggio(parziale);
  			
  			if(punteggio>punteggioMax) {
  				punteggioMax = punteggio;
  				result = parziale;
  			}
  		  }
  		}
  		
  		return result;
  	}
      
    public List<ComuneCandidato> espandiZeroPresenti(int L, String ragione, String regione, double perc){
  		
		    List<ComuneCandidato> parziale = new LinkedList<ComuneCandidato>();
		
			Map<String, ComuneCandidato> mappaCandidati = daoC.getComuniCandidatiZeroPresenti(regione, ragione, mappaComuni, perc);
		   
			if(!mappaCandidati.isEmpty()) 
			   cerca(parziale, 0, mappaCandidati, L);
		
			
		    return parziale;
    }

	public List<Supermarket> getRicercaInComune(String comune) {
	
		return daoS.getSuperInComune(comune);
	}
      
}
