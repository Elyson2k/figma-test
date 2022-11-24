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

import com.project.figma.entities.dto.CargoDTO;
import com.project.figma.entities.dto.CargoDtoALL;
import com.project.figma.entities.dto.CargoDtoPOST;
import com.project.figma.service.CargoService;

@RestController
@RequestMapping(value = "/api/v1/cargos")
public class CargoController {

	@Autowired
	private CargoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CargoDTO> find(@PathVariable Integer id){
		CargoDTO obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<CargoDtoALL>> findAll(){
		List<CargoDtoALL> obj = service.findAll();
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CargoDtoPOST obj){
		var newObj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CargoDTO> delete(@PathVariable Integer id, @RequestBody CargoDTO obj){
		obj.setId(id);
		var newObj1 = service.Update(obj);
		CargoDTO newObj2 = new CargoDTO(newObj1);
		return ResponseEntity.ok().body(newObj2);
	}
	
}
