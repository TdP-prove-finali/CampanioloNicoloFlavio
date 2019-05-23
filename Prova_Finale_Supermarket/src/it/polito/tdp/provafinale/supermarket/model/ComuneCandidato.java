package it.polito.tdp.provafinale.supermarket.model;


public class ComuneCandidato implements Comparable<ComuneCandidato>{
	
	private Comune comune;
	private double punteggio;
	private int tuttiSuper;
	private int catenaSuper;
	private double perc;
	private double numSuper;
	
	public ComuneCandidato(Comune comune, int tuttiSuper, int catenaSuper, double perc) {
		
		this.comune = comune;
		this.tuttiSuper = tuttiSuper;
		this.catenaSuper = catenaSuper;
		this.perc=perc;
		this.numSuper = tuttiSuper + (perc)*catenaSuper;
		
		this.punteggio=comune.getAbitanti()/this.numSuper;
		
	}

	public int getTuttiSuper() {
		return tuttiSuper;
	}

	public int getCatenaSuper() {
		return catenaSuper;
	}

	public double getNumSuper() {
		return numSuper;
	}

	public Comune getComune() {
		return comune;
	}
	public double getPunteggio() {
		return punteggio;
	}

	public void aggiornaPunteggio() {
	
		this.tuttiSuper++;
		this.catenaSuper++;
		
		this.numSuper = tuttiSuper + (perc)*catenaSuper;
		this.punteggio = this.comune.getAbitanti()/this.numSuper;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comune == null) ? 0 : comune.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComuneCandidato other = (ComuneCandidato) obj;
		if (comune == null) {
			if (other.comune != null)
				return false;
		} else if (!comune.equals(other.comune))
			return false;
		return true;
	}


	public String toString() {
		return comune+" "+punteggio;
	}

	@Override
	public int compareTo(ComuneCandidato cc) {
		// TODO Auto-generated method stub
		return this.comune.getNomeComune().compareTo(cc.comune.getNomeComune());
	}

}
