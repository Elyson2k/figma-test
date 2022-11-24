package com.project.figma.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "CARGO")
public class Cargo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "SETOR_ID")
	private Setor setor;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cargo")
	private List<Trabalhador> trabalhadores = new ArrayList<>();
	
	
	
	public Cargo(Integer id, String name, Setor setor) {
		super();
		this.id = id;
		this.name = name;
		this.setor = setor;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		return Objects.equals(id, other.id);
	}



	public Cargo setId(Integer id) {
		this.id = id;
		return this;
	}



	public Cargo setName(String name) {
		this.name = name;
		return this;
	}



	public Cargo setSetor(Setor setor) {
		this.setor = setor;
		return this;
	}



	public Cargo setTrabalhadores(List<Trabalhador> trabalhadores) {
		this.trabalhadores = trabalhadores;
		return this;
	}
	
}
