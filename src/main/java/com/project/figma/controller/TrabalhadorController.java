package com.project.figma.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.project.figma.entities.dto.TrabalhadorDTO;
import com.project.figma.entities.dto.TrabalhadorDtoPOST;
import com.project.figma.service.TrabalhadorService;

@RestController
@RequestMapping(value = "/api/v1/trabalhadores")
public class TrabalhadorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrabalhadorController.class);

	@Autowired
	private TrabalhadorService trabalhadorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TrabalhadorDTO> find(@PathVariable Integer id){
		TrabalhadorDTO obj = trabalhadorService.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<TrabalhadorDTO>> findAll(){
		List<TrabalhadorDTO> obj = trabalhadorService.findAll();
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody TrabalhadorDtoPOST trabalhadorDtoPOST){
		LOGGER.info("m=insert stage=init trabalhadorDtoPOST:{}", trabalhadorDtoPOST);
		var id = trabalhadorService.insert(trabalhadorDtoPOST).getId();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		LOGGER.info("m=insert stage=finish trabalhadorDtoPOST:{}", trabalhadorDtoPOST);
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TrabalhadorDTO> delete(@PathVariable Integer id, @RequestBody TrabalhadorDTO obj){
		obj.setId(id);
		var newObj1 = trabalhadorService.update(obj);
		TrabalhadorDTO newObj2 = new TrabalhadorDTO(newObj1);
		return ResponseEntity.ok().body(newObj2);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TrabalhadorDTO> delete(@PathVariable Integer id){
		trabalhadorService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
