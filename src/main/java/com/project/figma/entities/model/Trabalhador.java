package com.project.figma.entities.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "TRABALHADOR")
public class Trabalhador implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;	
	@Column(unique = true)
	private String cpf;
	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;
	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
	
	public Trabalhador(Integer id, String name, String email, String cpf, Setor setor, Cargo cargo) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.setor = setor;
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public Trabalhador setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Trabalhador setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Trabalhador setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public Trabalhador setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public Setor getSetor() {
		return setor;
	}

	public Trabalhador setSetor(Setor setor) {
		this.setor = setor;
		return this;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public Trabalhador setCargo(Cargo cargo) {
		this.cargo = cargo;
		return this;
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
		Trabalhador other = (Trabalhador) obj;
		return Objects.equals(id, other.id);
	}
}
