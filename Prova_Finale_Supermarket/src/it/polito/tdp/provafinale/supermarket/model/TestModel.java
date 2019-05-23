package it.polito.tdp.provafinale.supermarket.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.getRegioni();
	    
		List <ComuneCandidato> result=m.espandiZeroPresenti(20, "Ekom", "    LOMBARDIA",0);
		System.out.println(result);
		
	
	}

}
