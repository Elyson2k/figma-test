package com.project.figma.entities.dto;

import java.io.Serializable;

import com.project.figma.entities.Setor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SetorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public SetorDTO(Setor obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
	}
	
	
}
