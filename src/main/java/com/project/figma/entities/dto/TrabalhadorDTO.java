package com.project.figma.entities.dto;

import java.io.Serializable;

import com.project.figma.entities.Cargo;
import com.project.figma.entities.Trabalhador;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TrabalhadorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private String cpf;
	private Cargo cargo;
	
	public TrabalhadorDTO(Trabalhador obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.cargo = obj.getCargo();
	}
	
}
