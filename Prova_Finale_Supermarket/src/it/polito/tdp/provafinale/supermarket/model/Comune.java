package it.polito.tdp.provafinale.supermarket.model;

public class Comune {
	
	private String nomeComune;
	private String provincia;
	private String regione;
	private int abitanti;
	
	public Comune(String comune, String privincia, String regione, int abitanti) {
		super();
		this.nomeComune = comune;
		this.provincia = privincia;
		this.regione = regione;
		this.abitanti = abitanti;
	}

	public String getNomeComune() {
		return nomeComune;
	}

	public void setNomeComune(String comune) {
		this.nomeComune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String privincia) {
		this.provincia = privincia;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public int getAbitanti() {
		return abitanti;
	}

	public void setAbitanti(int abitanti) {
		this.abitanti = abitanti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeComune == null) ? 0 : nomeComune.hashCode());
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
		Comune other = (Comune) obj;
		if (nomeComune == null) {
			if (other.nomeComune != null)
				return false;
		} else if (!nomeComune.equals(other.nomeComune))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeComune+" "+provincia+" "+regione;
	}
	
	

}
