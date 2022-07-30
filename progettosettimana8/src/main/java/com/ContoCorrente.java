package com;

public class ContoCorrente {
	
	private String iban;
	private String data;
	private double saldo;
	private String intestatario;

	/////////////// GETTERS E SETTERS///////////////////

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	/////////////////////// costruttore vuoto//////////////////

	public ContoCorrente() {

	}

}
