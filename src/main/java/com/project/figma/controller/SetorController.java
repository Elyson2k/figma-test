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

import com.project.figma.entities.model.Setor;
import com.project.figma.entities.dto.SetorDtoPOST;
import com.project.figma.service.SetorService;

@RestController
@RequestMapping(value = "/api/v1/setores")
public class SetorController {

	@Autowired
	private SetorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Setor> find(@PathVariable Integer id){
		Setor obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Setor>> findAll(){
		List<Setor> obj = service.findAll();
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody SetorDtoPOST obj){
		Setor newObj = service.fromDto(obj);
		newObj = service.inserSetor(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
