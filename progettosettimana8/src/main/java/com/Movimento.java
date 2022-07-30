package com;

public class Movimento {
	private String dataMovimento;
	private double importo;
	private String iban;
	private TipoOperazione tipo;
//////////////////////getters e setters/////////////////////////////////////

	public String getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(String dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public TipoOperazione getTipo() {
		return tipo;
	}

	public void setTipo(TipoOperazione tipo) {
		this.tipo = tipo;
	}

}
