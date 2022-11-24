package com.project.figma.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.figma.entities.dto.SetorDTO;
import com.project.figma.entities.dto.SetorDtoPOST;
import com.project.figma.service.SetorService;

@RestController
@RequestMapping(value = "/api/v1/setores")
public class SetorController {

	@Autowired
	private SetorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SetorDTO> find(@PathVariable Integer id){
		SetorDTO obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<SetorDTO>> findAll(){
		List<SetorDTO> obj = service.findAll();
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody SetorDtoPOST obj){
		var newObj = service.inserSetor(obj);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SetorDTO> delete(@PathVariable Integer id, @RequestBody SetorDTO obj){
		obj.setId(id);
		var newObj1 = service.update(obj);
		SetorDTO newObj2 = new SetorDTO(newObj1);
		return ResponseEntity.ok().body(newObj2);
	}
	
}
