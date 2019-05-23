package it.polito.tdp.provafinale.supermarket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.provafinale.supermarket.model.Comune;
import it.polito.tdp.provafinale.supermarket.model.Supermarket;


public class SupermarketDAO {
	
	private static final int NUM_ELEMENTI_LISTA = 30;
	private Map<String, Comune> mappaComuni;
	
	public SupermarketDAO(Map<String, Comune> mappaComuni) {
		this.mappaComuni=mappaComuni;
	}
	
	/**
	 * Tale metodo serve a restituire la liste di catene di supermercati in ordine decrescente
	 * di frequenza.
	 * @return List di String contententi le prime NUM_ELEMENTI_LISTA catene di supermercati
	 */
	
	public List<String> getListaCatene(){
		
		List <String> catene = new ArrayList<String>();
		

		String sql = "SELECT ragione, COUNT(ragione) AS cnt FROM elenco_supermarket GROUP BY ragione ORDER BY cnt DESC"; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			for(int i=0; res.next() && i<NUM_ELEMENTI_LISTA ; i++){
				
				catene.add(res.getString("ragione"));
			}

			conn.close();
			
			return catene;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Supermarket> getSupermercatiCatena(String catena) {
		
		List <Supermarket> supermarket = new ArrayList<>();
		
        
		String sql = "SELECT DISTINCT ragione, indirizzo, cap, citta FROM elenco_supermarket WHERE ragione =?"; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, catena);
			
			ResultSet res = st.executeQuery();

			
			while (res.next()) {
				
				String ragione = res.getString("ragione");
				String indirizzo = res.getString("indirizzo");
				String cap = res.getString("cap");
				String citta = res.getString("citta").trim();
				
				Supermarket sm = new Supermarket(ragione, indirizzo, cap, mappaComuni.get(citta));
				
				supermarket.add(sm);
				
				
				
			}

			conn.close();
			
   			return supermarket;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Supermarket> getSupermercatiCatena(String regione, String catena) {

		List <Supermarket> supermarket = new ArrayList<>();
		
        
		String sql = "SELECT DISTINCT ragione, indirizzo, cap, citta FROM elenco_supermarket WHERE regione =? AND ragione=?"; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, regione);
			st.setString(2, catena);
			
			ResultSet res = st.executeQuery();

			
			while (res.next()) {
				
				String ragione = res.getString("ragione");
				String indirizzo = res.getString("indirizzo");
				String cap = res.getString("cap");
				String citta = res.getString("citta").trim();
				
				Supermarket sm = new Supermarket(ragione, indirizzo, cap, mappaComuni.get(citta));
				
				supermarket.add(sm);
				
				
				
			}

			conn.close();
			
   			return supermarket;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Supermarket> getSupermercatiRegione(String regione) {

		List <Supermarket> supermarket = new LinkedList<>();
		
        
		String sql = "SELECT DISTINCT ragione, indirizzo, cap, citta FROM elenco_supermarket WHERE regione =?"; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, regione);
			
			
			ResultSet res = st.executeQuery();

			
			while (res.next()) {
				
				String ragione = res.getString("ragione");
				String indirizzo = res.getString("indirizzo");
				String cap = res.getString("cap");
				String citta = res.getString("citta").trim();
				
				Supermarket sm = new Supermarket(ragione, indirizzo, cap, mappaComuni.get(citta));
				
				supermarket.add(sm);
				
				
				
			}

			conn.close();
			
   			return supermarket;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Supermarket> getDaCercare(String[] daCercare) {

		List <Supermarket> supermarket = new LinkedList<>();
		
        
		String sql = "SELECT DISTINCT ragione, indirizzo, cap, citta FROM elenco_supermarket WHERE ragione LIKE ? OR ragione LIKE ? OR ragione LIKE ? "; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, daCercare[0]);
			st.setString(2, daCercare[1]);
			st.setString(3, daCercare[2]);
			
			ResultSet res = st.executeQuery();

			
			while (res.next()) {
				
				String ragione = res.getString("ragione");
				String indirizzo = res.getString("indirizzo");
				String cap = res.getString("cap");
				String citta = res.getString("citta").trim();
				
				Supermarket sm = new Supermarket(ragione, indirizzo, cap, mappaComuni.get(citta));
				
				supermarket.add(sm);
				
				
				
			}

			conn.close();
			
   			return supermarket;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Supermarket> getDaCercare(String[] daCercare, String regione) {
	
		List <Supermarket> supermarket = new LinkedList<>();
		
        
		String sql = "SELECT DISTINCT ragione, indirizzo, cap, citta FROM elenco_supermarket WHERE regione = ? AND (ragione LIKE ? OR ragione LIKE ? OR ragione LIKE ?) "; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, regione);
			
			st.setString(2, daCercare[0]);
			st.setString(3, daCercare[1]);
			st.setString(4, daCercare[2]);
			
			ResultSet res = st.executeQuery();

			
			while (res.next()) {
				
				String ragione = res.getString("ragione");
				String indirizzo = res.getString("indirizzo");
				String cap = res.getString("cap");
				String citta = res.getString("citta").trim();
				
				Supermarket sm = new Supermarket(ragione, indirizzo, cap, mappaComuni.get(citta));
				
				supermarket.add(sm);
				
				
				
			}

			conn.close();
			
   			return supermarket;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Supermarket> getSuperInComune(String comune) {
		
	    List <Supermarket> supermarket = new LinkedList<>();
		
		String sql = "SELECT DISTINCT ragione, indirizzo, cap, citta FROM elenco_supermarket WHERE citta = ?"; 	
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, comune);
			ResultSet res = st.executeQuery();

			
			while (res.next()) {
				
				String ragione = res.getString("ragione");
				String indirizzo = res.getString("indirizzo");
				String cap = res.getString("cap");
				String citta = res.getString("citta").trim();
				
				if(mappaComuni.get(citta)!=null) {
				
				Supermarket sm = new Supermarket(ragione, indirizzo, cap, mappaComuni.get(citta));
				
				supermarket.add(sm);
				
				}
				
			}

			conn.close();
			
   			return supermarket;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
	}
