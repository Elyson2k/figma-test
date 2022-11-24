package com.project.figma.entities.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

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
	
	@Email(message = "ERROR: Email incorreto.")
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Column(unique = true)
	private String email;
	
	@CPF(message = "ERROR: CPF Inválido.")
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Column(unique = true)
	private String cpf;
	
	private Integer cargoId;
	
}
