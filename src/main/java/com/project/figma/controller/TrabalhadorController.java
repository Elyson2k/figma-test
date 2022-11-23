package com.project.figma.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.figma.entities.Trabalhador;
import com.project.figma.entities.dto.TrabalhadorDtoPOST;
import com.project.figma.service.TrabalhadorService;

@RestController
@RequestMapping(value = "/api/v1/trabalhadores")
public class TrabalhadorController {

	@Autowired
	private TrabalhadorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Trabalhador> find(@PathVariable Integer id){
		Trabalhador obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Trabalhador>> findAll(){
		List<Trabalhador> obj = service.findAll();
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody TrabalhadorDtoPOST obj){
		Trabalhador newObj = service.fromDto(obj);
		newObj = service.insert(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
