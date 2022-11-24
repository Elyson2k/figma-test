package com.project.figma.entities.dto;

import java.io.Serializable;

import com.project.figma.entities.Cargo;
import com.project.figma.entities.Setor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CargoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Setor setor;
	
	public CargoDTO(Cargo obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.setor = obj.getSetor();
	}
	
	
}
