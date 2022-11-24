package com.project.figma.entities.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "CARGO")
public class Cargo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
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
	
	
	
}
