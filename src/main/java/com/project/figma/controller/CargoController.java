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
import com.project.figma.entities.model.Cargo;
import com.project.figma.entities.dto.CargoDtoPOST;
import com.project.figma.service.CargoService;

@RestController
@RequestMapping(value = "/api/v1/cargos")
public class CargoController {
	private final CargoService service;
	public CargoController(CargoService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cargo> find(@PathVariable Integer id){
		Cargo obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Cargo>> findAll(){
		List<Cargo> obj = service.findAll();
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CargoDtoPOST obj){
		Cargo newObj = service.fromDto(obj);
		newObj = service.insert(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
