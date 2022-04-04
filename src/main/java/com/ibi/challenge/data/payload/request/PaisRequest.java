package com.ibi.challenge.data.payload.request;

public class PaisRequest {

	private String nome;
	
	private String regiao;
	
	private String subRegiao;
	
	private double area;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getRegiao() {
		return regiao;
	}
	
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	
	public String getSubRegiao() {
		return subRegiao;
	}
	
	public void setSubRegiao(String subRegiao) {
		this.subRegiao = subRegiao;
	}
	
	public double getArea() {
		return area;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
}