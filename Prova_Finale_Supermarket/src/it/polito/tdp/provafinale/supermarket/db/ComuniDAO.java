package it.polito.tdp.provafinale.supermarket.db;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.HashMap;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;

import it.polito.tdp.provafinale.supermarket.model.Comune;
import it.polito.tdp.provafinale.supermarket.model.ComuneCandidato;

	public class ComuniDAO {

		public List <String> getRegioni(){
			
			List <String> regioni = new LinkedList<>();
			
			String sql = "SELECT regione FROM abitanti_cpr GROUP BY regione ORDER BY regione"; 	
		
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();

				while (res.next()) {
					
					regioni.add(res.getString("regione"));
				}

				conn.close();
				
	   			return regioni;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}

		public List<Comune> getTuttiComuni(Map<String, Comune> mappaComuni) {
	        
			List <Comune> comuni = new LinkedList<>();
			
			String sql = "SELECT comune, provincia, regione, abitanti FROM abitanti_cpr"; 	
		
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();

				while (res.next()) {
					
					Comune co = new Comune(res.getString("comune").toUpperCase(),res.getString("provincia"),res.getString("regione"),res.getInt("abitanti"));
					mappaComuni.put(co.getNomeComune(), co);
					comuni.add(co);
				}
				conn.close();
				
	   			return comuni;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}

		public Map<String, ComuneCandidato> getComuniCandidati(String regione, String ragione,
				Map<String, Comune> mappaComuni, double perc) {
			
			Map<String, ComuneCandidato> result = new HashMap<>();
			
			String sql ="SELECT citta, COUNT(*) tutteRagioni, sum(case when ragione = ? then 1 else 0 end) ragioneCercata FROM elenco_supermarket WHERE regione = ? GROUP BY citta  ";
		
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1, ragione);
				st.setString(2, regione);
				
				ResultSet res = st.executeQuery();

				while (res.next()) {
					
					
					ComuneCandidato co = new ComuneCandidato(mappaComuni.get(res.getString("citta").trim()),res.getInt("tutteRagioni"),res.getInt("ragioneCercata"),perc);
					
					result.put((res.getString("citta").trim()), co);
				
				}
				conn.close();
				
	   			return result;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}

		public Map<String, ComuneCandidato> getComuniCandidatiZeroPresenti(String regione, String ragione,
				Map<String, Comune> mappaComuni, double perc) {
	Map<String, ComuneCandidato> result = new HashMap<>();
			
			String sql ="SELECT citta, COUNT(*) tutteRagioni, sum(case when ragione = ? then 1 else 0 end) ragioneCercata FROM elenco_supermarket WHERE regione = ? GROUP BY citta  ";
		
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1, ragione);
				st.setString(2, regione);
				
				ResultSet res = st.executeQuery();

				while (res.next()) {
					
					
					ComuneCandidato co = new ComuneCandidato(mappaComuni.get(res.getString("citta").trim()),res.getInt("tutteRagioni"),res.getInt("ragioneCercata"),perc);
					if(co.getCatenaSuper()==0)
					  result.put((res.getString("citta").trim()), co);
				
				}
				conn.close();
				
	   			return result;

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
			
			
		

}
