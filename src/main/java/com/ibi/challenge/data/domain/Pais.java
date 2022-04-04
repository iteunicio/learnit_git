package com.ibi.challenge.data.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

@Entity
public class Pais extends RepresentationModel<Pais> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pais_id")
	private Long id;
	
	@Column(name = "nome")
	@NotBlank(message = "Nome é um campo obrigatório!")
	private String nome;
	
	@Column(name = "regiao")
	@NotBlank(message = "Região é um campo obrigatório!")
	private String regiao;
	
	@Column(name = "sub_regiao")
	private String subRegiao;
	
	@Column(name = "area")
	private double area;
	
	public Pais() {}

	
	public Pais(String nome, String regiao, String subRegiao, double area) {
		this.nome = nome;
		this.regiao = regiao;
		this.subRegiao = subRegiao;
		this.area = area;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id, nome);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj) || getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(id, other.id) 
				&& Objects.equals(nome, other.nome);
	}


	@Override
	public String toString() {
		return "Pais={Nome: " + nome + ", Região: " + regiao + 
				", Sub-Região: " + subRegiao + 
				", Area (km²): " + area +"}";
	}
}