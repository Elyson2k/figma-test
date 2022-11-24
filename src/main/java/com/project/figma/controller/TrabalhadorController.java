package com.project.figma.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.figma.entities.model.Trabalhador;
import com.project.figma.entities.dto.TrabalhadorDtoPOST;
import com.project.figma.service.TrabalhadorService;

@RestController
@RequestMapping(value = "/api/v1/trabalhadores")
public class TrabalhadorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrabalhadorController.class);
	private final TrabalhadorService trabalhadorService;

	public TrabalhadorController(TrabalhadorService trabalhadorService) {
		this.trabalhadorService = trabalhadorService;
	}

	// TODO adicionar documentação
	@GetMapping(value = "/{id}")
	public ResponseEntity<Trabalhador> find(@PathVariable Integer id){
		Trabalhador obj = trabalhadorService.find(id);
		return ResponseEntity.ok(obj);
	}

	// TODO adicionar documentação
	@GetMapping
	public ResponseEntity<List<Trabalhador>> findAll(){
		List<Trabalhador> obj = trabalhadorService.findAll();
		return ResponseEntity.ok(obj);
	}

	// TODO adicionar documentação
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody TrabalhadorDtoPOST trabalhadorDtoPOST){
		LOGGER.info("m=insert stage=init trabalhadorDtoPOST:{}", trabalhadorDtoPOST);
		var id = trabalhadorService.insert(trabalhadorDtoPOST).getId();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		LOGGER.info("m=insert stage=finish trabalhadorDtoPOST:{}", trabalhadorDtoPOST);
		return ResponseEntity.created(uri).build();
	}
	
}
