package com.project.figma.entities.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TrabalhadorDtoPOST implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120)
	private String name;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Column(unique = true)
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Column(unique = true)
	private String cpf;
	
	private Integer cargoId;
	
}
