package com.project.figma.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "SETOR")
public class Setor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
	private List<Cargo> cargos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
	private List<Trabalhador> trabalhadores = new ArrayList<>();

	public Setor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		Setor other = (Setor) obj;
		return Objects.equals(id, other.id);
	}
	
}
