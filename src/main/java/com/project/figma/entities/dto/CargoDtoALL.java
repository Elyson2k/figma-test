package com.project.figma.entities.dto;

import java.io.Serializable;

import com.project.figma.entities.Cargo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CargoDtoALL implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CargoDtoALL(Cargo obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
	
	
}
