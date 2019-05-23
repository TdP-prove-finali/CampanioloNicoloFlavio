package it.polito.tdp.provafinale.supermarket.model;


public class Supermarket implements Comparable<Supermarket> {
	
	private String ragione;
	private String indirizzo;
	private String cap;
	private Comune citta;
	
	public Supermarket(String ragione, String indirizzo, String cap, Comune citta) {
		super();
		
		this.ragione = ragione;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
	}

	
	public String getRagione() {
		return ragione;
	}

	public void setRagione(String ragione) {
		this.ragione = ragione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public Comune getCitta() {
		return citta;
	}

	public void setCitta(Comune citta) {
		this.citta = citta;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((ragione == null) ? 0 : ragione.hashCode());
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
		Supermarket other = (Supermarket) obj;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (ragione == null) {
			if (other.ragione != null)
				return false;
		} else if (!ragione.equals(other.ragione))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return ragione+" "+indirizzo+" "+cap+" "+citta;
	}


	@Override
	public int compareTo(Supermarket s) {
		
		return this.ragione.compareTo(s.ragione);
	}
	
	
	
	

}

